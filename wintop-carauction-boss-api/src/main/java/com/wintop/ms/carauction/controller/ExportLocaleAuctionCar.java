package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.wintop.core.util.UtilDate;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:参怕车辆的导出
 * @date 2018-06-22
 */
@Controller
@RequestMapping("/exportLocalCar")
public class ExportLocaleAuctionCar {
    private final RestTemplate restTemplate;
    ExportLocaleAuctionCar(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @PostMapping(value = "/exportAuctionCarList",produces="application/json; charset=UTF-8")
    @AuthPublic
    public void exportBidRecordList(HttpServletRequest request, HttpServletResponse rep,
                                    @RequestParam("status") String status,
                                    @RequestParam("title") String title,
                                    @RequestParam("cityId") Long cityId,
                                    @RequestParam("beginTime") String beginTime,
                                    @RequestParam("endTime") String endTime) {
        String[] headers={"开拍时间","场次主题","车辆编号","车辆标题","车牌号","所属店铺","起拍价(元)","保留价（元）","最后出价（元）","车商号","状态","发车人"};
        HSSFWorkbook workbook= ExcelUtil.createStartExcel("参拍车辆列表",headers);
        Map<String,Object> map=new HashMap<>();
        map.put("status",status);
        map.put("cityId",cityId);
        map.put("title",title);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/hasAuctionCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        ApiUtil.getResultModel(response,ApiUtil.OBJECT);
        HSSFSheet sheet=workbook.getSheetAt(0);
        if(response.getStatusCode()== HttpStatus.OK){
            JSONObject obj = response.getBody();
            JSONObject content = obj.getJSONObject("result");
            JSONArray result=content.getJSONArray("list");
            if (result!=null && result.size()>0){
                for (int i=0;i<result.size();i++){
                    JSONObject object=result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i+2);
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(UtilDate.dataFormat(object.getDate("startTime")));

                    HSSFCell c12 = itemRow.createCell(1);
                    c12.setCellValue(object.getString("carAutoNo"));

                    HSSFCell c10 = itemRow.createCell(2);
                    c10.setCellValue(object.getString("title"));

                    HSSFCell c1 = itemRow.createCell(3);
                    c1.setCellValue(object.getString("autoInfoName"));

                    HSSFCell c2 = itemRow.createCell(4);
                    c2.setCellValue(object.getString("licenseNumber"));

                    HSSFCell c3 = itemRow.createCell(5);
                    c3.setCellValue(object.getString("storeName"));

                    HSSFCell c4 = itemRow.createCell(6);
                    c4.setCellValue(object.getString("startingPrice"));

                    HSSFCell c5 = itemRow.createCell(7);
                    c5.setCellValue(object.getString("reservePrice"));

                    HSSFCell c6 = itemRow.createCell(8);
                    c6.setCellValue(object.getString("topBidPrice"));

                    HSSFCell c8 = itemRow.createCell(9);
                    c8.setCellValue(object.getString("userNum"));

                    HSSFCell c7 = itemRow.createCell(10);
                    if ("0".equals(object.getString("auctionStatus"))){
                        c7.setCellValue("等待开拍");
                    }else if ("1".equals(object.getString("auctionStatus"))){
                        c7.setCellValue("拍卖中");
                    }else if ("2".equals(object.getString("auctionStatus"))){
                        c7.setCellValue("已成交");
                    }else if ("3".equals(object.getString("auctionStatus"))){
                        c7.setCellValue("流拍");
                    }else if ("4".equals(object.getString("auctionStatus"))){
                        c7.setCellValue("已设置二拍");
                    }else {
                        c7.setCellValue("");
                    }
                    HSSFCell c9 = itemRow.createCell(11);
                    c9.setCellValue(object.getString("publishUserName"));

                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request,"参拍车辆列表")).concat(".xls");
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
