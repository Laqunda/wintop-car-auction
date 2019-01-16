package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CustomerBoard;

import java.util.List;
import java.util.Map;

public interface CustomerBoardService {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CustomerBoard> selectByExample(Map<String,Object> map);

    /**
     * 根据当前拍牌查询是否有绑定
     */
    List<CustomerBoard> selectByBoardRealId(String boardRealId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CustomerBoard priceClient);

    /**
     * 更新绑定关系
     */
    int updateBindStatus(CustomerBoard priceClient);

    /**
     * 解除绑定关系
     */
    public int deleteCustomerBoard(CustomerBoard customerBoard);
}
