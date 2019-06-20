package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;
import com.wintop.ms.carauction.entity.CarOrder;
import com.wintop.ms.carauction.service.IFollowBidScanService;
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
 * Created by 12991 on 2018/3/8.
 */
@RestController
@RequestMapping("/service/followBidScan")
public class FollowBidScanApi {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarCustomerFollowAutoApi.class);
    @Autowired
    private IFollowBidScanService iFollowBidScanService;
    /**
     *获取客户关注/浏览/出价的车辆数量
     */
    @RequestMapping(value = "/selectFollowBidScan",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectFollowBidScan(@RequestBody JSONObject obj) {
             Logger.info("获取客户关注/浏览/出价/待确认的车辆数量");
             ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
             Long customerId = obj.getLong("customerId");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("customerId",customerId);
            map.put("clientType","app");

            //关注的车辆数量
           Integer attentionNum = iFollowBidScanService.selectFollowCount(map);
           //出价的车辆数量
           Integer bidNum = iFollowBidScanService.selectBidCount(map);
           //浏览的车辆数量
            Integer browseNum = iFollowBidScanService.selectBrowseCount(map);
            //待确认的车辆数量 status = 9 待议价的车辆
            map.put("status","9");
            Integer waitNum = iFollowBidScanService.selectBidCount(map);
            //订单数据
           List<CarOrder> orders = iFollowBidScanService.selectOrderCount(map);
           List<Map<String,Object>> list = new ArrayList<>();
           for(CarOrder carOrder : orders){
               Map<String,Object> map3 = new HashMap<>();
               // map3.put("status",carOrder.getStatus());
               // map3.put("orderNum",carOrder.getOrderNum());
                map3.put("totalDeal",carOrder.getOrderNum());
                map3.put("unPay",carOrder.getUnPay());
                map3.put("dealing",carOrder.getDealing());
                map3.put("finish",carOrder.getFinish());
                map3.put("unEvaluated",carOrder.getUnEvaluated());
                list.add(map3);
           }
           Map<String,Object> map2 = new HashMap<>();
           map2.put("attentionNum",attentionNum);
           map2.put("bidNum",bidNum);
           map2.put("browseNum",browseNum);
           map2.put("waitNum",waitNum);
           if(list != null && list.size() > 0){
               map2.put("order",list);
           }else{
               Map<String,Object> map4 = new HashMap<>();
              // map4.put("status",0);
              // map4.put("orderNum",0);
               map4.put("totalDeal",0);
               map4.put("unPay",0);
               map4.put("dealing",0);
               map4.put("finish",0);
               map4.put("unEvaluated",0);
               list.add(map4);
               map2.put("order",list);
           }
            result.setResult(map2);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("获取客户关注/浏览/出价的车辆数量失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

}
