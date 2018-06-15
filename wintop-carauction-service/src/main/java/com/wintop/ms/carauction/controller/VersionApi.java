package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取版本号接口
 */
@RestController
@RequestMapping("/service/version")
public class VersionApi {
    private static final Logger logger = LoggerFactory.getLogger(VersionApi.class);
    /**
     * 获取版本号接口
     */
    @RequestMapping(value = "/selectVersion",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectVersion(@RequestBody JSONObject obj){
        logger.info("获取版本号接口");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            map.put("logo","www.baidu.com");
            map.put("title","当前版本1.0");
            map.put("desc","无");
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("获取版本号失败",e);
        }
        return result;
    }
}
