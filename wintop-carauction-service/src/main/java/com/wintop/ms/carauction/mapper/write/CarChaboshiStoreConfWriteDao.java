package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarChaboshiStoreConf;

/**
 * 查博士店铺设置 数据层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiStoreConfWriteDao
{
	/**
     * 新增查博士店铺设置
     * 
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 结果
     */
	public int insertCarChaboshiStoreConf(CarChaboshiStoreConf carChaboshiStoreConf);
	
	/**
     * 修改查博士店铺设置
     * 
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 结果
     */
	public int updateCarChaboshiStoreConf(CarChaboshiStoreConf carChaboshiStoreConf);
	
	/**
     * 删除查博士店铺设置
     * 
     * @param id 查博士店铺设置ID
     * @return 结果
     */
	public int deleteCarChaboshiStoreConfById(Long id);
	
	/**
     * 批量删除查博士店铺设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiStoreConfByIds(String[] ids);
	
}