package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.OrderStatusEnum;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarAutoAuctionService;
import com.wintop.ms.carauction.service.ICarCustomerBreachService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户违约信息
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerBreachService")
public class CarCustomerBreachServiceImpl implements ICarCustomerBreachService {
    @Resource
    private CarCustomerBreachModel customerBreachModel;
    @Resource
    private CarOrderModel carOrderModel;
    @Resource
    private CarManagerUserModel managerUserModel;
    @Resource
    private CarAutoModel carAutoModel;
    @Resource
    private CarAutoTransferModel transferModel;
    @Autowired
    private CarAutoLogModel autoLogModel;
    @Autowired
    private CarOrderLogModel orderLogModel;
    @Resource
    private  CarAutoTransferLogModel transferLogModel;
    @Autowired
    private ICarAutoAuctionService carAutoAuctionService;
    private IdWorker idWorker = new IdWorker(10);

    @Override
    public CarCustomerBreach selectByPrimaryKey(Long id) {
        CarCustomerBreach customerBreach = customerBreachModel.selectByPrimaryKey(id);
        return customerBreach;
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        Integer count = customerBreachModel.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public Integer updateByPrimaryKeySelective(CarCustomerBreach record) {
        Integer count = customerBreachModel.updateByPrimaryKeySelective(record);
        return count;
    }

    @Override
    public Integer updateByPrimaryKey(CarCustomerBreach record) {
        Integer count = customerBreachModel.updateByPrimaryKey(record);
        return count;
    }

    @Override
    public Integer insert(CarCustomerBreach record) {
        Integer count = customerBreachModel.insert(record);
        return count;
    }

    /**
     * 查询订单争议列表
     * @param map
     * @return
     */
    @Override
    public List<CarCustomerBreach> selectBreachList(Map<String,Object> map){
        return customerBreachModel.selectBreachList(map);
    }

    /**
     * 查询订单违约信息
     * @param map
     * @return
     */
    public CarCustomerBreach queryNewBreachInfo(Map<String,Object> map){
        return customerBreachModel.queryNewBreachInfo(map);
    }
    /**
     * 申请争议
     *@Author:zhangzijuan
     *@date 2018/3/27
     *@param:
     */
    @Transactional
    public Integer applyBreach(JSONObject object){
        Integer result=0;
        Long orderId=object.getLong("orderId");
        Long managerId=object.getLong("managerId");
        String msg=object.getString("msg");
        CarManagerUser user=managerUserModel.selectByPrimaryKey(managerId);
        CarOrder order=carOrderModel.queryOrderBaseInfo(orderId);
        CarAuto auto=carAutoModel.selectByPrimaryKey(order.getCarId());
        //判断订单状态不是 4争议处理中  车辆状态 不是14争议处理中
        boolean flag=order!=null && user!=null && auto!=null && !"4".equals(order.getStatus()) && !"14".equals(auto.getStatus());
        if (flag){
            //1.保存争议信息
            CarCustomerBreach breach=new CarCustomerBreach();
            breach.setId(idWorker.nextId());
            breach.setInitiator(managerId);
            breach.setBreachObjType(object.getString("breachObjType"));
            breach.setInitiatCn(user.getUserName());
            breach.setInitiatMobile(user.getUserPhone());
            breach.setInitiatTime(new Date());
            breach.setInitiatMsg(msg);
            breach.setStatus("1");
            breach.setOrderId(orderId);
            breach.setBreachSource("3");
            breach.setAutoId(order.getCarId());
            //2.保存申请争议前的订单、车辆、过户状态
            breach.setBreachAutoStatus(auto.getStatus());
            breach.setBreachOrderStatus(order.getStatus());

            //3.修改车辆状态 14争议处理中
            auto.setStatus("14");
            auto.setUpdateTime(new Date());
            carAutoModel.updateByPrimaryKeySelective(auto);
            carAutoAuctionService.updateRedisAutoData(auto.getId());

            //4.保存车辆的轨迹日志
            CarAutoLog autoLog=new CarAutoLog();
            autoLog.setId(idWorker.nextId());
            autoLog.setAutoId(auto.getId());
            autoLog.setMsg(msg);
            autoLog.setStatus(auto.getStatus());
            autoLog.setUserType("2");
            autoLog.setUserId(user.getId());
            autoLog.setUserMobile(user.getUserPhone());
            autoLog.setUserName(user.getUserName());
            autoLog.setTime(new Date());
            autoLogModel.insert(autoLog);

//           5.修改订单状态 4争议处理中
            order.setStatus("4");
            carOrderModel.updateByIdSelective(order);
            //6.保存订单轨迹
            CarOrderLog orderLog=new CarOrderLog();
            orderLog.setId(idWorker.nextId());
            orderLog.setOrderId(order.getId());
            orderLog.setStatus(order.getStatus());
            orderLog.setLogMsg(msg);
            orderLog.setCreateTime(new Date());
            orderLog.setCreatePerson(managerId);
            orderLog.setUserMobile(user.getUserPhone());
            orderLog.setUserName(user.getUserName());
            orderLogModel.insert(orderLog);

            //7.如果是过户的订单，保存过户的状态
            CarAutoTransfer transfer=transferModel.selectByOrderId(order.getId(),auto.getId());
            if (transfer!=null){
                breach.setBreachTransferStatus(transfer.getStatus());
                //8.修改过户状态 9争议处理中
                transfer.setStatus("9");
                transferModel.updateByPrimaryKeySelective(transfer);
                //9.保存日志
                CarAutoTransferLog transferLog=new CarAutoTransferLog();
                transferLog.setId(idWorker.nextId());
                transferLog.setTransferId(transfer.getId());
                transferLog.setType(transfer.getStatus());
                transferLog.setTime(new Date());
                transferLog.setHandleTime(new Date());
                transferLog.setRemark(msg);
                transferLog.setHandlePerson(managerId);
                transferLogModel.insertSelective(transferLog);
            }

            //保存争议信息
            result=customerBreachModel.insert(breach);
        }
        return result;
    }

    /**
     * 争议审核
     * @param object
     * @return
     */
    @Transactional
    public int breachApprove(JSONObject object){
        BigDecimal amount = object.getBigDecimal("amount");
        Long id = object.getLong("breachId");
        CarCustomerBreach breach = JSONObject.toJavaObject(object,CarCustomerBreach.class);
        breach.setId(id);
        CarManagerUser managerUser = managerUserModel.selectByPrimaryKey(breach.getInitiatAuthManager());
        CarCustomerBreach newBreach = customerBreachModel.selectByPrimaryKey(breach.getId());
        if(!"1".equals(newBreach.getStatus())){
            return -1;
        }
        Date date = new Date();
        //***增加车辆操作日志
        CarAutoLog autoLog = new CarAutoLog();
        autoLog.setId(idWorker.nextId());
        autoLog.setAutoId(newBreach.getAutoId());
        autoLog.setTime(date);
        autoLog.setUserType("2");
        autoLog.setUserId(managerUser.getId());
        autoLog.setUserName(managerUser.getUserName());
        autoLog.setUserMobile(managerUser.getUserPhone());
        autoLog.setMsg(breach.getInitiatAuthMsg());
        //***增加订单轨迹
        CarOrderLog orderLog = new CarOrderLog();
        orderLog.setId(idWorker.nextId());
        orderLog.setOrderId(breach.getOrderId());
        orderLog.setCreateTime(date);
        orderLog.setCreatePerson(managerUser.getId());
        orderLog.setUserName(managerUser.getUserName());
        orderLog.setUserMobile(managerUser.getUserPhone());
        orderLog.setLogMsg(breach.getInitiatAuthMsg());
//        平退 、买家违约--不需要支付违约金、卖家赔付
        if("2".equals(breach.getStatus()) || "4".equals(breach.getStatus()) || "6".equals(breach.getStatus())){
            //**2.平退（退车款、车辆状态更改为流拍，订单状态更改为交易关闭）
            CarAuto carAuto = new CarAuto(newBreach.getAutoId(),CarStatusEnum.ABORTIVE_AUCTION.value(),date);
            carAutoModel.updateByPrimaryKeySelective(carAuto);
            autoLog.setStatus(CarStatusEnum.ABORTIVE_AUCTION.value());
            CarOrder carOrder = new CarOrder(newBreach.getOrderId(),OrderStatusEnum.EXCHANGE_CLOSED.value());
            carOrderModel.updateByIdSelective(carOrder);
            orderLog.setStatus(OrderStatusEnum.EXCHANGE_CLOSED.value());
        } else if("3".equals(breach.getStatus())){
            //**3.买家违约-需支付违约金（车辆状态更改为流拍，订单状态更改为违约金支付确认中）
            CarAuto carAuto = new CarAuto(newBreach.getAutoId(),CarStatusEnum.ABORTIVE_AUCTION.value(),date);
            carAutoModel.updateByPrimaryKeySelective(carAuto);
            autoLog.setStatus(CarStatusEnum.ABORTIVE_AUCTION.value());
            CarOrder carOrder = new CarOrder(newBreach.getOrderId(),OrderStatusEnum.BREAK_FEE_AUDITOR.value());
            carOrderModel.updateByIdSelective(carOrder);
            orderLog.setStatus(OrderStatusEnum.BREAK_FEE_AUDITOR.value());
        }
//        else if("4".equals(breach.getStatus())){
//            //***4,买家违约--不需要支付违约金（车辆继续成交）
//            CarAuto carAuto = new CarAuto(newBreach.getAutoId(),newBreach.getBreachAutoStatus(),date);
//            carAutoModel.updateByPrimaryKeySelective(carAuto);
//            autoLog.setStatus(newBreach.getBreachAutoStatus());
//            CarOrder carOrder = new CarOrder(newBreach.getOrderId(),newBreach.getBreachOrderStatus());
//            carOrderModel.updateByIdSelective(carOrder);
//            orderLog.setStatus(newBreach.getBreachOrderStatus());
//        }
        else if("5".equals(breach.getStatus())){
            //***5争议议价--修改待付车款（订单状态变为等待支付，车辆状态变为成交-等待付款）
            CarAuto carAuto = new CarAuto(newBreach.getAutoId(),CarStatusEnum.WAITING_PAY.value(),date);
            carAutoModel.updateByPrimaryKeySelective(carAuto);
            autoLog.setStatus(CarStatusEnum.WAITING_PAY.value());
//            CarOrder carOrder = new CarOrder(newBreach.getOrderId(),OrderStatusEnum.WAITING_PAY.value());
            CarOrder carOrder=carOrderModel.queryOrderBaseInfo(newBreach.getOrderId());
            carOrder.setBargainFee(carOrder.getTransactionFee());
            carOrder.setTransactionFee(amount);
            carOrder.setAmountFee(carOrder.getTransactionFee().add(carOrder.getServiceFee()).add(carOrder.getAgentFee()));
            carOrder.setStatus(OrderStatusEnum.WAITING_PAY.value());
            carOrderModel.updateByIdSelective(carOrder);
            orderLog.setStatus(OrderStatusEnum.WAITING_PAY.value());
        }
//        else if("6".equals(breach.getStatus())){
//            //***6,卖家赔付（车辆继续成交）
//            CarAuto carAuto = new CarAuto(newBreach.getAutoId(),newBreach.getBreachAutoStatus(),new Date());
//            carAutoModel.updateByPrimaryKeySelective(carAuto);
//            autoLog.setStatus(newBreach.getBreachAutoStatus());
//            //***订单状态改为违约待支付
//            CarOrder carOrder = new CarOrder(newBreach.getOrderId(),OrderStatusEnum.BREAK_FEE_AUDITOR.value());
//            carOrderModel.updateByIdSelective(carOrder);
//            orderLog.setStatus(newBreach.getBreachOrderStatus());
//        }
        autoLogModel.insert(autoLog);
        orderLogModel.insert(orderLog);
        breach.setInitiatAuthTime(date);
        if("36".contains(breach.getStatus())){
            breach.setMoney(amount);
        }
        return customerBreachModel.updateByPrimaryKeySelective(breach);
    }
}