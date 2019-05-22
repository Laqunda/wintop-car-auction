package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAuto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoReadDao {

    int countByExample(Map<String,Object> map);


    List<CarAuto> selectByExample(Map<String,Object> map);

    CarAuto selectByPrimaryKey(Long id);

    /**
     * 车辆信息接口
     * @return
     */
    CarAuto selectByCarId(Map<String,Object> map);

    /**
     * 根据条件查询记录
     */
    List<CarAuto> selectAuctionCarList(Map<String,Object> map);

    /**
     * 查询线上车辆管理列表
     */
    List<CarAuto> selectCarList(Map<String, Object> map);
    /**
     * 查询线上车辆管理数量
     */
    Integer selectCarCount(Map<String,Object> map);
    /**
     * 查询总数量
     * @param map
     * @return
     */
    int selectAuctionCarCount(Map<String,Object> map);


    /**
     * 根据条件查询线上拍的车辆列表
     */
     List<CarAuto> selectOnlineCarList(Map<String,Object> map);

    /**
     * 根据条件查询线上拍的车辆总数量
     * @param map
     * @return
     */
     int selectOnlineCarCount(Map<String,Object> map);


    /**
     * 根据条件查询发拍的车辆列表
     */
    List<CarAuto> selectHairShotCarList(Map<String,Object> map);

    /**
     * 根据条件查询线发拍的车辆数量
     * @param map
     * @return
     */
    int selectHairShotCarCount(Map<String,Object> map);

    /***
     * 查询用户最新发布的草稿车辆
     * @param paramMap
     * @return
     */
    CarAuto selectMyLastAuto(Map paramMap);


    /**
     * @Author 付陈林
     * @Date 2018年3月21号
     * @About 获取所有可以参加竞拍的车辆，1、status为5 等待开拍  2、竞拍类型为线下竞拍  3、未加入到已参加的竞拍列表中
     * */
    List<CarAuto> getLocaleAuctionCar(@Param(value = "searchParam") String searchParam);

    /**
     *查询所有的车辆列表
     * @Author zhangzijiuan
     * @param map
     * @return
     */
    List<CarAuto> getAllCarAutoList(Map<String,Object> map);
    /**
     *查询所有的车辆数目
     * @Author zhangzijiuan
     * @param map
     * @return
     */
    Integer getAllCarAutoCount(Map<String,Object> map);

    /**
     * 查询当日上新车辆数
     */
    Integer selectDayCarCount(Map<String,Object> map);

    /**
     * 库存管理--（零售[已售]、线上拍[车辆库存、审批状态、竞价状态、竞价结果]、现场拍[车辆库存、审批状态、竞价状态、竞价结果]）
     */
    Integer selectCarAutoForSaleCount(Map<String, Object> map);

    /**
     * 零售订单列表
     */
    List<CarAuto> selectRetailForExample(Map<String, Object> map);

    /**
     * 零售订单列表总数量
     */
    Integer selectRetailForCount(Map<String, Object> map);

    List<CarAuto> selectUserOrderList(Map<String, Object> map);

    int selectCountById(Long userId);
}