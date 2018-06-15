package com.wintop.ms.carauction.util.utils;


import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.RedisAutoData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 管理存入Reids中的车辆信息
 */
@Component
public class RedisAutoManager {
    Logger logger = LoggerFactory.getLogger(RedisAutoManager.class);

    private RedisAutoData redisAutoData;

    @Autowired
    private RedisManagerTemplate redisManagerTemplate;

    /***
     * 创建车辆
     * @param autoData
     * @return
     */
    public void createAuto(RedisAutoData autoData) {
        redisManagerTemplate.add(autoData.redisKey(),autoData.toJsonStr());
    }

    /***
     * 根据车辆id key获取车辆信息
     * @param autoIDKey
     * @return
     */
    public RedisAutoData getAutoByKey(String autoIDKey){
        String json = redisManagerTemplate.get(autoIDKey);
        if (StringUtils.isNotEmpty(json)){
            redisAutoData = JSONObject.parseObject(json,RedisAutoData.class);
            return redisAutoData;
        }
        return null;
    }

    /***
     * 修改redis车辆信息
     * @param autoData
     * @return
     */
    public void updateAuto(RedisAutoData autoData){
        if (redisManagerTemplate.get(autoData.redisKey())==null){
            this.createAuto(autoData);
        }else {
            redisManagerTemplate.update(autoData.redisKey(),JSONObject.toJSONString(autoData));
        }
    }

    /***
     * 删除redis车辆
     * @param autoIdKey
     */
    public void delAuto(String autoIdKey){
        redisManagerTemplate.delete(autoIdKey);
    }

    /***
     * 根据autoId删除redis车辆
     * @param autoId
     */
    public void delDataByAutoId(String autoId){
        redisManagerTemplate.delete(Constants.CAR_AUTO_AUCTION+"_"+autoId);
    }

    /**
     * 批量查询车辆信息
     * @param pattern
     * @return
     */
    public List<RedisAutoData> getRedisAutoDataList(String pattern){
        List<RedisAutoData> list = new ArrayList<>();
        Set<String> keysSet = redisManagerTemplate.getRedisTemplate().keys(pattern);
        for(String key:keysSet){
            RedisAutoData redisAutoData = this.getAutoByKey(key);
            if(redisAutoData!=null){
                list.add(redisAutoData);
            }
        }
        return list;
    }

    /***
     * 根据车辆id获取车辆信息
     * @param autoId
     * @return
     */
    public RedisAutoData getDataByAutoId(String autoId){
        String json = redisManagerTemplate.get(Constants.CAR_AUTO_AUCTION+"_"+autoId);
        if (StringUtils.isNotEmpty(json)){
            redisAutoData = JSONObject.parseObject(json,RedisAutoData.class);
            return redisAutoData;
        }
        return null;
    }
}
