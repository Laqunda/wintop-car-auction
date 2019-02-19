package com.wintop.ms.carauction.util;

import com.wintop.ms.carauction.entity.*;

import java.util.ArrayList;
import java.util.List;

public class CommonServiceUtils {

    public static ElectronAuctionCar getAuctionCar(CarLocaleAuctionCar localeAuctionCar){
        ElectronAuctionCar auctionCar = new ElectronAuctionCar();
        if(localeAuctionCar!=null){
            auctionCar.setAuctionTimesName(localeAuctionCar.getTitle());
            auctionCar.setStartTime(localeAuctionCar.getStartTime());
            auctionCar.setCarAutoNo(localeAuctionCar.getCarAutoNo());
            auctionCar.setAutoInfoName(localeAuctionCar.getAutoInfoName());
            auctionCar.setLicenseNumber(localeAuctionCar.getLicenseNumber());
            auctionCar.setStartingPrice(localeAuctionCar.getStartingPrice());
            auctionCar.setServicePrice(localeAuctionCar.getServicePrice());
            auctionCar.setStoreName(localeAuctionCar.getStoreName());
            auctionCar.setAuctionStatus(localeAuctionCar.getAuctionStatus());
        }
        return auctionCar;
    }

    public static List<ElectronAuctionBidRecord> getBidRecords(List<TblAuctionLog> auctionLogs){
        List<ElectronAuctionBidRecord> auctionBidRecords = new ArrayList<>();
        if(auctionLogs!=null && auctionLogs.size()>0){
            for(TblAuctionLog auctionLog:auctionLogs){
                ElectronAuctionBidRecord auctionBidRecord = new ElectronAuctionBidRecord();
                auctionBidRecord.setBoardName(auctionLog.getBoardName());
                auctionBidRecord.setCustomerName(auctionLog.getCustomerName());
                auctionBidRecord.setPriceRange(auctionLog.getPriceRange());
                auctionBidRecord.setBidFee(auctionLog.getBidFee());
                auctionBidRecord.setAuctionTime(auctionLog.getAuctionTime());
                auctionBidRecords.add(auctionBidRecord);
            }
        }
        return auctionBidRecords;
    }

    public static ListEntity<ElectronAuctionBidRecord> getBidRecordListEntity(List<TblAuctionLog> auctionLogs,int count){
        ListEntity<ElectronAuctionBidRecord> bidRecordListEntity = new ListEntity<>();
        List<ElectronAuctionBidRecord> auctionBidRecords = getBidRecords(auctionLogs);
        bidRecordListEntity.setList(auctionBidRecords);
        bidRecordListEntity.setCount(count);
        return bidRecordListEntity;
    }
}
