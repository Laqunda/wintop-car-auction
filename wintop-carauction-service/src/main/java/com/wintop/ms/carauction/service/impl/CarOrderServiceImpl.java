package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.OrderStatusEnum;
import com.wintop.ms.carauction.core.config.TransferStatusEnum;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarOrderService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarOrderServiceImpl implements ICarOrderService {
    @Autowired
    private CarOrderModel orderModel;
    @Autowired
    private CarCustomerBreachModel breachModel;
    @Autowired
    private CarFinancePayLogModel financePayLogModel;
    @Autowired
    private CarOrderLogModel orderLogModel;
    @Autowired
    private CarManagerUserModel managerUserModel;
    @Autowired
    private CarAutoModel autoModel;
    @Autowired
    private CarAutoLogModel autoLogModel;
    @Autowired
    private CarAutoTransferModel transferModel;
    @Autowired
    private CarAutoTransferLogModel transferLogModel;

    private IdWorker idWorker = new IdWorker(10);

    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = orderModel.countByExample(map);
        return count;
    }

    @Override
    public List<CarOrder> selectByExample(Map<String, Object> map) {
        List<CarOrder> list = orderModel.selectByExample(map);
        return list;
    }

    @Override
    public CarOrder selectById(Long id) {
        CarOrder carOrder = orderModel.selectById(id);
        return carOrder;
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = orderModel.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    /**
     * 线上竞拍订单
     * @param carOrder
     * @return
     */
    @Override
    @Transactional
    public Integer insert(CarOrder carOrder) {
        Integer count = orderModel.insert(carOrder);
        return count;
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarOrder carOrder) {
        Integer count = orderModel.updateByIdSelective(carOrder);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarOrder carOrder) {
        Integer count = orderModel.updateById(carOrder);
        return new ServiceResult<>(true,count);
    }

    /**
     * 查询我的所有订单
     * @param map
     * @return
     */
    @Override
    public List<CarOrder> selectUserOrderList(Map<String,Object> map){
        return orderModel.selectUserOrderList(map);
    }

    /**
     * 查询我的所有订单数量
     * @param map
     * @return
     */
    @Override
    public int selectUserOrderCount(Map<String,Object> map){
        return orderModel.selectUserOrderCount(map);
    }

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    @Override
    public CarOrder selectOrderDetail(Long orderId){
        CarOrder order = orderModel.selectOrderDetail(orderId);
        order.setBreachType("0");
        //***查询违约信息
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        CarCustomerBreach carBreach = breachModel.queryNewBreachInfo(map);
        if(carBreach != null){
            order.setBreachType(carBreach.getBreachObjType());
        }
        return order;
    }

    /**
     * 根据条件查询车辆详情
     */
    @Override
    public CarOrder queryCarDetail(Map<String,Object> map){
        return orderModel.queryCarDetail(map);
    }

    /**
     * 查询历史车辆列表
     */
    @Override
    public List<CarOrder> queryHistoryCar(Map<String,Object> map){
        return orderModel.queryHistoryCar(map);
    }


    /**
     * 查询历史车辆数据
     */
    @Override
    public int queryHistoryCarCount(Map<String,Object> map){
        return orderModel.queryHistoryCarCount(map);
    }
    /**
     * 根据条件查询历史车辆详情
     */
    @Override
    public CarOrder queryHistoryCarDetail(Map<String,Object> map){
        return orderModel.queryHistoryCarDetail(map);
    }

    /**
     * 查询订单基本信息
     * @param id
     * @return
     */
    public CarOrder queryOrderBaseInfo(Long id){
        return orderModel.queryOrderBaseInfo(id);
    }

    /**
     * 线下订单支付确认
     * @param financePayLog
     * @return
     */
    @Transactional
    public Integer saveOfflineOrderPay(CarOrder carOrder,CarFinancePayLog financePayLog){
        CarManagerUser managerUser = managerUserModel.selectByPrimaryKey(financePayLog.getCreatePerson());
        Date date = new Date();
        //***增加资金流水
        financePayLog.setId(idWorker.nextId());
        financePayLog.setType("3");
        financePayLog.setOrderNo(carOrder.getOrderNo());
        financePayLog.setStatus("1");
        financePayLog.setPayTime(date);
        financePayLog.setUserId(carOrder.getCustomerId());
        financePayLog.setPayType("2");
        financePayLog.setCreateTime(date);
        financePayLog.setCreatePersonType("2");
        int count = financePayLogModel.insert(financePayLog);
        //***增加订单操作日志
        CarOrderLog orderLog = new CarOrderLog();
        orderLog.setId(idWorker.nextId());
        orderLog.setOrderId(financePayLog.getOrderId());
        orderLog.setStatus(OrderStatusEnum.TRANSFER_HANLDING.value());
        orderLog.setLogMsg(financePayLog.getRemark());
        orderLog.setCreateTime(date);
        orderLog.setCreatePerson(managerUser.getId());
        orderLog.setUserName(managerUser.getUserName());
        orderLog.setUserMobile(managerUser.getUserPhone());
        orderLogModel.insert(orderLog);
        //***更改订单状态
        CarOrder order = new CarOrder(carOrder.getId(),OrderStatusEnum.TRANSFER_HANLDING.value());
        order.setPayFee(financePayLog.getPayFee());
        order.setPayTime(date);
        orderModel.updateByIdSelective(order);
        //***修改车辆状态
        CarAuto auto = new CarAuto(carOrder.getCarId(),CarStatusEnum.PAY_COMPLETE.value(),new Date());
        autoModel.updateByPrimaryKeySelective(auto);
        //***增加车辆操作日志
        CarAutoLog autoLog = new CarAutoLog();
        autoLog.setId(idWorker.nextId());
        autoLog.setAutoId(carOrder.getCarId());
        autoLog.setMsg(financePayLog.getRemark());
        autoLog.setStatus(auto.getStatus());
        autoLog.setTime(date);
        autoLog.setUserType("2");
        autoLog.setUserId(managerUser.getId());
        autoLog.setUserName(managerUser.getUserName());
        autoLog.setUserMobile(managerUser.getUserPhone());
        autoLogModel.insert(autoLog);
        return count;
    }

    /**
     * 查询需要支付违约金订单列表
     * @param map
     * @return
     */
    public List<CarOrder> selectBreachPayOrderList(Map<String,Object> map){
        return orderModel.selectBreachPayOrderList(map);
    }

    /**
     * 查询需要支付违约金订单数量
     * @param map
     * @return
     */
    public int selectBreachPayOrderCount(Map<String,Object> map){
        return orderModel.selectBreachPayOrderCount(map);
    }

    /**
     * 查询手续回传待确认订单列表
     * @param map
     * @return
     */
    public List<CarOrder> selectProcedurePassbackOrderList(Map<String,Object> map){
        return orderModel.selectProcedurePassbackOrderList(map);
    }

    /**
     * 查询手续回传待确认订单数量
     * @param map
     * @return
     */
    public int selectProcedurePassbackOrderCount(Map<String,Object> map){
        return orderModel.selectProcedurePassbackOrderCount(map);
    }

    /**
     * 订单违约金支付确认
     * @param financePayLog
     * @return
     */
    @Transactional
    public int saveBreachOrderPay(CarOrder carOrder,CarFinancePayLog financePayLog,CarCustomerBreach breach){
        CarManagerUser managerUser = managerUserModel.selectByPrimaryKey(financePayLog.getCreatePerson());
        Date date = new Date();
        //***增加资金流水
        financePayLog.setId(idWorker.nextId());
        //**2，违约款
        financePayLog.setType("2");
        financePayLog.setOrderNo(carOrder.getOrderNo());
        financePayLog.setStatus("1");
        financePayLog.setPayTime(date);
        financePayLog.setUserId(carOrder.getCustomerId());
        financePayLog.setPayType("2");
        financePayLog.setCreateTime(date);
        financePayLog.setCreatePersonType("2");
        int count = financePayLogModel.insert(financePayLog);
        //***增加订单操作日志
        CarOrderLog orderLog = new CarOrderLog();
        orderLog.setId(idWorker.nextId());
        orderLog.setOrderId(financePayLog.getOrderId());
        if("3".equals(breach.getStatus())){
            orderLog.setStatus(OrderStatusEnum.EXCHANGE_CLOSED.value());
            //***更改订单状态为交易关闭
            CarOrder order = new CarOrder(carOrder.getId(),OrderStatusEnum.EXCHANGE_CLOSED.value());
            orderModel.updateByIdSelective(order);
        }else{
            orderLog.setStatus(breach.getBreachOrderStatus());
            //***更改订单状态为继续成交
            CarOrder order = new CarOrder(carOrder.getId(),breach.getBreachOrderStatus());
            orderModel.updateByIdSelective(order);
        }
        orderLog.setLogMsg(financePayLog.getRemark());
        orderLog.setCreateTime(date);
        orderLog.setCreatePerson(managerUser.getId());
        orderLog.setUserName(managerUser.getUserName());
        orderLog.setUserMobile(managerUser.getUserPhone());
        orderLogModel.insert(orderLog);
        breach.setPayTime(date);
        breach.setPayAuthTime(date);
        breach.setPayAuthFile(financePayLog.getPayEvidence());
        breach.setPayAuthManager(managerUser.getId());
        breach.setPayAuthMsg(financePayLog.getRemark());
        breachModel.updateByPrimaryKeySelective(breach);
        return count;
    }

    /**
     * 审核手续回传待确认
     * @return
     */
    @Transactional
    public int saveProcedurePassback(CarAutoTransfer autoTransfer){
        CarManagerUser managerUser = managerUserModel.selectByPrimaryKey(autoTransfer.getAuthManager());
        //***更新手续信息
        int count = transferModel.updateByOrderId(autoTransfer);
        //***新增-CarAutoTransferLog
        CarAutoTransfer transfer = transferModel.selectByOrderId(autoTransfer.getOrderId(),autoTransfer.getAutoId());
        CarAutoTransferLog transferLog = new CarAutoTransferLog();
        transferLog.setId(idWorker.nextId());
        transferLog.setTransferId(transfer.getId());
        transferLog.setType(autoTransfer.getStatus());
        transferLog.setTime(autoTransfer.getAuthTime());
        transferLog.setHandleTime(autoTransfer.getAuthTime());
        transferLog.setHandlePerson(autoTransfer.getAuthManager());
        transferLog.setRemark(autoTransfer.getAuthMsg());
        transferLogModel.insert(transferLog);
        if(TransferStatusEnum.AGENT_COMPLETE.value().equals(autoTransfer.getStatus())){
            //***更改订单状态
            CarOrder order = new CarOrder(autoTransfer.getOrderId(), OrderStatusEnum.EXCHANGE_COMPLETE.value());
            orderModel.updateByIdSelective(order);
            //***增加订单操作日志
            CarOrderLog orderLog = new CarOrderLog();
            orderLog.setId(idWorker.nextId());
            orderLog.setOrderId(autoTransfer.getOrderId());
            orderLog.setStatus(OrderStatusEnum.EXCHANGE_COMPLETE.value());
            orderLog.setLogMsg(autoTransfer.getAuthMsg());
            orderLog.setCreateTime(autoTransfer.getAuthTime());
            orderLog.setCreatePerson(managerUser.getId());
            orderLog.setUserName(managerUser.getUserName());
            orderLog.setUserMobile(managerUser.getUserPhone());
            orderLogModel.insert(orderLog);
            //***更改车辆状态
            CarAuto auto = new CarAuto(autoTransfer.getAutoId(), CarStatusEnum.EXCHANGE_COMPLETE.value(),new Date());
            autoModel.updateByPrimaryKeySelective(auto);
            //***增加车辆操作日志
            CarAutoLog autoLog = new CarAutoLog();
            autoLog.setId(idWorker.nextId());
            autoLog.setId(autoTransfer.getAutoId());
            autoLog.setMsg(autoTransfer.getAuthMsg());
            autoLog.setStatus(CarStatusEnum.EXCHANGE_COMPLETE.value());
            autoLog.setTime(autoTransfer.getAuthTime());
            autoLog.setUserType("2");
            autoLog.setUserId(managerUser.getId());
            autoLog.setUserName(managerUser.getUserName());
            autoLog.setUserMobile(managerUser.getUserPhone());
            autoLogModel.insert(autoLog);
        }
        return count;
    }

    @Override
    public CarOrder selectOrderPrintInfo(Long id) {
        return orderModel.selectOrderPrintInfo(id);
    }

    @Override
    public int updateOrderUser(Long id, Long customerId) {
        return orderModel.updateOrderUser(id, customerId);
    }
}
