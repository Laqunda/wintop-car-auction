package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoAuction;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoAuctionWriteDao {

    /**
     *
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     */
    int insert(CarAutoAuction record);


    int insertSelective(CarAutoAuction record);


    /**
     *
     */
    int updateByPrimaryKeySelective(CarAutoAuction record);


    int updateByPrimaryKey(CarAutoAuction record);

    int batchUpdateById(@Param(value="autoAuctionList")List<CarAutoAuction> autoAuctionList);

    /**
     * 更新竞拍结束时间或状态
     * @param record
     * @return
     */
    int updateAuctionEndTime(CarAutoAuction record);
}