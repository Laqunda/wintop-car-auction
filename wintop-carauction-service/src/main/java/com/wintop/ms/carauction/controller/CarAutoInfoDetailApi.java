package com.wintop.ms.carauction.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.service.ICarAutoInfoDetailService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * 车辆基本信息
 */
@RequestMapping("autoDetail")
@RestController
public class CarAutoInfoDetailApi {

    IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarAutoInfoDetailService carAutoInfoDetailService;
    Logger logger = LoggerFactory.getLogger(CarAutoInfoDetailApi.class);

    @ApiOperation(value = "查询车辆基本信息",notes = "根据车辆id查询车辆基本信息")
    @PostMapping(value = "getByAutoId")
    public ServiceResult<CarAutoInfoDetail> getByAutoId(@RequestBody JSONObject object){
        logger.info("查询车辆基本信息");
        ServiceResult<CarAutoInfoDetail> serviceResult = new ServiceResult<>();
        CarAutoInfoDetail carAutoInfoDetail = carAutoInfoDetailService.selectDetailByCarId(object.getLong("autoId"));
        if (carAutoInfoDetail!=null) {
            serviceResult.setResult(carAutoInfoDetail);
            serviceResult.setSuccess("0", "查询成功");
        }else {
            serviceResult.setSuccess("-1","车辆已不存在");
        }
        return serviceResult;
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "修改车辆基本信息")
    public ServiceResult update(@RequestBody JSONObject object){
        logger.info("修改车辆基本信息");

        //
        Long id;
        if (object.get("id")==null && object.get("autoId")!=null){
            CarAutoInfoDetail autoInfoDetail = carAutoInfoDetailService.selectDetailByCarId(object.getLong("autoId"));
            id = autoInfoDetail.getId();
        }else {
            id = object.getLong("id");
        }

        CarAutoInfoDetail detail = new CarAutoInfoDetail();
        detail.setId(id);
        detail.setAutoId(object.getLong("autoId"));
        detail.setVin(object.getString("vin"));
        detail.setCarShape(object.getString("carShape"));
        detail.setCarShapeCn(object.getString("carShapeCn"));
        detail.setEngineCapacity(object.getString("engineCapacity"));
        detail.setEngineCapacityUnit(object.getString("engineCapacityUnit"));
        detail.setEnvironment(object.getString("environment"));
        detail.setEnvironmentCn(object.getString("environmentCn"));
        detail.setIntakeMethod(object.getString("intakeMethod"));
        detail.setIntakeMethodCn(object.getString("intakeMethodCn"));
        detail.setOilSupplySystem(object.getString("oilSupplySystem"));
        detail.setOilSupplySystemCn(object.getString("oilSupplySystemCn"));
        detail.setTransmission(object.getString("transmission"));
        detail.setTransmissionCn(object.getString("transmissionCn"));
        detail.setVehicleDriver(object.getString("vehicleDriver"));
        detail.setVehicleDriverCn(object.getString("vehicleDriverCn"));
        detail.setMileage(object.get("mileage")==null?null:object.getLong("mileage"));
        detail.setColor(object.getString("color"));
        detail.setColorChanged(object.getString("colorChanged"));
        detail.setManufactureDate(object.get("manufactureDate")==null?null:object.getDate("manufactureDate"));
        detail.setBeginRegisterDate(object.get("beginRegisterDate")==null?null:object.getDate("beginRegisterDate"));
        detail.setVehicleAttributionCity(object.getString("vehicleAttributionCity"));
        detail.setLicenseNumber(object.getString("licenseNumber"));
        detail.setCarNature(object.getString("carNature"));
        detail.setUseNature(object.getString("useNature"));
        detail.setIsModification(object.getString("isModification"));
        detail.setOriginalPrice(object.get("originalPrice")==null?null:object.getBigDecimal("originalPrice"));
        detail.setVehicleAttributionProvince(object.getString("vehicleAttributionProvince"));
        detail.setAutoBrand(object.getString("autoBrand"));
        detail.setAutoSeries(object.getString("autoSeries"));
        detail.setAutoStyle(object.getString("autoStyle"));
        detail.setAutoBrandCn(object.getString("autoBrandCn"));
        detail.setAutoSeriesCn(object.getString("autoSeriesCn"));
        detail.setAutoStyleCn(object.getString("autoStyleCn"));
        detail.setEnvironmentCn(object.getString("environmentCn"));
        detail.setOilSupplySystemCn(object.getString("oilSupplySystemCn"));
        detail.setTransmissionCn(object.getString("transmissionCn"));
        detail.setVehicleDriverCn(object.getString("vehicleDriverCn"));
        detail.setColorCn(object.getString("colorCn"));
        detail.setVehicleAttributionProvinceCn(object.getString("vehicleAttributionProvinceCn"));
        detail.setVehicleAttributionCityCn(object.getString("vehicleAttributionCityCn"));
        detail.setCarNatureCn(object.getString("carNatureCn"));
        detail.setUseNatureCn(object.getString("useNatureCn"));
        detail.setIntakeMethodCn(object.getString("intakeMethodCn"));
        detail.setUpdateTime(new Date());
        detail.setUpdateUser(object.getString("updateUser"));
        if (StringUtils.isBlank(object.getString("autoInfoName"))){
            detail.setAutoInfoName(detail.getAutoStyleCn());
        }else {
            detail.setAutoInfoName(object.getString("autoInfoName"));
        }
        return carAutoInfoDetailService.updateByPrimaryKeySelective(detail);
    }

