package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarChaboshiStoreAccount;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarChaboshiStoreAccountService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 查博士店铺资金流水 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
@RestController
@RequestMapping("/service/carChaboshiStoreAccount")
public class CarChaboshiStoreAccountApi
{
	private static final Logger logger = LoggerFactory.getLogger(CarAssessApi.class);
	private IdWorker idWorker = new IdWorker(10);

	@Autowired
	private ICarChaboshiStoreAccountService service;


	/**
	 * 查询查博士店铺资金流水列表
	 */
	@ApiOperation(value = "博士店铺资金流水列表")
	@RequestMapping(value = "/list",
			method = RequestMethod.POST,
			consumes = "application/json; charset=UTF-8",
			produces = "application/json; charset=UTF-8")

	public ServiceResult<ListEntity<CarChaboshiStoreAccount>> list(@RequestBody JSONObject obj) {
		ServiceResult<ListEntity<CarChaboshiStoreAccount>> result = null;
		try {
			//TODO 赋值参数
			CarChaboshiStoreAccount bean = JSONObject.toJavaObject(obj, CarChaboshiStoreAccount.class);
			if (bean == null) {
				bean = new CarChaboshiStoreAccount();
			}
			result = new ServiceResult<>();

			int count = service.selectCount(bean);

			PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
			bean.setStartRowNum(pageEntity.getStartRowNum());
			bean.setEndRowNum(pageEntity.getEndRowNum());

			List<CarChaboshiStoreAccount> list = service.selectCarChaboshiStoreAccountList(bean);
			ListEntity<CarChaboshiStoreAccount> listEntity = new ListEntity<>();
			listEntity.setList(list);
			listEntity.setCount(count);
			result.setResult(listEntity);
			result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
		} catch (Exception e) {
			logger.info("博士店铺资金流水列表", e);
			e.printStackTrace();
			result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
		}

		return result;

	}




	/**
	 * 新增保存查博士店铺资金流水
	 */
	@ApiOperation(value = "新增保存查博士店铺资金流水")
	@RequestMapping(value = "/add",
			method = RequestMethod.POST,
			consumes = "application/json; charset=UTF-8",
			produces = "application/json; charset=UTF-8")
	public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {
		ServiceResult<Map<String, Object>> result = new ServiceResult<>();

		try {
			//TODO 赋值参数
			CarChaboshiStoreAccount bean = JSONObject.toJavaObject(obj, CarChaboshiStoreAccount.class);
			if (bean == null) {
				bean = new CarChaboshiStoreAccount();
			}
			bean.setId(idWorker.nextId());
			int code = service.insertCarChaboshiStoreAccount(bean);
			if (code > 0) {
				result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
			} else {
				result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
			}
		} catch (Exception e) {
			logger.info("新增保存查博士店铺资金流水", e);
			e.printStackTrace();
			result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

		}
		return result;
	}


	/**
	 * 修改保存查博士店铺资金流水
	 */
	@RequestMapping(value = "/edit",
			method = RequestMethod.POST,
			consumes = "application/json; charset=UTF-8",
			produces = "application/json; charset=UTF-8")
	public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
		ServiceResult<Map<String, Object>> result = new ServiceResult<>();
		try {
			//TODO 赋值参数
			CarChaboshiStoreAccount bean = JSONObject.toJavaObject(obj, CarChaboshiStoreAccount.class);
			if (bean == null) {
				bean = new CarChaboshiStoreAccount();
			}
			int code = service.updateCarChaboshiStoreAccount(bean);
			if (code > 0) {
				result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
			} else {
				result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
			}
		} catch (Exception e) {
			logger.info("修改保存查博士店铺资金流水", e);
			e.printStackTrace();
			result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

		}
		return result;
	}

	/**
	 * 删除查博士店铺资金流水
	 */
	@ApiOperation(value = "删除查博士店铺资金流水")
	@RequestMapping(value = "/remove",
			method = RequestMethod.POST,
			consumes = "application/json; charset=UTF-8",
			produces = "application/json; charset=UTF-8")
	public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {

		ServiceResult<Map<String, Object>> result = new ServiceResult<>();
		try {
			String ids = obj.getString("ids");
			int code = service.deleteCarChaboshiStoreAccountByIds(ids);

			if (code > 0) {
				result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
			} else {
				result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
			}
		} catch (Exception e) {
			logger.info("删除店铺资金流水", e);
			e.printStackTrace();
			result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

		}
		return result;
	}



}
