package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.TblAuctionTimes;
import com.wintop.ms.carauction.service.TblAuctionTimesService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/electronAuction")
public class ElectronAuctionApi {
    @Autowired
    private TblAuctionTimesService tblAuctionTimesService;

    private static final Logger logger = LoggerFactory.getLogger(ElectronAuctionApi.class);

    @RequestMapping(value = "/bidding",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblAuctionTimes> bidding(@RequestBody Map<String,Object> map) {
        ServiceResult<TblAuctionTimes> result = new ServiceResult<>();
        try {
            map.put("auctionTime",new Date());
            TblAuctionTimes auctionTimes = tblAuctionTimesService.selectByParam(map);
            if(auctionTimes==null){
                result.setResult(null);
                result.setSuccess(ResultCode.REQUEST_DISABLED.strValue(),ResultCode.REQUEST_DISABLED.getRemark());
            }else{
                result.setResult(auctionTimes);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }
        }catch (Exception e){
            logger.info("提交电子竞价失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
