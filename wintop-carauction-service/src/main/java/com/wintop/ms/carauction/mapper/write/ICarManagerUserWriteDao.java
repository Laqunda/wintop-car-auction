package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarManagerUser;

import java.util.Map;


public interface ICarManagerUserWriteDao {

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

    /***
     * 修改头像
     * @param map
     * @return
     */
    int updateUserPhoto(Map map);
}