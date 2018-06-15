package com.wintop.ms.carauction.core.config;

public enum ManagerRole {
    PT_GLY(1,"管理员"),
    ZX_PGS(2,"评估师"),
    ZX_ESCFZR(3,"二手车负责人"),
    ZX_CLSHR(4,"车辆审核人"),
    ZX_ZYCLR(5,"争议处理人"),
    ZX_HYGLY(6,"会员管理员"),
    JXD_PGS(7,"评估师"),
    JXD_ESCFZR(8,"二手车负责人"),
    JXD_HYGLY(9,"会员管理员"),
    DB_DBR(10,"代办人");

    private final int value;
    private final String remark;

    private ManagerRole(int value, String remark){
        this.value=value;
        this.remark=remark;
    }

    public int value() {
        return this.value;
    }

    public String getRemark(){
        return this.remark;
    }
}
