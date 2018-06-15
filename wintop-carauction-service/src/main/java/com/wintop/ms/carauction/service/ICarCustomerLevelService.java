package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLevel;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 客户级别
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelService {
    /**
     * 根据id查询客户级别
     * @param id
     * @return CarCustomerLevel
     */
    ServiceResult<CarCustomerLevel> selectByPrimaryKey(Long id);
    /**
     * 根据id删除客户级别
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLevel record);
    /**
     * 根据主键更新客户级别
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerLevel record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerLevel record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerLevel record);


    /**
     * 根据获取等级查询该等级因交保证金金额
     * @Autor 付陈林
     * @Time  2018-3-7
     * @param customerLevelId
     * @return
     */
    ServiceResult<Map<String,Object>> getDepositAmountByCustomerLevelId(Long customerLevelId);

    /**
     * 根据参数查询会员等级列表
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    List<CarCustomerLevel> selectListByParam(Map<String,Object> map);

    /**
     *根据参数查询会员等级总数量
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    Integer selectCountByParam(Map<String,Object> map);
    /**
     * 保存会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:object
     */
     ServiceResult<Map<String,Object>> saveLevel(JSONObject object);
    /**
     *设置会员级别开启
     * @Author:zhangzijuan
     *@date 2018/3/15
     *@param:levelId
     */
     ServiceResult<Map<String,Object>> IsOpenLevel(Long levelId);

    /**
     * 修改会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:object
     */
    ServiceResult<Map<String,Object>> updateLevel(JSONObject object);
    /**
     * 删除会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:levelId
     */
    ServiceResult<Map<String,Object>> deleteLevel(Long levelId);
    /**
     * 设置默认级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:levelId
     */
    ServiceResult<Map<String,Object>> isDefaultLevel(Long levelId);

    /**
     * 根据级别id查询级别详情
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:map
     */
    ServiceResult<CarCustomerLevel> selectLevelById(Map<String,Object> map);

    /**
     * 查询默认级别
     *@Author:zhangzijuan
     *@date 2018/3/28
     *@param:
     */
    CarCustomerLevel getDefaultLevel();
}