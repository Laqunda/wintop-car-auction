package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CustomerQuitLog;
import com.wintop.ms.carauction.model.CustomerQuitLogModel;
import com.wintop.ms.carauction.service.ICustomerQuitLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * class_name: CustomerQuitLogServiceImpl
 * package: com.wintop.ms.carauction.service.impl
 * describe: 查询用户退会记录列表
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/13:51
 **/
@Service("customerQuitLogService")
public class CustomerQuitLogServiceImpl implements ICustomerQuitLogService{
    @Resource
    private CustomerQuitLogModel customerQuitLogModel;
    private static final Logger logger = LoggerFactory.getLogger(CustomerQuitLogServiceImpl.class);
    /**
     * 查询用户退会记录列表
     */
    @Override
    public List<CustomerQuitLog> queryCustomerQuitLogList(Map<String, Object> map) {
        List<CustomerQuitLog> list= customerQuitLogModel.queryCustomerQuitLogList(map);
        return list;
    }
    /**
     * 查询用户退会记录数量
     */
    @Override
    public Integer selectCustomerQuitLogCount(Map<String, Object> map) {
        return customerQuitLogModel.selectCustomerQuitLogCount(map);
    }
}
