package com.wintop.ms.carauction.controller;

/**
 * @author zhangzijuan
 * @Description:车辆日志
 * @date 2018-03-23
 */

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.service.ICarAutoLogService;
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

/***
 * 车辆基本信息
 */
@RequestMapping("/service/autoLog")
@RestController
public class CarAutoLogApi {
    @Autowired
    private ICarAutoLogService autoLogService;
    Logger logger = LoggerFactory.getLogger(CarAutoLogApi.class);
    /**
     * 查询车辆轨迹
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:
     */
    @ApiOperation(value = "查询车辆轨迹")
    @ApiImplicitParam(dataType = "Long", name = "carId", value = "车辆Id", required = true)
    @PostMapping(value = "selectCarLogByCarId")
    public ServiceResult<List<CarAutoLog>> selectCarLogByCarId(@RequestBody Map<String,Object> map){
        ServiceResult<List<CarAutoLog>> result=new ServiceResult<>();
        try {
            List<CarAutoLog> logs=autoLogService.selectCarLogByCarId(map);
            if(logs!=null && logs.size()!=0){
                result.setResult(logs);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
