package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAuctionBidRecord;
import com.wintop.ms.carauction.mapper.read.ICarAuctionBidRecordReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAuctionBidRecordWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class CarAuctionBidRecordModel {
    @Autowired
    private ICarAuctionBidRecordReadDao readDao;
    @Autowired
    private ICarAuctionBidRecordWriteDao writeDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAuctionBidRecord> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAuctionBidRecord selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据auctionId查询记录
     */
    public List<CarAuctionBidRecord> selectByAuctionId(Long auctionId){
        return readDao.selectByAuctionId(auctionId);
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
    public int insert(CarAuctionBidRecord carAuctionBidRecord){
        return writeDao.insert(carAuctionBidRecord);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAuctionBidRecord carAuctionBidRecord){
        return writeDao.updateByIdSelective(carAuctionBidRecord);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAuctionBidRecord carAuctionBidRecord){
        return writeDao.updateById(carAuctionBidRecord);
    }

    /**
     * 获取车辆的最后一次出价
     * @return
     */
    public BigDecimal selectMaxPrice(Map<String,Object> map){
        return readDao.selectMaxPrice(map);
    }

    /**
     * 获取出价列表
     * @param map
     * @return
     */
    public List<CarAuctionBidRecord> selectPriceList(Map<String,Object> map) {
        return readDao.selectPriceList(map);
    }
    /**
     * 根据场次车辆id查询最高一次的出价记录
     * */
    public CarAuctionBidRecord selectLastBidRecord(Long auctionCarId){
        return readDao.selectLastBidRecord(auctionCarId);
    }

    public Integer selectBidCountForLocale(Long auctionCarId){
        return readDao.selectBidCountForLocale(auctionCarId);
    }

    public Integer selectCustomerBidCountForLocale(Long auctionCarId){
        return readDao.selectCustomerBidCountForLocale(auctionCarId);
    }

    /**
     * 根据条件查询用户出价的车辆数量
     */
    public Integer selectBidCount(Map<String,Object> map){
        return readDao.selectBidCount(map);
    }

    /**
     * 根据条件查询用户出价的车辆列表
     */
    public List<CarAuctionBidRecord> queryUserBidList(Map<String,Object> map){
        return readDao.queryUserBidList(map);
    }

    public List<CarAuctionBidRecord> getCustomerBidPriceList(Map<String,Object> map){
        return readDao.getCustomerBidPriceList(map);
    }
    /**
     * @Author 付陈林
     * @Date 2018-4-3
     * @About 获取场次车辆的最后一条出价记录
     * */
    public CarAuctionBidRecord selectLastBidInfo(Long auctionCarId){
        return readDao.selectLastBidInfo(auctionCarId);
    }

    /**
     * 获取本场车辆的最高出价
     * @param carId
     * @param autoAuctionId
     * @return
     */
    public CarAuctionBidRecord selectMaxBidRecordByCarId(Long carId,Long autoAuctionId){
        return readDao.selectMaxBidRecordByCarId(carId,autoAuctionId);
    }

    /**
     * 批量保存竞价
     * @param bidRecordList
     * @return
     */
    public int insertBatch(List<CarAuctionBidRecord> bidRecordList){
        return writeDao.insertBatch(bidRecordList);
    }
}
