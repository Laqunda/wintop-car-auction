package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarPhotoTemp;
import com.wintop.ms.carauction.service.ICarDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class_name: ImportCarPhoto
 * package: com.wintop.ms.carauction.controller
 * describe: 上传图片api
 * creat_user: lizhaoyang
 * creat_date-time: 2018/9/8/12:16
 **/
@RestController
@RequestMapping("/service/importCarPhotoApi")
public class ImportCarPhotoApi {
    private static final Logger logger = LoggerFactory.getLogger(ImportCarPhotoApi.class);
    @Resource
    private ICarDataService carDataService;
    @PostMapping(value = "importCarPhoto")
    public ServiceResult<Map<String,Object>> importCarData(@RequestBody Map<String,Object> map){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> resultMap=new HashMap<String,Object>();
        try {
            //解析最上层api传过来的参数，拿到车辆信息对象数组
            List<CarPhotoTemp> carPhotoTemps=new ArrayList<CarPhotoTemp>();
            carPhotoTemps= JSONObject.parseArray(JSONObject.toJSONString(map.get("carPhotoTemps")),CarPhotoTemp.class);
            String auctionId=JSONObject.toJSONString(map.get("auctionId"));
            String timeCheck=JSONObject.toJSONString(map.get("timeCheck"));
            Integer count=0;
            if (carPhotoTemps==null||carPhotoTemps.size()==0){
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }else {
               count=carDataService.insertCarPhoto(carPhotoTemps,Long.parseLong(auctionId),Long.parseLong(timeCheck));
            }
            resultMap.put("count",count);
            if (count>0&&count==carPhotoTemps.size()){
                result.setResult(resultMap);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        } catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value="deleteCarPhoto")
    public ServiceResult<Map<String,Object>> deleteCarData(@RequestBody Map<String,Object> map){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> resultMap=new HashMap<String,Object>();
        try {
            String auctionId=JSONObject.toJSONString(map.get("auctionId"));
            carDataService.deleteCarPhoto(Long.parseLong(auctionId));
        } catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }
}
