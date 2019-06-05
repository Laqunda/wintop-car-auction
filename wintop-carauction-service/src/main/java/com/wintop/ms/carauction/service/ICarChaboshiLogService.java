package com.wintop.ms.carauction.service;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarChaboshiLog;
import com.wintop.ms.carauction.entity.CarFinancePayLog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 查博士日志 服务层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface ICarChaboshiLogService 
{
	/**
     * 查询查博士日志信息
     * 
     * @param id 查博士日志ID
     * @return 查博士日志信息
     */
	public CarChaboshiLog selectCarChaboshiLogById(Long id);
	
	/**
     * 查询查博士日志列表
     * 
     * @param map 查博士日志信息
     * @return 查博士日志集合
     */
	public List<CarChaboshiLog> selectCarChaboshiLogList(Map<String,Object> map);
	/**
     * 新增查博士日志
     * 
     * @param carChaboshiLog 查博士日志信息
     * @return 结果
     */
	public int insertCarChaboshiLog(CarChaboshiLog carChaboshiLog);
	
	/**
     * 修改查博士日志
     * 
     * @param map 查博士日志信息
     * @return 结果
     */
	public int updateCarChaboshiLog(Map<String,Object> map);
		
	/**
     * 删除查博士日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiLogByIds(String ids);

	int selectCount(Map<String,Object> map);

    ServiceResult<Map<String, Object>> chaboshi(Long userId,String userName, String edition, Long logId, String vin);

	ServiceResult<Map<String, Object>> chaboshiStore(BigDecimal payment, JSONObject obj);

	ServiceResult<Map<String, Object>> chaboshiOrder(CarChaboshiLog log,Long userId, String userName);

	CarChaboshiLog selectCarChaboshiLog(Map<String, Object> map);

	int savePayLog(CarFinancePayLog payLog);

	/**
	 * 店铺查询查博士
	 */
	ServiceResult<Map<String, Object>> searchForStore(JSONObject obj);
}
