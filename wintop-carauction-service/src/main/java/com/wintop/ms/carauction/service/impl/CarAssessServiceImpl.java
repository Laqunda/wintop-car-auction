package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.model.CarAssessModel;
import com.wintop.ms.carauction.service.ICarAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆评估 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessServiceImpl implements ICarAssessService {
    @Autowired
    private CarAssessModel carAssessModel;

    /**
     * 查询车辆评估信息
     *
     * @param id 车辆评估ID
     * @return 车辆评估信息
     */
    @Override
    public CarAssess selectCarAssessById(Long id) {
        return carAssessModel.selectCarAssessById(id);
    }

    /**
     * 查询车辆评估列表
     *
     * @param carAssess 车辆评估信息
     * @return 车辆评估集合
     */
    @Override
    public List<CarAssess> selectCarAssessList(CarAssess carAssess) {
        return carAssessModel.selectCarAssessList(carAssess);
    }

    /**
     * 新增车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
    @Override
    public int insertCarAssess(CarAssess carAssess) {
        return carAssessModel.insertCarAssess(carAssess);
    }

    /**
     * 修改车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
    @Override
    public int updateCarAssess(CarAssess carAssess) {
        return carAssessModel.updateCarAssess(carAssess);
    }

    /**
     * 删除车辆评估对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarAssessByIds(String ids) {
        return carAssessModel.deleteCarAssessByIds(ids);
    }

    /**
     * 根据条件查找数量
     *
     * @param carAssess
     */
    @Override
    public int selectAssessCount(CarAssess carAssess) {
        return carAssessModel.selectCarAssessCount(carAssess);
    }
}
