package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarAutoLogModel;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RedisAutoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理发拍的车详情有关问题接口
 */
@RestController
@RequestMapping("/service/handleDispute")
public class HandleDisputeApi {
    private static final Logger logger = LoggerFactory.getLogger(HandleDisputeApi.class);
    private static final String TRANSFER_OFFLINE = "1";
    private static final String AUDIT = "2";
    private static final String OFFLINE = "2";
    @Resource
    private ICarAutoService iCarAutoService;
    @Resource
    private ICarAutoAuctionService iCarAutoAuctionService;
    @Resource
    private ICarOrderService iCarOrderService;
    @Resource
    private ICarAutoTransferService iCarAutoTransferService;
    @Resource
    private ICarAutoLogService carAutoLogService;
    @Autowired
    private RedisAutoManager redisAutoManager;
    @Autowired
    private ICarManagerRoleService roleService;
    @Autowired
    private ICarManagerUserService userService;
    /**
     * 申请撤回车辆接口
     */
    @RequestMapping(value = "/insertRevocationCar",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> insertRevocationCar(@RequestBody JSONObject obj){
        logger.info("申请撤回车辆");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long userId = obj.getLong("userId");
            Long id = obj.getLong("carId");
            String msg = obj.getString("msg");
           CarAuto carAuto1 = iCarAutoService.selectByPrimaryKey(id).getResult();
            //**,2只能操作自己的数据
            CarManagerRole managerRole = roleService.selectByUserId(userId);
            if("2".equals(managerRole.getWriteType())){
                if(userId.compareTo(carAuto1.getCreateUser())!=0){
                    result.setError(ResultCode.NO_ALLOW_UPDATE.strValue(),ResultCode.NO_ALLOW_UPDATE.getRemark());
                    return result;
                }
            }
            Integer count = 0;
            CarAuto carAuto = new CarAuto();
            carAuto.setId(id);
            carAuto.setLogMsg(msg);
            if(carAuto1 != null ){
                //当开拍时间大于当前时间并且小于五分钟的是，不能申请撤回的。
                if(carAuto1.getAuctionStartTime() != null &&
                        carAuto1.getAuctionStartTime().getTime()>System.currentTimeMillis() &&
                        (carAuto1.getAuctionStartTime().getTime() - System.currentTimeMillis() < 300000L)){
                    result.setError("0","该车辆距开拍时间不到5分钟不能申请撤回");
                    return result;
                }else{
                    if("7".equals(carAuto1.getStatus())){
                        result.setError("0","该车辆正在竞拍中不能申请撤回");
                        return result;
                    }
                    if("2".equals(carAuto1.getStatus())){
                        carAuto.setStatus("1");
                    }
                    if("5".equals(carAuto1.getStatus()) || "6".equals(carAuto1.getStatus())){
                        carAuto.setStatus("4");
                    }
                    count = iCarAutoService.updateByPrimaryKeySelective(carAuto).getResult();
                    redisAutoManager.delAuto(Constants.CAR_AUTO_AUCTION+"_"+id);
                }
            }else{
                result.setError("0","查询不到相关车辆信息");
                return result;
            }
          if(count != null && count != 0){
              map.put("count",count);
          }else{
              map.put("count",0);
          }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("申请撤回车辆失败",e);
        }
        return result;
    }

