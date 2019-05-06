package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CarAssessFollowData;

import java.util.List;

/**
 * 车辆评估跟进 数据层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessFollowDataReadDao
{
	/**
     * 查询车辆评估跟进信息
     * 
     * @param id 车辆评估跟进ID
     * @return 车辆评估跟进信息
     */
	public CarAssessFollowData selectCarAssessFollowDataById(Long id);
	
	/**
     * 查询车辆评估跟进列表
     * 
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 车辆评估跟进集合
     */
	public List<CarAssessFollowData> selectCarAssessFollowDataList(CarAssessFollowData carAssessFollowData);
	
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

    int selectAssessFollowDataCount(CarAssessFollowData carAssessFollowData);
}