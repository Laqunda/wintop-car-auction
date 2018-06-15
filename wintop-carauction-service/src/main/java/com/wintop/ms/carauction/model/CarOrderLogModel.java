package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarOrderLog;
import com.wintop.ms.carauction.mapper.read.ICarOrderLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarOrderLogWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarOrderLogModel {
    @Autowired
    private ICarOrderLogReadDao readDao;
    @Autowired
    private ICarOrderLogWriteDao writeDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarOrderLog> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarOrderLog selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarOrderLog carOrderLog){
        return writeDao.insert(carOrderLog);
    }


}
