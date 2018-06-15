package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAgentUser;
import com.wintop.ms.carauction.mapper.read.ICarAgentUserReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 简单用户链接Mysql数据库微服务（通过@Repository注解标注该类为持久化操作对象）。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
@Repository
public class CarAgentUserModel  {

    @Autowired
    private ICarAgentUserReadDao readMapper;


    public List<CarAgentUser> findAllUsers() {
        return readMapper.findAllUsers();
    }

}
