package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarManagerRole;
import com.wintop.ms.carauction.entity.CarOrderBargain;
import com.wintop.ms.carauction.service.ICarAutoService;
import com.wintop.ms.carauction.service.ICarManagerRoleService;
import com.wintop.ms.carauction.service.ICarOrderBargainService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *议价审核接口
 */
@RestController
@RequestMapping("/service/bargainingAudit")
public class BargainingAuditApi {
    private static final Logger logger = LoggerFactory.getLogger(BargainingAuditApi.class);
    @Resource
    private ICarOrderBargainService iCarOrderBargainService;
    @Autowired
    private ICarManagerRoleService roleService;
    @Autowired
    private ICarAutoService autoService;
    private IdWorker idWorker = new IdWorker(10);

    /**
     * 议价审核(成功/失败)接口
     */
    @ApiOperation(value = "议价审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "status",value = "议价状态 1成功 2失败",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bargainFee",value = "议价价格",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "原因",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/insertBargainingAuditSure",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> insertBargainingAuditSure(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            Integer count=iCarOrderBargainService.sureBargaining(obj,null);
            if(count == 0 || count == null){
                result.setError("0","议价审核失败");
                map.put("count",0);
                return result;
            }else{
                map.put("count",count);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("议价审核失败",e);
        }
        return result;
    }

    /**
     * 议价处理
     *@Author:zhangzijuan
     *@date 2018/4/17
     *@param:
     */
    @ApiOperation(value = "议价处理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "status",value = "议价状态 1成功 2失败",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bargainFee",value = "议价价格",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "原因",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/sureBargaining",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> sureBargaining(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
        Long userId=obj.getLong("managerId");
        CarAuto auto = autoService.selectByPrimaryKey(obj.getLong("carId")).getResult();
        //**,2只能操作自己的数据
        CarManagerRole managerRole = roleService.selectByUserId(userId);
        if("2".equals(managerRole.getWriteType())){
            if(userId.compareTo(auto.getCreateUser())!=0){
                result.setError(ResultCode.NO_ALLOW_UPDATE.strValue(),ResultCode.NO_ALLOW_UPDATE.getRemark());
                return result;
            }
        }
        Integer i=iCarOrderBargainService.sureBargaining(obj,auto);
        if (i>0){
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }else if (i==-1){
            result.setError(ResultCode.ERROR_PARAM.strValue(),ResultCode.ERROR_PARAM.getRemark());
        }else {
            result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
        }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
            logger.info("议价失败",e);
        }

        return result;
    }
}
