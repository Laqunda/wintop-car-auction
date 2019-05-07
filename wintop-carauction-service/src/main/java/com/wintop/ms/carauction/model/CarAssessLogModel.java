package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAssessLog;
import com.wintop.ms.carauction.mapper.read.CarAssessLogReadDao;
import com.wintop.ms.carauction.mapper.write.CarAssessLogWriteDao;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估日志 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Repository
public class CarAssessLogModel {
    @Autowired
    private CarAssessLogReadDao readDao;

    @Autowired
    private CarAssessLogWriteDao writeDao;

    /**
     * 查询评估日志信息
     *
     * @param id 评估日志ID
     * @return 评估日志信息
     */
    public CarAssessLog selectCarAssessLogById(Long id) {
        return readDao.selectCarAssessLogById(id);
    }

    /**
     * 查询评估日志列表
     *
     * @param carAssessLog 评估日志信息
     * @return 评估日志集合
     */
    public List<CarAssessLog> selectCarAssessLogList(CarAssessLog carAssessLog) {
        return readDao.selectCarAssessLogList(carAssessLog);
    }

    /**
     * 新增评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
    public int insertCarAssessLog(CarAssessLog carAssessLog) {
        return writeDao.insertCarAssessLog(carAssessLog);
    }

    /**
     * 修改评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
    public int updateCarAssessLog(CarAssessLog carAssessLog) {
        return writeDao.updateCarAssessLog(carAssessLog);
    }

    /**
     * 删除评估日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarAssessLogByIds(String ids) {
        return writeDao.deleteCarAssessLogByIds(Convert.toStrArray(ids));
    }

    public int selectAssessCount(CarAssessLog carAssessLog) {

        return readDao.selectAssessCount(carAssessLog);

    }
}
