package com.wintop.ms.carauction.mapper.write;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblBaseStation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblBaseStationWriteDao {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TblBaseStation tblBaseStation);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TblBaseStation tblBaseStation);

    /**
     * 逻辑删除基站
     * @param tblBaseStation
     * @return
     */
    int updateDeleteFlag(TblBaseStation tblBaseStation);

}