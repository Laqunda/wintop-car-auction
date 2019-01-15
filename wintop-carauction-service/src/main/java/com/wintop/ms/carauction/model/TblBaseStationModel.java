package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.TblBaseStation;
import com.wintop.ms.carauction.mapper.read.TblBaseStationReadDao;
import com.wintop.ms.carauction.mapper.write.TblBaseStationWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TblBaseStationModel {
    @Autowired
    private TblBaseStationReadDao tblBaseStationReadDao;
    @Autowired
    private TblBaseStationWriteDao tblBaseStationWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return tblBaseStationReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<TblBaseStation> selectByExample(Map<String,Object> map){
        return tblBaseStationReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public TblBaseStation selectByPrimaryKey(Long id){
        return tblBaseStationReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return tblBaseStationWriteDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(TblBaseStation tblBaseStation){
        return tblBaseStationWriteDao.insert(tblBaseStation);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TblBaseStation tblBaseStation){
        return tblBaseStationWriteDao.updateByPrimaryKeySelective(tblBaseStation);
    }

    /**
     * 根据物理ID查询记录
     */
    public TblBaseStation selectByRealId(String stationRealId){
        return tblBaseStationReadDao.selectByRealId(stationRealId);
    }

    /**
     * 逻辑删除基站
     * @param tblBaseStation
     * @return
     */
    public int updateDeleteFlag(TblBaseStation tblBaseStation){
        return tblBaseStationWriteDao.updateDeleteFlag(tblBaseStation);
    }
}
