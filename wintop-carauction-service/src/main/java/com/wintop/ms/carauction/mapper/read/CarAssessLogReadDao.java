package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAssessLog;

import java.util.List;

/**
 * 评估日志 数据层
 *
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessLogReadDao {
    /**
     * 查询评估日志信息
     *
     * @param id 评估日志ID
     * @return 评估日志信息
     */
    public CarAssessLog selectCarAssessLogById(Long id);

    /**
     * 查询评估日志列表
     *
     * @param carAssessLog 评估日志信息
     * @return 评估日志集合
     */
    public List<CarAssessLog> selectCarAssessLogList(CarAssessLog carAssessLog);

    int selectAssessCount(CarAssessLog carAssessLog);
}