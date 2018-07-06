package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import com.wintop.ms.carauction.core.config.BreachStatusEnum;
import com.wintop.ms.carauction.core.config.OrderStatusEnum;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarCustomerBreachModel;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 车辆信息接口类
 */
@RestController
@RequestMapping("/service/carOrderBoss")
public class CarOrderBossApi {
    private static final Logger logger = LoggerFactory.getLogger(CarOrderBossApi.class);
    @Autowired
    private ICarOrderService carOrderService;
    @Autowired
    private ICarCustomerBreachService breachService;
    @Autowired
    private ICarOrderLogService orderLogService;
    @Autowired
    private ICarAuctionBidRecordService bidRecordService;
    @Autowired
    private ICarAutoTransferLogService transferLogService;
    @Autowired
    private ICarManagerUserService managerUserService;
    @Autowired
    private ICarManagerRoleService roleService;
    @Autowired
    private ICarAutoService autoService;
    @Autowired
    private IWtAppUserService userService;

    /***
     * 根据用户Id查询用的订单
     * @param obj
     * @return
     */
    @ApiOperation(value = "根据用户Id查询用户的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "/selectOrderByUserId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarOrder>> selectOrderByUserId(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarOrder>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("userId");
            Map<String,Object> paramMap = new HashMap<>();
            Integer page=obj.getInteger("page");
            Integer pageSize=obj.getInteger("limit");
            paramMap.put("startRowNum",(page-1)*pageSize);
            paramMap.put("endRowNum",pageSize);
            paramMap.put("customerId",customerId);
            int count = carOrderService.selectUserOrderCount(paramMap);
            List<CarOrder> carOrders = carOrderService.selectUserOrderList(paramMap);
            Map<String,Object> map = new HashMap<>();
            for (CarOrder carOrder:carOrders){
//                是否违约 1违约 2不违约
                map.put("orderId",carOrder.getId());
                CarCustomerBreach breach=breachService.queryNewBreachInfo(map);
                if (breach!=null){
                    carOrder.setIsBreach("1");
                    carOrder.setInitiatAuthMsg(breach.getInitiatAuthMsg());
                }else {
                    carOrder.setIsBreach("2");
                }
            }
            ListEntity<CarOrder> listEntity = new ListEntity<>();
            listEntity.setList(carOrders);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.strValue());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询我的订单信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 订单列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectOrderList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            Long userId=obj.getLong("userId");
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                paramMap.put("storeIds",storeIds);
            }
            if(obj.get("searchName")!=null){
                paramMap.put("searchName",obj.getString("searchName"));
            }
            if(obj.get("orderStatus")!=null){
                paramMap.put("orderStatus",obj.getString("orderStatus"));
            }
            if(obj.get("auctionType")!=null){
                paramMap.put("auctionType",obj.getString("auctionType"));
            }
            int count = carOrderService.countByExample(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarOrder> carOrders = carOrderService.selectByExample(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarOrder carOrder:carOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carOrder.getId());
                map.put("status",carOrder.getStatus());
                map.put("statusName", OrderStatusEnum.getDetailRemark(carOrder.getStatus()));
                map.put("transactionFee",carOrder.getTransactionFee());
                map.put("amountFee",carOrder.getAmountFee());
                map.put("orderNo",carOrder.getOrderNo());
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("payFee",carOrder.getPayFee());
                map.put("auctionType",carOrder.getAuctionType());
                map.put("payWay",carOrder.getPayWay());
                map.put("createTime",carOrder.getCreateTime());
                map.put("mainPhoto",carOrder.getMainPhoto());
                if (StringUtils.isNotBlank(carOrder.getAuctionCode())){
                    map.put("carAutoNo",carOrder.getAuctionCode());
                }else {
                    map.put("carAutoNo",carOrder.getCarAutoNo());
                }
                map.put("userName",carOrder.getUserName());
                map.put("mobile",carOrder.getMobile());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 争议订单列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectBreachOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectBreachOrderList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("orderStatus","4");
            if(obj.get("searchName")!=null){
                paramMap.put("searchName",obj.getString("searchName"));
            }
            if(obj.get("autoNo")!=null){
                paramMap.put("autoNo",obj.getString("autoNo"));
            }
            if(obj.get("autoName")!=null){
                paramMap.put("autoName",obj.getString("autoName"));
            }
            if(obj.get("licenseNumber")!=null){
                paramMap.put("licenseNumber",obj.getString("licenseNumber"));
            }
            if(obj.get("vin")!=null){
                paramMap.put("vin",obj.getString("vin"));
            }
            int count = carOrderService.countByExample(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarOrder> carOrders = carOrderService.selectByExample(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarOrder carOrder:carOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carOrder.getId());
                map.put("orderNo",carOrder.getOrderNo());
                map.put("mainPhoto",carOrder.getMainPhoto());
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("carAutoNo",carOrder.getCarAutoNo());
                map.put("cityName",carOrder.getCityName());
                map.put("registDate",carOrder.getRegistDate());
                map.put("mileage",carOrder.getMileage());
                map.put("rank",carOrder.getReportColligationRanks()+carOrder.getReportServicingRanks());
                map.put("vin",carOrder.getVin());
                map.put("licenseNumber",carOrder.getLicenseNumber());
                map.put("auctionNum",carOrder.getAuctionNum());
                map.put("userName",carOrder.getUserName());
                map.put("mobile",carOrder.getMobile());
                map.put("startingPrice",carOrder.getStartingPrice());
                map.put("reservePrice",carOrder.getReservePrice());
                map.put("auctionType",carOrder.getAuctionType());
                //*** 查询出价记录
                CarAuctionBidRecord bidRecord = bidRecordService.selectById(carOrder.getAuctionBidRecordId());
                if(bidRecord==null){
                    bidRecord = new CarAuctionBidRecord();
                }
                map.put("bidFee",bidRecord.getBidFee());
                map.put("bidTime",bidRecord.getBidTime());
                //***查询争议信息
                Map<String,Object> breachMap = new HashedMap();
                breachMap.put("orderId",carOrder.getId());
                breachMap.put("carId",carOrder.getCarId());
                CarCustomerBreach breach = breachService.queryNewBreachInfo(breachMap);
                if(breach==null){
                    breach = new CarCustomerBreach();
                }
                map.put("breachOrderStatus",breach.getBreachOrderStatus());
                map.put("breachId",breach.getId());
                map.put("initiatCn",breach.getInitiatCn());
                map.put("initiatTime",breach.getInitiatTime());
                map.put("breachObjType",breach.getBreachObjType());
                map.put("initiatMsg",breach.getInitiatMsg());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询争议订单列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询订单详情信息
     */
    @RequestMapping(value = "/selectOrderDetailById",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectOrderDetailById(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Long id = obj.getLong("id");
            CarOrder carOrder = carOrderService.selectById(id);
            if(carOrder==null){
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
                return result;
            }
            Map<String,Object> map = new HashMap<>();
            //***订单信息
            map.put("id",carOrder.getId());
            map.put("status",carOrder.getStatus());
            map.put("statusName", OrderStatusEnum.getDetailRemark(carOrder.getStatus()));
            map.put("transactionFee",carOrder.getTransactionFee());
            map.put("amountFee",carOrder.getAmountFee());
            map.put("orderNo",carOrder.getOrderNo());
            map.put("payNo",carOrder.getLogNo());
            map.put("payFee",carOrder.getPayFee());
            map.put("serviceFee",carOrder.getServiceFee());
            map.put("agentFee",carOrder.getAgentFee());
            map.put("auctionType",carOrder.getAuctionType());
            map.put("payWay",carOrder.getPayWay());
            map.put("payEvidence",carOrder.getPayEvidence());
            map.put("createTime",carOrder.getCreateTime());
            map.put("payTime",carOrder.getPayTime());
            //***车辆信息
            map.put("storeName",carOrder.getStoreName());
            map.put("autoInfoName",carOrder.getAutoInfoName());
            map.put("vin",carOrder.getVin());
            map.put("carAutoNo",carOrder.getCarAutoNo());
            //***买家信息
            map.put("userName",carOrder.getUserName());
            map.put("mobile",carOrder.getMobile());
            map.put("identityNumber",carOrder.getIdentityNumber());
            map.put("moveAddress",carOrder.getMoveAddress());
            //***争议信息
            /*Map<String,Object> paramMap = new HashedMap();
            paramMap.put("orderId",obj.getLong("orderId"));
            List<CarCustomerBreach> breachList = breachService.selectBreachList(paramMap);
            map.put("breachList",breachList);
            //***订单轨迹
            List<CarOrderLog> orderLogList = orderLogService.selectByExample(paramMap);
            map.put("orderLogList",orderLogList);*/
            //----------------
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单详情信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询订单轨迹
     */
    @RequestMapping(value = "/selectOrderLogList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarOrderLog>> selectOrderLogList(@RequestBody JSONObject obj) {
        ServiceResult<List<CarOrderLog>> result = new ServiceResult<>();
        try {
            //***订单轨迹
            Map<String,Object> paramMap = new HashedMap();
            paramMap.put("orderId",obj.getLong("orderId"));
            List<CarOrderLog> orderLogList = orderLogService.selectByExample(paramMap);
            result.setResult(orderLogList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单轨迹失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询订单争议信息
     */
    @RequestMapping(value = "/selectOrderBreach",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarCustomerBreach> selectOrderBreach(@RequestBody JSONObject obj) {
        ServiceResult<CarCustomerBreach> result = new ServiceResult<>();
        try {
            //***争议信息
            Map<String,Object> paramMap = new HashedMap();
            paramMap.put("orderId",obj.getLong("orderId"));
            List<CarCustomerBreach> breachList = breachService.selectBreachList(paramMap);
            CarCustomerBreach breach = null;
            if(breachList.size()>0){
                breach = breachList.get(0);
            }
            result.setResult(breach);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单争议信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询订单争议信息列表
     */
    @RequestMapping(value = "/selectOrderBreachList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarCustomerBreach>> selectOrderBreachList(@RequestBody JSONObject obj) {
        ServiceResult<List<CarCustomerBreach>> result = new ServiceResult<>();
        try {
            //***争议信息
            Map<String,Object> paramMap = new HashedMap();
            paramMap.put("orderId",obj.getLong("orderId"));
            List<CarCustomerBreach> breachList = breachService.selectBreachList(paramMap);
            result.setResult(breachList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单争议信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存线下订单支付确认
     */
    @RequestMapping(value = "/saveOfflineOrderPay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveOfflineOrderPay(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarFinancePayLog financePayLog = JSONObject.toJavaObject(obj,CarFinancePayLog.class);
            CarOrder carOrder = carOrderService.queryOrderBaseInfo(financePayLog.getOrderId());
            Long userId = financePayLog.getCreatePerson();
            CarManagerRole managerRole = roleService.selectByUserId(userId);
            //**,2只能操作自己的数据
            if("2".equals(managerRole.getWriteType())){
                CarAuto auto = autoService.selectById(carOrder.getCarId());
                if(userId.compareTo(auto.getCreateUser())!=0){
                    result.setError(ResultCode.NO_ALLOW_UPDATE.strValue(),ResultCode.NO_ALLOW_UPDATE.getRemark());
                    return result;
                }
            }
            if(carOrder==null || !"12".contains(carOrder.getStatus())){
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
                return result;
            }
            int count = carOrderService.saveOfflineOrderPay(carOrder,financePayLog);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存线下订单支付确认失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 争议需支付违约金订单列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectBreachPayOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectBreachPayOrderList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            if(obj.get("searchName")!=null){
                paramMap.put("searchName",obj.getString("searchName"));
            }
            if(obj.get("breachStatus")!=null){
                paramMap.put("breachStatus",obj.getString("breachStatus"));
            }
            int count = carOrderService.selectBreachPayOrderCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarOrder> carOrders = carOrderService.selectBreachPayOrderList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarOrder carOrder:carOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carOrder.getId());
                map.put("orderNo",carOrder.getOrderNo());
                map.put("mainPhoto",carOrder.getMainPhoto());
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("carAutoNo",carOrder.getCarAutoNo());
                map.put("userName",carOrder.getUserName());
                map.put("mobile",carOrder.getMobile());
                map.put("auctionType",carOrder.getAuctionType());
                map.put("breachId",carOrder.getBreachId());
                map.put("breachStatus",carOrder.getBreachStatus());
                String breachStatusValue = BreachStatusEnum.getDetailRemark("1",carOrder.getBreachStatus());
                map.put("breachStatusValue",breachStatusValue);
                map.put("breakFee",carOrder.getBreakFee());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询争议需支付违约金订单列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存违约金支付确认
     */
    @RequestMapping(value = "/saveBreachOrderPay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveBreachOrderPay(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarFinancePayLog financePayLog = JSONObject.toJavaObject(obj,CarFinancePayLog.class);
            CarOrder carOrder = carOrderService.queryOrderBaseInfo(financePayLog.getOrderId());
            //***5，违约金支付待确认
            if(carOrder==null || !"5".equals(carOrder.getStatus())){
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
                return result;
            }
            CarCustomerBreach breach = breachService.selectByPrimaryKey(obj.getLong("breachId"));
            if(breach==null || !"36".contains(breach.getStatus())){
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
                return result;
            }
            int count = carOrderService.saveBreachOrderPay(carOrder,financePayLog,breach);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存违约金支付确认失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 手续回传待确认列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectProcedurePassbackOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectProcedurePassbackOrderList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            Long userId=obj.getLong("userId");
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                paramMap.put("storeIds",storeIds);
            }
            if(obj.get("searchName")!=null){
                paramMap.put("searchName",obj.getString("searchName"));
            }
            if(obj.get("auctionType")!=null){
                paramMap.put("auctionType",obj.getString("auctionType"));
            }
            if(obj.get("moveType")!=null){
                paramMap.put("moveType",obj.getString("moveType"));
            }
            int count = carOrderService.selectProcedurePassbackOrderCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarOrder> carOrders = carOrderService.selectProcedurePassbackOrderList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarOrder carOrder:carOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carOrder.getId());
                map.put("orderNo",carOrder.getOrderNo());
                map.put("createTime",carOrder.getCreateTime());
                map.put("mainPhoto",carOrder.getMainPhoto());
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("carAutoNo",carOrder.getCarAutoNo());
                map.put("userName",carOrder.getUserName());
                map.put("mobile",carOrder.getMobile());
                map.put("auctionType",carOrder.getAuctionType());
                map.put("licenseNumber",carOrder.getLicenseNumber());
                map.put("moveToWhere", carOrder.getMoveToWhere());
                map.put("moveAddress", carOrder.getMoveAddress());
                map.put("ifAgent",carOrder.getIfAgent());
                map.put("agentCompanyName",carOrder.getAgentCompanyName());
                map.put("handleUserName",carOrder.getHandleUserName());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询手续回传待确认订单列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询订单手续信息
     *@date 2018/3/26
     *@param:
     */
    @RequestMapping(value = "/selectProcedurePassback",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarAutoTransferLog>> selectProcedurePassback(@RequestBody JSONObject object){
        ServiceResult<List<CarAutoTransferLog>> result = new ServiceResult<>();
        try {
            List<CarAutoTransferLog> list = transferLogService.selectTransferLogByOrderId(object.getLong("orderId"));
            result.setResult(list);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单手续信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 审核订单手续信息
     *@date 2018/3/26
     *@param:
     */
    @RequestMapping(value = "/saveProcedurePassback",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveProcedurePassback(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Long orderId = object.getLong("orderId");
            String authResult = object.getString("authResult");
            String authMsg = object.getString("authMsg");
            Long userId = object.getLong("userId");
            CarOrder order = carOrderService.queryOrderBaseInfo(orderId);
            //**,2只能操作自己的数据
            CarManagerRole managerRole = roleService.selectByUserId(userId);
            if("2".equals(managerRole.getWriteType())){
                CarAuto auto = autoService.selectById(order.getCarId());
                if(userId.compareTo(auto.getCreateUser())!=0){
                    result.setError(ResultCode.NO_ALLOW_UPDATE.strValue(),ResultCode.NO_ALLOW_UPDATE.getRemark());
                    return result;
                }
            }
            //**6,手续回传待确认
            if(order==null || !"6".equals(order.getStatus())){
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
                return result;
            }
            CarAutoTransfer autoTransfer = new CarAutoTransfer();
            autoTransfer.setOrderId(orderId);
            autoTransfer.setAutoId(order.getCarId());
            autoTransfer.setAuthMsg(authMsg);
            autoTransfer.setAuthManager(userId);
            autoTransfer.setAuthTime(new Date());
            if("1".equals(authResult)){
                autoTransfer.setStatus("8");
            }else{
                autoTransfer.setStatus("7");
            }
            int count = carOrderService.saveProcedurePassback(autoTransfer);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("审核订单手续信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询订单打印信息
     * @param object
     * @return
     */
    @RequestMapping(value = "/selectOrderPrintInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectOrderPrintInfo(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarOrder order = carOrderService.selectOrderPrintInfo(object.getLong("id"));
            if(order==null){
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
                return result;
            }
            Map<String,Object> map = new HashMap<>();
            if (StringUtils.isNotBlank(order.getAuctionCode())){
                map.put("carAutoNo",order.getAuctionCode());
            }else {
                map.put("carAutoNo",order.getCarAutoNo());
            }
            map.put("licenseNumber",order.getLicenseNumber());
            String name="";
            if (StringUtils.isNotBlank(order.getAutoInfoName())){
                String [] autoName=order.getAutoInfoName().split(" ");
                if (autoName!=null && autoName.length>3){
                    name=autoName[1]+autoName[2];
                }
            }
            map.put("autoInfoName",StringUtils.isNotBlank(name)?name:order.getAutoInfoName());
            map.put("vin",order.getVin());
            map.put("auctionPlateNum",order.getAuctionPlateNum());
            map.put("serviceFee",order.getServiceFee());
            map.put("transactionFee",order.getTransactionFee());
            map.put("agentFee",order.getAgentFee());
            map.put("amountFee",order.getAmountFee());
            map.put("customerName",order.getCustomerName());
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单打印信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 订单用户信息补全
     * @param object
     * @return
     */
    @RequestMapping(value = "/updateOrderUser",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateOrderUser(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Long orderId = object.getLong("id");
            Long customerId = object.getLong("userId");
            int count = carOrderService.updateOrderUser(orderId,customerId);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("订单用户信息补全失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
