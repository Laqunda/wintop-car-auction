package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
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
import java.util.Map;

@RestController
@RequestMapping("/carOrderSale")
public class CarSaleOrderListApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarSaleOrderListApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @RequestMapping(value = "/getCarSaleOrderRetailList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> getCarSaleOrderRetailList(@RequestBody Map<String,Object> map, @CurrentUserId Long userId){
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carSaleOrder/getCarSaleOrderRetailAllList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    @RequestMapping(value = "/getCarSaleOrderRetail",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> getCarSaleOrderRetail(@RequestBody Map<String,Object> map, @CurrentUserId Long userId){
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carSaleOrder/getCarSaleOrderRetail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     *
     * @param request
     * @param rep
     * @param searchName
     */
    @AuthPublic
    @PostMapping( value = "/export" )
    public void export(HttpServletRequest request, HttpServletResponse rep,
                                    @RequestParam("searchName") String searchName,
                                    @RequestParam("authorization") String authorization) {
        String[] headers = {"订单编号","车辆编号","车辆名称","车主类型","公司名/姓名","手机号","付款方式","成交价","创建时间"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("searchName",searchName);
        if (StringUtils.isNotEmpty(authorization)) {
            map.put("customerId", StringUtils.split(authorization,"_")[0]);
        }
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("零售订单记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carSaleOrder/getCarSaleOrderRetailAllList"))
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
                    c12.setCellValue(object.getString("carAutoNo"));

                    HSSFCell c1 = itemRow.createCell(2);
                    c1.setCellValue(object.getString("autoInfoName"));

                    String vehicleOwnerType = "";
                    if (object.getString("vehicleOwnerType") != null) {
                        vehicleOwnerType = object.getString("vehicleOwnerType").equals("1") ? "个人" : "公司";
                    }
                    HSSFCell c2 = itemRow.createCell(3);
                    c2.setCellValue(vehicleOwnerType);

                    HSSFCell c3 = itemRow.createCell(4);
                    c3.setCellValue(object.getString("name"));

                    HSSFCell c4 = itemRow.createCell(5);
                    c4.setCellValue(object.getString("phone"));

                    String paymentType = "";
                    if (object.getString("paymentType") != null) {
                        paymentType = object.getString("paymentType").equals("1") ? "全款" : "按揭";
                    }
                    HSSFCell c5 = itemRow.createCell(6);
                    c5.setCellValue(paymentType);

                    HSSFCell c6 = itemRow.createCell(7);
                    c6.setCellValue(object.getString("phone"));

                    HSSFCell c7 = itemRow.createCell(8);
                    c7.setCellValue(object.getString("vin"));

                    String createDate = "";
                    if (object.getString("createDate") != null) {
                        createDate = sdf.format(new Date(object.getLong("createDate")));
                    }
                    HSSFCell c8 = itemRow.createCell(8);
                    c8.setCellValue(createDate);
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "零售车辆信息列表")).concat(".xls");
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
}
