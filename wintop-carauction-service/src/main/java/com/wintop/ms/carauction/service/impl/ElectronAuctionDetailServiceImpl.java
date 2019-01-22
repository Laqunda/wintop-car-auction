package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarAuctionBidRecordModel;
import com.wintop.ms.carauction.model.CarLocaleAuctionCarModel;
import com.wintop.ms.carauction.model.TblAuctionLogModel;
import com.wintop.ms.carauction.service.ElectronAuctionDetailService;
import com.wintop.ms.carauction.util.CommonServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectronAuctionDetailServiceImpl implements ElectronAuctionDetailService {
    @Autowired
    private CarLocaleAuctionCarModel auctionCarModel;
    @Autowired
    private TblAuctionLogModel auctionLogModel;
    @Autowired
    private CarAuctionBidRecordModel bidRecordModel;
    @Override
    public ElectronAuctionDetail selectElectronAuctionDetail(Long localeAuctionId) {
        ElectronAuctionDetail auctionDetail = new ElectronAuctionDetail();
        auctionDetail.setLocaleAuctionId(localeAuctionId);
        CarLocaleAuctionCar localeAuctionCar = auctionCarModel.selectCurrentAuctionCar(localeAuctionId);
        if(localeAuctionCar==null){
            auctionDetail.setSynch("0");
        }else{
            auctionDetail.setSynch("1");
            auctionDetail.setAuctionCarId(localeAuctionCar.getId());
            //当前价格幅度
            auctionDetail.setPriceRange(localeAuctionCar.getPriceRange());
            //车辆信息
            ElectronAuctionCar auctionCar = CommonServiceUtils.getAuctionCar(localeAuctionCar);
            auctionDetail.setAuctionCar(auctionCar);
            //出价记录
            Map<String,Object> map = new HashMap<>();
            map.put("priceType","0");
            map.put("auctionCarId",localeAuctionCar.getId());
            map.put("startRowNum",0);
            map.put("endRowNum",5);
            List<TblAuctionLog> auctionLogs =  auctionLogModel.selectByExample(map);
            List<ElectronAuctionBidRecord> bidRecords = CommonServiceUtils.getBidRecords(auctionLogs);
            auctionDetail.setBidRecords(bidRecords);
            //当前打点价格
            //查询是否首次调价器
            TblAuctionLog auctionLog = auctionLogModel.selectByAuctionCarId(localeAuctionCar.getId(),"2");
            if(auctionLog==null){
                auctionDetail.setBidFee(new BigDecimal(0));
            }else{
                auctionDetail.setBidFee(auctionLog.getBidFee());
            }
        }
        return auctionDetail;
    }

    /**
     * 查询拍卖详情
     * @param map
     * @return
     */
    public ElectronAuctionCarDetail selectAuctionCarDetail(Map<String,Object> map){
        Long auctionCarId = (Long)map.get("auctionCarId");
        ElectronAuctionCarDetail auctionCarDetail = new ElectronAuctionCarDetail();
        auctionCarDetail.setAuctionCarId(auctionCarId);
        CarLocaleAuctionCar localeAuctionCar = auctionCarModel.selectById(auctionCarId);
        if(localeAuctionCar!=null){
            auctionCarDetail.setLocaleAuctionId(localeAuctionCar.getAuctionId());
            //,,车辆信息
            ElectronAuctionCar auctionCar = CommonServiceUtils.getAuctionCar(localeAuctionCar);
            //,,最后出价
            CarAuctionBidRecord bidRecord = bidRecordModel.selectLastBidRecord(auctionCarId);
            if(bidRecord!=null){
                auctionCar.setLastPrice(bidRecord.getBidFee());
            }
            auctionCarDetail.setAuctionCar(auctionCar);
            //,,出价记录
            map.put("priceType","0");
            List<TblAuctionLog> auctionLogs0 =  auctionLogModel.selectByExample(map);
            int count0 = auctionLogModel.countByExample(map);
            ListEntity<ElectronAuctionBidRecord> bidRecords = CommonServiceUtils.getBidRecordListEntity(auctionLogs0,count0);
            auctionCarDetail.setBidRecords(bidRecords);
            //,,加价记录
            map.put("priceType","2");
            List<TblAuctionLog> auctionLogs2 =  auctionLogModel.selectByExample(map);
            int count2 = auctionLogModel.countByExample(map);
            ListEntity<ElectronAuctionBidRecord> addRecords = CommonServiceUtils.getBidRecordListEntity(auctionLogs2,count2);
            auctionCarDetail.setAddRecords(addRecords);
            //,,价格幅度调整记录
            map.put("priceType","3");
            List<TblAuctionLog> auctionLogs3 =  auctionLogModel.selectByExample(map);
            int count3 = auctionLogModel.countByExample(map);
            ListEntity<ElectronAuctionBidRecord> rangeRecords = CommonServiceUtils.getBidRecordListEntity(auctionLogs3,count3);
            auctionCarDetail.setRangeRecords(rangeRecords);
        }
        return auctionCarDetail;
    }
}
