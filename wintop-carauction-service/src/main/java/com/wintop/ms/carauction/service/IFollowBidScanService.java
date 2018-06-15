package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/8.
 */
public interface IFollowBidScanService {
    /**
     * 浏览的车辆数量
     */
    Integer selectBrowseCount(Map<String, Object> map);
    /**
     * 关注的车辆数量
     */
    Integer selectFollowCount(Map<String,Object> map);
    /**
     * 出价的车辆数量
     */
    Integer selectBidCount(Map<String,Object> map);

    /**
     * 根据条件查询订单数据
     */
    List<CarOrder>  selectOrderCount(Map<String,Object> map);
}
