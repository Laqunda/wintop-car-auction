package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerDeposit;

/**
 * @Description:用户保证金
 * @author zhangzijuan
 * @date 2018-02-08
 */
public interface ICarCustomerDepositWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerDeposit record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerDeposit record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerDeposit record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerDeposit record);

    /**
     * 根据会员id修改会员保证金
     * @param record
     * @return
     */
    int updateDepositByUserId(CarCustomerDeposit record);
}