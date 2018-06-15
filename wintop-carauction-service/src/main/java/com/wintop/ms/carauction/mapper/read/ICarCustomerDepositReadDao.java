package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerDeposit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户保证金
 * @author zhangzijuan
 * @date 2018-02-08
 */
public interface ICarCustomerDepositReadDao {
    /**
     *@Author:zhangzijuan
     *@date 2018/2/8
     *@Description:根据主键查询记录
     *@param:id
     */
    CarCustomerDeposit selectByPrimaryKey(Long id);

    /**
     * 根据用户ID查询保证金余额
     * @Autor 付陈林
     * @Time 2018-3-5
     * @param userId
     * @return
     */
    BigDecimal getDepositAmountByUserId(Long userId);


    CarCustomerDeposit selectDepositByUserId(Map<String,Object> map);

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
}