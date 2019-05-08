package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarChaboshiPaymentConf;

import java.util.List;	

/**
 * 查博士买家端支付金额设置 数据层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiPaymentConfReadDao
{
	/**
     * 查询查博士买家端支付金额设置信息
     * 
     * @param id 查博士买家端支付金额设置ID
     * @return 查博士买家端支付金额设置信息
     */
	public CarChaboshiPaymentConf selectCarChaboshiPaymentConfById(Long id);
	
	/**
     * 查询查博士买家端支付金额设置列表
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 查博士买家端支付金额设置集合
     */
	public List<CarChaboshiPaymentConf> selectCarChaboshiPaymentConfList(CarChaboshiPaymentConf carChaboshiPaymentConf);

	int selectCount(CarChaboshiPaymentConf bean);
}