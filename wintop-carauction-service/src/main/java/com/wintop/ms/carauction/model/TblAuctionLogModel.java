package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import com.wintop.ms.carauction.entity.TblDataLog;
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
    public TblAuctionLog selectByPrimaryKey(Long id){
        return tblAuctionLogReadDao.selectByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(TblAuctionLog tblAuctionLog){
        return tblAuctionLogWriteDao.insert(tblAuctionLog);
    }

    /**
     * 根据竞拍详情id查询数据
     * @param auctionCarId
     * @param priceType
     * @return
     */
    public TblAuctionLog selectByAuctionCarId(Long auctionCarId,String priceType){
        return tblAuctionLogReadDao.selectByAuctionCarId(auctionCarId, priceType);
    }

    /**
     * 增加打点
     * @param tblAuctionLog
     * @return
     */
    public int updatePriceAdd(TblAuctionLog tblAuctionLog){
        return tblAuctionLogWriteDao.updatePriceAdd(tblAuctionLog);
    }

    /**
     * 取消打点
     * @param id
     * @return
     */
    public int updatePriceSub(Long id,Long managerId){
        return tblAuctionLogWriteDao.updatePriceSub(id,managerId);
    }

    /**
     * 取消出价
     * @param id
     * @return
     */
    public int updateBidFeeSub(Long id){
        return tblAuctionLogWriteDao.updateBidFeeSub(id);
    }

    /**
     * 保存记录
     * @param dataLog
     * @return
     */
    public int insertDataLog(TblDataLog dataLog){
        return tblAuctionLogWriteDao.insertDataLog(dataLog);
    }

}
