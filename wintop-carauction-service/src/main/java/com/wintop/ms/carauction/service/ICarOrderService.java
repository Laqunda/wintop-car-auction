package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoTransfer;
import com.wintop.ms.carauction.entity.CarCustomerBreach;
import com.wintop.ms.carauction.entity.CarFinancePayLog;
import com.wintop.ms.carauction.entity.CarOrder;

import java.util.List;
import java.util.Map;

public interface ICarOrderService {
    /**
     * 根据条件查询记录总数
     */
    Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarOrder> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarOrder selectById(Long id);

    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer>  deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Integer  insert(CarOrder carOrder);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Integer>  updateByIdSelective(CarOrder carOrder);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer>  updateById(CarOrder carOrder);

    /**
     * 查询我的所有订单
     * @param map
     * @return
     */
    List<CarOrder> selectUserOrderList(Map<String,Object> map);

    /**
     * 查询我的所有订单数量
     * @param map
     * @return
     */
    public int selectUserOrderCount(Map<String,Object> map);

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    public CarOrder selectOrderDetail(Long orderId);

    /**
     * 根据条件查询车辆详情
     */
    public CarOrder queryCarDetail(Map<String,Object> map);

    /**
     * 查询历史车辆列表
     */
    public List<CarOrder> queryHistoryCar(Map<String,Object> map);

    /**
     * 查询历史车辆数量
     */
    public int queryHistoryCarCount(Map<String,Object> map);

    /**
     * 根据条件查询历史车辆详情
     */
    public CarOrder queryHistoryCarDetail(Map<String,Object> map);

    /**
     * 查询订单基本信息
     * @param id
     * @return
     */
    public CarOrder queryOrderBaseInfo(Long id);

    /**
     * 线下订单支付确认
     * @param carOrder
     * @param financePayLog
     * @return
     */
    public Integer saveOfflineOrderPay(CarOrder carOrder,CarFinancePayLog financePayLog);

    /**
     * 查询需要支付违约金订单列表
     * @param map
     * @return
     */
    public List<CarOrder> selectBreachPayOrderList(Map<String,Object> map);

    /**
     * 查询需要支付违约金订单数量
     * @param map
     * @return
     */
    public int selectBreachPayOrderCount(Map<String,Object> map);

    /**
     * 查询手续回传待确认订单列表
     * @param map
     * @return
     */
    public List<CarOrder> selectProcedurePassbackOrderList(Map<String,Object> map);

    /**
     * 查询手续回传待确认订单数量
     * @param map
     * @return
     */
    public int selectProcedurePassbackOrderCount(Map<String,Object> map);

    /**
     * 订单违约金支付确认
     * @param carOrder
     * @param financePayLog
     * @return
     */
    public int saveBreachOrderPay(CarOrder carOrder,CarFinancePayLog financePayLog,CarCustomerBreach breach);

    /**
     * 审核手续回传待确认
     * @return
     */
    public int saveProcedurePassback(CarAutoTransfer autoTransfer);

    /**
     * 查询订单打印数据
     * @param id
     * @return
     */
    public CarOrder selectOrderPrintInfo(Long id);

    /**
     * 订单拍牌号补全
     * @param id
     * @param customerId
     * @return
     */
    public int updateOrderUser(Long id,Long customerId);
}
