package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarChaboshiLog;

import java.util.List;
import java.util.Map;

/**
 * 查博士日志 数据层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiLogReadDao
{
	/**
     * 查询查博士日志信息
     * 
     * @param id 查博士日志ID
     * @return 查博士日志信息
     */
	public CarChaboshiLog selectCarChaboshiLogById(Long id);
	
	/**
     * 查询查博士日志列表
     * 
     * @param map 查博士日志信息
     * @return 查博士日志集合
     */
	public List<CarChaboshiLog> selectCarChaboshiLogList(Map<String,Object> map);


	int selectCount(Map<String,Object> map);
}