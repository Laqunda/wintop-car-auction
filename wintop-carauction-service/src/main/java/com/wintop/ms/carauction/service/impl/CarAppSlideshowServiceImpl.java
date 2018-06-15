package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppSlideshow;
import com.wintop.ms.carauction.model.CarAppSlideshowModel;
import com.wintop.ms.carauction.service.ICarAppSlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
@Service
public class CarAppSlideshowServiceImpl implements ICarAppSlideshowService {
    @Autowired
    private CarAppSlideshowModel model;
    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarAppSlideshow> selectByExample(Map<String, Object> map) {
        List<CarAppSlideshow> list = model.selectByExample(map);
        return list;
    }

    @Override
    public CarAppSlideshow selectById(Long id) {
        CarAppSlideshow carAppSlideshow =  model.selectById(id);
        return carAppSlideshow;
    }

    @Override
    public Integer deleteById(Long id) {
        Integer count = model.deleteById(id);
        return count;
    }

    @Override
    public Integer insert(CarAppSlideshow carAppSlideshow) {
        Integer count = model.insert(carAppSlideshow);
        return count;
    }

    @Override
    public Integer updateByIdSelective(CarAppSlideshow carAppSlideshow) {
        Integer count = model.updateByIdSelective(carAppSlideshow);
        return count;
    }

    @Override
    public Integer updateById(CarAppSlideshow carAppSlideshow) {
        Integer count = model.updateById(carAppSlideshow);
        return count;
    }
}
