package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoAuction;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ICarAutoAuctionReadDao {
    /**
     *
     */
    int countByExample(Map<String,Object> map);

    /**
     *
     */
    List<CarAutoAuction> selectByExample(Map<String,Object> map);

    /**
     *
     */
    CarAutoAuction selectByPrimaryKey(Long id);

    /**
     * 查询-填充使用,最近的开拍时间
     */
    CarAutoAuction selectForToday(Map<String,Object> map);

    /**
     * 获取车辆拍卖活动
     */
    CarAutoAuction selectAutoAuction(@Param("carId") Long carId);

    /**
     * 查询所有有效竞拍车辆
     */
    List<CommonNameVo> selectAllValidAuto();

    /**
     * 获取竞拍信息
     */
    CarAutoAuction selectAuctionInformation(Map<String,Object> param);

    /**
     * 获取车辆拍卖活动
     */
    CarAutoAuction selectAutoAuctionByCarId(@Param("carId") Long carId);

    /**
     * 获取现场拍卖的车辆竞拍信息
     */
    List<CarAutoAuction> selectAutoAuctionBylocale(Map<String,Object> map);
}