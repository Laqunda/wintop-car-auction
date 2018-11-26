package com.wintop.ms.carauction.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.core.util.UtilDate;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Controller
public class ScheduleJob {

    private final RestTemplate restTemplate;
    ScheduleJob(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void redisAutoDataJob() {
        //System.out.println("--------2------");
        this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoSchedule/dataJob"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap()),Integer.class);
    }

}
