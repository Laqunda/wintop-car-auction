package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;
import java.util.Map;

/**
 * describe: 评价数据记录
 * @author mazg
 * @date 2019-5-7
 */
@RestController
@RequestMapping("/carEvaluateDataApi")
public class CarEvaluateDataApi {

    private ResultModel resultModel;
    private final RestTemplate restTemplate;

    public CarEvaluateDataApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询评估列表
     */
    @ApiOperation(value = "查询评估列表")
    @PostMapping(value = "/list", produces = "application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel list(@RequestBody Map<String, Object> map) {
        if (map.get("page") == null || map.get("limit") == null) {
            return new ResultModel(false, ResultCode.NO_PARAM.value(), ResultCode.NO_PARAM.getRemark(), null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carEvaluateDataApi/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @AuthPublic
    @PostMapping( value = "/reportExport" )
    public void reportExport(HttpServletRequest request, HttpServletResponse rep,
                       @RequestParam("storeName") String storeName,
                       @RequestParam("type") Long type) {
        String[] headers = {"会员信息","评价详情","车辆名称","车辆编号","发拍人","车辆所在店铺","评价时间"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("storeName", storeName);
        map.put("type", type);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("报告评价记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carEvaluateDataApi/allList"))
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

                    String userInfo = "";
                    JSONObject wtAppUser = object.getJSONObject("wtAppUser");
                    if (wtAppUser != null) {
                         userInfo = String.format("%s (%s)", wtAppUser.getString("name"), wtAppUser.getString("mobile"));
                    }
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(userInfo);

                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(object.getString("content"));

                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(object.getJSONObject("carAuto").getString("address"));



                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(object.getJSONObject("carAuto").getString("carAutoNo"));


                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(object.getJSONObject("carAuto").getString("publishUserName"));

                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(object.getJSONObject("carAuto").getString("storeName"));

                    HSSFCell c6 = itemRow.createCell(6);
                    c6.setCellValue(sdf.format(object.getDate("createTime")));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "报告评价信息列表")).concat(".xls");
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
    @PostMapping( value = "/localeExport" )
    public void localeExport(HttpServletRequest request, HttpServletResponse rep,
                       @RequestParam("title") String title,
                       @RequestParam("beginTime") String beginTime,
                       @RequestParam("endTime") String endTime,
                       @RequestParam("type") Long type) {
        String[] headers = {"成员信息","评价详情","拍卖场次","场次编号","场次地点","评价时间"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("title", title);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("type", type);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("现场服务评价记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carEvaluateDataApi/allList"))
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

                    String userInfo = "";
                    JSONObject wtAppUser = object.getJSONObject("carManagerUser");
                    if (wtAppUser != null) {
                        userInfo = String.format("%s (%s)", wtAppUser.getString("userName"), wtAppUser.getString("userPhone"));
                    }
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(userInfo);

                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(object.getString("content"));

                    String titleInfo = String.format("场次主题:%s 开拍时间%s",
                            object.getJSONObject("carLocaleAuction").getString("title"),
                            sdf.format(object.getJSONObject("carLocaleAuction").getDate("startTime"))
                    );
                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(titleInfo);

                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(object.getJSONObject("carLocaleAuction").getString("code"));


                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(object.getJSONObject("carLocaleAuction").getString("address"));

                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(sdf.format(object.getDate("createTime")));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "现场服务评价信息列表")).concat(".xls");
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
    @PostMapping( value = "/orderExport" )
    public void orderExport(HttpServletRequest request, HttpServletResponse rep,
                             @RequestParam("storeName") String storeName,
                             @RequestParam("auctionType") String auctionType,
                             @RequestParam("type") Long type) {
        String[] headers = {"订单编号","会员信息","评价详情","拍卖场次","车辆编号","发拍人","车辆所在店铺"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("storeName", storeName);
        map.put("auctionType", auctionType);
        map.put("type", type);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("成交车辆评价记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carEvaluateDataApi/allList"))
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
                    c0.setCellValue(object.getJSONObject("carOrder").getString("orderNo"));

                    String userInfo = "";
                    JSONObject wtAppUser = object.getJSONObject("carManagerUser");
                    if (wtAppUser != null) {
                        userInfo = String.format("%s (%s)", wtAppUser.getString("userName"), wtAppUser.getString("userPhone"));
                    }
                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(userInfo);


                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(object.getString("content"));

                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(object.getJSONObject("carLocaleAuction").getString("title"));

                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(object.getJSONObject("carOrder").getString("carAutoNo"));

                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(object.getJSONObject("carAuto").getString("publishUserName"));

                    HSSFCell c6 = itemRow.createCell(6);
                    c6.setCellValue(object.getJSONObject("carOrder").getString("storeName"));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "成交车辆评价信息列表")).concat(".xls");
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
    @PostMapping( value = "/sellerExport" )
    public void sellerExport(HttpServletRequest request, HttpServletResponse rep,
                            @RequestParam("storeName") String storeName,
                            @RequestParam("type") Long type) {
        String[] headers = {"卖家用户名","卖家电话","评价详情","车辆名称","车辆编号","车辆所在店铺","评价时间"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("storeName", storeName);
        map.put("type", type);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("卖家评价记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carEvaluateDataApi/allList"))
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
                    c0.setCellValue(object.getJSONObject("carAutoAuction").getString("ownerName"));


                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(object.getJSONObject("carAutoAuction").getString("ownerMobile"));


                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(object.getString("content"));

                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(object.getJSONObject("carAuto").getString("autoInfoName"));

                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(object.getJSONObject("carAuto").getString("carAutoNo"));

                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(object.getJSONObject("carAuto").getString("storeName"));

                    HSSFCell c6 = itemRow.createCell(6);
                    c6.setCellValue(sdf.format(object.getDate("createTime")));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "卖家评价信息列表")).concat(".xls");
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