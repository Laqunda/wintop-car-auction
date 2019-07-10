package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarManagerRoleData;
import com.wintop.ms.carauction.service.ICarManagerRoleDataService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping( "/service/managerRoleData" )
public class CarManagerRoleDataApi {

    private static final Logger logger = LoggerFactory.getLogger(CarManagerRoleDataApi.class);

    @Autowired
    private ICarManagerRoleDataService carManagerRoleDataService;

    /**
     * 用户数据权限关联表
     */
    @ApiOperation( value = "用户数据权限关联表" )
    @RequestMapping( value = "/allList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<List<CarManagerRoleData>> list(@RequestBody JSONObject obj) {
        ServiceResult<List<CarManagerRoleData>> result = new ServiceResult<>();
        try {
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));

            List<CarManagerRoleData> resultList = carManagerRoleDataService.selectForCondition(param);
            result.setResult(resultList);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("用户数据权限关联表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存用户数据权限关联表
     */
    @ApiOperation( value = "保存用户数据权限关联表" )
    @RequestMapping( value = "/save",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult save(@RequestBody JSONObject obj) {
        ServiceResult result = new ServiceResult<>();
        try {
            CarManagerRoleData carManagerRoleData = JSONObject.toJavaObject(obj, CarManagerRoleData.class);
            if (Objects.nonNull(carManagerRoleData)) {
                carManagerRoleDataService.save(carManagerRoleData);
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else{
                result.setSuccess(ResultCode.NO_PARAM.strValue(), ResultCode.NO_PARAM.getRemark());
            }
        } catch (Exception e) {
            logger.info("保存用户数据权限关联表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


}
