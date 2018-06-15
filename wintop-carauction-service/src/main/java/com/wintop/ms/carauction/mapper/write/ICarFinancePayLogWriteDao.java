package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarFinancePayLog;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface ICarFinancePayLogWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarFinancePayLog carFinancePayLog);

}
