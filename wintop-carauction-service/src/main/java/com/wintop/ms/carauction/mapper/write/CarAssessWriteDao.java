package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAssess;

/**
 * 车辆评估 数据层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessWriteDao
{

	/**
     * 新增车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
	public int insertCarAssess(CarAssess carAssess);

	/**
     * 修改车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
	public int updateCarAssess(CarAssess carAssess);
	
	/**
     * 删除车辆评估
     * 
     * @param id 车辆评估ID
     * @return 结果
     */
	public int deleteCarAssessById(Long id);
	
	/**
     * 批量删除车辆评估
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessByIds(String[] ids);
	
}