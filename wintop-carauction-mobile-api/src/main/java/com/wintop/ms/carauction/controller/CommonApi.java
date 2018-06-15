package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/***
 * 公共接口
 */
@RequestMapping("common")
@RestController
public class CommonApi {

    Logger logger = LoggerFactory.getLogger(CommonApi.class);

    /***
     * 获取当前服务器时间
     * @return
     */
    @RequestMapping(value = "/getCurrentTime")
    public ResponseEntity<ResultModel> getCurrentTime(){
        logger.info("调用获取当前服务器时间");
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel = null;
        try {
            Map map = new HashMap();
            map.put("currentTime",System.currentTimeMillis());
            resultModel = new ResultModel(true, ResultStatus.SUCCESS,map);
            responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            resultModel = new ResultModel(true, ResultStatus.SERVICE_ERROR);
            responseEntity = new ResponseEntity<>(resultModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }
}
