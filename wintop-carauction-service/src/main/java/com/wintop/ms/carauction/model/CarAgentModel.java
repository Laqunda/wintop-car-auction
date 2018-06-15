package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAgent;
import com.wintop.ms.carauction.mapper.read.ICarAgentReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAgentWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CarAgentModel {
    @Resource
    private ICarAgentReadDao readDao;
    @Resource
    private ICarAgentWriteDao writeDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByParam(Map<String,Object> map){
        return readDao.countByParam(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAgent> selectListByParam(Map<String,Object> map){
        return readDao.selectListByParam(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAgent selectById(Long id){
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
    public int insert(CarAgent carAgent){
        return writeDao.insert(carAgent);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAgent carAgent){
        return writeDao.updateByIdSelective(carAgent);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAgent carAgent){
        return writeDao.updateById(carAgent);
    }

    public CarAgent selectByOrderId(Long orderId){
        return readDao.selectByOrderId(orderId);
    }
}
