package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarChaboshiStoreAccount;

import java.util.List;	

/**
 * 查博士店铺资金流水 数据层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiStoreAccountReadDao
{
	/**
     * 查询查博士店铺资金流水信息
     * 
     * @param id 查博士店铺资金流水ID
     * @return 查博士店铺资金流水信息
     */
	public CarChaboshiStoreAccount selectCarChaboshiStoreAccountById(Long id);
	
	/**
     * 查询查博士店铺资金流水列表
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 查博士店铺资金流水集合
     */
	public List<CarChaboshiStoreAccount> selectCarChaboshiStoreAccountList(CarChaboshiStoreAccount carChaboshiStoreAccount);


	int selectCount(CarChaboshiStoreAccount bean);
}