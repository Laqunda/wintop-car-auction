package com.wintop.ms.carauction.core.config;

/**
 * 定义接口请求相应码
 * Created by liangtingsen on 2018/2/9.
 */
public class HttpResponseStatus {
    public static final int
            OK = 200,               //正常
            ERROR = 500,            //网络异常
            APPID_NULL     = 201,   //缺少appid
            APPID_DISABLED = 202,   //无效的appid
            TIMESTAMP_ERROR= 203,   //错误的时间戳
            TOKEN_NULL     = 204,   //缺少token
            TOKEN_DISABLED = 205,   //无效的token
            TOKEN_TIME_OUT = 206,   //超时的token
            SIGN_ERROR     = 207,   //签名错误
            REQUEST_DISABLED = 208, //无效的请求
            API_NOT_REALIZE = 209;  //接口未实现

}