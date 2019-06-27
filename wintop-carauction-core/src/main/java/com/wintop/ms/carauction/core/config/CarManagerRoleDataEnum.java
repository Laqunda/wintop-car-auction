package com.wintop.ms.carauction.core.config;

public enum CarManagerRoleDataEnum {
    CHABOSHI("CHABOSHI");

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    CarManagerRoleDataEnum(String content) {
        this.content = content;
    }
}
