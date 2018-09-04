package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarDataExcel;
import com.wintop.ms.carauction.service.ICarDataService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * class_name: ImportCarDataApi
 * package: com.wintop.ms.carauction.controller
 * describe: 在车辆列表界面进行车辆信息的批量导入的第二步，得到处理后的excel数据对象数组，导入数据库
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/24/11:15
 **/
@RestController
@RequestMapping("/service/importCarData")
public class ImportCarDataApi {
    private static final Logger logger = LoggerFactory.getLogger(ImportCarDataApi.class);
    @Resource
    private ICarDataService carDataService;
    @PostMapping(value = "importCarDataExcel")
    public ServiceResult<Map<String,Object>> importCarData(@RequestBody Map<String,Object> map){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        //用来存放数据异常提醒语句
        String exceptionContent="";
        //用来拼接异常语句
        String concatContent="";
        Boolean exceptionFlag=true;
        try {
            //解析最上层api传过来的参数，拿到车辆信息对象数组
            List<CarDataExcel> carDataExcels=new ArrayList<CarDataExcel>();
            carDataExcels= JSONObject.parseArray(JSONObject.toJSONString(map.get("carDataExcels")),CarDataExcel.class);
            String managerId=JSONObject.toJSONString(map.get("managerId"));
            String auctionId=JSONObject.toJSONString(map.get("auctionId"));
            if (carDataExcels==null||carDataExcels.size()==0){
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }else {
                CarDataExcel carDataExcel=null;
                for (int i=0;i<carDataExcels.size();i++){
                    try {
                        carDataExcel=carDataExcels.get(i);
                        carDataExcel.setStoreName(transformData(carDataExcel.getStoreName(),"storeName"));
                        carDataExcel.setTransferNumber(transformData(carDataExcel.getTransferNumber(),"transferNumber"));
                        carDataExcel.setUnIllegal(transformData(carDataExcel.getUnIllegal(),"unIllegal"));
                        carDataExcel.setUseNatureCn(transformData(carDataExcel.getUseNatureCn(),"useNatureCn"));
                        carDataExcel.setIfAgent(transformData(carDataExcel.getIfAgent(),"ifAgent"));
                        carDataExcel.setPurchaseTax(transformData(carDataExcel.getPurchaseTax(),"purchaseTax"));
                        carDataExcel.setDrivingLicense(transformData(carDataExcel.getDrivingLicense(),"drivingLicense"));
                        carDataExcel.setRegistrationCertificate(transformData(carDataExcel.getRegistrationCertificate(),"registrationCertificate"));
                        concatContent="第"+(i+2)+"行的出厂时间数据格式存在问题";
                        carDataExcel.setManufactureDate(transformDate(carDataExcel.getManufactureDate()));
                        concatContent="第"+(i+2)+"行的登记时间数据格式存在问题";
                        carDataExcel.setBeginRegisterDate(transformDate(carDataExcel.getBeginRegisterDate()));
                        concatContent="第"+(i+2)+"行的年检时间数据格式存在问题";
                        carDataExcel.setYearInsurance(transformDate(carDataExcel.getYearInsurance()));
                        concatContent="第"+(i+2)+"行的保险截止时间数据格式存在问题";
                        carDataExcel.setBusinessInsurance(transformDate(carDataExcel.getBusinessInsurance()));
                        concatContent="第"+(i+2)+"行的强险截止时间时间数据格式存在问题";
                        carDataExcel.setCompulsoryInsurance(transformDate(carDataExcel.getCompulsoryInsurance()));
                        System.out.println(carDataExcel);
                    }catch (ParseException e){
                        e.printStackTrace();
                        exceptionFlag=false;
                        exceptionContent+=concatContent+"    ";
                    }
                }
            }
            Integer count=carDataService.insertCarDataList(carDataExcels,Long.parseLong(managerId),Long.parseLong(auctionId));
            if (count>0&&count==carDataExcels.size()){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }finally{
            if (!exceptionFlag){
                System.out.println(exceptionContent);
                result.setError(ResultCode.ERROR_PARAM.strValue(),exceptionContent);
            }
        }
        return result;
    }
    //用来将String类型的字符串是否能转换为时间格式
    public static String transformDate(String cellValue) throws ParseException {
        if (cellValue==null||("无").equals(cellValue.trim())||StringUtils.isBlank(cellValue.trim())){
            cellValue=null;
        }
        String returnValue=null;
        if (cellValue!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(cellValue);
            returnValue = sdf.format(date);
        }
        return returnValue;
    }
    //用来处理carDataExcel对象里存储的数据
    public static String transformData(String cellValue,String name){
        if("storeName".equals(name)){
            if(cellValue!=null){
                cellValue=cellValue.trim();
            }
        }else if("transferNumber".equals(name)){
            if(cellValue==null||StringUtils.isBlank(cellValue.trim())||("无").equals(cellValue.trim())){
                cellValue="0";
            }else if(("有").equals(cellValue.trim())){
                cellValue="1";
            }
        }else if("unIllegal".equals(name)){
            if(cellValue==null||StringUtils.isBlank(cellValue.trim())||("无").equals(cellValue.trim())){
                cellValue="2";
            }else if(cellValue.indexOf("无")==-1){
                cellValue="1";
            }
        }else if("useNatureCn".equals(name)){
           if (cellValue==null||cellValue.indexOf("非")!=-1){
               cellValue="非营运";
           }
        }else if("ifAgent".equals(name)){
            if (cellValue==null||cellValue.indexOf("不")!=-1){
                cellValue="2";
            }else {
                cellValue="1";
            }
        }else if ("purchaseTax".equals(name)){
            if (cellValue==null||cellValue.indexOf("无")!=-1){
                cellValue="2";
            }else {
                cellValue="1";
            }
        }else if ("drivingLicense".equals(name)){
            if (cellValue==null||cellValue.indexOf("无")!=-1){
                cellValue="2";
            }else {
                cellValue="1";
            }
        }else if ("registrationCertificate".equals(name)){
            if (cellValue==null||cellValue.indexOf("无")!=-1){
                cellValue="2";
            }else {
                cellValue="1";
            }
        }
        return cellValue;
    }
}
