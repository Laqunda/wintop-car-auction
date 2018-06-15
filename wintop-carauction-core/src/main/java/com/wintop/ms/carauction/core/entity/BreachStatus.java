package com.wintop.ms.carauction.core.entity;

public class BreachStatus {
    private String status;
    private String statusValue;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public BreachStatus(String status, String statusValue) {
        this.status = status;
        this.statusValue = statusValue;
    }

    public BreachStatus() {
    }
}
