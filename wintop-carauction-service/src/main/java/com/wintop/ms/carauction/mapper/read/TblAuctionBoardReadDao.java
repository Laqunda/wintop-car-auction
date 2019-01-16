package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionBoard;
import com.wintop.ms.carauction.entity.TblBoardStation;
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

    /**
     * 根据拍牌物理ID查询
     * @param boardRealId
     * @return
     */
    TblAuctionBoard selectByRealId(String boardRealId);

    /**
     * 查询拍牌关联的基站
     * @param boardRealId
     * @return
     */
    List<TblBoardStation> selectStationListByBoardRealId(String boardRealId);

}