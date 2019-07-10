package com.wintop.ms.carauction.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.wintop.ms.carauction.core.config.AlipayConfig;
import com.wintop.ms.carauction.entity.CarFinancePayLog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AlipayUtil {

    private static String CHARSET = "UTF-8";

    public static Map refundOrder(CarFinancePayLog payLog) {
        Map map = new HashMap<>();
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.ALI_PAY_SERVER_URL,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                "json",
                CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                "RSA2");
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeRefundModel refundModel = new AlipayTradeRefundModel();
        refundModel.setTradeNo(payLog.getBankOrderNo());
        refundModel.setRefundAmount(payLog.getPayFee() + "");
        refundModel.setRefundReason("查博士退款");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(refundModel);
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            System.out.println("-----------" + response.getMsg() + "\n");
            System.out.println("----body-------" + response.getBody());

            if ("10000".equals(response.getCode())) {
                map.put("code", "0");
                map.put("msg", "退款成功");
            } else {
                map.put("code", "1");
                map.put("msg", response.getSubMsg());
            }
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] arg) {
        CarFinancePayLog payLog = new CarFinancePayLog();
        payLog.setPayFee(new BigDecimal("0.01"));
        payLog.setBankOrderNo("2018061421001004550573031341");
        Map map = refundOrder(payLog);
        System.out.println(map);
    }
}
