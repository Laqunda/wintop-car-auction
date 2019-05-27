package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.ICarLocaleAuctionTemplateService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 现场拍主题表
 *
 * @author mazg
 * @date 2019-05-23 17:40:13
 */
@RestController
@RequestMapping("/service/template")
public class CarLocaleAuctionTemplateApi {

    private static final Logger logger = LoggerFactory.getLogger(CarLocaleAuctionTemplateApi.class);


    @Autowired
    private ICarLocaleAuctionTemplateService carLocaleAuctionTemplateService;
    @Autowired
    private ICarManagerUserService managerUserService;

    /**
     * 查询分页列表
     * @param obj
     * @return
     */
    @PostMapping(value = "/list",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarLocaleAuctionTemplate>> list(@RequestBody JSONObject obj){
        ServiceResult<ListEntity<CarLocaleAuctionTemplate>> result = new ServiceResult<ListEntity<CarLocaleAuctionTemplate>>();
        logger.info("查询主题设置列表");
        CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
        if (carLocaleAuctionTemplate == null) {
            carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
        }
        try {
            int count = carLocaleAuctionTemplateService.count(carLocaleAuctionTemplate);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            carLocaleAuctionTemplate.setStartRowNum(pageEntity.getStartRowNum());
            carLocaleAuctionTemplate.setEndRowNum(pageEntity.getEndRowNum());

            List<CarLocaleAuctionTemplate> list = carLocaleAuctionTemplateService.list(carLocaleAuctionTemplate);

            ListEntity<CarLocaleAuctionTemplate> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);

            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询主题设置列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    @PostMapping(value = "/allList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> allList(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        logger.info("查询主题设置列表");
        CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
        if (carLocaleAuctionTemplate == null) {
            carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
        }
        try {
            List<CarLocaleAuctionTemplate> list = carLocaleAuctionTemplateService.list(carLocaleAuctionTemplate);
            result.setResult(Collections.singletonMap("list",list));
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询主题设置列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询主题设置详情
     */
    @ApiOperation(value = "查询主题设置详情")
    @RequestMapping(value = "/detail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<CarLocaleAuctionTemplate> detail(@RequestBody JSONObject obj) {
        ServiceResult<CarLocaleAuctionTemplate> result = null;
        try {
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            result = new ServiceResult<>();

            carLocaleAuctionTemplate = carLocaleAuctionTemplateService.get(carLocaleAuctionTemplate.getId());
            result.setResult(carLocaleAuctionTemplate);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询主题设置详情", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 修改保存车辆评估
     */
    @RequestMapping(value = "/save",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> save(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            carLocaleAuctionTemplate.setEditTime(new Date());
            carLocaleAuctionTemplate.setEditor(managerUser.getUserName());
            int code = carLocaleAuctionTemplateService.insertSelective(carLocaleAuctionTemplate);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 修改保存车辆评估
     */
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            carLocaleAuctionTemplate.setEditTime(new Date());
            carLocaleAuctionTemplate.setEditor(managerUser.getUserName());
            int code = carLocaleAuctionTemplateService.updateByPrimaryKeySelective(carLocaleAuctionTemplate);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除主题设置
     */
    @ApiOperation(value = "删除主题设置")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            int code = carLocaleAuctionTemplateService.delete(carLocaleAuctionTemplate.getId());
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除主题设置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


}
