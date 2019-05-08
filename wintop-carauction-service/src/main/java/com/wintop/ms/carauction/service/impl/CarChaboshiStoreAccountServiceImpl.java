package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarChaboshiStoreAccount;
import com.wintop.ms.carauction.model.CarChaboshiStoreAccountModel;
import com.wintop.ms.carauction.service.ICarChaboshiStoreAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查博士店铺资金流水 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
@Service
public class CarChaboshiStoreAccountServiceImpl implements ICarChaboshiStoreAccountService 
{
	@Autowired
	private CarChaboshiStoreAccountModel model;

	/**
     * 查询查博士店铺资金流水信息
     * 
     * @param id 查博士店铺资金流水ID
     * @return 查博士店铺资金流水信息
     */
    @Override
	public CarChaboshiStoreAccount selectCarChaboshiStoreAccountById(Long id)
	{
	    return model.selectCarChaboshiStoreAccountById(id);
	}
	
	/**
     * 查询查博士店铺资金流水列表
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 查博士店铺资金流水集合
     */
	@Override
	public List<CarChaboshiStoreAccount> selectCarChaboshiStoreAccountList(CarChaboshiStoreAccount carChaboshiStoreAccount)
	{
	    return model.selectCarChaboshiStoreAccountList(carChaboshiStoreAccount);
	}

	@Override
	public int selectCount(CarChaboshiStoreAccount bean) {
		return model.selectCount(bean);
	}

	/**
     * 新增查博士店铺资金流水
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 结果
     */
	@Override
	public int insertCarChaboshiStoreAccount(CarChaboshiStoreAccount carChaboshiStoreAccount)
	{
	    return model.insertCarChaboshiStoreAccount(carChaboshiStoreAccount);
	}
	
	/**
     * 修改查博士店铺资金流水
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 结果
     */
	@Override
	public int updateCarChaboshiStoreAccount(CarChaboshiStoreAccount carChaboshiStoreAccount)
	{
	    return model.updateCarChaboshiStoreAccount(carChaboshiStoreAccount);
	}

	/**
     * 删除查博士店铺资金流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCarChaboshiStoreAccountByIds(String ids)
	{
		return model.deleteCarChaboshiStoreAccountByIds(ids);
	}
	
}
