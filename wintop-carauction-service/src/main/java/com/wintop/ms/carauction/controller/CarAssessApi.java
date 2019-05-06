package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAssessService;
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
 * 车辆评估 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "/service/carAssess")
public class CarAssessApi {

    private static final Logger logger = LoggerFactory.getLogger(CarAssessApi.class);
    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarAssessService carAssessService;


    /**
     * 查询车辆评估列表
     */
    @ApiOperation(value = "查询车辆评估列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAssess>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAssess>> result = null;
        try {
            //TODO 赋值参数
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            result = new ServiceResult<>();

            int count = carAssessService.selectAssessCount(carAssess);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            carAssess.setStartRowNum(pageEntity.getStartRowNum());
            carAssess.setEndRowNum(pageEntity.getEndRowNum());

            List<CarAssess> list = carAssessService.selectCarAssessList(carAssess);
            ListEntity<CarAssess> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询车辆评估列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }

    /**
     * 新增保存车辆评估
     */
    @ApiOperation(value = "新增保存车辆评估")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            carAssess.setId(idWorker.nextId());
            int code = carAssessService.insertCarAssess(carAssess);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增保存车辆评估", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 修改保存车辆评估
     */
    @ApiOperation(value = "修改车辆评估")
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            int code = carAssessService.updateCarAssess(carAssess);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改保存车辆评估", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除车辆评估
     */
    @ApiOperation(value = "删除车辆评估")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String ids = obj.getString("ids");
            int code = carAssessService.deleteCarAssessByIds(ids);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除车辆评估", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

}
