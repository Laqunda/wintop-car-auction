package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import com.wintop.ms.carauction.mapper.read.TblAuctionLogReadDao;
import com.wintop.ms.carauction.mapper.write.TblAuctionLogWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TblAuctionLogModel {
    @Autowired
    private TblAuctionLogReadDao tblAuctionLogReadDao;
    @Autowired
    private TblAuctionLogWriteDao tblAuctionLogWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return tblAuctionLogReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<TblAuctionLog> selectByExample(Map<String,Object> map){
        return tblAuctionLogReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public TblAuctionLog selectByPrimaryKey(Integer id){
        return tblAuctionLogReadDao.selectByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(TblAuctionLog tblAuctionLog){
        return tblAuctionLogWriteDao.insert(tblAuctionLog);
    }

}
