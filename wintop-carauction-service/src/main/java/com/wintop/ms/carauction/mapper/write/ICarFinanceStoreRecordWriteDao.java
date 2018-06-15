package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarFinanceStoreRecord;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface ICarFinanceStoreRecordWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarFinanceStoreRecord carFinanceStoreRecord);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(CarFinanceStoreRecord carFinanceStoreRecord);

    /**
     * 根据主键更新记录
     */
    int updateById(CarFinanceStoreRecord carFinanceStoreRecord);
}
