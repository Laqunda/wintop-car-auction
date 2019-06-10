package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoAuction;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ICarAutoAuctionService {
    ServiceResult<Integer> countByExample(Map<String,Object> map);

    ServiceResult<CarAutoAuction> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoAuction>> selectByExample(Map<String,Object> map);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoAuction record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoAuction record);

    ServiceResult<Integer> insert(CarAutoAuction record);

    ServiceResult<Integer> insertSelective(CarAutoAuction record);

    /**
     * 获取拍卖最高价
     * @param carId
     * @param customerId
     * @return
     */
    CarAutoAuction selectMaxAuctionPrice(Long carId,Long customerId);

    /**
     * 获取拍卖最高价
     * @param auctionId
     * @return
     */
    CarAutoAuction selectMaxAuctionPriceByAuctionId(Long auctionId,Long customerId);

    /***
     * 查询车辆竞拍信息
     * @param carId
     * @return
     */
    CarAutoAuction selectByAutoId(Long carId);

    /**
     * 查询所有有效竞拍车辆
     * @return
     */
    public List<CommonNameVo> selectAllValidAuto();

    /**
     * 获取竞拍信息
     */
    CarAutoAuction selectAuctionInformation(Long carId);

    /**
     * 批量导入车辆信息
     *@Author:zhangzijuan
     *@date 2018/4/4
     *@param:
     */
     int batchUpdateById(List<CarAutoAuction> autoAuctionList);

    /**
     * 获取竞拍车辆数据
     * @param carId
     * @return
     */
    RedisAutoData getRedisAutoData(Long carId);

    /**
     * JPUSH推送
     * @param carId
     * @return
     */
    public boolean sendPushAutoData(Long carId);

    /**
     * 更新竞拍车辆数据redis
     * @param carId
     * @return
     */
    boolean updateRedisAutoData(Long carId);

    /**
     * 更新竞拍车辆数据redis
     * @param autoData
     * @return
     */
    boolean updateRedisAutoData(RedisAutoData autoData);

    /**
     * JPUSH推送
     * @param autoData
     * @return
     */
    public boolean sendPushAutoData(RedisAutoData autoData);

    /**
     * 更新竞拍结束时间或状态
     * @param record
     * @return
     */
    public int updateAuctionEndTime(CarAutoAuction record);

    /**
     * JOB更新竞拍结束
     * @param autoData
     * @return
     */
    public int updateAuctionFinish(RedisAutoData autoData);

    /**
     * 查询-填充使用,最近的开拍时间
     *
     * @return
     */
    public String selectForToday();
}