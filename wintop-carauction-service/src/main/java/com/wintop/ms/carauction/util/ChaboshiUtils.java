package com.wintop.ms.carauction.util;

import com.alibaba.fastjson.JSONObject;
import com.chaboshi.builder.CBSBuilder;
import com.chaboshi.util.CBS;
import com.wintop.ms.carauction.core.config.ChaBoShiConfig;
import com.wintop.ms.carauction.core.config.Constants;

import java.util.HashMap;
import java.util.Map;

public class ChaboshiUtils {
    static CBSBuilder cbs = CBSBuilder.newCBSBuilder(ChaBoShiConfig.userId, ChaBoShiConfig.keySecret, true);

    /**
     * 维保购买报告接口
     *
     * @param vin
     */
    public static JSONObject report(String vin) {
        HashMap param = new HashMap();
        param.put("vin", vin);
        param.put("callbackurl", Constants.CALLBACK_CHABOSHI);
        String str = cbs.sendPost("/report/buy_report", param);
        return JSONObject.parseObject(str);
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
        HashMap param = new HashMap();
        param.put("vin", vin);
        param.put("callbackurl", Constants.CALLBACK_CHABOSHI);
        String str = cbs.sendPost("/new_report/buy", param);
        return JSONObject.parseObject(str);
    }


    //****************************************************************************************************************************

//    {"Code":"0","Message":"成功","orderId":"d05c2d6b480940d6802665c0fc468312"}
//综合版{"Code":"0","Message":"request success","data":{"orderId":"e888ca0b91fe4640a02879308ac0f595"}}
    public static void main(String[] args) {

//        System.out.println(repairReport("LGG7B2D14EZ103435"));
//        System.out.println(report("LSGWS52X67S050013"));
//        System.out.println(reportJson("d05c2d6b480940d6802665c0fc468312"));
        System.out.println(orderStatus("e888ca0b91fe4640a02879308ac0f595"));
//        System.out.println(reportDetail("d05c2d6b480940d6802665c0fc468312"));

    }
}

