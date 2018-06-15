package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarManagerRole;
import com.wintop.ms.carauction.entity.CarManagerRoleType;
import com.wintop.ms.carauction.service.ICarManagerRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色信息接口类
 */
@RestController
@RequestMapping("/service/managerRole")
public class CarManagerRoleApi {
    private static final Logger logger = LoggerFactory.getLogger(CarManagerRoleApi.class);
    @Autowired
    private ICarManagerRoleService roleService;

    /***
     * 查询所有角色
     * @return
     */
    @RequestMapping(value = "/selectAllRole",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarManagerRoleType>> selectAllRole() {
        ServiceResult<List<CarManagerRoleType>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            List<CarManagerRoleType> roleTypeList = roleService.selectByExample(map);
            result.setResult(roleTypeList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有角色失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询单个角色信息
     * @return
     */
    @RequestMapping(value = "/selectRoleById",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarManagerRole> selectRoleById(@RequestBody JSONObject obj) {
        ServiceResult<CarManagerRole> result = new ServiceResult<>();
        try {
            CarManagerRole managerRole = roleService.selectById(obj.getLong("roleId"));
            result.setResult(managerRole);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询单个角色信息失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
