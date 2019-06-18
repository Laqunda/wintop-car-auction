package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.CarManagerRolePublish;
import com.wintop.ms.carauction.service.ICarManagerRolePublishService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/publish")
public class CarManagerRolePublishApi {

    private static final Logger logger = LoggerFactory.getLogger(CarManagerRolePublishApi.class);

    @Autowired
    private ICarManagerRolePublishService carManagerRolePublishService;


    /**
     * 查询用户审车权限关联表
     */
    @ApiOperation(value = "查询用户审车权限关联表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> list(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = null;

        try {
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));

            result = new ServiceResult<>();
            Map<Long,List<CarManagerRolePublish>> publishMap = carManagerRolePublishService.selectTreeByCondition(param);
            result.setResult(Collections.singletonMap("data",publishMap));
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询用户审车权限关联表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存用户审车权限关联表
     */
    @ApiOperation(value = "保存用户审车权限关联表")
    @RequestMapping(value = "/saveOrUpdateList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult saveOrUpdateList(@RequestBody JSONObject obj) {
        ServiceResult result = null;
        try {
            List<CarManagerRolePublish> publishList = Lists.newArrayList();
            try {
                publishList = JSONObject.parseArray(obj.get("data").toString(), CarManagerRolePublish.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (CollectionUtils.isEmpty(publishList)) {
                result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
                return result;
            }
            carManagerRolePublishService.saveOrUpdate(publishList);
            result = new ServiceResult();
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("保存用户审车权限关联表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}