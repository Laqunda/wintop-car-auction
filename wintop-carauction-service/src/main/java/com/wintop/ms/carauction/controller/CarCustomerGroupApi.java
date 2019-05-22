package com.wintop.ms.carauction.controller;

import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerGroup;
import com.wintop.ms.carauction.entity.CarCustomerLevel;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.service.ICarCustomerGroupService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:会员分组
 * @date 2018-03-14
 */
@RestController
@RequestMapping(value = "/service/group")
public class CarCustomerGroupApi {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarCustomerGroupApi.class);

    private static final Long ADMINISTRATOR = 1L;
    @Autowired
    private ICarCustomerGroupService customerGroupService;
    @Autowired
    private ICarManagerUserService managerUserService;
    /**
     * 查询用户分组及对应的用户数量
     * @Author:zhangzijuan
     * @param map
     * @return
     */
    @ApiOperation(value = "根据获取用户分组及对应的用户数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName",value = "姓名、手机号模糊搜索",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "status",value = "会员状态",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "storeId",value = "店铺ID",required = false,paramType = "query",dataType = "long"),
    })
    @PostMapping(value = "selectGroupAndNum", produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarCustomerGroup>> selectGroupAndNum(@RequestBody Map<String,Object> map){
        return customerGroupService.selectGroupAndNum(map);
    }

    /***
     * 获取用户角色
     * @param object
     * @return
     */
    @RequestMapping(value = "/selectUserRole",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectUserRole(@RequestBody JSONObject object) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarManagerUser mangerUser = managerUserService.selectByPrimaryKey(Longs.tryParse(object.get("managerId").toString()), false);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            if (ADMINISTRATOR.equals(mangerUser.getRoleId())) {
                result.setResult(Collections.singletonMap("flag", true));
            } else {
                result.setResult(Collections.singletonMap("flag", false));
            }
        } catch (Exception e) {
            Logger.info("获取用户角色失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 新建会分组
     * @Author:zhangzijuan
     * @param object
     * @return
     */
    @ApiOperation(value = "新建会员分组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName",value = "客户分组名称",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "会员状态",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "saveGroup", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveGroup(@RequestBody JSONObject object){
        CarCustomerGroup customerGroup=(CarCustomerGroup) JSONObject.toBean(object, CarCustomerGroup.class);
        return customerGroupService.saveGroup(customerGroup);
    }

    /**
     * 查询所有可以选择的用户分组
     * @Author:zhangzijuan
     * @return
     */
    @ApiOperation(value = "查询所有可以选择的用户分组")
    @PostMapping(value = "selectGroupForSelect", produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarCustomerGroup>> selectGroupForSelect(){
        Map<String,Object> map=new HashMap<>();
        return customerGroupService.selectGroupForSelect(map);
    }

    /**
     * 删除会员分组
     * @Author:zhangzijuan
     * @return
     */
    @ApiOperation(value = "删除会员分组")
    @ApiImplicitParam(dataType = "Long", name = "groupId", value = "会员级别Id", required = true)
    @PostMapping(value = "deleteGroupById", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteGroupById(@RequestBody JSONObject object){
        return customerGroupService.deleteGroupById(object.getLong("groupId"));
    }
}
