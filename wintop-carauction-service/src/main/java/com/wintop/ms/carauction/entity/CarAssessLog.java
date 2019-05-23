package com.wintop.ms.carauction.entity;

public class CarAssessLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 采购订单id
     */
    private Long assessId;

    /**
     * 订单描述
     */
    private String logMsg;
    /**
     * 操作用户ID
     */
    private Long createPerson;

    /**
     * 操作人姓名
     */
    private String userName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAssessId(Long assessId) {
        this.assessId = assessId;
    }

    public Long getAssessId() {
        return assessId;
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


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    private String mainPhoto;

    private String createUser;

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

    private String autoInfoName;

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

}
