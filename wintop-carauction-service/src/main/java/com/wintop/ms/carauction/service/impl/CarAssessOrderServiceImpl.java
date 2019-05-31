package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.CarAssessOrder;
import com.wintop.ms.carauction.entity.CarStore;
import com.wintop.ms.carauction.model.CarAssessModel;
import com.wintop.ms.carauction.model.CarAssessOrderModel;
import com.wintop.ms.carauction.model.CarStoreModel;
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
    @Autowired
    private CarAssessModel carAssessModel;
    @Autowired
    private CarStoreModel carStoreModel;
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
     * @param param 评估采购单信息
     * @return 评估采购单集合
     */
    @Override
    public List<CarAssessOrder> selectCarAssessOrderList(Map<String,Object> param) {
        List<CarAssessOrder> carAssessOrderList = model.selectCarAssessOrderList(param);
        for (CarAssessOrder recored : carAssessOrderList) {
            CarAssess carAssess = new CarAssess();
            carAssess.setId(recored.getAssessId());
            CarAssess carAssessDao = carAssessModel.selectCarAssessById(carAssess);
            recored.setCarAssess(carAssessDao);
            if (recored.getStoreId() != null) {
                CarStore carStore = carStoreModel.selectByPrimaryKey(recored.getStoreId());
                recored.setCarStore(carStore);
            }
        }
        return carAssessOrderList;
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
    public int selectAssessOrderCount(Map<String,Object> param) {
        return model.selectAssessOrderCount(param);
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
