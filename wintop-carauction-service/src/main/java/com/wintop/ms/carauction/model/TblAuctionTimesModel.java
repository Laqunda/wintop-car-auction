package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.TblAuctionTimes;
import com.wintop.ms.carauction.mapper.read.TblAuctionTimesReadDao;
import com.wintop.ms.carauction.mapper.write.TblAuctionTimesWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TblAuctionTimesModel {
    @Autowired
    private TblAuctionTimesReadDao tblAuctionTimesReadDao;
    @Autowired
    private TblAuctionTimesWriteDao tblAuctionTimesWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return tblAuctionTimesReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<TblAuctionTimes> selectByExample(Map<String,Object> map){
        return tblAuctionTimesReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public TblAuctionTimes selectByPrimaryKey(Integer id){
        return tblAuctionTimesReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Integer id){
        return tblAuctionTimesWriteDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(TblAuctionTimes tblAuctionTimes){
        return tblAuctionTimesWriteDao.insert(tblAuctionTimes);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TblAuctionTimes tblAuctionTimes){
        return tblAuctionTimesWriteDao.updateByPrimaryKeySelective(tblAuctionTimes);
    }

    /**
     * 根据条件查询对象
     */
    public TblAuctionTimes selectByParam(Map<String,Object> map){
        return tblAuctionTimesReadDao.selectByParam(map);
    }
}
