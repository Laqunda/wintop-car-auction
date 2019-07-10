package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.OrderStatusEnum;
import com.wintop.ms.carauction.core.entity.JPushAutoData;
import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.mapper.read.ICarAutoAuctionReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoAuctionWriteDao;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarAutoAuctionService;
import com.wintop.ms.carauction.util.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.*;

@Service("CarAutoAuctionService")
public class CarAutoAuctionServiceImpl implements ICarAutoAuctionService {
    @Autowired
    private CarAutoAuctionModel autoAuctionModel;
    @Autowired
    private CarAuctionBidRecordModel bidRecordModel;
    @Autowired
    private CarAuctionSettingModel auctionSettingModel;
    @Autowired
    private CarAutoModel autoModel;
    @Autowired
    private CarAutoLogModel autoLogModel;
    @Autowired
    private AppUserModel appUserModel;
    @Autowired
    private RedisAutoManager redisAutoManager;
    @Autowired
    private CarOrderLogModel orderLogModel;
    @Autowired
    private CarOrderModel orderModel;
    @Autowired
    private CarOrderBargainModel orderBargainModel;
    @Autowired
    private CarRegionSettingModel regionSettingModel;

    private IdWorker idWorker = new IdWorker(10);

    private static final Logger logger = LoggerFactory.getLogger(CarAutoAuctionServiceImpl.class);

