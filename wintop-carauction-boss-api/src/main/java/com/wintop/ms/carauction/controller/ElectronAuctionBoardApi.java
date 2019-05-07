package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/electronAuctionBoard")
public class ElectronAuctionBoardApi {

    private final RestTemplate restTemplate;
    ElectronAuctionBoardApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询拍牌列表
     * @return
     */
    @RequestMapping(value = "/selectAuctionBoardList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAuctionBoardList(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuctionBoard/selectAuctionBoardList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 保存拍牌
     * @param managerId
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveAuctionBoard",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveAuctionBoard(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("createPerson",managerId);
        map.put("createTime",new Date());
        map.put("delFlag","1");
        map.put("id", IdWorker.getInstance().nextId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuctionBoard/saveAuctionBoard"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 更新拍牌
     * @param managerId
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateAuctionBoard",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateAuctionBoard(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("modifyPerson",managerId);
        map.put("modifyTime",new Date());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuctionBoard/updateAuctionBoard"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 删除拍牌
     * @param managerId
     * @param map
     * @return
     */
    @RequestMapping(value = "/deleteAuctionBoard",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteAuctionBoard(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("delPerson",managerId);
        map.put("delTime",new Date());
        map.put("delFlag","2");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuctionBoard/deleteAuctionBoard"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询拍牌
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectAuctionBoard",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAuctionBoard(@RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuctionBoard/selectAuctionBoard"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}