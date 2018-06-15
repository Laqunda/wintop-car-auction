package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerViewedAuto;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAuctionBidRecordService;
import com.wintop.ms.carauction.service.ICarCustomerViewedAutoService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/12.
 */
@RestController
@RequestMapping("/service/carCustomerViewAuto")
public class CarCustomerViewAutoApi {
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerViewAutoApi.class);
    @Autowired
    private ICarCustomerViewedAutoService iCarCustomerViewedAutoService;
    @Resource
    private ICarAuctionBidRecordService bidRecordService;
    /***
     * 获取用户浏览的车辆列表
     */
    @RequestMapping(value = "/queryCarCustomerViewAutoList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectCarCustomerViewAutoList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("userId");
            String brandId = obj.getString("brandId");
            String auctionType = obj.getString("auctionType");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId",customerId);
            paramMap.put("brandId",brandId);
            paramMap.put("auctionType",auctionType);
            paramMap.put("clientType","app");
            int count = iCarCustomerViewedAutoService.queryUserViewCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarCustomerViewedAuto> carCustomerViewAutos = iCarCustomerViewedAutoService.selectUserViewList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarCustomerViewedAuto carCustomerViewedAuto:carCustomerViewAutos){
                Map<String,Object> map = new HashMap<>();
                map.put("reportColligationRanks",carCustomerViewedAuto.getReportColligationRanks());
                map.put("reportServicingRanks",carCustomerViewedAuto.getReportServicingRanks());
                map.put("status",carCustomerViewedAuto.getStatus());
                map.put("isFollow",carCustomerViewedAuto.getIsFollow());
                map.put("autoInfoName",carCustomerViewedAuto.getAutoInfoName());
                map.put("vehicleAttributionCity",carCustomerViewedAuto.getVehicleAttributionCityCn());
                map.put("startingPrice",carCustomerViewedAuto.getStartingPrice());
                map.put("carAutoNo",carCustomerViewedAuto.getCarAutoNo());
                map.put("id",carCustomerViewedAuto.getCarAutoId());
                map.put("mileage",carCustomerViewedAuto.getMileage());
                map.put("auctionEndTime",carCustomerViewedAuto.getAuctionEndTime());
                map.put("auctionStartTime",carCustomerViewedAuto.getAuctionStartTime());
                map.put("auctionType",carCustomerViewedAuto.getAuctionType());
                map.put("mainPhoto",carCustomerViewedAuto.getMainPhoto());
                map.put("engineCapacity",carCustomerViewedAuto.getEngineCapacity());
                map.put("engineCapacityUnit",carCustomerViewedAuto.getEngineCapacityUnit());
                map.put("environment",carCustomerViewedAuto.getEnvironmentCn());
                map.put("beginRegisterDate",carCustomerViewedAuto.getBeginRegisterDate());
                map.put("auctionId",carCustomerViewedAuto.getAutoAuctionId());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取浏览车辆列表异常",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 获取用户浏览的车辆列表PC
     */
    @ApiOperation(value = "获取用户浏览的车辆列表P")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "/queryViewListByUserId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarCustomerViewedAuto>> queryViewListByUserId(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarCustomerViewedAuto>> result = new ServiceResult<>();
        try {
            Long userId = obj.getLong("userId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId",userId);
            Integer page=obj.getInteger("page");
            Integer pageSize=obj.getInteger("limit");
            paramMap.put("startRowNum",(page-1)*pageSize);
            paramMap.put("endRowNum",pageSize);
            int count = iCarCustomerViewedAutoService.queryUserViewCount(paramMap);
            List<CarCustomerViewedAuto> carCustomerViewAutos = iCarCustomerViewedAutoService.selectUserViewList(paramMap);
            for(CarCustomerViewedAuto carCustomerViewedAuto:carCustomerViewAutos){
                paramMap.put("carId",carCustomerViewedAuto.getCarAutoId());
                carCustomerViewedAuto.setMaxBidPrice(bidRecordService.selectMaxPrice(paramMap));
            }
            ListEntity<CarCustomerViewedAuto> listEntity = new ListEntity<>();
            listEntity.setList(carCustomerViewAutos);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取浏览车辆列表异常",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
