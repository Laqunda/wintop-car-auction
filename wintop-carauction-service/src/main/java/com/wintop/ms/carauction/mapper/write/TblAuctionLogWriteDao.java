package com.wintop.ms.carauction.mapper.write;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import org.apache.ibatis.annotations.Param;

public interface TblAuctionLogWriteDao {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TblAuctionLog tblAuctionLog);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TblAuctionLog tblAuctionLog);

}