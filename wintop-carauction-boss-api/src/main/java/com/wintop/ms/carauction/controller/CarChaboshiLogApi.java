package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.*;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
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
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 查博士日志 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "carChaboshiLog")
public class CarChaboshiLogApi {

    private static final Logger logger = LoggerFactory.getLogger(CarChaboshiLogApi.class);

    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarChaboshiLogApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志列表")
    @PostMapping(value = "/list",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(value = false)
    public ResultModel list(@RequestBody Map<String,Object> map,@CurrentUserId Long managerId) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
//        map.put("userType","2");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志详情")
    @PostMapping(value = "/detail",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel detail(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/detail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 新增保存查博士日志
     */
    @ApiOperation(value = "新增保存查博士日志")
    @RequestMapping(value = "/add",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel addSave(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {

        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/add"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 修改保存查博士日志
     */
    @ApiOperation(value = "修改查博士日志")
    @RequestMapping(value = "/edit",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel editSave(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/edit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 删除查博士日志
     */
    @ApiOperation(value = "删除查博士日志")
    @RequestMapping(value = "/remove",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel remove(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/remove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查博士查询
     */
    @ApiOperation(value = "查博士查询")
    @RequestMapping(value = "/vinSearch",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel vinSearch(@CurrentUser CarManagerUser managerUser, @RequestBody Map<String,Object> map) {
        map.put("userId",managerUser.getId());
        map.put("userName",managerUser.getUserName());
//        map.put("storeId",managerUser.getDepartmentId());
        map.put("userType","2");//店铺人员
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/vinSearch"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志列表")
    @PostMapping(value = "/buyerPayLog",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(value = false)
    public ResultModel buyerPayLog(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/buyerPayLog"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @AuthPublic
    @PostMapping( value = "/export" )
    public void export(HttpServletRequest request, HttpServletResponse rep,
                             @RequestParam("userType") String userType,
                             @RequestParam("sourceType") Long sourceType,
                             @RequestParam("responseResult") String responseResult,
                             @RequestParam("buyerSearchName") Long buyerSearchName) {
        String[] headers = {"客户姓名","客户电话","查询车辆","查询版本","查询时间","查询结果","报告来源"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("userType", userType);
        map.put("sourceType", sourceType);
        map.put("responseResult", responseResult);
        map.put("buyerSearchName", buyerSearchName);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("查博士买家记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carChaboshiLog/allList"))
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
                    c0.setCellValue(object.getJSONObject("wtAppUser").getString("name"));


                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(object.getJSONObject("wtAppUser").getString("mobile"));

                    String carInfo = "";
                    if (object.getJSONObject("carChaboshiVinData") != null) {
                        carInfo = String.format("%s %s",object.getJSONObject("carChaboshiVinData").getString("modelName"),
                                object.getJSONObject("carChaboshiVinData").getString("seriesName"));
                    }
                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(carInfo);

                    String editionMoney = "";
                    if (object.getJSONObject("carChaboshiPaymentConf") != null) {
                        editionMoney = object.getString("edition").equals("1")
                                ? object.getJSONObject("carChaboshiPaymentConf").getString("payment") :
                                object.getJSONObject("carChaboshiPaymentConf").getString("paymentComposite");
                        editionMoney = editionMoney + "元";
                        editionMoney = getEdition(object.getString("edition")) + ' ' + editionMoney;
                    }
                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(editionMoney);

                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(sdf.format(object.getDate("finishTime")));

                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(getResponseResult(object.getString("responseResult")));

                    HSSFCell c6 = itemRow.createCell(6);
                    c6.setCellValue(getSourceType(object.getString("sourceType")));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "查博士买家列表")).concat(".xls");
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
                       @RequestParam("userType") String userType,
                       @RequestParam("sourceType") Long sourceType,
                       @RequestParam("responseResult") String responseResult,
                       @RequestParam("sellerSearchName") Long sellerSearchName) {
        String[] headers = {"操作人","电话","所属店铺","查询车辆","查询版本","查询金额（元）","查询时间","查询结果","报告来源"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("userType", userType);
        map.put("sourceType", sourceType);
        map.put("responseResult", responseResult);
        map.put("sellerSearchName", sellerSearchName);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("查博士卖家记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carChaboshiLog/allList"))
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
                    c0.setCellValue(object.getJSONObject("carManagerUser").getString("userName"));


                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(object.getJSONObject("carManagerUser").getString("userPhone"));

                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(object.getJSONObject("carStore").getString("simpleName"));

                    String carInfo = "";
                    if (object.getJSONObject("carChaboshiVinData") != null) {
                        carInfo = String.format("%s %s",object.getJSONObject("carChaboshiVinData").getString("modelName"),
                                object.getJSONObject("carChaboshiVinData").getString("seriesName"));
                    }
                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(carInfo);



                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(getEdition(object.getString("edition")));

                    String editionMoney = "";
                    if (object.getJSONObject("carChaboshiPaymentConf") != null) {
                        editionMoney = object.getString("edition").equals("1")
                                ? object.getJSONObject("carChaboshiPaymentConf").getString("payment") :
                                object.getJSONObject("carChaboshiPaymentConf").getString("paymentComposite");

                    }
                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(editionMoney);


                    HSSFCell c6 = itemRow.createCell(6);
                    c6.setCellValue(sdf.format(object.getDate("createTime")));

                    HSSFCell c7 = itemRow.createCell(7);
                    c7.setCellValue(getResponseResult(object.getString("responseResult")));

                    HSSFCell c8 = itemRow.createCell(8);
                    c8.setCellValue(getSourceType(object.getString("sourceType")));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "查博士卖家列表")).concat(".xls");
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


    private String getEdition(String key) {
        Map<String, String> map = new HashMap<String, String>(){{
            put("1", "维修版");
            put("2", "综合版");
        }};
        return map.get(key);
    }

    private String getResponseResult(String key) {
        Map<String, String> map = new HashMap<String, String>() {{
            put("1", "查询成功");
            put("2", "查询失败");
            put("3", "查询中");
            put("4", "查询失败-已退款");
        }};
        return map.get(key);
    }

    private String  getSourceType(String key) {
        Map<String, String> map = new HashMap<String, String>() {{
            put("1", "查博士");
            put("2", "数据库");
        }};
        return map.get(key);
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志列表")
    @PostMapping(value = "/allList",produces="application/json; charset=UTF-8")
    public ResultModel allList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/allList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志列表")
    @PostMapping(value = "/refund",produces="application/json; charset=UTF-8")
    public ResultModel refund(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/refund"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @AuthPublic
    @ApiOperation(value = "查博士回调")
    @PostMapping("cbsCallback")
    public Map cbsCallback(@RequestParam String result, @RequestParam String message, @RequestParam String orderid) {
        logger.info("查博士回调通知");
        Map map = new HashMap();
        map.put("result",result);
        map.put("message",message);
        map.put("orderid",orderid);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/cbsCallback"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        Map resultMap = new HashMap();
        resultMap.put("code", "1");
        resultMap.put("message", "接口访问失败");
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject object = response.getBody();
            resultMap.put("code", object.getString("code"));
            resultMap.put("message", object.getString("message"));
        }
        return resultMap;
    }
}
