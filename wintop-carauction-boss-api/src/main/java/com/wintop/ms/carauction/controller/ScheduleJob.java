package com.wintop.ms.carauction.controller;


import com.wintop.ms.carauction.core.config.Constants;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;

// @Controller
public class ScheduleJob {

    private final RestTemplate restTemplate;
    ScheduleJob(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//   @Scheduled(fixedRate = 1000)
    public void redisAutoDataJob() {
        //System.out.println("--------2------");
        this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoSchedule/dataJob"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap()),Integer.class);
    }

//    @Scheduled(cron = "0 0 5 * * ?")
    public void deleteHisDataLog() {
        this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoSchedule/deleteHisDataLog"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap()),Integer.class);
    }

//    @Scheduled( fixedRate = 1000 )
    public void startAuctionViewed(){
        this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoSchedule/startAuctionViewed"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap()),Integer.class);
    }

}
