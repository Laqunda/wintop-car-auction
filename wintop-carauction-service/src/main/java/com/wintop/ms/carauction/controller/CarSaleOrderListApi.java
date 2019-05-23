package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarSaleOrder;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarSaleOrderService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/carSaleOrder")
public class CarSaleOrderListApi {
    private static final Logger logger = LoggerFactory.getLogger(CarSaleOrderListApi.class);
    @Autowired
    private ICarSaleOrderService iCarSaleOrderService;
    @ApiOperation(value = "查询零售订单")
    @PostMapping(value = "getCarSaleOrderRetailList", produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> getCarSaleOrderRetailList(@RequestBody JSONObject object){
        ServiceResult<ListEntity<Map<String,Object>>> result=new ServiceResult<>();
        try{
            Long customerId = object.getLong("customerId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId",customerId);
            int count = iCarSaleOrderService.selectCarSaleOrderCount(customerId);
            PageEntity pageEntity= CarAutoUtils.getPageParam(object);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarSaleOrder> carSaleOrders = iCarSaleOrderService.selectCarSaleOrder(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for (CarSaleOrder carSaleOrder : carSaleOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("name", carSaleOrder.getName());
                map.put("autoInfoName", carSaleOrder.getAutoInfoName());
                map.put("vehicleAttributionCityCN",carSaleOrder.getVehicleAttributionCityCN());
                map.put("mileage",carSaleOrder.getMileage());
                map.put("reportColligationRanks",carSaleOrder.getReportColligationRanks());
                map.put("reportServicingRanks",carSaleOrder.getReportServicingRanks());
                map.put("mainPhoto",carSaleOrder.getMainPhoto());
                map.put("transactionFee",carSaleOrder.getTransactionFee());
                map.put("storeName",carSaleOrder.getStoreName());
                map.put("saleDate",carSaleOrder.getSalesDate());
                map.put("salesType",carSaleOrder.getSalesType());
                map.put("managerName",carSaleOrder.getManagerName());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch(Exception e){
            logger.info("查询零售订单信息失败",e);
            result.setError("-1","异常");
        }
        return result;

    }
    @ApiOperation(value = "查询销售订单")
    @PostMapping(value = "getCarOrderList", produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> getCarOrderList(@RequestBody JSONObject object){
        ServiceResult<ListEntity<Map<String,Object>>> result=new ServiceResult<>();
        try{
            Long customerId = object.getLong("customerId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("customerId",customerId);
            int count = iCarSaleOrderService.selectCarOrderCount(customerId);
            PageEntity pageEntity= CarAutoUtils.getPageParam(object);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarSaleOrder> carSaleOrders = iCarSaleOrderService.selectCarOrder(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for (CarSaleOrder carSaleOrder : carSaleOrders){
                Map<String,Object> map = new HashMap<>();
                map.put("name", carSaleOrder.getName());
                map.put("autoInfoName", carSaleOrder.getAutoInfoName());
                map.put("vehicleAttributionCityCN",carSaleOrder.getVehicleAttributionCityCN());
                map.put("mileage",carSaleOrder.getMileage());
                map.put("reportColligationRanks",carSaleOrder.getReportColligationRanks());
                map.put("reportServicingRanks",carSaleOrder.getReportServicingRanks());
                map.put("mainPhoto",carSaleOrder.getMainPhoto());
                map.put("transactionFee",carSaleOrder.getTransactionFee());
                map.put("storeName",carSaleOrder.getStoreName());
                map.put("createTime",carSaleOrder.getCreateTime());
                map.put("salesType","线上销售");
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch(Exception e){
            logger.info("查询线上订单信息失败",e);
            result.setError("-1","异常");
        }
        return result;

    }
    @ApiOperation(value = "查询零售订单详情")
    @PostMapping(value = "getCarSaleOrderRetail", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> getCarSaleOrderRetail(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try{
            Long customerId = object.getLong("customerId");
            CarSaleOrder carSaleOrder = iCarSaleOrderService.selectCarSaleOrderRetail(customerId);
            Map<String,Object> map = new HashMap<>();
            if(carSaleOrder != null){
                map.put("autoInfoName", carSaleOrder.getAutoInfoName());
                map.put("vehicleAttributionCityCN",carSaleOrder.getVehicleAttributionCityCN());
                map.put("mileage",carSaleOrder.getMileage());
                map.put("reportColligationRanks",carSaleOrder.getReportColligationRanks());
                map.put("reportServicingRanks",carSaleOrder.getReportServicingRanks());
                map.put("mainPhoto",carSaleOrder.getMainPhoto());
                map.put("carAssess",carSaleOrder.getCarAssess());
                map.put("transactionFee",carSaleOrder.getTransactionFee());
                map.put("earnestMoney",carSaleOrder.getEarnestMoney());
                map.put("transfer",carSaleOrder.getTransfer());
                map.put("salesType",carSaleOrder.getSalesType());
                map.put("managerName",carSaleOrder.getManagerName());
                map.put("saleDate",carSaleOrder.getSalesDate());
                map.put("insuranceCompany",carSaleOrder.getInsuranceCompany());
                map.put("commercialInsuranceExpires",carSaleOrder.getCommercialInsuranceExpires());
                map.put("compulsoryInsuranceExpires",carSaleOrder.getCompulsoryInsuranceExpires());
                map.put("mortgageCharges",carSaleOrder.getMortgageCharges());
                map.put("insuranceRebate",carSaleOrder.getInsuranceRebate());
                map.put("boutiqueProfit",carSaleOrder.getTransactionFee());
                map.put("guaranteedProfit",carSaleOrder.getTransactionFee());
                map.put("addProfit",carSaleOrder.getTransactionFee());
                map.put("remarks",carSaleOrder.getTransactionFee());
                map.put("vehicleOwnerType",carSaleOrder.getTransactionFee());
                map.put("phone",carSaleOrder.getTransactionFee());
                map.put("source",carSaleOrder.getTransactionFee());
                map.put("idCard",carSaleOrder.getTransactionFee());
                map.put("address",carSaleOrder.getTransactionFee());
                map.put("drawee",carSaleOrder.getTransactionFee());
                map.put("openingBank",carSaleOrder.getTransactionFee());
                map.put("bankAccount",carSaleOrder.getTransactionFee());
                map.put("otherAccounts",carSaleOrder.getTransactionFee());
                if(carSaleOrder.getPaymentType().equals("1")){
                    map.put("paymentType",carSaleOrder.getPaymentType());
                }else{
                    map.put("paymentType",carSaleOrder.getPaymentType());
                    map.put("mortgageAmount",carSaleOrder.getPaymentType());
                    map.put("mortgagePeriod",carSaleOrder.getPaymentType());
                    map.put("monthlyRepayment",carSaleOrder.getPaymentType());
                    map.put("downPayments",carSaleOrder.getDownPayments());
                    map.put("mortgagePlan",carSaleOrder.getMortgagePlan());
                }
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch(Exception e){
            logger.info("查询零售订单详情失败",e);
            result.setError("-1","异常");
        }
        return result;
    }
}
