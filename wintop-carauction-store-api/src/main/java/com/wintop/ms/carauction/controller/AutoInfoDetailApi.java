package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.core.util.UtilDate;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ocr.alibabaapi.DrivingCardParser;
import com.wintop.ocr.entity.DrivingCard;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/***
 * 车辆-----------基本信息
 */
@RestController
@RequestMapping("/autoDetail")
public class AutoInfoDetailApi {
    private final RestTemplate restTemplate;
    private ResultModel resultModel;
    private static final Logger logger = LoggerFactory.getLogger(AutoInfoDetailApi.class);

    public static final String update_URL = Constants.ROOT+"/autoDetail/update";
    public static final String getAutoDetail_URL = Constants.ROOT+"/autoDetail/getByAutoId";
    public static final String updateRemark_URL = Constants.ROOT+"/autoDetail/updateRemark";
    public static final String saveDetailAndConf_URL = Constants.ROOT+"/autoDetail/saveDetailAndConf";

    //获取车辆全部配置项
    public static final String getAllConf_URL= Constants.ROOT + "/autoConfTitle/getAllApp";

    AutoInfoDetailApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "getBaseInfo")
    @ApiOperation(value = "获取车辆基础信息",notes = "根据车辆id获取车辆的基本信息")
    public ResponseEntity<ResultModel> getBaseInfo(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId){
        logger.info("获取车辆基础信息");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            JSONObject object = new JSONObject();
            object.put("autoId",autoId);

            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getAutoDetail_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            responseEntity = ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @PostMapping(value = "updateInfo")
    @ApiOperation(value = "修改车辆基本信息",notes = "修改保存车辆基本信息")
    @AuthUserToken
    public ResponseEntity<ResultModel> updateInfo(@CurrentUserId Long userId,
                                                  @ApiParam(value = "车辆信息",required = true) @RequestBody JSONObject object){
        logger.info("修改车辆基本信息");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            ResponseEntity<JSONObject> response;
            if (object.get("autoStyle")!=null) {
                //获取当前车型在精友中的全称
                JSONObject object_ = new JSONObject();
                object_.put("id", object.getString("autoStyle"));
                response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ID_GET_VEHICLEDETAIL_URL))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(object_), JSONObject.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject body = response.getBody();
                    if (body != null && "000000".equals(body.getString("code"))) {
                        String commonName = body.getJSONObject("result").getJSONObject("vehConfig").getJSONObject("vcBase").getString("commonName");
                        resultModel = ResultModel.ok();
                        object.put("autoInfoName",commonName);
                    }
                }
            }

            object.put("updateUser",userId);
            response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(update_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            responseEntity = ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @PostMapping(value = "updateRemark")
    @ApiOperation(value = "修改车辆备注信息",notes = "修改保存车辆备注信息")
    @AuthUserToken
    public ResponseEntity<ResultModel> updateRemark(@CurrentUserId Long userId,
                                                    @ApiParam(value = "车辆id+备注信息",required = true) @RequestBody JSONObject object){
        logger.info("修改车辆备注信息");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            object.put("updateUser",userId);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(updateRemark_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            responseEntity = ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }


    @PostMapping(value = "getVehicleListByVin")
    @ApiOperation(value = "根据vin码匹配车型列表",notes = "根据vin码匹配车型列表")
    public ResponseEntity<ResultModel> getDetailByVIN(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId,@ApiParam(value = "车辆vin码",required = true) @RequestParam String vin){
        logger.info("根据vin码匹配车型列表");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            JSONObject object = new JSONObject();
            object.put("autoId",autoId);

            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getAutoDetail_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject object1 = response.getBody().getJSONObject("result");
                if (object1 != null && StringUtils.isNotEmpty(vin)) {
                    object.put("vin", vin);
                    response = this.restTemplate.exchange(
                            RequestEntity
                                    .post(URI.create(Constants.VIN_GET_VEHICLELIST_URL))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(object), JSONObject.class);
                    if (response.getStatusCode() == HttpStatus.OK) {
                        JSONObject body = response.getBody();
                        if (body != null && "000000".equals(body.getString("code"))) {
                            JSONObject object2 = new JSONObject();
                            JSONArray returnStyleList = new JSONArray();
                            JSONArray styleList = body.getJSONObject("result").getJSONArray("vehicleList");
                            if (styleList!=null && styleList.size()>0) {
                                for (Object obj : styleList) {
                                    JSONObject styleObj = (JSONObject) obj;
                                    JSONObject styleObj_1 = new JSONObject();
                                    styleObj_1.put("autoBrand", styleObj.getString("brandId"));
                                    styleObj_1.put("autoBrandCn", styleObj.getString("brandName"));
                                    styleObj_1.put("autoStyle", styleObj.getString("vehicleId"));
                                    styleObj_1.put("autoStyleCn", styleObj.getString("vehicleName"));
                                    styleObj_1.put("autoSeriesCn", styleObj.getString("familyName"));
                                    returnStyleList.add(styleObj_1);
                                }
                                object2.put("styleList", returnStyleList);
                                object2.put("autoId", autoId);
                                object2.put("id", object1.get("id"));

                                resultModel = ResultModel.ok(object2);
                            }else {
                                resultModel = ResultModel.error(ResultStatus.VIN_AUTO_ERROR);
                            }
                        }
                    }
                }
            }
            responseEntity = new ResponseEntity(resultModel, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @PostMapping(value = "vinOCRGetAutoStyle")
    @ApiOperation(value = "通过行驶证图片ocr识别获取vin码 同时调用车型库匹配车型数据返回")
    public ResponseEntity<ResultModel> vinOCRGetAutoStyle(@RequestParam String url,@RequestParam Long autoId){
        ResponseEntity<ResultModel> responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.OK);
        try {
            //1、根据行驶证图片做OCR解析，获取vin码
            //识别行驶证
            DrivingCard result = DrivingCardParser.parseDrivingCardFace(url);
            //2、根据vin码匹配车型库的车型
            if (result!=null && !result.getVin().isEmpty()){
                //将车牌号、车辆类型、vin码保存到车辆信息
                JSONObject autoObj = new JSONObject();
                autoObj.put("autoId",autoId);
                //车牌
                autoObj.put("licenseNumber",result.getPlate_num());
                //车辆初登日期
                if (result.getRegister_date()!=null) {
                    if (result.getRegister_date().length()==8) {
                        autoObj.put("beginRegisterDate", UtilDate.stringToDate(result.getRegister_date(), "yyyyMMdd"));
                    }else if (result.getRegister_date().length()==6) {
                        autoObj.put("beginRegisterDate", UtilDate.stringToDate(result.getRegister_date(), "yyyyMM"));
                    }
                }
                //车辆类型
                String carShape = null,carShapeCn = result.getVehicle_type();
                carShape = carShapeCn.indexOf("微")>-1?"AUTO_CLLX_WXCH":carShape;
                carShape = carShapeCn.indexOf("小")>-1?"AUTO_CLLX_XXCH":carShape;
                carShape = carShapeCn.indexOf("中")>-1?"AUTO_CLLX_ZHXCH":carShape;
                carShape = carShapeCn.indexOf("大")>-1?"AUTO_CLLX_DXCH":carShape;
                carShape = carShapeCn.indexOf("轻型")>-1?"AUTO_CLLX_QXFBHCH":carShape;
                autoObj.put("carShape",StringUtils.isNotEmpty(carShape)?carShape:"AUTO_CLLX_XXCH");
                autoObj.put("carShapeCn",StringUtils.isNotEmpty(carShape)?carShapeCn:"小型车");

                this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(update_URL))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(autoObj),JSONObject.class);

                responseEntity = this.getDetailByVIN(autoId,result.getVin());
                if (responseEntity.getStatusCode()==HttpStatus.OK){
                    ResultModel resultModel = responseEntity.getBody();
                    //获取车型成功
                    if (resultModel!=null && resultModel.getSuccess() && resultModel.getData()!=null
                            && ResultStatus.SUCCESS.getCode() == resultModel.getResultCode()){
                        Object object = resultModel.getData();
                        JSONObject autoJson = (JSONObject) object;
                        autoJson.put("vinData",result);
                        responseEntity = new ResponseEntity<>(ResultModel.ok(autoJson),HttpStatus.OK);
                    }
                }
            }else {
                responseEntity = new ResponseEntity(ResultModel.error(ResultStatus.VIN_AUTO_ERROR),HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.OK);
        }finally {
            return responseEntity;
        }
    }

    @PostMapping(value = "bindAutoData")
    @ApiOperation(value = "根据匹配的车型id匹配车型库，并保存到车辆信息表中",notes = "根据匹配的车型id匹配车型库，并保存到车辆信息表中")
    public ResponseEntity<ResultModel> bindAutoData(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId,
                                                            @ApiParam(value = "匹配车型的id",required = true) @RequestParam String styleId,
                                                            @ApiParam(value = "当前车辆vin",required = true) @RequestParam String vin){
        logger.info("根据匹配的车型id匹配车型库，并保存到车辆信息表中");
        ResponseEntity<ResultModel> responseEntity = null;
        try {

            JSONObject object = new JSONObject();
            object.put("id", styleId);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ID_GET_VEHICLEDETAIL_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object), JSONObject.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject body = response.getBody();
                if (body != null && "000000".equals(body.getString("code"))) {
                    JSONObject autoConf = body.getJSONObject("result").getJSONObject("vehConfig");
                    JSONObject carModel = body.getJSONObject("result").getJSONObject("carModel");
                    JSONObject vcBase = autoConf.getJSONObject("vcBase");
//                    JSONObject vcGearbox = autoConf.getJSONObject("vcGearbox");
//                    JSONObject vcBody = autoConf.getJSONObject("vcBody");
                    JSONObject vcSafety = autoConf.getJSONObject("vcSafety");
                    JSONObject vcLight = autoConf.getJSONObject("vcLight");
                    JSONObject vcExternal = autoConf.getJSONObject("vcExternal");
                    JSONObject vcMedia = autoConf.getJSONObject("vcMedia");
//                    JSONObject vcHighTech = autoConf.getJSONObject("vcHighTech");
                    JSONObject vcControl = autoConf.getJSONObject("vcControl");

                    //发动机配置 (vcEngine)
                    JSONObject vcEngine = autoConf.getJSONObject("vcEngine");


///=================================重新封装我们系统 规则的车型数据===================================
                    JSONObject resultAuto = new JSONObject();
                    //封装车辆基本信息
                    if (carModel!=null){
                        resultAuto.put("engineCapacity",carModel.getString("outputVolume"));//排量
                        resultAuto.put("autoBrand",carModel.getString("brandId"));
                        resultAuto.put("autoBrandCn",carModel.getString("brandName"));
                        resultAuto.put("autoSeries",carModel.getString("seriesId"));
                        resultAuto.put("autoSeriesCn",carModel.getString("seriesName"));
                        resultAuto.put("autoStyleCn",carModel.getString("modelNameFull"));
                    }
                    resultAuto.put("autoStyle",styleId);
                    resultAuto.put("vin",vin);
                    resultAuto.put("autoId",autoId);

                    //车型==三项、两厢车====================
//                    String bodyType = vcBase.getString("bodyType");
//                    String carShape = null;
//                    String carShapeCn = null;
//                    if (bodyType!=null && bodyType.indexOf("三厢")>-1){
//                        carShape = "AUTO_CHX_SXC";
//                        carShapeCn = "三厢车";
//                    }else if (bodyType!=null && bodyType.indexOf("两厢")>-1){
//                        carShape = "AUTO_CHX_LXC";
//                        carShapeCn = "两厢车";
//                    }else if (bodyType!=null && bodyType.toUpperCase().indexOf("SUV")>-1){
//                        carShape = "AUTO_CHX_SUV";
//                        carShapeCn = "SUV";
//                    }else if (bodyType!=null && bodyType.toUpperCase().indexOf("MPV")>-1){
//                        carShape = "AUTO_CHX_MPV";
//                        carShapeCn = "MPV";
//                    }else if (bodyType!=null && bodyType.indexOf("敞篷")>-1){
//                        carShape = "AUTO_CHX_CHP";
//                        carShapeCn = "敞篷";
//                    }else if (bodyType!=null && bodyType.indexOf("跑")>-1){
//                        carShape = "AUTO_CHX_PCH";
//                        carShapeCn = "跑车";
//                    }else if (bodyType!=null && bodyType.indexOf("旅行")>-1){
//                        carShape = "AUTO_CHX_LXC";
//                        carShapeCn = "旅行车";
//                    }else if (bodyType!=null && bodyType.indexOf("皮卡")>-1){
//                        carShape = "AUTO_CHX_PKC";
//                        carShapeCn = "皮卡车";
//                    }else if (bodyType!=null && bodyType.indexOf("面包")>-1){
//                        carShape = "AUTO_CHX_MBC";
//                        carShapeCn = "面包车";
//                    }else if (bodyType!=null && bodyType.indexOf("特种")>-1){
//                        carShape = "AUTO_CHX_TZC";
//                        carShapeCn = "特种车";
//                    }else if (bodyType!=null && bodyType.indexOf("其他")>-1){
//                        carShape = "AUTO_CHX_QT";
//                        carShapeCn = "其他";
//                    }
//                    resultAuto.put("carShape",carShape);
//                    resultAuto.put("carShapeCn",carShapeCn);

                    //排放标准
                    String environment = null;
                    String environmentCn = null;
                    if (vcEngine!=null && vcEngine.getString("hbbz")!=null){
                        String effluentStandard = vcEngine.getString("hbbz");
                        if (effluentStandard.indexOf("国一")>-1){
                            environment = "AUTO_PFBZ_G1";
                            environmentCn = "国一";
                        }else if (effluentStandard.indexOf("国二")>-1){
                            environment = "AUTO_PFBZ_G2";
                            environmentCn = "国二";
                        }else if (effluentStandard.indexOf("国三")>-1){
                            environment = "AUTO_PFBZ_G3";
                            environmentCn = "国三";
                        }else if (effluentStandard.indexOf("国四")>-1){
                            environment = "AUTO_PFBZ_G4";
                            environmentCn = "国四";
                        }else if (effluentStandard.indexOf("国五")>-1){
                            environment = "AUTO_PFBZ_G5";
                            environmentCn = "国五";
                        }
                    }
                    resultAuto.put("environment",environment);//环保标准
                    resultAuto.put("environmentCn",environmentCn);//环保标准中文

                    ////////////=====================供油系统
                    if (vcBase!=null) {
                        String oilSupplySystem_ = vcBase.getString("powerType");
                        String oilSupplySystem = null;
                        String oilSupplySystemCn = null;
                        if (oilSupplySystem_ != null && oilSupplySystem_.indexOf("汽油") > -1) {
                            oilSupplySystem = "AUTO_GYXT_QY";
                            oilSupplySystemCn = "汽油";
                        } else if (oilSupplySystem_ != null && oilSupplySystem_.indexOf("柴油") > -1) {
                            oilSupplySystem = "AUTO_GYXT_CHY";
                            oilSupplySystemCn = "柴油";
                        } else if (oilSupplySystem_ != null && oilSupplySystem_.indexOf("电动") > -1) {
                            oilSupplySystem = "AUTO_GYXT_DD";
                            oilSupplySystemCn = "电动";
                        } else if (oilSupplySystem_ != null && oilSupplySystem_.indexOf("混合") > -1) {
                            oilSupplySystem = "AUTO_GYXT_HH";
                            oilSupplySystemCn = "混合";
                        } else if (oilSupplySystem_ != null && oilSupplySystem_.indexOf("其他") > -1) {
                            oilSupplySystem = "AUTO_GYXT_QT";
                            oilSupplySystemCn = "其他";
                        }
                        resultAuto.put("oilSupplySystem", oilSupplySystem);//供油系统
                        resultAuto.put("oilSupplySystemCn", oilSupplySystemCn);//供油系统中文
                    }
                    //=============================进气方式 intakeMethod ==
                    if (vcEngine!=null) {
                        String intakeMethod_ = vcEngine.getString("jqxs");
                        String intakeMethod = null;
                        String intakeMethodCn = null;
                        if (intakeMethod_ != null && intakeMethod_.indexOf("自然") > -1) {
                            intakeMethod = "AUTO_JQFS_ZRXQ";
                            intakeMethodCn = "自然吸气";
                        } else if (intakeMethod_ != null && intakeMethod_.indexOf("双涡轮增压") > -1) {
                            intakeMethod = "AUTO_JQFS_SWLZY";
                            intakeMethodCn = "双涡轮增压";
                        } else if (intakeMethod_ != null && intakeMethod_.indexOf("涡轮增压") > -1) {
                            intakeMethod = "AUTO_JQFS_WLZY";
                            intakeMethodCn = "涡轮增压";
                        } else if (intakeMethod_ != null && intakeMethod_.indexOf("机械增压") > -1) {
                            intakeMethod = "AUTO_JQFS_JXZY";
                            intakeMethodCn = "机械增压";
                        } else if (intakeMethod_ != null && intakeMethod_.indexOf("双增压") > -1) {
                            intakeMethod = "AUTO_JQFS_SZY";
                            intakeMethodCn = "双增压";
                        } else if (intakeMethod_ != null && intakeMethod_.indexOf("其他") > -1) {
                            intakeMethod = "AUTO_JQFS_QT";
                            intakeMethodCn = "其他";
                        }
                        resultAuto.put("intakeMethod", intakeMethod);
                        resultAuto.put("intakeMethodCn", intakeMethodCn);
                    }
                    //变速器=============================
                    if (vcBase!=null) {
                        String gearboxType = vcBase.getString("gearboxType");
                        String transmission = null;
                        String transmissionCn = null;
                        if (gearboxType.indexOf("AMT") > -1) {
                            transmission = "AUTO_BSX_JXS";
                            transmissionCn = "AMT-机械式变速";
                        } else if (gearboxType.indexOf("MT") > -1) {
                            transmission = "AUTO_BSX_SD";
                            transmissionCn = "MT-手动";
                        } else if (gearboxType.indexOf("AT") > -1) {
                            transmission = "AUTO_BSX_ZD";
                            transmissionCn = "AT-自动";
                        } else if (gearboxType.indexOf("CVT") > -1) {
                            transmission = "AUTO_BSX_WJ";
                            transmissionCn = "CVT-无极";
                        } else if (gearboxType.indexOf("DSG") > -1) {
                            transmission = "AUTO_BSX_SHLH";
                            transmissionCn = "DSG-双离合";
                        } else if (gearboxType.indexOf("手自") > -1) {
                            transmission = "AUTO_BSX_SZYT";
                            transmissionCn = "手自一体";
                        } else if (gearboxType.indexOf("其他") > -1) {
                            transmission = "AUTO_BSX_QT";
                            transmissionCn = "其他";
                        }
                        resultAuto.put("transmission", transmission);//变速器
                        resultAuto.put("transmissionCn", transmissionCn);//变速器
                    }
                    //=============驱动形式============================
                    if (vcBase!=null) {
                        String drivenType = vcBase.getString("drivenType");
                        String vehicleDriver = null;
                        String vehicleDriverCn = null;
                        if (drivenType.indexOf("前置后驱") > -1) {
                            vehicleDriver = "AUTO_QDXS_QZHQ";
                            vehicleDriverCn = "FR-前置后驱";
                        } else if (drivenType.indexOf("前置前驱") > -1) {
                            vehicleDriver = "AUTO_QDXS_QZQQ";
                            vehicleDriverCn = "FF-前置前驱";
                        } else if (drivenType.indexOf("中置后驱") > -1) {
                            vehicleDriver = "AUTO_QDXS_ZZHQ";
                            vehicleDriverCn = "MR-中置后驱";
                        } else if (drivenType.indexOf("后置后驱") > -1) {
                            vehicleDriver = "AUTO_QDXS_HZHQ";
                            vehicleDriverCn = "RR-后置后驱";
                        } else if (drivenType.indexOf("前置四驱") > -1) {
                            vehicleDriver = "AUTO_QDXS_QZSQ";
                            vehicleDriverCn = "FWD-前置四驱";
                        } else if (drivenType.indexOf("中置四驱") > -1) {
                            vehicleDriver = "AUTO_QDXS_ZZSQ";
                            vehicleDriverCn = "MWD-中置四驱";
                        } else if (drivenType.indexOf("后置四驱") > -1) {
                            vehicleDriver = "AUTO_QDXS_HZSQ";
                            vehicleDriverCn = "RWD-后置四驱";
                        } else if (drivenType.indexOf("其他") > -1) {
                            vehicleDriver = "AUTO_QDXS_QT";
                            vehicleDriverCn = "其他";
                        }
                        resultAuto.put("vehicleDriver", vehicleDriver);//驱动形式
                        resultAuto.put("vehicleDriverCn", vehicleDriverCn);//驱动形式
                    }
                    resultAuto.put("originalPrice",vcBase.getString("listPrice"));//新车指导价

                    //封装配置信息
                    Map map = new HashMap();
                    map.put("autoId",autoId);
                    response=this.restTemplate.exchange(
                            RequestEntity
                                    .post(URI.create(getAllConf_URL))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(map),JSONObject.class);
                    JSONArray confArray = null;

                    if (response.getStatusCode()==HttpStatus.OK && response.getBody().getJSONArray("result")!=null){
                        confArray = response.getBody().getJSONArray("result");

                        if (vcBase!=null) {
                            //安全气囊
                            if ("0".equals(vcBase.getString("airbagNum"))) {
                                ((JSONObject) confArray.get(0)).put("confOption", "2");
                                ((JSONObject) confArray.get(0)).put("confOptionCn", "无");
                            } else {
                                ((JSONObject) confArray.get(0)).put("confOption", "1");
                                ((JSONObject) confArray.get(0)).put("confOptionCn", "有");
                            }
                        }
                        if (vcControl!=null) {
                            //防抱死制动系统
                            if ("无".equals(vcControl.getString("abs"))) {
                                ((JSONObject) confArray.get(1)).put("confOption", "2");
                                ((JSONObject) confArray.get(1)).put("confOptionCn", "无");
                            } else {
                                ((JSONObject) confArray.get(1)).put("confOption", "1");
                                ((JSONObject) confArray.get(1)).put("confOptionCn", "有");
                            }
                            //刹车辅助系统
                            if ("无".equals(vcControl.getString("scfz"))) {
                                ((JSONObject) confArray.get(2)).put("confOption", "2");
                                ((JSONObject) confArray.get(2)).put("confOptionCn", "无");
                            } else {
                                ((JSONObject) confArray.get(2)).put("confOption", "1");
                                ((JSONObject) confArray.get(2)).put("confOptionCn", "有");
                            }
                            //车身稳定控制系统
                            if ("无".equals(vcControl.getString("cswdkz"))) {
                                ((JSONObject) confArray.get(3)).put("confOption", "2");
                                ((JSONObject) confArray.get(3)).put("confOptionCn", "无");
                            } else {
                                ((JSONObject) confArray.get(3)).put("confOption", "1");
                                ((JSONObject) confArray.get(3)).put("confOptionCn", "有");
                            }
                        }
                        if (vcLight!=null) {
                            //后视镜电动调节
                            if ("无".equals(vcLight.getString("hsjddtj"))) {
                                ((JSONObject) confArray.get(4)).put("confOption", "2");
                                ((JSONObject) confArray.get(4)).put("confOptionCn", "无");
                            } else {
                                ((JSONObject) confArray.get(4)).put("confOption", "1");
                                ((JSONObject) confArray.get(4)).put("confOptionCn", "有");
                            }
                        }
                        if (vcMedia!=null) {
                            //导航
                            if ("无".equals(vcMedia.getString("gps"))) {
                                ((JSONObject) confArray.get(5)).put("confOption", "2");
                                ((JSONObject) confArray.get(5)).put("confOptionCn", "无");
                            } else {
                                ((JSONObject) confArray.get(5)).put("confOption", "1");
                                ((JSONObject) confArray.get(5)).put("confOptionCn", "有");
                            }
                            //CD/DVD
                            if ("无".equals(vcMedia.getString("ddcd"))) {
                                ((JSONObject) confArray.get(6)).put("confOption", "2");
                                ((JSONObject) confArray.get(6)).put("confOptionCn", "无");
                            } else {
                                ((JSONObject) confArray.get(6)).put("confOption", "1");
                                ((JSONObject) confArray.get(6)).put("confOptionCn", "有");
                            }
                            //空调
                            if ("有".equals(vcMedia.getString("ddcd")) || "有".equals(vcMedia.getString("zdkt"))) {
                                ((JSONObject) confArray.get(7)).put("confOption", "1");
                                ((JSONObject) confArray.get(7)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(7)).put("confOption", "2");
                                ((JSONObject) confArray.get(7)).put("confOptionCn", "无");
                            }
                            //遥控钥匙
                            if ("有".equals(vcMedia.getString("ykys"))) {
                                ((JSONObject) confArray.get(9)).put("confOption", "1");
                                ((JSONObject) confArray.get(9)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(9)).put("confOption", "2");
                                ((JSONObject) confArray.get(9)).put("confOptionCn", "无");
                            }

                            //一键启动
                            if ("有".equals(vcMedia.getString("wysqdxt"))) {
                                ((JSONObject) confArray.get(10)).put("confOption", "1");
                                ((JSONObject) confArray.get(10)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(10)).put("confOption", "2");
                                ((JSONObject) confArray.get(10)).put("confOptionCn", "无");
                            }

                        }

                        if (vcSafety!=null) {
                            //中控锁
                            if ("有".equals(vcSafety.getString("cnzks"))) {
                                ((JSONObject) confArray.get(8)).put("confOption", "1");
                                ((JSONObject) confArray.get(8)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(8)).put("confOption", "2");
                                ((JSONObject) confArray.get(8)).put("confOptionCn", "无");
                            }
                        }
                        if (vcExternal!=null) {
                            //电动车窗
                            if ("有".equals(vcExternal.getString("hddcc")) || "有".equals(vcExternal.getString("qddcc"))) {
                                ((JSONObject) confArray.get(11)).put("confOption", "1");
                                ((JSONObject) confArray.get(11)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(11)).put("confOption", "2");
                                ((JSONObject) confArray.get(11)).put("confOptionCn", "无");
                            }
                            //倒车雷达
                            if ("有".equals(vcExternal.getString("hdcld"))) {
                                ((JSONObject) confArray.get(12)).put("confOption", "1");
                                ((JSONObject) confArray.get(12)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(12)).put("confOption", "2");
                                ((JSONObject) confArray.get(12)).put("confOptionCn", "无");
                            }
                            //倒车影像
                            if ("有".equals(vcExternal.getString("dcspyx"))) {
                                ((JSONObject) confArray.get(13)).put("confOption", "1");
                                ((JSONObject) confArray.get(13)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(13)).put("confOption", "2");
                                ((JSONObject) confArray.get(13)).put("confOptionCn", "无");
                            }
                            //定速巡航
                            if ("有".equals(vcExternal.getString("dsxh"))) {
                                ((JSONObject) confArray.get(15)).put("confOption", "1");
                                ((JSONObject) confArray.get(15)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(15)).put("confOption", "2");
                                ((JSONObject) confArray.get(15)).put("confOptionCn", "无");
                            }
                            //电动天窗
                            if ("有".equals(vcExternal.getString("ddtc"))) {
                                ((JSONObject) confArray.get(16)).put("confOption", "1");
                                ((JSONObject) confArray.get(16)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(16)).put("confOption", "2");
                                ((JSONObject) confArray.get(16)).put("confOptionCn", "无");
                            }
                            //真皮坐椅
                            if ("有".equals(vcExternal.getString("zpzy"))) {
                                ((JSONObject) confArray.get(17)).put("confOption", "1");
                                ((JSONObject) confArray.get(17)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(17)).put("confOption", "2");
                                ((JSONObject) confArray.get(17)).put("confOptionCn", "无");
                            }
                            //前座椅电动调节
                            if ("有".equals(vcExternal.getString("fjswddtj")) || "有".equals(vcExternal.getString("jswddtj"))) {
                                ((JSONObject) confArray.get(18)).put("confOption", "1");
                                ((JSONObject) confArray.get(18)).put("confOptionCn", "有");
                            } else {
                                ((JSONObject) confArray.get(18)).put("confOption", "2");
                                ((JSONObject) confArray.get(18)).put("confOptionCn", "无");
                            }
                        }
                        //去掉备用轮胎
                        confArray.remove(14);
                    }

                    //调用保存车辆基本信息+配置信息接口，完成保存，返回客户端，客户端只需要继续获取信息即可
                    resultAuto.put("confArray",confArray);
                    response=this.restTemplate.exchange(
                            RequestEntity
                                    .post(URI.create(saveDetailAndConf_URL))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(resultAuto),JSONObject.class);

                    responseEntity = ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }
}