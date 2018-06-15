package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarFinancePayLog;
import com.wintop.ms.carauction.mapper.read.ICarFinancePayLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarFinancePayLogWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Repository
public class CarFinancePayLogModel {
    @Autowired
    private ICarFinancePayLogReadDao carFinancePayLogReadDao;
    @Autowired
    private ICarFinancePayLogWriteDao carFinancePayLogWriteDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carFinancePayLogReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarFinancePayLog> selectByExample(Map<String,Object> map){
        return carFinancePayLogReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarFinancePayLog selectById(Long id){
        return carFinancePayLogReadDao.selectById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarFinancePayLog carFinancePayLog){
        return carFinancePayLogWriteDao.insert(carFinancePayLog);
    }

}
