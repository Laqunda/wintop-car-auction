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

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarAssessOrder carAssessOrder = JSONObject.toJavaObject(obj, CarAssessOrder.class);
            if (carAssessOrder == null) {
                carAssessOrder = new CarAssessOrder();
            }


            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), true);

            CarAssessFollowData follow = carAssessOrder.getFollow();
            long followId = idWorker.nextId();
            long order_id = idWorker.nextId();
            long orderlog_id = idWorker.nextId();

            int code = 0;
            if (follow != null) {
                //存评估跟进
                follow.setId(followId);
                follow.setFollowUser(managerUser.getId());
                followDataService.insertCarAssessFollowData(follow);

                //存储采购订单
                carAssessOrder.setId(order_id);
                carAssessOrder.setCreateTime(new Date());
                carAssessOrder.setFollowId(followId);
                carAssessOrder.setStatus("1");//待审核
                code = carAssessOrderService.insertCarAssessOrder(carAssessOrder);
                if (code > 0) {
                    //更改评估状态 为 3:提交审核（确认采购）
                    CarAssess assess = new CarAssess();
                    assess.setId(carAssessOrder.getAssessId());
                    assess.setStatus("3");
                    assessService.updateCarAssess(assess);

                    //评估日志
                    logService.saveLog(managerUser, "申请采购", idWorker.nextId(), carAssessOrder.getAssessId());

                    //写入订单记录表
                    orderLogService.saveOrderLog(managerUser, "提交申请采购单！", "1", orderlog_id, order_id);

                    result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
                } else {
                    result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
                }
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), "没有发现跟进信息！");

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
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), true);

            CarAssessOrder carAssessOrder = JSONObject.toJavaObject(obj, CarAssessOrder.class);
            CarAssessOrder old = carAssessOrderService.selectCarAssessOrderById(carAssessOrder.getId());
            if (carAssessOrder == null) {
                carAssessOrder = new CarAssessOrder();
            }


            //写入日日志表
            //审核通过
            String logMsg = "";
            if ("2".equals(carAssessOrder.getStatus())) {
                logMsg = "审核通过";
                //审核不通过
                //评估日志
                logService.saveLog(managerUser, "申请通过", idWorker.nextId(), old.getAssessId());

                //修改评估状态为 审核通过
                CarAssess assess = new CarAssess();
                assess.setId(old.getAssessId());
                assess.setStatus("5");
                assess.setRejectReason(obj.getString("rejectReason"));
                assessService.updateCarAssess(assess);

            } else if ("-1".equals(carAssessOrder.getStatus())) {
                logMsg = "审核不通过";
                //评估日志
                logService.saveLog(managerUser, "申请不通过", idWorker.nextId(), old.getAssessId());

                //修改评估状态为 审核不通过
                CarAssess assess = new CarAssess();
                assess.setId(old.getAssessId());
                assess.setStatus("4");
                assess.setRejectReason(obj.getString("rejectReason"));
                assessService.updateCarAssess(assess);
            }  else if ("4".equals(carAssessOrder.getStatus())) {
                logMsg = "已付全款";
            }
            int code = carAssessOrderService.updateCarAssessOrder(carAssessOrder);
            if (code > 0) {
                //写入订单记录表
                orderLogService.saveOrderLog(managerUser, logMsg, carAssessOrder.getStatus(), idWorker.nextId(), carAssessOrder.getId());
                if ("2".equals(carAssessOrder.getStatus())) {
                    /*写入库存 car_auto car_auto_auction ...*/
                    int backCode = writeAuto(old.getId(), carAssessOrder.getId(), idWorker.nextId(), managerUser);


                }
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
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
     * 写入汽车相关表
     *
     * @param assessId
     * @param orderId
     * @return
     */
    private int writeAuto(Long assessId, Long orderId, Long autoId, CarManagerUser managerUser) {


        /*更新评估表*/
        CarAssess t = new CarAssess();
        t.setId(assessId);
        t.setAutoId(autoId);
        assessService.updateCarAssess(t);


        CarAssess a = assessService.selectCarAssessById(assessId);
        CarAssessOrder oreder = carAssessOrderService.selectCarAssessOrderById(orderId);
        CarManagerUser user = managerUserService.selectByPrimaryKey(a.getCreateUser(), true);

        /*写入car_auto*/
        CarAuto auto = new CarAuto();
        auto.setId(autoId);
        auto.setPublishUserId(a.getCreateUser());
        auto.setPublishUserName(user.getUserName());
        auto.setPublishUserMobile(user.getUserPhone());
        auto.setAutoInfoName(a.getAutoBrandCn() + " " + a.getAutoSeriesCn() + " " + a.getAutoStyleCn());//车辆名称=品牌+车系+车型
        auto.setStoreId(oreder.getStoreId());
        auto.getStoreName();
        auto.setReportColligationRanks(a.getReportColligationRanks());
        auto.setPublishTime(new Date());
        auto.setCreateUser(a.getCreateUser());
        auto.setCreateTime(new Date());
        auto.setAuctionNum(0);
        if (a.getRegionId() != null) {
            auto.setRegionId(Long.parseLong(a.getRegionId()));
        }

        auto.setMainPhoto(a.getCarPhoto());
        auto.setStatus("5");//等待上拍
        carAutoService.insert(auto);

        /*写入car_auto_auction*/
        CarAutoAuction auction = new CarAutoAuction();
        auction.setId(idWorker.nextId());
        auction.setAutoId(autoId);
        auction.setCreatePerson(a.getCreateUser());
        auction.setCreateTime(new Date());

        Long roleTypeId = user.getRoleTypeId();

        if (roleTypeId == 2) {
            auction.setAuctionType("2");//线下
        }
        if (roleTypeId == 3) {
            auction.setAuctionType("1");//线上
        }
        auction.setRemark(a.getRemark());
        auctionService.insert(auction);

        /*car_auto_info_detail*/

        CarAutoInfoDetail detail = new CarAutoInfoDetail();
        detail.setId(idWorker.nextId());
        detail.setAutoId(autoId);
        detail.setVin(a.getVin());
        detail.setAutoBrand(a.getAutoBrand());
        detail.setAutoBrandCn(a.getAutoBrandCn());
        detail.setAutoSeries(a.getAutoSeries());
        detail.setAutoStyle(a.getAutoStyle());
        detail.setAutoStyleCn(a.getAutoStyleCn());
        detail.setEngineCapacity(a.getEngineCapacity() + "");
        detail.setEngineCapacityUnit("L");
        detail.setMileage(a.getMileage());
        detail.setColor(a.getColor());
        detail.setColorCn(a.getColorCn());

        detail.setManufactureDate(a.getManufactureDate());
        detail.setBeginRegisterDate(a.getBeginRegisterDate());

        detail.setLicenseNumber(a.getPlateNum());
        detail.setCarNature(a.getAutoNature());//Nature CN
        detail.setUseNature(a.getFunction());//Function CN
        detail.setIsModification(oreder.getIfAdd());
        detail.setOriginalPrice(oreder.getNewCarPrice());
        detail.setRemark(a.getRemark());
        detail.setRemarkPhoto(a.getOtherPhoto());
        detail.setCreateTime(new Date());
        detail.setCreateUser(a.getCreateUser() + "");
        autoInfoDetailService.insert(detail);
        /* car auto log*/

        CarAutoLog log = new CarAutoLog();
        log.setId(idWorker.nextId());
        log.setAutoId(autoId);
        log.setMsg("评估车辆审核通过，入库操作");
        log.setStatus("5");//等待上架
        log.setTime(new Date());
        log.setUserType("2");//TODO '操作人类型  1买家 2管理员 3代办'???,
        log.setUserId(managerUser.getId());
        log.setUserMobile(managerUser.getUserPhone());
        log.setUserName(managerUser.getUserName());
        autoLogService.insert(log);

        return 1;
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
