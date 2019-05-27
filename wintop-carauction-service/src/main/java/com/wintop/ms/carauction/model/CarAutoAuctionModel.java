package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.mapper.read.*;
import com.wintop.ms.carauction.mapper.write.ICarAutoAuctionWriteDao;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class CarAutoAuctionModel {
    @Resource
    private ICarAutoAuctionReadDao readDao;
    @Autowired
    private ICarAuctionBidRecordReadDao bidRecordReadDao;
    @Autowired
    private ICarCustomerEntrustCarReadDao entrustCarReadDao;
    @Autowired
    private ICarAutoAuctionWriteDao writeDao;

    private static final Logger logger = LoggerFactory.getLogger(CarAutoAuctionModel.class);

    public int countByExample(Map<String,Object> map) {
        int count = this.readDao.countByExample(map);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoAuction selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoAuction> selectByExample(Map<String,Object> map) {
        return this.readDao.selectByExample(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoAuction record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoAuction record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoAuction record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoAuction record) {
        return this.writeDao.insertSelective(record);
    }

    /**
     * 获取车辆拍卖活动
     * @param carId
     * @return
     */
    public CarAutoAuction selectAutoAuction(Long carId){
        return readDao.selectAutoAuction(carId);
    }

    /**
     * 查询所有有效竞拍车辆
     * @return
     */
    public List<CommonNameVo> selectAllValidAuto(){
        return readDao.selectAllValidAuto();
    }

    /**
     * 获取竞拍信息
     */
    public CarAutoAuction selectAuctionInformation(Long carId){
        return readDao.selectAuctionInformation(carId);
    }

    /**
     * 获取车辆拍卖活动
     * @param carId
     * @return
     */
    public CarAutoAuction selectAutoAuctionByCarId(Long carId){
        return readDao.selectAutoAuctionByCarId(carId);
    }
        /**
         * 批量导入车辆信息
         *@Author:zhangzijuan
         *@date 2018/4/4
         *@param:
         */
        public int batchUpdateById(List<CarAutoAuction> autoAuctionList){
        return writeDao.batchUpdateById(autoAuctionList);
    }

    public RedisAutoData getRedisAutoData(Long carId){
        BigDecimal zero = new BigDecimal(0);
        CarAutoAuction autoAuction = readDao.selectAutoAuction(carId);
        CarAuctionBidRecord bidRecord = bidRecordReadDao.selectMaxBidRecordByCarId(carId,autoAuction.getId());
        CarCustomerEntrustCar entrustCar = entrustCarReadDao.selectMaxEntrustByCarId(carId);
        RedisAutoData autoData = new RedisAutoData();
        autoData.setAutoId(carId);
        autoData.setAutoAuctionId(autoAuction.getId());
        autoData.setSourceType(autoAuction.getSourceType());
        autoData.setRegionId(autoAuction.getRegionId());
        autoData.setStatus(autoAuction.getCarStatus());
        autoData.setAuctionStartTime(autoAuction.getAuctionStartTime().getTime());
        autoData.setAuctionEndTime(autoAuction.getAuctionEndTime().getTime());
        autoData.setReservePrice(autoAuction.getReservePrice());
        autoData.setStartingPrice(autoAuction.getStartingPrice());
        autoData.setMaxPrice(bidRecord==null?autoAuction.getStartingPrice():bidRecord.getBidFee());
        autoData.setMaxPriceUserId(bidRecord==null?0l:bidRecord.getCustomerId());
        autoData.setEntrustPrice(entrustCar==null?zero:entrustCar.getEntrustFee());
        autoData.setEntrustPriceUserId(entrustCar==null?0l:entrustCar.getCustomerId());
        return autoData;
    }

    /**
     * 更新竞拍结束时间或状态
     * @param record
     * @return
     */
    public int updateAuctionEndTime(CarAutoAuction record){
        return writeDao.updateAuctionEndTime(record);
    }

    /**
     * 获取场次中车辆竞拍信息
     */
    public List<CarAutoAuction> selectAutoAuctionBylocale(Map<String,Object> map){
        return readDao.selectAutoAuctionBylocale(map);
    }
}