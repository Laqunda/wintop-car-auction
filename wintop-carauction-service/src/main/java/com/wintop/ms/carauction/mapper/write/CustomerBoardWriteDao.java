package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CustomerBoard;

public interface CustomerBoardWriteDao {

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CustomerBoard customerBoard);

    /**
     * 更新绑定关系
     */
    int updateBindStatus(CustomerBoard customerBoard);

    /**
     * 解除绑定关系
     */
    int deleteCustomerBoard(CustomerBoard customerBoard);
}