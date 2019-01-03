package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionTimes;
import org.apache.ibatis.annotations.Param;

public interface TblAuctionTimesReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<TblAuctionTimes> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    TblAuctionTimes selectByPrimaryKey(Integer id);

    /**
     * 根据条件查询对象
     */
    TblAuctionTimes selectByParam(Map<String,Object> map);

}