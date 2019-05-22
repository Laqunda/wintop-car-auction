package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarAssessOrder;
import com.wintop.ms.carauction.model.CarAssessOrderModel;
import com.wintop.ms.carauction.service.ICarAssessOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 评估采购单 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessOrderServiceImpl implements ICarAssessOrderService {
    @Autowired
    private CarAssessOrderModel model;

    /**
     * 查询评估采购单信息
     *
     * @param id 评估采购单ID
     * @return 评估采购单信息
     */
    @Override
    public CarAssessOrder selectCarAssessOrderById(Long id) {
        return model.selectCarAssessOrderById(id);
    }

    /**
     * 查询评估采购单列表
     *
     * @param carAssessOrder 评估采购单信息
     * @return 评估采购单集合
     */
    @Override
    public List<CarAssessOrder> selectCarAssessOrderList(CarAssessOrder carAssessOrder) {
        return model.selectCarAssessOrderList(carAssessOrder);
    }

    /**
     * 新增评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
    @Override
    public int insertCarAssessOrder(CarAssessOrder carAssessOrder) {
        return model.insertCarAssessOrder(carAssessOrder);
    }

    /**
     * 修改评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
    @Override
    public int updateCarAssessOrder(CarAssessOrder carAssessOrder) {
        return model.updateCarAssessOrder(carAssessOrder);
    }

    /**
     * 删除评估采购单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarAssessOrderByIds(String ids) {
        return model.deleteCarAssessOrderByIds(ids);
    }


    @Override
    public int selectAssessOrderCount(CarAssessOrder carAssessOrder) {
        return model.selectAssessOrderCount(carAssessOrder);
    }

    @Override
    public int selectCountById(Long userId) {
        return model.selectCountById(userId);
    }

    @Override
    public List<CarAssessOrder> selectUserOrderList(Map<String, Object> map) {
        return model.selectUserOrderList(map);
    }
}
