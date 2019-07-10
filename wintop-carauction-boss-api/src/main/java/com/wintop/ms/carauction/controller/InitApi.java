package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author admin
 * @Description:店铺信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/initapi")
public class InitApi {

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    @Qualifier(value = "redisTemplate")
    public void setRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        //泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }
    /**
     * 查询店铺列表
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询店铺列表")
    @RequestMapping(value = "/initappid",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public ResponseEntity<JSONObject> selectStoreList(@RequestBody Map<String,Object> map) {
        Object obj = map.get("appid");
        if(obj!=null){
            redisTemplate.boundValueOps(obj.toString()).set("true");
        }
        ResponseEntity<JSONObject> response = new ResponseEntity<JSONObject>(HttpStatus.OK);
        return response;
    }
}
