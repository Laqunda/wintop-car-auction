package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarMessage;
import com.wintop.ms.carauction.mapper.read.ICarMessageReadDao;
import com.wintop.ms.carauction.mapper.write.ICarMessageWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/6.
 * 系统消息
 */
@Repository
public class CarMessageModel {
    @Autowired
    private ICarMessageReadDao readDao;
    @Autowired
    private ICarMessageWriteDao writeDao;

    public CarMessage findById(Long id){
        return readDao.findById(id);
    }

    public List<CarMessage> findByUserId(Map map){
        return readDao.findByUserId(map);
    }

    public int findByUserIdPageCount(Map map){
        return readDao.findByUserIdPageCount(map);
    }

    public void saveMsg(CarMessage carMessage){
        writeDao.save(carMessage);
    }
    public void updateMsg(CarMessage carMessage){
        writeDao.update(carMessage);
    }
}
