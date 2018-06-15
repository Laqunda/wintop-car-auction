package com.wintop.ms.carauction.controller;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAuctionBidRecordService;
import com.wintop.ms.carauction.service.ICarCustomerFollowAutoService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by 12991 on 2018/3/7.
 */
@RestController
@RequestMapping("/service/carCustomerFollowAuto")
public class CarCustomerFollowAutoApi {
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerFollowAutoApi.class);
    @Autowired
    private ICarCustomerFollowAutoService carFollowService;
    @Resource
    private ICarAuctionBidRecordService bidRecordService;
    private IdWorker idWorker = new IdWorker(10);
    /**
     * 收藏接口
     */
    @RequestMapping(value = "/insertCustomerCollection",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> insertCustomerCollection(@RequestBody JSONObject obj){
        logger.info("收藏车辆");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long userId = obj.getLong("userId");
            Long autoId = obj.getLong("autoId");
           CarCustomerFollowAuto vo = carFollowService.selectCustomerFollow(autoId,userId).getResult();
            if(vo != null){
                    result.setError("0","该用户本车辆已经关注");
                    map.put("count",0);
                    return result;
            }
           CarCustomerFollowAuto carCustomerFollowAuto = new CarCustomerFollowAuto();
           carCustomerFollowAuto.setId(idWorker.nextId());
           carCustomerFollowAuto.setUserId(userId);
           carCustomerFollowAuto.setAutoId(autoId);
           carCustomerFollowAuto.setCreateTime(new Date());
           carCustomerFollowAuto.setStatus("1");
           Integer count = carFollowService.insertCustomerCollection(carCustomerFollowAuto).getResult();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("收藏车辆失败",e);
        }
        return result;
    }
    /**
     * 取消收藏接口
     */
    @RequestMapping(value = "/deleteCustomerCollection",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteCustomerCollection(@RequestBody JSONObject obj){
        logger.info("取消收藏车辆");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long userId = obj.getLong("userId");
            Long autoId = obj.getLong("autoId");
            map.put("userId",userId);
            map.put("autoId",autoId);
            Integer count = carFollowService.deleteCustomerCollection(map).getResult();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("取消收藏车辆失败",e);
        }
        return result;
    }

    /***
     * 获取关注车辆列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/queryUserFollowList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectAuctionCarList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            String auctionType = obj.getString("auctionType");
            Long customerId = obj.getLong("customerId");
            String cityName = obj.getString("cityName");
            String carName = obj.getString("carName");
            String carStatus = obj.getString("carStatus");
            String grade = obj.getString("grade");
            String brandId = obj.getString("brandId");
            String carSort = obj.getString("carSort");
            Map<String,Object> paramMap = new HashMap<>();
            String[] carAges = CarAutoUtils.getCarAgeArray(obj.getString("carAge"));
            paramMap.put("carAge1",carAges[0]);
            paramMap.put("carAge2",carAges[1]);
            paramMap.put("brandId",brandId);
            paramMap.put("carSort",carSort);
            paramMap.put("auctionType",auctionType);
            paramMap.put("cityName",cityName);
            paramMap.put("carStatus",carStatus);
            paramMap.put("customerId",customerId);
            paramMap.put("carName",carName);
            paramMap.put("grade",grade);
            paramMap.put("clientType","app");
            int count = carFollowService.queryUserFollowCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarCustomerFollowAuto> carCustomerFollowAutos = carFollowService.queryUserFollowList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarCustomerFollowAuto carFollow:carCustomerFollowAutos){
                Map<String,Object> map = new HashMap<>();
                map.put("mainPhoto",carFollow.getMainPhoto());
                map.put("vehicleAttributionCity",carFollow.getVehicleAttributionCityCn());
                map.put("autoInfoName",carFollow.getAutoInfoName());
                map.put("mileage",carFollow.getMileage());
                map.put("reportColligationRanks",carFollow.getReportColligationRanks());
                map.put("reportServicingRanks",carFollow.getReportServicingRanks());
                map.put("startingPrice",carFollow.getStartingPrice());
                map.put("auctionStartTime",carFollow.getAuctionStartTime());
                map.put("auctionEndTime",carFollow.getAuctionEndTime());
                map.put("status",carFollow.getCarStatus());
                map.put("id",carFollow.getCarId());
                if ("2".equals(auctionType)){
                    map.put("carAutoNo",carFollow.getAuctionCode());
                }else {
                    map.put("carAutoNo",carFollow.getCarAutoNo());
                }
                map.put("isEntrust",carFollow.getIsEntrust());
                map.put("environment",carFollow.getEnvironment());
                map.put("beginRegisterDate",carFollow.getBeginRegisterDate());
                map.put("isFollow","1");
                map.put("auctionId",carFollow.getAutoAuctionId());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取关注车辆列表",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 获取关注车辆列表
     * @param obj
     * @return
     */
    @ApiOperation(value = "获取关注车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "/queryListByUserId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarCustomerFollowAuto>> queryListByUserId(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarCustomerFollowAuto>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("userId");
            Map<String,Object> paramMap = new HashMap<>();
            Integer page=obj.getInteger("page");
            Integer pageSize=obj.getInteger("limit");
            paramMap.put("startRowNum",(page-1)*pageSize);
            paramMap.put("endRowNum",pageSize);
            paramMap.put("customerId",customerId);
            int count = carFollowService.queryUserFollowCount(paramMap);
            List<CarCustomerFollowAuto> carCustomerFollowAutos = carFollowService.queryUserFollowList(paramMap);
            Map<String,Object> map = new HashMap<>();
            map.put("customerId",customerId);
            for(CarCustomerFollowAuto carFollow:carCustomerFollowAutos){
                map.put("carId",carFollow.getCarId());
                carFollow.setMaxBidPrice(bidRecordService.selectMaxPrice(map));
            }
            ListEntity<CarCustomerFollowAuto> listEntity = new ListEntity<>();
            listEntity.setList(carCustomerFollowAutos);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.strValue());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取关注车辆列表",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

}
