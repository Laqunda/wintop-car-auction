package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.entity.CarPriceExcel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

/**
 * @author zhangzijuan
 * @Description:导入车辆的相关价格信息
 * @date 2018-04-03
 */
@Controller
@RequestMapping("/importPrice")
public class ImportCarPriceInfoApi {
    private final RestTemplate restTemplate;
    ImportCarPriceInfoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/importCarPriceInfo",
            produces="application/json; charset=UTF-8")
    @ResponseBody
    @RequestAuth(false)
    public ResultModel importCarPriceInfo(@RequestParam("auctionId") Long auctionId,@RequestParam("file") MultipartFile multipartFile){
        if(auctionId==null || multipartFile==null || multipartFile.isEmpty()){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        String fileName=multipartFile.getOriginalFilename();
        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
            return new ResultModel(false, ResultCode.FILE_FORMAT.value(),ResultCode.FILE_FORMAT.getRemark(),null);
        }
        ResponseEntity<JSONObject> response=null;
        Map<String,Object> map=new HashMap<>();
        try {
            //创建工作簿
            Workbook workbook=ExcelUtil.createWorkbook(multipartFile.getInputStream(),fileName );
            //创建工作表sheet
            Sheet sheet = ExcelUtil.getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            if (sheet==null || sheet.getRow(0)==null){
                return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
            }
            int colNum = sheet.getRow(0).getLastCellNum();//获取Excel列数
            List<CarPriceExcel> carPriceExcels=new ArrayList<>();
                //读取每一行，第一行为标题，从第二行开始
            for(int r=1;r<rowNum;r++){
                    Row row= sheet.getRow(r);
                    if(row!=null){
                        CarPriceExcel priceExcel=new CarPriceExcel();
                        Field[] fields = priceExcel.getClass().getDeclaredFields();
                        //遍历列 从下标第一列开始
                        for (int i = 0; i < colNum; i++) {
                            //装载obj
                            Cell cell = row.getCell(i);
                            if (null == cell) {
                                cell = row.createCell(i);
                            }
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String value = null == cell.getStringCellValue()?"":cell.getStringCellValue();
                            Field field = fields[i];
                            String fieldName = field.getName();
                            String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                            Method setMethod = priceExcel.getClass().getMethod(methodName, new Class[]{String.class});
                            setMethod.invoke(priceExcel, new Object[]{value});
                        }
                        carPriceExcels.add(priceExcel);
                    }
                }
                map.put("carPriceExcels", carPriceExcels);
                map.put("auctionId",auctionId);
                map.put("fileName",multipartFile.getOriginalFilename());
                response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ROOT+"/service/importPrice/importCarPriceInfo"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(map),JSONObject.class);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel(false, ResultCode.BUSS_EXCEPTION.value(),ResultCode.BUSS_EXCEPTION.getRemark(),null);
        }
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    @GetMapping(value = "/exportCarPriceTemplate",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public void exportCarPriceTemplate(HttpServletRequest request,HttpServletResponse rep, @RequestParam("auctionId") Long auctionId){
        String[] headers={"车辆编号","车牌号","所属店铺","起拍价(元)","保留价(元)"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet("sheet");
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = ExcelUtil.getCellStyle(workbook);
        //生成一个字体
        HSSFFont font =ExcelUtil.getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);
        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)300);
        HSSFCell cell = null;

        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("auctionId",auctionId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getAuctionCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);


        if(response.getStatusCode()== HttpStatus.OK){
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONArray("result");
            for (int i=0;i<result.size();i++){
                JSONObject object=result.getJSONObject(i);
                HSSFRow itemRow = sheet.createRow(i+1);
                HSSFCell c0 = itemRow.createCell(0);
                c0.setCellValue(object.getString("auctionCode"));

                HSSFCell c1 = itemRow.createCell(1);
                c1.setCellValue(object.getString("licenseNumber"));

                HSSFCell c2 = itemRow.createCell(2);
                c2.setCellValue(object.getString("storeName"));
            }

        }
        String filename = String.valueOf(ExcelUtil.processFileName(request,"模版")).concat(".xls");
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
