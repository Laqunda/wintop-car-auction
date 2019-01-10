package com.wintop.ms.carauction.mapper.write;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionBoard;
import org.apache.ibatis.annotations.Param;

public interface TblAuctionBoardWriteDao {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TblAuctionBoard tblAuctionBoard);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TblAuctionBoard tblAuctionBoard);

}