package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CustomerBoard;
import com.wintop.ms.carauction.model.CustomerBoardModel;
import com.wintop.ms.carauction.service.CustomerBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class CustomerBoardServiceImpl implements CustomerBoardService {
    @Autowired
    private CustomerBoardModel customerBoardModel;

    @Override
    public int countByExample(Map<String, Object> map) {
        return customerBoardModel.countByExample(map);
    }

    @Override
    public List<CustomerBoard> selectByExample(Map<String, Object> map) {
        return customerBoardModel.selectByExample(map);
    }

    @Override
    public List<CustomerBoard> selectByBoardRealId(String boardRealId) {
        return customerBoardModel.selectByBoardRealId(boardRealId);
    }

    @Override
    @Transactional
    public int insert(CustomerBoard customerBoard) {
        //绑定拍牌前先解绑之前的用户
        CustomerBoard board = new CustomerBoard();
        board.setBoardRealId(customerBoard.getBoardRealId());
        board.setBindStatus("2");
        board.setModifyPerson(customerBoard.getCreatePerson());
        board.setModifyTime(customerBoard.getCreateTime());
        customerBoardModel.updateBindStatus(board);
        return customerBoardModel.insert(customerBoard);
    }

    @Override
    public int updateBindStatus(CustomerBoard customerBoard) {
        return customerBoardModel.updateBindStatus(customerBoard);
    }

    /**
     * 解除绑定关系
     */
    @Override
    public int deleteCustomerBoard(CustomerBoard customerBoard){
        return customerBoardModel.deleteCustomerBoard(customerBoard);
    }
}