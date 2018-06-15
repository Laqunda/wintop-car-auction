package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CustomerQuitLog;

import java.util.List;
import java.util.Map;

/**
 * class_name: ICustomerQuitLogReadDao
 * package: com.wintop.ms.carauction.mapper.read
 * describe: 查询用户退会记录列表
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/13:26
 **/
public interface ICustomerQuitLogReadDao {
    /**
     * 查询用户退会记录列表
     */
    List<CustomerQuitLog> queryCustomerQuitLogList(Map<String,Object> map);
    /**
     * 查询用户退会记录的数量
     */
    Integer selectCustomerQuitLogCount(Map<String,Object> map);
}
