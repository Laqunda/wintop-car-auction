package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarEvaluateData;
import com.wintop.ms.carauction.mapper.read.ICarEvaluateDataReadDao;
import com.wintop.ms.carauction.mapper.write.ICarEvaluateDataWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarEvaluateDataModel {

    @Autowired
    private ICarEvaluateDataReadDao readDao;

    @Autowired
    private ICarEvaluateDataWriteDao writeDao;

    public Integer insertSelective(CarEvaluateData carEvaluateData){
        return writeDao.insertSelective(carEvaluateData);
    }

    /**
     * 通过主键查询数据
     * @param id
     * @return
     */
    public CarEvaluateData selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    /**
     * 查询列表数据
     * @param map
     * @return
     */
    public List<CarEvaluateData> list(Map<String, Object> map){
        return readDao.list(map);
    }

    /**
     * 查询记录数
     * @param map
     * @return
     */
    public int count(Map<String, Object> map){
        return readDao.count(map);
    }

}
