package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.entity.CarChaboshiPaymentConf;
import com.wintop.ms.carauction.service.ICarChaboshiPaymentConfService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 查博士买家端支付金额设置 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-08
 */
@RestController
@RequestMapping("/service/carChaboshiPaymentConf")
public class CarChaboshiPaymentConfApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAssessApi.class);
    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarChaboshiPaymentConfService service;


    /**
     * 查询查博士买家端支付金额设置列表
     */
    @ApiOperation(value = "博士买家端支付金额设置列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")

    public ServiceResult<ListEntity<CarChaboshiPaymentConf>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarChaboshiPaymentConf>> result = null;
        try {
            //TODO 赋值参数
            CarChaboshiPaymentConf bean = JSONObject.toJavaObject(obj, CarChaboshiPaymentConf.class);
            if (bean == null) {
                bean = new CarChaboshiPaymentConf();
            }
            result = new ServiceResult<>();

            int count = service.selectCount(bean);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            bean.setStartRowNum(pageEntity.getStartRowNum());
            bean.setEndRowNum(pageEntity.getEndRowNum());

            List<CarChaboshiPaymentConf> list = service.selectCarChaboshiPaymentConfList(bean);
            ListEntity<CarChaboshiPaymentConf> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("博士买家端支付金额设置列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;

    }


    /**
     * 查询查博士买家端支付金额设置
     */
    @ApiOperation(value = "查博士买家端支付金额设置")
    @RequestMapping(value = "/detail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")

    public ServiceResult<CarChaboshiPaymentConf> detail(@RequestBody JSONObject obj) {
        ServiceResult<CarChaboshiPaymentConf> result = null;
        try {
            CarChaboshiPaymentConf conf = new CarChaboshiPaymentConf();
            result = new ServiceResult<>();
            List<CarChaboshiPaymentConf> list = service.selectCarChaboshiPaymentConfList(new CarChaboshiPaymentConf());
            if (list != null && list.size() > 0) {
                conf = list.get(0);
            }
            result.setResult(conf);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("博士买家端支付金额设置列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 新增保存查博士买家端支付金额设置
     */
    @ApiOperation(value = "新增保存查博士买家端支付金额设置")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();

        try {
            //TODO 赋值参数
            CarChaboshiPaymentConf bean = JSONObject.toJavaObject(obj, CarChaboshiPaymentConf.class);
            if (bean == null) {
                bean = new CarChaboshiPaymentConf();
            }
            bean.setId(idWorker.nextId());
            int code = service.insertCarChaboshiPaymentConf(bean);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增保存查博士买家端支付金额设置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 修改保存查博士买家端支付金额设置
     */
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarChaboshiPaymentConf bean = JSONObject.toJavaObject(obj, CarChaboshiPaymentConf.class);
            if (bean == null) {
                bean = new CarChaboshiPaymentConf();
            }
            int code = service.updateCarChaboshiPaymentConf(bean);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改保存查博士买家端支付金额设置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除查博士买家端支付金额设置
     */
    @ApiOperation(value = "删除查博士买家端支付金额设置")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String ids = obj.getString("ids");
            int code = service.deleteCarChaboshiPaymentConfByIds(ids);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除查博士日志", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


}
