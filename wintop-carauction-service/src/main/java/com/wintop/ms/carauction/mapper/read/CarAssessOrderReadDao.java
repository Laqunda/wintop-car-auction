package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAssessOrder;

import java.util.List;

/**
 * 评估采购单 数据层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface CarAssessOrderReadDao
{
	/**
     * 查询评估采购单信息
     * 
     * @param id 评估采购单ID
     * @return 评估采购单信息
     */
	public CarAssessOrder selectCarAssessOrderById(Long id);
	
	/**
     * 查询评估采购单列表
     * 
     * @param carAssessOrder 评估采购单信息
     * @return 评估采购单集合
     */
	public List<CarAssessOrder> selectCarAssessOrderList(CarAssessOrder carAssessOrder);

    int selectAssessOrderCount(CarAssessOrder carAssessOrder);
}