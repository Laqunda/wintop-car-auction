package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionFareSetting;
import com.wintop.ms.carauction.service.ICarAuctionFareSettingService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:加价金额配置基础表
 * @date 2018-03-16
 */
@RestController
@RequestMapping("service/fareSetting")
public class CarAuctionFareSettingApi {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarAuctionFareSettingApi.class);

    @Autowired
    private ICarAuctionFareSettingService fareSettingService;
    /***
     * 查询所有的价格
     * @return
     */
    @PostMapping(value = "/selectAllFare",produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarAuctionFareSetting>> selectAllFare() {
        Logger.info("查询所有的价格");
        return fareSettingService.selectAllFare();
    }
}
