package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;
import com.wintop.ms.carauction.entity.CarStore;
import com.wintop.ms.carauction.service.ICarStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/9.
 */
@RestController
@RequestMapping("/service/serviceNetwork")
public class ServiceNetworkApi {
    private static final Logger logger = LoggerFactory.getLogger(ServiceNetworkApi.class);
    @Autowired
    private ICarStoreService storeService;
    /**
     * 店铺详情接口
     */
    @RequestMapping(value = "/selectServiceNetwork",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectServiceNetwork(@RequestBody JSONObject obj){
        logger.info("店铺详情");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long serviceNetworkId = obj.getLong("serviceNetworkId");
            CarStore vo = storeService.selectByPrimaryKey(serviceNetworkId);
            map.put("name",vo.getName());
            map.put("storePhoto",vo.getStorePhoto());
            map.put("address",vo.getAddress());
            map.put("serviceTel",vo.getServiceTel());
            map.put("latitude",vo.getLatitude());
            map.put("longitude",vo.getLongitude());
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("查询店铺详情失败",e);
        }
        return result;
    }

}
