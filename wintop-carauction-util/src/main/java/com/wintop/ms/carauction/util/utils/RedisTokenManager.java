package com.wintop.ms.carauction.util.utils;


import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.TokenModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 *
 * @see TokenManager
 */
@Component
public class RedisTokenManager implements TokenManager {
    Logger logger = LoggerFactory.getLogger(RedisTokenManager.class);

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    @Qualifier(value = "redisTemplate")
    public void setRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.opsForValue().set("bdce67a4230d20f2a14e72a2670cbd52", "true");
        //泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public boolean appIfAuth(String appId) {
        if (appId.isEmpty()) {
            return false;
        }
        //从redis中取出应用授权信息，并判断授权标示
        String ifAuth = redisTemplate.boundValueOps(appId).get();
//        redisTemplate.opsForValue().set("bdce67a4230d20f2a14e72a2670cbd42", "true");
        if (ifAuth != null && ifAuth.equals("true")) {
            return true;
        }
        return false;
    }

    /***
     * 根据请求用户和所使用应用id--生成新的口令token
     * 用户重新获取token使用
     * @param appId
     * @param userId
     * @return
     */
    public TokenModel createToken(String appId, Long userId) {
        TokenModel model = null;
        if (StringUtils.isNotEmpty(appId) && userId != null) {
            //使用uuid作为源token
            String token = UUID.randomUUID().toString().replace("-", "");
            model = new TokenModel(appId, userId, token);
            //存储到redis并设置过期时间
            redisTemplate.boundValueOps(appId + "_" + userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        }
        return model;
    }

    /***
     * 分析提取接口请求头中的appid userid token 信息
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken(String appId, String authentication) {
        if (appId.isEmpty() || authentication.isEmpty() || authentication.indexOf("null") > -1) {
            return null;
        }
        TokenModel tokenModel = null;
        String[] param = null;
        if (authentication != null && authentication.indexOf("_") > -1) {
            param = authentication.split("_");
        }
        try {
            //判断authentication 并提取appid+token+user_id信息
            if (appId.isEmpty() || authentication.isEmpty()) {
                tokenModel = null;
            } else if (param.length != 2) {
                tokenModel = null;
            } else {
                //将提取出来的信息封装为token对象返回
                long userId = Long.parseLong(param[0]);
                String token = param[1];
                tokenModel = new TokenModel(appId, userId, token);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        } finally {
            return tokenModel;
        }

    }

    /***
     * 检查token 并刷新token的过期时间
     * @param model token
     * @return
     */
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        if (model != null && StringUtils.isNotEmpty(model.getAppId()) && model.getUserId() != null) {
            String token = redisTemplate.boundValueOps(model.getAppId() + "_" + model.getUserId()).get();
            if (token == null || !token.equals(model.getToken())) {
                return false;
            }
            //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
            redisTemplate.boundValueOps(model.getAppId() + "_" + model.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
            return true;
        } else {
            return false;
        }
    }

    /***
     * 根据appid和用户ID清理token
     * @param tokenModel
     */
    public void deleteToken(TokenModel tokenModel) {
        if (tokenModel != null && StringUtils.isNotEmpty(tokenModel.getAppId()) && tokenModel.getUserId() != null) {
            redisTemplate.delete(tokenModel.getAppId() + "_" + (tokenModel.getUserId()));
        }
    }

    /***
     * 删除买家端用户token
     * @param userId
     */
    @Override
    public void deleteCusAppUserToken(String userId) {
        if (StringUtils.isNotEmpty(userId)) {
            redisTemplate.delete(Constants.CUS_APP_ID + "_" + userId);
        }
    }

    /***
     * 删除卖家端用户token
     * @param userId
     */
    @Override
    public void deleteStoreUserToken(String userId) {
        if (StringUtils.isNotEmpty(userId)) {
            redisTemplate.delete(Constants.STORE_APP_ID + "_" + userId);
        }
    }
}
