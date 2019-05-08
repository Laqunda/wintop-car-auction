package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarChaboshiStoreConf;
import com.wintop.ms.carauction.mapper.read.CarChaboshiStoreConfReadDao;
import com.wintop.ms.carauction.mapper.write.CarChaboshiStoreConfWriteDao;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查博士店铺设置 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-08
 */
@Repository
public class CarChaboshiStoreConfModel {
    @Autowired
    private CarChaboshiStoreConfReadDao readDao;
    @Autowired
    private CarChaboshiStoreConfWriteDao writeDao;

    /**
     * 查询查博士店铺设置信息
     *
     * @param id 查博士店铺设置ID
     * @return 查博士店铺设置信息
     */
    public CarChaboshiStoreConf selectCarChaboshiStoreConfById(Long id) {
        return readDao.selectCarChaboshiStoreConfById(id);
    }

    /**
     * 查询查博士店铺设置列表
     *
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 查博士店铺设置集合
     */
    public List<CarChaboshiStoreConf> selectCarChaboshiStoreConfList(CarChaboshiStoreConf carChaboshiStoreConf) {
        return readDao.selectCarChaboshiStoreConfList(carChaboshiStoreConf);
    }
    public int selectCount(CarChaboshiStoreConf bean) {
        return readDao.selectCount(bean);
    }

    /**
     * 新增查博士店铺设置
     *
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 结果
     */
    public int insertCarChaboshiStoreConf(CarChaboshiStoreConf carChaboshiStoreConf) {
        return writeDao.insertCarChaboshiStoreConf(carChaboshiStoreConf);
    }

    /**
     * 修改查博士店铺设置
     *
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 结果
     */
    public int updateCarChaboshiStoreConf(CarChaboshiStoreConf carChaboshiStoreConf) {
        return writeDao.updateCarChaboshiStoreConf(carChaboshiStoreConf);
    }

    /**
     * 删除查博士店铺设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarChaboshiStoreConfByIds(String ids) {
        return writeDao.deleteCarChaboshiStoreConfByIds(Convert.toStrArray(ids));
    }


}
