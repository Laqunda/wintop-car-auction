package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateLevelConf;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarEvaluateLevelConfService;
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

/**
 * describe: 查询星级评价配置列表
 * creat_user: mazg.
 * creat_date-time: 2019/5/7/
 **/
@RestController
@RequestMapping("/service/carEvaluateLevelConfApi")
public class CarEvaluateLevelConfApi {

    private static final Logger logger = LoggerFactory.getLogger(CarEvaluateLevelConfApi.class);

    @Autowired
    private ICarEvaluateLevelConfService carEvaluateLevelConfService;

    @RequestMapping( value = "/queryCarEvaluateLevelConfList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<List<Map<String, Object>>> queryCarEvaluateLevelConfList(@RequestBody JSONObject obj) {
        logger.info("查询星级评价配置列表");
        ServiceResult<List<Map<String,Object>>> result = new ServiceResult<>();
        try {
            List<Map<String,Object>> list= Lists.newArrayList();
            List<CarEvaluateLevelConf> recordList = carEvaluateLevelConfService.queryCarEvaluateLevelConfList(Collections.emptyMap());
            Map<String, Object> recordMap = Maps.newHashMap();
            for (CarEvaluateLevelConf record : recordList) {
                recordMap = Maps.newHashMap();
                recordMap.put("level", record.getLevel());
                recordMap.put("star", record.getStar());
                list.add(recordMap);
            }
            result.setResult(list);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("参数数据转换",e);
            result.setError(String.valueOf(ResultStatus.PARAMETERS_ERROR.getCode()),ResultStatus.PARAMETERS_ERROR.getMessage());
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
}
