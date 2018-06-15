package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAuctionBidRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAuctionBidRecordReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAuctionBidRecord> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAuctionBidRecord selectById(Long id);

    /**
     * 根据auctionId查询记录
     */
    List<CarAuctionBidRecord> selectByAuctionId(Long auctionId);

    /**
     * 获取车辆的最后一次出价
     * 如果customerId为空，则是获取本车的最高出价
     * @return
     */
    BigDecimal selectMaxPrice(Map<String,Object> map);

    /**
     * 获取车辆的最后一次出价
     * @return
     */
    CarAuctionBidRecord selectLastBidRecord(Long auctionCarId);

    Integer selectBidCountForLocale(Long auctionCarId);

    Integer selectCustomerBidCountForLocale(Long auctionCarId);

    /**
     * 根据条件查询出价的车辆数量
     */
    Integer selectBidCount(Map<String,Object> map);

    /**
     * 查询用户出价的车辆列表
     */
    List<CarAuctionBidRecord> queryUserBidList(Map<String,Object> map);

    /**
     * 查询用户出价的车辆列表
     */
    List<CarAuctionBidRecord> getCustomerBidPriceList(Map<String,Object> map);

    /**
     * @Author 付陈林
     * @Date 2018-4-3
     * @About 获取场次车辆的最后一条出价记录
     * */
    CarAuctionBidRecord selectLastBidInfo(@Param("auctionCarId")Long auctionCarId);

    /**
     * 获取本场车辆的最高出价
     * @param carId
     * @param autoAuctionId
     * @return
     */
    CarAuctionBidRecord selectMaxBidRecordByCarId(@Param("carId") Long carId,@Param("autoAuctionId") Long autoAuctionId);

}