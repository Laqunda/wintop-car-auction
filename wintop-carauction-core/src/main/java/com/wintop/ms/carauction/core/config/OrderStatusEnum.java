package com.wintop.ms.carauction.core.config;

public enum OrderStatusEnum {
    WAITING_PAY("1","等待付款"),
    PAY_AUDITOR("2","付款信息待审核"),
    TRANSFER_HANLDING("3","过户处理中"),
    BREACH_HANLDING("4","争议处理中"),
    BREAK_FEE_AUDITOR("5","违约金支付确认中"),
    PROCEDURE_HANLDING("6","手续回传待确认"),
    EXCHANGE_COMPLETE("7","交易完成"),
    EXCHANGE_CLOSED ("8","交易关闭");

    private final String value;
    private final String remark;

    private OrderStatusEnum(String value, String remark){
        this.value=value;
        this.remark=remark;
    }

    public String value(){
        return this.value;
    }

    public String getRemark(){
        return this.remark;
    }

    public static String getDetailRemark(String value){
        for(OrderStatusEnum statusEnum:OrderStatusEnum.values()){
            if(value.equals(statusEnum.value())){
                return statusEnum.getRemark();
            }
        }
        return "";
    }
}
