package com.wintop.ms.carauction.core.config;

public enum CarStatusEnum {
    DRAFT("1","草稿"),
    WAITING_AUDITOR("2","审核中"),
    AUDITOR_NO_PASS("3","审核不通过"),
    RECALL_HANLDING("4","撤回审核中"),
    WAITING_PREPARE_AUCTION("5","等待上拍"),
    WAITING_AUCTION("6","等待开拍"),
    AUCTIONING("7","正在竞拍"),
    WAITING_PAY("8","成交-等待付款"),
    BARGAIN_HANLDING("9","议价处理中"),
    PAY_AUDITOR("10","付款信息待审核"),
    PAY_COMPLETE("11","已付款"),
    TRANSFER_HANLDING("12","过户中"),
    BREAK_HANLDING("13","违约"),
    BREACH_HANLDING("14","争议处理中"),
    PROCEDURE_HANLDING("15","手续回传待确认"),
    EXCHANGE_COMPLETE("16","交易完成"),
    WAITING_COMMENT("17","待评价"),
    EXCHANGE_CLOSED ("18","交易关闭"),
    ABORTIVE_AUCTION ("19","流拍");

    private final String value;
    private final String remark;

    private CarStatusEnum(String value, String remark){
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
        for(CarStatusEnum statusEnum:CarStatusEnum.values()){
            if(value.equals(statusEnum.value())){
                return statusEnum.getRemark();
            }
        }
        return "";
    }
}
