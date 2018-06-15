package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.model.CarAgentUserModel;
import com.wintop.ms.carauction.entity.CarAgentUser;
import com.wintop.ms.carauction.service.ICarAgentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author mike
 *
 * @version 0.0.1
 *
 * @date 2018-10-19
 *
 */
@Service
public class CarAgentUserServiceImpl implements ICarAgentUserService {

    private static final String CACHE_KEY = "'user'";
    private static final String CACHE_NAME_B = "cache-b";

    //* @Cacheable : Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
    //* @CacheEvict : 清除缓存。
    //* @CachePut : @CachePut也可以声明一个方法支持缓存功能。使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。

    @Autowired
    CarAgentUserModel carAgentUserModel;


    @Override
    public List<CarAgentUser> findAllUsers() {
        return carAgentUserModel.findAllUsers();
    }

}