package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarChaboshiStoreAccount;

/**
 * 查博士店铺资金流水 数据层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiStoreAccountWriteDao
{
	/**
     * 新增查博士店铺资金流水
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 结果
     */
	public int insertCarChaboshiStoreAccount(CarChaboshiStoreAccount carChaboshiStoreAccount);
	
	/**
     * 修改查博士店铺资金流水
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 结果
     */
	public int updateCarChaboshiStoreAccount(CarChaboshiStoreAccount carChaboshiStoreAccount);
	
	/**
     * 删除查博士店铺资金流水
     * 
     * @param id 查博士店铺资金流水ID
     * @return 结果
     */
	public int deleteCarChaboshiStoreAccountById(Long id);
	
	/**
     * 批量删除查博士店铺资金流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiStoreAccountByIds(String[] ids);
	
}