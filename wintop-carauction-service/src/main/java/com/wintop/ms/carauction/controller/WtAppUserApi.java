package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.ICarCustomerDepositService;
import com.wintop.ms.carauction.service.ICarCustomerSignService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.IWtAppUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户端用户使用接口类
 * 如：登陆
 */
@RestController
public class WtAppUserApi {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(WtAppUserApi.class);

    @Autowired
    private IWtAppUserService appUserService;
    @Autowired
    private ICarCustomerSignService signService;
    @Autowired
    private ICarManagerUserService managerUserService;
    @Autowired
    private ICarCustomerDepositService depositService;


    /***
     * 客户端用户登陆使用
     * @param map
     * @return
     */
    @RequestMapping(value = "/service/appuser/login",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method=RequestMethod.POST,/*指定请求的method类型， GET、POST、PUT、DELETE等；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<WtAppUser> findUserList(@RequestBody Map map) {
        Logger.info("登陆查询");
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        return this.appUserService.findByUserNamePwd(username,password);
    }

    /***
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @RequestMapping(value = "/service/appuser/findByUsername",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method=RequestMethod.POST,/*指定请求的method类型， GET、POST、PUT、DELETE等；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<WtAppUser> findByUsername(@RequestBody String username) {
        Logger.info("查询用户信息");
        return this.appUserService.findByUserByUsername(username);
    }

    /***
     * 客户端获取用户信息
     * @param appUserId
     * @return
     */
    @RequestMapping(value = "/service/appuser/getuser",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method=RequestMethod.POST,/*指定请求的method类型， GET、POST、PUT、DELETE等；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<WtAppUser> findUserById(@RequestBody Long appUserId) {
        Logger.info("查询用户信息");
        return this.appUserService.findById(appUserId);
    }

    /***
     *  保存用户信息
     * @return
     */
    @RequestMapping(value = "/service/appuser/saveuser",
    method = RequestMethod.POST,
    consumes = "application/json; charset=UTF-8",
    produces = "application/json; charset=UTF-8")
    public ServiceResult<WtAppUser> saveUser(@RequestBody Map userMap){
        Logger.info("保存用户信息");
        String username = userMap.get("username").toString();
        String password = userMap.get("password").toString();
        String name = userMap.get("name").toString();
        WtAppUser appUser = new WtAppUser();
        appUser.setUserName(username);
        appUser.setPassword(password);
        appUser.setName(name);
        appUser.setManualAdd("0");
        appUser.setIsShare("2");
        return appUserService.saveUser(appUser);
    }

    /***
     *  修改用户密码
     * @return
     */
    @RequestMapping(value = "/service/appuser/updatePassword",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<WtAppUser> updatePassword(@RequestBody Map userMap){
        Logger.info("修改用户信息");
        String username = (String) userMap.get("username");
        String password = userMap.get("password").toString();
        WtAppUser appUser = new WtAppUser();
        appUser.setUserName(username);
        appUser.setPassword(password);
        return appUserService.updatePwd(appUser);
    }

    /***
     *  修改用户头像
     * @return
     */
    @RequestMapping(value = "/service/appuser/updateHeadImg",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<WtAppUser> updateHeadImg(@RequestBody Map userMap){
        Logger.info("修改用户信息");
        Long id = Long.parseLong(userMap.get("id")+"");
        String headImg = userMap.get("headImg").toString();
        WtAppUser appUser = new WtAppUser();
        appUser.setId(id);
        appUser.setHeadImg(headImg);
        return appUserService.updateUser(appUser);
    }

    /***
     *  修改用户信息
     * @return
     */
    @RequestMapping(value = "/service/appuser/updateUser",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<WtAppUser> updateUser(@RequestBody Map userMap) {
        Logger.info("修改用户信息");
        Long id = (Long) userMap.get("id");
        String name = (String) userMap.get("name");
        String address = (String) userMap.get("address");
        String city = (String) userMap.get("city");
        String headImg = (String) userMap.get("headImg");
        String sex = (String) userMap.get("sex");
        String mobile = (String) userMap.get("mobile");
        String loginIp = (String) userMap.get("loginIp");
        Timestamp loginTime = (Timestamp) userMap.get("loginTime");
        String status = (String) userMap.get("status");
        String userNum = (String) userMap.get("userNum");
        Long userLevelId = (Long) userMap.get("userLevelId");
        WtAppUser appUser = new WtAppUser();
        appUser.setName(name);
        appUser.setId(id);
        appUser.setAddress(address);
        appUser.setCity(city);
        appUser.setHeadImg(headImg);
        appUser.setSex(sex);
        appUser.setMobile(mobile);
        appUser.setLoginIp(loginIp);
        appUser.setLoginTime(loginTime);
        appUser.setStatus(status);
        appUser.setUserNum(userNum);
        appUser.setUserLevelId(userLevelId);
        return appUserService.updateUser(appUser);
    }
    /**
     * 用户信息的查询
     * @param appUserId
     * @return
     */
    @ApiOperation(value = "用户信息的查询")
    @PostMapping(value = "/service/appuser/getUserDetail",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<WtAppUser> selectUserById(@RequestBody Long appUserId) {
        Logger.info("用户信息的查询");
        return this.appUserService.selectUserById(appUserId);
    }

    /**
     * 根据参数查询用户信息列表
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "根据参数查询用户信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName",value = "姓名、手机号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "status",value = "会员状态",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "groupId",value = "分组Id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "storeId",value = "店铺ID",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/service/appuser/selectListByParam", produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<WtAppUser>> selectListByParam(@RequestBody Map<String,Object> map){
        Logger.info("根据参数查询用户信息列表");
        ServiceResult<ListEntity<WtAppUser>> result = new ServiceResult<>();
        try {
            /**
             * 数据权限过滤
             */
            Long userId=Long.parseLong(map.get("managerId").toString());
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                map.put("storeIds",storeIds);
            }
            if(map.get("groupId")!=null && "0".equals(map.get("groupId").toString())){
                map.put("groupId",null);
            }
            ListEntity<WtAppUser> listEntity = new ListEntity<>();
            Logger.info("page: "+map.get("page")+";limit:"+map.get("limit"));
            Integer page=(Integer) map.get("page");
            Integer pageSize=(Integer) map.get("limit");
            map.put("startRowNum",(page-1)*pageSize);
            map.put("endRowNum",pageSize);
            List<WtAppUser> appUsers=appUserService.selectListByParam(map);
            if(appUsers!=null && appUsers.size()!=0){
                //如果查询的签约列表
                if (map.get("status")!=null && "5".equals(map.get("status"))){
                    Map<String,Object> paraMap=new HashMap<>();
                    for (WtAppUser appUser:appUsers){
                        CarCustomerSign sign=signService.querySignByUserId(appUser.getId());
                        appUser.setSignatureTime(sign.getSignatureTime());
                        appUser.setSignId(sign.getId());
                        paraMap.put("userId",appUser.getId());
                        CarCustomerDeposit deposit=depositService.selectDepositByUserId(paraMap);
                        //1=type保证金和签约都未审核
                        if ("1".equals(deposit.getStatus()) && "1".equals(sign.getStatus())){
                            appUser.setType("1");
                        // 2=保证金已经审核通过，需审核签约
                        }else if ("2".equals(deposit.getStatus()) && "1".equals(sign.getStatus())){
                            appUser.setType("2");
                            //3=签约已经通过，需审核保证金
                        }else if ("1".equals(deposit.getStatus()) && "2".equals(sign.getStatus())){
                            appUser.setType("3");
                        }
                    }
                }
                listEntity.setList(appUsers);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
            listEntity.setCount(appUserService.selectCountByParam(map));
            result.setResult(listEntity);
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
           e.printStackTrace();
        }
        return  result;
    }
/**
 * 设置会员退会
 *@Author:zhangzijuan
 *@date 2018/3/16
 *@param:
 */
@ApiOperation(value = "设置会员退会")
@ApiImplicitParam(dataType = "Long", name = "userId", value = "会员Id", required = true)
@PostMapping(value = "/service/appuser/userSignOut", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> userSignOut(@RequestBody JSONObject object){
        return  appUserService.userSignOut(object);
    }
    /**
     * 批量修改会员级别
     *@Author:zhangzijuan
     *@date 2018/3/16
     *@param:
     */
    @ApiOperation(value = "批量修改会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds",value = "用户id，多个用户id用逗号拼接",required = true,dataType = "string"),
            @ApiImplicitParam(name = "userLevelId",value = "级别id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "msg",value = "调整原因",required = true,dataType = "string"),
    })
    @PostMapping(value = "/service/appuser/batchUpdateUserLevel", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> batchUpdateUserLevel(@RequestBody JSONObject object){
    return appUserService.batchUpdateUserLevel(object);
    }

    /**
     * 修改会员分组
     *@Author:zhangzijuan
     *@date 2018/3/16
     *@param:
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "groupIds",value = "分组Id，多个id用逗号拼接",required = true,dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "调整原因",required = true,dataType = "string"),
    })
    @ApiOperation(value = "修改会员分组")
    @PostMapping(value = "/service/appuser/updateUserGroup", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateUserGroup(@RequestBody JSONObject object){
        return appUserService.updateUserGroup(object);
    }


    /**
     * 修改会员
     *@Author:zhangzijuan
     *@date 2018/3/16
     *@param:
     */
    @ApiOperation(value = "修改会员信息")
    @PostMapping(value = "/service/appuser/editUser", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> editUser(@RequestBody JSONObject object){
        return  appUserService.editUser(object);
    }

    /**
     * 查询会员详情信息
     *@Author:zhangzijuan
     *@date 2018/3/19
     *@param:
     */
    @ApiOperation(value = "查询会员详情信息")
    @PostMapping(value = "/service/appuser/getUserInfoById", produces="application/json; charset=UTF-8")
    public ServiceResult<WtAppUser> getUserInfoById(@RequestBody JSONObject object){
        return  appUserService.getUserInfoById(object.getLong("userId"));
    }
    /**
     * 审批签约信息
     *@Author:zhangzijuan
     *@date 2018/3/21
     *@param:
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "signId",value = "签约信息id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "authStatus",value = "审核状态 2通过 ，3不通过",required = true,dataType = "string"),
            @ApiImplicitParam(name = "authMsg",value = "调整原因",required = true,dataType = "string"),
    })
    @ApiOperation(value = "审批签约信息")
    @PostMapping(value = "/service/appuser/approveSignInfo", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> approveSignInfo(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=appUserService.approveSignInfo(object);
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
     * 设置会员冻结解冻
     *@Author:zhangzijuan
     *@date 2018/3/21
     *@param:
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "file",value = "附件",required = true,dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "冻结原因",required = true,dataType = "string"),
    })
    @ApiOperation(value = "设置会员冻结解冻")
    @PostMapping(value = "/service/appuser/setUserFreeze", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> setUserFreeze(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=appUserService.setUserFreeze(object);
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
     * 查询可以出价的用户
     * @return
     */
    @ApiOperation(value = "查询可以出价的用户")
    @PostMapping(value = "/service/appuser/selectAllUserForSelect", produces="application/json; charset=UTF-8")
    public ServiceResult<List<CommonNameVo>>selectAllUserForSelect(){
        return appUserService.selectAllUserForSelect();
    }


    /**
     * 根据拍牌号查询用户
     *@Author:zhangzijuan
     *@date 2018/4/3
     *@param:
     */
    @ApiImplicitParam(name = "auctionPlateNum",value = "拍牌号",required = true,dataType = "string")
    @ApiOperation(value = "根据拍牌号查询用户")
    @PostMapping(value = "/service/appuser/selectUserByAuctionPlateNum", produces="application/json; charset=UTF-8")
    public ServiceResult<WtAppUser> selectUserByAuctionPlateNum(@RequestBody JSONObject object){
        ServiceResult<WtAppUser> result=new ServiceResult<>();
        try {
            WtAppUser appUser=appUserService.selectUserByAuctionPlateNum(object.getString("auctionPlateNum"));
            if (appUser!=null){
                result.setResult(appUser);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 简单存储用户信息
     *@Author:zhangzijuan
     *@date 2018/3/21
     *@param:
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionPlateNum",value = "拍牌号",required = true,dataType = "string"),
            @ApiImplicitParam(name = "name",value = "姓名",required = true,dataType = "string"),
            @ApiImplicitParam(name = "mobile",value = "电话",required = true,dataType = "string"),
            @ApiImplicitParam(name = "depositAmount",value = "保证金金额",required = true,dataType = "double"),
    })
    @ApiOperation(value = "简单存储用户信息")
    @PostMapping(value = "/service/appuser/simpleSaveUser", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> simpleSaveUser(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=appUserService.simpleSaveUser(object);
            if (i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else if(i==-1){
                result.setError(ResultCode.REPEAT_AUCTION_PLATE.strValue(),ResultCode.REPEAT_AUCTION_PLATE.getRemark());
            }else if (i==-2){
                result.setError(ResultCode.KEY_EXIST.strValue(),ResultCode.KEY_EXIST.getRemark());
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
     * 修改会员拍牌号
     *@Author:zhangzijuan
     *@date 2018/4/9
     *@param:
     */
    @ApiOperation(value = "修改会员拍牌号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionPlateNum",value = "拍牌号",required = true,dataType = "string"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "long"),
    })
    @PostMapping(value = "/service/appuser/editUserAuctionPlateNum", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> editUserAuctionPlateNum(@RequestBody JSONObject object){
        return  appUserService.editUserAuctionPlateNum(object);
    }
}