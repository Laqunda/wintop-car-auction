package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAssessOrder;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAssessOrderService;
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
 * 评估采购单 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "/service/carAssessOrder")
public class CarAssessOrderApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAssessOrderApi.class);
    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarAssessOrderService carAssessOrderService;


    /**
     * 查询评估采购单列表
     */
    @ApiOperation(value = "查询评估采购单列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAssessOrder>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAssessOrder>> result = null;
        try {
            //TODO 赋值参数
            CarAssessOrder carAssessOrder = JSONObject.toJavaObject(obj, CarAssessOrder.class);
            if (carAssessOrder == null) {
                carAssessOrder = new CarAssessOrder();
            }
            result = new ServiceResult<>();

            int count = carAssessOrderService.selectAssessOrderCount(carAssessOrder);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            carAssessOrder.setStartRowNum(pageEntity.getStartRowNum());
            carAssessOrder.setEndRowNum(pageEntity.getEndRowNum());


            List<CarAssessOrder> list = carAssessOrderService.selectCarAssessOrderList(carAssessOrder);

            ListEntity<CarAssessOrder> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);

            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询评估采购单列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }


    /**
     * 新增保存评估采购单
     */
    @ApiOperation(value = "新增保存评估采购单")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssessOrder carAssessOrder = JSONObject.toJavaObject(obj, CarAssessOrder.class);
            if (carAssessOrder == null) {
                carAssessOrder = new CarAssessOrder();
            }
            carAssessOrder.setId(idWorker.nextId());
            int code = carAssessOrderService.insertCarAssessOrder(carAssessOrder);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增保存评估采购单", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 修改保存评估采购单
     */
    @ApiOperation(value = "修改保存评估采购单")
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            //TODO 赋值参数
            CarAssessOrder carAssessOrder = JSONObject.toJavaObject(obj, CarAssessOrder.class);
            if (carAssessOrder == null) {
                carAssessOrder = new CarAssessOrder();
            }
            int code = carAssessOrderService.updateCarAssessOrder(carAssessOrder);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改保存评估采购单", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除评估采购单
     */
    @ApiOperation(value = "删除评估采购单")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String ids = obj.getString("ids");
            int code = carAssessOrderService.deleteCarAssessOrderByIds(ids);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除评估采购单", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

}
