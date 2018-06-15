package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.AppVersion;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.service.impl.CarAppVersionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * 客户端版本信息
 */
@RestController()
@RequestMapping(value = "appVersion")
public class CarAppVersionApi {

    @Autowired
    private CarAppVersionService appVersionService;

    @ApiOperation("根据客户端类型，获取最新版本号")
    @PostMapping(value = "getNewByAppType")
    public ServiceResult<AppVersion> getNewByAppType(@RequestBody JSONObject object){
        ServiceResult<AppVersion> result = new ServiceResult<>();
        try {
            AppVersion appVersion = appVersionService.getNewByAppType(object.getString("appType"));
//            if (appVersion!=null){
                result.setSuccess("0","成功");
//            }else {
//                result.setError("-1","失败");
//            }
            result.setResult(appVersion);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","失败");
        }finally {
            return result;
        }
    }

}
