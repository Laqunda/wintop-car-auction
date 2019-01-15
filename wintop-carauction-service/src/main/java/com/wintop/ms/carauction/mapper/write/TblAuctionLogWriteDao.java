package com.wintop.ms.carauction.mapper.write;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import org.apache.ibatis.annotations.Param;

public interface TblAuctionLogWriteDao {

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TblAuctionLog tblAuctionLog);

    /**
     * 增加价格
     * @param tblAuctionLog
     * @return
     */
    int updatePriceAdd(TblAuctionLog tblAuctionLog);

}