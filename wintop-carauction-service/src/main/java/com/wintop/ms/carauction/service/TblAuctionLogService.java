package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import com.wintop.ms.carauction.entity.TblAuctionLog;
import com.wintop.ms.carauction.entity.TblAuctionTimes;
import com.wintop.ms.carauction.entity.TblDataLog;

import java.util.List;
import java.util.Map;

public interface TblAuctionLogService {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<TblAuctionLog> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    TblAuctionLog selectByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TblAuctionLog tblAuctionLog);

    /**
     * 保存电子竞价
     */
    TblAuctionTimes saveBidding(Map<String,Object> map);

    /**
     * 更新价格幅度调整
     * @param auctionLog
     * @return
     */
    int updatePriceRange(TblAuctionLog auctionLog);

    /**
     * 调价器打点
     * @param localeAuctionCar
     * @param auctionLog
     * @param adjustType
     * @return
     */
    int updateBidFeePoint(CarLocaleAuctionCar localeAuctionCar,TblAuctionLog auctionLog,String adjustType);

    /**
     * 保存记录
     * @return
     */
    int insertDataLog(String dateType,String dataContent,String resultContent);
}
