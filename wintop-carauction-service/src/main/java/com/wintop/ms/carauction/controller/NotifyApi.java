package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.Notify;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.service.ICarAutoService;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author mazg
 * @Description : 获取卖家端消息通知
 */
@RestController
@RequestMapping("/service/notify")
public class NotifyApi {

    @Autowired
    private RedisManagerTemplate redisManagerTemplate;

    /**
     * 消息通知
     * @param obj
     * @return
     */
    @RequestMapping(value = "/getInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Notify> getInfo(@RequestBody JSONObject obj){
        ServiceResult<Notify> result = new ServiceResult<>();
        try {
            String managerId = null;
            if (Objects.nonNull(obj.getString("managerId"))) {
                managerId = obj.getString("managerId");
            }
            String json = redisManagerTemplate.get(managerId);
            Notify notify = JSONObject.toJavaObject(JSONObject.parseObject(json), Notify.class);
            if (Objects.isNull(notify)) {
                notify = new Notify();
            }
            result.setResult(notify);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
        }
        return result;
    }
}
