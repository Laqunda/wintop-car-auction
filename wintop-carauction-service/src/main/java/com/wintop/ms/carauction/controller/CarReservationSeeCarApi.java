package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.mapper.read.ICarReservationSeeCarReadDao;
import com.wintop.ms.carauction.service.ICarReservationSeeCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-04-07
 */
@RestController
@RequestMapping("/service/reservationSeeCar")
public class CarReservationSeeCarApi {
    @Resource
    private ICarReservationSeeCarService seeCarService;
    @PostMapping(value = "/saveReservationSeeCar", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveReservationSeeCar(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=seeCarService.save(object);
            if (i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else if(i==-1){
                result.setError(ResultCode.REPEAT_RESERVATION.strValue(),ResultCode.REPEAT_RESERVATION.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

}
