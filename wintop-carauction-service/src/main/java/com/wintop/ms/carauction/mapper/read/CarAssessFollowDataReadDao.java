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

    int selectAssessFollowDataCount(CarAssessFollowData carAssessFollowData);
}