package com.wintop.ms.carauction.core.config;

import java.util.*;

/**
 * 业务使用常量
 */
public class Constants {

    /***
     * 存放当前登陆人的ID 的KEY
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * 用户redis 存放token 键名
     */
    public static final String TOKEN_KEY = "TOKEN_KEY";

    /**
     * 用户redis 存放用户登陆后用户信息 键名
     */
    public static final String APP_USER_REDIS_KEY = "APP_USER_REDIS_KEY";

    /**
     * redis中存放的登陆手机号验证码前缀
     * 实际使用需要将手机号拼接到后面组成key
     */
    public static final String LOGIN_VALIDATE_CODE_KEY = "LOGIN_VALIDATE_CODE_";

    /**
     * redis中存放的修改密码用手机号验证码前缀
     * 实际使用需要将手机号拼接到后面组成key
     */
    public static final String UPD_PWD_VALIDATE_CODE_KEY = "UPD_PWD_VALIDATE_CODE_";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 支付有效时间（小时）
     */
    public static final int PAY_EXPIRES_HOUR = 12;

    /**
     * 存放Authorization的header字段
     * userId_token
     */
    public static final String HEADER_AUTHORIZATION = "authorization";

    /***
     * 请求头里的时间戳
     */
    public static final String HEADER_TIMESTAMP = "timestamp";

    /**
     * 存放appId的header字段
     */
    public static final String HEADER_APPID = "appid";
    /**
     * 存放version的header字段
     */
    public static final String APP_VERSION = "version";

    public static final String ROOT = "http://127.0.0.1:8185" ;

    /**在线签约微服务地址*/
    public static final String COMMON_MODULE_ROOT = "http://test.yuntongauto.com" ;

    /**pdf模板方式合同生成甲方签字合同**/
    public static final String CREATESIGNATURE_TEMP_URL =  Constants.COMMON_MODULE_ROOT + "/signature/gosign/pdf/fill";

    /**在线签约--个人签约--签署关键字*/
    public static final String SIGNATURE_CUS_KEY = "甲方签字";

    /**在线签约--个人签约--签署关键字*/
    public static final String SIGNATURE_BSE_KEY = "乙方：（盖章）";

    /**根据身份证号获取e签宝的已有账号编号
     * */
    public static final String SIGNATURE_GETUSERID_URL = Constants.COMMON_MODULE_ROOT + "/signature/account/user/getid";

    /**给合同补签公司章-url*/
    public static final String PLATFORM_SIGNATURE_URL =  Constants.COMMON_MODULE_ROOT + "/signature/gosign/multi/platform";

    /**
     * 签约服务--两要素接口
     * */
    public static final String PERSON_CARNO_VERIFICATION = Constants.COMMON_MODULE_ROOT + "/signature/account/user/vrification";

    /***
     * 签约使用模板pdf
     */
    public static final String SIGNATURE_TEMP_BASEPDF_URL = "http://static.yuntongauto.com/web/liemon/lemon_sign_temp.pdf";
    /***
     * 待签约的pdf
     */
    public static final String SIGNATURE_BASEPDF_URL = "http://static.yuntongauto.com/web/liemon/contract.pdf";
    /***
     * 待签约的img
     */
    public static final String SIGNATURE_BASEPDF_URL_IMG = "http://static.yuntongauto.com/web/liemon/contract.Jpeg";
    /**
     * 短信发送服务器地址
     */
    public static final String SENDSMS_URL = "http://192.168.22.67:2004/message/sendMessage";

    /***
     * 车辆配置，两个选项
     */
    public static final List<Map<String,String>> AUTO_CONF_OPTION_TWO = new ArrayList<>(
            Arrays.asList(
                    new HashMap<String,String>(){{put("confOption","1");put("confOptionCn","有");}},
                    new HashMap<String,String>(){{put("confOption","2");put("confOptionCn","无");}}
            ));

    /***
     * 车辆配置，三个选项
     */
    public static final List<Map<String,String>> AUTO_CONF_OPTION_THREE = new ArrayList<>(
            Arrays.asList(
                    new HashMap<String,String>(){{put("confOption","1");put("confOptionCn","有");}},
                    new HashMap<String,String>(){{put("confOption","2");put("confOptionCn","无");}},
                    new HashMap<String,String>(){{put("confOption","3");put("confOptionCn","加装");}}
            ));

    /**获取品牌信息
     * */
    public static final String GET_BRANDS_URL = COMMON_MODULE_ROOT+"/carmodel/brands";

    /**获取品牌下车系
     * */
    public static final String GET_SERIES_URL = COMMON_MODULE_ROOT+"/carmodel/series";

    /**获取车系下车型
     * */
    public static final String GET_VEHICLE_URL = COMMON_MODULE_ROOT+"/carmodel/vehicle";

    /***
     * 根据vin码匹配车型列表
     */
//    public static final String VIN_GET_VEHICLELIST_URL = COMMON_MODULE_ROOT+"/carmodel/vehiclelist";
    public static final String VIN_GET_VEHICLELIST_URL = "http://192.168.22.67:2001/carmodel/vehiclelist";

    /***
     * 根据具体车型获取车辆详细配置信息
     */
//    public static final String ID_GET_VEHICLEDETAIL_URL = COMMON_MODULE_ROOT+"/carmodel/vehicledetail";
    public static final String ID_GET_VEHICLEDETAIL_URL = "http://192.168.22.67:2001/carmodel/vehicledetail";


    /**
     * 线上拍卖的时间设置
     */
    public static final Integer AUCTION_TIME=120000;

    /***
     * 保证金支付宝支付通知接口
     */
    public static final String ALIPAY_NOTIFY_URL = "http://111.202.186.103:8080/mobile/aliPay/payNotifyDeposit";
//    public static final String ALIPAY_NOTIFY_URL = "http://2sc.wintop2sc.com/mobile/aliPay/payNotifyDeposit";

    /**图片上传*/
//    public static final String UPLOADFILE_URL = COMMON_MODULE_ROOT+"/file/uploadFile";
    public static final String UPLOADFILE_URL = "http://192.168.22.67:2003/file/uploadFile";
    /**图片上传*/
//    public static final String UPLOADIMAGEFORQUALITY_URL = COMMON_MODULE_ROOT+"/file/uploadImageForQuality";
    public static final String UPLOADIMAGEFORQUALITY_URL = "http://192.168.22.67:2003/file/uploadImageForQuality";


    /***
     * 买家端appid--
     */
    public static final String CUS_APP_ID = "1234567";

    /***
     * 卖家端appid---
     */
    public static final String STORE_APP_ID = "1234567_store";
    /***
     * 静态资源服务器地址
     */
//    public static final String STATIC_WEBSITE = "http://2sc.wintop2sc.com";
    public static final String STATIC_WEBSITE = "http://test-admin.yuntongauto.com";

    /***
     * 柠檬竞价logo，用于通用图片使用
     */
    public static final String STATIC_LEMON_LOGO = "http://static.yuntongauto.com/web/img/lemon.png";

    public static final String CAR_AUTO_AUCTION = "CAR_AUTO_AUCTION";

    public static final String AUCTION_SETTING = "AUCTION_SETTING";
}
