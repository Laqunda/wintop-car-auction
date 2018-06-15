package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerDepositLog;
import com.wintop.ms.carauction.entity.DepositFreeze;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:保证金日志
 * @date 2018-03-27
 */
public interface CarCustomerDepositLogReadDao {
    /**
     * 根据主键查询记录
     */
    CarCustomerDepositLog selectByPrimaryKey(Long id);
    /**
     * 查询保证金冻结记录列表
     */
    List<DepositFreeze> queryDepositFreezeList(Map<String,Object> map);
    /**
     * 查询保证金冻结记录的数量
     */
    Integer selectDepositFreezeCount(Map<String,Object> map);
}
