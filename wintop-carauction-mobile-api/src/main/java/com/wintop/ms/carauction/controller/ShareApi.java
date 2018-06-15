package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liangtingsen on 2018/3/9.
 * 分享使用接口
 */
@RestController
@RequestMapping("share")
public class ShareApi {

    /***
     * 分享车辆回调接口
     * @param autoId
     * @return
     */
    @RequestMapping(value = "/carCallBack",method = RequestMethod.POST)
    @ApiOperation(value = "分享车辆回调")
    public ResponseEntity<ResultModel> carCallBack(@RequestParam Long autoId){
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel = null;
        try {
            resultModel = new ResultModel(true, ResultStatus.SUCCESS);
            responseEntity = new ResponseEntity(resultModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resultModel = new ResultModel(true, ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return responseEntity;
        }
    }

    /***
     * 分享邀请回调通知
     * @return
     */
    @RequestMapping(value = "/invite",method = RequestMethod.POST)
    @ApiOperation(value = "分享邀请回调")
    public ResponseEntity<ResultModel> invite() {
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel = null;
        try {
            resultModel = new ResultModel(true, ResultStatus.SUCCESS);
            responseEntity = new ResponseEntity(resultModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resultModel = new ResultModel(true, ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return responseEntity;
        }
    }
}
