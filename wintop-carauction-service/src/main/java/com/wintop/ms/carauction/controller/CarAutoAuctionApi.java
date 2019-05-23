package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarAutoModel;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RedisAutoManager;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.*;

/**
 * 车辆拍卖信息接口类
 */
@RestController
@RequestMapping("/service/carAutoAuction")
public class CarAutoAuctionApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAutoAuctionApi.class);
    private static final String TRANSFER = "1";
    private static final String YES = "1";
    private static final String UNDER_LINE = "2";
    @Autowired
    private ICarAutoAuctionService carAutoAuctionService;
    @Autowired
    private ICarAuctionFareSettingService fareSettingService;
    @Autowired
    private ICarAuctionBidRecordService bidRecordService;
    @Autowired
    private ICarAuctionSettingService auctionSettingService;
    @Autowired
    private ICarManagerUserService carManagerUserService;
    @Autowired
    private RedisAutoManager redisAutoManager;
    @Autowired
    private RedisManagerTemplate redisManagerTemplate;
    @Autowired
    private CarAutoApi carAutoApi;

    private IdWorker idWorker = new IdWorker(10);

    /***
     * 我要竞价
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectMyFareList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<Map<String,Object>>> selectMyFareList(@RequestBody JSONObject obj) {
        Long customerId = obj.getLong("customerId");
        ServiceResult<List<Map<String,Object>>> result = new ServiceResult<>();
        try {
            List<CarAuctionFareSetting> fareSettings =  fareSettingService.selectMyFareList(customerId);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAuctionFareSetting fareSetting:fareSettings){
                Map<String,Object> map = new HashMap<>();
                map.put("farePrice",fareSetting.getFarePrice());
                if(fareSetting.getClickable()==null){
                    map.put("clickable","0");
                }else{
                    map.put("clickable","1");
                }
                list.add(map);
            }
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询我要竞价失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /**
     * 查询车辆评估列表
     */
    @ApiOperation(value = "查询车辆评估列表")
    @RequestMapping(value = "/saveTransferFlag",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> saveTransferFlag(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        JSONObject object = new JSONObject();
        CarManagerUser carManagerUser = carManagerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
        Long autoId = obj.getLong("autoId");
        object.put("autoId",autoId);
        object.put("roleTypeId",carManagerUser.getRoleTypeId());
        Map<String, Object> checkUpMap = carAutoApi.getAutoPublishStatus(object).getResult();
        checkUpMap = Maps.filterValues(checkUpMap, Predicates.equalTo(YES));
        if (MapUtils.isNotEmpty(checkUpMap)) {
            CarAutoAuction info = carAutoAuctionService.selectByAutoId(autoId);
            if (!TRANSFER.equals(info.getTransferFlag())){
                CarAutoAuction carAutoAuction = new CarAutoAuction();
                // 线下
                carAutoAuction.setAuctionType(UNDER_LINE);
                // 已转渠道
                carAutoAuction.setTransferFlag(TRANSFER);
                carAutoAuction.setAutoId(obj.getLong("autoId"));
                try {
                    carAutoAuctionService.updateAuctionEndTime(carAutoAuction);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } else {
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
        }
        return result;
    }

    /***
     * 获取车辆拍卖最高价信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectMaxAuctionPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectMaxAuctionPrice(@RequestBody JSONObject obj) {
        Long carId = obj.getLong("carId");
        Long customerId = obj.getLong("customerId");
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarAutoAuction carAutoAuction = null;
            if (obj.get("auctionId")!=null && StringUtils.isNotEmpty(obj.getString("auctionId"))){
                carAutoAuction = carAutoAuctionService.selectMaxAuctionPriceByAuctionId(obj.getLong("auctionId"),customerId);
            }else {
                carAutoAuction = carAutoAuctionService.selectMaxAuctionPrice(carId, customerId);
            }
            if(carAutoAuction==null){
                result.setError("302","车辆没有拍卖信息");
                return result;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("maxPrice",carAutoAuction.getTopBidPrice());
            map.put("reservePrice",carAutoAuction.getReservePrice());
            map.put("outReserve",carAutoAuction.getOutReserve());
            map.put("auctionEndTime",carAutoAuction.getAuctionEndTime());
            map.put("status",carAutoAuction.getCarStatus());
            map.put("myPrice",carAutoAuction.getMyBidPrice());
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询车辆信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 提交我的竞价
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveMyBidPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveMyBidPrice(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long carId = obj.getLong("carId");
            Long customerId = obj.getLong("customerId");
            BigDecimal bidPrice = obj.getBigDecimal("bidPrice");
            BigDecimal addPrice = obj.getBigDecimal("addPrice");
            RedisAutoData autoData = redisAutoManager.getAutoByKey(Constants.CAR_AUTO_AUCTION+"_"+carId);
            if(autoData==null){
                autoData = carAutoAuctionService.getRedisAutoData(carId);
                redisAutoManager.createAuto(autoData);
            }
            Date date = new Date();
            long currentTime=date.getTime();
            if(!CarStatusEnum.AUCTIONING.value().equals(autoData.getStatus()) || (autoData.getAuctionStartTime()-currentTime)>0l
                    || (currentTime-autoData.getAuctionEndTime())>0l){
                result.setError(ResultCode.NO_AUCTION.strValue(),ResultCode.NO_AUCTION.getRemark());
                return result;
            }
            if(bidPrice.compareTo(autoData.getMaxPrice())<=0){
                result.setError(ResultCode.NO_OVER_MAXPRICE.strValue(),ResultCode.NO_OVER_MAXPRICE.getRemark());
                return result;
            }
            CarAuctionBidRecord auctionBidRecord = new CarAuctionBidRecord();
            //auctionBidRecord.setId(idWorker.nextId());
            auctionBidRecord.setAuctionId(autoData.getAutoAuctionId());
            auctionBidRecord.setCarId(carId);
            //***如果最高出价不超过委托价，则以委托价成交
            if(bidPrice.compareTo(autoData.getEntrustPrice())==0){
                auctionBidRecord.setCustomerId(autoData.getEntrustPriceUserId());
                auctionBidRecord.setBidFee(bidPrice);
                auctionBidRecord.setAddFee(autoData.getEntrustPrice().subtract(autoData.getMaxPrice()));
            }else{
                auctionBidRecord.setCustomerId(customerId);
                auctionBidRecord.setBidFee(bidPrice);
                auctionBidRecord.setAddFee(addPrice);
            }
            auctionBidRecord.setBidTime(date);
            //**出价成功，更新redisautodata信息
            autoData.setMaxPriceUserId(customerId);
            autoData.setMaxPrice(bidPrice);
            //***获取线上拍卖时间设置信息
            String json = redisManagerTemplate.get(CarAuctionRedis.getAuctionKey(autoData.getRegionId()));
            CarAuctionSetting carAuctionSetting = CarAuctionRedis.getAuctionSetting(json);
            if(carAuctionSetting==null){
                carAuctionSetting = auctionSettingService.selectByRegionId(autoData.getRegionId());
                redisManagerTemplate.add(CarAuctionRedis.getAuctionKey(autoData.getRegionId()),CarAuctionRedis.getAuctionSettingJson(carAuctionSetting));
            }
            long time1 = autoData.getAuctionEndTime()-currentTime;
            if(time1<carAuctionSetting.getDelayedTime().longValue()*1000l){
                autoData.setAuctionEndTime(autoData.getAuctionEndTime()+carAuctionSetting.getEndKeepTime()*1000l);
            }
            Integer count = bidRecordService.insertBatch(auctionBidRecord,autoData);
            //Integer count = bidRecordService.insert(auctionBidRecord,autoData);
            map.put("bidPrice",bidPrice);
            map.put("addPrice",addPrice);
            map.put("count",count);
            result.setResult(map);
            if(bidPrice.compareTo(autoData.getEntrustPrice())==0){
                result.setError(ResultCode.NO_OVER_MAXPRICE.strValue(),ResultCode.NO_OVER_MAXPRICE.getRemark());
            }else{
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }
            //***最高价推送
            carAutoAuctionService.sendPushAutoData(autoData);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("提交我的竞价失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    @ApiOperation(value = "查询车辆拍卖信息",notes = "根据车辆id查询车辆拍卖信息")
    @PostMapping("getByAutoId")
    public ServiceResult<CarAutoAuction> getByAutoId(@RequestBody JSONObject object){
        ServiceResult<CarAutoAuction> result = new ServiceResult<>();
        result.setResult(carAutoAuctionService.selectByAutoId(object.getLong("carId")));
        result.setSuccess("0","查询成功");
        return result;
    }

    /**
     * 查询所有有效竞拍车辆
     * @return
     */
    @RequestMapping(value = "/selectAllValidAuto",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CommonNameVo>> selectAllValidAuto(){
        ServiceResult<List<CommonNameVo>> result = new ServiceResult<>();
        try {
            List<CommonNameVo> commonNameVos = carAutoAuctionService.selectAllValidAuto();
            result.setResult(commonNameVos);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有有效竞拍车辆",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /***
     * 获取竞拍信息接口
     */
    @RequestMapping(value = "/selectAuctionInformation",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectAuctionInformation(@RequestBody JSONObject obj) {
        Long carId = obj.getLong("carId");
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarAutoAuction carAutoAuction = carAutoAuctionService.selectAuctionInformation(carId);
            Map<String,Object> map = new HashMap<>();
            if(carAutoAuction != null){
                map.put("sourceId",carAutoAuction.getSourceType());
                map.put("startingPrice",carAutoAuction.getStartingPrice());
                map.put("reservePrice",carAutoAuction.getReservePrice());
                map.put("linkManName",carAutoAuction.getLinkManName());
                map.put("linkManMobile",carAutoAuction.getLinkManMobile());
                map.put("expectedFeedbackTime",carAutoAuction.getExpectedFeedbackTime());
                map.put("remark",carAutoAuction.getRemark());
                map.put("isAgent",carAutoAuction.getIfAgent());
                map.put("serviceNetworkId",carAutoAuction.getStoreId());
                map.put("ownerPrice",carAutoAuction.getOwnerPrice());
                map.put("simpleName",carAutoAuction.getStoreName());
                map.put("ownerMobile",carAutoAuction.getOwnerMobile());
                map.put("ownerName",carAutoAuction.getOwnerName());
                map.put("moveToWhere",carAutoAuction.getMoveToWhere());
                result.setResult(map);
            }else{
                map.put("sourceId",0);
                map.put("startingPrice",0);
                map.put("reservePrice",0);
                map.put("linkManName","");
                map.put("linkManMobile","");
                map.put("expectedFeedbackTime","");
                map.put("remark","");
                map.put("isAgent","");
                map.put("serviceNetworkId",0);
                map.put("ownerPrice",0);
                map.put("simpleName","");
                result.setResult(map);
            }
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取竞拍信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    @PostMapping(value = "saveInfo")
    @ApiOperation(value = "保存车辆拍卖信息",notes = "车辆管理，维护车辆拍卖信息")
    public ServiceResult<Integer> saveInfo(@RequestBody JSONObject object){
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            //车辆竞拍信息
            CarAutoAuction carAutoAuction = new CarAutoAuction();
            carAutoAuction.setAutoId(object.getLong("autoId"));
            carAutoAuction.setStoreId(object.getLong("storeId"));
            carAutoAuction.setStoreName(object.getString("storeName"));
            carAutoAuction.setSourceType(object.getString("sourceType"));
            carAutoAuction.setIfAgent(object.getString("ifAgent"));
            carAutoAuction.setStartingPrice(object.getBigDecimal("startingPrice"));
            carAutoAuction.setReservePrice(object.getBigDecimal("reservePrice"));
            carAutoAuction.setExpectedFeedbackTime(object.getDate("expectedFeedbackTime"));
            carAutoAuction.setLinkManName(object.getString("linkManName"));
            carAutoAuction.setLinkManMobile(object.getString("linkManMobile"));
            carAutoAuction.setRemark(object.getString("remark"));
            carAutoAuction.setOwnerPrice(object.getBigDecimal("ownerPrice"));
            carAutoAuction.setOwnerName(object.getString("ownerName"));
            carAutoAuction.setOwnerMobile(object.getString("ownerMobile"));
            carAutoAuction.setMoveToWhere(object.getString("moveToWhere"));

            if (object.getLong("id")!=null){
                carAutoAuction.setId(object.getLong("id"));
                carAutoAuction.setModifyPerson(object.getLong("userId"));
                carAutoAuction.setModifyTime(new Date());
                result = carAutoAuctionService.updateByPrimaryKey(carAutoAuction);
            }else {
                carAutoAuction.setCreateTime(new Date());
                carAutoAuction.setCreatePerson(object.getLong("userId"));
                carAutoAuction.setId(idWorker.nextId());
                carAutoAuction.setStatus("1");
                result = carAutoAuctionService.insert(carAutoAuction);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }
}
