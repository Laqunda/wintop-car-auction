package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.AlipayConfig;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
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

    //***************店铺查博士充值*******************
    @ApiOperation(value = "店铺查博士充值")
    @PostMapping("payCharge")
    @AuthUserToken
    public ResponseEntity<ResultModel> payChaBoShi(@CurrentUser CarManagerUser user, @RequestBody Map<String, Object> map) {
        logger.info("创建支付宝订单--店铺查博士充值");
        try {

            try {
                if (map.get("count") == null || Double.parseDouble(map.get("count") + "") <= 0) {
                    resultModel = ResultModel.error(ResultStatus.UNSUCCESS);
                    return new ResponseEntity<>(resultModel, HttpStatus.OK);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                resultModel = ResultModel.error(ResultStatus.UNSUCCESS);
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }

            /*1、封装支付所需对象*/
            AlipayRequestModel alipayRequestModel = new AlipayRequestModel();
            alipayRequestModel.setBody("充值");//描述
            alipayRequestModel.setPassbackParams(user.getId() + "");//附加字段
            alipayRequestModel.setOutTradeNo(RandCodeUtil.getOrderNumber());//单号
            alipayRequestModel.setPayNotifyUrl(Constants.ALIPAY_NOTIFY_URL_CHABOSHI_STORE);//回调通知地址
            alipayRequestModel.setSubject("充值");//标题
            alipayRequestModel.setTimeoutExpress("30m");//支付有效期30分钟
            alipayRequestModel.setTotalAmount("" + map.get("count"));//付款金额
//            alipayRequestModel.setTotalAmount("0.01");//付款金额
            /*2、将支付对象放入redis，用于通知回调时 取出 做对应业务处理*/
            JSONObject jo = JSONObject.parseObject(JSONObject.toJSONString(alipayRequestModel));
            jo.put("userName", user.getUserName());
            jo.put("storeId", user.getDepartmentId());
            redisManager.setKeyValue(alipayRequestModel.getOutTradeNo(), jo.toJSONString(), Constants.PAY_EXPIRES_HOUR, TimeUnit.HOURS);
            /*3、初始化封装好的支付宝sdk---调用创建订单方法*/
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
    @ApiOperation(value = "充值回调")
    @PostMapping("payCbsChargeCallback")
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
                jsonObject.put("userId", requestModel.getString("passbackParams"));//支付人-通过附加字段回传
                jsonObject.put("userName", requestModel.getString("userName"));
                jsonObject.put("storeId", requestModel.getString("storeId"));
                jsonObject.put("userType", "2");//卖家

                ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ROOT + "/service/carChaboshiLog/payCbsChargeCallback"))
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
