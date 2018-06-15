package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerDeposit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户保证金业务层
 * @author zhangzijuan
 * @date 2018-02-08
 */
public interface ICarCustomerDepositService {
    /**
     * 根据id查询用户保证金
     * @param id
     * @return CarCustomerDeposit
     */
    ServiceResult<CarCustomerDeposit> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户保证金
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerDeposit record);
    /**
     * 根据主键更新用户保证金
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerDeposit record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerDeposit record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerDeposit record);


    /**
     * 保存用户保证金
     * @Autor 付陈林
     * @Time 2018-3-13
     * @param record
     * @return
     */
    int insertCarCustomerDeposit(CarCustomerDeposit record);

    /**
    * 根据用户ID查询保证金余额
    * @Autor 付陈林
    * @Time 2018-3-5
    * @param userId
    * @return
    */
    ServiceResult<Map<String,Object>> getDepositAmountByUserId(Long userId);

    /**
     * 查询保证金列表
     * @Autor zhangzijuan
     * @param map
     * @return
     */
    List<CarCustomerDeposit> selectDepositList(Map<String,Object> map);
    /**
     * 查询保证金总数目
     * @Autor zhangzijuan
     * @param map
     * @return
     */
    Integer  selectDepositCount(Map<String,Object> map);

    CarCustomerDeposit selectDepositByUserId(Map<String,Object> map);
}