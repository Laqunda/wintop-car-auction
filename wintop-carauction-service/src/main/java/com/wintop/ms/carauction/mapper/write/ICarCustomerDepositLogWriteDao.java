package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerDepositLog;

/**
 * @author zhangzijuan
 * @Description:保证金日志
 * @date 2018-03-27
 */
public interface ICarCustomerDepositLogWriteDao {

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerDepositLog record);
}
