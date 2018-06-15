package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoTransferLog;
import com.wintop.ms.carauction.service.ICarAutoTransferLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-26
 */
@RestController
@RequestMapping("/service/transferLog")
public class CarAutoTransferLogApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAutoTransferBossApi.class);
    @Autowired
    private ICarAutoTransferLogService transferLogService;

    /**
     * 查询过户轨迹
     *@Author:zhangzijuan
     *@date 2018/3/26
     *@param:
     */
    @ApiOperation(value = "查询过户轨迹")
    @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long")
    @PostMapping(value = "/queryTransferList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarAutoTransferLog>> queryTransferList(@RequestBody Map<String,Object> map){
        logger.info("查询过户轨迹");
        return transferLogService.queryTransferList(map);
    }

}
