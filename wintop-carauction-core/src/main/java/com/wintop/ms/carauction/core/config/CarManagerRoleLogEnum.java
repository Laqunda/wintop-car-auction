package com.wintop.ms.carauction.core.config;

public enum CarManagerRoleLogEnum {
    APPLY("1","提交申请"),
    PASS("2","审核通过"),
    NOT_PASS("-1","审核不通过"),
    CANCEL("3","审核撤销");

    private String val;
    private String msg;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    CarManagerRoleLogEnum(String val, String msg) {
        this.val = val;
        this.msg = msg;
    }

    public CarManagerRoleLogEnum getEnum(String paramVal) {
        for (CarManagerRoleLogEnum value : values()) {
            if (value.getVal().equals(paramVal) || value.getMsg().equals(paramVal)) {
                return value;
            }
        }
        return null;
    }
}
