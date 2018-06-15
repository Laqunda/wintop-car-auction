package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarOrderLog implements Serializable {

    private static final long serialVersionUID = -6391454497477283852L;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 状态：1等待付款、2付款信息待审核、3过户处理中、4争议处理中、5违约金支付确认中、6手续回传待确认、7交易完成、8交易关闭
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
     * 操作时间
     */
    private Date createTime;

    private Long createPerson;

    private String userName;

    private String userMobile;

    /**
     * @return 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
	 *            订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return 状态：1等待付款、2付款信息待审核、3过户处理中、4争议处理中、5违约金支付确认中、6手续回传待确认、7交易完成、8交易关闭
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *  状态：1等待付款、2付款信息待审核、3过户处理中、4争议处理中、5违约金支付确认中、6手续回传待确认、7交易完成、8交易关闭
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 订单状态描述
     */
    public String getStatusCn() {
        return statusCn;
    }

    /**
     * @param statusCn 
	 *            订单状态描述
     */
    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    /**
     * @return 订单描述
     */
    public String getLogMsg() {
        return logMsg;
    }

    /**
     * @param logMsg 
	 *            订单描述
     */
    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
}