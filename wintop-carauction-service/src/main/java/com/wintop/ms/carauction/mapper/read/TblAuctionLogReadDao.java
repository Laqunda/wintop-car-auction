package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblAuctionLogReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<TblAuctionLog> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    TblAuctionLog selectByPrimaryKey(Long id);

    /**
     * 根据竞拍详情id查询数据
     * @param auctionCarId
     * @param priceType
     * @return
     */
    TblAuctionLog selectByAuctionCarId(@Param("auctionCarId") Long auctionCarId,@Param("priceType") String priceType);

    /**
     * 查询出价次数
     * @param auctionLog
     * @return
     */
    List<TblAuctionLog> selectBidByAuctionCarId(TblAuctionLog auctionLog);
}