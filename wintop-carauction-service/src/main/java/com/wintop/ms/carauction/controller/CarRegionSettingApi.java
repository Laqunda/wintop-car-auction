package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ManagerRole;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.mapper.read.ICarRegionServerfeeSettingReadDao;
import com.wintop.ms.carauction.service.ICarCenterService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.ICarRegionSettingService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 车辆拍卖信息接口类
 */
@RestController
@RequestMapping("/service/carRegionSetting")
public class CarRegionSettingApi {
    private static final Logger logger = LoggerFactory.getLogger(CarRegionSettingApi.class);
    private static final long CENTER = 2L;
    @Autowired
    private ICarRegionSettingService regionSettingService;
    @Autowired
    private ICarManagerUserService managerUserService;
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

    /***
     * 查询地区拍卖设置
     * @param obj
     * @return
     */
    @RequestMapping(value = "/getBossRegionSetting",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarRegionSetting>> getBossRegionSetting(@RequestBody JSONObject obj) {
        ServiceResult<List<CarRegionSetting>> result = new ServiceResult<>();
        try {
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));

            CarManagerUser user = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
            if (isNotEmpty(user)) {
                if (new Long(CENTER).equals(user.getRoleTypeId())
                        && ManagerRole.ZX_ESCFZR.value() == user.getRoleId().intValue()){
                    param.put("centerId",user.getDepartmentId());
                }
            }
            List<CarRegionSetting> settingList  = regionSettingService.selectByExample(param);
            if(CollectionUtils.isEmpty(settingList)){
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
                return result;
            }
            result.setResult(settingList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询地区拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 保存及修改数据
     * @param
     * @return
     */
    @RequestMapping(value = "/getBossSaveOrUpdate",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult getBossSaveOrUpdate(@RequestBody JSONObject obj) {
        ServiceResult result = new ServiceResult();
        try {
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            CarManagerUser user = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
            if (isNotEmpty(user)) {
                if (new Long(CENTER).equals(user.getRoleTypeId())
                        && ManagerRole.ZX_ESCFZR.value() == user.getRoleId().intValue()){
                    param.put("centerId",user.getDepartmentId());
                }
            }
            List<CarRegionSetting> settingList = regionSettingService.selectByExample(param);
            saveOrUpdateSetting(obj, param, settingList, user);

            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询地区拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    private void saveOrUpdateSetting(@RequestBody JSONObject obj, Map param, List<CarRegionSetting> settingList,CarManagerUser user) {
        if (CollectionUtils.isNotEmpty(settingList)) {
            CarRegionSetting setting = settingList.stream().findFirst().get();
            if (isNotEmpty(setting.getId())) {
                if (isNotEmpty(obj.getString("regionId"))) {
                    setting.setRegionId(obj.getLong("regionId"));
                }
                if (isNotEmpty(param.get("centerId"))) {
                    setting.setCenterId(Long.valueOf(param.get("centerId") + ""));
                }
                if (isNotEmpty(obj.getString("agentFee"))) {
                    setting.setAgentFee(BigDecimal.valueOf(obj.getLong("agentFee")));
                }
                if (isNotEmpty(user)) {
                    setting.setUpdateTime(new Date());
                    setting.setUpdatePerson(user.getId());
                }
                regionSettingService.updateByPrimaryKeySelective(setting);
            }
            List<CarRegionServerfeeSetting> serverfeeSettingList = setting.getServerfeeSettingList();
            if (CollectionUtils.isNotEmpty(serverfeeSettingList)) {
                for (int i = 0; i < serverfeeSettingList.size(); i++) {
                    CarRegionServerfeeSetting serverfeeSetting = serverfeeSettingList.get(i);
                    if (i == 0) {
                        serverfeeSetting.setStartClosingPrice(BigDecimal.ZERO);
                        if (isNotEmpty(obj.getString("closingPrice"))) {
                            serverfeeSetting.setEndClosingPrice(BigDecimal.valueOf(obj.getLong("closingPrice")));
                        }
                        if (isNotEmpty(obj.getString("serviceFeeBefore"))) {
                            serverfeeSetting.setServiceFee(BigDecimal.valueOf(obj.getLong("serviceFeeBefore")));
                        }
                        regionSettingService.updateServerfeeByPrimaryKeySelective(serverfeeSetting);
                    }
                    if (i == 1){
                        serverfeeSetting.setEndClosingPrice(BigDecimal.valueOf(Long.parseLong("5000000000")));
                        if (isNotEmpty(obj.getString("closingPrice"))) {
                            serverfeeSetting.setStartClosingPrice(BigDecimal.valueOf(obj.getLong("closingPrice")));
                        }
                        if (isNotEmpty(obj.getString("serviceFeeAfter"))) {
                            serverfeeSetting.setServiceFee(BigDecimal.valueOf(obj.getLong("serviceFeeAfter")));
                        }
                        regionSettingService.updateServerfeeByPrimaryKeySelective(serverfeeSetting);
                    }
                }
            }
        } else{
            CarRegionSetting setting = new CarRegionSetting();
            Long regionSettingId  = idWorker.nextId();
            setting.setId(regionSettingId);
            if (isNotEmpty(obj.getString("regionId"))) {
                setting.setRegionId(obj.getLong("regionId"));
            }
            if (isNotEmpty(param.get("centerId"))) {
                setting.setCenterId(Long.valueOf(param.get("centerId") + ""));
            }
            if (isNotEmpty(obj.getString("agentFee"))) {
                setting.setAgentFee(BigDecimal.valueOf(obj.getLong("agentFee")));
            }
            if (isNotEmpty(user)) {
                setting.setCreateTime(new Date());
                setting.setCreatePerson(user.getId());
            }
            regionSettingService.insert(setting);

            CarRegionServerfeeSetting serverfeeSetting = new CarRegionServerfeeSetting();
            serverfeeSetting.setId(idWorker.nextId());
            serverfeeSetting.setRegionSettingId(regionSettingId);

            serverfeeSetting.setStartClosingPrice(BigDecimal.ZERO);
            if (isNotEmpty(obj.getString("closingPrice"))) {
                serverfeeSetting.setEndClosingPrice(BigDecimal.valueOf(obj.getLong("closingPrice")));
            }
            if (isNotEmpty(obj.getString("serviceFeeBefore"))) {
                serverfeeSetting.setServiceFee(BigDecimal.valueOf(obj.getLong("serviceFeeBefore")));
            }
            if (isNotEmpty(param.get("centerId"))) {
                serverfeeSetting.setCenterId(Long.valueOf(param.get("centerId") + ""));
            }
            regionSettingService.insertServerfeeSelective(serverfeeSetting);

            serverfeeSetting.setId(idWorker.nextId());
            serverfeeSetting.setRegionSettingId(regionSettingId);
            serverfeeSetting.setEndClosingPrice(BigDecimal.valueOf(Long.parseLong("5000000000")));
            if (isNotEmpty(obj.getString("closingPrice"))) {
                serverfeeSetting.setStartClosingPrice(BigDecimal.valueOf(obj.getLong("closingPrice")));
            }
            if (isNotEmpty(obj.getString("serviceFeeAfter"))) {
                serverfeeSetting.setServiceFee(BigDecimal.valueOf(obj.getLong("serviceFeeAfter")));
            }
            if (isNotEmpty(param.get("centerId"))) {
                serverfeeSetting.setCenterId(Long.valueOf(param.get("centerId") + ""));
            }
            regionSettingService.insertServerfeeSelective(serverfeeSetting);
        }
    }

    private static <T> boolean isNotEmpty(T t) {
        return Optional.ofNullable(t).isPresent();
    }
}
