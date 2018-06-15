package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppInfo;
import com.wintop.ms.carauction.service.ICarAppInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:app相关的信息
 * @date 2018-03-06
 */
@RestController
@RequestMapping(value = "/service/appInfo")
public class CarAppInfoApi {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarAppInfoApi.class);

    @Autowired
    private ICarAppInfoService appInfoService;

    /**
     * 根据appId获取版本号
     * @param appId
     * @return
     */
    @PostMapping(value = "getVersionByAppId",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<CarAppInfo> selectVersionByAppId(@RequestBody String appId) {
        Logger.info("根据appId获取版本号");
        return this.appInfoService.selectVersionByAppId(appId);
    }

    /**
     * 修改APP关于我们
     */
    @RequestMapping(value = "/updateAboutUs",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateAboutUs(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarAppInfo appInfo = JSONObject.toJavaObject(obj,CarAppInfo.class);
            int count = appInfoService.updateByIdSelective(appInfo);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("修改APP关于我们",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查看APP关于我们
     */
    @RequestMapping(value = "/selectAboutUs",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectAboutUs(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarAppInfo appInfo = appInfoService.selectById(obj.getLong("id"));
            map.put("about",appInfo.getAbout());
            map.put("tel",appInfo.getTel());
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("修改APP关于我们",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
