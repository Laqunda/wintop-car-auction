package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CustomerQuitLog;
import com.wintop.ms.carauction.mapper.read.ICustomerQuitLogReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * class_name: CustomerQuitLogModel
 * package: com.wintop.ms.carauction.model
 * describe: 查询用户退会记录模型
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/13:28
 **/
@Repository
public class CustomerQuitLogModel {
    @Autowired
    private ICustomerQuitLogReadDao readDao;

    /**
     * 查询用户退会记录列表
     */
    public List<CustomerQuitLog> queryCustomerQuitLogList(Map<String,Object> map){
        return readDao.queryCustomerQuitLogList(map);
    }
    /**
     * 查询用户退会记录的数量
     */
    public Integer selectCustomerQuitLogCount(Map<String,Object> map){
        return readDao.selectCustomerQuitLogCount(map);
    }
}
