package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionBidRecord;
import com.wintop.ms.carauction.entity.CarBidRecord;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAuctionBidRecordService;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 12991 on 2018/3/12.
 */
@RestController
@RequestMapping("/service/carAuctionBidRecord")
public class CarAuctionBidRecordApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAuctionBidRecordApi.class);
    @Autowired
    private ICarAuctionBidRecordService iCarAuctionBidRecordService;
    /***
     * 获取用户出价的车辆列表
     */
    @RequestMapping(value = "/queryCarAuctionBidRecordList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectCarAuctionBidRecordList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("customerId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId",customerId);
            int count = iCarAuctionBidRecordService.selectBidCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuctionBidRecord> CarAuctionBidRecords = iCarAuctionBidRecordService.queryUserBidList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAuctionBidRecord carAuctionBidRecord:CarAuctionBidRecords){
                Map<String,Object> map = new HashMap<>();
                //增加出价记录
                if(carAuctionBidRecord.getAuctionType().equals("2")){
                    continue;
                }
                map.put("bidRecordId",carAuctionBidRecord.getId());
                map.put("mainPhoto",carAuctionBidRecord.getMainPhoto());
                map.put("bidFee",carAuctionBidRecord.getBidFee());
                map.put("addFee",carAuctionBidRecord.getAddFee());
                map.put("autoInfoName",carAuctionBidRecord.getAutoInfoName());
                map.put("vehicleAttributionCity",carAuctionBidRecord.getVehicleAttributionCityCn());
                map.put("mileage",carAuctionBidRecord.getMileage());
                map.put("reportServicingRanks",carAuctionBidRecord.getReportServicingRanks());
                map.put("reportScore",carAuctionBidRecord.getReportScore());
                map.put("id",carAuctionBidRecord.getAutoId());
                map.put("reportColligationRanks",carAuctionBidRecord.getReportColligationRanks());
                map.put("status",carAuctionBidRecord.getStatus());
                map.put("auctionId",carAuctionBidRecord.getAuctionId());

                /**出价的车辆列表
                 竞价成功：8 、10、11、12、14、15、16、17
                 竞价失败：0、1、2、3、4、5、6、18、19
                 竞价中：7
                 待确认：9*/
                String[] successArray = {"8","10","11","12","14","15","16","17"};
                String[] failArray = {"0","1","2","3","4","5","6","18","19"};
                if (Arrays.asList(successArray).contains(carAuctionBidRecord.getStatus())){
                    //车辆状态竞拍成功
                    if ("1".equals(carAuctionBidRecord.getBidSuccess())){
                        map.put("autoStatus","1");
                        map.put("autoStatusCn","竞价成功");
                    }else {
                        map.put("autoStatus","2");
                        map.put("autoStatusCn","竞价失败");
                    }
                    //判断是否是当前用户竞价成功
                }else if (Arrays.asList(failArray).contains(carAuctionBidRecord.getStatus())
                        || "9".equals(carAuctionBidRecord.getStatus()) && carAuctionBidRecord.getMaxBidPrice().compareTo(carAuctionBidRecord.getBidFee())>0){
                    //车辆状态竞拍失败
                    map.put("autoStatus","2");
                    map.put("autoStatusCn","竞价失败");
                }else if ("7".equals(carAuctionBidRecord.getStatus())){
                    //竞价中
                    map.put("autoStatus","3");
                    map.put("autoStatusCn","竞价中");
                }else if ("9".equals(carAuctionBidRecord.getStatus()) && carAuctionBidRecord.getMaxBidPrice().compareTo(carAuctionBidRecord.getBidFee())==0){
                    //待确认
                    map.put("autoStatus","4");
                    map.put("autoStatusCn","待确认");
                }else {
                    //服务器未知状态下
                    map.put("autoStatus","0");
                    map.put("autoStatusCn","--");
                }
                map.put("auctionEndTime",carAuctionBidRecord.getAuctionEndTime());
                map.put("auctionStartTime",carAuctionBidRecord.getAuctionStartTime());
                map.put("maxPrice",carAuctionBidRecord.getMaxBidPrice());
                map.put("carAutoNo",carAuctionBidRecord.getCarAutoNo());
                //如果是先下车，需要将场次内车辆编号返回
                if ("2".equals(carAuctionBidRecord.getAuctionType()) && StringUtils.isNotEmpty(carAuctionBidRecord.getAuctionCode())){
                    map.put("carAutoNo",carAuctionBidRecord.getAuctionCode());
                }
                map.put("auctionCode",carAuctionBidRecord.getAuctionCode());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取出价车辆列表异常",e);
            result.setError("-1","异常");
        }
        return result;
    }


    /***
     * 获取用户出价的车辆列表
     *  zhangzijuan
     */
    @ApiOperation(value = "根据用户Id查询用户出价的车辆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "/queryBidCarListByUserId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAuctionBidRecord>> queryBidCarListByUserId(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAuctionBidRecord>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("userId");
            Map<String,Object> paramMap = new HashMap<>();
            Integer page=obj.getInteger("page");
            Integer pageSize=obj.getInteger("limit");
            paramMap.put("startRowNum",(page-1)*pageSize);
            paramMap.put("endRowNum",pageSize);
            paramMap.put("customerId",customerId);
            int count = iCarAuctionBidRecordService.selectBidCount(paramMap);
            List<CarAuctionBidRecord> CarAuctionBidRecords = iCarAuctionBidRecordService.queryUserBidList(paramMap);
            for(CarAuctionBidRecord carAuctionBidRecord:CarAuctionBidRecords){
                paramMap.put("carId",carAuctionBidRecord.getCarId());
                carAuctionBidRecord.setMaxBidPrice(iCarAuctionBidRecordService.selectMaxPrice(paramMap));
            }
            ListEntity<CarAuctionBidRecord> listEntity = new ListEntity<>();
            listEntity.setList(CarAuctionBidRecords);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.strValue());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取出价车辆列表异常",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    @RequestMapping(value = "/getBidPriceList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<Map<String,Object>>> getBidPriceList(@RequestBody JSONObject obj) {
        ServiceResult<List<Map<String, Object>>> result = new ServiceResult<>();
        CarAuctionBidRecord carAuctionBidRecord = JSONObject.toJavaObject(obj, CarAuctionBidRecord.class);
        if (carAuctionBidRecord == null) {
            carAuctionBidRecord = new CarAuctionBidRecord();
        }
        try {
            List<Map<String, Object>> list = iCarAuctionBidRecordService.getBidPriceList(carAuctionBidRecord.getCarId());
            result.setResult(list);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
        }
        return result;
    }

    @RequestMapping(value = "/querySceneCarAuctionBidRecordList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectSceneCarAuctionBidRecordList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("customerId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId",customerId);
            int count = iCarAuctionBidRecordService.selectBidCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuctionBidRecord> CarAuctionBidRecords = iCarAuctionBidRecordService.queryUserBidList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAuctionBidRecord carAuctionBidRecord:CarAuctionBidRecords){
                Map<String,Object> map = new HashMap<>();
                if(carAuctionBidRecord.getAuctionType().equals("1")){
                    continue;
                }
                //增加出价记录
                map.put("bidRecordId",carAuctionBidRecord.getId());
                map.put("mainPhoto",carAuctionBidRecord.getMainPhoto());
                map.put("bidFee",carAuctionBidRecord.getBidFee());
                map.put("addFee",carAuctionBidRecord.getAddFee());
                map.put("autoInfoName",carAuctionBidRecord.getAutoInfoName());
                map.put("vehicleAttributionCity",carAuctionBidRecord.getVehicleAttributionCityCn());
                map.put("mileage",carAuctionBidRecord.getMileage());
                map.put("reportServicingRanks",carAuctionBidRecord.getReportServicingRanks());
                map.put("reportScore",carAuctionBidRecord.getReportScore());
                map.put("id",carAuctionBidRecord.getAutoId());
                map.put("reportColligationRanks",carAuctionBidRecord.getReportColligationRanks());
                map.put("status",carAuctionBidRecord.getStatus());
                map.put("auctionId",carAuctionBidRecord.getAuctionId());

                /**出价的车辆列表
                 竞价成功：8 、10、11、12、14、15、16、17
                 竞价失败：0、1、2、3、4、5、6、18、19
                 竞价中：7
                 待确认：9*/
                String[] successArray = {"8","10","11","12","14","15","16","17"};
                String[] failArray = {"0","1","2","3","4","5","6","18","19"};
                if (Arrays.asList(successArray).contains(carAuctionBidRecord.getStatus())){
                    //车辆状态竞拍成功
                    if ("1".equals(carAuctionBidRecord.getBidSuccess())){
                        map.put("autoStatus","1");
                        map.put("autoStatusCn","竞价成功");
                    }else {
                        map.put("autoStatus","2");
                        map.put("autoStatusCn","竞价失败");
                    }
                    //判断是否是当前用户竞价成功
                }else if (Arrays.asList(failArray).contains(carAuctionBidRecord.getStatus())
                        || "9".equals(carAuctionBidRecord.getStatus()) && carAuctionBidRecord.getMaxBidPrice().compareTo(carAuctionBidRecord.getBidFee())>0){
                    //车辆状态竞拍失败
                    map.put("autoStatus","2");
                    map.put("autoStatusCn","竞价失败");
                }else if ("7".equals(carAuctionBidRecord.getStatus())){
                    //竞价中
                    map.put("autoStatus","3");
                    map.put("autoStatusCn","竞价中");
                }else if ("9".equals(carAuctionBidRecord.getStatus()) && carAuctionBidRecord.getMaxBidPrice().compareTo(carAuctionBidRecord.getBidFee())==0){
                    //待确认
                    map.put("autoStatus","4");
                    map.put("autoStatusCn","待确认");
                }else {
                    //服务器未知状态下
                    map.put("autoStatus","0");
                    map.put("autoStatusCn","--");
                }
                map.put("auctionEndTime",carAuctionBidRecord.getAuctionEndTime());
                map.put("auctionStartTime",carAuctionBidRecord.getAuctionStartTime());
                map.put("maxPrice",carAuctionBidRecord.getMaxBidPrice());
                map.put("carAutoNo",carAuctionBidRecord.getCarAutoNo());
                //如果是先下车，需要将场次内车辆编号返回
                if ("2".equals(carAuctionBidRecord.getAuctionType()) && StringUtils.isNotEmpty(carAuctionBidRecord.getAuctionCode())){
                    map.put("carAutoNo",carAuctionBidRecord.getAuctionCode());
                }
                map.put("auctionCode",carAuctionBidRecord.getAuctionCode());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取出价车辆列表异常",e);
            result.setError("-1","异常");
        }
        return result;
    }
}
