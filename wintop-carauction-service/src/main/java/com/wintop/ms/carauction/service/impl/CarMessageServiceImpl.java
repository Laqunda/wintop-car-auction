package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarMessage;
import com.wintop.ms.carauction.model.CarMessageModel;
import com.wintop.ms.carauction.service.ICarMessageService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/6.
 * 系统消息接口
 */
@Service
public class CarMessageServiceImpl implements ICarMessageService {

    private IdWorker idWorker = new IdWorker(10);


    @Autowired
    private CarMessageModel carMessageModel;

    @Override
    public CarMessage findById(Long id) {
        return carMessageModel.findById(id);
    }

    @Override
    public CarMessage findByIdForApp(Long id) {
        CarMessage carMessage = new CarMessage();
        carMessage.setId(id);
        carMessage.setIsRead("2");
        carMessage.setReadTime(new Timestamp(System.currentTimeMillis()));
        this.updateMsg(carMessage);
        return carMessageModel.findById(id);
    }

    @Override
    public List<CarMessage> findByUserIdAll(Long userId,String isRead) {
        Map map = new HashMap();
        map.put("userId",userId);
        map.put("isRead",isRead);
        return carMessageModel.findByUserId(map);
    }

    @Override
    public List<CarMessage> findByUserIdPage(Map map) {
        return carMessageModel.findByUserId(map);
    }

    @Override
    public int findByUserIdPageCount(Map map) {
        return carMessageModel.findByUserIdPageCount(map);
    }

    @Transactional
    public void saveMsg(CarMessage carMessage) {
        carMessage.setId(idWorker.nextId());
        carMessageModel.saveMsg(carMessage);
    }

    @Override
    public void readMsg(CarMessage carMessage) {
        carMessage.setIsRead("2");
        carMessageModel.updateMsg(carMessage);
    }

    @Override
    public void updateMsg(CarMessage message) {
        carMessageModel.updateMsg(message);
    }
}
