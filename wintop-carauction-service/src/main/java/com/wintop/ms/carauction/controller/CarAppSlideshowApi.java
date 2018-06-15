package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppInfo;
import com.wintop.ms.carauction.entity.CarAppSlideshow;
import com.wintop.ms.carauction.service.ICarAppSlideshowService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by 12991 on 2018/3/6.
 */
@RestController
@RequestMapping("/service/carAppSlideshow")
public class CarAppSlideshowApi {
    @Autowired
    private ICarAppSlideshowService slideshowService;
    private IdWorker idWorker = new IdWorker(10);
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarAppSlideshowApi.class);

    /**
     *获取获取轮播图,现场播报
     */
    @RequestMapping(value = "/selectCarAppSlideshow",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<Map<String,Object>>> selectCarAppSlideshow(@RequestBody JSONObject obj) {
        Logger.info("获取轮播图,现场播报");
        ServiceResult<List<Map<String,Object>>> result =new ServiceResult<List<Map<String,Object>>>();
        Map<String,Object> map = new HashMap<>();
        map.put("type",obj.getString("type"));
        map.put("ifShow","1");
        try {
            List<CarAppSlideshow> list =  slideshowService.selectByExample(map);
            List<Map<String,Object>> list1 = new ArrayList<>();
            for(CarAppSlideshow carAppSlideshow : list){
                Map<String,Object> param = new HashMap<>();
                param.put("type",carAppSlideshow.getType());
                param.put("openType",carAppSlideshow.getOpenType());
                param.put("openObj",carAppSlideshow.getOpenObj());
                param.put("img",carAppSlideshow.getImg());
                param.put("title",carAppSlideshow.getTitle());
                list1.add(param);
            }
            result.setResult(list1);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.strValue());
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("获取轮播图,现场播报失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存APP轮播图或播报
     */
    @RequestMapping(value = "/saveAppSlideshow",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveAppSlideshow(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarAppSlideshow slideshow = JSONObject.toJavaObject(obj,CarAppSlideshow.class);
            slideshow.setId(idWorker.nextId());
            slideshow.setEditor(obj.getLong("editor"));
            slideshow.setEditTime(new Date());
            int count = slideshowService.insert(slideshow);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("保存APP轮播图或播报失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 修改APP轮播图或播报
     */
    @RequestMapping(value = "/updateAppSlideshow",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateAppSlideshow(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarAppSlideshow slideshow = JSONObject.toJavaObject(obj,CarAppSlideshow.class);
            slideshow.setEditor(obj.getLong("editor"));
            slideshow.setEditTime(new Date());
            int count = slideshowService.updateByIdSelective(slideshow);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("修改APP轮播图或播报失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询APP轮播图或播报
     */
    @RequestMapping(value = "/selectAppSlideshow",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarAppSlideshow> selectAppSlideshow(@RequestBody JSONObject obj) {
        ServiceResult<CarAppSlideshow> result = new ServiceResult<>();
        try {
            CarAppSlideshow slideshow = slideshowService.selectById(obj.getLong("id"));
            result.setResult(slideshow);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("查询APP轮播图或播报失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询APP轮播图或播报列表
     */
    @RequestMapping(value = "/selectAppSlideshowList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarAppSlideshow>> selectAppSlideshowList(@RequestBody JSONObject obj) {
        ServiceResult<List<CarAppSlideshow>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("type",obj.getString("type"));
            List<CarAppSlideshow> slideshow = slideshowService.selectByExample(map);
            result.setResult(slideshow);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            Logger.info("查询APP轮播图或播报列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
