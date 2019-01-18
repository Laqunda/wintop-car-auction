package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarLocaleAuctionCarModel;
import com.wintop.ms.carauction.model.TblAuctionLogModel;
import com.wintop.ms.carauction.service.ElectronAuctionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectronAuctionDetailServiceImpl implements ElectronAuctionDetailService {
    @Autowired
    private CarLocaleAuctionCarModel auctionCarModel;
    @Autowired
    private TblAuctionLogModel auctionLogModel;
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
            ElectronAuctionCar auctionCar = new ElectronAuctionCar();
            auctionCar.setAuctionTimesName(localeAuctionCar.getTitle());
            auctionCar.setStartTime(localeAuctionCar.getStartTime());
            auctionCar.setCarAutoNo(localeAuctionCar.getCarAutoNo());
            auctionCar.setAutoInfoName(localeAuctionCar.getAutoInfoName());
            auctionCar.setLicenseNumber(localeAuctionCar.getLicenseNumber());
            auctionCar.setStartingPrice(localeAuctionCar.getStartingPrice());
            auctionDetail.setAuctionCar(auctionCar);
            //出价记录
            Map<String,Object> map = new HashMap<>();
            map.put("priceType","0");
            map.put("auctionCarId",localeAuctionCar.getId());
            map.put("startRowNum",0);
            map.put("endRowNum",5);
            List<TblAuctionLog> auctionLogs =  auctionLogModel.selectByExample(map);
            List<ElectronAuctionBidRecord> bidRecords = new ArrayList<>();
            for(TblAuctionLog auctionLog:auctionLogs){
                ElectronAuctionBidRecord bidRecord = new ElectronAuctionBidRecord();
                bidRecord.setBoardName(auctionLog.getBoardName());
                bidRecord.setCustomerName(auctionLog.getCustomerName());
                bidRecord.setPriceRange(auctionLog.getPriceRange());
                bidRecord.setBidFee(auctionLog.getBidFee());
                bidRecord.setAuctionTime(auctionLog.getAuctionTime());
                bidRecords.add(bidRecord);
            }
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

}
