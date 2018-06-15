package com.wintop.ms.carauction.core.config;

public enum TransferStatusEnum {
    TRANSFER_HANLDING("1","过户事宜确定中"),
    PLATE_HANLDING("2","出牌确认中"),
    HANDOVER_FILE("3","交档确认中"),
    EXTRACT_FILE("4","提档确认中"),
    PROCEDURE_UPLOADING("5","手续上传中"),
    PROCEDURE_HANLDING("6","手续回传确认中"),
    PROCEDURE_NO_PASS("7","手续回传不通过"),
    AGENT_COMPLETE("8","代办完结"),
    BREACH_HANLDING("9","争议处理中"),
    EXCHANGE_CLOSED ("10","交易关闭");

    private final String value;
    private final String remark;

    private TransferStatusEnum(String value, String remark){
        this.value=value;
        this.remark=remark;
    }

    public String value(){
        return this.value;
    }

    public String getRemark(){
        return this.remark;
    }
}
