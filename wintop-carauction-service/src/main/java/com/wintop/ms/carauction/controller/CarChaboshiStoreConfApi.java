package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarChaboshiStoreConf;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarChaboshiStoreConfService;
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
 * 查博士店铺设置 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-08
 */
@RestController
@RequestMapping("/service/carChaboshiStoreConf")
public class CarChaboshiStoreConfApi {


    private static final Logger logger = LoggerFactory.getLogger(CarAssessApi.class);
    private IdWorker idWorker = new IdWorker(10);


    @Autowired
    private ICarChaboshiStoreConfService service;


    /**
     * 查询查博士店铺设置列表
     */
    @ApiOperation(value = "博士店铺设置列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")

    public ServiceResult<ListEntity<CarChaboshiStoreConf>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarChaboshiStoreConf>> result = null;
        try {
            CarChaboshiStoreConf bean = JSONObject.toJavaObject(obj, CarChaboshiStoreConf.class);
            if (bean == null) {
                bean = new CarChaboshiStoreConf();
            }
            result = new ServiceResult<>();

            int count = service.selectCount(bean);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            bean.setStartRowNum(pageEntity.getStartRowNum());
            bean.setEndRowNum(pageEntity.getEndRowNum());

            List<CarChaboshiStoreConf> list = service.selectCarChaboshiStoreConfList(bean);
            ListEntity<CarChaboshiStoreConf> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("博士店铺设置列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;

    }

    /**
     * 查博士店铺设置详情
     */
    @ApiOperation(value = "查博士店铺设置详情")
    @RequestMapping(value = "/detail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")

    public ServiceResult<CarChaboshiStoreConf> detail(@RequestBody JSONObject obj) {
        ServiceResult<CarChaboshiStoreConf> result = null;
        try {
            CarChaboshiStoreConf bean = JSONObject.toJavaObject(obj, CarChaboshiStoreConf.class);
            if (bean == null) {
                bean = new CarChaboshiStoreConf();
            }
            result = new ServiceResult<>();
            List<CarChaboshiStoreConf> list = service.selectCarChaboshiStoreConfList(new CarChaboshiStoreConf());
            if (list != null && list.size() > 0) {
                bean = list.get(0);
            }
            result.setResult(bean);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查博士店铺设置详情", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 新增保存查博士店铺设置
     */
    @ApiOperation(value = "新增保存查博士店铺设置")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();

        try {
            CarChaboshiStoreConf bean = JSONObject.toJavaObject(obj, CarChaboshiStoreConf.class);
            if (bean == null) {
                bean = new CarChaboshiStoreConf();
            }
            bean.setId(idWorker.nextId());
            int code = service.insertCarChaboshiStoreConf(bean);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增保存查博士店铺设置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 修改保存查博士店铺设置
     */
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarChaboshiStoreConf bean = JSONObject.toJavaObject(obj, CarChaboshiStoreConf.class);
            if (bean == null) {
                bean = new CarChaboshiStoreConf();
            }
            List<CarChaboshiStoreConf> storeConfs = service.selectCarChaboshiStoreConfList(bean);

            int code = 0;
            if (storeConfs == null || storeConfs.size() == 0) {
                //新建
                bean.setId(idWorker.nextId());
                code = service.insertCarChaboshiStoreConf(bean);
            } else {
                code = service.updateCarChaboshiStoreConf(bean);
            }

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改保存查博士店铺设置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除查博士店铺设置
     */
    @ApiOperation(value = "删除查博士店铺设置")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String ids = obj.getString("ids");
            int code = service.deleteCarChaboshiStoreConfByIds(ids);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除店铺设置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


}
