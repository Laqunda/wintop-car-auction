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
    private TblBaseStationReadDao baseStationReadDao;
    @Autowired
    private TblBaseStationWriteDao baseStationWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return baseStationReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<TblBaseStation> selectByExample(Map<String,Object> map){
        return baseStationReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public TblBaseStation selectByPrimaryKey(Long id){
        return baseStationReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return baseStationWriteDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(TblBaseStation tblBaseStation){
        return baseStationWriteDao.insert(tblBaseStation);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TblBaseStation tblBaseStation){
        return baseStationWriteDao.updateByPrimaryKeySelective(tblBaseStation);
    }

    /**
     * 根据物理ID查询记录
     */
    public TblBaseStation selectByRealId(String stationRealId){
        return baseStationReadDao.selectByRealId(stationRealId);
    }

    /**
     * 根据物理ID和token查询记录
     */
    public TblBaseStation selectByRealIdAndToken(String stationRealId,String token){
        return baseStationReadDao.selectByRealIdAndToken(stationRealId, token);
    }

    /**
     * 逻辑删除基站
     * @param tblBaseStation
     * @return
     */
    public int updateDeleteFlag(TblBaseStation tblBaseStation){
        return baseStationWriteDao.updateDeleteFlag(tblBaseStation);
    }

    /**
     * 查询拍牌关联的基站
     * @param boardRealId
     * @return
     */
    public List<TblBaseStation> selectStationListByBoardRealId(String boardRealId){
        return baseStationReadDao.selectStationListByBoardRealId(boardRealId);
    }

    /**
     * 查询所有基站
     * @return
     */
    public List<TblBaseStation> selectAllStationList(){
        return baseStationReadDao.selectAllStationList();
    }
}
