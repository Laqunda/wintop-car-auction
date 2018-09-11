package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.entity.CarDataExcel;
import com.wintop.ms.carauction.entity.CarPhotoTemp;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import jdk.nashorn.internal.ir.TernaryNode;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
import java.io.File;
import java.io.IOException;
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
    public static final Long IMG_MAX_SIZE=100L;
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
            if (sheet==null || sheet.getRow(0)==null){
                return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
            }
            //获取sheet中数据的行数，去掉了空行
            int rowNum = sheet.getPhysicalNumberOfRows();
            System.out.println("总行数："+rowNum);
            if (rowNum>1000){
                return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"数据量过大，请联系开发人员导入！",null);
            }
            //获取Excel列数
            int colNum = sheet.getRow(0).getLastCellNum();
            System.out.println("总列数："+colNum);
            if (colNum!=22){
                return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"请您使用提供的excel模板进行导入！",null);
            }
            List<CarDataExcel> carDataExcels=new ArrayList<>();
            //用来存放首列id判断是否重复
            Set<Double> set=new HashSet<Double>();
            //读取每一行，第一行为标题，从第二行开始
            for(int i=1;i<rowNum;i++){
                //获取第一行
                Row row=sheet.getRow(i);
                if(row.getCell(0).getCellType()!=HSSFCell.CELL_TYPE_NUMERIC){
                    return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"表格首列必须为数字编号！请检查您的表格第"+(i+1)+"行",null);
                }else if(row.getCell(0).getNumericCellValue()<1||row.getCell(0).getNumericCellValue()>999){
                    return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"首列编号需要在1~999之间！请检查您的表格第"+(i+1)+"行",null);
                }
                set.add(row.getCell(0).getNumericCellValue());
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
            if (set.size()<rowNum-1){
                return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"首列编号不可重复！请检查您的首列编号！",null);
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
//用来生成数据填写模板
    @GetMapping(value = "/exportCarDataModelExcel",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public void exportCarPriceTemplate(HttpServletRequest request, HttpServletResponse response){
        String[] headerName={"牌号(序列号)","经销店","车牌号码","车架号码","品牌型号","出厂时间","登记日期","年检日期"
                ,"保险截止日期","强险截止日期","排量","颜色","表里显程(万公里)","是否需要代办过户/转籍",
                "使用性质","行驶证","登记证","过户变更次数","购置证","违章情况","备注(过户后手续留存特殊需要、本市或外迁、是否指定过户场地等）\n" +
                "该车辆如有重大车损、事故及故障、影响过户的所有因素、是否为转籍车辆必须注明)","备注一"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet("车辆信息批量导入模板");
        //设置表格默认列宽20个字节
        sheet.setDefaultColumnWidth(20);
        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)300);
        HSSFCell cell = null;
        for (int i = 0; i < headerName.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerName[i]);
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request,"车辆信息批量导入模板")).concat(".xls");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        try {
            ServletOutputStream ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @PostMapping(value = "/importCarPhoto", produces="application/json; charset=UTF-8")
    @ResponseBody
    @RequestAuth(false)
    public ResultModel importCarPhoto(@RequestParam(value = "file", required = false)List<MultipartFile> multipartFiles,
                                          @RequestParam("auctionId") Long auctionId,@RequestParam("timeCheck") Long timeCheck){
        //1.判断传入参数的非空
        if( multipartFiles==null || multipartFiles.size()==0){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        if(multipartFiles.size()>999){
            return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"上传图片数量过多！",null);
        }
        String fileName=null;
        Map<String,Object> map=new HashMap<>();
        HttpPost httpPost=new HttpPost("http://test.yuntongauto.com/file/uploadImageForQuality");
        httpPost.setHeader("appId","1234567_boss");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        ResponseEntity<JSONObject> responseEntity=null;
        Integer count =1;
        List<CarPhotoTemp> list=new ArrayList<CarPhotoTemp>();
        //用来存放图片序号id判断是否重复
        Set<Integer> set=new HashSet<Integer>();
        try {
            for (MultipartFile multipartFile:multipartFiles){
                //2.获取上载文件的文件名
                fileName=multipartFile.getOriginalFilename();
                //3.如果文件不是jpg或者png文件，则返回文件格式不正确提示
                if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")){
                    return new ResultModel(false, ResultCode.FILE_FORMAT.value(),ResultCode.FILE_FORMAT.getRemark(),null);
                }
                Integer id=0;
                if(fileName.endsWith(".jpg")){
                    id=Integer.valueOf(fileName.replace(".jpg",""));
                }else if (fileName.endsWith(".png")){
                    id=Integer.valueOf(fileName.replace(".png",""));
                }
                if(id<1||id>999){
                    return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"图片名称序号超出限定！",null);
                }
                //判断图片名称序号是否重复
                set.add(id);
                if(set.size()<count){
                    return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"图片名称序号不能重复！",null);
                }
                count++;
                //4.将提交过来的图片保存为服务器临时文件
                File tempFile = File.createTempFile(UUID.randomUUID().toString(),fileName.substring(fileName.lastIndexOf(".")));
                //5.multipartFile转化为File
                multipartFile.transferTo(tempFile);
                MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
                multipartEntityBuilder.addBinaryBody("file",tempFile);
                Long imgSize=tempFile.length()/1024;
                //6.判断图片大小
                if(imgSize>IMG_MAX_SIZE){
                    return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"图片"+fileName+"大小超限",null);
                }
                HttpEntity httpEntity = multipartEntityBuilder.build();
                httpPost.setEntity(httpEntity);
                response = httpclient.execute(httpPost);
                HttpEntity entity2 = response.getEntity();
                String result = EntityUtils.toString(entity2,"UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(result);
                CarPhotoTemp carPhotoTemp=new CarPhotoTemp();
                carPhotoTemp.setId(id);
                carPhotoTemp.setMainPhoto(jsonObject.getString("result"));
                list.add(carPhotoTemp);
            }
            map.put("auctionId",auctionId);
            map.put("carPhotoTemps",list);
            map.put("timeCheck",timeCheck);
            responseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT+"/service/importCarPhotoApi/importCarPhoto"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return new ResultModel(false, ResultCode.REQUEST_DISABLED.value(),"请确定上传图片文件名为数字！确认无误联系开发人员",null);
        } catch (Exception e){
            e.printStackTrace();
            return new ResultModel(false, ResultCode.BUSS_EXCEPTION.value(),ResultCode.BUSS_EXCEPTION.getRemark(),null);
        }
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
    }

    @PostMapping(value = "/deleteCarPhoto", produces="application/json; charset=UTF-8")
    @ResponseBody
    @RequestAuth(false)
    public ResultModel deleteCarPhoto(@RequestParam("auctionId") Long auctionId){
        //1.判断传入参数的非空
        if( auctionId==null || auctionId==0){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> responseEntity=null;
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("auctionId",auctionId);
            responseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT+"/service/importCarPhotoApi/deleteCarPhoto"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel(false, ResultCode.BUSS_EXCEPTION.value(),ResultCode.BUSS_EXCEPTION.getRemark(),null);
        }
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
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
