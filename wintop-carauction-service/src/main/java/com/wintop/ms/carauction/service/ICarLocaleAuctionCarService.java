package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

import javax.xml.ws.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ICarLocaleAuctionCarService {
    /**
     * 根据条件查询记录总数
     */
    public Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    public List<CarLocaleAuctionCar> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    public CarLocaleAuctionCar selectById(Long id);

    /**
     * 根据carId查询记录
     */
    public List<CarLocaleAuctionCar> selectByCarId(Long carId);

    /**
     * 根据主键删除记录
     */
    public ServiceResult<Map<String,Object>> deleteById(Long id,Long userId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public Integer insert(CarLocaleAuctionCar carAuctionCar);

    /**
     * 根据主键更新属性不为空的记录
     */
    public ServiceResult<Map<String,Object>> updateByIdSelective(CarLocaleAuctionCar carAuctionCar);

    /**
     * 调整车辆的编号
     */
    public ServiceResult<Map<String,Object>> updateAuctionCode(Long auctionId);

    /**
     * 根据主键更新记录
     */
    public Integer updateById(CarLocaleAuctionCar carAuctionCar);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public ServiceResult<Map<String ,Object>> saveLocaleAuctionCar(Long auctionId,String carIds,Long userId);

    public int selectCarNumByAuction(Map<String,Object> map);

    /**
     * @Autor 付陈林
     * @Date 2018-3-24
     * @About 根据车辆关联ID，查询车辆的基本信息
     *
     * */
    public ServiceResult<CarLocaleAuctionCar> getCarLocaleAuctionCar(Long auctionCarId);


    /**
     * @Autor 付陈林
     * @Date 2018-3-24
     * @About 根据场次ID查询该场次下面所有车辆
     * */
    public ServiceResult<List<CarLocaleAuctionCar>> getAuctionCarList(Long auctionId);

    /**
     * @Autor 付陈林
     * @Date 2018-3-25
     * @About 根据场次Id及序号，查询下一辆车的现场拍信息
     * */
    public ServiceResult<Map<String,Object>> largeScreenShowCar(Long auctionId,Long auctionCarId);

    /**
     * @Autor 付陈林
     * @Date 2018-3-28
     * @About 设置二拍操作
     * */
    public ServiceResult<Map<String,Object>> setAgainAuction(Long auctionCarId, Long carId,Long userId, BigDecimal startAmount,BigDecimal reserveAmount);

    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆
     * */
    public ServiceResult<List<CarLocaleAuctionCar>> hasAuctionCarList(Map<String,Object> map);

    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆梳理
     * */
    public ServiceResult<Integer> hasAuctionCarCount(Map<String,Object> map);
}
