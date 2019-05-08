package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAssess;

import java.util.List;

/**
 * 车辆评估 数据层
 *
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessReadDao {
    /**
     * 查询车辆评估信息
     *
     * @param id 车辆评估ID
     * @return 车辆评估信息
     */
    public CarAssess selectCarAssessById(Long id);

    /**
     * 查询车辆评估列表
     *
     * @param carAssess 车辆评估信息
     * @return 车辆评估集合
     */
    public List<CarAssess> selectCarAssessList(CarAssess carAssess);

    /**
     * 根据条件查找数量
     *
     * @param carAssess
     */
    Integer selectCarAssessCount(CarAssess carAssess);
}