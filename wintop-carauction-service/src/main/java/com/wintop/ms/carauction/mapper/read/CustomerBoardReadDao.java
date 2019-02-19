package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.CustomerBoard;
import org.apache.ibatis.annotations.Param;

public interface CustomerBoardReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CustomerBoard> selectByExample(Map<String,Object> map);

    /**
     * 根据当前拍牌查询是否有绑定
     */
    List<CustomerBoard> selectByBoardRealId(@Param("boardRealId") String boardRealId);

}