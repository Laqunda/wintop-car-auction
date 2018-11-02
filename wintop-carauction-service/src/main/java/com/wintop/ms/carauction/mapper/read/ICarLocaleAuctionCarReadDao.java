package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import java.util.List;
import java.util.Map;

public interface ICarLocaleAuctionCarReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarLocaleAuctionCar> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarLocaleAuctionCar selectById(Long id);

    /**
     * 根据carId查询记录
     */
    List<CarLocaleAuctionCar> selectByCarId(Long carId);

    /**
     * 根据auctionId查询车辆数量
     * @param map
     * @return
     */
    int selectCarNumByAuction(Map<String,Object> map);

    /**
     * @Autor 付陈林
     * @Date 2018年3月19号
     * @About 根据场次车辆ID 查询该场次的所有车辆信息
     * */
    List<CarLocaleAuctionCar> getAuctionCarList(Long auctionId);

    /**
     * @Autor 付陈林
     * @Date 2018年3月22号
     * @About 根据场次车辆ID 查该场次下面车辆最大的排序
     * */
    Integer getMaxSortForActionCar(Long auctionId);

    /**
     * 查询最近的正在拍卖的车辆序号
     * @param auctionId
     * @return
     */
    Integer getMinSortForActionCar(Long auctionId);

    CarLocaleAuctionCar getCarLocaleAuctionCar(Long auctionCarId);

    /**
     * @Autor 付陈林
     * @Date 2018-3-25
     * @About 根据场次Id及序号，查询下一辆车的现场拍信息
     * */
    List<CarLocaleAuctionCar> largeScreenShowCar(Long auctionId);

    /**
     * @Autor 付陈林
     * @Date 2018-3-25
     * @About 查询当前车辆在整个车辆中的排序
     * */
    Integer largeScreenSort(Map paramMap);

    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆
     * */
    List<CarLocaleAuctionCar> hasAuctionCarList(Map<String,Object> map);
    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆梳理
     * */
    Integer hasAuctionCarCount(Map<String,Object> map);
}