package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.TblAuctionBoard;
import com.wintop.ms.carauction.mapper.read.TblAuctionBoardReadDao;
import com.wintop.ms.carauction.mapper.write.TblAuctionBoardWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TblAuctionBoardModel {
    @Autowired
    private TblAuctionBoardReadDao tblAuctionBoardReadDao;
    @Autowired
    private TblAuctionBoardWriteDao tblAuctionBoardWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return tblAuctionBoardReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<TblAuctionBoard> selectByExample(Map<String,Object> map){
        return tblAuctionBoardReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public TblAuctionBoard selectByPrimaryKey(Long id){
        return tblAuctionBoardReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return tblAuctionBoardWriteDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(TblAuctionBoard tblAuctionBoard){
        return tblAuctionBoardWriteDao.insert(tblAuctionBoard);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TblAuctionBoard tblAuctionBoard){
        return tblAuctionBoardWriteDao.updateByPrimaryKeySelective(tblAuctionBoard);
    }
}
