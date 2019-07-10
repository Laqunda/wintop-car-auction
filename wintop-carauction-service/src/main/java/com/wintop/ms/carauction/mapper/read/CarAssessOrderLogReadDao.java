package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAssessOrderLog;

import java.util.List;
import java.util.Map;

/**
 * 评估采购日志 数据层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessOrderLogReadDao
{
	/**
     * 查询评估采购日志信息
     * 
     * @param id 评估采购日志ID
     * @return 评估采购日志信息
     */
	public CarAssessOrderLog selectCarAssessOrderLogById(Long id);
	
	/**
     * 查询评估采购日志列表
     * 
     * @param carAssessOrderLog 评估采购日志信息
     * @return 评估采购日志集合
     */
	public List<CarAssessOrderLog> selectCarAssessOrderLogList(CarAssessOrderLog carAssessOrderLog);

	int selectAssessOrderCount(CarAssessOrderLog carAssessOrderLog);

	int selectCountWaitByParams(Map<String,Object> map);

	int selectCountEndByUserId(Long userId);

	List<CarAssessOrderLog> selectWaitOrderList(Map<String,Object> map);

	List<CarAssessOrderLog> selectEndOrderList(Map<String,Object> map);
}
