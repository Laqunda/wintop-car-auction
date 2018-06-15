package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAgentLog;
import com.wintop.ms.carauction.mapper.read.ICarAgentLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAgentLogWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAgentLogModel {
    @Autowired
    private ICarAgentLogReadDao readDao;
    @Autowired
    private ICarAgentLogWriteDao writeDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAgentLog> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAgentLog selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAgentLog carAgentLog){
        return writeDao.insert(carAgentLog);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAgentLog carAgentLog){
        return writeDao.updateByIdSelective(carAgentLog);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAgentLog carAgentLog){
        return writeDao.updateById(carAgentLog);
    }
}
