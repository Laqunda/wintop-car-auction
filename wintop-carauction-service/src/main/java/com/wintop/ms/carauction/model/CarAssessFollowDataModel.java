package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAssessFollowData;
import com.wintop.ms.carauction.mapper.read.CarAssessFollowDataReadDao;
import com.wintop.ms.carauction.mapper.write.CarAssessFollowDataWriteDao;
import com.wintop.ms.carauction.service.ICarAssessFollowDataService;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆评估跟进 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
@Repository
public class CarAssessFollowDataModel
{
	@Autowired
	private CarAssessFollowDataReadDao readDao;

	@Autowired
	private CarAssessFollowDataWriteDao writeDao;

	/**
     * 查询车辆评估跟进信息
     * 
     * @param id 车辆评估跟进ID
     * @return 车辆评估跟进信息
     */

	public CarAssessFollowData selectCarAssessFollowDataById(Long id)
	{
	    return readDao.selectCarAssessFollowDataById(id);
	}
	
	/**
     * 查询车辆评估跟进列表
     * 
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 车辆评估跟进集合
     */
	public List<CarAssessFollowData> selectCarAssessFollowDataList(CarAssessFollowData carAssessFollowData)
	{
	    return readDao.selectCarAssessFollowDataList(carAssessFollowData);
	}

	public int selectAssessFollowDataCount(CarAssessFollowData carAssessFollowData) {
		return readDao.selectAssessFollowDataCount(carAssessFollowData);
	}

    /**
     * 新增车辆评估跟进
     *
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 结果
     */

	public int insertCarAssessFollowData(CarAssessFollowData carAssessFollowData)
	{
	    return writeDao.insertCarAssessFollowData(carAssessFollowData);
	}

	/**
     * 修改车辆评估跟进
     *
     * @param carAssessFollowData 车辆评估跟进信息
     * @return 结果
     */

	public int updateCarAssessFollowData(CarAssessFollowData carAssessFollowData)
	{
	    return writeDao.updateCarAssessFollowData(carAssessFollowData);
	}

	/**
     * 删除车辆评估跟进对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */

	public int deleteCarAssessFollowDataByIds(String ids)
	{
		return writeDao.deleteCarAssessFollowDataByIds(Convert.toStrArray(ids));
	}


}
