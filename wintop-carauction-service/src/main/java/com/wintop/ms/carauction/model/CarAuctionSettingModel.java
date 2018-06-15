package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAuctionSetting;
import com.wintop.ms.carauction.mapper.read.ICarAuctionSettingReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAuctionSettingWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAuctionSettingModel {
    @Autowired
    private ICarAuctionSettingReadDao readDao;
    @Autowired
    private ICarAuctionSettingWriteDao writeDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }


    /**
     * 根据条件查询记录集
     */
    public List<CarAuctionSetting> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAuctionSetting selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据地区主键查询记录
     */
    public CarAuctionSetting selectByRegionId(Long regionId){
        return readDao.selectByRegionId(regionId);
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
    public int insert(CarAuctionSetting carAuctionSetting){
        return writeDao.insert(carAuctionSetting);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAuctionSetting carAuctionSetting){
        return writeDao.updateByIdSelective(carAuctionSetting);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAuctionSetting carAuctionSetting){
        return writeDao.updateById(carAuctionSetting);
    }

}
