package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarFinanceStoreRecord;
import com.wintop.ms.carauction.mapper.read.ICarFinanceStoreRecordReadDao;
import com.wintop.ms.carauction.mapper.write.ICarFinanceStoreRecordWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Repository
public class CarFinanceStoreRecordModel {
    @Autowired
    private ICarFinanceStoreRecordReadDao carFinanceStoreRecordReadDao;
    @Autowired
    private ICarFinanceStoreRecordWriteDao carFinanceStoreRecordWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carFinanceStoreRecordReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarFinanceStoreRecord> selectByExample(Map<String,Object> map){
        return carFinanceStoreRecordReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarFinanceStoreRecord selectById(Long id){
        return carFinanceStoreRecordReadDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return carFinanceStoreRecordWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarFinanceStoreRecord carFinanceStoreRecord){
        return carFinanceStoreRecordWriteDao.insert(carFinanceStoreRecord);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarFinanceStoreRecord carFinanceStoreRecord){
        return carFinanceStoreRecordWriteDao.updateByIdSelective(carFinanceStoreRecord);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarFinanceStoreRecord carFinanceStoreRecord){
        return carFinanceStoreRecordWriteDao.updateById(carFinanceStoreRecord);
    }
}
