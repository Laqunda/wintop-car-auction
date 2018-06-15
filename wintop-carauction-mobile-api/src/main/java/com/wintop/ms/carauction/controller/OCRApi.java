package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ocr.alibabaapi.DriversLicenseParser;
import com.wintop.ocr.alibabaapi.DrivingCardParser;
import com.wintop.ocr.alibabaapi.IDcardParser;
import com.wintop.ocr.alibabaapi.LicensePlateParser;
import com.wintop.ocr.entity.DriversLicense;
import com.wintop.ocr.entity.DrivingCard;
import com.wintop.ocr.entity.IDcard;
import com.wintop.ocr.entity.LicensePlate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "图像识别接口，可是别行驶证，身份证")
@RestController
@RequestMapping("OCR")
public class OCRApi {

    Logger logger = LoggerFactory.getLogger(OCRApi.class);

    @ApiOperation(value = "OCR图像识别车牌号")
    @PostMapping(value = "licensePlate")
    public ResponseEntity<ResultModel> drivingLicense(@ApiParam(value = "车牌图片url") @RequestParam String url){
        logger.info("OCR图像识别车牌号");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            //车牌
            LicensePlate result = LicensePlateParser.parseLicensePlateFace(url);
            responseEntity = new ResponseEntity<>(ResultModel.ok(result), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @ApiOperation(value = "OCR图像识别行驶本")
    @PostMapping(value = "drivingCard")
    public ResponseEntity<ResultModel> drivingCard(@ApiParam(value = "行驶本图片url") @RequestParam String url){
        logger.info("OCR图像识别行驶本");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            //识别行驶证
            DrivingCard result = DrivingCardParser.parseDrivingCardFace(url);
            responseEntity = new ResponseEntity<>(ResultModel.ok(result), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @ApiOperation(value = "OCR图像识别驾照")
    @PostMapping(value = "driversLicense")
    public ResponseEntity<ResultModel> driversLicense(@ApiParam(value = "驾照图片url") @RequestParam String url){
        logger.info("OCR图像识别驾照");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            //识别驾照
            DriversLicense result = DriversLicenseParser.parseDriversLicenseFace(url);
            responseEntity = new ResponseEntity<>(ResultModel.ok(result), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @ApiOperation(value = "OCR图像识别身份证正面")
    @PostMapping(value = "parseIdCardFace")
    public ResponseEntity<ResultModel> parseIdCardFace(@ApiParam(value = "身份证正面图片url") @RequestParam String url){
        logger.info("OCR图像识别身份证正面");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            //识别身份证正面
            IDcard result = IDcardParser.parseIdCardFace(url);
            responseEntity = new ResponseEntity<>(ResultModel.ok(result), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @ApiOperation(value = "OCR图像识别身份证反面")
    @PostMapping(value = "parseIdCardBack")
    public ResponseEntity<ResultModel> parseIdCardBack(@ApiParam(value = "身份证反面图片url") @RequestParam String url){
        logger.info("OCR图像识别身份证反面");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            //识别身份证反面
            IDcard result = IDcardParser.parseIdCardBack(url);
            responseEntity = new ResponseEntity<>(ResultModel.ok(result), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }
}
