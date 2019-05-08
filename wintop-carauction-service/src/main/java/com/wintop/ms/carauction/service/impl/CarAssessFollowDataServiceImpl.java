package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarAssessFollowData;
import com.wintop.ms.carauction.model.CarAssessFollowDataModel;
import com.wintop.ms.carauction.service.ICarAssessFollowDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆评估跟进 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessFollowDataServiceImpl implements ICarAssessFollowDataService
{
	@Autowired
	private CarAssessFollowDataModel model;

	/**
     * 查询车辆评估跟进信息
     * 
     * @param id 车辆评估跟进ID
     * @return 车辆评估跟进信息
     */
    @Override
	public CarAssessFollowData selectCarAssessFollowDataById(Long id)
	{
	    return model.selectCarAssessFollowDataById(id);
	}
	
	/**
     * 查询车辆评估跟进列表
     * 
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 车辆评估跟进集合
     */
	@Override
	public List<CarAssessFollowData> selectCarAssessFollowDataList(CarAssessFollowData carAssessFollowData)
	{
	    return model.selectCarAssessFollowDataList(carAssessFollowData);
	}

    /**
     * 新增车辆评估跟进
     *
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 结果
     */
	@Override
	public int insertCarAssessFollowData(CarAssessFollowData carAssessFollowData)
	{
	    return model.insertCarAssessFollowData(carAssessFollowData);
	}

	/**
     * 修改车辆评估跟进
     *
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 结果
     */
	@Override
	public int updateCarAssessFollowData(CarAssessFollowData carAssessFollowData)
	{
	    return model.updateCarAssessFollowData(carAssessFollowData);
	}

	/**
     * 删除车辆评估跟进对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCarAssessFollowDataByIds(String ids)
	{
		return model.deleteCarAssessFollowDataByIds(ids);
	}

	@Override
	public int selectAssessFollowDataCount(CarAssessFollowData carAssessFollowData) {
		return model.selectAssessFollowDataCount(carAssessFollowData);
	}

}