    @PostMapping(value = "updateRemark")
    @ApiOperation(value = "修改车辆备注信息")
    public ServiceResult updateRemark(@RequestBody JSONObject object){
        logger.info("修改车辆备注信息");
        ServiceResult result = new ServiceResult();
        try {
            CarAutoInfoDetail carAutoInfoDetail = new CarAutoInfoDetail();
            carAutoInfoDetail.setAutoId(object.getLong("autoId"));
            carAutoInfoDetail.setRemark(object.getString("remark"));
            carAutoInfoDetail.setRemarkPhoto(object.getString("remarkPhoto"));
            carAutoInfoDetail.setUpdateUser(object.getString("updateUser"));
            carAutoInfoDetail.setUpdateTime(new Date());
            if (carAutoInfoDetailService.updateRemarkByautoId(carAutoInfoDetail)>0){
                result.setSuccess("0","成功");
            }else {
                result.setSuccess("-1","失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    @PostMapping(value = "saveDetailAndConf")
    @ApiOperation(value = "保存基本信息+配置信息")
    public ServiceResult saveDetailAndConf(@RequestBody JSONObject object){
        logger.info("保存基本信息+配置信息");
        ServiceResult serviceResult = new ServiceResult();
        try {
            CarAutoInfoDetail detail = carAutoInfoDetailService.selectDetailByCarId(object.getLong("autoId"));
            if(detail!=null) {
                //封装基本信息
                detail.setVin(object.getString("vin"));
                detail.setCarShape(object.getString("carShape"));
                detail.setCarShapeCn(object.getString("carShapeCn"));
                detail.setEngineCapacity(object.getString("engineCapacity"));
                detail.setEngineCapacityUnit(object.getString("engineCapacityUnit"));
                detail.setEnvironment(object.getString("environment"));
                detail.setEnvironmentCn(object.getString("environmentCn"));
                detail.setIntakeMethod(object.getString("intakeMethod"));
                detail.setIntakeMethodCn(object.getString("intakeMethodCn"));
                detail.setOilSupplySystem(object.getString("oilSupplySystem"));
                detail.setOilSupplySystemCn(object.getString("oilSupplySystemCn"));
                detail.setTransmission(object.getString("transmission"));
                detail.setTransmissionCn(object.getString("transmissionCn"));
                detail.setVehicleDriver(object.getString("vehicleDriver"));
                detail.setVehicleDriverCn(object.getString("vehicleDriverCn"));

                detail.setOriginalPrice(object.get("originalPrice") == null ? new BigDecimal(0) : object.getBigDecimal("originalPrice"));
                detail.setAutoBrand(object.getString("autoBrand"));
                detail.setAutoSeries(object.getString("autoSeries"));
                detail.setAutoStyle(object.getString("autoStyle"));
                detail.setAutoBrandCn(object.getString("autoBrandCn"));
                detail.setAutoSeriesCn(object.getString("autoSeriesCn"));
                detail.setAutoStyleCn(object.getString("autoStyleCn"));
                detail.setEnvironmentCn(object.getString("environmentCn"));
                detail.setOilSupplySystemCn(object.getString("oilSupplySystemCn"));
                detail.setTransmissionCn(object.getString("transmissionCn"));
                detail.setVehicleDriverCn(object.getString("vehicleDriverCn"));
                detail.setIntakeMethodCn(object.getString("intakeMethodCn"));
                detail.setUpdateTime(new Date());
                detail.setUpdateUser(object.getString("updateUser"));
                detail.setAutoInfoName(object.getString("autoStyleCn"));

                //封装配置信息
                List<CarAutoConfDetail> optionList = new ArrayList<>();
                JSONArray jsonArray = object.getJSONArray("confArray");
                CarAutoConfDetail confDetail;
                for (Object option : jsonArray) {
                    JSONObject optionJson = (JSONObject) option;
                    confDetail = new CarAutoConfDetail();
                    confDetail.setId(idWorker.nextId());
                    confDetail.setAutoId(object.getLong("autoId"));
                    confDetail.setConfOption(optionJson.getString("confOption"));
                    confDetail.setConfOptionCn(optionJson.getString("confOptionCn"));
                    confDetail.setConfTitleId(optionJson.getLong("id"));
                    confDetail.setConfTitleName(optionJson.getString("title"));
                    optionList.add(confDetail);
                }

                detail.setConfDetails(optionList);

                carAutoInfoDetailService.updateDetailAndConf(detail);
            }
            serviceResult.setSuccess("0", "成功");
        }catch (Exception e){
            e.printStackTrace();
            serviceResult.setSuccess("-1", "失败");
        }finally {
            return serviceResult;
        }

    }

}
