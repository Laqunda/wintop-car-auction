package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.entity.CarReservationSeeCar;
import com.wintop.ms.carauction.model.CarReservationSeeCarModel;
import com.wintop.ms.carauction.service.ICarReservationSeeCarService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-04-07
 */
@Service
public class ICarReservationSeeCarServiceImpl implements ICarReservationSeeCarService {
    Logger logger = LoggerFactory.getLogger(ICarReservationSeeCarServiceImpl.class);
    IdWorker idWorker=new IdWorker(10);
    @Resource
    private CarReservationSeeCarModel reservationSeeCarModel;

    @Override
    public Integer save(JSONObject object) {
        Integer result=0;
        Map<String,Object> map=new HashMap<>();
        map.put("contactPhone",object.getString("contactPhone"));
        map.put("reservationId",object.getLong("reservationId"));
        CarReservationSeeCar seeCar=reservationSeeCarModel.selectByPhoneAndReservationId(map);
        if (seeCar==null){
            CarReservationSeeCar reservationSeeCar=new CarReservationSeeCar();
            reservationSeeCar.setId(idWorker.nextId());
            reservationSeeCar.setCreateTime(new Date());
            reservationSeeCar.setReservationId(object.getLong("reservationId"));
            reservationSeeCar.setContactPhone(object.getString("contactPhone"));
            reservationSeeCar.setType(object.getString("type"));
            result=reservationSeeCarModel.insert(reservationSeeCar);
        }else {
            result=-1;
        }
        return result;
    }
}
