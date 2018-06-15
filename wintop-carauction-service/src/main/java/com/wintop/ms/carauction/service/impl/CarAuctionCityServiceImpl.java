package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionCity;
import com.wintop.ms.carauction.model.CarAuctionCityModel;
import com.wintop.ms.carauction.service.ICarAuctionCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarAuctionCityServiceImpl implements ICarAuctionCityService {
    @Autowired
    private CarAuctionCityModel model;
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        return new ServiceResult<>(true,0);
    }

    @Override
    public ServiceResult<List<CarAuctionCity>> selectByExample(Map<String, Object> map) {
        List<CarAuctionCity> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarAuctionCity> selectById(Long id) {
        CarAuctionCity auctionCity = model.selectById(id);
        return new ServiceResult<>(true,auctionCity);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateDelById(Long id) {
        Integer count = model.updateDelById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarAuctionCity carAuctionCity) {
        Integer count = model.insert(carAuctionCity);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdKeySelective(CarAuctionCity carAuctionCity) {
        Integer count = model.updateByIdSelective(carAuctionCity);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarAuctionCity carAuctionCity) {
        Integer count = model.updateById(carAuctionCity);
        return new ServiceResult<>(true,count);
    }
}
