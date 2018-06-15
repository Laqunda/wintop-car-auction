package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.mapper.read.ICarCenterReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCenterWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarCenterModel {
    @Autowired
    private ICarCenterReadDao carCenterReadDao;
    @Autowired
    private ICarCenterWriteDao centerWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carCenterReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarCenter> selectByExample(Map<String,Object> map){
        return carCenterReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarCenter selectByPrimaryKey(Long id){
        return carCenterReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return centerWriteDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarCenter carCenter){
        return centerWriteDao.insert(carCenter);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(CarCenter carCenter){
        return centerWriteDao.updateByPrimaryKeySelective(carCenter);
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(CarCenter carCenter){
        return centerWriteDao.updateByPrimaryKey(carCenter);
    }

    /**
     * 查询所有中心
     */
    public List<CommonNameVo> selectAllCenter(){
        return carCenterReadDao.selectAllCenter();
    }
}
