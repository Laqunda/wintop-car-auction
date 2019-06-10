package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarChaboshiPaymentConf;

import java.util.List;

/**
 * 查博士买家端支付金额设置 服务层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface ICarChaboshiPaymentConfService 
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
	
	/**
     * 新增查博士买家端支付金额设置
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 结果
     */
	public int insertCarChaboshiPaymentConf(CarChaboshiPaymentConf carChaboshiPaymentConf);
	
	/**
     * 修改查博士买家端支付金额设置
     * 
     * @param carChaboshiPaymentConf 查博士买家端支付金额设置信息
     * @return 结果
     */
	public int updateCarChaboshiPaymentConf(CarChaboshiPaymentConf carChaboshiPaymentConf);
		
	/**
     * 删除查博士买家端支付金额设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiPaymentConfByIds(String ids);

	int selectCount(CarChaboshiPaymentConf bean);
}
