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
     * 维保购买报告接口
     *
     * @param vin
     */
    public static JSONObject report(String vin) {
        String engino = "";//发动机号
        String licenseplate = "";//车牌号


        String buyReport = CBS.getInstance(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret, false).getBuyReport(vin, engino, licenseplate, Constants.CALLBACK_CHABOSHI);

        JSONObject o = JSONObject.parseObject(buyReport);

        //订单成功
        if ("0".equals(o.getString("code"))) {

            String message = o.getString("Message");
            String orderId = o.getString("orderId");
            System.out.println("message:" + message + "\norderId:" + orderId);
        } else {
            //订单失败
        }
        return o;

    }


    /**
     * 获取报告json
     *
     * @param orderId
     */
    public static JSONObject reportJson(String orderId) {
        String buyReport = CBS.getInstance(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret).getNewReportJson(orderId);
        return JSONObject.parseObject(buyReport);

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
     * 碰撞综合版
     *
     * @param vin
     * @return]
     */
    public static JSONObject repairReport(String vin) {
        CBSBuilder cbs = CBSBuilder.newCBSBuilder(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret, true);
        HashMap param = new HashMap();
        param.put("vin", vin);
        param.put("callbackurl", Constants.CALLBACK_CHABOSHI);
        String str = cbs.sendPost("/new_report/buy", param);
        return JSONObject.parseObject(str);
    }


    //****************************************************************************************************************************


    public static void main(String[] args) {

        JSONObject report = repairReport("LSGWS52X67S050013");


        /*String userId = "824791";
        String keySecret = "9021288d82f7e0175aad2db9c05a0026";
        CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(userId, keySecret, false);
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("vin", "LSGWS52X67S050013");
        String data = cbsBuilder.sendPost("/new_report/buy", params);*/

        System.out.println(report);
        System.out.println(report("LSGWS52X67S050013"));

//        System.out.println(report.toJSONString());
    }
}

