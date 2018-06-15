package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAuctionCity;
import com.wintop.ms.carauction.mapper.read.ICarAuctionCityReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAuctionCityWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAuctionCityModel {
    @Autowired
    private ICarAuctionCityReadDao readDao;
    @Autowired
    private ICarAuctionCityWriteDao writeDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAuctionCity> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAuctionCity selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 根据主键逻辑删除记录
     */
    public int updateDelById(Long id){
        return writeDao.updateDelById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAuctionCity carAuctionCity){
        return writeDao.insert(carAuctionCity);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAuctionCity carAuctionCity){
        return writeDao.updateByIdSelective(carAuctionCity);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAuctionCity carAuctionCity){
        return writeDao.updateById(carAuctionCity);
    }
}
