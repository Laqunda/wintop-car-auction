package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppAccord;
import com.wintop.ms.carauction.service.ICarAppAccordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 获取客户端相关协议接口
 */
@RestController
@RequestMapping("service/accordApi")
public class CarAppAccordApi {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarAppAccordApi.class);

    @Autowired
    private ICarAppAccordService iCarAppAccordService;

    /***
     * 根据编号查询协议内容
     * @param map
     * @return
     */
    @RequestMapping(value = "/getAccordByCode",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method=RequestMethod.POST)
    public ServiceResult<CarAppAccord> findUserList(@RequestBody Map map) {
        Logger.info("查询协议内容");
        String code = map.get("code").toString();
        return iCarAppAccordService.findByCode(code);
    }
}