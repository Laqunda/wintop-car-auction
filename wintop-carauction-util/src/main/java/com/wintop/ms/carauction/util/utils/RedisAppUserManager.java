package com.wintop.ms.carauction.util.utils;


import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.AppUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * app用户redis信息管理
 */
@Component
public class RedisAppUserManager {
    Logger logger = LoggerFactory.getLogger(RedisAppUserManager.class);

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    @Qualifier(value = "redisTemplate")
    public void setRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        //泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public String get(String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                BoundValueOperations operations = redisTemplate.boundValueOps(key);
                return operations.get(0,-1);
            }
        });
        return result;
    }
    public void delete(String key) {
        List<String> list = new ArrayList<String>();
        list.add(key);
        delete(list);
    }
    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    /***
     * 保存用户信息到缓存
     * @param appUser
     * @return
     */
    public boolean saveUser(AppUser appUser) {
        logger.info("保存用户信息到缓存");
        boolean result = false;
        if (appUser!=null) {
            String key = Constants.CUS_APP_ID + appUser.getId();
            if (get(key) == null) {
                return this.add(key, JSONObject.toJSONString(appUser));
            }
            result = redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    BoundValueOperations operations = redisTemplate.boundValueOps(key);
                    operations.set(JSONObject.toJSONString(appUser));
                    return true;
                }
            });
        }
        return result;
    }

    public boolean add(String key,String value) {
        logger.info("新增redis中保存的用户信息");
        boolean result = false;
        if (StringUtils.isNotEmpty(value)) {
            result = redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    BoundValueOperations operations = redisTemplate.boundValueOps(key);
                    operations.set(value);
                    return true;
                }
            }, false, true);
        }
        return result;
    }

    /***
     * 获取redis中保存的用户信息
     * @param key
     * @return
     */
    public AppUser getAppUser(String key) {
        logger.info("获取redis中保存的用户信息");
        key = Constants.CUS_APP_ID + key;
        String finalKey = key;
        AppUser result = null;
        try {
            result = redisTemplate.execute(new RedisCallback<AppUser>() {
                public AppUser doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    BoundValueOperations operations = redisTemplate.boundValueOps(finalKey);
                    String value = operations.get(0,-1);
                    if (StringUtils.isEmpty(value)){
                        return null;
                    }else {
                        if (value.indexOf("{")>0){
                            value  = value.substring(value.indexOf("{"));
                        }
                        return JSONObject.parseObject(value, AppUser.class);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            this.delete(key);
        } finally {
            return result;
        }
    }

    /***
     * 修改用户再redis中的的状态
     * @param key
     * @param status
     * @return
     */
    public boolean updateUserStatus(String key,String status) {
        logger.info("修改用户再redis中的的状态");
        AppUser appUser = getAppUser(key);
        if (appUser == null) {
            return false;
        }
        appUser.setStatus(status);
        key = Constants.CUS_APP_ID+key;
        String finalKey = key;
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                BoundValueOperations operations = redisTemplate.boundValueOps(finalKey);
                operations.set(JSONObject.toJSONString(appUser));
                return true;
            }
        });
        return result;
    }

    /***
     * 清理app买家用户的redis信息
     * @param key
     */
    public void cleanAppUser(String key) {
        delete(Constants.CUS_APP_ID+key);
    }
}
