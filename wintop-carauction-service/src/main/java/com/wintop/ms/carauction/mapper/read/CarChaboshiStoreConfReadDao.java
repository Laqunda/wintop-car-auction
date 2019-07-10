package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarChaboshiStoreConf;

import java.util.List;	

/**
 * 查博士店铺设置 数据层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiStoreConfReadDao
{
	/**
     * 查询查博士店铺设置信息
     * 
     * @param id 查博士店铺设置ID
     * @return 查博士店铺设置信息
     */
	public CarChaboshiStoreConf selectCarChaboshiStoreConfById(Long id);
	
	/**
     * 查询查博士店铺设置列表
     * 
     * @param carChaboshiStoreConf 查博士店铺设置信息
     * @return 查博士店铺设置集合
     */
	public List<CarChaboshiStoreConf> selectCarChaboshiStoreConfList(CarChaboshiStoreConf carChaboshiStoreConf);


	int selectCount(CarChaboshiStoreConf bean);

	/**
	 * 查询查博士店铺设置
	 * @param carChaboshiStoreConf
	 * @return
	 */
	public CarChaboshiStoreConf selectCarChaboshiStoreConfByParams(CarChaboshiStoreConf carChaboshiStoreConf);
}