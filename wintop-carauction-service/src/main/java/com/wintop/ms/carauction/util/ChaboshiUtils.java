package com.wintop.ms.carauction.util;

import com.alibaba.fastjson.JSONObject;
import com.chaboshi.builder.CBSBuilder;
import com.chaboshi.util.CBS;
import com.wintop.ms.carauction.core.config.ChaBoShiConfig;
import com.wintop.ms.carauction.core.config.Constants;

import java.util.HashMap;
import java.util.Map;

public class ChaboshiUtils {

    /**
     * 购买报告接口
     *
     * @param vin
     */
    public static JSONObject report(String vin) {
        String engino = "";//发动机号
        String licenseplate = "";//车牌号

        String buyReport = CBS.getInstance(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret).getBuyReport(vin, engino, licenseplate, Constants.CALLBACK_CHABOSHI);

        JSONObject o = JSONObject.parseObject(buyReport);

        //订单成功
        if ("0".equals(o.getString("code"))) {

            String message = o.getString("Message");
            String orderId = o.getString("orderId");
        } else {
            //订单失败
        }
        return o;

    }

    /**
     * 查看订单状态接口
     *
     * @param orderId
     * @return
     */
    public static JSONObject orderStatus(String orderId) {
        String str = CBS.getInstance(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret).getReportStatus(orderId);
        JSONObject result = JSONObject.parseObject(str);
        String code = result.getString("code");
        String msg = result.getString("Message");

        return result;

    }

    /**
     * 查询品牌是否支持查询
     *
     * @param vin
     */
    public static JSONObject isSupport(String vin) {
        String str = CBS.getInstance(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret).getCheckBrand(vin);
        JSONObject result = JSONObject.parseObject(str);
        String code = result.getString("code");
        String msg = result.getString("Message");

        return result;
    }

    /**
     * 根据订单查询报告详情
     *
     * @param orderId
     * @return
     */
    public static Map reportDetail(String orderId) {
        Map result = CBS.getInstance(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret).getNewReportUrl(orderId);
        String pcUrl = (String) result.get("pcUrl");
        String mobileUrl = (String) result.get("mobileUrl");
        return result;
    }


    /**
     * 维保接口
     *
     * @param vin
     * @return]
     */
    public static JSONObject repairReport(String vin) {
        CBSBuilder cbs = CBSBuilder.newCBSBuilder(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret, false);
        HashMap param = new HashMap();
        param.put("vin", vin);
        param.put("callbackurl", Constants.CALLBACK_CHABOSHI);
        String str = cbs.sendPost("/new_report/buy", param);
        return JSONObject.parseObject(str);
    }
}

