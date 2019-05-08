package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarChaboshiLog;
import com.wintop.ms.carauction.mapper.read.CarChaboshiLogReadDao;
import com.wintop.ms.carauction.mapper.write.CarChaboshiLogWriteDao;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查博士日志 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-08
 */
@Repository
public class CarChaboshiLogModel {
    @Autowired
    private CarChaboshiLogReadDao readDao;

    @Autowired
    private CarChaboshiLogWriteDao writeDao;

    /**
     * 查询查博士日志信息
     *
     * @param id 查博士日志ID
     * @return 查博士日志信息
     */
    public CarChaboshiLog selectCarChaboshiLogById(Long id) {
        return readDao.selectCarChaboshiLogById(id);
    }

    /**
     * 查询查博士日志列表
     *
     * @param carChaboshiLog 查博士日志信息
     * @return 查博士日志集合
     */
    public List<CarChaboshiLog> selectCarChaboshiLogList(CarChaboshiLog carChaboshiLog) {
        return readDao.selectCarChaboshiLogList(carChaboshiLog);
    }

    public int selectCount(CarChaboshiLog carChaboshiLog) {
        return readDao.selectCount(carChaboshiLog);
    }

    /**
     * 新增查博士日志
     *
     * @param carChaboshiLog 查博士日志信息
     * @return 结果
     */
    public int insertCarChaboshiLog(CarChaboshiLog carChaboshiLog) {
        return writeDao.insertCarChaboshiLog(carChaboshiLog);
    }

    /**
     * 修改查博士日志
     *
     * @param carChaboshiLog 查博士日志信息
     * @return 结果
     */
    public int updateCarChaboshiLog(CarChaboshiLog carChaboshiLog) {
        return writeDao.updateCarChaboshiLog(carChaboshiLog);
    }

    /**
     * 删除查博士日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarChaboshiLogByIds(String ids) {
        return writeDao.deleteCarChaboshiLogByIds(Convert.toStrArray(ids));
    }


}
