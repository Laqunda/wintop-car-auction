package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/***
 * 车辆管理------
 * 发车
 * 修改
 * 删除
 * 复制
 */
@RestController
@RequestMapping("autoManager")
public class AutoManagerApi {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AutoManagerApi.class);
    public static final String initAuto_URL = Constants.ROOT+"/service/carAuto/initAuto";
    public static final String submitAuth_URL = Constants.ROOT+"/service/carAuto/submitAuth";
    public static final String delAuto_URL = Constants.ROOT+"/service/carAuto/del";
    public static final String getAutoProcedureInfo_URL = Constants.ROOT+"/service/procedures/getProcedurefo";
    public static final String getAutoAuction_URL = Constants.ROOT+"/service/carAutoAuction/getByAutoId";
    /**查询车辆发布完成情况*/
    public static final String getAutoPublishStatus_URL = Constants.ROOT+"/service/carAuto/getAutoPublishStatus";

    AutoManagerApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "initAuto")
    @AuthUserToken
    @ApiOperation(value = "初始化一辆新车记录" ,notes = "初始化一辆车辆信息")
    public ResultModel initAuto(@CurrentUser CarManagerUser managerUser,
                                                @ApiParam(value = "二手车、新车",required = true) @RequestParam String ifNew){
        logger.info("初始化一辆新车记录");
        String auctionType;
        if (managerUser==null || managerUser.getRoleTypeId()==null){
            //如果不是中心或经销店人员，则无权限新增车辆
            return new ResultModel(false, ResultCode.NO_REQUEST_AUTH.value(),ResultCode.NO_REQUEST_AUTH.getRemark(),null);
        }else if (managerUser.getRoleTypeId()==2L){
            //中心--线下
            auctionType = "2";
        }else if (managerUser.getRoleTypeId()==3L){
            //经销店--线上
            auctionType = "1";
        }else {
            //如果不是中心或经销店人员，则无权限新增车辆
            return new ResultModel(false, ResultCode.NO_REQUEST_AUTH.value(),ResultCode.NO_REQUEST_AUTH.getRemark(),null);
        }

        JSONObject object = new JSONObject();
        object.put("ifNew",ifNew);
        object.put("userId",managerUser.getId());
        object.put("auctionType",auctionType);
        object.put("regionId",managerUser.getRegionId());
        object.put("userMobile",managerUser.getUserPhone());
        object.put("userName",managerUser.getUserName());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(initAuto_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    @PostMapping(value = "submitAuth")
    @AuthUserToken
    @ApiOperation(value = "提交审核车辆",notes = "提交车辆为待审核状态")
    public ResultModel submitAuth(@CurrentUser CarManagerUser managerUser, @ApiParam(value = "车辆id") @RequestParam Long autoId){
        ResultModel resultModel = null;
        try {
            logger.info("提交车辆为待审核状态");
            JSONObject object = new JSONObject();
            object.put("updateUser",managerUser.getId());
            object.put("autoId",autoId);
            object.put("userMobile",managerUser.getUserPhone());
            object.put("userName",managerUser.getUserName());
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(submitAuth_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            resultModel = ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            resultModel  =new ResultModel(false, ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark());
        }finally {
            return resultModel;
        }
    }

    @PostMapping(value = "del")
    @AuthUserToken
    @ApiOperation(value = "删除车辆")
    public ResultModel del(@CurrentUser CarManagerUser managerUser, @ApiParam(value = "车辆id") @RequestParam Long autoId){
        ResultModel resultModel = null;
        try {
            logger.info("删除车辆");
            JSONObject object = new JSONObject();
            object.put("updateUser",managerUser.getId());
            object.put("autoId",autoId);
            object.put("userMobile",managerUser.getUserPhone());
            object.put("userName",managerUser.getUserName());
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(delAuto_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            resultModel = ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            resultModel  =new ResultModel(false, ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark());
        }finally {
            return resultModel;
        }
    }


}
