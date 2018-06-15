package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.service.ICarAutoProceduresService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhangzijuan
 * @Description:车辆手续信息
 * @date 2018-03-07
 */
@RestController
@RequestMapping(value = "/service/procedures")
public class CarAutoProceduresApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAutoProceduresApi.class);

    private IdWorker idWorker = new IdWorker(10);

    @Resource
    private ICarAutoProceduresService autoProceduresService;
    /**
     * 根据车辆d查询车辆手续信息
     * @return
     */
    @PostMapping(value = "getAutoProceduresByCarId",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarAutoProcedures> getAutoProceduresByCarId(@RequestBody JSONObject object){
        logger.info("根据车辆d查询车辆手续信息");
        return autoProceduresService.getAutoProceduresByCarId(object.getLong("carId"));
    }

    @PostMapping(value = "save")
    @ApiOperation(value = "保存车辆手续信息")
    public ServiceResult<CarAutoProcedures> save(@RequestBody JSONObject object){
        logger.info("保存车辆手续信息");

        ServiceResult<CarAutoProcedures> result = new ServiceResult<>();
        try {
            CarAutoProcedures procedures = new CarAutoProcedures();
//
            procedures.setAutoId(object.getLong("autoId"));
            procedures.setPurchaseTax(object.getLong("purchaseTax"));
            procedures.setDrivingLicense(object.getString("drivingLicense"));
            procedures.setRegistrationCertificate(object.getString("registrationCertificate"));
            procedures.setInstruction(object.getString("instruction"));
            procedures.setCompulsoryInsurance(object.getDate("compulsoryInsurance"));
            procedures.setBusinessInsurance(object.getDate("businessInsurance"));
            procedures.setYearInsurance(object.getDate("yearInsurance"));
            procedures.setNewCarInvoice(object.getString("newCarInvoice"));
            procedures.setQualityGuarantee(object.getString("qualityGuarantee"));
            procedures.setCarKeys(object.getInteger("carKeys"));
            procedures.setMaintenanceManual(object.getString("maintenanceManual"));
            procedures.setTicketOfTransfer(object.getString("ticketOfTransfer"));
            procedures.setCostPrice(object.getString("costPrice"));
            procedures.setIllegalPrice(object.getBigDecimal("illegalPrice"));
            procedures.setIllegalScore(object.getString("illegalScore"));
            procedures.setIllegalWho(object.getString("illegalWho"));
            procedures.setUnIllegal(object.getString("unIllegal"));
            procedures.setTransferNumber(object.getInteger("transferNumber"));
//            procedures.setFreightWho(object.getString("freightWho"));
//            procedures.setMentionFeeWho(object.getString("mentionFeeWho"));
            procedures.setTransferFee(object.getString("transferFee"));
            int x;
            if (object.get("id")==null){
                procedures.setId(idWorker.nextId());
                procedures.setCreateTime(new Date());
                procedures.setCreateUser(object.getLong("userId"));
                x = autoProceduresService.insert(procedures);
            }else {
                procedures.setId(object.getLong("id"));
                procedures.setUpdateTime(new Date());
                procedures.setUpdateUser(object.getLong("userId"));
                x = autoProceduresService.updateByPrimaryKey(procedures).getResult();
            }
            if (x>0){
                result.setResult(procedures);
                result.setSuccess("0","保存成功");
            }else {
                result.setSuccess("-1","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess("-1","保存失败");
        }finally {
            return result;
        }



    }
}
