package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAssessOrder;
import com.wintop.ms.carauction.mapper.read.CarAssessOrderReadDao;
import com.wintop.ms.carauction.mapper.write.CarAssessOrderWriteDao;
import com.wintop.ms.carauction.service.ICarAssessOrderService;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 评估采购单 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
@Repository
public class CarAssessOrderModel
{
	@Autowired
	private CarAssessOrderReadDao readDao;
	@Autowired
	private CarAssessOrderWriteDao writeDao;

	/**
     * 查询评估采购单信息
     * 
     * @param id 评估采购单ID
     * @return 评估采购单信息
     */
	public CarAssessOrder selectCarAssessOrderById(Long id)
	{
	    return readDao.selectCarAssessOrderById(id);
	}
	
	/**
     * 查询评估采购单列表
     * 
     * @param param 评估采购单信息
     * @return 评估采购单集合
     */
	public List<CarAssessOrder> selectCarAssessOrderList(Map<String,Object> param)
	{
	    return readDao.selectCarAssessOrderList(param);
	}
	public int selectAssessOrderCount(Map<String,Object> map) {
		return readDao.selectAssessOrderCount(map);

	}

    /**
     * 新增评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
	public int insertCarAssessOrder(CarAssessOrder carAssessOrder)
	{
	    return writeDao.insertCarAssessOrder(carAssessOrder);
	}

	/**
     * 修改评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
	public int updateCarAssessOrder(CarAssessOrder carAssessOrder)
	{
	    return writeDao.updateCarAssessOrder(carAssessOrder);
	}

	/**
     * 删除评估采购单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessOrderByIds(String ids)
	{
		return writeDao.deleteCarAssessOrderByIds(Convert.toStrArray(ids));
	}

	public int selectCountById(Long userId){
		return readDao.selectCountById(userId);
	}
	public List<CarAssessOrder> selectUserOrderList(Map<String,Object> map){
		return readDao.selectUserOrderList(map);
	}
}
