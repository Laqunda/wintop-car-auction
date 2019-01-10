package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionBoard;
import org.apache.ibatis.annotations.Param;

public interface TblAuctionBoardReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<TblAuctionBoard> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    TblAuctionBoard selectByPrimaryKey(Long id);

}