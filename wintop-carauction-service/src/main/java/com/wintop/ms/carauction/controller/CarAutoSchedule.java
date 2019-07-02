package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.Notify;
import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.JPushUtil;
import com.wintop.ms.carauction.util.utils.RedisAutoManager;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping( "service/carAutoSchedule" )
public class CarAutoSchedule {
    private static final String SMS_TEMPLATE = "【柠檬竞价】恭喜您成功拍得%s，祝您生活愉快";
    private static final String WAIT_AUCTION = "1";
    private static final String BUYER = "2";
    private static final String AUCTION_START_TITLE = "即将开拍";
    private static final String AUCTION_START_TEMPLATE = "您关注的%s即将开拍，快来看看";
    @Autowired
    private RedisAutoManager redisAutoManager;
    @Autowired
    private ICarAutoAuctionService auctionService;
    @Autowired
    private ICarAutoService carAutoService;
    @Autowired
    private TblAuctionLogService auctionLogService;
    @Autowired
    private IWtAppUserService wtAppUserService;
    @Autowired
    private ICarCustomerViewedAutoService carCustomerViewedAutoService;
    @Autowired
    private ICarAppInfoService appInfoService;

    @Autowired
    private RedisManagerTemplate redisManagerTemplate;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    private static final Logger logger = LoggerFactory.getLogger(CarAutoSchedule.class);

    /***
     * 开发环境请将@Scheduled   注释掉
     * 每1000毫秒执行一次job方法
     */
    @RequestMapping( "/dataJob" )
    @ResponseBody
    public int RedisAutoDataJob() {
        //System.out.println("--------1------");
        List<RedisAutoData> dataList = redisAutoManager.getRedisAutoDataList(Constants.CAR_AUTO_AUCTION + "_*");
        long currentTime = System.currentTimeMillis();
        for (RedisAutoData autoData : dataList) {
            //***到达开拍时间，更改状态
            if (currentTime - autoData.getAuctionStartTime() >= 0 && CarStatusEnum.WAITING_AUCTION.value().equals(autoData.getStatus())) {
                //***更新car_auto状态
                CarAuto carAuto = new CarAuto(autoData.getAutoId(), CarStatusEnum.AUCTIONING.value(), new Date());
                carAutoService.updateByIdSelective(carAuto);
                //**更新CarAutoAuction状态,2正在竞拍
                CarAutoAuction autoAuction = new CarAutoAuction();
                autoAuction.setAutoId(autoData.getAutoId());
                autoAuction.setStatus("2");
                auctionService.updateAuctionEndTime(autoAuction);
                //***更新redis状态
                autoData.setStatus(CarStatusEnum.AUCTIONING.value());
                redisAutoManager.updateAuto(autoData);
                //***推送
                auctionService.sendPushAutoData(autoData);
                continue;
            }
            //***结束时间超过3小时的无效数据，直接清除
            if (currentTime - autoData.getAuctionEndTime() >= 3 * 60 * 60 * 1000l) {
                redisAutoManager.delAuto(autoData.redisKey());
                continue;
            }
            //***超过10秒的无效数据，直接清除
            if (currentTime - autoData.getAuctionEndTime() >= 10000l && !CarStatusEnum.AUCTIONING.value().equals(autoData.getStatus()) && "1".equals(autoData.getSuccess())) {
                redisAutoManager.delAuto(autoData.redisKey());
                continue;
            }
            //***同步失败继续同步
            if ("0".equals(autoData.getSuccess())) {
                try {
                    auctionService.updateAuctionFinish(autoData);
                    autoData.setSuccess("1");
                } catch (Exception e) {
                    autoData.setSuccess("0");
                }
                continue;
            }
            if (currentTime - autoData.getAuctionEndTime() >= 0l && CarStatusEnum.AUCTIONING.value().equals(autoData.getStatus())) {
                //***无人出价并且无委托价直接流拍
                if (autoData.getMaxPriceUserId().compareTo(0l) == 0 && autoData.getEntrustPriceUserId().compareTo(0l) == 0) {
                    autoData.setStatus(CarStatusEnum.ABORTIVE_AUCTION.value());
                } else {
                    //***判断是否个人车源
                    if ("1".equals(autoData.getSourceType())) {
                        autoData.setStatus(CarStatusEnum.BARGAIN_HANLDING.value());
                    } else {
                        //***判断最高价是否过委托价
                        BigDecimal maxPrice = autoData.getMaxPrice();
                        if (maxPrice.compareTo(autoData.getEntrustPrice()) < 0) {
                            maxPrice = autoData.getEntrustPrice();
                        }
                        //***判断最高价是否过保留价
                        if (maxPrice.compareTo(autoData.getReservePrice()) >= 0) {
                            autoData.setStatus(CarStatusEnum.WAITING_PAY.value());
                        } else {
                            autoData.setStatus(CarStatusEnum.ABORTIVE_AUCTION.value());
                        }
                    }
                }
                try {
                    auctionService.updateAuctionFinish(autoData);
                    autoData.setSuccess("1");
                } catch (Exception e) {
                    autoData.setSuccess("0");
                }
                redisAutoManager.updateAuto(autoData);
                // 如查存在最高出拍人
                if (autoData.getMaxPriceUserId().compareTo(0L) > 0){
                    //***推送
                    auctionService.sendPushAutoData(autoData);
                    // 成功拍车后给客户发送短信
                    sendMsgAuction(autoData.getMaxPriceUserId(),autoData.getAutoId());

                    Long managerId = autoData.getMaxPriceUserId();
                    setStoreOrderNotify(managerId);
                }
            }
            //System.out.println("1=="+JSONObject.toJSONString(autoData));
        }
        //System.out.println(LocalDateTime.now());
        return 1;
    }

