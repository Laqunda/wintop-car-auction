package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblBaseStation;
import com.wintop.ms.carauction.entity.TblBoardStation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblBaseStationReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<TblBaseStation> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    TblBaseStation selectByPrimaryKey(Long id);

    /**
     * 根据物理ID查询记录
     */
    TblBaseStation selectByRealId(String stationRealId);

    /**
     * 根据物理ID和token查询记录
     */
    TblBaseStation selectByRealIdAndToken(@Param("stationRealId") String stationRealId, @Param("token")String token);

    /**
     * 查询拍牌关联的基站
     * @param boardRealId
     * @return
     */
    List<TblBaseStation> selectStationListByBoardRealId(String boardRealId);

    /**
     * 查询所有基站
     * @return
     */
    List<TblBaseStation> selectAllStationList();
}