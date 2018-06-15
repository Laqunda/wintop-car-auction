package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLog;
import com.wintop.ms.carauction.service.ICarCustomerLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:会员日志
 * @date 2018-03-20
 */
@RestController
@RequestMapping("/service/userLog")
public class CarCustomerLogApi {
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerLogApi.class);
    @Autowired
    private ICarCustomerLogService logService;
    /**
     * 查询会员轨迹
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:userId
     */
    @ApiOperation(value = "查询会员轨迹")
    @ApiImplicitParam(dataType = "Long", name = "userId", value = "用户Id", required = true)
    @PostMapping(value = "selectUserLogByUserId", produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarCustomerLog>> selectUserLogByUserId(@RequestBody JSONObject object){
        logger.info("查询会员轨迹");
        return logService.selectUserLogByUserId(object.getLong("userId"));
    }

}
