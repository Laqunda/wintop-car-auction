package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CustomerBoard;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.entity.TblAuctionBoard;
import com.wintop.ms.carauction.entity.TblBaseStation;
import com.wintop.ms.carauction.service.CustomerBoardService;
import com.wintop.ms.carauction.service.TblAuctionBoardService;
import com.wintop.ms.carauction.service.TblBaseStationService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/customerBoard")
public class CustomerBoardApi {
    @Autowired
    private TblAuctionBoardService boardService;
    @Autowired
    private CustomerBoardService customerBoardService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerBoardApi.class);

    /**
     * 绑定拍牌
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveCustomerBoard",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CustomerBoard> saveCustomerBoard(@RequestBody JSONObject obj) {
        ServiceResult<CustomerBoard> result = new ServiceResult<>();
        try {
            CustomerBoard customerBoard = JSONObject.toJavaObject(obj,CustomerBoard.class);
            TblAuctionBoard board = boardService.selectByRealId(customerBoard.getBoardRealId());
            if(board==null){
                result.setResult(customerBoard);
                result.setSuccess(ResultCode.NO_OBJECT.strValue(),"拍牌不存在");
                return result;
            }else{
                customerBoardService.insert(customerBoard);
                result.setResult(customerBoard);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                return result;
            }
        }catch (Exception e){
            logger.info("绑定拍牌失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /**
     * 解绑拍牌
     * @param obj
     * @return
     */
    @RequestMapping(value = "/deleteCustomerBoard",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CustomerBoard> deleteCustomerBoard(@RequestBody JSONObject obj) {
        ServiceResult<CustomerBoard> result = new ServiceResult<>();
        try {
            CustomerBoard customerBoard = JSONObject.toJavaObject(obj,CustomerBoard.class);
            customerBoardService.deleteCustomerBoard(customerBoard);
            result.setResult(customerBoard);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            return result;
        }catch (Exception e){
            logger.info("解绑拍牌失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }
}
