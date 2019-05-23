package com.wintop.ms.carauction.entity;

/**
 * 评估采购日志表 car_assess_order_log
 *
 * @author ruoyi
 * @date 2019-05-05
 */
public class CarAssessOrderLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 采购订单id
     */
    private Long assessOrderId;
    /**
     * 状态 1提交申请，2审核通过，-1审核不通过 3审核撤销
     */
    private String status;
    /**
     * 订单状态描述
     */
    private String statusCn;
    /**
     * 订单描述
     */
    private String logMsg;
    /**
     * 操作用户ID
     */
    private Long createPerson;
    /**
     * 操作人手机号
     */
    private String userMobile;
    /**
     * 操作人姓名
     */
    private String userName;

    private String mainPhoto;

    private String autoInfoName;

    private String createUser;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAssessOrderId(Long assessOrderId) {
        this.assessOrderId = assessOrderId;
    }

    public Long getAssessOrderId() {
        return assessOrderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }


    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

}
