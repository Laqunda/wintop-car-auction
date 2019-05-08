package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarChaboshiStoreAccount;

import java.util.List;

/**
 * 查博士店铺资金流水 服务层
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public interface ICarChaboshiStoreAccountService 
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
     * 删除查博士店铺资金流水信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiStoreAccountByIds(String ids);

	int selectCount(CarChaboshiStoreAccount bean);
}
