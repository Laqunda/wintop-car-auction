package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.DepositDetail;
import com.wintop.ms.carauction.mapper.read.IDepositDetailReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * class_name: DepositDetailModel
 * package: com.wintop.ms.carauction.model
 * describe: 查询保证金明细
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/17:59
 **/
@Repository
public class DepositDetailModel {
    @Autowired
    private IDepositDetailReadDao readDao;


    /**
     * 查询保证金缴纳明细记录列表
     */
    public List<DepositDetail> queryDepositDetailList(Map<String,Object> map){
        List<DepositDetail> list=readDao.queryDepositDetailList(map);
        return list;
    }
    /**
     * 查询保证金缴纳明细记录的数量
     */
    public Integer selectDepositDetailCount(Map<String,Object> map){
        return readDao.selectDepositDetailCount(map);
    }
}
