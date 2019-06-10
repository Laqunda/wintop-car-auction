package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarChaboshiStoreConf;
import com.wintop.ms.carauction.model.CarChaboshiStoreConfModel;
import com.wintop.ms.carauction.service.ICarChaboshiStoreConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查博士店铺设置 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-08
 */
@Service
public class CarChaboshiStoreConfServiceImpl implements ICarChaboshiStoreConfService {
    @Autowired
    private CarChaboshiStoreConfModel model;

    /**
     * 查询查博士店铺设置信息
     *
     * @param id 查博士店铺设置ID
     * @return 查博士店铺设置信息
     */
    @Override
    public CarChaboshiStoreConf selectCarChaboshiStoreConfById(Long id) {
        return model.selectCarChaboshiStoreConfById(id);
    }

    /**
     * 查询查博士店铺设置列表
     *
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 查博士店铺设置集合
     */
    @Override
    public List<CarChaboshiStoreConf> selectCarChaboshiStoreConfList(CarChaboshiStoreConf carChaboshiStoreConf) {
        return model.selectCarChaboshiStoreConfList(carChaboshiStoreConf);
    }

    @Override
    public int selectCount(CarChaboshiStoreConf bean) {
        return model.selectCount(bean);
    }

    /**
     * 新增查博士店铺设置
     *
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 结果
     */
    @Override
    public int insertCarChaboshiStoreConf(CarChaboshiStoreConf carChaboshiStoreConf) {
        return model.insertCarChaboshiStoreConf(carChaboshiStoreConf);
    }

    /**
     * 修改查博士店铺设置
     *
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 结果
     */
    @Override
    public int updateCarChaboshiStoreConf(CarChaboshiStoreConf carChaboshiStoreConf) {
        return model.updateCarChaboshiStoreConf(carChaboshiStoreConf);
    }

    /**
     * 删除查博士店铺设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarChaboshiStoreConfByIds(String ids) {
        return model.deleteCarChaboshiStoreConfByIds(ids);
    }

}
