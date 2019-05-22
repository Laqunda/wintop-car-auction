package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.CarOrderRetail;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.ICarOrderRetailService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/service/carOrderRetail")
public class CarOrderRetailApi {

    private static final Logger logger = LoggerFactory.getLogger(CarOrderRetailApi.class);
    private IdWorker idWorker = new IdWorker(10);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    private ICarOrderRetailService carOrderRetailService;
    @Autowired
    private ICarManagerUserService managerUserService;

    /**
     * 新增零售成交订单
     */
    @ApiOperation(value = "新增零售成交订单")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarOrderRetail carOrderRetail = JSONObject.toJavaObject(obj, CarOrderRetail.class);
            if (carOrderRetail == null) {
                carOrderRetail = new CarOrderRetail();
            }
            carOrderRetail.setOrderNo(sdf.format(new Date())+idWorker.nextId());
            carOrderRetail.setId(idWorker.nextId());
            carOrderRetailService.insertSelective(carOrderRetail);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("新增保存车辆评估", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
