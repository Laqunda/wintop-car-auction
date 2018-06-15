package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.CarRegionSetting;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarCenterService;
import com.wintop.ms.carauction.service.ICarRegionSettingService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆拍卖信息接口类
 */
@RestController
@RequestMapping("/service/carRegionSetting")
public class CarRegionSettingApi {
    private static final Logger logger = LoggerFactory.getLogger(CarRegionSettingApi.class);
    @Autowired
    private ICarRegionSettingService regionSettingService;

    private IdWorker idWorker = new IdWorker(10);

    /***
     * 查询地区拍卖设置列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectRegionSettingList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarRegionSetting>> selectRegionSettingList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarRegionSetting>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            if(obj.get("regionId")!=null){
                map.put("regionId",obj.getLong("regionId"));
            }
            int count = regionSettingService.countByExample(map);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            List<CarRegionSetting> regionSettingList = regionSettingService.selectByExample(map);
            ListEntity<CarRegionSetting> listEntity = new ListEntity<>();
            listEntity.setList(regionSettingList);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询地区拍卖设置列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 保存地区拍卖设置
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveRegionSetting",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveRegionSetting(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            obj.put("payBreachTime",sdf.parse(obj.getString("payBreachTime")));
            obj.put("transferBreachTime",sdf.parse(obj.getString("transferBreachTime")));
            CarRegionSetting regionSetting = JSONObject.toJavaObject(obj,CarRegionSetting.class);
            if(regionSettingService.selectByRegionId(regionSetting.getRegionId())!=null){
                result.setError(ResultCode.UNIQUE_REGION_SETTING.strValue(),ResultCode.UNIQUE_REGION_SETTING.getRemark());
                return result;
            };
            regionSetting.setId(idWorker.nextId());
            regionSetting.setCreateTime(new Date());
            regionSetting.setStatus("1");
            int count = regionSettingService.insert(regionSetting);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存地区拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改地区拍卖设置
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateRegionSetting",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateRegionSetting(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            obj.put("payBreachTime",sdf.parse(obj.getString("payBreachTime")));
            obj.put("transferBreachTime",sdf.parse(obj.getString("transferBreachTime")));
            CarRegionSetting regionSetting = JSONObject.toJavaObject(obj,CarRegionSetting.class);
            regionSetting.setUpdateTime(new Date());
            int count = regionSettingService.updateByPrimaryKeySelective(regionSetting);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改地区拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /***
     * 查询地区拍卖设置
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectRegionSetting",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarRegionSetting> selectRegionSetting(@RequestBody JSONObject obj) {
        ServiceResult<CarRegionSetting> result = new ServiceResult<>();
        try {
            CarRegionSetting regionSetting = regionSettingService.selectByPrimaryKey(obj.getLong("id"));
            if(regionSetting==null){
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
                return result;
            }
            result.setResult(regionSetting);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询地区拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
