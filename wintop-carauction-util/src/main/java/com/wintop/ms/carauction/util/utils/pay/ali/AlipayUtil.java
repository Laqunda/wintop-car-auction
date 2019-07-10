package com.wintop.ms.carauction.util.utils.pay.ali;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.wintop.ms.carauction.core.config.AlipayConfig;
import com.wintop.ms.carauction.core.model.AlipayRequestModel;
import com.wintop.ms.carauction.core.model.AlipayResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Api(value = "支付宝支付使用工具类",description = "用于吊起支付宝支付及处理回调通知使用")
public class AlipayUtil {

    static Logger logger = LoggerFactory.getLogger(AlipayUtil.class);

    @ApiOperation(value = "生成支付订单",notes = "返回的内容就是orderString 可以直接给客户端请求，无需再做处理。")
    public static String createPayOrder(@ApiParam(value = "支付宝应用配置") AlipayConfig alipayConfig,
                                 @ApiParam(value = "生成支付订单所需业务参数") AlipayRequestModel alipayRequestModel){
        logger.info("调用创建支付宝--支付订单");
        String result = null;
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.ALI_PAY_SERVER_URL,
                alipayConfig.APP_ID,
                alipayConfig.APP_PRIVATE_KEY,
                AlipayConstants.FORMAT_JSON,
                AlipayConstants.CHARSET_UTF8,
                alipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConstants.SIGN_TYPE_RSA2);
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(alipayRequestModel.getBody());
        model.setSubject(alipayRequestModel.getSubject());
        model.setOutTradeNo(alipayRequestModel.getOutTradeNo());
        model.setTimeoutExpress(alipayRequestModel.getTimeoutExpress());
        model.setTotalAmount(alipayRequestModel.getTotalAmount());
        model.setProductCode(alipayRequestModel.getProductCode());
        request.setBizModel(model);
        request.setNotifyUrl(alipayRequestModel.getPayNotifyUrl());
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            result = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            result = null;
        }finally {
            return result;
        }
    }

    @ApiOperation(value = "处理支付回调结果",notes = "验证支付通知，并提取封装返回值")
    public static AlipayResponseModel aliPayNotify(@ApiParam(value = "支付宝应用配置") AlipayConfig alipayConfig,
                                            @ApiParam(value = "支付结果通知接口返回值") Map requestParams){
        logger.info("处理支付回调结果");
        AlipayResponseModel responseModel = null;
        try {
            System.out.println("支付宝支付结果通知"+requestParams.toString());
            //获取支付宝POST过来反馈信息
            Map<String,String> params = new HashMap<String,String>();

            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            try {
                //验证签名
                boolean flag = AlipaySignature.rsaCheckV1(params, alipayConfig.ALIPAY_PUBLIC_KEY, AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
                if(flag){
                    if("TRADE_SUCCESS".equals(params.get("trade_status"))){
                        //付款金额
                        String amount = params.get("buyer_pay_amount");
                        //商户订单号
                        String out_trade_no = params.get("out_trade_no");
                        //支付宝交易号
                        String trade_no = params.get("trade_no");
                        //附加数据
                        String passback_params = params.get("passback_params");
                        responseModel = new AlipayResponseModel();
                        responseModel.setAmount(amount);
                        responseModel.setOutTradeNo(out_trade_no);
                        responseModel.setTradeNo(trade_no);
                        responseModel.setPassbackParams(passback_params);
                        responseModel.setLog(JSONObject.toJSONString(requestParams));
                    }
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
                responseModel = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            responseModel = null;
        }finally {
            return responseModel;
        }
    }
}
