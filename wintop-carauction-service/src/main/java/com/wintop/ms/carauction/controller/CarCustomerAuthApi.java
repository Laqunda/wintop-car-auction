package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerAuth;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarCustomerAuthService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:用户认证信息
 * @date 2018-03-05
 */
@RestController
@RequestMapping(value = "/service/customerAuth")
public class CarCustomerAuthApi {
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerAuthApi.class);
    @Resource
    private ICarCustomerAuthService customerAuthService;
    /**
     * 根据用户id查询用户认证信息
     * @return
     */
    @PostMapping(value = "getAuthInfoByUserId",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarCustomerAuth> getAuthInfoByUserId(@RequestBody Long userId){
        logger.info("查询客户认证信息");
        return customerAuthService.getAuthInfoByUserId(userId);
    }

    /**
     * 提交认证接口
     *@Author:zhangzijuan
     */
    @PostMapping(value = "saveAuthInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveAuthInfo(@RequestBody JSONObject object){
        logger.info("提交客户认证信息");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=customerAuthService.saveAuthInfo(object);
            if (i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 获取用户的认证信息列表
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    @ApiOperation(value = "获取用户的认证信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName",value = "用户名或电话",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "/selectUserAuthList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarCustomerAuth>> selectUserAuthList(@RequestBody Map<String,Object> paramMap) {
        ServiceResult<ListEntity<CarCustomerAuth>> result = new ServiceResult<>();
        try {
            Integer page=(Integer) paramMap.get("page");
            Integer pageSize=(Integer) paramMap.get("limit");
            paramMap.put("startRowNum",(page-1)*pageSize);
            paramMap.put("endRowNum",pageSize);
            paramMap.put("authStatus","1");
            int count = customerAuthService.selectUserAuthCount(paramMap);
            List<CarCustomerAuth> customerAuths = customerAuthService.selectUserAuthList(paramMap);
            ListEntity<CarCustomerAuth> listEntity = new ListEntity<>();
            listEntity.setList(customerAuths);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取认证信息列表异常");
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 根据id查询用户认证信息
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    @ApiOperation(value = "根据id查询用户认证信息")
    @ApiImplicitParam(name = "authId",value = "认证信息id",required = true,paramType = "query",dataType = "long")
    @PostMapping(value = "selectByPrimaryKey",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarCustomerAuth> selectByPrimaryKey(@RequestBody Long authId){
        logger.info("根据id查询用户认证信息");
        return customerAuthService.selectByPrimaryKey(authId);
    }

    /**
     * 认证信息审核
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */

    @ApiOperation(value = "认证信息审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户信息Id，多个用逗号拼接",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "authStatus",value = "2审核通过，-1审核不通过",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "authMsg",value = "审批留言",required = true,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "approveUserAuth",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> approveUserAuth(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        logger.info("认证信息审核");
        try {
            Integer i=customerAuthService.approveUserAuth(obj);
            if (i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

}
