package com.wintop.ms.carauction.entity;


import java.io.Serializable;
import java.sql.Date;

public class CarAgentUser implements Serializable{

    private static final long serialVersionUID = -1119926530655773115L;
    private Long id;
    private Long companyId;
    private String userName;
    private String password;
    private Long updateManager;
    private Date updateTime;
    private String status;
    private String from;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUpdateManager() {
        return updateManager;
    }

    public void setUpdateManager(Long updateManager) {
        this.updateManager = updateManager;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
