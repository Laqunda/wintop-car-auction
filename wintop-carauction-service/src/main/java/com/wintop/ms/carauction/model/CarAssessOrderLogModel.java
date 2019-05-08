package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAssessOrderLog;
import com.wintop.ms.carauction.mapper.read.CarAssessOrderLogReadDao;
import com.wintop.ms.carauction.mapper.write.CarAssessOrderLogWriteDao;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估采购日志 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Repository
public class CarAssessOrderLogModel {
    @Autowired
    private CarAssessOrderLogReadDao readDao;

    @Autowired
    private CarAssessOrderLogWriteDao writeDao;

    /**
     * 查询评估采购日志信息
     *
     * @param id 评估采购日志ID
     * @return 评估采购日志信息
     */
    public CarAssessOrderLog selectCarAssessOrderLogById(Long id) {
        return readDao.selectCarAssessOrderLogById(id);
    }

    /**
     * 查询评估采购日志列表
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 评估采购日志集合
     */
    public List<CarAssessOrderLog> selectCarAssessOrderLogList(CarAssessOrderLog carAssessOrderLog) {
        return readDao.selectCarAssessOrderLogList(carAssessOrderLog);
    }

    /**
     * 新增评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
    public int insertCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog) {
        return writeDao.insertCarAssessOrderLog(carAssessOrderLog);
    }

    /**
     * 修改评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
    public int updateCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog) {
        return writeDao.updateCarAssessOrderLog(carAssessOrderLog);
    }

    /**
     * 删除评估采购日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarAssessOrderLogByIds(String ids) {
        return writeDao.deleteCarAssessOrderLogByIds(Convert.toStrArray(ids));
    }

    public int selectAssessOrderCount(CarAssessOrderLog carAssessOrderLog) {

        return readDao.selectAssessOrderCount(carAssessOrderLog);

    }
}
