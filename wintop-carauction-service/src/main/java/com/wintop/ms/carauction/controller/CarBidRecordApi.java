package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionBidRecord;
import com.wintop.ms.carauction.entity.CarBidRecord;
import com.wintop.ms.carauction.entity.CarBidReport;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarBidRecordService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * class_name: CarBidRecordApi
 * package: com.wintop.ms.carauction.controller
 * describe: 查询车辆出价记录列表
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/24/19:09
 **/
@RestController
@RequestMapping("/service/carBidRecordApi")
public class CarBidRecordApi {
    private static final String COMPLETE = "4";
    @Autowired
    private ICarBidRecordService carBidRecordService;
    @Autowired
    private ICarManagerUserService managerUserService;
    private static final Logger logger = LoggerFactory.getLogger(CarBidRecordApi.class);

    /***
     * 获取出价记录列表
     */
    @RequestMapping(value = "/queryCarBidRecordList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> queryCarBidRecordList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<ListEntity<Map<String,Object>>>();
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String,Object> paramMap = new HashMap<>();
            Long cityId = obj.get("cityId") == null ? null : obj.getLong("cityId");
            String searchName=obj.get("searchName")==null?null:obj.getString("searchName");
            searchName=checkParamNull(searchName);
            String carSearchName=obj.get("carSearchName")==null?null:obj.getString("carSearchName");
            carSearchName=checkParamNull(carSearchName);
            String storeId=obj.get("customerStoreId")==null?null:obj.getString("customerStoreId");
            Long customerStoreId=checkParamNull(storeId)==null?null:Long.parseLong(checkParamNull(storeId));
            System.out.println(customerStoreId);
            storeId=obj.get("carStoreId")==null?null:obj.getString("carStoreId");
            Long carStoreId=checkParamNull(storeId)==null?null:Long.parseLong(checkParamNull(storeId));
            String auctionType=obj.get("auctionType")==null?null:obj.getString("auctionType");
            auctionType=checkParamNull(auctionType);
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
            paramMap.put("auctionType",auctionType);
            paramMap.put("beginTime",beginTime);
            paramMap.put("endTime",endTime);
            paramMap.put("cityId", cityId);
            if (obj.get("carId")!=null){
                paramMap.put("carId",obj.getLong("carId"));
            }
            Long userId=obj.getLong("userId");
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                paramMap.put("storeIds",storeIds);
            }
            int count=carBidRecordService.selectCarBidRecordCount(paramMap);
            List<CarBidRecord> recordList=carBidRecordService.queryCarBidRecordRecordList(paramMap);
            ListEntity<Map<String,Object>> listEntity=new ListEntity<Map<String,Object>>();
            List<Map<String,Object>> list=new ArrayList<>();
            for(CarBidRecord record:recordList){
                Map<String,Object> recordMap=new HashMap<String,Object>();
                if(record.getUserNum()!=null){
                    recordMap.put("userNum",record.getUserNum());
                }else{
                    recordMap.put("userNum","");
                }
                if(record.getUsername()!=null){
                    recordMap.put("username",record.getUsername());
                }else{
                    recordMap.put("username","");
                }
                if(record.getMobile()!=null){
                    recordMap.put("mobile",record.getMobile());
                }else{
                    recordMap.put("mobile","");
                }
                if(record.getUserStoreName()!=null){
                    recordMap.put("userStoreName",record.getUserStoreName());
                }else{
                    recordMap.put("userStoreName","");
                }
                recordMap.put("carAutoNo",record.getCarAutoNo());
                recordMap.put("autoInfoName",record.getAutoInfoName());
                recordMap.put("carStoreName",record.getCarStoreName());
                recordMap.put("auctionType",record.getAuctionType());
                recordMap.put("startingPrice",record.getStartingPrice());
                recordMap.put("bidTime",fmt.format(record.getBidTime()));
                recordMap.put("bidFee",record.getBidFee());
                recordMap.put("centerName", record.getCenterName());
                if(record.getUserCode()!=null){
                    recordMap.put("userCode",record.getUserCode());
                }else{
                    recordMap.put("userCode","");
                }
                list.add(recordMap);
            }
            listEntity.setList(list);
            listEntity.setCount(count);
            System.out.println("查询车辆出价记录结果数"+count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (NumberFormatException e){
            e.printStackTrace();
            logger.info("参数数据转换",e);
            result.setError(String.valueOf(ResultStatus.PARAMETERS_ERROR.getCode()),ResultStatus.PARAMETERS_ERROR.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取车辆出价记录列表",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
    /***
     * 获取出价记录列表
     */
    @RequestMapping(value = "/queryCarBidRecordAllList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> queryCarBidRecordAllList(@RequestBody JSONObject obj) {

        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String, Object> map = Maps.newHashMap();
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("carId", obj.getLong("carId"));
            Long userId=obj.getLong("userId");
            System.out.println(userId);
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                paramMap.put("storeIds",storeIds);
            }
            List<CarBidRecord> recordList=carBidRecordService.queryCarBidRecordRecordList(paramMap);
            List<Map<String,Object>> list=new ArrayList<>();
            for(CarBidRecord record:recordList){
                Map<String,Object> recordMap=new HashMap<String,Object>();
                if(record.getUserNum()!=null){
                    recordMap.put("userNum",record.getUserNum());
                }else{
                    recordMap.put("userNum","");
                }
                if(record.getUsername()!=null){
                    recordMap.put("username",record.getUsername());
                }else{
                    recordMap.put("username","");
                }
                if(record.getMobile()!=null){
                    recordMap.put("mobile",record.getMobile());
                }else{
                    recordMap.put("mobile","");
                }
                if(record.getUserStoreName()!=null){
                    recordMap.put("userStoreName",record.getUserStoreName());
                }else{
                    recordMap.put("userStoreName","");
                }
                recordMap.put("bidTime",fmt.format(record.getBidTime()));
                recordMap.put("bidFee",record.getBidFee());
                list.add(recordMap);
            }
            if (CollectionUtils.isNotEmpty(recordList)) {
                map.put("autoInfoName", recordList.get(0).getAutoInfoName());
                map.put("carAutoNo", recordList.get(0).getCarAutoNo());
            }
            map.put("list", list);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (NumberFormatException e){
            e.printStackTrace();
            logger.info("参数数据转换",e);
            result.setError(String.valueOf(ResultStatus.PARAMETERS_ERROR.getCode()),ResultStatus.PARAMETERS_ERROR.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取车辆出价记录列表",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
    /***
     * 获取竞拍统计列表
     */
    @RequestMapping(value = "/queryCarBidRecordAllStatistic",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> queryCarBidRecordAllStatistic(@RequestBody JSONObject obj) {

        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String, Object> map = Maps.newHashMap();
        try {
            SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("carIds", Splitter.on(",").splitToList(obj.getString("carIds")));
//            Long userId=obj.getLong("userId");
//            System.out.println(userId);
//            if(userId!=null){
//                List<Long> storeIds = managerUserService.queryStoreScope(userId);
//                paramMap.put("storeIds",storeIds);
//            }
            List<Map<String, Object>> resultList = Lists.newArrayList();
            List<CarBidReport> reportList = Lists.newArrayList();
            List<CarBidRecord> recordList=carBidRecordService.queryCarBidRecordRecordList(paramMap);
            for (CarBidRecord record : recordList) {
                CarBidReport report = new CarBidReport();
                report.setUserStoreName(record.getUserStoreName());
                report.setAuctionStatus(record.getAuctionStatus());
                report.setCarAutoNo(record.getCarAutoNo());
                reportList.add(report);
            }
            Map<String, List<CarBidReport>> reportGroupList = reportList.stream().collect(Collectors.groupingBy(CarBidReport::getUserStoreName));
            reportGroupList.forEach((k,v)->{
                Map<String, Object> reportMap = Maps.newHashMap();
                long successCount = v.stream().filter(r -> r.getAuctionStatus().equals(COMPLETE)).count();
                long auctionCount = v.stream().count();
                reportMap.put("userStoreName", k);
                reportMap.put("successCount", successCount);
                reportMap.put("auctionCount", auctionCount);
                resultList.add(reportMap);
            });
            map.put("list", resultList);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (NumberFormatException e){
            e.printStackTrace();
            logger.info("参数数据转换",e);
            result.setError(String.valueOf(ResultStatus.PARAMETERS_ERROR.getCode()),ResultStatus.PARAMETERS_ERROR.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取竞拍统计列表",e);
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
    @PostMapping(value = "BidRecord",produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarBidRecord>> BidRecord(@RequestBody Map<String,Object> map){
        ServiceResult<List<CarBidRecord>> result=new ServiceResult<>();
        try {
            List<CarBidRecord> recordList=carBidRecordService.queryCarBidRecordRecordList(map);
            result.setResult(recordList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("导出车辆出价记录列表",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
