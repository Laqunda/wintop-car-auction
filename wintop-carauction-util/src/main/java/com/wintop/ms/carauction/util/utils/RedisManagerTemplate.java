package com.wintop.ms.carauction.util.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RedisManagerTemplate {

    private RedisTemplate<String, String> redisTemplate;

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
    }

    public boolean add(String key, String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
                valueOper.set(key, value);
                return true;
            }
        }, false, true);
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

    public boolean update(String key,String value) {
        if (get(key) == null) {
            add(key,value);
            return true;
            //throw new NullPointerException("数据行不存在, key = " + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
                valueOper.set(key, value);
                return true;
            }
        });
        return result;
    }

    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                ValueOperations<String, String > operations = redisTemplate.opsForValue();
                String value =  operations.get(key);
                return value;
            }
        });
        return result;
    }

}
