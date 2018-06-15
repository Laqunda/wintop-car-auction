package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarOrder;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarOrderReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarOrder> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarOrder selectById(Long id);

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
    int selectUserOrderCount(Map<String,Object> map);


    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    public CarOrder selectOrderDetail(@Param("orderId") Long orderId);

    /**
     * 根据条件查询订单数据
     */
    List<CarOrder> selectOrderCount(Map<String,Object> map);

    /**
     * 查询某个车辆是否存在订单
     * @param map
     * @return
     */
    CarOrder queryCarOrder(Map<String,Object> map);

    /**
     *根据条件查询车辆详情
     */
    CarOrder queryCarDetail(Map<String,Object> map);

    /**
     * 查询历史车辆列表
     */
    List<CarOrder> queryHistoryCar(Map<String,Object> map);

    /**
     * 查询历史车辆数量
     */
    int  queryHistoryCarCount(Map<String,Object> map);

    /**
     * 根据条件查询历史车辆详情
     */
    CarOrder queryHistoryCarDetail(Map<String,Object> map);

    /**
     * 查询订单基本信息
     * @param id
     * @return
     */
    CarOrder queryOrderBaseInfo(Long id);

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

    public CarOrder selectOrderByCarId(Map<String,Object> paramMap);

    /**
     * 查询订单打印数据
     * @param id
     * @return
     */
    public CarOrder selectOrderPrintInfo(Long id);
}