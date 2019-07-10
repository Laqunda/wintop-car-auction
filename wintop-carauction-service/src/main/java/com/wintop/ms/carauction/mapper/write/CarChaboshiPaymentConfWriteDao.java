package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarChaboshiPaymentConf;

/**
 * 查博士买家端支付金额设置 数据层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiPaymentConfWriteDao
{
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
     * 删除查博士买家端支付金额设置
     * 
     * @param id 查博士买家端支付金额设置ID
     * @return 结果
     */
	public int deleteCarChaboshiPaymentConfById(Long id);
	
	/**
     * 批量删除查博士买家端支付金额设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiPaymentConfByIds(String[] ids);
	
}