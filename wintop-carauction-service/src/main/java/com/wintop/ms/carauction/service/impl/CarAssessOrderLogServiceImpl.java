package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarAssessOrderLog;
import com.wintop.ms.carauction.model.CarAssessOrderLogModel;
import com.wintop.ms.carauction.service.ICarAssessOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评估采购日志 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessOrderLogServiceImpl implements ICarAssessOrderLogService {
    @Autowired
    private CarAssessOrderLogModel model;

    /**
     * 查询评估采购日志信息
     *
     * @param id 评估采购日志ID
     * @return 评估采购日志信息
     */
    @Override
    public CarAssessOrderLog selectCarAssessOrderLogById(Long id) {
        return model.selectCarAssessOrderLogById(id);
    }

    /**
     * 查询评估采购日志列表
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 评估采购日志集合
     */
    @Override
    public List<CarAssessOrderLog> selectCarAssessOrderLogList(CarAssessOrderLog carAssessOrderLog) {
        return model.selectCarAssessOrderLogList(carAssessOrderLog);
    }

    /**
     * 新增评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
    @Override
    public int insertCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog) {
        return model.insertCarAssessOrderLog(carAssessOrderLog);
    }

    /**
     * 修改评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
    @Override
    public int updateCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog) {
        return model.updateCarAssessOrderLog(carAssessOrderLog);
    }

    /**
     * 删除评估采购日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarAssessOrderLogByIds(String ids) {
        return model.deleteCarAssessOrderLogByIds(ids);
    }

    @Override
    public int selectAssessOrderCount(CarAssessOrderLog carAssessOrderLog) {
        return model.selectAssessOrderCount(carAssessOrderLog);
    }

}
