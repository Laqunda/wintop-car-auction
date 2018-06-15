package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarReservationSeeCar;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarReservationSeeCarReadDao {
    /**
     * 根据主键查询记录
     */
    CarReservationSeeCar selectByPrimaryKey(Long id);

    /**
     * 查看是否预约过
     *@Author:zhangzijuan
     *@date 2018/4/7
     *@param:
     */
    CarReservationSeeCar selectByPhoneAndReservationId(Map<String,Object> map);

}