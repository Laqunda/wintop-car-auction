package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAuctionFareSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface ICarAuctionFareSettingReadDao {

    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAuctionFareSetting> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAuctionFareSetting selectById(Long id);

    /**
     * 我要竞价接口
     * @param customerId
     * @return
     */
    List<CarAuctionFareSetting> selectMyFareList(@Param("customerId") Long customerId);

    List<CarAuctionFareSetting> selectAllFare();

    /**
     * 查询自动出价加价最高金额
     * @param customerId
     * @return
     */
    CarAuctionFareSetting selectEnableMaxFare(Long customerId);
}
