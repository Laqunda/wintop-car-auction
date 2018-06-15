package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.CarStore;
import com.wintop.ms.carauction.service.ICarCenterService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.ICarMessageService;
import com.wintop.ms.carauction.service.ICarStoreService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/19.
 */
@RestController
@RequestMapping("/service/carStoreLogoMessageCount")
public class CarStoreLogoMessageCountApi {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarStoreLogoMessageCountApi.class);
    @Autowired
    private ICarStoreService iCarStoreService;
    @Autowired
    private ICarMessageService iCarMessageService;
    @Autowired
    private ICarManagerUserService iCarManagerUserService;
    @Autowired
    private ICarCenterService centerService;

    /**
     *获取经销商logo及消息总数
     */
    @RequestMapping(value = "/selectCarStoreLogoMessageCount",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectCarStoreLogoMessageCount(@RequestBody JSONObject obj) {
        Logger.info("获取经销商logo及消息总数");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Long userId = obj.getLong("userId");
        try {
            Map<String,Object> map2 = new HashMap<>();
            map2.put("userId",userId);
            CarManagerUser carManagerUser = iCarManagerUserService.selectByPrimaryKey(userId,true);
            if(carManagerUser != null){
                String storePhoto=null,simpleName=null;
                if (2L==(carManagerUser.getRoleTypeId())){
                    CarCenter carCenter = centerService.selectByPrimaryKey(carManagerUser.getDepartmentId());
                    simpleName = carCenter.getCenterName();
                    storePhoto = Constants.STATIC_LEMON_LOGO;
                }else if (3L == (carManagerUser.getRoleTypeId())){
                    CarStore carStore = iCarStoreService.selectByPrimaryKey(carManagerUser.getDepartmentId());
                    storePhoto = carStore.getStorePhoto();
                    simpleName = carStore.getSimpleName();
                }
                Integer count = iCarMessageService.findByUserIdPageCount(map2);
                Map<String,Object> map = new HashMap<>();
                map.put("storePhoto",storePhoto);
                map.put("simpleName",simpleName);
                map.put("messageNum",count);
                result.setResult(map);
                result.setSuccess("0","成功");
            }else {
                result.setSuccess("-1","失败");
            }


        }catch (Exception e){
            e.printStackTrace();
            Logger.info("获取经销商logo及消息总数失败",e);
            result.setError("-1","异常");
        }
        return result;
    }
}
