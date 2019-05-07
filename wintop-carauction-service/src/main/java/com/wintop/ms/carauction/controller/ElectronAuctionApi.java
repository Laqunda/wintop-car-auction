package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.entity.TblAuctionLog;
import com.wintop.ms.carauction.entity.TblAuctionTimes;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.TblAuctionLogService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/electronAuction")
public class ElectronAuctionApi {
    @Autowired
    private TblAuctionLogService tblAuctionLogService;
    @Autowired
    private ICarManagerUserService managerUserService;

    private static final Logger logger = LoggerFactory.getLogger(ElectronAuctionApi.class);

    @RequestMapping(value = "/bidding",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblAuctionTimes> bidding(@RequestBody Map<String,Object> map) {
        ServiceResult<TblAuctionTimes> result = new ServiceResult<>();
        try {
            TblAuctionTimes auctionTimes = tblAuctionLogService.saveBidding(map);
            if(auctionTimes==null){
                result.setResult(null);
                result.setError(ResultCode.REQUEST_DISABLED.strValue(),ResultCode.REQUEST_DISABLED.getRemark());
            }else{
                result.setResult(null);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }
        }catch (Exception e){
            logger.info("提交电子竞价失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        try{
            tblAuctionLogService.insertDataLog("1",map.toString(),JSONObject.toJSONString(result));
        } catch (Exception e){
            logger.info("保存数据失败",e);
        }
        return result;
    }

    /***
     * 查询所有竞价记录
     * @return
     */
    @RequestMapping(value = "/selectLogList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<TblAuctionLog>> selectLogList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<TblAuctionLog>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            //用户权限
            if (obj.getLong("userId")!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(obj.getLong("userId"));
                paramMap.put("storeIds",storeIds);
            }
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            if(StringUtils.isNotBlank(obj.getString("auctionTimesName"))){
                paramMap.put("auctionTimesName",obj.getString("auctionTimesName"));
            }
            if(StringUtils.isNotBlank(obj.getString("boardName"))){
                paramMap.put("boardName",obj.getString("boardName"));
            }
            if(StringUtils.isNotBlank(obj.getString("carInfo"))){
                paramMap.put("carInfo",obj.getString("carInfo"));
            }
            if(obj.getLong("carId")!=null){
                paramMap.put("carId",obj.getLong("carId"));
            }
            if(obj.getLong("auctionCarId")!=null){
                paramMap.put("auctionCarId",obj.getLong("auctionCarId"));
            }
            paramMap.put("priceType",obj.getString("priceType"));
            List<TblAuctionLog> logList = tblAuctionLogService.selectByExample(paramMap);
            int count = tblAuctionLogService.countByExample(paramMap);
            ListEntity<TblAuctionLog> listEntity = new ListEntity<>();
            listEntity.setList(logList);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            logger.info("查询所有电子竞价接口失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}