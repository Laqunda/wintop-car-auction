package com.wintop.ms.carauction.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.core.util.UtilDate;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.RedisTokenManager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

/**
 * 会员信息相关
 *@Author:zhangzijuan
 *@date 2018/3/14
 *@param:
 */
@RestController
@RequestMapping("/user")
public class AppUserApi {
    @Autowired
    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(CarCustomerSignApi.class);
    AppUserApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
    @PostMapping(value = "/getCustomerList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getCustomerList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/selectListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 设置会员退会
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "设置会员退会")
    @ApiImplicitParam(dataType = "Long", name = "userId", value = "会员Id", required = true)
    @PostMapping(value = "/userSignOut",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel userSignOut(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null || map.get("msg")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/userSignOut"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);

        if(response.getStatusCode()== HttpStatus.OK) {
            JSONObject obj = response.getBody();
            boolean success = obj.getBoolean("success");
            if (success) {
                Map<String,Object> mapMessage=new HashMap<>();
                mapMessage.put("userId",map.get("userId"));
                //2=买家
                mapMessage.put("userType","1");
                //打开类型 1=系统消息
                mapMessage.put("openObjType","1");
                mapMessage.put("openObjId",map.get("userId"));
                mapMessage.put("title","退会成功");
                mapMessage.put("content","您已经退会，退会原因为："+map.get("msg")+"，如有疑问，请联系客服。");
                this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ROOT+"/carMessage/sendMessageByUserId"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(mapMessage),JSONObject.class);
            }
        }
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 修改会员分组
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "修改会员分组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "groupIds",value = "分组Id，多个id用逗号拼接",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "调整原因",required = true,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/editUserGroup",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateUserGroup(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null || map.get("groupIds")==null ){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/updateUserGroup"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 批量修改会员级别
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "批量修改会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds",value = "用户id，多个用户id用逗号拼接",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "userLevelId",value = "级别id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "msg",value = "调整原因",required = true,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/editUserLevel",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel batchUpdateUserLevel(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("userLevelId")==null || map.get("userIds")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/batchUpdateUserLevel"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 修改会员信息
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "修改会员信息")
    @PostMapping(value = "/editUser",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel editUser(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/editUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 查询会员信息
     *@Author:zhangzijuan
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "查询会员信息")
    @PostMapping(value = "/getUserInfo",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getUserInfo(@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/getUserInfoById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询签约审核的用户信息列表
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:map
     */
    @ApiOperation(value = "查询签约审核的用户信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName",value = "姓名、手机号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getSignUserList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getSignUserList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("status","5");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/selectListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 审批签约信息
     *@Author:zhangzijuan
     *@date 2018/3/21
     *@param:map
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "signId",value = "签约信息id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "authStatus",value = "审核状态 2通过 ，3保证金通过、签约不通过 4保证金审核不通过,签约通过 5保证金，签约都不通过" ,dataType = "string"),
            @ApiImplicitParam(name = "authMsg",value = "调整原因",required = true,dataType = "string"),
    })
    @ApiOperation(value = "审批签约信息")
    @PostMapping(value = "/approveSignInfo",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel approveSignInfo(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null || map.get("signId")==null || map.get("authStatus")==null || map.get("authMsg")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        String authStatus=(String) map.get("authStatus");
        map.put("type","1");
        if ("2".equals(authStatus) || "4".equals(authStatus) || "6".equals(authStatus) ) {
            //审核通过
            //a、获取当前用户的签名合同，调用签约服务接口生成签章合同
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT + "/sign/selectSignLogBySignId"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map.get("signId")), JSONObject.class);
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONArray("result");
            if (result != null && result.size() > 0) {
                JSONObject object = result.getJSONObject(0);
                //3、调用e签宝生成签字版合同

                //获取用户信息
                ResultModel resultModel = this.getUserInfo(map);
                JSONObject userObj = (JSONObject) resultModel.getData();

                //3、根据模板生成填充好的pdf合同
                Map map2 = new HashMap();
                map2.put("url", object.getString("pdfFileUrl"));
                Calendar cal = Calendar.getInstance();
                Calendar cal_next = Calendar.getInstance();
                cal_next.add(Calendar.YEAR,1);
                List pdf_map = new ArrayList();

                Map map1 = new HashMap();
                map1.put("title", "柠檬竞价");
                map1.put("merchant_name", userObj.getString("name"));
                map1.put("payway_alipay", "√");
                map1.put("paytime_year", (cal.get(Calendar.YEAR) + "").substring(2));
                map1.put("paytime_month", cal.get(Calendar.MONTH) + 1);
                map1.put("paytime_day", cal.get(Calendar.DAY_OF_MONTH));
                map1.put("signtime_year", (cal.get(Calendar.YEAR) + "").substring(2));
                map1.put("signtime_month", cal.get(Calendar.MONTH) + 1);
                map1.put("signtime_day", cal.get(Calendar.DAY_OF_MONTH));
                map1.put("expire_year", ((cal_next.get(Calendar.YEAR)) + "").substring(2));
                map1.put("expire_month", cal_next.get(Calendar.MONTH)+1);
                map1.put("expire_day", cal_next.get(Calendar.DAY_OF_MONTH));
                map1.put("representative_idCard", userObj.getString("identityNumber"));
                map1.put("representative_tel", userObj.getString("mobile"));
                map1.put("representative_date", UtilDate.todayFormat(new Date()));
                map1.put("platform_date", UtilDate.todayFormat(new Date()));
                pdf_map.add(map1);
                map2.put("pdf_map", pdf_map);
                //=====================根据空白模板生成填充好的pdf合同
                response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.CREATESIGNATURE_TEMP_URL))
                                .header("Content-Type",new String[]{MediaType.APPLICATION_JSON_UTF8.toString()})
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .body(JSONObject.toJSON(map2)), JSONObject.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject pdfObj = response.getBody();
                    if (pdfObj != null && "0".equals(pdfObj.get("errcode").toString())) {
                        //3、=====================调用e签宝生成签字版合同=============
                        Map map3 = new HashMap();
                        map3.put("user", userObj.get("name"));
                        map3.put("cmp_key", Constants.SIGNATURE_BSE_KEY);
                        map3.put("path_src", pdfObj.getString("pdf"));
                        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                                RequestEntity
                                        .post(URI.create(Constants.PLATFORM_SIGNATURE_URL))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(JSONObject.toJSON(map3)), JSONObject.class);

                        JSONObject serviceResult = resultResponseEntity.getBody();
                        logger.info(serviceResult.toJSONString());
                        if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                            if ("0".equals(serviceResult.get("errcode").toString())) {
                                String picture = serviceResult.getString("picture");
                                String pdf = serviceResult.getString("pdf");
                                //4、保存签约成功的信息
                                map.put("pdfUrl", pdf);
                                map.put("picUrl", picture);
                                map.put("log", serviceResult.toJSONString());
                            }

                        }
                    }

                }
            } else {
                //获取不到签约信息
                return new ResultModel(false, ResultCode.NO_PARAM.value(), ResultCode.NO_PARAM.getRemark(), null);
            }
        }
        //5、保存签约表状态 + 保存签约日志 + 修改用户签约状态
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/approveSignInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);

        if(response.getStatusCode()== HttpStatus.OK) {
            JSONObject obj = response.getBody();
            boolean success = obj.getBoolean("success");
            if (success) {
                Map<String,Object> mapMessage=new HashMap<>();
                mapMessage.put("userId",map.get("userId"));
                //2=买家
                mapMessage.put("userType","1");
                mapMessage.put("openObjId",map.get("userId"));
                mapMessage.put("title","柠檬竞价提醒您,签约信息审核");
//                2都通过 ，3保证金通过、签约不通过 4保证金审核不通过,签约通过 5保证金，签约都不通过
                if ("2".equals(map.get("authStatus")) || "6".equals(map.get("authStatus")) || "8".equals(map.get("authStatus"))){
                    mapMessage.put("content","签约信息审核通过。");
                    //打开类型 6=签约界面
                    mapMessage.put("openObjType","6");
                }else if ("3".equals(map.get("authStatus")) || "7".equals(map.get("authStatus"))){
                    mapMessage.put("openObjType","6");
                    mapMessage.put("content","签约信息审核未通过，原因："+map.get("authMsg")+"，如有疑问，请联系客服。");
                }else if ("4".equals(map.get("authStatus")) ||"9".equals(map.get("authStatus"))){
                    //8=保证金
                    mapMessage.put("openObjType","8");
                    mapMessage.put("content","保证金审核未通过，原因："+map.get("authMsg")+"，如有疑问，请联系客服。");
                }else if ("5".equals(map.get("authStatus"))){
                    mapMessage.put("openObjType","6");
                    mapMessage.put("content","签约不通过，原因："+map.get("authMsg")+"，如有疑问，请联系客服。");
                }
                this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ROOT+"/carMessage/sendMessageByUserId"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(mapMessage),JSONObject.class);
            }
        }
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
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
    @PostMapping(value = "/setUserFreeze",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel setUserFreeze(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null  || map.get("msg")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/setUserFreeze"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        if(response.getStatusCode()== HttpStatus.OK) {
            JSONObject obj = response.getBody();
            boolean success = obj.getBoolean("success");
            if (success) {
                Map<String,Object> mapMessage=new HashMap<>();
                mapMessage.put("userId",map.get("userId"));
                //2=买家
                mapMessage.put("userType","1");
                //打开类型 1=系统消息
                mapMessage.put("openObjType","1");
                mapMessage.put("openObjId",map.get("userId"));
                mapMessage.put("title","柠檬竞价提醒您，您的账号已冻结");
                mapMessage.put("content","您的账号已被冻结，冻结原因为："+map.get("msg")+"，如有疑问，请联系客服。");
                this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ROOT+"/carMessage/sendMessageByUserId"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(mapMessage),JSONObject.class);
            }
        }
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询可以出价的用户
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:
     */
    @ApiOperation(value = "查询可以出价的用户")
    @PostMapping(value = "/selectAllUserForSelect",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllUserForSelect() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/selectAllUserForSelect"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 查询用户相关的所有的状态
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:
     */
    @ApiOperation(value = "查询用户相关的所有的状态")
    @PostMapping(value = "/selectAllUserStatus",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllUserStatus() {
        Map<String,Object> map=new HashMap<>();
        map.put("type","1");
        map.put("noStatus",8);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/relateStatus/selectAllStatus"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 根据拍牌号查询用户
     *@Author:zhangzijuan
     *@date 2018/4/3
     *@param:
     */
    @ApiImplicitParam(name = "getUserByAuctionPlateNum",value = "拍牌号",required = true,dataType = "string")
    @ApiOperation(value = "根据拍牌号查询用户")
    @PostMapping(value = "/getUserByAuctionPlateNum",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel getUserByAuctionPlateNum(@RequestBody Map<String,Object> map) {
        if(map.get("auctionPlateNum")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/selectUserByAuctionPlateNum"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
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
    @PostMapping(value = "/simpleSaveUser",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel simpleSaveUser(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("auctionPlateNum")==null  || map.get("name")==null  || map.get("mobile")==null || map.get("depositAmount")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/simpleSaveUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
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
    @PostMapping(value = "/editUserAuctionPlateNum",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel editUserAuctionPlateNum(@RequestBody Map<String,Object> map) {
        if(map.get("auctionPlateNum")==null || map.get("userId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/editUserAuctionPlateNum"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
