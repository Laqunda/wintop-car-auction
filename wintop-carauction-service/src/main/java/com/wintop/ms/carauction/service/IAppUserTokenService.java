package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.AppUserToken;
/**
 * 用户token设置业务层
 * @author zhangzijuan
 * @date 2018-02-08
 */
public interface IAppUserTokenService {
    /**
     * 根据id查询用户token设置
     * @param id
     * @return AppUserToken
     */
    ServiceResult<AppUserToken> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户token设置
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(AppUserToken record);
    /**
     * 根据主键更新用户token设置
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(AppUserToken record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(AppUserToken record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(AppUserToken record);
}