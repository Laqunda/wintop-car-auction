package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarAutoAuction;
import com.wintop.ms.carauction.service.ICarAutoAuctionService;
import com.wintop.ms.carauction.service.ICarAutoService;
import com.wintop.ms.carauction.service.TblAuctionLogService;
import com.wintop.ms.carauction.util.utils.RedisAutoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@Controller
//@RequestMapping("service/carAutoSchedule")
public class CarAutoSchedule {
    @Autowired
    private RedisAutoManager redisAutoManager;
    @Autowired
    private ICarAutoAuctionService auctionService;
    @Autowired
    private ICarAutoService carAutoService;
    @Autowired
    private TblAuctionLogService auctionLogService;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoSchedule.class);
    /***
     * 开发环境请将@Scheduled   注释掉
     * 每1000毫秒执行一次job方法
     */
    @RequestMapping("/dataJob")
    @ResponseBody
    public int RedisAutoDataJob(){
        //System.out.println("--------1------");
        List<RedisAutoData> dataList = redisAutoManager.getRedisAutoDataList(Constants.CAR_AUTO_AUCTION+"_*");
        long currentTime = System.currentTimeMillis();
        for(RedisAutoData autoData:dataList){
            //***到达开拍时间，更改状态
            if(currentTime-autoData.getAuctionStartTime()>=0 && CarStatusEnum.WAITING_AUCTION.value().equals(autoData.getStatus())){
                //***更新car_auto状态
                CarAuto carAuto = new CarAuto(autoData.getAutoId(),CarStatusEnum.AUCTIONING.value(),new Date());
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
            if(currentTime-autoData.getAuctionEndTime()>=3*60*60*1000l){
                redisAutoManager.delAuto(autoData.redisKey());
                continue;
            }
            //***超过10秒的无效数据，直接清除
            if(currentTime-autoData.getAuctionEndTime()>=10000l && !CarStatusEnum.AUCTIONING.value().equals(autoData.getStatus()) && "1".equals(autoData.getSuccess())){
                redisAutoManager.delAuto(autoData.redisKey());
                continue;
            }
            //***同步失败继续同步
            if("0".equals(autoData.getSuccess())){
                try{
                    auctionService.updateAuctionFinish(autoData);
                    autoData.setSuccess("1");
                } catch (Exception e){
                    autoData.setSuccess("0");
                }
                continue;
            }
            if(currentTime-autoData.getAuctionEndTime()>=0l && CarStatusEnum.AUCTIONING.value().equals(autoData.getStatus())){
                //***无人出价并且无委托价直接流拍
                if(autoData.getMaxPriceUserId().compareTo(0l)==0 && autoData.getEntrustPriceUserId().compareTo(0l)==0){
                    autoData.setStatus(CarStatusEnum.ABORTIVE_AUCTION.value());
                }else{
                    //***判断是否个人车源
                    if("1".equals(autoData.getSourceType())){
                        autoData.setStatus(CarStatusEnum.BARGAIN_HANLDING.value());
                    }else{
                        //***判断最高价是否过委托价
                        BigDecimal maxPrice = autoData.getMaxPrice();
                        if(maxPrice.compareTo(autoData.getEntrustPrice())<0){
                            maxPrice=autoData.getEntrustPrice();
                        }
                        //***判断最高价是否过保留价
                        if(maxPrice.compareTo(autoData.getReservePrice())>=0){
                            autoData.setStatus(CarStatusEnum.WAITING_PAY.value());
                        }else{
                            autoData.setStatus(CarStatusEnum.ABORTIVE_AUCTION.value());
                        }
                    }
                }
                try{
                    auctionService.updateAuctionFinish(autoData);
                    autoData.setSuccess("1");
                } catch (Exception e){
                    autoData.setSuccess("0");
                }
                redisAutoManager.updateAuto(autoData);
                //***推送
                auctionService.sendPushAutoData(autoData);
            }
            //System.out.println("1=="+JSONObject.toJSONString(autoData));
        }
        //System.out.println(LocalDateTime.now());
        return 1;
    }

    /**
     * 删除无效历史拍牌数据
     * @return
     */
    @RequestMapping("/deleteHisDataLog")
    @ResponseBody
    public int deleteHisDataLog(){
        try{
            auctionLogService.deleteHisDataLog();
        } catch (Exception e){
            logger.error("删除无效历史拍牌数据日志失败",e);
        }
        return 1;
    }

}
