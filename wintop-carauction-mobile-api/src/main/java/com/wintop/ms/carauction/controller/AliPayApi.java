package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.AlipayConfig;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.model.AlipayRequestModel;
import com.wintop.ms.carauction.core.model.AlipayResponseModel;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import com.wintop.ms.carauction.util.utils.RedisManager;
import com.wintop.ms.carauction.util.utils.pay.ali.AlipayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api("支付宝支付使用api")
@RestController
@RequestMapping("aliPay")
public class AliPayApi {

    Logger logger = LoggerFactory.getLogger(AliPayApi.class);
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private RedisAppUserManager appUserManager;
    private ResultModel resultModel;
    private final RestTemplate restTemplate;

    AliPayApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据用户等级获取该用户所需缴纳的保证金金额
     */
    private String getDepositAmountByCustomerLevelId_URL = Constants.ROOT + "/service/carCustomerDeposit/getDepositAmountByCustomerLevelId";

    /**
     * 支付成功通知保证金接口
     */
    private String payDepositAmountCallback_URL = Constants.ROOT + "/service/carCustomerDeposit/payDepositAmountCallback";


    @ApiOperation(value = "支付保证金")
    @PostMapping("payDeposit")
    @AuthUserToken
    public ResponseEntity<ResultModel> payDeposit(@CurrentUser AppUser user) {
        logger.info("创建支付宝订单--支付保证金");
        try {
            Map map = new HashMap();
            map.put("customerLevelId", user.getUserRankId());
            //1、根据用户获取此用户需要支付的保证金金额
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getDepositAmountByCustomerLevelId_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map), JSONObject.class);
            BigDecimal depositMoney = response.getBody().getJSONObject("result").getBigDecimal("depositMoney");
            //2、封装支付所需对象
            AlipayRequestModel alipayRequestModel = new AlipayRequestModel();
            alipayRequestModel.setBody("柠檬竞拍保证金缴纳");//描述
            alipayRequestModel.setPassbackParams(user.getId() + "");//附加字段
            alipayRequestModel.setOutTradeNo(RandCodeUtil.getOrderNumber());//单号
            alipayRequestModel.setPayNotifyUrl(Constants.ALIPAY_NOTIFY_URL);//回调通知地址
            alipayRequestModel.setSubject("保证金缴纳");//标题
            alipayRequestModel.setTimeoutExpress("30m");//支付有效期30分钟
            alipayRequestModel.setTotalAmount(depositMoney + "");//付款金额
//            alipayRequestModel.setTotalAmount("0.01");//付款金额
            //3、将保证金支付对象放入redis，用于通知回调时 取出 做对应业务处理
            redisManager.setKeyValue(alipayRequestModel.getOutTradeNo(), JSONObject.toJSONString(alipayRequestModel), Constants.PAY_EXPIRES_HOUR, TimeUnit.HOURS);
            //4、初始化封装好的支付宝sdk---调用创建订单方法
            AlipayConfig alipayConfig = new AlipayConfig();
            String orderString = AlipayUtil.createPayOrder(alipayConfig, alipayRequestModel);
            if (orderString != null && orderString != "") {
                Map payMap = new HashMap();
                payMap.put("orderString", orderString);
                resultModel = ResultModel.ok(payMap);
            } else {
                resultModel = ResultModel.error(ResultStatus.UNSUCCESS);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultModel = ResultModel.error(ResultStatus.UNSUCCESS);
        } finally {
            return new ResponseEntity<>(resultModel, HttpStatus.OK);
        }
    }

    @AuthPublic
    @ApiOperation(value = "保证金支付通知")
    @PostMapping("payNotifyDeposit")
    public String payNotifyDeposit(HttpServletRequest request) {
        Map requestMap = request.getParameterMap();
        logger.info("保证金支付通知");
        logger.info("通知内容：" + JSONObject.toJSONString(requestMap));
        String result = "error";
        try {
            //1、通过支付宝工具类解析支付通知结果
            AlipayConfig alipayConfig = new AlipayConfig();
            AlipayResponseModel alipayResponseModel = AlipayUtil.aliPayNotify(alipayConfig, requestMap);
            //2、根据结果处理保证金业务
            if (alipayResponseModel != null) {
                String alipayRequestModelJsonStr = redisManager.getKeyValue(alipayResponseModel.getOutTradeNo());
                JSONObject requestModel = JSONObject.parseObject(alipayRequestModelJsonStr);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("depositAmount", alipayResponseModel.getAmount());//支付金额
                jsonObject.put("payLogNo", alipayResponseModel.getOutTradeNo());//平台支付流水号
                jsonObject.put("bankOrderNo", alipayResponseModel.getTradeNo());//银行交易流水号
                jsonObject.put("payType", "1");//支付方式1线上，2线下
                jsonObject.put("payWay", "3");//支付渠道：3支付宝
                jsonObject.put("bankOrderLog", alipayResponseModel.getLog());//支付银行订单日至
                jsonObject.put("userId", requestModel.getString("passbackParams"));//支付人-通过附加字段回传
                jsonObject.put("passbackParams", requestModel.getString("passbackParams"));//附加字段
                jsonObject.put("userType", "1");
                ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(payDepositAmountCallback_URL))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(jsonObject), JSONObject.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject object = response.getBody();
                    if ("0".equals(object.getString("code"))) {
                        //支付成功业务处理完成，将redis中的待支付对象清理掉
                        redisManager.delKeyValue(alipayResponseModel.getOutTradeNo());
                        result = "success";
                        logger.info("支付成功通知处理完成，返回支付宝success");
                        //更新redis中用户的状态
                        appUserManager.updateUserStatus(requestModel.getString("passbackParams"), AppUserStatusEnum.SIG_ING.value());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
            logger.error("支付成功通知处理失败！");
        } finally {
            return result;
        }


    }

    //***************查博士支付*******************
    @ApiOperation(value = "查博士支付")
    @PostMapping("payChaBoShi")
    @AuthUserToken
    public ResponseEntity<ResultModel> payChaBoShi(@CurrentUser AppUser user, @RequestBody Map<String, Object> map) {
        logger.info("创建支付宝订单--查博士支付");
        try {
            //1、查找需要支付的查询费用
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT + "/service/carChaboshiPaymentConf/detail"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map), JSONObject.class);
            BigDecimal count = null;

            //            查询版本 1维修版 2综合版
            if ("1".equals(map.get("edition"))) {
                response.getBody().getJSONObject("result").getBigDecimal("payment");
            } else if ("2".equals(map.get("edition"))) {
                response.getBody().getJSONObject("result").getBigDecimal("paymentComposite");
            } else {
                resultModel = ResultModel.error(ResultStatus.PARAMETERS_ERROR);
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }

            //TODO count 金额校验
            //2、封装支付所需对象
            AlipayRequestModel alipayRequestModel = new AlipayRequestModel();
            alipayRequestModel.setBody("查博士查询消费");//描述
            alipayRequestModel.setPassbackParams(user.getId() + "");//附加字段
            alipayRequestModel.setOutTradeNo(RandCodeUtil.getOrderNumber());//单号
            alipayRequestModel.setPayNotifyUrl(Constants.ALIPAY_NOTIFY_URL);//回调通知地址
            alipayRequestModel.setSubject("查博士查询");//标题
            alipayRequestModel.setTimeoutExpress("30m");//支付有效期30分钟
            alipayRequestModel.setTotalAmount(count + "");//付款金额
//            alipayRequestModel.setTotalAmount("0.01");//付款金额
            //3、将支付对象放入redis，用于通知回调时 取出 做对应业务处理
            JSONObject jo = JSONObject.parseObject(JSONObject.toJSONString(alipayRequestModel));
            jo.put("edition", map.get("edition"));
            jo.put("vin", map.get("vin"));
            jo.put("userName", user.getUserName());
            redisManager.setKeyValue(alipayRequestModel.getOutTradeNo(), jo.toJSONString(), Constants.PAY_EXPIRES_HOUR, TimeUnit.HOURS);
            //4、初始化封装好的支付宝sdk---调用创建订单方法
            AlipayConfig alipayConfig = new AlipayConfig();
            String orderString = AlipayUtil.createPayOrder(alipayConfig, alipayRequestModel);
            if (orderString != null && orderString != "") {
                Map payMap = new HashMap();
                payMap.put("orderString", orderString);
                resultModel = ResultModel.ok(payMap);
            } else {
                resultModel = ResultModel.error(ResultStatus.UNSUCCESS);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultModel = ResultModel.error(ResultStatus.UNSUCCESS);
        } finally {
            return new ResponseEntity<>(resultModel, HttpStatus.OK);
        }
    }

    @AuthPublic
    @ApiOperation(value = "查博士支付通知")
    @PostMapping("payChaboshiAmountCallback")
    public String payNotifyChaBoShi(HttpServletRequest request) {
        Map requestMap = request.getParameterMap();
        logger.info("查博士支付通知");
        logger.info("通知内容：" + JSONObject.toJSONString(requestMap));
        String result = "error";
        try {
            //1、通过支付宝工具类解析支付通知结果
            AlipayConfig alipayConfig = new AlipayConfig();
            AlipayResponseModel alipayResponseModel = AlipayUtil.aliPayNotify(alipayConfig, requestMap);
            //2、根据结果处理查博士业务
            if (alipayResponseModel != null) {
                String alipayRequestModelJsonStr = redisManager.getKeyValue(alipayResponseModel.getOutTradeNo());
                JSONObject requestModel = JSONObject.parseObject(alipayRequestModelJsonStr);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("money", alipayResponseModel.getAmount());//支付金额
                jsonObject.put("type", "4");//查博士
                jsonObject.put("payLogNo", alipayResponseModel.getOutTradeNo());//平台支付流水号
                jsonObject.put("bankOrderNo", alipayResponseModel.getTradeNo());//银行交易流水号
                jsonObject.put("payType", "1");//支付方式1线上，2线下
                jsonObject.put("payWay", "3");//支付渠道：3支付宝
                jsonObject.put("bankOrderLog", alipayResponseModel.getLog());//支付银行订单日至

                jsonObject.put("userId", requestModel.getString("passbackParams"));//支付人-通过附加字段回传
                jsonObject.put("passbackParams", requestModel.getString("passbackParams"));//附加字段

                jsonObject.put("edition", requestModel.getString("edition"));
                jsonObject.put("vin", requestModel.getString("vin"));
                jsonObject.put("userName", requestModel.getString("userName"));
                jsonObject.put("edition", requestModel.getString("edition"));
                jsonObject.put("userType", "1");//个人

                ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ROOT + "/service/carChaboshiLog/payChaboshiAmountCallback"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(jsonObject), JSONObject.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject object = response.getBody();
                    if ("0".equals(object.getString("code"))) {
                        //支付成功业务处理完成，将redis中的待支付对象清理掉
                        redisManager.delKeyValue(alipayResponseModel.getOutTradeNo());
                        result = "success";
                        logger.info("支付成功通知处理完成，返回支付宝success");
                        //更新redis中用户的状态
                        appUserManager.updateUserStatus(requestModel.getString("passbackParams"), AppUserStatusEnum.SIG_ING.value());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
            logger.error("支付成功通知处理失败！");
        } finally {
            return result;
        }
    }
}
