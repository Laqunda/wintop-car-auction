package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.DepositDetail;
import com.wintop.ms.carauction.entity.DepositFreeze;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarCustomerDepositLogService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.IDepositDetailService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class_name: DepositDetailApi
 * package: com.wintop.ms.carauction.controller
 * describe: 保证金明细记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/18:40
 **/
@RestController
@RequestMapping("/service/depositDetailApi")
public class DepositDetailApi {
    @Autowired
    private IDepositDetailService depositDetailService;
    @Autowired
    private ICarCustomerDepositLogService carCustomerDepositLogService;
    @Autowired
    private ICarManagerUserService managerUserService;
    private static final Logger logger = LoggerFactory.getLogger(DepositDetailApi.class);
    /***
     * 获取用户退会记录列表
     */
    @RequestMapping(value = "/queryDepositDetailList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> queryDepositDetailList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<ListEntity<Map<String,Object>>>();
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String,Object> paramMap = new HashMap<>();
            String searchName=obj.get("searchName")==null?null:obj.getString("searchName");
            searchName=checkParamNull(searchName);
            String storeId=obj.get("customerStoreId")==null?null:obj.getString("customerStoreId");
            Long customerStoreId=checkParamNull(storeId)==null?null:Long.parseLong(checkParamNull(storeId));
            System.out.println(customerStoreId);
            String beginTime=obj.get("beginTime")==null?"1970-01-01 00:00:00":obj.getString("beginTime")+" 00:00:00";
            String endTime=obj.get("endTime")==null?"2099-12-31 00:00:00":obj.getString("endTime")+" 23:59:59";
            System.out.println("开始时间："+beginTime);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            paramMap.put("searchName",searchName);
            paramMap.put("customerStoreId",customerStoreId);
            paramMap.put("beginTime",beginTime);
            paramMap.put("endTime",endTime);
            Long userId=obj.getLong("userId");
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                paramMap.put("storeIds",storeIds);
            }
            int count=depositDetailService.selectDepositDetailCount(paramMap);
            List<DepositDetail> recordList=depositDetailService.queryDepositDetailList(paramMap);
            ListEntity<Map<String,Object>> listEntity=new ListEntity<Map<String,Object>>();
            List<Map<String,Object>> list=new ArrayList<>();
            for(DepositDetail record:recordList){
                Map<String,Object> recordMap=new HashMap<String,Object>();
                recordMap.put("userNum",record.getUserNum());
                recordMap.put("username",record.getUsername());
                recordMap.put("mobile",record.getMobile());
                recordMap.put("customerStoreName",record.getCustomerStoreName());
                recordMap.put("payFee",record.getPayFee());
                recordMap.put("payTime",fmt.format(record.getPayTime()));
                recordMap.put("payType",record.getPayType());
                recordMap.put("logNo",record.getLogNo());
                recordMap.put("userCode",record.getUserCode());
                list.add(recordMap);
            }
            listEntity.setList(list);
            listEntity.setCount(count);
            System.out.println("查询保证金缴费记录结果数"+count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (NumberFormatException e){
            e.printStackTrace();
            logger.info("参数数据转换",e);
            result.setError(String.valueOf(ResultStatus.PARAMETERS_ERROR.getCode()),ResultStatus.PARAMETERS_ERROR.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取保证金缴费记录列表",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /***
     * 获取保证金冻结记录列表
     */
    @RequestMapping(value = "/queryDepositFreezeList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> queryDepositFreezeList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<ListEntity<Map<String,Object>>>();
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String,Object> paramMap = new HashMap<>();
            String searchName=obj.get("searchName")==null?null:obj.getString("searchName");
            searchName=checkParamNull(searchName);
            String storeId=obj.get("customerStoreId")==null?null:obj.getString("customerStoreId");
            Long customerStoreId=checkParamNull(storeId)==null?null:Long.parseLong(checkParamNull(storeId));
            System.out.println(customerStoreId);
            String beginTime=obj.get("beginTime")==null?"1970-01-01 00:00:00":obj.getString("beginTime")+" 00:00:00";
            String endTime=obj.get("endTime")==null?"2099-12-31 00:00:00":obj.getString("endTime")+" 23:59:59";
            System.out.println("开始时间："+beginTime);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            paramMap.put("searchName",searchName);
            paramMap.put("customerStoreId",customerStoreId);
            paramMap.put("beginTime",beginTime);
            paramMap.put("endTime",endTime);
            Long userId=obj.getLong("userId");
            System.out.println(userId);
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                paramMap.put("storeIds",storeIds);
            }
            int count=carCustomerDepositLogService.selectDepositFreezeCount(paramMap);
            List<DepositFreeze> recordList=carCustomerDepositLogService.queryDepositFreezeList(paramMap);
            ListEntity<Map<String,Object>> listEntity=new ListEntity<Map<String,Object>>();
            List<Map<String,Object>> list=new ArrayList<>();
            for(DepositFreeze record:recordList){
                Map<String,Object> recordMap=new HashMap<String,Object>();
                recordMap.put("userNum",record.getUserNum());
                recordMap.put("username",record.getUsername());
                recordMap.put("mobile",record.getMobile());
                recordMap.put("customerStoreName",record.getCustomerStoreName());
                recordMap.put("depositAmount",record.getDepositAmount());
                recordMap.put("freezeTime",fmt.format(record.getFreezeTime()));
                recordMap.put("remark",record.getRemark());
                recordMap.put("userCode",record.getUserCode());
                list.add(recordMap);
            }
            listEntity.setList(list);
            listEntity.setCount(count);
            System.out.println("保证金冻结记录结果数"+count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (NumberFormatException e){
            e.printStackTrace();
            logger.info("参数数据转换",e);
            result.setError(String.valueOf(ResultStatus.PARAMETERS_ERROR.getCode()),ResultStatus.PARAMETERS_ERROR.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取保证金冻结记录列表",e);
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
