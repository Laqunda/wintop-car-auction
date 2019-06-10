package com.wintop.ms.carauction.core.config;

public enum ResultCode {
    NET_EXCEPTION(0, "网络异常"),
    SUCCESS(100, "操作成功"),
    NO_PARAM(101, "缺少参数"),
    NO_USER(111, "用户不存在"),
    NO_OBJECT(114, "数据不存在"),
    FAIL(115, "保存/更新失败（未有异常）"),
    APPID_NULL(201, "缺少appid"),
    APPID_DISABLED(202, "无效的appid"),
    TIMESTAMP_ERROR(203, "错误的时间戳"),
    TOKEN_NULL(204, "缺少token"),
    TOKEN_DISABLED(205, "无效的token"),
    TOKEN_TIME_OUT(206, "超时的token"),
    SIGN_ERROR(207, "签名错误"),
    REQUEST_DISABLED(208, "无效的请求"),
    API_NOT_REALIZE(209, "接口未实现"),
    NO_REQUEST_AUTH(210, "无权限操作"),
    NOT_VERIFY_REAL_NAME(211, "未实名认证"),
    DID_NOT_SIGN_UP(212, "未签约"),
    UNSECURED_DEPOSIT(213, "未缴纳保证金"),
    SIG_AUTH_ING(214, "签约审核中"),
    BUSS_EXCEPTION(300, "服务器走神了，一会儿再试。"),
    //***mobile-api
    NO_SET_ENTRUST(301, "未设置委托价"),
    NO_AUCTION(302, "车辆没有拍卖活动"),
    NO_OVER_MAXPRICE(303, "竞价出价未超过最大价"),
    NO_ALLOW_ENTRUST(304, "开拍状态，不允许操作委托价"),
    //***boss-api;
    NOT_SYNC(400, "数据不同步"),
    PASSWORD_NOT_MATCH(401, "旧密码错误"),
    USERNUM_IS_REPEAT(402, "车商号重复"),
    NO_MANAGER_USER(403, "登录账号不存在"),
    NO_MATCH_PASSWORD(404, "密码不正确"),
    NO_ALLOW_LOGIN(405, "该账号禁止登录"),
    KEY_YET_DELETE(406, "该账号已删除"),
    KEY_EXIST(407, "该账号已存在"),
    UNIQUE_REGION_SETTING(408, "每个地区只能设置唯一拍卖信息"),
    NO_AUCTION_START_TIME(409, "开拍时间不能为空"),
    FORBID_CONFIRM(410, "不允许操作"),
    NO_ALLOW_DEL(411, "不允许删除自己"),
    NO_REGION_ID(412, "缺少regionId参数"),
    SIGN_FAIL(413, "签约失败"),
    REPEAT_AUCTION_PLATE(414, "拍牌号已经存在"),
    FILE_FORMAT(416, "文件格式不正确"),
    REPEAT_RESERVATION(417, "您已经预约过，请不要重复预约"),
    REPEAT_MOBILE(418, "该手机号已经存在"),
    NO_REGION_AUCTION_SETTING(419, "获取不到该地区的拍卖信息"),
    ERROR_PARAM(420, "错误的参数"),
    NO_ALLOW_UPDATE(421, "只允许操作自己的发拍的车辆"),
    AMOUNT_ERROR(422, "金额填写错误"),
    DELETE_LEVEL_DEFAUIT(423, "该级别为默认级别，不能删除"),
    PARAM_ERROR(424, "参数错误"),
    NO_UPDATE_BREACH(425, "修改车价必须是争议之前订单状态为待付款"),
    EXISTS_REPEAT_AUCTION_PLATE_NUM(426, "存在重复的拍牌号"),

    JZ_INVALID(501, "基站无效"),
    PP_INVALID(502, "拍牌无效"),
    MM_INVALID(503, "密码无效"),
    DUPLICATE_ADD(504, "不可重复添加"),
    //    ******** 2.0 store
    LOW_BALANCE(1000, "余额不足"),
    NO_ORDER(1001, "未发现订单");


    private final int value;
    private final String remark;

    private ResultCode(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int value() {
        return this.value;
    }

    public String strValue() {
        return String.valueOf(this.value);
    }

    public String getRemark() {
        return this.remark;
    }
}
