package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionBidRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ICarAuctionBidRecordService {
    /**
     * 根据条件查询记录总数
     */
    Integer countByExample(Map<String,Object> map);

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
     * 根据主键删除记录
     */
    Integer deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarAuctionBidRecord carAuctionBidRecord, RedisAutoData autoData);

    /**
     * 委托价批量自动竞价
     * @param carAuctionBidRecord
     * @param autoData
     * @return
     */
    public int insertBatch(CarAuctionBidRecord carAuctionBidRecord, RedisAutoData autoData);

    /**
     * 根据主键更新属性不为空的记录
     */
    Integer updateByIdSelective(CarAuctionBidRecord carAuctionBidRecord);

    /**
     * 根据主键更新记录
     */
    Integer updateById(CarAuctionBidRecord carAuctionBidRecord);

    /**
     * 获取车辆的最后一次出价
     * @return
     */
    public BigDecimal selectMaxPrice(Map<String,Object> map);

    /**
     * 获取出价列表
     *
     * @param map
     * @return
     */
    List<CarAuctionBidRecord> selectPriceList(Map<String, Object> map);

    /**
     * 根据条件查询用户出价的车辆列表
     */
    List<CarAuctionBidRecord> queryUserBidList(Map<String,Object> map);

    /**
     * 根据条件查询用户出价车辆的数量
     */
    int selectBidCount(Map<String,Object> map);

    ServiceResult<Map<String,Object>> saveCarAuctionBidRecord(CarAuctionBidRecord carAuctionBidRecord);

    ServiceResult<Map<String,Object>> saveBiddenInfoByCode(CarAuctionBidRecord carAuctionBidRecord,String customerCode);

    ServiceResult<List<CarAuctionBidRecord>> getCustomerBidPriceList(Map<String,Object> paramMap);

    /**
     * @Author 付陈林
     * @Date 2018-4-3
     * @About 清除场次车辆的所有出价记录
     * */
    ServiceResult<Map<String,Object>> cleanBiddenInfo(Map<String,Object> paramMap);

    /**
     * @Author 付陈林
     * @Date 2018-4-3
     * @About 清除场次车辆的最后一条出价记录
     * */
    ServiceResult<Map<String,Object>> cleanLastBiddenInfo(Long auctionCarId);

    ServiceResult<Map<String,Object>> setCustomerBidNumber(String auctionBidRecordIds);

    ServiceResult<Map<String,Object>> setCustomerBidNumberByCode(String auctionBidRecordCodes);

    ServiceResult<CarAuctionBidRecord> selectLastBidInfo(Long auctionCarId);

    /**
     * 批量保存竞价
     * @param bidRecordList
     * @return
     */
    public int insertBatch(List<CarAuctionBidRecord> bidRecordList);

    /**
     * 竞拍开始，自动出价最高委托价
     */
    int insertAuto(Long carId);

    /**
     * 根据车辆id 查询出价列表
     * @param carAuctionBidRecord
     * @return
     */
    public List<Map<String,Object>> getBidPriceList(CarAuctionBidRecord carAuctionBidRecord);
}
