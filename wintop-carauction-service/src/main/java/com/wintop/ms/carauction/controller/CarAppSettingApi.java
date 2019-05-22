package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppInfo;
import com.wintop.ms.carauction.service.ICarAppSettingService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author mazg
 * @Description:app配置的相关的信息
 * @date 2018-03-06
 */
@RestController
@RequestMapping(value = "/service/appSetting")
public class CarAppSettingApi {

    private static final Logger Logger = LoggerFactory.getLogger(CarAppInfoApi.class);

    @Autowired
    private ICarAppSettingService carAppSettingService;

    /**
     * 根据code获取配置
     * @param obj
     * @return
     */
    @PostMapping(value = "getSettingsByCode",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<Map<String,Object>> getSettingsByCode(@RequestBody JSONObject obj) {
        return carAppSettingService.getAcutionHint(Collections.singletonMap("code",obj.getString("code")));
    }

    /**
     * 根据code获取配置
     * @param obj
     * @return
     */
    @PostMapping(value = "getAppSetting",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<Map<String,Object>> getAppSetting(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<Map<String, Object>>();
        Map<String, Object> map = carAppSettingService.getAppSetting();
        if (MapUtils.isNotEmpty(map)) {
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } else {
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
        }
        return result;
    }

    /**
     * 根据code获取配置
     * @param obj
     * @return
     */
    @PostMapping(value = "update",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<Map<String,Object>> update(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<Map<String, Object>>();
        Map<String,Object> params = JSONObject.toJavaObject(obj, Map.class);
        int code = carAppSettingService.updateSelective(params);
        if (code > 0) {
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } else {
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
        }
        return result;
    }
}
