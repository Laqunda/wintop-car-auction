package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import com.wintop.ms.carauction.util.utils.ParamValidUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-19
 */
@RestController
@RequestMapping("/order")
public class CarOrderApi {
    private final RestTemplate restTemplate;
    CarOrderApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据用户Id查询用的订单
     *@Author:zhangzijuan
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "根据用户Id查询用的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getUserOrder",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getUserOrder(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null ||map.get("userId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderByUserId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 订单列表
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "订单列表")
    @RequestMapping(value = "/selectOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 争议订单列表
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "争议订单列表")
    @RequestMapping(value = "/selectBreachOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectBreachOrderList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectBreachOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单详情信息
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "查询订单详情信息")
    @RequestMapping(value = "/selectOrderDetailById",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderDetailById(@RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderDetailById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单争议信息
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "查询订单争议信息")
    @RequestMapping(value = "/selectOrderBreach",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderBreach(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderBreach"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单轨迹
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "查询订单轨迹")
    @RequestMapping(value = "/selectOrderLogList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderLogList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderLogList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 保存线下订单支付确认
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "保存线下订单支付确认")
    @RequestMapping(value = "/saveOfflineOrderPay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveOfflineOrderPay(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("orderId")==null || map.get("payFee")==null ||map.get("payWay")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveOfflineOrderPay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 争议需支付违约金订单列表
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "争议需支付违约金订单列表")
    @RequestMapping(value = "/selectBreachPayOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectBreachPayOrderList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectBreachPayOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 保存订单违约金支付确认
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "保存订单违约金支付确认")
    @RequestMapping(value = "/saveBreachOrderPay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveBreachOrderPay(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(ParamValidUtil.invalidMapParam(map,"orderId") || ParamValidUtil.invalidMapParam(map,"breachId") || ParamValidUtil.invalidMapParam(map,"payWay")){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveBreachOrderPay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 手续回传待确认订单列表
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "手续回传待确认订单列表")
    @RequestMapping(value = "/selectProcedurePassbackOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectProcedurePassbackOrderList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectProcedurePassbackOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单手续信息
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "查询订单手续信息")
    @RequestMapping(value = "/selectProcedurePassback",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectProcedurePassback(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectProcedurePassback"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 审核回传手续信息
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "审核回传手续信息")
    @RequestMapping(value = "/saveProcedurePassback",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveProcedurePassback(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("orderId")==null){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveProcedurePassback"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "查询订单打印信息")
    @RequestMapping(value = "/selectOrderPrintInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderPrintInfo(@RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderPrintInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "订单用户信息补全")
    @RequestMapping(value = "/updateOrderUser",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateOrderUser(@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null || map.get("id")==null){
            return new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/updateOrderUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     *
     * @param request
     * @param rep
     * @param orderStatus
     * @param orderName
     * @param auctionType
     * @param authorization
     */
    @AuthPublic
    @PostMapping( value = "/export" )
    public void export(HttpServletRequest request, HttpServletResponse rep,
                       @RequestParam("orderStatus") String orderStatus,
                       @RequestParam("orderName") String orderName,
                       @RequestParam("auctionType") String auctionType,
                       @RequestParam("authorization") String authorization) {
        String[] headers = {"订单编号","买家姓名","买家电话","拍牌号","车辆编号","车辆名称","竞拍类型","成交价","应付总金额","实付总金额","订单状态","创建时间"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("orderStatus",orderStatus);
        map.put("searchName",orderName);
        map.put("auctionType",auctionType);
        if (StringUtils.isNotEmpty(authorization)) {
            map.put("userId", StringUtils.split(authorization,"_")[0]);
        }
        String orderExportName = auctionType.equals("1") ? "线上" : "现场";
        HSSFWorkbook workbook = ExcelUtil.createStartExcel(orderExportName+"订单记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carOrderBoss/selectOrderAllList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        HSSFSheet sheet = workbook.getSheetAt(0);
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONObject("result").getJSONArray("list");
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    JSONObject object = result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i + 2);
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(object.getString("orderNo"));

                    HSSFCell c12 = itemRow.createCell(1);
                    c12.setCellValue(object.getString("userName"));

                    HSSFCell c1 = itemRow.createCell(2);
                    c1.setCellValue(object.getString("mobile"));

                    HSSFCell c2 = itemRow.createCell(3);
                    c2.setCellValue(object.getString("auctionPlateNum"));

                    HSSFCell c3 = itemRow.createCell(4);
                    c3.setCellValue(object.getString("carAutoNo"));

                    HSSFCell c4 = itemRow.createCell(5);
                    c4.setCellValue(object.getString("autoInfoName"));

                    HSSFCell c5 = itemRow.createCell(6);
                    c5.setCellValue(getAuctionType(object.getString("auctionType")));

                    HSSFCell c6 = itemRow.createCell(7);
                    c6.setCellValue(object.getString("transactionFee"));

                    HSSFCell c7 = itemRow.createCell(8);
                    c7.setCellValue(object.getString("amountFee"));

                    HSSFCell c8 = itemRow.createCell(9);
                    c8.setCellValue(object.getString("payFee"));

                    HSSFCell c9 = itemRow.createCell(10);
                    c9.setCellValue(getOrderStatus(object.getString("status")));

                    HSSFCell c10 = itemRow.createCell(11);
                    c10.setCellValue(sdf.format(object.getDate("createTime")));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, orderExportName+"信息列表")).concat(".xls");
        rep.setContentType("application/vnd.ms-excel;charset=utf-8");
        rep.setHeader("Content-disposition", "attachment;filename=" + filename);
        try {
            ServletOutputStream ouputStream = rep.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getOrderStatus(String key) {
        HashMap<String, String> map = new HashMap<String, String>() {{
            put("1", "等待付款");
            put("2", "付款信息待审核");
            put("3", "过户处理中");
            put("4", "争议处理中");
            put("5", "违约金支付确认中");
            put("6", "手续回传待确认");
            put("7", "交易完成");
            put("8", "交易关闭");
        }};
        return MapUtils.getString(map, key);
    }

    /**
     * 竞拍类型
     */
    public static String getAuctionType(String key) {
        HashMap<String, String> map = new HashMap<String, String>() {{
            put("1", "线上竞拍");
            put("2", "现场竞拍");
        }};
        return MapUtils.getString(map, key);
    }
}
