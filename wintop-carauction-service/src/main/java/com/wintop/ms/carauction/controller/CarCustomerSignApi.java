package com.wintop.ms.carauction.controller;


import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerSign;
import com.wintop.ms.carauction.entity.CarCustomerSignLog;
import com.wintop.ms.carauction.service.ICarCustomerSignLogService;
import com.wintop.ms.carauction.service.ICarCustomerSignService;
import io.swagger.annotations.ApiImplicitParam;
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

/***
 * 签约服务
 */
@RestController
@RequestMapping("/sign")
public class CarCustomerSignApi {

    private Logger logger = LoggerFactory.getLogger(CarCustomerSignApi.class);

    @Autowired
    private ICarCustomerSignService carCustomerSignService;
    @Autowired
    private ICarCustomerSignLogService signLogService;

    /***
     * 查询某个签约详情
     * @param map
     * @return
     */
    @PostMapping(value = "getById")
    public ServiceResult<CarCustomerSign> getById(@RequestBody Map map){
        logger.info("查询某个签约详情");
        Long id = Long.parseLong(map.get("id").toString());
        return carCustomerSignService.findById(id);
    }

    /***
     * 查询某人的签约数据
     * @param map
     * @return
     */
    @PostMapping(value = "getByCustomerId")
    public ServiceResult<CarCustomerSign> getByCustomerId(@RequestBody Map map){
        logger.info("查询某人的签约数据");
        Long customerId = Long.parseLong(map.get("customerId").toString());
        return carCustomerSignService.findByCustomerId(customerId);
    }

    /***
     * 保存签字
     * @param map
     * @return
     */
    @PostMapping(value = "saveSignature")
    public ServiceResult<CarCustomerSign> saveSignature(@RequestBody Map map){
        logger.info("保存签字");
        CarCustomerSignLog carCustomerSignLog = new CarCustomerSignLog();
        carCustomerSignLog.setPdfFileUrl(map.get("pdfUrl").toString());
        carCustomerSignLog.setPicFileUrl(map.get("picUrl").toString());
        carCustomerSignLog.setLog(map.get("log").toString());

        CarCustomerSign carCustomerSign = new CarCustomerSign();
        carCustomerSign.setCustomerId(Long.parseLong(map.get("customerId").toString()));
        return carCustomerSignService.insert(carCustomerSign,carCustomerSignLog);
    }

    /***
     * 签字审核
     * @param map
     * @return
     */
    @PostMapping(value = "saveAuth")
    public ServiceResult<CarCustomerSign> saveAuth(@RequestBody Map map){
        logger.info("签字审核");
        CarCustomerSignLog signLog = new CarCustomerSignLog();
        signLog.setLog(map.get("log").toString());
        signLog.setPdfFileUrl(map.get("pdfUrl").toString());
        signLog.setPicFileUrl(map.get("picUrl").toString());
        CarCustomerSign carCustomerSign = new CarCustomerSign();
        carCustomerSign.setAuthManager(Long.parseLong(map.get("authManager").toString()));
        carCustomerSign.setStatus(map.get("status").toString());
        return carCustomerSignService.saveAuth(carCustomerSign,signLog);
    }
    /**
     * 根据签约id查询合同信息
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:
     */
    @ApiOperation(value = "根据签约id查询合同信息")
    @ApiImplicitParam(dataType = "Long", name = "signId", value = "签约Id", required = true)
    @PostMapping(value = "/selectSignLogBySignId")
    public ServiceResult<List<CarCustomerSignLog>> selectSignLogBySignId(@RequestBody Long signId){
      return signLogService.findBySignId(signId);
    }
}
