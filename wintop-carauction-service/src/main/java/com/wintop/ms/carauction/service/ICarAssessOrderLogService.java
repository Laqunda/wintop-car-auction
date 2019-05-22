package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarAssessOrderLog;
import com.wintop.ms.carauction.entity.CarManagerUser;

import java.util.List;
import java.util.Map;

/**
 * 评估采购日志 服务层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface ICarAssessOrderLogService 
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

	/**
     * 新增评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
	public int insertCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog);

	/**
     * 修改评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
	public int updateCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog);
		
	/**
     * 删除评估采购日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessOrderLogByIds(String ids);

    int selectAssessOrderCount(CarAssessOrderLog carAssessOrderLog);

    void saveOrderLog(CarManagerUser managerUser, String s, String s1, long orderlog_id,long orderId);

	int selectCountWaitByUserId(Long userId);

	int selectCountEndByUserId(Long userId);

	public List<CarAssessOrderLog> selectWaitOrderList(Map<String,Object> map);

	public List<CarAssessOrderLog> selectEndOrderList(Map<String,Object> map);
}
