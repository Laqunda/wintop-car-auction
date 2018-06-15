package com.wintop.ms.carauction.core.config;

/**
 * 自定义业务处理结果
 * 业务返回结果码+描述
 * @author ScienJus
 * @date 2015/7/15.
 */
public enum ResultStatus {
    ERROR(0,"网络异常"),
    UNSUCCESS(99,"失败请重试"),
    SUCCESS(100, "成功"),
    PARAMETERS_ERROR(101,"缺少参数或参数错误"),
    SMS_FREQUENTLY(102,"短信发送频繁"),
    VALIDATE_CODE_ERROR(103,"验证码错误"),
    PHONE_EXISTS(104,"手机号码已存在"),
    PASSWORD_ERROR(105, "密码错误"),
    USERNAME_EXISTS(106,"账号已存在"),
    UNBOUND_PASSWORD(107,"账号未绑定密码"),
    UNPAID_BOND(108,"用户未缴纳保证金"),
    UNSIGNED(109,"未签约"),
    USER_UNAUTHENTICATED(110,"未实名认证"),
    USERNAME_UNKNOWN(111,"用户名不存在"),
    USER_NOT_LOGIN(112,"用户未登录"),
    USERNAME_OR_PASSWORD_ERROR(113, "用户名或密码错误"),
    OBJECT_NOT_EXIST(114, "该对象不存在"),
    SERVICE_ERROR(300,"业务处理异常"),
    SIGNED_UNAUTH(116,"签约待审核"),
    SIGNED_FAILED(117,"签约失败"),
    SIGNED_SUCCESS(118,"签约成功"),
    UN_AUTHORIZED(119,"没有权限使用该接口"),
    PERSON_CARDNO_ERROR(215,"身份信息有误"),
    VIN_AUTO_ERROR(216,"VIN码没有匹配的车型");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
