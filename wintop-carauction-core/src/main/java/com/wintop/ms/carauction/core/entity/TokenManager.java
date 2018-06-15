package com.wintop.ms.carauction.core.entity;

import com.wintop.ms.carauction.core.model.TokenModel;

/**
 * 对Token进行操作的接口
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface TokenManager {

    /**
     * 查询应用ID是否受过权
     * @param appId
     * @return
     */
    boolean appIfAuth(String appId);

    /**
     * 创建一个token关联上指定用户
     * @param appId     客户端应用ID
     * @param userId    用户id
     * @return          生成token并封装返回
     */
    TokenModel createToken(String appId, Long userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @param appId          应用ID
     * @return
     */
    TokenModel getToken(String appId, String authentication);

    /**
     * 清除token
     */
    void deleteToken(TokenModel tokenModel);

    /**
     * 清除买家端用户token
     */
    void deleteCusAppUserToken(String userId);

    /**
     * 清除商户端用户token
     */
    void deleteStoreUserToken(String userId);
}
