package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAuctionFareSetting;
import com.wintop.ms.carauction.mapper.read.ICarAuctionFareSettingReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAuctionFareSettingWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Repository
public class CarAuctionFareSettingModel {
    @Autowired
    private ICarAuctionFareSettingReadDao carAuctionFareSettingReadDao;
    @Autowired
    private ICarAuctionFareSettingWriteDao carAuctionFareSettingWriteDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carAuctionFareSettingReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAuctionFareSetting> selectByExample(Map<String,Object> map){
        return carAuctionFareSettingReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAuctionFareSetting selectById(Long id){
        return carAuctionFareSettingReadDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return carAuctionFareSettingWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAuctionFareSetting carAuctionFareSetting){
        return carAuctionFareSettingWriteDao.insert(carAuctionFareSetting);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAuctionFareSetting carAuctionFareSetting){
        return carAuctionFareSettingWriteDao.updateByIdSelective(carAuctionFareSetting);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAuctionFareSetting carAuctionFareSetting){
        return carAuctionFareSettingWriteDao.updateById(carAuctionFareSetting);
    }

    /**
     * 我要竞价接口
     * @param customerId
     * @return
     */
    public List<CarAuctionFareSetting> selectMyFareList(Long customerId){
        return carAuctionFareSettingReadDao.selectMyFareList(customerId);
    }

    /**
     * 查询所有的价格
     */
    public List<CarAuctionFareSetting> selectAllFare(){
        return  carAuctionFareSettingReadDao.selectAllFare();
    }

    /**
     * 查询自动出价加价最高金额
     * @param customerId
     * @return
     */
    public CarAuctionFareSetting selectEnableMaxFare(Long customerId){
        return carAuctionFareSettingReadDao.selectEnableMaxFare(customerId);
    }
}
