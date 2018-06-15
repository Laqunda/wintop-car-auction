package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.TreeEntity;
import com.wintop.ms.carauction.service.ICarManagerRolePageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-04-11
 */
@RestController
@RequestMapping("/service/rolePage")
public class CarManagerRolePageApi {
    private static final Logger logger = LoggerFactory.getLogger(CarManagerPageApi.class);
    @Autowired
    private ICarManagerRolePageService rolePageService;

    /**
     * 修改角色权限
     *@Author:zhangzijuan
     *@date 2018/4/11
     *@param:
     */
    @ApiOperation(value = "修改角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "pageIds",value = "页面id 多个用逗号拼接",required = true,dataType = "long")
    })
    @PostMapping(value = "/editRolePage",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> editRolePage(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            Integer i= rolePageService.editRolePage(obj);
            if (i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else if (i==-1){
                result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
            } else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改角色权限",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询角色所拥有的权限
     * @param obj
     * @return
     */
    @PostMapping(value = "/selectAllPages",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<String>> selectAllPages(@RequestBody JSONObject obj) {
        ServiceResult<List<String>> result = new ServiceResult<>();
        try {
            List<String> urls= rolePageService.selectAllPages(obj.getLong("roleId"));
            result.setResult(urls);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询角色所拥有的权限",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

}
