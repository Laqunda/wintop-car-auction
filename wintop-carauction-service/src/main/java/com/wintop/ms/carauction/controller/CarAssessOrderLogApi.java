package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssessOrderLog;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAssessOrderLogService;
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
 * 评估采购日志 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "/service/carAssessOrderLog")
public class CarAssessOrderLogApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAssessOrderLogApi.class);
    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarAssessOrderLogService carAssessOrderLogService;

    /**
     * 查询评估采购日志列表
     */
    @PostMapping("/list")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAssessOrderLog>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAssessOrderLog>> result = null;
        try {
            //TODO 赋值参数
            CarAssessOrderLog carAssessOrderLog = JSONObject.toJavaObject(obj, CarAssessOrderLog.class);
            if (carAssessOrderLog == null) {
                carAssessOrderLog = new CarAssessOrderLog();
            }

            result = new ServiceResult<>();

            int count = carAssessOrderLogService.selectAssessOrderCount(carAssessOrderLog);


            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            carAssessOrderLog.setStartRowNum(pageEntity.getStartRowNum());
            carAssessOrderLog.setEndRowNum(pageEntity.getEndRowNum());

            List<CarAssessOrderLog> list = carAssessOrderLogService.selectCarAssessOrderLogList(carAssessOrderLog);

            ListEntity<CarAssessOrderLog> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);

            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询评估采购日志列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }


    /**
     * 新增保存评估采购日志
     */
    @ApiOperation(value = "新增保存评估采购日志")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssessOrderLog carAssessOrderLog = JSONObject.toJavaObject(obj, CarAssessOrderLog.class);
            if (carAssessOrderLog == null) {
                carAssessOrderLog = new CarAssessOrderLog();
            }
            carAssessOrderLog.setId(idWorker.nextId());
            int code = carAssessOrderLogService.insertCarAssessOrderLog(carAssessOrderLog);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增保存评估采购日志", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 修改保存评估采购日志
     */
    @ApiOperation(value = "修改保存评估采购日志")
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssessOrderLog carAssessOrderLog = JSONObject.toJavaObject(obj, CarAssessOrderLog.class);
            if (carAssessOrderLog == null) {
                carAssessOrderLog = new CarAssessOrderLog();
            }
            int code = carAssessOrderLogService.updateCarAssessOrderLog(carAssessOrderLog);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改评估采购日志", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除评估采购日志
     */
    @ApiOperation(value = "删除评估采购日志")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String ids = obj.getString("ids");

            int code = carAssessOrderLogService.deleteCarAssessOrderLogByIds(ids);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除评估采购日志", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

}
