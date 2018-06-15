package com.wintop.ms.carauction.util.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by liangtingsen on 2018/3/3.
 * redis管理工具类
 */
@Component
public class RedisManager {
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    @Qualifier(value = "redisTemplate")
    public void setRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        //泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    /***
     * 根据指定的key和value将值存入redis中，并设置超时时间
     */
    public void setKeyValue(String key, String val, int times, TimeUnit timeUnit) {
        //存储到redis并设置过期时间
        redisTemplate.boundValueOps(key).set(val, times, timeUnit);
    }

    /***
     * 根据key获取redis中对应存储的值
     * @param key
     * @return
     */
    public String getKeyValue(String key){
        return redisTemplate.boundValueOps(key).get();
    }

    /***
     * 清除redis key
     * @param key
     */
    public void delKeyValue(String key){
        redisTemplate.delete(key);
    }

    /***
     * 根据key重新设置存储对象的失效时间
     * @param key
     * @param times
     * @param timeUnit
     */
    public void updateExpire(String key, int times, TimeUnit timeUnit){
        redisTemplate.boundValueOps(key).expire(times, timeUnit);

    }

}
