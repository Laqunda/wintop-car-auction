package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerDepositLog;
import com.wintop.ms.carauction.entity.DepositFreeze;
import com.wintop.ms.carauction.mapper.read.CarCustomerDepositLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerDepositLogWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * class_name: CarCustomerDepositLogModel
 * package: com.wintop.ms.carauction.model
 * describe: 保证金记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/27/19:55
 **/
@Repository
public class CarCustomerDepositLogModel {
    @Resource
    private CarCustomerDepositLogReadDao readDao;
    @Resource
    private ICarCustomerDepositLogWriteDao writeDao;

    /**
     * 根据主键查询记录
     */
    public CarCustomerDepositLog selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }
    /**
     * 查询保证金冻结记录列表
     */
    public List<DepositFreeze> queryDepositFreezeList(Map<String,Object> map){
        List<DepositFreeze> list=readDao.queryDepositFreezeList(map);
        return list;
    }
    /**
     * 查询保证金冻结记录的数量
     */
    public Integer selectDepositFreezeCount(Map<String,Object> map){
        return readDao.selectDepositFreezeCount(map);
    }

    public Integer insert(CarCustomerDepositLog record){
        return writeDao.insert(record);
    }
}
