package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAssessOrder;

import java.util.List;
import java.util.Map;

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
     * @param param 评估采购单信息
     * @return 评估采购单集合
     */
	public List<CarAssessOrder> selectCarAssessOrderList(Map<String,Object> param);

    int selectAssessOrderCount(Map<String,Object> map);

	int selectCountById(Long userId);
	public List<CarAssessOrder> selectUserOrderList(Map<String,Object> map);
}