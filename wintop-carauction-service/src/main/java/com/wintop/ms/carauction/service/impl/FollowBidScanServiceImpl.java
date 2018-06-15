package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarOrder;
import com.wintop.ms.carauction.model.CarAuctionBidRecordModel;
import com.wintop.ms.carauction.model.CarCustomerFollowAutoModel;
import com.wintop.ms.carauction.model.CarCustomerViewedAutoModel;
import com.wintop.ms.carauction.model.CarOrderModel;
import com.wintop.ms.carauction.service.IFollowBidScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/8.
 */
@Service
public class FollowBidScanServiceImpl implements IFollowBidScanService{
    @Autowired
    private CarCustomerFollowAutoModel carCustomerFollowAutoModel;
    @Autowired
    private CarAuctionBidRecordModel carAuctionBidRecordModel;
    @Autowired
    private CarOrderModel carOrderModel;
    @Autowired
    private CarCustomerViewedAutoModel carCustomerViewedAutoModel;
    /**
     *关注的车辆数量
     */
    @Override
    public Integer selectFollowCount(Map<String, Object> map) {
        Integer count = carCustomerFollowAutoModel.queryUserFollowCount(map);
        return count;
    }

    /**
     *出价的车辆数量
     */
    @Override
    public Integer selectBidCount(Map<String, Object> map) {
        Integer count = carAuctionBidRecordModel.selectBidCount(map);
        return count;
    }

    /**
     * 浏览的车辆数量
     */
    @Override
    public Integer selectBrowseCount(Map<String, Object> map) {
        Integer count = carCustomerViewedAutoModel.queryUserViewCount(map);
        return count;
    }

    /**
     * 根据条件查询订单数据
     */
    @Override
    public List<CarOrder> selectOrderCount(Map<String, Object> map) {
       List<CarOrder>  list = carOrderModel.selectOrderCount(map);
        return list;
    }
}
