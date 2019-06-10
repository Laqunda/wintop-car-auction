package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarChaboshiPaymentConf;
import com.wintop.ms.carauction.mapper.read.CarChaboshiPaymentConfReadDao;
import com.wintop.ms.carauction.mapper.write.CarChaboshiPaymentConfWriteDao;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查博士买家端支付金额设置 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
@Repository
public class CarChaboshiPaymentConfModel
{
	@Autowired
	private CarChaboshiPaymentConfReadDao readDao;
	@Autowired
	private CarChaboshiPaymentConfWriteDao writeDao;

	/**
     * 查询查博士买家端支付金额设置信息
     * 
     * @param id 查博士买家端支付金额设置ID
     * @return 查博士买家端支付金额设置信息
     */
	public CarChaboshiPaymentConf selectCarChaboshiPaymentConfById(Long id)
	{
	    return readDao.selectCarChaboshiPaymentConfById(id);
	}
	
	/**
     * 查询查博士买家端支付金额设置列表
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 查博士买家端支付金额设置集合
     */
	public List<CarChaboshiPaymentConf> selectCarChaboshiPaymentConfList(CarChaboshiPaymentConf carChaboshiPaymentConf)
	{
	    return readDao.selectCarChaboshiPaymentConfList(carChaboshiPaymentConf);
	}
	public int selectCount(CarChaboshiPaymentConf bean) {
		return readDao.selectCount(bean);
	}
	
    /**
     * 新增查博士买家端支付金额设置
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 结果
     */
	public int insertCarChaboshiPaymentConf(CarChaboshiPaymentConf carChaboshiPaymentConf)
	{
	    return writeDao.insertCarChaboshiPaymentConf(carChaboshiPaymentConf);
	}
	
	/**
     * 修改查博士买家端支付金额设置
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 结果
     */
	public int updateCarChaboshiPaymentConf(CarChaboshiPaymentConf carChaboshiPaymentConf)
	{
	    return writeDao.updateCarChaboshiPaymentConf(carChaboshiPaymentConf);
	}

	/**
     * 删除查博士买家端支付金额设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiPaymentConfByIds(String ids)
	{
		return writeDao.deleteCarChaboshiPaymentConfByIds(Convert.toStrArray(ids));
	}


}
