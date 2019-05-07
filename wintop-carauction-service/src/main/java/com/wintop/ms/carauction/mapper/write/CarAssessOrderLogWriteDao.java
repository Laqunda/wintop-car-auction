package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAssessOrderLog;

/**
 * 评估采购日志 数据层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessOrderLogWriteDao
{

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
     * 删除评估采购日志
     * 
     * @param id 评估采购日志ID
     * @return 结果
     */
	public int deleteCarAssessOrderLogById(Long id);
	
	/**
     * 批量删除评估采购日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessOrderLogByIds(String[] ids);
	
}