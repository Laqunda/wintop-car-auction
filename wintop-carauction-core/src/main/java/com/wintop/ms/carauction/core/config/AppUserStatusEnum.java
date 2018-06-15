package com.wintop.ms.carauction.core.config;

/***
 * 买家用户几种状态
 * 可买家调用接口鉴权使用
 */
public enum AppUserStatusEnum {
    AUTH_NO("1","未实名认证"),
    AUTH_ING("2","认证未审核"),
    AUTH_SUCCESS("3","已认证-未签约"),
    SIG_SUCCESS("4","已签约-未缴纳保证金"),
    SIG_ING("5","签约审核中"),
    SIG_ERROR("6","签约审核不通过"),
    SIG_OK("7","签约审核通过");

    private final String value;
    private final String remark;

    AppUserStatusEnum(String value, String remark){
        this.value=value;
        this.remark=remark;
    }

    public String value() {
        return this.value;
    }

    public String getRemark(){
        return this.remark;
    }
}
