package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.CarEvaluateTagConf;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.service.ICarEvaluateTagConfService;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 评价标签配置
 * @author mazg
 * @date 2019-6-6
 */
@RestController
@RequestMapping("/service/carEvaluateTagConf")
public class CarEvaluateTagConfApi {

    private static final Logger logger = LoggerFactory.getLogger(CarEvaluateTagConfApi.class);
    @Autowired
    private ICarEvaluateTagConfService carEvaluateTagConfService;
    /***
     * 获取评价标签模板
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectEvaluateTemplateList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ServiceResult<Map<String,Object>> selectEvaluateTemplateList( @RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("type", obj.get("type"));
        Map<String, List<CarEvaluateTagConf>> configListMap = carEvaluateTagConfService.queryEvaluateTagConfTreeList(paramMap);
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> entityMap = new HashMap<>();
        List<Map<String, Object>> dataList = Lists.newArrayList();
        List<Map<String, Object>> allDataList = Lists.newArrayList();
        for (Map.Entry<String, List<CarEvaluateTagConf>> entry : configListMap.entrySet()) {
            dataMap = new HashMap<>();
            dataList = Lists.newArrayList();
            dataMap.put("title", entry.getKey());
            for (CarEvaluateTagConf conf : entry.getValue()) {
                entityMap = new HashMap<>();
                entityMap.put("tagId", conf.getId());
                entityMap.put("title", conf.getTitle());
                entityMap.put("star", conf.getStar());
                dataList.add(entityMap);
            }
            dataMap.put("list", dataList);
            allDataList.add(dataMap);
        }
        result.setResult(Collections.singletonMap("dataList",allDataList));
        result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        return result;
    }

    /***
     * 获取评价标签父列表数据
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectBossParentConfList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectBossParentConfList( @RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> param = JSONObject.toJavaObject(obj, Map.class);
        param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));

        List<CarEvaluateTagConf> parentList = Lists.newArrayList();
        // 父列表数据
        param.put("pId",-1);
        try {
            parentList = carEvaluateTagConfService.queryEvaluateTagConfList(param);
            result.setResult(Collections.singletonMap("parentList",parentList));
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            result.setSuccess(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            e.printStackTrace();
        }
        return result;
    }

    /***
     * 获取评价标签父列表数据
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectBossChildrenConfList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectBossChildrenConfList( @RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> param = JSONObject.toJavaObject(obj, Map.class);
        param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));

        Map<Object, List<Map<String, Object>>> childrenList = Maps.newHashMap();
        try {
            // 子列表数据
            childrenList = carEvaluateTagConfService.selectChildrenTagConfList(param);
            result.setResult(Collections.singletonMap("childrenList",childrenList));
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            result.setSuccess(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 新增评价标签配置
     */
    @ApiOperation(value = "新增评价标签配置")
    @RequestMapping(value = "/save",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> save(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //评估
            CarEvaluateTagConf carEvaluateTagConf = JSONObject.toJavaObject(obj, CarEvaluateTagConf.class);
            if (carEvaluateTagConf == null) {
                carEvaluateTagConf = new CarEvaluateTagConf();
            }
            //设置name
            int code = carEvaluateTagConfService.insertSelective(carEvaluateTagConf);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增评价标签配置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 修改评价标签配置
     */
    @ApiOperation(value = "修改评价标签配置")
    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> update(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarEvaluateTagConf carEvaluateTagConf = JSONObject.toJavaObject(obj, CarEvaluateTagConf.class);
            if (carEvaluateTagConf == null) {
                carEvaluateTagConf = new CarEvaluateTagConf();
            }
            int code = carEvaluateTagConfService.updateByPrimaryKeySelective(carEvaluateTagConf);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改评价标签配置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }
}
