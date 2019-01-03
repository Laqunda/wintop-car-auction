package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.TblAuctionLog;

import java.util.List;
import java.util.Map;

public interface TblAuctionLogService {
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
    TblAuctionLog selectByPrimaryKey(Integer id);

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
