package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarChaboshiStoreConf;

import java.util.List;

/**
 * 查博士店铺设置 服务层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface ICarChaboshiStoreConfService 
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
     * 删除查博士店铺设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiStoreConfByIds(String ids);

	int selectCount(CarChaboshiStoreConf bean);
}
