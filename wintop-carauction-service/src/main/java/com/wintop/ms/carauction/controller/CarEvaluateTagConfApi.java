package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateTagConf;
import com.wintop.ms.carauction.service.ICarEvaluateTagConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆信息接口类
 */
@RestController
@RequestMapping("/service/carEvaluateTagConf")
public class CarEvaluateTagConfApi {

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
}
