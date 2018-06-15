package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarCenterStore;
import com.wintop.ms.carauction.entity.CommonNameVo;

import java.util.List;

public interface ICarCenterStoreService {
    /**
     * 查询所有记录集
     */
    public List<CommonNameVo> selectAllStore(Long centerId);

    /**
     * 根据主键删除记录
     */
    public int deleteByCenterId(Long centerId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarCenterStore carCenterStore);
}
