package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAgent;
import com.wintop.ms.carauction.model.CarAgentModel;
import com.wintop.ms.carauction.service.ICarAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CarAgentServiceImpl implements ICarAgentService {
    @Autowired
    private CarAgentModel model;
    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByParam(map);
        return count;
    }

    @Override
    public List<CarAgent> selectByExample(Map<String, Object> map) {
        List<CarAgent> list = model.selectListByParam(map);
        return list;
    }

    @Override
    public CarAgent selectById(Long id) {
        CarAgent carAgent = model.selectById(id);
        return carAgent;
    }

    @Override
    public Integer deleteById(Long id) {
        Integer count = model.deleteById(id);
        return count;
    }

    @Override
    public Integer insert(CarAgent carAgent) {
        Integer count = model.insert(carAgent);
        return count;
    }

    @Override
    public Integer updateByIdSelective(CarAgent carAgent) {
        Integer count = model.updateByIdSelective(carAgent);
        return count;
    }

    @Override
    public Integer updateById(CarAgent carAgent) {
        Integer count = model.updateById(carAgent);
        return count;
    }

    /**
     * 查询代办列表
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:
     */
    @Override
    public List<CarAgent> selectListByParam(Map<String,Object> map){
        return model.selectListByParam(map);
    }
    /**
     * 查询代办总数
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:
     */
    @Override
    public Integer countByParam(Map<String,Object> map){
        return model.countByParam(map);
    }

    public CarAgent selectByOrderId(Long orderId){
        return model.selectByOrderId(orderId);
    }
}
