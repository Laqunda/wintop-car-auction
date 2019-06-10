package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarChaboshiPaymentConf;
import com.wintop.ms.carauction.model.CarChaboshiPaymentConfModel;
import com.wintop.ms.carauction.service.ICarChaboshiPaymentConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查博士买家端支付金额设置 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
@Service
public class CarChaboshiPaymentConfServiceImpl implements ICarChaboshiPaymentConfService 
{
	@Autowired
	private CarChaboshiPaymentConfModel model;

	/**
     * 查询查博士买家端支付金额设置信息
     * 
     * @param id 查博士买家端支付金额设置ID
     * @return 查博士买家端支付金额设置信息
     */
    @Override
	public CarChaboshiPaymentConf selectCarChaboshiPaymentConfById(Long id)
	{
	    return model.selectCarChaboshiPaymentConfById(id);
	}
	
	/**
     * 查询查博士买家端支付金额设置列表
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 查博士买家端支付金额设置集合
     */
	@Override
	public List<CarChaboshiPaymentConf> selectCarChaboshiPaymentConfList(CarChaboshiPaymentConf carChaboshiPaymentConf)
	{
	    return model.selectCarChaboshiPaymentConfList(carChaboshiPaymentConf);
	}

	@Override
	public int selectCount(CarChaboshiPaymentConf bean) {
		return model.selectCount(bean);
	}

	/**
     * 新增查博士买家端支付金额设置
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 结果
     */
	@Override
	public int insertCarChaboshiPaymentConf(CarChaboshiPaymentConf carChaboshiPaymentConf)
	{
	    return model.insertCarChaboshiPaymentConf(carChaboshiPaymentConf);
	}
	
	/**
     * 修改查博士买家端支付金额设置
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 结果
     */
	@Override
	public int updateCarChaboshiPaymentConf(CarChaboshiPaymentConf carChaboshiPaymentConf)
	{
	    return model.updateCarChaboshiPaymentConf(carChaboshiPaymentConf);
	}

	/**
     * 删除查博士买家端支付金额设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCarChaboshiPaymentConfByIds(String ids)
	{
		return model.deleteCarChaboshiPaymentConfByIds(ids);
	}
	
}