    /**
     * @since 增加店铺车源角标
     * @param managerId
     */
    private void setStoreOrderNotify(Long managerId) {
        Notify notify = new Notify();
        if (Objects.isNull(redisManagerTemplate.get(managerId.toString()))) {
            notify.getOrder().setInsertStoreOrder(notify.getOrder().getInsertStoreOrder() + 1);
            setRedis(managerId, notify);
        } else {
            notify = getNotify(managerId);
            notify.getOrder().setInsertStoreOrder(notify.getOrder().getInsertStoreOrder() + 1);
            setRedis(managerId, notify);
        }
    }

    private void sendMsgAuction(Long userId,Long autoId){
        WtAppUser wtAppUser = wtAppUserService.findById(userId).getResult();
        String mobile = wtAppUser.getMobile();
        CarAuto carAuto = carAutoService.selectById(autoId);
        String msg = String.format(SMS_TEMPLATE, carAuto.getName());
        sendMsg(mobile,msg);
    }

    private void sendMsg(String mobile,String msg){
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Map map = Maps.newHashMap();
        map.put("mobile",mobile);
        map.put("content",msg);
        logger.info("消息内容："+ gson.toJson(map));
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate().exchange(
                RequestEntity
                        .post(URI.create(Constants.SENDSMS_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mobile),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        if (serviceResult.getBoolean("success")){
            logger.info("短信发送成功");
        } else{
            logger.info("短信发送失败");
        }
    }


    /**
     *
     * 客户关注某车辆即将开拍
     * @return
     */
    @RequestMapping( "/startAuctionViewed" )
    @ResponseBody
    public void startAuctionViewed() {
        List<WtAppUser> wtAppUserList = wtAppUserService.selectListByParam(Collections.singletonMap("status", AppUserStatusEnum.SIG_OK.value()));
        CarAppInfo carAppInfo = appInfoService.selectByType(BUYER);
        for (WtAppUser wtAppUser : wtAppUserList) {
            Map<String, Object> param = Maps.newHashMap();
            param.put("status",WAIT_AUCTION);
            param.put("date", new Date());
            List<CarCustomerViewedAuto> viewedAutoList = carCustomerViewedAutoService.selectUserViewList(param);
            for (CarCustomerViewedAuto viewedAuto : viewedAutoList) {
                String auctionStartContent = String.format(AUCTION_START_TEMPLATE, viewedAuto.getAutoInfoName());
                JPushUtil.sendAutoMsg(carAppInfo.getAppId(), new String[]{wtAppUser.getId() + ""},AUCTION_START_TITLE,auctionStartContent,viewedAuto.getAutoId() +"");
            }
        }
    }

    /**
     * 删除无效历史拍牌数据
     *
     * @return
     */
    @RequestMapping( "/deleteHisDataLog" )
    @ResponseBody
    public int deleteHisDataLog() {
        try {
            auctionLogService.deleteHisDataLog();
        } catch (Exception e) {
            logger.error("删除无效历史拍牌数据日志失败", e);
        }
        return 1;
    }

    /**
     * 个人数据-保存到redis 中
     */
    private void setRedis(Long managerId, Notify notify) {
        redisManagerTemplate.update(managerId.toString(), JSONObject.toJSONString(notify));
    }

    /**
     * 获取 redis 中的-个人数据
     */
    private Notify getNotify(Long managerId) {
        return JSONObject.toJavaObject(JSONObject.parseObject(redisManagerTemplate.get(managerId.toString())), Notify.class);
    }

}
