package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ManagerRole;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
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

import java.util.*;

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
    @Autowired
    private ICarAssessFollowDataService followDataService;
    @Autowired
    private ICarAssessService assessService;
    @Autowired
    private ICarAssessOrderLogService orderLogService;

    @Autowired
    private ICarAssessLogService logService;
    @Autowired
    private ICarManagerUserService managerUserService;

    @Autowired
    private ICarAutoService carAutoService;

    @Autowired
    private ICarAutoAuctionService auctionService;

    @Autowired
    private ICarAutoInfoDetailService autoInfoDetailService;

    @Autowired
    private ICarAutoLogService autoLogService;

    @Autowired
    private ICarManagerUserService iCarManagerUserService;

    /**
     * 查询评估采购单列表
     */
    @ApiOperation(value = "查询评估采购单列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAssessOrder>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAssessOrder>> result = new ServiceResult<>();
        try {
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));

            int count = carAssessOrderService.selectAssessOrderCount(param);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
//            carAssessOrder.setStartRowNum(pageEntity.getStartRowNum());
//            carAssessOrder.setEndRowNum(pageEntity.getEndRowNum());
            param.put("startRowNum", pageEntity.getStartRowNum());
            param.put("endRowNum", pageEntity.getEndRowNum());

            List<CarAssessOrder> list = carAssessOrderService.selectCarAssessOrderList(param);

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

        return carAssessOrderService.createAssessOrder(obj,idWorker);
    }

    /**
     * 修改保存评估采购单
     */
    @ApiOperation(value = "修改评估采购单")
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
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
            logger.info("修改评估采购单", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 采购单审核通过/不通过
     */
    @ApiOperation(value = "采购单审核通过/不通过")
    @RequestMapping(value = "/editStatus",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editStatus(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
           result = carAssessOrderService.editStatus(obj,idWorker);
        } catch (Exception e) {
            logger.info("采购单审核通过/不通过", e);
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
    /**
     * 根据条件查询采购申请
     */
    @ApiOperation(value = "根据条件查询采购申请")
    @RequestMapping(value = "/selectListByType",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>>selectListByType(@RequestBody JSONObject object) {
        ServiceResult<ListEntity<Map<String,Object>>>result = new ServiceResult<>();
        try {
            Long userId = object.getLong("userId");
            String type = object.getString("type");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("userId",userId);
            PageEntity pageEntity= CarAutoUtils.getPageParam(object);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<Map<String,Object>> list = new ArrayList<>();
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            //待我审批 -- 店铺管理员可以审批采购申请
            if(type.equals("1")){
                CarManagerUser carManagerUser = iCarManagerUserService.selectByPrimaryKey(userId,true);
                if(ManagerRole.JXD_ESCFZR.value() == carManagerUser.getRoleId()){
                    paramMap.put("storeId",carManagerUser.getDepartmentId());
                    int count = orderLogService.selectCountWaitByParams(paramMap);
                    paramMap.put("count",count);
                    listEntity.setCount(count);
                    List<CarAssessOrderLog> carAssessOrderLogs = orderLogService.selectWaitOrderList(paramMap);
                    for (CarAssessOrderLog carAssessOrderLog:carAssessOrderLogs){
                        Map<String,Object> map = new HashMap<>();
                        map.put("mainPhoto",carAssessOrderLog.getMainPhoto());
                        map.put("autoInfoName",carAssessOrderLog.getAutoInfoName());
                        map.put("creatTime",carAssessOrderLog.getCreateTime());
                        map.put("creatUser",carAssessOrderLog.getUserName());
                        map.put("orderId",carAssessOrderLog.getAssessOrderId());
                        map.put("assessId",carAssessOrderLog.getAssessId());
                        list.add(map);
                    }
                }
                listEntity.setList(list);
            }else if (type.equals("2")){
                int count = orderLogService.selectCountEndByUserId(userId);
                paramMap.put("count",count);
                listEntity.setCount(count);
                List<CarAssessOrderLog> carAssessOrderLogs = orderLogService.selectEndOrderList(paramMap);
                for (CarAssessOrderLog carAssessOrderLog:carAssessOrderLogs){
                    Map<String,Object> map = new HashMap<>();
                    map.put("mainPhoto",carAssessOrderLog.getMainPhoto());
                    map.put("autoInfoName",carAssessOrderLog.getAutoInfoName());
                    map.put("creatTime",carAssessOrderLog.getCreateTime());
                    map.put("creatUser",carAssessOrderLog.getCreateUser());
                    map.put("status",carAssessOrderLog.getStatus());
                    list.add(map);
                }
                listEntity.setList(list);
            }else if (type.equals("3")){
                //我提交的申请
                int count = carAssessOrderService.selectCountById(userId);
                paramMap.put("count",count);
                listEntity.setCount(count);
                List<CarAssessOrder> carAssessOrders = carAssessOrderService.selectUserOrderList(paramMap);
                for (CarAssessOrder carAssessOrder:carAssessOrders){
                    Map<String,Object> map = new HashMap<>();
                    map.put("mainPhoto",carAssessOrder.getMainPhoto());
                    map.put("autoInfoName",carAssessOrder.getAutoInfoName());
                    map.put("creatTime",carAssessOrder.getCreateTime());
                    map.put("creatUser",carAssessOrder.getCreateUser());
                    map.put("status",carAssessOrder.getStatus());
                    map.put("orderId",carAssessOrder.getId());
                    map.put("assessId",carAssessOrder.getAssessId());
                    list.add(map);
                }
                listEntity.setList(list);
            }
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }
}
