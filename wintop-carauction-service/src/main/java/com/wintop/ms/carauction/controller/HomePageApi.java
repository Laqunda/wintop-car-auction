package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppSetting;
import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.service.ICarAppSettingService;
import com.wintop.ms.carauction.service.ICarAutoService;
import com.wintop.ms.carauction.service.ICarLocaleAuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 12991 on 2018/4/4.
 */
@RestController
@RequestMapping("/service/homePage")
public class HomePageApi {
    private static final Logger logger = LoggerFactory.getLogger(HomePageApi.class);
    @Autowired
    private ICarLocaleAuctionService iCarLocaleAuctionService;
    @Autowired
    private ICarAutoService iCarAutoService;
    //    本月场次数量
   // private int auctionCount = 20;
    //开拍时间
  //  private String startTime = "2018-03-07 23:59:59";
    //城市数量
  //  private int cityCount = 5;
    //今日上新数量
  //  private int newCarSum = 200;
    //成交率
    private String successRate = "85%";
    //置换比
    private String replaceRate = "95%";
    @Autowired
    private ICarAppSettingService carAppSettingService;
    /**
     * 买家端首页信息接口
     */
    @RequestMapping(value = "/selectHomePage",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectHomePage(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Map<String,Object> param = new HashMap<>();
            Map<String,Object> map = new HashMap<>();
            param.put("curDateType","month");
           // param.put("cityId",obj.getLong("cityId"));
            if (obj.getString("cityIds") != null) {
                param.put("cityIds", Splitter.on(",").splitToList(obj.getString("cityIds")).stream().map(a -> Longs.tryParse(a)).collect(Collectors.toList()));
            }
            //竞拍场次site_val  竞拍城市city_val
            List<String> listVal = Arrays.asList("site_val", "city_val");
            for(String code:listVal){
                CarAppSetting appSet = carAppSettingService.getAcutionHint(Collections.singletonMap("code", code)).getResult();
                map.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, code),appSet.getContent());
            }
            //竞拍场次
            Integer auctionCount =  iCarLocaleAuctionService.queryAuctionCount(param).getResult();
            //新上车辆
            Integer  newCarSum = iCarAutoService.selectDayCarCount(map);
            List<CarLocaleAuction> list = iCarLocaleAuctionService.queryCarLocaleAuctionList(param).getResult();
            Date startTime = null;
            if(list != null && list.size() > 0){
                startTime = list.get(0).getStartTime();
            }
            //开拍城市
           Integer cityCount = iCarLocaleAuctionService.selectCityCount(param).getResult();
            if(newCarSum != null && newCarSum != 0){
                map.put("newCarSum",newCarSum);
            }else{
                map.put("newCarSum",0);
            }
            if(cityCount != null && cityCount != 0){
                map.put("cityCount",cityCount);
            }else{
                map.put("cityCount",0);
            }
            if(auctionCount != null && auctionCount != 0){
                map.put("auctionCount",auctionCount);
            }else{
                map.put("auctionCount",0);
            }
            map.put("startTime",startTime);
            map.put("successRate",successRate);
            map.put("replaceRate",replaceRate);
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询首页信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }
}
