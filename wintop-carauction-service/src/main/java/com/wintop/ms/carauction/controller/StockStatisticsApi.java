package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author mazg
 * @Description : 库存管理入口 api
 * @date 2019-05-09
 */
@RestController
@RequestMapping("/service/stockStatisticsApi")
public class StockStatisticsApi {

    @Autowired
    private ICarAutoService carAutoService;

    /**
     * 库存管理统计
     * @param obj
     * @return
     */
    @RequestMapping(value = "/stockInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> insertCarEvaluateData(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            String type = obj.getString("type");
            List<Map<String,Object>> list = carAutoService.selectCarAutoForSaleCount(Collections.singletonMap("type", type));
            result.setResult(Collections.singletonMap("dataList",list));
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
        }
        return result;
    }
}
