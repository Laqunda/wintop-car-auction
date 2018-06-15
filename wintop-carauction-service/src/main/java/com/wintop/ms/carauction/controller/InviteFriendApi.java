package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.AppUserModel;
import com.wintop.ms.carauction.service.ICarAutoService;
import com.wintop.ms.carauction.service.ICarCustomerFollowAutoService;
import com.wintop.ms.carauction.service.ICarOrderService;
import com.wintop.ms.carauction.service.InviteFriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/10.
 */
@RestController
@RequestMapping("/service/inviteFriend")
public class InviteFriendApi {
    @Autowired
    private AppUserModel appUserModel;
    @Autowired
    private ICarCustomerFollowAutoService followAutoService;
    @Autowired
    private ICarOrderService carOrderService;
    @Autowired
    private ICarAutoService carAutoService;
    private static final Logger logger = LoggerFactory.getLogger(InviteFriendApi.class);
    /**
     * 邀请好友接口
     */
    @RequestMapping(value = "/selectInviteFriend",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectInviteFriend(@RequestBody JSONObject obj){
        logger.info("邀请好友接口");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long userId = obj.getLong("userId");
            if(userId != null && userId != 0){
                map.put("shareUrl", Constants.STATIC_WEBSITE+"/ht/share/friends.html?userId="+userId);
            }else{
                map.put("shareUrl", Constants.STATIC_WEBSITE+"/ht/share/friends.html");
            }
          map.put("shareTitle","柠檬竞价-让二手车买卖更轻松");
          map.put("sharePhoto","http://static.yuntongauto.com/web/lay-ui/images/share/logo.png");
          map.put("subtitle","精选车源、现场竞拍场次、车辆抢鲜看");
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("查询邀请好友失败",e);
        }
        return result;
    }

    /**
     * 获取邀请好友内容
     * @param object
     * @return
     */
    @PostMapping(value = "selectShareContent",produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectShareContent(@RequestBody JSONObject object){
        logger.info("获取邀请好友内容");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("startRowNum",0);
        paraMap.put("endRowNum",5);
        List<CarAuto> carAutos=carAutoService.selectOnlineCarList(paraMap);
        try {
            if (object.get("userId")!=null){
                WtAppUser appUser=appUserModel.findById(object.getLong("userId"));
                if (appUser!=null){
                    map.put("userName",appUser.getName());
                    //查询用户是否有订单
                    paraMap.put("customerId",object.getLong("userId"));
                    List<CarOrder> carOrders=carOrderService.selectUserOrderList(paraMap);
                    if (carOrders!=null&&carOrders.size()>0){
                        map.put("code","好友已购买");
                        map.put("list",carOrders);
                    }else {
                        List<CarCustomerFollowAuto> followAutos=followAutoService.queryUserFollowList(paraMap);
                        if (followAutos!=null && followAutos.size()>0){
                            map.put("code","好友已收藏");
                            map.put("list",followAutos);
                        }else {
                            map.put("code","今日上新");
                            map.put("list",carAutos);
                        }
                    }
                }else{
                    map.put("userName","");
                    map.put("code","今日上新");
                    map.put("list",carAutos);
                }
            }else{
                map.put("userName","");
                map.put("code","今日上新");
                map.put("list",carAutos);
            }
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
            logger.info("查询邀请好友内容失败",e);
        }
        return result;
    }
}
