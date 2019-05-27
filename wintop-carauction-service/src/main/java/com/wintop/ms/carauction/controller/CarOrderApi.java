package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆信息接口类
 */
@RestController
@RequestMapping("/service/carOrder")
public class CarOrderApi {
    private static final Logger logger = LoggerFactory.getLogger(CarOrderApi.class);
    @Autowired
    private ICarOrderService carOrderService;
    @Autowired
    private ICarAutoTransferLogService transferLogService;
    @Autowired
    private ICarCustomerBreachService breachService;
    @Autowired
    private ICarOrderLogService orderLogService;
    @Autowired
    private ICarAutoTransferService autoTransferService;
    @Autowired
    private ICarManagerUserService iCarManagerUserService;
    @Autowired
    private ICarAgentService agentService;

    /***
     * 我的订单信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectUserOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectUserOrderList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("customerId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId",customerId);
            if(obj.get("orderStatus")!=null){
                paramMap.put("orderStatus",obj.getString("orderStatus"));
            }
            int count = carOrderService.selectUserOrderCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarOrder> carOrders = carOrderService.selectUserOrderList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarOrder carOrder:carOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carOrder.getId());
                map.put("status",carOrder.getStatus());
                map.put("vehicleAttributionCity",carOrder.getVehicleAttributionCityCn());
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("payFee",carOrder.getPayFee());
                map.put("mileage",carOrder.getMileage());
                map.put("reportColligationRanks",carOrder.getReportColligationRanks());
                map.put("reportServicingRanks",carOrder.getReportServicingRanks());
                map.put("mainPhoto",carOrder.getMainPhoto());
                map.put("carAutoNo",carOrder.getCarAutoNo());
                map.put("amountFee",carOrder.getAmountFee());
                map.put("transactionFee",carOrder.getTransactionFee());
                map.put("dealTime",carOrder.getAutoUpdateTime());
                map.put("autoAuctionId",carOrder.getAutoAuctionId());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询我的订单信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 订单详细信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectOrderDetail",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectOrderDetail(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Long orderId = obj.getLong("orderId");
            CarOrder carOrder = carOrderService.selectOrderDetail(orderId);
            if(carOrder==null){
                result.setError(ResultCode.NO_OBJECT.strValue(), ResultCode.NO_OBJECT.getRemark());
                return  result;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("id",carOrder.getId());
            map.put("status",carOrder.getStatus());
            map.put("vehicleAttributionCity",carOrder.getVehicleAttributionCity());
            map.put("vehicleAttributionCityCn",carOrder.getVehicleAttributionCityCn());
            map.put("vehicleAttributionProvince",carOrder.getVehicleAttributionProvince());
            map.put("vehicleAttributionProvinceCn",carOrder.getVehicleAttributionProvinceCn());
            map.put("autoInfoName",carOrder.getAutoInfoName());
            map.put("payFee",carOrder.getPayFee());
            map.put("mileage",carOrder.getMileage());
            map.put("reportColligationRanks",carOrder.getReportColligationRanks());
            map.put("reportServicingRanks",carOrder.getReportServicingRanks());
            map.put("mainPhoto",carOrder.getMainPhoto());
            map.put("carAutoNo",carOrder.getCarAutoNo());
            map.put("transactionFee",carOrder.getTransactionFee());
            //服务费
            BigDecimal serviceFee=carOrder.getServiceFee()!=null?carOrder.getServiceFee():new BigDecimal(0);
            //代办费
            BigDecimal agentFee=carOrder.getAgentFee()!=null?carOrder.getAgentFee():new BigDecimal(0);
            map.put("serviceFee",serviceFee.add(agentFee));
            map.put("orderNo",carOrder.getOrderNo());
            map.put("orderTime",carOrder.getCreateTime());
            map.put("payTime",carOrder.getPayTime());
            map.put("payEndTime",carOrder.getPayEndTime());
            map.put("moveToWhere",carOrder.getMoveToWhere());
            map.put("breachType",carOrder.getBreachType());
            map.put("serviceTel",null);
            map.put("isAgent",carOrder.getIfAgent());
            map.put("autoId",carOrder.getCarId());
            map.put("amountFee",carOrder.getAmountFee());
            map.put("auctionId",carOrder.getAutoAuctionId());
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单详细信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 订单过户信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/queryTransferFlowList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> queryTransferFlowList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long orderId = obj.getLong("orderId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("orderId",orderId);
            List<CarAutoTransferLog> transferLogs = transferLogService.queryTransferFlowList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAutoTransferLog transferLog:transferLogs){
                Map<String,Object> map = new HashMap<>();
                map.put("handleTime",transferLog.getHandleTime());
                map.put("photo",transferLog.getPhoto());
                map.put("type",transferLog.getType());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(list.size());
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单过户信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /**
     * 根据条件查询车辆详情
     */
    @RequestMapping(value = "/selectCarDetail",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectCarDetail(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Long carId = obj.getLong("carId");
            Map<String,Object> param = new HashMap<>();
            param.put("carId",carId);
            CarOrder carOrder = carOrderService.queryCarDetail(param);
            Map<String,Object> map = new HashMap<>();
            if(carOrder != null){
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("reservePrice",carOrder.getReservePrice());
                map.put("mainPhoto",carOrder.getMainPhoto());
                map.put("status",carOrder.getStatus());
                map.put("startingPrice",carOrder.getStartingPrice());
                map.put("authTime",carOrder.getAuthTime());
                if ("3".equals(carOrder.getStatus())){
                    //审核不通过状态取车辆审核意见
                    map.put("authMsg",carOrder.getAutoAuthMsg());
                }else {
                    map.put("authMsg", carOrder.getAuthMsg());
                }
                //开拍时间--现场拍取场次的开拍时间
                map.put("auctionStartTime","1".equals(carOrder.getAuctionType())?carOrder.getAuctionStartTime():carOrder.getStartTime());
                map.put("auctionEndTime",carOrder.getAuctionEndTime());
                map.put("sourceType",carOrder.getSourceType());
                map.put("publishUserId",carOrder.getPublishUserId());
                map.put("remark",carOrder.getRemark());
                map.put("id",carId);
                map.put("ifNew",carOrder.getIfNew());
                map.put("carAutoNo",carOrder.getCarAutoNo());
                map.put("vehicleAttributionCity",carOrder.getVehicleAttributionCityCn());
                map.put("manufactureDate",carOrder.getManufactureDate());
                map.put("environmentCn",carOrder.getEnvironmentCn());
                map.put("mileage",carOrder.getMileage());
                map.put("createTime",carOrder.getCreateTime());
                map.put("moveToWhere",carOrder.getMoveToWhere());
                map.put("createUserId",carOrder.getCreateUser());
                map.put("orderId",carOrder.getId());
                map.put("saveTime",carOrder.getAutoUpdateTime());
                map.put("ifAgent",carOrder.getIfAgent());
                map.put("auctionType",carOrder.getAuctionType());
                map.put("topBidPrice",carOrder.getTopBidPrice());
                map.put("beginRegisterDate",carOrder.getBeginRegisterDate());
                if("2".equals(carOrder.getStatus())){
                    map.put("submitTime",carOrder.getAutoUpdateTime());
                    map.put("approveTime","");
                    map.put("payTime","");
                    map.put("dealTime","");
                }else if("3".equals(carOrder.getStatus()) || "4".equals(carOrder.getStatus()) || "5".equals(carOrder.getStatus())){
                    map.put("approveTime",carOrder.getAutoUpdateTime());
                    map.put("submitTime",carOrder.getAutoUpdateTime());
                    map.put("payTime","");
                    map.put("dealTime","");
                }else if("10".equals(carOrder.getStatus())){
                    map.put("payTime",carOrder.getPayTime());
                    map.put("submitTime","");
                    map.put("approveTime","");
                    map.put("dealTime","");
                }else if("11".equals(carOrder.getStatus())){
                    map.put("payTime",carOrder.getPayTime());
                }else if("16".equals(carOrder.getStatus()) || "15".equals(carOrder.getStatus())){
                    map.put("dealTime",carOrder.getAuthTime());
                    map.put("submitTime","");
                    map.put("approveTime","");
                    map.put("payTime",carOrder.getPayTime());
                }else{
                    map.put("dealTime","");
                    map.put("submitTime","");
                    map.put("payTime","");
                }
            }
            result.setResult(map);
            map.put("approveTime","");
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询车辆详情信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }


    /***
     * 查询历史车辆列表
     */
    @RequestMapping(value = "/selectHistoryCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectHistoryCarList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long userId = obj.getLong("customerId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("userId",userId);
            if(userId != null){
                CarManagerUser carManagerUser = iCarManagerUserService.selectByPrimaryKey(userId,true);
                paramMap.put("departmentId",carManagerUser.getDepartmentId());
            }
            paramMap.put("auctionType",obj.getString("auctionType"));
            int count = carOrderService.queryHistoryCarCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarOrder> carOrders = carOrderService.queryHistoryCar(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarOrder carOrder:carOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carOrder.getId());
                map.put("mainPhoto",carOrder.getMainPhoto());
                map.put("auctionStartTime", carOrder.getAuctionStartTime());
                map.put("auctionEndTime",carOrder.getAuctionEndTime());
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("startingPrice",carOrder.getStartingPrice());;
                map.put("carAutoNo",carOrder.getCarAutoNo());
                map.put("status",carOrder.getStatus());
                map.put("transactionFee",carOrder.getTransactionFee());
                map.put("createTime",carOrder.getCreateTime());
                map.put("pulishUserName",carOrder.getPublishUserName());
                map.put("pulishTime",carOrder.getPublishTime());
                map.put("vehicleAttributionCity",carOrder.getVehicleAttributionCityCn());
                map.put("mileage",carOrder.getMileage());
                map.put("reportColligationRanks",carOrder.getReportColligationRanks());
                map.put("reportServicingRanks",carOrder.getReportServicingRanks());
                map.put("licenseNumber",carOrder.getLicenseNumber());
                map.put("storeName",carOrder.getStoreName());
                map.put("userName",carOrder.getUserName());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询历史车辆列表失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /**
     * 根据条件查询历史车辆详情
     */
    @RequestMapping(value = "/selectHistoryCarDetail",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectHistoryCarDetail(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Long carId = obj.getLong("carId");
            Long customerId = obj.getLong("customerId");
            Map<String,Object> param = new HashMap<>();
            param.put("carId",carId);
            param.put("customerId",customerId);
            Map<String,Object> map = new HashMap<>();
            CarOrder carOrder = carOrderService.queryHistoryCarDetail(param);
            if(carOrder != null){
                map.put("carAutoNo",carOrder.getCarAutoNo());
                map.put("autoInfoName",carOrder.getAutoInfoName());
                map.put("transactionFee",carOrder.getTransactionFee());
                map.put("reportColligationRanks",carOrder.getReportColligationRanks());
                map.put("reportServicingRanks",carOrder.getReportServicingRanks());
                map.put("mileage",carOrder.getMileage());
                map.put("vehicleAttributionCity",carOrder.getVehicleAttributionCity());
                map.put("views",carOrder.getViews());
                map.put("mainPhoto",carOrder.getMainPhoto());
            }else{
                map.put("carAutoNo","");
                map.put("autoInfoName","");
                map.put("transactionFee",0);
                map.put("reportColligationRanks","");
                map.put("reportServicingRanks","");
                map.put("mileage",0);
                map.put("vehicleAttributionCity","");
                map.put("views",0);
                map.put("mainPhoto","");
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询历史车辆详情失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /**
     * 根据订单id查询订单的支付金额
     */
    @PostMapping(value = "/selectOrderById",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarOrder> selectOrderById(@RequestBody JSONObject object){
        ServiceResult<CarOrder> result=new ServiceResult<>();
        CarOrder order=carOrderService.queryOrderBaseInfo(object.getLong("orderId"));
        if (order!=null){
            result.setResult(order);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }else {
            result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
        }
        return result;
    }


    /***
     * 订单过户信息
     * @param obj
     * @return
     */
    @PostMapping(value = "/queryTransferInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarAutoTransfer> queryTransferInfo(@RequestBody JSONObject obj) {
        ServiceResult<CarAutoTransfer> result = new ServiceResult<>();
        try {
            Long orderId = obj.getLong("orderId");
            CarOrder order=carOrderService.queryOrderBaseInfo(orderId);
            if (order==null){
                result.setError(ResultCode.ERROR_PARAM.strValue(),ResultCode.ERROR_PARAM.strValue());
            }
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("orderId",orderId);
            CarAutoTransfer carAutoTransfer=autoTransferService.selectByOrderIdAndAutoId(orderId,order.getCarId());
            if (carAutoTransfer!=null){
                carAutoTransfer.setLogList(transferLogService.queryTransferFlowList(paramMap));
                result.setResult(carAutoTransfer);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询订单过户信息失败",e);
            result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.strValue());
        }
        return result;
    }

    /***
     * 查询订单的联系电话
     * @param obj
     * @return
     */
    @PostMapping(value = "/getOrderContactPhone",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> getOrderContactPhone(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map=new HashMap<>();
        Long orderId=obj.getLong("orderId");
        CarOrder order=carOrderService.selectById(orderId);
        if (order!=null){
            map.put("storeMobile",order.getServiceTel());
            CarAgent agent=agentService.selectByOrderId(orderId);
            if (agent!=null){
                map.put("agentMobile",agent.getHandlerTel());
            }
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }else {
            result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.strValue());
        }
        return result;
    }
}
