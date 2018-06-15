package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEntrustRecord;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarEntrustRecordService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * class_name: CarEntrustRecordApi
 * package: com.wintop.ms.carauction.controller
 * describe: 查询委托价设置记录列表
 * creat_user: lizhaoyang.
 * creat_date-time: 2018/3/23/14:19
 **/
@RestController
@RequestMapping("/service/carEntrustRecordApi")
public class CarEntrustRecordApi {
    private static final Logger logger = LoggerFactory.getLogger(CarEntrustRecordApi.class);
    @Autowired
    private ICarEntrustRecordService carEntrustRecordService;
    @Autowired
    private ICarManagerUserService managerUserService;
    /***
     * 获取委托价设置记录列表
     */
    @RequestMapping(value = "/queryCarEntrustRecordList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> queryCarEntrustRecordList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<ListEntity<Map<String,Object>>>();
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String,Object> paramMap = new HashMap<>();
            String searchName=obj.get("searchName")==null?null:obj.getString("searchName");
            searchName=checkParamNull(searchName);
            String carSearchName=obj.get("carSearchName")==null?null:obj.getString("carSearchName");
            carSearchName=checkParamNull(carSearchName);
            String storeId=obj.get("customerStoreId")==null?null:obj.getString("customerStoreId");
            Long customerStoreId=checkParamNull(storeId)==null?null:Long.parseLong(checkParamNull(storeId));
            System.out.println(customerStoreId);
            storeId=obj.get("carStoreId")==null?null:obj.getString("carStoreId");
            Long carStoreId=checkParamNull(storeId)==null?null:Long.parseLong(checkParamNull(storeId));
            String beginTime=obj.get("beginTime")==null?"1970-01-01 00:00:00":obj.getString("beginTime")+" 00:00:00";
            String endTime=obj.get("endTime")==null?"2099-12-31 00:00:00":obj.getString("endTime")+" 23:59:59";
            System.out.println("开始时间："+beginTime);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            paramMap.put("searchName",searchName);
            paramMap.put("carSearchName",carSearchName);
            paramMap.put("customerStoreId",customerStoreId);
            paramMap.put("carStoreId",carStoreId);
            paramMap.put("beginTime",beginTime);
            paramMap.put("endTime",endTime);
            Long userId=obj.getLong("userId");
            System.out.println(userId);
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                paramMap.put("storeIds",storeIds);
            }
            int count=carEntrustRecordService.selectEntrustRecordCount(paramMap);
            List<CarEntrustRecord> recordList=carEntrustRecordService.queryCarEntrustRecordList(paramMap);
            ListEntity<Map<String,Object>> listEntity=new ListEntity<Map<String,Object>>();
            List<Map<String,Object>> list=new ArrayList<>();
            for(CarEntrustRecord record:recordList){
                Map<String,Object> recordMap=new HashMap<String,Object>();
                recordMap.put("userNum",record.getUserNum());
                recordMap.put("username",record.getUsername());
                recordMap.put("mobile",record.getMobile());
                recordMap.put("userStoreName",record.getUserStoreName());
                recordMap.put("carAutoNo",record.getCarAutoNo());
                recordMap.put("autoInfoName",record.getAutoInfoName());
                recordMap.put("carStoreName",record.getCarStoreName());
                recordMap.put("startingPrice",record.getStartingPrice());
                recordMap.put("createTime",fmt.format(record.getCreateTime()));
                recordMap.put("entrustFee",record.getEntrustFee());
                recordMap.put("userCode",record.getUserCode());
                list.add(recordMap);
            }
            listEntity.setList(list);
            listEntity.setCount(count);
            System.out.println("查询委托设置记录结果数"+count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (NumberFormatException e){
            e.printStackTrace();
            logger.info("参数数据转换",e);
            result.setError(String.valueOf(ResultStatus.PARAMETERS_ERROR.getCode()),ResultStatus.PARAMETERS_ERROR.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取委托价设置记录列表",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 检查传入参数是否为有效参数（非空格）
     * @param param 传入参数
     * @return
     */
    private  String checkParamNull(String param){
        if(param!=null){
            param=param.trim().length()==0?null:param.trim();
        }
        return param;
    }

}
