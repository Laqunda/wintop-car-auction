package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarLocaleBoardRecord;
import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.entity.TblBaseStation;
import com.wintop.ms.carauction.service.CarLocaleBoardRecordService;
import com.wintop.ms.carauction.service.ICarLocaleAuctionService;
import com.wintop.ms.carauction.service.TblAuctionLogService;
import com.wintop.ms.carauction.service.TblBaseStationService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/service/localeBoardRecord")
public class CarLocaleBoardRecordApi {
    @Autowired
    private TblBaseStationService baseStationService;
    @Autowired
    private CarLocaleBoardRecordService recordService;
    @Autowired
    private ICarLocaleAuctionService localeAuctionService;
    @Autowired
    private TblAuctionLogService tblAuctionLogService;

    private static final Logger logger = LoggerFactory.getLogger(CarLocaleBoardRecordApi.class);

    @RequestMapping(value = "/saveLocaleBoard",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarLocaleBoardRecord> saveLocaleBoard(@RequestBody Map<String,Object> map) {
        ServiceResult<CarLocaleBoardRecord> result = new ServiceResult<>();
        try {
            tblAuctionLogService.insertDataLog("2",map.toString());
            String jz = (String)map.get("jz");
            String mm = (String)map.get("mm");
            TblBaseStation baseStation = baseStationService.selectByRealIdAndToken(jz,mm);
            if(baseStation==null){
                //基站不存在
                result.setResult(null);
                result.setError(ResultCode.REQUEST_DISABLED.strValue(),ResultCode.REQUEST_DISABLED.getRemark());
                return result;
            }
            CarLocaleAuction localeAuction = localeAuctionService.selectByStationRealId(jz);
            if(localeAuction==null){
                //该基站没有完成场次
                result.setResult(null);
                result.setError(ResultCode.REQUEST_DISABLED.strValue(),ResultCode.REQUEST_DISABLED.getRemark());
                return result;
            }
            Map<String,Object> map1 = new HashMap<>();
            map1.put("localeAuctionId",localeAuction.getId());
            List<CarLocaleBoardRecord> records =  recordService.selectByExample(map1);
            if(records.size()>0){
                //记录已经存在
                result.setResult(null);
                result.setSuccess(ResultCode.DUPLICATE_ADD.strValue(),ResultCode.DUPLICATE_ADD.getRemark());
                return result;
            }else{
                List<CarLocaleBoardRecord> boardRecords = new ArrayList<>();
                String[] pps = ((String)map.get("pps")).split(",");
                for(String pp:pps){
                    CarLocaleBoardRecord boardRecord = new CarLocaleBoardRecord();
                    boardRecord.setId(IdWorker.getInstance().nextId());
                    boardRecord.setLocaleAuctionId(localeAuction.getId());
                    boardRecord.setStationRealId(jz);
                    boardRecord.setBoardRealId(pp);
                    boardRecord.setCreateTime(new Date());
                    boardRecord.setRecordDate((String)map.get("rq"));
                    boardRecords.add(boardRecord);
                }
                recordService.saveBatchLocaleBoardRecord(boardRecords);
                result.setResult(null);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                return result;
            }
        }catch (Exception e){
            logger.info("提交电子竞价失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }
}
