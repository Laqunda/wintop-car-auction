package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 店铺信息接口类
 */
@RestController
@RequestMapping("/service/carStore")
public class CarStoreApi {
    private static final Logger logger = LoggerFactory.getLogger(CarStoreApi.class);
    // 中心
    private static final long CENTER = 2L;
    // 代办公司
    private static final long AGENCY = 3L;
    @Autowired
    private ICarAgentCompanyService agentCompanyService;
    @Autowired
    private ICarStoreService storeService;
    @Autowired
    private ICarCenterStoreService carCenterStoreService;
    @Autowired
    private ICarStoreService carStoreService;
    @Autowired
    private ICarManagerUserService carManagerUserService;

    private IdWorker idWorker = new IdWorker(10);

    /***
     * 查询所有店铺
     * @return
     */
    @RequestMapping(value = "/selectAllStore",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CommonNameVo>> selectAllStore(@RequestBody JSONObject obj) {
        ServiceResult<List<CommonNameVo>> result = new ServiceResult<>();
        try {
            List<CommonNameVo> storeList;
            if (obj.getLong("userId")!=null) {
                storeList = storeService.selectAllStore(obj.getLong("userId"));
            }else {
                storeList = storeService.selectAllStore();
            }
            result.setResult(storeList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有有效店铺失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询店铺列表
     * @return
     */
    @RequestMapping(value = "/selectStoreList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarStore>> selectStoreList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarStore>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            if(obj.get("storeName")!=null){
                map.put("storeName",obj.getString("storeName"));
            }
            if (obj.getString("managerId") != null){
                CarManagerUser user = carManagerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
                if (Objects.equals(CENTER,user.getRoleTypeId() )) {
                    List<CommonNameVo> centerStoreList = carCenterStoreService.selectAllStore(user.getDepartmentId());
                    if (CollectionUtils.isNotEmpty(centerStoreList)) {
                        List<Long> storeList = centerStoreList.stream().map(centerStore -> centerStore.getId()).collect(Collectors.toList());
                        map.put("storeList", storeList);
                    }
                } else if (Objects.equals(AGENCY,user.getRoleTypeId())){
                    List<Long> storeList = carManagerUserService.queryStoreScope(obj.getLong("managerId"));
                    map.put("storeList", storeList);
                }
            }
            int count = storeService.countByExample(map);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            List<CarStore> storeList = storeService.selectByExample(map);
            ListEntity<CarStore> listEntity = new ListEntity<>(storeList,count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询店铺列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 保存店铺
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveCarStore",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveCarStore(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarStore carStore = JSONObject.toJavaObject(obj,CarStore.class);
            carStore.setId(idWorker.nextId());
            if(carStore.getStatus()==null){
                carStore.setStatus("1");
            }
            carStore.setCreateTime(new Date());
            int count = storeService.insert(carStore);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存店铺失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改店铺
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateCarStore",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateCarStore(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarStore carStore = JSONObject.toJavaObject(obj,CarStore.class);
            if(carStore.getStatus()==null){
                carStore.setStatus("1");
            }
            carStore.setUpdateTime(new Date());
            int count = storeService.updateByPrimaryKey(carStore);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改店铺失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 删除店铺
     * @param obj
     * @return
     */
    @RequestMapping(value = "/deleteCarStore",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteCarStore(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            int count = storeService.updateStoreDel(obj.getLong("delPerson"),obj.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除店铺失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询店铺
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectCarStore",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarStore> selectCarStore(@RequestBody JSONObject obj) {
        ServiceResult<CarStore> result = new ServiceResult<>();
        try {
            CarStore carStore = storeService.selectByPrimaryKey(obj.getLong("id"));
            result.setResult(carStore);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询店铺失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
