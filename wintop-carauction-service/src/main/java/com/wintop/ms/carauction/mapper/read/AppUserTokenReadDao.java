package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.AppUserToken;

import java.util.Map;

/**
 * AppUserTokenReadDao:读用户token设置
 * @author zhangzijuan
 * @date 2018-02-08
 */
public interface AppUserTokenReadDao {
    /**
     * 根据主键查询记录
     */
    AppUserToken selectByPrimaryKey(Long id);


}