    public ServiceResult<Integer> countByExample(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.autoAuctionModel.countByExample(map);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoAuction> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoAuction> result = new ServiceResult<>();
        try {
            CarAutoAuction carAutoAuction = this.autoAuctionModel.selectByPrimaryKey(id);
            result.setResult(carAutoAuction);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoAuction>> selectByExample(Map<String,Object> map) {
        ServiceResult<List<CarAutoAuction>> result = new ServiceResult<>();
        try {
            List<CarAutoAuction> list = this.autoAuctionModel.selectByExample(map);
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.autoAuctionModel.deleteByPrimaryKey(id);
            result.setSuccess("0","成功");
            result.setResult(x);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoAuction record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            //当客户端没有选择前往何地时 直接视为均可
            if (record.getMoveToWhere()==null){
                record.setMoveToWhere("1");
            }
            Integer x = this.autoAuctionModel.updateByPrimaryKeySelective(record);
            result.setSuccess("0","成功");
            result.setResult(x);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoAuction record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count;
            //先保存车辆竞拍信息
            count = this.autoAuctionModel.updateByPrimaryKeySelective(record);
            CarAuto auto = new CarAuto();
            auto.setId(record.getAutoId());
            auto.setSourceType(record.getSourceType());
            auto.setStoreId(record.getStoreId());
            auto.setStoreName(record.getStoreName());
            //在保存车辆归属地+归属店铺
            count += autoModel.updateByPrimaryKeySelective(auto);
            result.setSuccess("0", "成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insert(CarAutoAuction record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count;
            //先保存车辆竞拍信息
            record.setBidsCount(0);
            record.setBidersCount(0);
            count = this.autoAuctionModel.insert(record);
            CarAuto auto = new CarAuto();
            auto.setId(record.getAutoId());
            auto.setSourceType(record.getSourceType());
            auto.setStoreId(record.getStoreId());
            auto.setStoreName(record.getStoreName());
            //在保存车辆归属地+归属店铺
            count += autoModel.updateByPrimaryKeySelective(auto);
            if (count==2) {
                result.setSuccess("0", "成功");
            }else {
                result.setError("-1","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insertSelective(CarAutoAuction record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.autoAuctionModel.insertSelective(record);
            result.setSuccess("0","成功");
            result.setResult(x);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    /**
     * 获取拍卖最高价
     * @param carId
     * @param customerId
     * @return
     */
    @Override
    public CarAutoAuction selectMaxAuctionPrice(Long carId,Long customerId){
        BigDecimal zero = new BigDecimal("0");
        CarAutoAuction autoAuction = autoAuctionModel.selectAutoAuction(carId);
        if(autoAuction==null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("carId",carId);
        //**获取最高价
        map.put("auctionId",autoAuction.getId());
        BigDecimal maxPrice = bidRecordModel.selectMaxPrice(map);
        autoAuction.setTopBidPrice(maxPrice==null?autoAuction.getStartingPrice():maxPrice);
        map.put("customerId",customerId);
        BigDecimal myPrice = bidRecordModel.selectMaxPrice(map);
        autoAuction.setMyBidPrice(myPrice==null?zero:myPrice);
        /**
         * 判断是否过保留价（0未过，1是已过，2未过我最高。3已过我最高）
         */
        if(autoAuction.getTopBidPrice().compareTo(autoAuction.getReservePrice())<0){
            autoAuction.setOutReserve("0");
            if(autoAuction.getMyBidPrice().compareTo(autoAuction.getTopBidPrice())>0){
                autoAuction.setOutReserve("2");
            }
        }else {
            autoAuction.setOutReserve("1");
            if(autoAuction.getMyBidPrice().compareTo(autoAuction.getTopBidPrice())>0){
                autoAuction.setOutReserve("3");
            }
        }
        return autoAuction;
    }

    @Override
    public CarAutoAuction selectMaxAuctionPriceByAuctionId(Long auctionId,Long customerId) {
        BigDecimal zero = new BigDecimal("0");
        CarAutoAuction autoAuction = autoAuctionModel.selectByPrimaryKey(auctionId);
        if(autoAuction==null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("carId",autoAuction.getAutoId());
        //**获取最高价
        map.put("auctionId",autoAuction.getId());
        BigDecimal maxPrice = bidRecordModel.selectMaxPrice(map);
        autoAuction.setTopBidPrice(maxPrice==null?autoAuction.getStartingPrice():maxPrice);
        map.put("customerId",customerId);
        BigDecimal myPrice = bidRecordModel.selectMaxPrice(map);
        autoAuction.setMyBidPrice(myPrice==null?zero:myPrice);
        /**
         * 判断是否过保留价（0未过，1是已过，2未过我最高。3已过我最高）
         */
        if(autoAuction.getTopBidPrice().compareTo(autoAuction.getReservePrice())<0){
            autoAuction.setOutReserve("0");
            if(autoAuction.getMyBidPrice().compareTo(autoAuction.getTopBidPrice())>0){
                autoAuction.setOutReserve("2");
            }
        }else {
            autoAuction.setOutReserve("1");
            if(autoAuction.getMyBidPrice().compareTo(autoAuction.getTopBidPrice())>0){
                autoAuction.setOutReserve("3");
            }
        }
        return autoAuction;
    }

    @Override
    public CarAutoAuction selectByAutoId(Long carId) {
        return autoAuctionModel.selectAutoAuction(carId);
    }

    /**
     * 查询所有有效竞拍车辆
     * @return
     */
    @Override
    public List<CommonNameVo> selectAllValidAuto(){
        return autoAuctionModel.selectAllValidAuto();
    }

    /**
     *获取竞拍信息
     */
    @Override
    public CarAutoAuction selectAuctionInformation(Long carId) {
        return autoAuctionModel.selectAuctionInformation(Collections.singletonMap("carId",carId));
    }

    /**
     * 批量导入车辆信息
     *@Author:zhangzijuan
     *@date 2018/4/4
     *@param:
     */
    public int batchUpdateById(List<CarAutoAuction> autoAuctionList){
        return autoAuctionModel.batchUpdateById(autoAuctionList);
    }

    @Override
    public RedisAutoData getRedisAutoData(Long carId) {
        return autoAuctionModel.getRedisAutoData(carId);
    }

    @Override
    public boolean sendPushAutoData(Long carId) {
        try{
            BigDecimal zero = new BigDecimal(0);
            //CarAuto carAuto = autoModel.selectByPrimaryKey(carId);
            CarAutoAuction autoAuction = autoAuctionModel.selectAutoAuction(carId);
            CarAuctionBidRecord bidRecord = bidRecordModel.selectMaxBidRecordByCarId(carId,autoAuction.getId());
            JPushAutoData autoData = new JPushAutoData();
            autoData.setMaxPrice(bidRecord==null?zero:bidRecord.getBidFee());
            autoData.setMaxPriceUserId(bidRecord==null?0L:bidRecord.getCustomerId());
            autoData.setStartingPrice(autoAuction.getStartingPrice());
            autoData.setAuctionStartTime(autoAuction.getAuctionStartTime().getTime()+"");
            autoData.setAuctionEndTime(autoAuction.getAuctionEndTime().getTime()+"");
            autoData.setStatus(autoAuction.getCarStatus());
            autoData.setAutoId(carId);
            JPushUtil.sendAutoData(autoData);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            logger.info("推送竞价信息失败",e);
            return false;
        }
    }

    /**
     * 更新竞拍车辆数据redis
     * @param carId
     * @return
     */
    public boolean updateRedisAutoData(Long carId){
        try{
            RedisAutoData autoData =  autoAuctionModel.getRedisAutoData(carId);
            redisAutoManager.updateAuto(autoData);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 更新竞拍车辆数据redis
     * @param autoData
     * @return
     */
    public boolean updateRedisAutoData(RedisAutoData autoData){
        try{
            redisAutoManager.updateAuto(autoData);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * JPUSH推送
     * @param autoData
     * @return
     */
    public boolean sendPushAutoData(RedisAutoData autoData){
        try{
            JPushAutoData pushAutoData = new JPushAutoData();
            pushAutoData.setMaxPrice(autoData.getMaxPrice());
            pushAutoData.setMaxPriceUserId(autoData.getMaxPriceUserId());
            pushAutoData.setStartingPrice(autoData.getStartingPrice());
            pushAutoData.setAuctionStartTime(autoData.getAuctionStartTime()+"");
            pushAutoData.setAuctionEndTime(autoData.getAuctionEndTime()+"");
            pushAutoData.setStatus(autoData.getStatus());
            pushAutoData.setAutoId(autoData.getAutoId());
            JPushUtil.sendAutoData(pushAutoData);
            return true;
        } catch (Exception e){
            //e.printStackTrace();
            logger.info("推送竞价信息失败");
            return false;
        }
    }

    /**
     * 更新竞拍结束时间或状态
     * @param record
     * @return
     */
    public int updateAuctionEndTime(CarAutoAuction record){
        return autoAuctionModel.updateAuctionEndTime(record);
    }

    /**
     * JOB更新竞拍结束
     * @param autoData
     * @return
     */
    @Transactional
    @Override
    public int updateAuctionFinish(RedisAutoData autoData){
        Date date = new Date();
        CarAuctionBidRecord auctionBidRecord = new CarAuctionBidRecord();
        if(autoData.getMaxPrice().compareTo(autoData.getEntrustPrice())<0 || (autoData.getMaxPrice().compareTo(autoData.getEntrustPrice())==0 && autoData.getMaxPriceUserId().compareTo(autoData.getEntrustPriceUserId())!=0)){
            BigDecimal addFee = autoData.getEntrustPrice().subtract(autoData.getMaxPrice());
            auctionBidRecord.setId(idWorker.nextId());
            auctionBidRecord.setAuctionId(autoData.getAutoAuctionId());
            auctionBidRecord.setCarId(autoData.getAutoId());
            auctionBidRecord.setCustomerId(autoData.getEntrustPriceUserId());
            auctionBidRecord.setBidFee(autoData.getEntrustPrice());
            auctionBidRecord.setAddFee(addFee);
            auctionBidRecord.setBidTime(date);
            bidRecordModel.insert(auctionBidRecord);
            autoData.setMaxPrice(autoData.getEntrustPrice());
            autoData.setMaxPriceUserId(autoData.getEntrustPriceUserId());
        }else{
            auctionBidRecord = bidRecordModel.selectMaxBidRecordByCarId(autoData.getAutoId(),autoData.getAutoAuctionId());
        }
        //redis信息不同步，再次更新
        /*if(auctionBidRecord.getBidFee().compareTo(autoData.getMaxPrice())!=0 || auctionBidRecord.getCustomerId().compareTo(autoData.getMaxPriceUserId())!=0){
            autoData = autoAuctionModel.getRedisAutoData(autoData.getAutoId());
            redisAutoManager.updateAuto(autoData);
            return 0;
        }*/
        //**更新CarAuto状态
        CarAuto auto = new CarAuto(autoData.getAutoId(),autoData.getStatus(),new Date());
        int count = autoModel.updateByPrimaryKeySelective(auto);
        //**更新CarAutoAuction状态
        CarAutoAuction autoAuction = new CarAutoAuction();
        autoAuction.setAutoId(autoData.getAutoId());
        if(auctionBidRecord!=null){
            autoAuction.setTopBidPrice(auctionBidRecord.getBidFee());
            autoAuction.setTopBidTime(auctionBidRecord.getBidTime());
            autoAuction.setTopPricerId(auctionBidRecord.getCustomerId());
        }
        //***,4成交，3流拍
        autoAuction.setStatus(autoData.getStatus().equals(CarStatusEnum.ABORTIVE_AUCTION.value())?"3":"4");
        autoAuctionModel.updateAuctionEndTime(autoAuction);
        //***增加车辆操作日志
        CarAutoLog autoLog = new CarAutoLog();
        autoLog.setId(idWorker.nextId());
        autoLog.setAutoId(autoData.getAutoId());
        autoLog.setMsg(CarStatusEnum.getDetailRemark(autoData.getStatus()));
        autoLog.setStatus(auto.getStatus());
        autoLog.setTime(date);
        autoLog.setUserType("1");
        WtAppUser appUser = appUserModel.findById(autoData.getMaxPriceUserId());
        if(appUser!=null){
            autoLog.setUserId(appUser.getId());
            autoLog.setUserName(appUser.getName());
            autoLog.setUserMobile(appUser.getMobile());
        }
        autoLogModel.insert(autoLog);
        //判断是否需要生成订单
        if(CarStatusEnum.WAITING_PAY.value().equals(autoData.getStatus())){
            CarAutoAuction autoAuction1 = autoAuctionModel.selectAutoAuction(autoData.getAutoId());
            if(autoAuction1.getServicePrice()==null || autoAuction1.getAgentPrice()==null){
                CarAuctionSetting auctionSetting = auctionSettingModel.selectByRegionId(autoData.getRegionId());
                autoAuction1.setServicePrice(auctionSetting.getServerFee());
                autoAuction1.setAgentPrice(auctionSetting.getAgentFee());
            }
            Date payEndTime = regionSettingModel.getBreachTime(date,autoData.getRegionId(),"1");
            CarOrder order = new CarOrder();
            order.setId(idWorker.nextId());
            order.setAuctionId(1l);
            order.setOrderNo(RandCodeUtil.getOrderNumber());
            order.setCarId(autoData.getAutoId());
            order.setCustomerId(autoData.getMaxPriceUserId());
            order.setTransactionFee(autoData.getMaxPrice());
            order.setServiceFee(autoAuction1.getServicePrice());
            order.setStatus(OrderStatusEnum.WAITING_PAY.value());
            order.setLockFee(autoAuction1.getReservePrice());
            order.setCreateTime(date);
            order.setPayEndTime(payEndTime);
            if("1".equals(autoAuction1.getIfAgent())){
                order.setAgentFee(autoAuction1.getAgentPrice());
            }else{
                order.setAgentFee(new BigDecimal(0));
            }
            order.setAmountFee(order.getTransactionFee().add(order.getServiceFee()).add(order.getAgentFee()));
            order.setAuctionBidRecordId(auctionBidRecord.getId());
            order.setAutoAuctionId(autoAuction1.getId());
            orderModel.insert(order);
            //***生成订单日志
            CarOrderLog orderLog = new CarOrderLog();
            orderLog.setId(idWorker.nextId());
            orderLog.setOrderId(order.getId());
            orderLog.setStatus(OrderStatusEnum.WAITING_PAY.value());
            orderLog.setStatusCn(OrderStatusEnum.WAITING_PAY.getRemark());
            orderLog.setLogMsg(autoLog.getMsg());
            orderLog.setCreateTime(date);
            orderLog.setCreatePerson(appUser.getId());
            orderLog.setUserName(appUser.getName());
            orderLog.setUserMobile(appUser.getMobile());
            orderLogModel.insert(orderLog);
        }
        //***生成议价订单
        if(CarStatusEnum.BARGAIN_HANLDING.value().equals(autoData.getStatus())){
            CarOrderBargain orderBargain = new CarOrderBargain();
            orderBargain.setId(idWorker.nextId());
            orderBargain.setCarId(autoData.getAutoId());
            orderBargain.setCustomerId(autoData.getMaxPriceUserId());
            orderBargain.setCreateTime(date);
            orderBargain.setTransactionFee(autoData.getMaxPrice());
            //****，1待议价
            orderBargain.setStatus("1");
            orderBargainModel.insert(orderBargain);
        }
        return count;
    }

    /**
     * 查询-填充使用,最近的开拍时间
     * @return
     */
    @Override
    public CarAutoAuction selectForToday(Map<String,Object> map) {
        return autoAuctionModel.selectForToday(map);
    }
}