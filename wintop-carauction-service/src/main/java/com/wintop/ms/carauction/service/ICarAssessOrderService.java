package com.wintop.ms.carauction.service;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssessOrder;
import com.wintop.ms.carauction.util.utils.IdWorker;

import java.util.List;
import java.util.Map;

/**
 * 评估采购单 服务层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface ICarAssessOrderService 
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

	/**
     * 新增评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
	public int insertCarAssessOrder(CarAssessOrder carAssessOrder);

	/**
     * 修改评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
	public int updateCarAssessOrder(CarAssessOrder carAssessOrder);
		
	/**
     * 删除评估采购单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessOrderByIds(String ids);

    int selectAssessOrderCount(Map<String,Object> map);

	int selectCountById(Long userId);

	public List<CarAssessOrder> selectUserOrderList(Map<String,Object> map);

    ServiceResult<Map<String, Object>> createAssessOrder(JSONObject obj, IdWorker idWorker);

	ServiceResult<Map<String, Object>> editStatus(JSONObject obj,IdWorker idWorker);
}
