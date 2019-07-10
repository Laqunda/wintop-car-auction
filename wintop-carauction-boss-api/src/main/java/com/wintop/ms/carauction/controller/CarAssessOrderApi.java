package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 评估采购单 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping( value = "carAssessOrder" )
public class CarAssessOrderApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAssessOrderApi.class);
    private final RestTemplate restTemplate;

    CarAssessOrderApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * 查询评估采购单列表
     */
    @ApiOperation( value = "查询评估采购单列表" )
    @RequestMapping( value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResultModel list(@RequestBody Map map) {
        if (map.get("page") == null || map.get("limit") == null) {
            return new ResultModel(false, ResultCode.NO_PARAM.value(), ResultCode.NO_PARAM.getRemark(), null);
        }
//        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询评估采购单详情
     */
    @ApiOperation( value = "查询评估采购单详情" )
    @RequestMapping( value = "/detail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResultModel detail(@RequestBody Map map) {
        if (map.get("id") == null) {
            return new ResultModel(false, ResultCode.NO_PARAM.value(), ResultCode.NO_PARAM.getRemark(), null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 新增保存评估采购单
     */
    @ApiOperation( value = "新增保存评估采购单" )
    @RequestMapping( value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResultModel addSave(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId", managerId);

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/add"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    /**
     * 修改保存评估采购单
     */
    @ApiOperation( value = "修改保存评估采购单" )
    @RequestMapping( value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResultModel editSave(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/edit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 采购单审核通过/不通过
     */
    @ApiOperation( value = "采购单审核通过/不通过" )
    @RequestMapping( value = "/editStatus",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResultModel editStatus(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/editStatus"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除评估采购单
     */
    @ApiOperation( value = "删除评估采购单" )
    @RequestMapping( value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResultModel remove(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId", managerId);

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/remove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @AuthPublic
    @PostMapping( value = "/exportVerify" )
    @ApiOperation( value = "采购审核导出" )
    public void exportVerify(HttpServletRequest request, HttpServletResponse rep,
                             @RequestParam( "verifySearchName" ) Long verifySearchName,
                             @RequestParam( "startCreateTime" ) String startCreateTime,
                             @RequestParam( "endCreateTime" ) String endCreateTime) {
        String[] headers = {"订单编号", "买家信息", "车辆编号", "车辆标题", "采购价", "创建时间"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", 1);
        map.put("verifySearchName", verifySearchName);
        map.put("startCreateTime", startCreateTime);
        map.put("endCreateTime", endCreateTime);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("采购审核记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/allList"))
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
                    c0.setCellValue(object.getString("id"));

                    String customerInfo = "";
                    if (isNotEmpty(object.getString("customerName")) && isNotEmpty(object.getString("customerTel"))) {
                        customerInfo = String.format("%s\r\n%s", object.getString("customerName"), object.getString("customerTel"));
                    }
                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(customerInfo);

                    String plateNum = "";
                    String name = "";
                    if (isNotEmpty(object.getJSONObject("carAssess"))) {
                        plateNum = object.getJSONObject("carAssess").getString("plateNum");
                        name = object.getJSONObject("carAssess").getString("name");
                    }
                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(plateNum);

                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(name);

                    BigDecimal price = BigDecimal.ZERO;
                    if (isNotEmpty(object.getString("price"))) {
                        price = BigDecimal.valueOf(object.getDouble("price")).divide(BigDecimal.valueOf(Double.valueOf("10000")), 2, BigDecimal.ROUND_HALF_DOWN);
                    }
                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(price.toPlainString());

                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(sdf.format(object.getDate("createTime")));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "采购审核列表")).concat(".xls");
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

    @AuthPublic
    @PostMapping( value = "/exportCarAssessOrder" )
    @ApiOperation( value = "评估采购单导出" )
    public void exportCarAssessOrder(HttpServletRequest request, HttpServletResponse rep,
                             @RequestParam( "searchName" ) String searchName,
                             @RequestParam( "createUser" ) String createUser,
                             @RequestParam( "status" ) String status,
                             @RequestParam( "startTime" ) String startTime,
                            @RequestParam( "endTime" ) String endTime) {
        String[] headers = {"车辆名称","上牌日期","公里数(万公里)","vin码","客户名称","评估师","门店","采购日期","采购价(万元)","状态"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat registerSdf = new SimpleDateFormat("yyyy/MM");
        Map<String, Object> map = Maps.newHashMap();
        map.put("searchName", searchName);
        map.put("createUser", createUser);
        map.put("status", status);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("评估采购单记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/allList"))
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

                    // 车辆名称
                    String name = "";
                    String register = "未上牌";
                    // 上牌时间
                    String mileage = "";
                    String vin = "";
                    // 客户名称
                    String customerName = "";
                    // 评估师
                    String createUserName = "";
                    // 店铺名称
                    String storeName = "";
                    // 采购日期
                    String procurementDate = "";
                    // 价格
                    String price = "";
                    status = object.getString("status");
                    if (isNotEmpty(object.getString("customerName"))){
                        customerName = object.getString("customerName");
                    }
                    if (isNotEmpty(object.getString("price"))) {
                        price = BigDecimal.valueOf(object.getDouble("price")).divide(BigDecimal.valueOf(new Double(10000))).setScale(2, BigDecimal.ROUND_HALF_DOWN).toPlainString();
                    }
                    if (isNotEmpty(object.getJSONObject("carAssess"))) {
                        name = object.getJSONObject("carAssess").getString("name");
                        if (isNotEmpty(object.getJSONObject("carAssess").getString("beginRegisterDate"))) {
                            register = registerSdf.format(object.getJSONObject("carAssess").getDate("beginRegisterDate")) + "上牌";
                        }
                        if (isNotEmpty(object.getJSONObject("carAssess").getDouble("mileage"))) {
                            mileage = BigDecimal.valueOf(object.getJSONObject("carAssess").getDouble("mileage")).divide(BigDecimal.valueOf(new Double(10000))).setScale(2, BigDecimal.ROUND_HALF_DOWN).toPlainString();
                        }
                        vin = object.getJSONObject("carAssess").getString("vin" );
                        createUserName  = object.getJSONObject("carAssess").getString("createUserName");
                    }
                    if (isNotEmpty(object.getJSONObject("carStore"))) {
                        storeName  = object.getJSONObject("carStore").getString("name");
                    }
                    if (isNotEmpty(object.getString("procurementDate"))) {
                        procurementDate = sdf.format(object.getDate("procurementDate"));
                    }

                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(name);

                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(register);

                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(mileage);

                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(vin);

                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(customerName);

                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(createUserName);

                    HSSFCell c6 = itemRow.createCell(6);
                    c6.setCellValue(storeName);

                    HSSFCell c7 = itemRow.createCell(7);
                    c7.setCellValue(procurementDate);

                    HSSFCell c8 = itemRow.createCell(8);
                    c8.setCellValue(price);

                    HSSFCell c9 = itemRow.createCell(9);
                    c9.setCellValue(getStatusMap(status));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "评估采购单列表")).concat(".xls");
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

    public String getStatusMap(String key){
        Map<String, String> map = new HashMap<String, String>() {{
            put("1", "待审核");
            put("2", "审核通过");
            put("-1", "审核未通过");
            put("3", "审核撤销");
            put("4", "已付全款");
        }};
        return map.get(key);
    }

    private static <T> boolean isNotEmpty(T t) {
        return Optional.ofNullable(t).isPresent();
    }

}
