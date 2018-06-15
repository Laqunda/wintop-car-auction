package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarCenterService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆拍卖信息接口类
 */
@RestController
@RequestMapping("/service/carCenter")
public class CarCenterApi {
    private static final Logger logger = LoggerFactory.getLogger(CarCenterApi.class);
    @Autowired
    private ICarCenterService centerService;

    /***
     * 查询中心列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectCenterList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarCenter>> selectCenterList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarCenter>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            if(obj.get("status")!=null){
                map.put("status",obj.getString("status"));
            }
            if(obj.get("centerName")!=null){
                map.put("centerName",obj.getString("centerName"));
            }
            int count = centerService.countByExample(map);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            List<CarCenter> centerList = centerService.selectByExample(map);
            ListEntity<CarCenter> listEntity = new ListEntity<>();
            listEntity.setList(centerList);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询中心失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 保存中心
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveCarCenter",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveCarCenter(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarCenter carCenter = JSONObject.toJavaObject(obj,CarCenter.class);
            if(carCenter.getStatus()==null){
                carCenter.setStatus("1");
            }
            carCenter.setCreateTime(new Date());
            int count = centerService.insert(carCenter);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存中心失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改中心
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateCarCenter",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateCarCenter(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarCenter carCenter = JSONObject.toJavaObject(obj,CarCenter.class);
            if(carCenter.getStatus()==null){
                carCenter.setStatus("1");
            }
            carCenter.setUpdateTime(new Date());
            int count = centerService.updateByPrimaryKeySelective(carCenter);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改中心失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 删除中心
     * @param obj
     * @return
     */
    @RequestMapping(value = "/deleteCarCenter",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteCarCenter(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            int count = centerService.updateCenterDel(obj.getLong("delPerson"),obj.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除中心失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询中心
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectCarCenter",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarCenter> selectCarCenter(@RequestBody JSONObject obj) {
        ServiceResult<CarCenter> result = new ServiceResult<>();
        try {
            CarCenter center = centerService.selectByPrimaryKey(obj.getLong("id"));
            result.setResult(center);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询中心失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
