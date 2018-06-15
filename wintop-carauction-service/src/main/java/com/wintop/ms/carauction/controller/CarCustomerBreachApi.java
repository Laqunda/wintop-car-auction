package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.BreachStatusEnum;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.BreachStatus;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerBreach;
import com.wintop.ms.carauction.service.ICarCustomerBreachService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:违约信息
 * @date 2018-03-27
 */
@RestController
@RequestMapping(value = "/service/customerBreach")
public class CarCustomerBreachApi {
    @Resource
    private ICarCustomerBreachService breachService;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerAuthApi.class);
/**
 * 申请争议
 *@Author:zhangzijuan
 *@date 2018/3/27
 *@param:
 */
@PostMapping(value = "applyBreach",produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> applyBreach(@RequestBody JSONObject object){
    ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        logger.info("申请争议");
        try {
            Integer i=breachService.applyBreach(object);
            if(i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询争议类型
     * @param object
     * @return
     */
    @ApiOperation(value = "查询争议类型")
    @ApiImplicitParam(name = "type",value = "类型",required = true,paramType = "query",dataType = "string")
    @PostMapping(value = "getBreachStatusList",produces="application/json; charset=UTF-8")
    public ServiceResult<List<BreachStatus>> getBreachStatusList(@RequestBody JSONObject object){
        ServiceResult<List<BreachStatus>> result=new ServiceResult<>();
        result.setResult(BreachStatusEnum.getBreachStatusList(object.getString("type")));
        result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
    return result;
    }

    /**
     * 争议审核
     *@Author:admin
     *@date 2018/3/27
     *@param:
     */
    @PostMapping(value = "breachApprove",produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> breachApprove(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            int count = breachService.breachApprove(object);
            if(count==-1){
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
                return result;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("争议审核失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
