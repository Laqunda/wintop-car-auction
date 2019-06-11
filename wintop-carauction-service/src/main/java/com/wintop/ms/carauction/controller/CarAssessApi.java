package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @Autowired
    private ICarAutoConfDetailService carAutoConfDetailService;

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
            //店铺用户
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), true);
            Long roleTypeId = managerUser.getRoleTypeId();


            result = new ServiceResult<>();
            Long userId = obj.getLong("customerId");
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            carAssess.setCreateUser(userId);
            //额外参数
            //1 ：平台
            if (1 != roleTypeId) {
                if (carAssess.getParams() == null) {
                    carAssess.setParams(new HashMap<>());
                }
                carAssess.getParams().put("deptId", "" + managerUser.getDepartmentId());
            }

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
     * 根据跟进数据 分组
     */
    @ApiOperation(value = "根据跟进数据分组")
    @RequestMapping(value = "/groupFollowCount",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> groupFollowCount(@RequestBody JSONObject obj) {
        ServiceResult result = null;
        try {
            //店铺用户
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), true);
            Long roleTypeId = managerUser.getRoleTypeId();

            Map back = new HashMap();
            CarAssess carAssess = new CarAssess();
            result = new ServiceResult<>();

            Map params = new HashMap();
            //额外参数
            //1 ：平台
            if (1 != roleTypeId) {
                params.put("deptId", "" + managerUser.getDepartmentId());
            }
            params.put("followResult", 1);
            carAssess.setParams(params);
            back.put("followResult1", carAssessService.selectAssessCount(carAssess));

            params.put("followResult", 2);
            carAssess.setParams(params);
            back.put("followResult2", carAssessService.selectAssessCount(carAssess));

            params.put("followResult", 3);
            carAssess.setParams(params);
            back.put("followResult3", carAssessService.selectAssessCount(carAssess));

            params.put("followResult", 4);
            carAssess.setParams(params);
            back.put("followResult4", carAssessService.selectAssessCount(carAssess));

            result.setResult(back);
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
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            result = new ServiceResult<>();
            Map params = new HashMap();
            carAssess = carAssessService.selectCarAssessById(carAssess);
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
     * 查询采购审批详情
     */
    @ApiOperation(value = "查询采购审批详情")
    @RequestMapping(value = "/purchaseAuditDetail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> purchaseAuditDetail(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = null;

        try {
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            result = new ServiceResult<>();
            Map<String, Object> resultMap = carAssessService.selectAccessAuditDetail(carAssess.getAutoId());
            result.setResult(resultMap);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询采购审批详情", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 库存管理-线上车辆详情
     */
    @ApiOperation(value = "库存管理-线上车辆详情")
    @RequestMapping(value = "/onlineDetail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<CarAssess> onlineDetail(@RequestBody JSONObject obj) {
        ServiceResult<CarAssess> result = null;
        try {
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            result = new ServiceResult<>();
            carAssess = carAssessService.selectCarAssessDetailById(carAssess.getAutoId());
            List<CarAutoConfDetail> confDetailList = carAutoConfDetailService.selectConfigsByCarId(carAssess.getAutoId());
            Map<String, Object> map = Maps.newHashMap();
            if (CollectionUtils.isEmpty(confDetailList)) {
                map.put("title", "安全气囊");
                map.put("val", "无");
                carAssess.setParamConf(map);
            } else {
                String val = confDetailList.stream().filter(conf -> conf.getConfTitleId().equals(new Long(1))).map(conf -> conf.getConfTitleName()).findFirst().orElse("无");
                map.put("title", "安全气囊");
                map.put("val", val);
                carAssess.setParamConf(map);
            }
            result.setResult(carAssess);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("库存管理-线上车辆详情", e);
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
            //评估
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            if (carAssess.getStatus() == null) {
                carAssess.setStatus("2"); //已完成
            }
            carAssess.setCreateUserName(managerUser.getUserName());
            //设置name
            carAssess.setName(carAssess.getAutoBrandCn() + " " + carAssess.getAutoSeriesCn() + " " + carAssess.getAutoStyleCn());//车辆名称=品牌+车系+车型
            carAssess.setCreateUser(managerUser.getId());
            carAssess.setCreateUserName(managerUser.getUserName());
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
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), true);
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            carAssess.setName(carAssess.getAutoBrandCn() + " " + carAssess.getAutoSeriesCn() + " " + carAssess.getAutoStyleCn());//车辆名称=品牌+车系+车型
            int code = carAssessService.updateCarAssess(carAssess);

            if (code > 0 && carAssess.getOrder() != null && carAssess.getOrder().getId() != null) {
                orderService.updateCarAssessOrder(carAssess.getOrder());
                //评估创建日志
                logService.saveLog(managerUser, "申请采购", idWorker.nextId(), carAssess.getId());
                //写入订单记录表
                orderLogService.saveOrderLog(managerUser, "提交申请采购单！", "1", idWorker.nextId(), carAssess.getOrder().getId());
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
            CarAssess carAssess = JSONObject.toJavaObject(obj, CarAssess.class);
            if (carAssess == null) {
                carAssess = new CarAssess();
            }
            int code = carAssessService.updateCarAssess(carAssess);
            if (code > 0) {
                carAssess = carAssessService.selectCarAssessById(carAssess);
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
                orderLogService.saveOrderLog(managerUser, obj.getString("logMsg"), "3", idWorker.nextId(), order.getId());
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
