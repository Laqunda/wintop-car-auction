package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblAuctionLogWriteDao {

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TblAuctionLog tblAuctionLog);

    /**
     * 增加打点
     * @param tblAuctionLog
     * @return
     */
    int updatePriceAdd(TblAuctionLog tblAuctionLog);

    /**
     * 取消打点
     * @param id
     * @return
     */
    int updatePriceSub(@Param("id") Long id, @Param("managerId") Long managerId);

    /**
     * 取消出价
     * @param id
     * @return
     */
    int updateBidFeeSub(Long id);

}