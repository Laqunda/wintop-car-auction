package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarCustomerEntrustCar;
import com.wintop.ms.carauction.service.ICarAutoAuctionService;
import com.wintop.ms.carauction.service.ICarAutoService;
import com.wintop.ms.carauction.service.ICarCustomerEntrustCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/service/carEntrust")
public class CarCustomerEntrustApi {
    @Autowired
    private ICarCustomerEntrustCarService entrustCarService;
    @Autowired
    private ICarAutoAuctionService carAutoAuctionService;
    @Autowired
    private ICarAutoService autoService;

    private static final Logger logger = LoggerFactory.getLogger(CarCustomerEntrustApi.class);

    @RequestMapping(value = "/queryEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> queryEntrustPrice(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        logger.info("查询车辆委托价");
        Long carId = obj.getLong("carId");
        Long customerId = obj.getLong("customerId");
        try{
            CarCustomerEntrustCar entrustCar = entrustCarService.selectEntrustByCarId(carId,customerId);
            BigDecimal entrustPrice = null;
            //1已设置，0未设置
            String isSet = "0";
            String status = "0";
            if(entrustCar!=null){
                entrustPrice = entrustCar.getEntrustFee();
                isSet = "1";
                status = entrustCar.getStatus();
            }
            Map<String,Object> map = new HashMap<>();
            map.put("entrustPrice",entrustPrice);
            map.put("isSet",isSet);
            map.put("status",status);
            result.setResult(map);
            result.setSuccess("0","成功");
        } catch (Exception e){
            e.printStackTrace();
            logger.info("查询委托价失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    @RequestMapping(value = "/saveEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveEntrustPrice(@RequestBody JSONObject obj) {
        logger.info("设置车辆委托价");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long carId = obj.getLong("carId");
            Long customerId = obj.getLong("customerId");
            CarAuto auto = autoService.selectById(carId);
            if(!CarStatusEnum.WAITING_AUCTION.value().equals(auto.getStatus())){
                result.setError(ResultCode.NO_ALLOW_ENTRUST.strValue(),ResultCode.NO_ALLOW_ENTRUST.getRemark());
                return result;
            }
            CarCustomerEntrustCar entrustCar = entrustCarService.selectEntrustByCarId(carId,customerId);
            if(entrustCar!=null){
                result.setError("0","已经设置过委托价，无需再次设置");
                map.put("count",0);
                return result;
            }
            CarCustomerEntrustCar record = new CarCustomerEntrustCar();
            record.setId(obj.getLong("id"));
            record.setCarId(carId);
            record.setAutoAuctionId(auto.getAutoAuctionId());
            record.setCustomerId(customerId);
            record.setCreateTime(new Date());
            record.setCreateManager(obj.getString("createManager"));
            record.setEntrustFee(obj.getBigDecimal("entrustPrice"));
            record.setStatus(obj.getString("status"));
            Integer count = entrustCarService.insert(record);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess("0","成功");
            //**设置委托价成功，更新redisautodata信息
            carAutoAuctionService.updateRedisAutoData(carId);
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("保存设置委托价失败",e);
        }
        return result;
    }

    @RequestMapping(value = "/updateEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateEntrustPrice(@RequestBody JSONObject obj) {
        logger.info("修改车辆委托价");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long carId = obj.getLong("carId");
            Long customerId = obj.getLong("customerId");
            CarAuto auto = autoService.selectById(carId);
            if(!CarStatusEnum.WAITING_AUCTION.value().equals(auto.getStatus())){
                result.setError(ResultCode.NO_ALLOW_ENTRUST.strValue(),ResultCode.NO_ALLOW_ENTRUST.getRemark());
                return result;
            }
            BigDecimal entrustPrice = obj.getBigDecimal("entrustPrice");
            map.put("entrustPrice",entrustPrice);
            CarCustomerEntrustCar entrustCar = entrustCarService.selectEntrustByCarId(carId,customerId);
            if(entrustCar==null){
                result.setError("301","还未设置委托价");
                map.put("count",0);
                return result;
            }
            CarCustomerEntrustCar record = new CarCustomerEntrustCar();
            record.setCarId(carId);
            record.setCustomerId(customerId);
            record.setEntrustFee(obj.getBigDecimal("entrustPrice"));
            record.setStatus(obj.getString("status"));
            record.setUpdateTime(new Date());
            Integer count = entrustCarService.updateCarEntrustPrice(record);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess("0","成功");
            //**修改委托价成功，更新redisautodata信息
            carAutoAuctionService.updateRedisAutoData(carId);
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("修改委托价失败",e);
        }
        return result;
    }

    /**
     * 设置委托价状态
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateEntrustPriceStatus",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateEntrustPriceStatus(@RequestBody JSONObject obj) {
        logger.info("关闭/启用车辆委托价");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        map.put("status",obj.getString("status"));
        try {
            Long carId = obj.getLong("carId");
            Long customerId = obj.getLong("customerId");
            CarAuto auto = autoService.selectById(carId);
            if(!CarStatusEnum.WAITING_AUCTION.value().equals(auto.getStatus())){
                result.setError(ResultCode.NO_ALLOW_ENTRUST.strValue(),ResultCode.NO_ALLOW_ENTRUST.getRemark());
                return result;
            }
            CarCustomerEntrustCar entrustCar = entrustCarService.selectEntrustByCarId(carId,customerId);
            if(entrustCar==null){
                result.setError("301","还未设置委托价");
                map.put("count",0);
                return result;
            }
            CarCustomerEntrustCar record = new CarCustomerEntrustCar();
            record.setCarId(carId);
            record.setCustomerId(customerId);
            record.setStatus(obj.getString("status"));
            Integer count = entrustCarService.updateCarEntrustPriceStatus(record);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess("0","成功");
            //**设置委托价状态成功，更新redisautodata信息
            carAutoAuctionService.updateRedisAutoData(carId);
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("设置委托价失败",e);
        }
        return result;
    }

    @RequestMapping(value = "/deleteEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteEntrustPrice(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long carId = obj.getLong("carId");
            Long customerId = obj.getLong("customerId");
            CarAuto auto = autoService.selectById(carId);
            if(!CarStatusEnum.WAITING_AUCTION.value().equals(auto.getStatus())){
                result.setError(ResultCode.NO_ALLOW_ENTRUST.strValue(),ResultCode.NO_ALLOW_ENTRUST.getRemark());
                return result;
            }
            CarCustomerEntrustCar entrustCar = entrustCarService.selectEntrustByCarId(carId,customerId);
            if(entrustCar==null){
                result.setError("301","还未设置委托价");
                map.put("count",0);
                return result;
            }
            CarCustomerEntrustCar record = new CarCustomerEntrustCar();
            record.setCarId(carId);
            record.setCustomerId(customerId);
            Integer count = entrustCarService.deleteCarEntrustPriceStatus(record);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess("0","成功");
            //**删除委托价成功，更新redisautodata信息
            carAutoAuctionService.updateRedisAutoData(carId);
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("删除委托价失败",e);
        }
        return result;
    }
}
