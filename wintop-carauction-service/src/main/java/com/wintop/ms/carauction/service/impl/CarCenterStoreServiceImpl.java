package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarCenterStore;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.model.CarCenterStoreModel;
import com.wintop.ms.carauction.service.ICarCenterStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCenterStoreServiceImpl implements ICarCenterStoreService{
    @Autowired
    private CarCenterStoreModel storeModel;
    /**
     * 查询所有记录集
     */
    @Override
    public List<CommonNameVo> selectAllStore(Long centerId){
        return storeModel.selectAllStore(centerId);
    }

    /**
     * 根据主键删除记录
     */
    @Override
    public int deleteByCenterId(Long centerId){
        return storeModel.deleteByCenterId(centerId);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    @Override
    public int insert(CarCenterStore carCenterStore){
        return storeModel.insert(carCenterStore);
    }
}
