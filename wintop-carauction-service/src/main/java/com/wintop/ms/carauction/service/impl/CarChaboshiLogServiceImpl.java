package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarChaboshiLog;
import com.wintop.ms.carauction.model.CarChaboshiLogModel;
import com.wintop.ms.carauction.service.ICarChaboshiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查博士日志 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
@Service
public class CarChaboshiLogServiceImpl implements ICarChaboshiLogService 
{
	@Autowired
	private CarChaboshiLogModel model;

	/**
     * 查询查博士日志信息
     * 
     * @param id 查博士日志ID
     * @return 查博士日志信息
     */
    @Override
	public CarChaboshiLog selectCarChaboshiLogById(Long id)
	{
	    return model.selectCarChaboshiLogById(id);
	}
	
	/**
     * 查询查博士日志列表
     * 
     * @param carChaboshiLog 查博士日志信息
     * @return 查博士日志集合
     */
	@Override
	public List<CarChaboshiLog> selectCarChaboshiLogList(CarChaboshiLog carChaboshiLog)
	{
	    return model.selectCarChaboshiLogList(carChaboshiLog);
	}
	
    /**
     * 新增查博士日志
     * 
     * @param carChaboshiLog 查博士日志信息
     * @return 结果
     */
	@Override
	public int insertCarChaboshiLog(CarChaboshiLog carChaboshiLog)
	{
	    return model.insertCarChaboshiLog(carChaboshiLog);
	}
	
	/**
     * 修改查博士日志
     * 
     * @param carChaboshiLog 查博士日志信息
     * @return 结果
     */
	@Override
	public int updateCarChaboshiLog(CarChaboshiLog carChaboshiLog)
	{
	    return model.updateCarChaboshiLog(carChaboshiLog);
	}

	/**
     * 删除查博士日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCarChaboshiLogByIds(String ids)
	{
		return model.deleteCarChaboshiLogByIds(ids);
	}

	@Override
	public int selectCount(CarChaboshiLog carChaboshiLog) {
		return model.selectCount(carChaboshiLog);
	}
}
