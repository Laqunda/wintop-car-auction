package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.CarAssessOrder;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.*;
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

    @Autowired
    private ICarAssessOrderService orderService;

    @Autowired
    private ICarManagerUserService managerUserService;

    @Autowired
    private ICarAssessOrderLogService orderLogService;

    @Autowired
    private ICarAssessLogService logService;

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
     * 查询车辆评估详情
     */
    @ApiOperation(value = "查询车辆评估详情")
    @RequestMapping(value = "/detail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<CarAssess> detail(@RequestBody JSONObject obj) {
        ServiceResult<CarAssess> result = null;
        try {
            //TODO 赋值参数
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            result = new ServiceResult<>();

            carAssess = carAssessService.selectCarAssessById(carAssess.getId());
            result.setResult(carAssess);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询车辆评估详情", e);
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
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), true);

            //TODO 赋值参数
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            if (carAssess.getStatus() == null) {
                carAssess.setStatus("2"); //已完成
            }
            carAssess.setCreateUser(managerUser.getId());
            carAssess.setId(idWorker.nextId());
            int code = carAssessService.insertCarAssess(carAssess);

            if (code > 0) {
                //评估创建日志
                logService.saveLog(managerUser, "创建评估", idWorker.nextId(), carAssess.getId());

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
     * 撤销申请收购
     */
    @ApiOperation(value = "撤销申请收购")
    @RequestMapping(value = "/cancel",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> cancelSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), true);

            //TODO 赋值参数
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            int code = carAssessService.updateCarAssess(carAssess);

            if (code > 0) {
                carAssess = carAssessService.selectCarAssessById(carAssess.getId());
                //order 取消
                CarAssessOrder order = new CarAssessOrder();
                order.setAssessId(carAssess.getId());

                if (carAssess.getOrder() != null)
                    order.setId(carAssess.getOrder().getId());
                order.setStatus("3");//审核撤销
                orderService.updateCarAssessOrder(order);

                //评估日志
                logService.saveLog(managerUser, "撤销申请采购", idWorker.nextId(), carAssess.getId());

                //order操作日志
                orderLogService.saveOrderLog(managerUser, "撤销审核", "3", idWorker.nextId(), order.getId());


                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("撤销申请收购", e);
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
