package com.wintop.ms.carauction.entity;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;

public class CarAuctionRedis {

    public static String getAuctionKey(Long regionId){
        return Constants.AUCTION_SETTING+"_"+regionId;
    }

    public static String getAuctionSettingJson(CarAuctionSetting carAuctionSetting){
        return JSONObject.toJSONString(carAuctionSetting);
    }

    public static String getRedisAutoDataKey(Long carId){
        return Constants.CAR_AUTO_AUCTION+"_"+carId;
    }

    public static CarAuctionSetting getAuctionSetting(String json){
        try{
            CarAuctionSetting carAuctionSetting = JSONObject.parseObject(json,CarAuctionSetting.class);
            return carAuctionSetting;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
