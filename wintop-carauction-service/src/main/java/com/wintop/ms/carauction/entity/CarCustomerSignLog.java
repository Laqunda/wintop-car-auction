package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/***
 * 用户签约日志表
 */
public class CarCustomerSignLog implements Serializable {
    private Long id;//主键

    private Long signId;//签约主表id

    private String log;//签约日志

    private String pdfFileUrl;//签约生成pdf合同下载地址

    private String picFileUrl;//签约生成图片合同下载地址

    private Date createTime;//创建时间

    private String type;//类型：1签字未盖章，2盖章生效



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public String getPicFileUrl() {
        return picFileUrl;
    }

    public void setPicFileUrl(String picFileUrl) {
        this.picFileUrl = picFileUrl;
    }
}
