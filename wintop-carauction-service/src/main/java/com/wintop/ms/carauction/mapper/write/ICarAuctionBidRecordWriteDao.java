package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAuctionBidRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICarAuctionBidRecordWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarAuctionBidRecord carAuctionBidRecord);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(CarAuctionBidRecord carAuctionBidRecord);

    /**
     * 根据主键更新记录
     */
    int updateById(CarAuctionBidRecord carAuctionBidRecord);

    /**
     * 批量保存竞价
     * @param bidRecordList
     * @return
     */
    int insertBatch(@Param("bidRecordList") List<CarAuctionBidRecord> bidRecordList);
}