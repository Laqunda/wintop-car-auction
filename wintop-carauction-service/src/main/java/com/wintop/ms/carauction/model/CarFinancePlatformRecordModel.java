package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarFinancePlatformRecord;
import com.wintop.ms.carauction.mapper.read.ICarFinancePlatformRecordReadDao;
import com.wintop.ms.carauction.mapper.write.ICarFinancePlatformRecordWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Repository
public class CarFinancePlatformRecordModel {
    @Autowired
    private ICarFinancePlatformRecordReadDao carFinancePlatformRecordReadDao;
    @Autowired
    private ICarFinancePlatformRecordWriteDao carFinancePlatformRecordWriteDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carFinancePlatformRecordReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarFinancePlatformRecord> selectByExample(Map<String,Object> map){
        return carFinancePlatformRecordReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarFinancePlatformRecord selectById(Long id){
        return carFinancePlatformRecordReadDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return carFinancePlatformRecordWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarFinancePlatformRecord carFinancePlatformRecord){
        return carFinancePlatformRecordWriteDao.insert(carFinancePlatformRecord);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarFinancePlatformRecord carFinancePlatformRecord){
        return carFinancePlatformRecordWriteDao.updateByIdSelective(carFinancePlatformRecord);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarFinancePlatformRecord carFinancePlatformRecord){
        return carFinancePlatformRecordWriteDao.updateById(carFinancePlatformRecord);
    }
}
