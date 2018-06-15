package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarOrder;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarOrderWriteDao {

    /**
     * 根据主键删除记录
     */
    int deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarOrder carOrder);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(CarOrder carOrder);

    /**
     * 根据主键更新记录
     */
    int updateById(CarOrder carOrder);

    /**
     * 订单拍牌号补全
     * @param id
     * @param customerId
     * @return
     */
    public int updateOrderUser(@Param("id") Long id,@Param("customerId") Long customerId);
}