package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarCustomerDepositLog;
import com.wintop.ms.carauction.entity.DepositFreeze;
import com.wintop.ms.carauction.model.CarCustomerDepositLogModel;
import com.wintop.ms.carauction.service.ICarCustomerDepositLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * class_name: CarCustomerDepositLogServiceImpl
 * package: com.wintop.ms.carauction.service.impl
 * describe: 保证金记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/27/20:01
 **/
@Service("carCustomerDepositLogService")
public class CarCustomerDepositLogServiceImpl implements ICarCustomerDepositLogService{
    @Resource
    private CarCustomerDepositLogModel carCustomerDepositLogModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerDepositLogServiceImpl.class);
    /**
     * 根据主键查询记录
     */
    @Override
    public CarCustomerDepositLog selectByPrimaryKey(Long id) {
        return carCustomerDepositLogModel.selectByPrimaryKey(id);
    }
    /**
     * 查询保证金冻结记录列表
     */
    @Override
    public List<DepositFreeze> queryDepositFreezeList(Map<String, Object> map) {
        List<DepositFreeze> list=carCustomerDepositLogModel.queryDepositFreezeList(map);
        return list;
    }
    /**
     * 查询保证金冻结记录的数量
     */
    @Override
    public Integer selectDepositFreezeCount(Map<String, Object> map) {
        return carCustomerDepositLogModel.selectDepositFreezeCount(map);
    }
}
