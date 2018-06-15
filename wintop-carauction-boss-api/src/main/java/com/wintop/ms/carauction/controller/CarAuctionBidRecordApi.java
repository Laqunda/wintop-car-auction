package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.core.util.UtilDate;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:出价记录
 * @date 2018-03-19
 */
@Controller
@RequestMapping("/bid")
public class CarAuctionBidRecordApi {
    private final RestTemplate restTemplate;
    CarAuctionBidRecordApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取用户出价的车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "获取用户出价的车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getBidCarList",produces="application/json; charset=UTF-8")
    @ResponseBody
    @AuthUserToken
    public ResultModel getBidCarList(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null ||map.get("userId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuctionBidRecord/queryBidCarListByUserId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * @Author: lizhaoyang
     * @Description: 获取车辆出价记录列表
     * @Date:2018/3/26/18:58
     **/
    @ApiOperation(value = "获取车辆出价记录列表")
    @PostMapping(value = "/getCarBidRecordList",produces="application/json; charset=UTF-8")
    @ResponseBody
    @AuthUserToken
    public ResultModel getCarBidRecordList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carBidRecordApi/queryCarBidRecordList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @PostMapping(value = "/exportBidRecordList",produces="application/json; charset=UTF-8")
    @AuthPublic
    public void exportBidRecordList(HttpServletRequest request, HttpServletResponse rep,
                                    @RequestParam("searchName") String searchName,
                                    @RequestParam("carSearchName") String carSearchName,
                                    @RequestParam("customerStoreId") Long customerStoreId,
                                    @RequestParam("carStoreId") Long carStoreId,
                                    @RequestParam("auctionType") String auctionType,
                                    @RequestParam("beginTime") String beginTime,
                                    @RequestParam("endTime") String endTime) {
        String[] headers={"会员编号","车商号","会员姓名","联系电话","会员所属店铺","车辆编号","车辆标题","车辆所属店铺","竞拍类型","起拍价(万)","出价时间","出价金额(万)"};
        HSSFWorkbook workbook= ExcelUtil.createStartExcel("出价记录",headers);
        Map<String,Object> map=new HashMap<>();
        map.put("searchName",searchName);
        map.put("carSearchName",carSearchName);
        map.put("customerStoreId",customerStoreId);
        map.put("carStoreId",carStoreId);
        map.put("auctionType",auctionType);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carBidRecordApi/exportBidRecord"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
         ApiUtil.getResultModel(response,ApiUtil.LIST);
        HSSFSheet sheet=workbook.getSheetAt(0);
        if(response.getStatusCode()== HttpStatus.OK){
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONArray("result");
            if (result!=null && result.size()>0){
                for (int i=0;i<result.size();i++){

                    JSONObject object=result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i+2);
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(object.getString("userCode"));

                    HSSFCell c12 = itemRow.createCell(1);
                    c12.setCellValue(object.getString("userNum"));

                    HSSFCell c1 = itemRow.createCell(2);
                    c1.setCellValue(object.getString("username"));

                    HSSFCell c2 = itemRow.createCell(3);
                    c2.setCellValue(object.getString("mobile"));

                    HSSFCell c3 = itemRow.createCell(4);
                    c3.setCellValue(object.getString("userStoreName"));

                    HSSFCell c4 = itemRow.createCell(5);
                    c4.setCellValue(object.getString("carAutoNo"));

                    HSSFCell c5 = itemRow.createCell(6);
                    c5.setCellValue(object.getString("autoInfoName"));

                    HSSFCell c6 = itemRow.createCell(7);
                    c6.setCellValue(object.getString("carStoreName"));

                    HSSFCell c7 = itemRow.createCell(8);
                    if ("1".equals(object.getString("auctionType"))){
                        c7.setCellValue("线上竞拍");
                    }else if ("2".equals(object.getString("auctionType"))){
                        c7.setCellValue("现场竞拍");
                    }else {
                        c7.setCellValue("");
                    }
                    HSSFCell c8 = itemRow.createCell(9);
                    c8.setCellValue(object.getString("startingPrice"));

                    HSSFCell c9 = itemRow.createCell(10);
                    c9.setCellValue(UtilDate.dataFormat(object.getDate("bidTime")));

                    HSSFCell c10 = itemRow.createCell(11);
                    c10.setCellValue(object.getString("bidFee"));

                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request,"出价记录")).concat(".xls");
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

    /**
     * @Description:根据车辆id获取车辆出价记录
     * @Date:2018/4/16
     **/
    @PostMapping(value = "/getCarBidRecordByCarId",produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResultModel getCarBidRecordByCarId(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null || map.get("carId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carBidRecordApi/queryCarBidRecordList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