    /**
     * 线上转线下渠道
     */
    @RequestMapping(value = "/transferChannelCar",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> transferChannelCar(@RequestBody JSONObject obj){
        logger.info("线上转线下渠道");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long userId = obj.getLong("userId");
            Long id = obj.getLong("carId");
            CarAuto carAuto1 = iCarAutoService.selectByPrimaryKey(id).getResult();
            //**,2只能操作自己的数据
            CarManagerRole managerRole = roleService.selectByUserId(userId);
            if("2".equals(managerRole.getWriteType())){
                if(userId.compareTo(carAuto1.getCreateUser())!=0){
                    result.setError(ResultCode.NO_ALLOW_UPDATE.strValue(),ResultCode.NO_ALLOW_UPDATE.getRemark());
                    return result;
                }
            }
            Integer count = 0;
            Integer autoCount = 0;
            //更改销售渠道类型
            CarAutoAuction carAutoAuction = new CarAutoAuction();
            carAutoAuction.setId(carAuto1.getAutoAuctionId());
            carAutoAuction.setAuctionType(OFFLINE);
            carAutoAuction.setAuctionStartTime(null);
            //更新车辆信息
            CarAuto carAuto = new CarAuto();
            carAuto.setId(id);
            carAuto.setStatus(AUDIT);
            carAuto.setTransferFlag(TRANSFER_OFFLINE);
            if(carAuto1 != null ){
                //
                autoCount =  iCarAutoService.updateByIdSelective(carAuto);
                // 修改已转换标识
                count = iCarAutoAuctionService.updateByPrimaryKeySelective(carAutoAuction).getResult();
                // 日志信息记录
                CarManagerUser user = userService.selectByPrimaryKey(userId, false);
                CarAutoLog carAutoLog = new CarAutoLog();
                IdWorker idWorker=new IdWorker(10);
                carAutoLog.setId(idWorker.nextId());
                carAutoLog.setAutoId(id);
                carAutoLog.setMsg("线上拍转现场拍申请");
                carAutoLog.setUserMobile(user.getUserKey());
                carAutoLog.setUserName(user.getUserName());
                carAutoLog.setStatus(AUDIT);
                carAutoLogService.insert(carAutoLog);
            }else{
                result.setError("0","查询不到相关车辆信息");
                return result;
            }
            if((count != null && count != 0) && (autoCount != null && autoCount != 0)){
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("线上转线下渠道失败",e);
        }
        return result;
    }

    /**
     * 申请二拍接口
     */
    @RequestMapping(value = "/insertForTwo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> insertForTwo(@RequestBody JSONObject obj){
        logger.info("申请二拍");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long userId = obj.getLong("userId");
            Long carId = obj.getLong("carId");
            CarAuto carAuto = new CarAuto();
            carAuto.setId(carId);
            carAuto.setStatus("1");
           CarAuto carAuto1 = iCarAutoService.selectByPrimaryKey(carId).getResult();
            Integer count = 0;
            if(carAuto1 != null && "19".equals(carAuto1.getStatus())){
                count =  iCarAutoService.updateByPrimaryKeySelective(carAuto).getResult();
           }else{
                result.setError("0","非流拍状态下的车辆不允许申请二拍");
                return result;
            }
            map.put("count",count);
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("申请二拍失败",e);
        }
        return result;
    }

    /**
     * 申请争议接口
     */
    @RequestMapping(value = "/insertApplicationDispute",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> insertApplicationDispute(@RequestBody JSONObject obj){
        logger.info("申请争议");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long userId = obj.getLong("userId");
            Long carId = obj.getLong("carId");
            String disputeReason = obj.getString("disputeReason");
            Long disputeId = obj.getLong("disputeId");
            CarAuto carAuto = new CarAuto();
            carAuto.setId(carId);
            carAuto.setStatus("14");
            Integer count =  iCarAutoService.updateByPrimaryKeySelective(carAuto).getResult();
            if(count != null && count != 0){
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("申请争议失败",e);
        }
        return result;
    }

    /**
     * 确定过户接口
     */
    @RequestMapping(value = "/saveDetermine",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveDetermine(@RequestBody JSONObject obj){
        logger.info("确定过户");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long orderId = obj.getLong("orderId");
            CarOrder carOrder = iCarOrderService.selectById(orderId);
            Long autoId = 0L;
            if(carOrder != null){
               autoId = carOrder.getCarId();
            }
            Date handleTime = obj.getDate("handleTime");
            String photo = obj.getString("photo");
            Long userId = obj.getLong("userId");
            CarAutoTransfer vo = iCarAutoTransferService.selectByOrderId(orderId,autoId).getResult();
            if(vo != null && "1".equals(vo.getStatus())){
                result.setError("0","该订单车辆已确定过户事宜");
                map.put("count",0);
                return result;
            }else if(vo != null){
                CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
                carAutoTransfer.setId(vo.getId());
                carAutoTransfer.setOrderId(orderId);
                carAutoTransfer.setAutoId(autoId);
                carAutoTransfer.setCommissionPhoto(photo);
                carAutoTransfer.setCreateTime(handleTime);
                carAutoTransfer.setStatus("1");
                Integer count =  iCarAutoTransferService.insertDetermine(carAutoTransfer).getResult();
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("确定过户失败",e);
        }
        return result;
    }


    /**
     * 确定收款接口
     */
    @RequestMapping(value = "/saveGathering",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveGathering(@RequestBody JSONObject obj){
        logger.info("确定收款");
        ServiceResult<Map<String,Object>> result = new ServiceResult<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long carId = obj.getLong("carId");
            CarAuto carAuto = new CarAuto();
            carAuto.setId(carId);
            carAuto.setStatus("11");
            Integer count =  iCarAutoService.updateByPrimaryKeySelective(carAuto).getResult();
            if(count != null && count != 0){
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            map.put("count",0);
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("确定收款失败",e);
        }
        return result;
    }
}
