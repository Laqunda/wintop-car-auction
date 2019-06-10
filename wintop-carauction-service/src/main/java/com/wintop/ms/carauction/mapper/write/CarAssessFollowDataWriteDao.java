package com.wintop.ms.carauction.mapper.write;


import com.wintop.ms.carauction.entity.CarAssessFollowData;

/**
 * 车辆评估跟进 数据层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessFollowDataWriteDao
{

	/**
     * 新增车辆评估跟进
     * 
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 结果
     */
	public int insertCarAssessFollowData(CarAssessFollowData carAssessFollowData);
	
	/**
     * 修改车辆评估跟进
     * 
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 结果
     */
	public int updateCarAssessFollowData(CarAssessFollowData carAssessFollowData);
	
	/**
     * 删除车辆评估跟进
     * 
     * @param id 车辆评估跟进ID
     * @return 结果
     */
	public int deleteCarAssessFollowDataById(Long id);
	
	/**
     * 批量删除车辆评估跟进
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessFollowDataByIds(String[] ids);
	
}