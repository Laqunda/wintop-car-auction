package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CustomerBoard;
import com.wintop.ms.carauction.mapper.read.CustomerBoardReadDao;
import com.wintop.ms.carauction.mapper.write.CustomerBoardWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CustomerBoardModel {
    @Autowired
    private CustomerBoardReadDao customerBoardReadDao;
    @Autowired
    private CustomerBoardWriteDao customerBoardWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return customerBoardReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CustomerBoard> selectByExample(Map<String,Object> map){
        return customerBoardReadDao.selectByExample(map);
    }

    /**
     * 根据当前拍牌查询是否有绑定
     */
    public List<CustomerBoard> selectByBoardRealId(String boardRealId){
        return customerBoardReadDao.selectByBoardRealId(boardRealId);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CustomerBoard customerBoard){
        return customerBoardWriteDao.insert(customerBoard);
    }

    /**
     * 更新绑定关系
     */
    public int updateBindStatus(CustomerBoard customerBoard){
        return customerBoardWriteDao.updateBindStatus(customerBoard);
    }

    /**
     * 解除绑定关系
     */
    public int deleteCustomerBoard(CustomerBoard customerBoard){
        return customerBoardWriteDao.deleteCustomerBoard(customerBoard);
    }
}
