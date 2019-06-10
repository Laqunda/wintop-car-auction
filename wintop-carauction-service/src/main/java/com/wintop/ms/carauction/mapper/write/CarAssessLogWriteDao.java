package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAssessLog;

/**
 * 评估日志 数据层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessLogWriteDao
{
	/**
     * 新增评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
	public int insertCarAssessLog(CarAssessLog carAssessLog);

	/**
     * 修改评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
	public int updateCarAssessLog(CarAssessLog carAssessLog);
	
	/**
     * 删除评估日志
     * 
     * @param id 评估日志ID
     * @return 结果
     */
	public int deleteCarAssessLogById(Long id);
	
	/**
     * 批量删除评估日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessLogByIds(String[] ids);
	
}