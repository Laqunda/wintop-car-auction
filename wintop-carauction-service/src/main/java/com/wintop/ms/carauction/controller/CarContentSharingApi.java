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
 * 获取分享车辆内容[分享前调用]接口
 */
@RestController
@RequestMapping("/service/carContentSharing")
public class CarContentSharingApi {
    private static final Logger logger = LoggerFactory.getLogger(CarContentSharingApi.class);
    /**
     * 获取分享车辆内容[分享前调用]接口
     */
    @RequestMapping(value = "/selectCarContentSharing",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectCarContentSharing(@RequestBody JSONObject obj){
        logger.info("获取分享车辆内容[分享前调用]接口");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Long carId = obj.getLong("carId");
        Map<String,Object> map = new HashMap<>();
        try {
            map.put("shareAddress","www.baidu.com");
            map.put("subtitle","精选车源、现场竞拍场次、车辆抢鲜看");
            map.put("titleImg","www.baidu.com");
            map.put("shareTitle","无");
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("获取分享车辆内容[分享前调用]失败",e);
        }
        return result;
    }
}
