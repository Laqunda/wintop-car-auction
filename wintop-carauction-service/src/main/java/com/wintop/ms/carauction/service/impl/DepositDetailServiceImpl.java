package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.DepositDetail;
import com.wintop.ms.carauction.model.DepositDetailModel;
import com.wintop.ms.carauction.service.IDepositDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * class_name: DepositDetailServiceImpl
 * package: com.wintop.ms.carauction.service.impl
 * describe: 查询保证金明细列表
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/18:18
 **/
@Service("depositDetailService")
public class DepositDetailServiceImpl implements IDepositDetailService{
    @Resource
    private DepositDetailModel depositDetailModel;
    private static final Logger logger = LoggerFactory.getLogger(DepositDetailServiceImpl.class);

    /**
     * 查询保证金明细记录列表
     */
    @Override
    public List<DepositDetail> queryDepositDetailList(Map<String, Object> map) {
        List<DepositDetail> list=depositDetailModel.queryDepositDetailList(map);
        return list;
    }
    /**
     * 查询保证金明细记录数量
     */
    @Override
    public Integer selectDepositDetailCount(Map<String, Object> map) {
        return depositDetailModel.selectDepositDetailCount(map);
    }
}
