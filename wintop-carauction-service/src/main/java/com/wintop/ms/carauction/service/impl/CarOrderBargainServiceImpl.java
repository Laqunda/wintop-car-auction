package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.OrderStatusEnum;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarOrderBargainService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarOrderBargainServiceImpl implements ICarOrderBargainService {
    @Autowired
    private CarOrderBargainModel model;
    @Autowired
    private CarOrderModel orderModel;
    @Autowired
    private CarAutoModel autoModel;
    @Autowired
    private CarAutoLogModel autoLogModel;
    @Autowired
    private CarAuctionSettingModel auctionSettingModel;
    @Autowired
    private CarOrderLogModel orderLogModel;
    @Autowired
    private CarAutoAuctionModel autoAuctionModel;
    @Autowired
    private CarManagerUserModel managerUserModel;
    @Autowired
    private CarAuctionBidRecordModel bidRecordModel;
    @Autowired
    private CarRegionSettingModel regionSettingModel;
    private IdWorker idWorker = new IdWorker(10);
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarOrderBargain>> selectByExample(Map<String, Object> map) {
        List<CarOrderBargain> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarOrderBargain> selectById(Long id) {
        CarOrderBargain carOrderBargain = model.selectById(id);
        return new ServiceResult<>(true,carOrderBargain);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarOrderBargain carOrderBargain) {
        Integer count = model.insert(carOrderBargain);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarOrderBargain carOrderBargain) {
        Integer count = model.updateByIdSelective(carOrderBargain);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarOrderBargain carOrderBargain) {
        Integer count = model.updateById(carOrderBargain);
        return new ServiceResult<>(true,count);
    }
    @Transactional
    public Integer sureBargaining(JSONObject object,CarAuto auto){
        Integer result=0;
        Long carId=object.getLong("carId");
        String status=object.getString("status");
        Long managerId=object.getLong("managerId");
        //查询议价信息
        Map<String,Object> map=new HashMap<>();
        map.put("carId",carId);
        map.put("status","1");
        if (auto==null){
            auto=autoModel.selectByPrimaryKey(carId);
        }
        CarManagerUser managerUser=managerUserModel.selectByPrimaryKey(managerId);
        CarOrderBargain bargain=model.selectBargainByCarId(map);
        CarAutoAuction autoAuction = autoAuctionModel.selectByPrimaryKey(auto.getAutoAuctionId());
        if (bargain != null && managerUser!=null && autoAuction!=null) {
            if(autoAuction.getServicePrice()==null || autoAuction.getAgentPrice()==null){
                CarAuctionSetting auctionSetting = auctionSettingModel.selectByRegionId(auto.getRegionId());
                autoAuction.setServicePrice(auctionSetting.getServerFee());
                autoAuction.setAgentPrice(auctionSetting.getAgentFee());
            }
            //议价成功
            if ("1".equals(status)){
                //车辆状态更改 8成交-等待付款
                auto.setStatus("8");
                //4成交
                autoAuction.setStatus("4");
//                生成订单
                CarAuctionBidRecord bidRecord=bidRecordModel.selectMaxBidRecordByCarId(carId,auto.getAutoAuctionId());
                if (bidRecord==null){
                    return result;
                }

                Date date=new Date();
                CarOrder order = new CarOrder();
                order.setId(idWorker.nextId());
                order.setAuctionId(1l);
                order.setOrderNo(RandCodeUtil.getOrderNumber());
                order.setCarId(carId);
                order.setCustomerId(bargain.getCustomerId());
                order.setTransactionFee(object.getBigDecimal("bargainFee"));
                order.setServiceFee(autoAuction.getServicePrice());
                order.setStatus(OrderStatusEnum.WAITING_PAY.value());
                order.setLockFee(autoAuction.getReservePrice());
                order.setCreateTime(date);
                order.setAgentFee(autoAuction.getAgentPrice());
                order.setAmountFee(order.getTransactionFee().add(order.getServiceFee()).add(order.getAgentFee()));
                order.setAuctionBidRecordId(bidRecord.getId());
                order.setAutoAuctionId(auto.getAutoAuctionId());
                order.setPayEndTime(regionSettingModel.getBreachTime(date,auto.getRegionId(),"1"));
                orderModel.insert(order);
                //***生成订单日志
                CarOrderLog orderLog = new CarOrderLog();
                orderLog.setId(idWorker.nextId());
                orderLog.setOrderId(order.getId());
                orderLog.setStatus(OrderStatusEnum.WAITING_PAY.value());
                orderLog.setStatusCn(OrderStatusEnum.WAITING_PAY.getRemark());
                orderLog.setLogMsg(object.getString("msg"));
                orderLog.setCreateTime(date);
                orderLog.setCreatePerson(managerId);
                orderLog.setUserName(managerUser.getUserName());
                orderLog.setUserMobile(managerUser.getUserPhone());
                orderLogModel.insert(orderLog);
                //修改议价信息
                bargain.setOrderId(order.getId());
                //议价成功
                bargain.setStatus("2");
                //议价失败
            }else if ("2".equals(status)){
                //车辆状态更改 19流拍
                auto.setStatus("19");
                //3流拍
                autoAuction.setStatus("3");
                //修改议价信息 3议价失败
                bargain.setStatus("3");
            }else {
                return -1;
            }
            //修改车辆
            //**更新CarAuto状态
             autoModel.updateByPrimaryKeySelective(auto);
            //**更新CarAutoAuction状态
            //***,4成交，3流拍
            autoAuctionModel.updateByPrimaryKeySelective(autoAuction);
            //***增加车辆操作日志
            CarAutoLog autoLog = new CarAutoLog();
            autoLog.setId(idWorker.nextId());
            autoLog.setAutoId(carId);
            autoLog.setMsg(object.getString("msg"));
            autoLog.setStatus(auto.getStatus());
            autoLog.setTime(new Date());
            autoLog.setUserType("2");
            autoLog.setUserId(managerId);
            autoLog.setUserName(managerUser.getUserName());
            autoLog.setUserMobile(managerUser.getUserPhone());
            autoLogModel.insert(autoLog);
            /**
             * 修改议价信息表
             */
            bargain.setBargainFee(object.getBigDecimal("bargainFee"));
            bargain.setAuthManager(managerId);
            bargain.setAuthTime(new Date());
            bargain.setAuthMsg(object.getString("msg"));
            result=model.updateByIdSelective(bargain);
        }
        return result;
    }

}
