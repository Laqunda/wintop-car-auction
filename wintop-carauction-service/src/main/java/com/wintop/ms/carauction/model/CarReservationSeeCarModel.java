package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarReservationSeeCar;
import com.wintop.ms.carauction.mapper.read.ICarReservationSeeCarReadDao;
import com.wintop.ms.carauction.mapper.write.ICarReservationSeeCarWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * 预约看车
 */
@Repository
public class CarReservationSeeCarModel {
    @Resource
    private ICarReservationSeeCarWriteDao writeDao;
    @Resource
    private ICarReservationSeeCarReadDao readDao;

    public CarReservationSeeCar findById(Long id){
        return readDao.selectByPrimaryKey(id);
    }
    /**
     * 查看是否预约过
     *@Author:zhangzijuan
     *@date 2018/4/7
     *@param:
     */
    public CarReservationSeeCar selectByPhoneAndReservationId(Map<String,Object> map){
        return readDao.selectByPhoneAndReservationId(map);
    }

    public Integer insert(CarReservationSeeCar record){
        return writeDao.insert(record);
    }

}
