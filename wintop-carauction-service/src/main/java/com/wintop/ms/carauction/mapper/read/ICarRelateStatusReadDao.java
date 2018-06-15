package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarRelateStatus;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:二手车相关状态
 * @date 2018-03-26
 */
public interface ICarRelateStatusReadDao {
    /**
     * 根据条件类型查询所有的状态
     */
    List<CarRelateStatus> selectAllStatus(Map<String,Object> map);
}
