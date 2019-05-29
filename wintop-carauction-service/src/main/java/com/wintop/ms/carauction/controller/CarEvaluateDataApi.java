package com.wintop.ms.carauction.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.CarEvaluateData;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarEvaluateDataService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * class_name: CarEvaluateDataApi
 * describe: 评价数据记录
 * creat_user: mazg.
 * creat_date-time: 2019/5/7
 **/
@RestController
@RequestMapping("/service/carEvaluateDataApi")
public class CarEvaluateDataApi {
    private static final Logger logger = LoggerFactory.getLogger(CarEvaluateDataApi.class);

    @Autowired
    private ICarEvaluateDataService carEvaluateDataService;

    /**
     * 保存评价内容
     * @param obj
     * @return
     */
    @RequestMapping(value = "/insertCarEvaluateData",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ServiceResult<Map<String,Object>> insertCarEvaluateData(@RequestBody JSONObject obj){
        logger.info("保存评估数据");
        CarEvaluateData carEvaluateData = new CarEvaluateData();
        if (StringUtils.isNotEmpty(obj.getString("objId"))) {
            carEvaluateData.setObjId(Longs.tryParse(obj.getString("objId")));
        }
        if (StringUtils.isNotEmpty(obj.getString("type"))) {
            carEvaluateData.setType(Longs.tryParse(obj.getString("type")));
        }
        if (StringUtils.isNotEmpty(obj.getString("star"))) {
            carEvaluateData.setStarLevel(BigDecimal.valueOf(Double.valueOf(obj.getString("star"))));
        }
        if (StringUtils.isNotEmpty(obj.getString("tagIds"))) {
            carEvaluateData.setTagIds(obj.getString("tagIds"));
        }
        if (StringUtils.isNotEmpty(obj.getString("tagContent"))) {
            carEvaluateData.setTags(obj.getString("tagContent"));
        }
        if (StringUtils.isNotEmpty(obj.getString("content"))) {
            carEvaluateData.setContent(obj.getString("content"));
        }
        if (StringUtils.isNotEmpty(obj.getString("createPerson"))) {
            try {
                carEvaluateData.setCreatePerson(Longs.tryParse(obj.getString("createPerson")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        carEvaluateData.setCreateTime(new Date());
        return carEvaluateDataService.insertSelective(carEvaluateData);
    }

    /**
     * 查询评估列表
     */
    @ApiOperation(value = "查询评估列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarEvaluateData>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarEvaluateData>> result = null;
        try {
            result = new ServiceResult<>();
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            int count = carEvaluateDataService.count(param);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            param.put("startRowNum",pageEntity.getStartRowNum());
            param.put("endRowNum",pageEntity.getEndRowNum());
            List<CarEvaluateData> list = carEvaluateDataService.list(param);
            ListEntity<CarEvaluateData> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询评估列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }

    /**
     * 查询评估列表
     */
    @ApiOperation(value = "查询评估列表")
    @RequestMapping(value = "/allList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> allList(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = null;
        try {
            result = new ServiceResult<>();
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            List<CarEvaluateData> list = carEvaluateDataService.list(param);
            result.setResult(Collections.singletonMap("list",list));
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询评估列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }
}
