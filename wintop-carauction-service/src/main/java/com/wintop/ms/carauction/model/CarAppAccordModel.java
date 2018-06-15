package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAppAccord;
import com.wintop.ms.carauction.mapper.read.ICarAppAccordReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAppAccordWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
@Repository
public class CarAppAccordModel {
    @Autowired
    private ICarAppAccordReadDao carAppAccordReadDao;
    @Autowired
    private ICarAppAccordWriteDao carAppAccordWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carAppAccordReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAppAccord> selectByExample(Map<String,Object> map){
        return carAppAccordReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAppAccord selectById(Long id){
        return carAppAccordReadDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return carAppAccordWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAppAccord carAppAccord){
        return carAppAccordWriteDao.insert(carAppAccord);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAppAccord carAppAccord){
        return carAppAccordWriteDao.updateByIdSelective(carAppAccord);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAppAccord carAppAccord){
        return carAppAccordWriteDao.updateById(carAppAccord);
    }

    public CarAppAccord findByCode(String code) {
        return carAppAccordReadDao.findByCode(code);
    }
}
