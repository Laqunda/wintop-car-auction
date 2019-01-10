package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.TblAuctionBoard;

import java.util.List;
import java.util.Map;

public interface TblAuctionBoardService {
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
