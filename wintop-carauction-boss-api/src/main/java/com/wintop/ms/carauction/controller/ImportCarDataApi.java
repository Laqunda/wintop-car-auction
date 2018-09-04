package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.entity.CarDataExcel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * class_name: ImportCarDataApi
 * package: com.wintop.ms.carauction.controller
 * describe: 在车辆列表界面进行车辆信息的批量导入的第一步，读取excel表格数据
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/23/15:10
 **/
@Controller
@RequestMapping("/importCarData")
public class ImportCarDataApi {
    private final RestTemplate restTemplate;
    ImportCarDataApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @PostMapping(value = "/importCarDataExcel", produces="application/json; charset=UTF-8")
    @ResponseBody
    @RequestAuth(false)
    public ResultModel importCarPriceInfo(@RequestParam("file") MultipartFile multipartFile,
                                          @RequestParam("auctionId") Long auctionId){
        //1.判断传入参数的非空
        if( multipartFile==null || multipartFile.isEmpty()){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        //2.获取上载文件的文件名
        String fileName=multipartFile.getOriginalFilename();
        //3.如果文件不是xls或者xlsx文件，则返回文件格式不正确提示
        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
            return new ResultModel(false, ResultCode.FILE_FORMAT.value(),ResultCode.FILE_FORMAT.getRemark(),null);
        }
        ResponseEntity<JSONObject> response=null;
        Map<String,Object> map=new HashMap<>();
        //4.处理上载文件
        try {
            //创建工作簿
            Workbook workbook= ExcelUtil.createWorkbook(multipartFile.getInputStream(),fileName );
            //创建工作表sheet,并将上载文件转为对象
            Sheet sheet = ExcelUtil.getSheet(workbook, 0);
            //获取sheet中数据的行数，去掉了空行
            int rowNum = sheet.getPhysicalNumberOfRows();
            if (sheet==null || sheet.getRow(0)==null){
                return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
            }
            //获取Excel列数
            int colNum = sheet.getRow(0).getLastCellNum();
            List<CarDataExcel> carDataExcels=new ArrayList<>();
            //读取每一行，第一行为标题，从第二行开始
            for(int i=1;i<rowNum;i++){
                //获取第一行
                Row row=sheet.getRow(i);
                //如果为空，终止循环，理论上不会为空，因为获取的行数已经把空行去掉了
                if (row==null){
                    break;
                }else {
                    //创建对象，用于存储本行的数据
                    CarDataExcel carDataExcel=new CarDataExcel();
                    //通过反射获取CarDataExcel的所有属性
                    Field[] fields=CarDataExcel.class.getDeclaredFields();
                    //不为空的时候，开始遍历列
                    for (int j=0;j<colNum;j++){
                        Cell cell=row.getCell(j);
                        //得到处理后的cell数据内容
                        String cellValue=checkCellValue(cell);
                        //获取属性名
                        Field field=fields[j];
                        String fieldName=field.getName();
                        //字符串拼接属性的set方法
                        String methodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                        //获取指定方法名，指定参数类型的方法对象
                        Method fieldMethod=CarDataExcel.class.getMethod(methodName,String.class);
                        //调用方法，参数为：方法所在对象，传入参数值
                        fieldMethod.invoke(carDataExcel,cellValue);
                    }
                    carDataExcels.add(carDataExcel);
                }
            }
            //TODO 是否需要根据登录人获取？怎么获取
            Long managerId=2L;
            map.put("carDataExcels", carDataExcels);
            map.put("auctionId",auctionId);
            map.put("managerId",managerId);
            System.out.println("获取的excel数组长度"+carDataExcels.size());
            System.out.println("获取的拍卖场次id"+auctionId);
            System.out.println("获取的登录用户id"+managerId);
            response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT+"/service/importCarData/importCarDataExcel"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
        } catch (Exception e){
            e.printStackTrace();
            return new ResultModel(false, ResultCode.BUSS_EXCEPTION.value(),ResultCode.BUSS_EXCEPTION.getRemark(),null);
        }
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    //用来处理cell中的数据
    public static String checkCellValue(Cell cell){
        String str="";
        if(cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        //如果是date类型则 ，获取该cell的date值
                        str=HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
                    } else { // 纯数字
                        double d = cell.getNumericCellValue();
                        if (d - (int) d < Double.MIN_VALUE) {
                            // 是否为int型
                            str=Integer.toString((int) d);
                        } else {
                            // 是否为double型
                            str=Double.toString(cell.getNumericCellValue());
                        }
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    str=cell.getStringCellValue() + "";
                    if ("无".equals(str.trim())){
                        str="";
                    }
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    str=cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    str=cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    str="";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    str="";
                    break;
                default:
                    str="";
                    break;
            }
        } else {
            str="";
        }
        return str;
    }
}
