package com.wintop.ms.carauction.service;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.entity.CarReservationSeeCar;

import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:预约看车
 * @date 2018-04-07
 */
public interface ICarReservationSeeCarService {
//    保存预约信息
   Integer save (JSONObject object);
}
