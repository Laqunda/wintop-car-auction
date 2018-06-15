package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarAgentUser;

import java.util.List;

/**
 *
 * @author mike
 *
 * @version 0.0.1
 *
 * @date 2019-1-30
 *
 */
public interface ICarAgentUserService {

    List<CarAgentUser> findAllUsers();
}
