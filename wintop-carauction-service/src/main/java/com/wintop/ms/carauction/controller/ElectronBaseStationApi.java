package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.entity.TblBaseStation;
import com.wintop.ms.carauction.service.TblBaseStationService;
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

@RestController
@RequestMapping("/service/electronBaseStation")
public class ElectronBaseStationApi {
    @Autowired
    private TblBaseStationService tblBaseStationService;

    private static final Logger logger = LoggerFactory.getLogger(ElectronBaseStationApi.class);

    /***
     * 查询所有基站
     * @return
     */
    @RequestMapping(value = "/selectBaseStationList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<TblBaseStation>> selectBaseStationList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<TblBaseStation>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<TblBaseStation> baseList = tblBaseStationService.selectByExample(paramMap);
            int count = tblBaseStationService.countByExample(paramMap);
            ListEntity<TblBaseStation> listEntity = new ListEntity<>();
            listEntity.setList(baseList);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            logger.info("查询所有基站接口失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存基站
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveBaseStation",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblBaseStation> saveBaseStation(@RequestBody JSONObject obj) {
        ServiceResult<TblBaseStation> result = new ServiceResult<>();
        try {
            TblBaseStation baseStation = JSONObject.toJavaObject(obj,TblBaseStation.class);
            TblBaseStation baseStation1 = tblBaseStationService.selectByRealId(baseStation.getStationRealId());
            if(baseStation1!=null){
                result.setResult(baseStation);
                result.setError(ResultCode.DUPLICATE_ADD.strValue(),ResultCode.DUPLICATE_ADD.getRemark());
                return result;
            }else{
                tblBaseStationService.insert(baseStation);
                result.setResult(baseStation);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                return result;
            }
        }catch (Exception e){
            logger.info("保存基站失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /**
     * 更新基站
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateBaseStation",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblBaseStation> updateBaseStation(@RequestBody JSONObject obj) {
        ServiceResult<TblBaseStation> result = new ServiceResult<>();
        try {
            TblBaseStation baseStation = JSONObject.toJavaObject(obj,TblBaseStation.class);
            //更新操作
            TblBaseStation baseStation1 = tblBaseStationService.selectByRealId(baseStation.getStationRealId());
            if(baseStation1!=null && baseStation.getId().compareTo(baseStation1.getId())!=0){
                result.setResult(baseStation);
                result.setError(ResultCode.DUPLICATE_ADD.strValue(),ResultCode.DUPLICATE_ADD.getRemark());
                return result;
            }else{
                tblBaseStationService.updateByPrimaryKeySelective(baseStation);
                result.setResult(baseStation);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                return result;
            }
        }catch (Exception e){
            logger.info("更新基站失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /**
     * 删除基站
     * @param obj
     * @return
     */
    @RequestMapping(value = "/deleteBaseStation",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblBaseStation> deleteBaseStation(@RequestBody JSONObject obj) {
        ServiceResult<TblBaseStation> result = new ServiceResult<>();
        try {
            TblBaseStation baseStation = JSONObject.toJavaObject(obj,TblBaseStation.class);
            //删除操作
            tblBaseStationService.updateDeleteFlag(baseStation);
            result.setResult(baseStation);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            return result;
        }catch (Exception e){
            logger.info("删除基站失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

}
