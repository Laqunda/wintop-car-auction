package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.ICarAgentCompanyService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 代办公司信息接口类
 */
@RestController
@RequestMapping("/service/agentCompany")
public class CarAgentCompanyApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAgentCompanyApi.class);
    @Autowired
    private ICarAgentCompanyService agentCompanyService;
    @Autowired
    private ICarManagerUserService managerUserService;

    /***
     * 查询所有公司
     * @return
     */
    @RequestMapping(value = "/selectAllAgentCompany",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CommonNameVo>> selectAllAgentCompany() {
        ServiceResult<List<CommonNameVo>> result = new ServiceResult<>();
        try {
            List<CommonNameVo> companyList = agentCompanyService.selectAllAgentCompany();
            result.setResult(companyList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有有效代办公司失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询代办公司列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAgentCompanyList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAgentCompany>> selectAgentCompanyList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAgentCompany>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            if(obj.get("status")!=null){
                map.put("status",obj.getString("status"));
            }
            if(obj.get("companyName")!=null){
                map.put("companyName",obj.getString("companyName"));
            }
            int count = agentCompanyService.countByExample(map);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAgentCompany> companyList = agentCompanyService.selectByExample(map);
            ListEntity<CarAgentCompany> listEntity = new ListEntity<>();
            listEntity.setList(companyList);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询代办公司失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 保存中心
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveAgentCompany",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveAgentCompany(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarAgentCompany company = JSONObject.toJavaObject(obj,CarAgentCompany.class);
            if(company.getStatus()==null){
                company.setStatus("1");
            }
            company.setCreateTime(new Date());
            int count = agentCompanyService.insert(company);
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
     * 修改代办公司
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateAgentCompany",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateAgentCompany(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarAgentCompany company = JSONObject.toJavaObject(obj,CarAgentCompany.class);
            if(company.getStatus()==null){
                company.setStatus("1");
            }
            company.setUpdateTime(new Date());
            int count = agentCompanyService.updateByIdSelective(company);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改代办公司失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 删除代办公司
     * @param obj
     * @return
     */
    @RequestMapping(value = "/deleteAgentCompany",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteAgentCompany(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            int count = agentCompanyService.updateCenterDel(obj.getLong("delPerson"),obj.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除代办公司失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询代办公司
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAgentCompany",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarAgentCompany> selectAgentCompany(@RequestBody JSONObject obj) {
        ServiceResult<CarAgentCompany> result = new ServiceResult<>();
        try {
            CarAgentCompany company = agentCompanyService.selectById(obj.getLong("id"));
            result.setResult(company);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询代办公司失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改代办公司负责人
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateAgentCompanyHandler",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateAgentCompanyHandler(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Long userId = obj.getLong("userId");
            CarManagerUser user = managerUserService.selectByPrimaryKey(userId,false);
            if(user == null){
                result.setSuccess(ResultCode.NO_USER.strValue(),ResultCode.NO_USER.getRemark());
                return result;
            }
            CarAgentCompany company = new CarAgentCompany();
            company.setId(obj.getLong("id"));
            company.setHandlerId(userId);
            company.setHandlerName(user.getUserName());
            company.setHandlerTel(user.getUserPhone());
            int count = agentCompanyService.updateByIdSelective(company);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改代办公司负责人失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 根据条件查询可选的代办公司
     */
    @RequestMapping(value = "/selectCarAgentCompanyList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarAgentCompany>> selectCarAgentCompanyList(@RequestBody JSONObject obj) {
        ServiceResult<List<CarAgentCompany>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            Long userId = obj.getLong("customerId");
            if(userId != null){
                CarManagerUser carManagerUser = managerUserService.selectByPrimaryKey(userId,true);
                paramMap.put("departmentId",carManagerUser.getDepartmentId());
            }
           List<CarAgentCompany> list  = agentCompanyService.selectCompanyByDepId(paramMap);
            result.setResult(list);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询代办公司失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
