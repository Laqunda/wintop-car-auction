package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarChaboshiStoreAccount;
import com.wintop.ms.carauction.mapper.read.CarChaboshiStoreAccountReadDao;
import com.wintop.ms.carauction.mapper.write.CarChaboshiStoreAccountWriteDao;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查博士店铺资金流水 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
@Repository
public class CarChaboshiStoreAccountModel{
	@Autowired
	private CarChaboshiStoreAccountReadDao readDao;
		@Autowired
		private CarChaboshiStoreAccountWriteDao writeDao;

	/**
     * 查询查博士店铺资金流水信息
     * 
     * @param id 查博士店铺资金流水ID
     * @return 查博士店铺资金流水信息
     */
	public CarChaboshiStoreAccount selectCarChaboshiStoreAccountById(Long id)
	{
	    return readDao.selectCarChaboshiStoreAccountById(id);
	}
	
	/**
     * 查询查博士店铺资金流水列表
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 查博士店铺资金流水集合
     */
	public List<CarChaboshiStoreAccount> selectCarChaboshiStoreAccountList(CarChaboshiStoreAccount carChaboshiStoreAccount)
	{
	    return readDao.selectCarChaboshiStoreAccountList(carChaboshiStoreAccount);
	}

	public int selectCount(CarChaboshiStoreAccount bean) {

		return readDao.selectCount(bean);
	}
	
    /**
     * 新增查博士店铺资金流水
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 结果
     */
	public int insertCarChaboshiStoreAccount(CarChaboshiStoreAccount carChaboshiStoreAccount)
	{
	    return writeDao.insertCarChaboshiStoreAccount(carChaboshiStoreAccount);
	}
	
	/**
     * 修改查博士店铺资金流水
     * 
     * @param carChaboshiStoreAccount 查博士店铺资金流水信息
     * @return 结果
     */
	public int updateCarChaboshiStoreAccount(CarChaboshiStoreAccount carChaboshiStoreAccount)
	{
	    return writeDao.updateCarChaboshiStoreAccount(carChaboshiStoreAccount);
	}

	/**
     * 删除查博士店铺资金流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarChaboshiStoreAccountByIds(String ids)
	{
		return writeDao.deleteCarChaboshiStoreAccountByIds(Convert.toStrArray(ids));
	}


}
