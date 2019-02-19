package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.WtAppUser;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * Created by liangtingsen on 2018/2/8.
 * 前端用户使用
 */
public interface IWtAppUserWriteDao {
    /***
     * 保存用户信息
     * @param wtAppUser
     */
    Integer saveUser(WtAppUser wtAppUser);

    /***
     * 修改用户信息
     * @param wtAppUser
     */
    Integer updateUser(WtAppUser wtAppUser);

    /***
     * 修改密码
     * @param appUser
     */
    void updatePwd(WtAppUser appUser);

    Integer updateUserStatus(WtAppUser appUser);
        /**
         * 批量修改用户级别
         *@Author:zhangzijuan
         *@date 2018/3/17
         *@param:
         */
    Integer batchUpdateUserLevel(Map<String,Object> map);

    Integer updateUserInfo(WtAppUser appUser);

}
