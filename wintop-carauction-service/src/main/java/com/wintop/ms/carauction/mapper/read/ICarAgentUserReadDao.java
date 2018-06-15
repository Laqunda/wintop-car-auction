package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAgentUser;

import java.util.List;

/**
 * 用户 mybatis 接口文件。
 *
 * @author Mike
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
public interface ICarAgentUserReadDao {

    List<CarAgentUser> findAllUsers();
}