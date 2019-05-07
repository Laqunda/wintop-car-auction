package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarAssess;

import java.util.List;

/**
 * 车辆评估 服务层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface ICarAssessService 
{
	/**
     * 查询车辆评估信息
     * 
     * @param id 车辆评估ID
     * @return 车辆评估信息
     */
	public CarAssess selectCarAssessById(Long id);
	
	/**
     * 查询车辆评估列表
     * 
     * @param carAssess 车辆评估信息
     * @return 车辆评估集合
     */
	public List<CarAssess> selectCarAssessList(CarAssess carAssess);

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
     * 删除车辆评估信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessByIds(String ids);

	/**
	 * 根据条件查找评估数量
	 * @param carAssess
	 * @return
	 */
	int selectAssessCount(CarAssess carAssess);
}
