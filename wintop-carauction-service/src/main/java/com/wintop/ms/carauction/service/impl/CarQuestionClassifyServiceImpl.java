package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarQuestionClassify;
import com.wintop.ms.carauction.model.CarQuestionClassifyModel;
import com.wintop.ms.carauction.model.CarQuestionModel;
import com.wintop.ms.carauction.service.ICarQuestionClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
@Service
public class CarQuestionClassifyServiceImpl implements ICarQuestionClassifyService{
    @Autowired
    private CarQuestionClassifyModel model;
    @Autowired
    private CarQuestionModel questionModel;
    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarQuestionClassify> selectByExample(Map<String, Object> map) {
        List<CarQuestionClassify> list = model.selectByExample(map);
        return list;
    }

    @Override
    public CarQuestionClassify selectById(Long id) {
        CarQuestionClassify carQuestionClassify =  model.selectById(id);
        return carQuestionClassify;
    }

    @Override
    @Transactional
    public Integer deleteById(Long id) {
        Integer count = model.deleteById(id);
        questionModel.deleteByClassifyId(id);
        return count;
    }

    @Override
    public Integer insert(CarQuestionClassify carQuestionClassify) {
        Integer count = model.insert(carQuestionClassify);
        return count;
    }

    @Override
    public Integer updateByIdSelective(CarQuestionClassify carQuestionClassify) {
        Integer count = model.updateByIdSelective(carQuestionClassify);
        return count;
    }

    @Override
    public Integer updateById(CarQuestionClassify carQuestionClassify) {
        Integer count = model.updateById(carQuestionClassify);
        return count;
    }

}
