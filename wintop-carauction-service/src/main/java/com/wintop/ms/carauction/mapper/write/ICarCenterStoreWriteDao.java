package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCenterStore;

public interface ICarCenterStoreWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByCenterId(Long centerId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCenterStore carCenterStore);
}
