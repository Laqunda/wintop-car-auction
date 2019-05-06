package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssessFollowData;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAssessFollowDataService;
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
 * 车辆评估跟进 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "/service/carAssessFollowData")
public class CarAssessFollowDataApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAssessFollowDataApi.class);
    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarAssessFollowDataService carAssessFollowDataService;

    /**
     * 查询车辆评估跟进列表
     */
    @ApiOperation(value = "查询车辆评估跟进列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")

    public ServiceResult<ListEntity<CarAssessFollowData>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAssessFollowData>> result = null;
        try {
            //TODO 赋值参数
            CarAssessFollowData carAssessFollowData = JSONObject.toJavaObject(obj, CarAssessFollowData.class);
            if (carAssessFollowData == null) {
                carAssessFollowData = new CarAssessFollowData();
            }
            result = new ServiceResult<>();

            int count = carAssessFollowDataService.selectAssessFollowDataCount(carAssessFollowData);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            carAssessFollowData.setStartRowNum(pageEntity.getStartRowNum());
            carAssessFollowData.setEndRowNum(pageEntity.getEndRowNum());


            result = new ServiceResult<>();
            List<CarAssessFollowData> list = carAssessFollowDataService.selectCarAssessFollowDataList(carAssessFollowData);

            ListEntity<CarAssessFollowData> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);

            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询车辆评估跟进列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }


    /**
     * 新增保存车辆评估跟进
     */
    @ApiOperation(value = "新增车辆评估跟进")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssessFollowData carAssessFollowData = JSONObject.toJavaObject(obj, CarAssessFollowData.class);
            if (carAssessFollowData == null) {
                carAssessFollowData = new CarAssessFollowData();
            }
            carAssessFollowData.setId(idWorker.nextId());
            int code = carAssessFollowDataService.insertCarAssessFollowData(carAssessFollowData);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增车辆评估跟进", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 修改保存车辆评估跟进
     */
    @ApiOperation(value = "修改车辆评估跟进")
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssessFollowData carAssessFollowData = JSONObject.toJavaObject(obj, CarAssessFollowData.class);
            if (carAssessFollowData == null) {
                carAssessFollowData = new CarAssessFollowData();
            }
            carAssessFollowData.setId(idWorker.nextId());
            int code = carAssessFollowDataService.updateCarAssessFollowData(carAssessFollowData);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改车辆评估跟进", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除车辆评估跟进
     */
    @ApiOperation(value = "删除车辆评估跟进")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String ids = obj.getString("ids");
            int code = carAssessFollowDataService.deleteCarAssessFollowDataByIds(ids);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除车辆评估跟进", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

}
