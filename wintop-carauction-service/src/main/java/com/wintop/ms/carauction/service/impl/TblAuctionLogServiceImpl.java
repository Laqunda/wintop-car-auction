package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import com.wintop.ms.carauction.entity.TblAuctionLog;
import com.wintop.ms.carauction.entity.TblAuctionTimes;
import com.wintop.ms.carauction.entity.TblDataLog;
import com.wintop.ms.carauction.model.CarLocaleAuctionCarModel;
import com.wintop.ms.carauction.model.TblAuctionLogModel;
import com.wintop.ms.carauction.model.TblAuctionTimesModel;
import com.wintop.ms.carauction.service.TblAuctionLogService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TblAuctionLogServiceImpl implements TblAuctionLogService {
    @Autowired
    private TblAuctionLogModel auctionLogModel;
    @Autowired
    private TblAuctionTimesModel auctionTimesModel;
    @Autowired
    private CarLocaleAuctionCarModel localeAuctionCarModel;
    @Override
    public int countByExample(Map<String, Object> map) {
        return auctionLogModel.countByExample(map);
    }

    @Override
    public List<TblAuctionLog> selectByExample(Map<String, Object> map) {
        return auctionLogModel.selectByExample(map);
    }

    @Override
    public TblAuctionLog selectByPrimaryKey(Long id) {
        return auctionLogModel.selectByPrimaryKey(id);
    }

    @Override
    public int insert(TblAuctionLog tblAuctionLog) {
        return auctionLogModel.insert(tblAuctionLog);
    }

    @Override
    @Transactional
    public TblAuctionTimes saveBidding(Map<String,Object> map){
        TblAuctionTimes auctionTimes = auctionTimesModel.selectAuctionCar(map);
        if(auctionTimes!=null){
            TblAuctionLog auctionLog = new TblAuctionLog();
            auctionLog.setId(IdWorker.getInstance().nextId());
            auctionLog.setBoardRealId((String)map.get("pp"));
            auctionLog.setToken((String)map.get("mm"));
            auctionLog.setStationRealId(auctionTimes.getStationRealId());
            auctionLog.setLocaleAuctionId(auctionTimes.getLocaleAuctionId());
            auctionLog.setAuctionCarId(auctionTimes.getAuctionCarId());
            auctionLog.setCarId(auctionTimes.getCarId());
            auctionLog.setCustomerId(auctionTimes.getCustomerId());
            auctionLog.setAuctionTimesName(auctionTimes.getAuctionTimesName());
            auctionLog.setBoardName(auctionTimes.getBoardName());
            auctionLog.setAuctionTime(new Date());
            auctionLog.setPriceType(auctionTimes.getCuttingSign());
            auctionLog.setEnable("0");
            List<TblAuctionLog> auctionLogs = auctionLogModel.selectBidByAuctionCarId(auctionLog);
            if(auctionLogs.size()==0){
                auctionLogModel.insert(auctionLog);
            }else{
                return null;
            }
        }
        return auctionTimes;
    }

    /**
     * 更新价格幅度调整
     * @param auctionLog
     * @return
     */
    @Transactional
    @Override
    public int updatePriceRange(TblAuctionLog auctionLog){
        auctionLogModel.insert(auctionLog);
        CarLocaleAuctionCar auctionCar = new CarLocaleAuctionCar();
        auctionCar.setId(auctionLog.getAuctionCarId());
        auctionCar.setPriceRange(auctionLog.getBidFee());
        localeAuctionCarModel.updateByIdSelective(auctionCar);
        return 1;
    }

    @Override
    @Transactional
    public int updateBidFeePoint(CarLocaleAuctionCar localeAuctionCar,TblAuctionLog tblAuctionLog,String adjustType){
        //,,查询最后一次打点调价器
        TblAuctionLog auctionLog = auctionLogModel.selectByAuctionCarId(localeAuctionCar.getId(),"2");
        if("2".equals(adjustType)){
            //,,确定打点
            tblAuctionLog.setLocaleAuctionId(localeAuctionCar.getAuctionId());
            tblAuctionLog.setAuctionTimesName(localeAuctionCar.getTitle());
            tblAuctionLog.setAuctionCarId(localeAuctionCar.getId());
            tblAuctionLog.setCarId(localeAuctionCar.getCarId());
            if(auctionLog==null){
                //,,首次打点
                tblAuctionLog.setPriceRange(new BigDecimal(0));
                tblAuctionLog.setBidFee(localeAuctionCar.getStartingPrice());
            }else{
                tblAuctionLog.setPriceRange(localeAuctionCar.getPriceRange());
                tblAuctionLog.setBidFee(auctionLog.getBidFee().add(localeAuctionCar.getPriceRange()));
            }
            auctionLogModel.insert(tblAuctionLog);
            //,,更新出价人记录
            auctionLogModel.updatePriceAdd(tblAuctionLog);
        }else if("1".equals(adjustType)){
            //取消打点
            if(auctionLog!=null){
                auctionLogModel.updatePriceSub(auctionLog.getId(),tblAuctionLog.getCreatePerson());
                auctionLogModel.updateBidFeeSub(auctionLog.getId());
            }
        }
        return 1;
    }

    /**
     * 保存记录
     * @return
     */
    @Override
    public int insertDataLog(String dataType,String dataContent,String resultContent){
        try{
            TblDataLog dataLog = new TblDataLog();
            dataLog.setId(IdWorker.getInstance().nextId());
            dataLog.setDataType(dataType);
            dataLog.setDataContent(dataContent);
            dataLog.setResultContent(resultContent);
            dataLog.setCreateTime(new Date());
            return auctionLogModel.insertDataLog(dataLog);
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 删除历史无效数据
     * @return
     */
    @Override
    public int deleteHisDataLog(){
        return auctionLogModel.deleteHisDataLog();
    }

}
