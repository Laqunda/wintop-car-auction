package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.CommonNameVo;

import java.util.List;
import java.util.Map;

public interface ICarManagerUserService {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarManagerUser> selectByExample(Map<String,Object> map);

    /**
     * 根据用户详情
     */
    CarManagerUser selectByPrimaryKey(Long id,boolean detail);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarManagerUser carManagerUser);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarManagerUser carManagerUser);

    /**
     * 根据账号查询用户
     */
    CarManagerUser selectByUserId(String userKey);

    /***
     * 修改用户头像
     * @param id
     * @param userPhoto
     * @return
     */
    int updateUserPhoto(Long id,String userPhoto);

    /**
     * 查询某个子部门下的所有用户
     * @param departmentId
     * @return
     */
    List<CommonNameVo> selectAllManagerUser(Long departmentId);

    /**
     * 查询管理店铺范围
     * @param roleType
     * @param departmentId
     * @return
     */
    List<Long> queryStoreScope(Long roleType,Long departmentId);

    /**
     * 根据当前用户查询管理范围
     * @param userId
     * @return
     */
    List<Long> queryStoreScope(Long userId);
}
