package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarOrder;
import com.wintop.ms.carauction.mapper.read.ICarOrderReadDao;
import com.wintop.ms.carauction.mapper.write.ICarOrderWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarOrderModel {
    @Autowired
    private ICarOrderReadDao readDao;
    @Autowired
    private ICarOrderWriteDao writeDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarOrder> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarOrder selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarOrder carOrder){
        return writeDao.insert(carOrder);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarOrder carOrder){
        return writeDao.updateByIdSelective(carOrder);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarOrder carOrder){
        return writeDao.updateById(carOrder);
    }

    /**
     * 查询我的所有订单
     * @param map
     * @return
     */
    public List<CarOrder> selectUserOrderList(Map<String,Object> map){
        return readDao.selectUserOrderList(map);
    }

    /**
     * 查询我的所有订单数量
     * @param map
     * @return
     */
    public int selectUserOrderCount(Map<String,Object> map){
        return readDao.selectUserOrderCount(map);
    }

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    public CarOrder selectOrderDetail(Long orderId){
        return readDao.selectOrderDetail(orderId);
    }

    /**
     * 根据条件查询订单数据
     */
    public List<CarOrder> selectOrderCount(Map<String,Object> map){
        return readDao.selectOrderCount(map);
    }

    /**
     * 查询某个车辆是否存在订单
     * @param map
     * @return
     */
    public CarOrder queryCarOrder(Map<String,Object> map){
        return readDao.queryCarOrder(map);
    }

    /**
     * 根据条件查询车辆详情
     */
    public CarOrder queryCarDetail(Map<String,Object> map){
        return readDao.queryCarDetail(map);
    }

    /**
     * 查询历史车辆列表
     */
    public List<CarOrder> queryHistoryCar(Map<String,Object> map){
        return readDao.queryHistoryCar(map);
    }
    /**
     * 查询历史车辆数量
     */
    public int queryHistoryCarCount(Map<String,Object> map){
        return readDao.queryHistoryCarCount(map);
    }

    /**
     * 根据条件查询历史车辆详情
     */
    public CarOrder queryHistoryCarDetail(Map<String,Object> map){
        return readDao.queryHistoryCarDetail(map);
    }

    /**
     * 查询订单基本信息
     * @param id
     * @return
     */
    public CarOrder queryOrderBaseInfo(Long id){
        return readDao.queryOrderBaseInfo(id);
    }

    /**
     * 查询需要支付违约金订单列表
     * @param map
     * @return
     */
    public List<CarOrder> selectBreachPayOrderList(Map<String,Object> map){
        return readDao.selectBreachPayOrderList(map);
    }

    /**
     * 查询需要支付违约金订单数量
     * @param map
     * @return
     */
    public int selectBreachPayOrderCount(Map<String,Object> map){
        return readDao.selectBreachPayOrderCount(map);
    }

    /**
     * 查询手续回传待确认订单列表
     * @param map
     * @return
     */
    public List<CarOrder> selectProcedurePassbackOrderList(Map<String,Object> map){
        return readDao.selectProcedurePassbackOrderList(map);
    }

    /**
     * 查询手续回传待确认订单数量
     * @param map
     * @return
     */
    public int selectProcedurePassbackOrderCount(Map<String,Object> map){
        return readDao.selectProcedurePassbackOrderCount(map);
    }

    public CarOrder selectOrderByCarId(Map<String,Object> paramMap){
        return readDao.selectOrderByCarId(paramMap);
    }

    /**
     * 查询订单打印数据
     * @param id
     * @return
     */
    public CarOrder selectOrderPrintInfo(Long id){
        return readDao.selectOrderPrintInfo(id);
    }

    /**
     * 订单拍牌号补全
     * @param id
     * @param customerId
     * @return
     */
    public int updateOrderUser(Long id,Long customerId){
        return writeDao.updateOrderUser(id,customerId);
    }
}
