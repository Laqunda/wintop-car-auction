package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * AppUserToken:用户保证金实体
 * @author zhangzijuan
 * @date 2018-02-08
 */
public class CarCustomerDeposit implements Serializable {
    private static final long serialVersionUID = -4120235580096934971L;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 保证金
     */
    private BigDecimal depositAmount;

    /**
     * 保证金时间
     */
    private Date payTime;

    /**
     * 审核时间
     */
    private Date authTime;

    private Long authManager;

    /**
     * 保证金审核状态，1待审核，2通过，-1不通过，3冻结
     */
    private String status;

    /**
     * 审核留言
     */
    private String authMsg;

    private Date freezeTime;

    /**
     * 是否冻结：-1冻结，其他不冻结
     */
    private String freezeFlag;

    /**
     * 保证金流水号
     */
    private String depositNo;

    private String userStoreName;

    private String userNum;

    private String name;

    private String mobile;

    private String payWay;

    private String useStatus;

    /***
     * 支付流水对象
     */
    private CarFinancePayLog payLog;

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getUserStoreName() {
        return userStoreName;
    }

    public void setUserStoreName(String userStoreName) {
        this.userStoreName = userStoreName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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
     * @return 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    /**
     * @return 保证金时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime 
	 *            保证金时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return 审核时间
     */
    public Date getAuthTime() {
        return authTime;
    }

    /**
     * @param authTime 
	 *            审核时间
     */
    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public Long getAuthManager() {
        return authManager;
    }

    public void setAuthManager(Long authManager) {
        this.authManager = authManager;
    }

    /**
     * @return 保证金审核状态，1待审核，2通过，-1不通过，3冻结
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            保证金审核状态，1待审核，2通过，-1不通过，3冻结
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 审核留言
     */
    public String getAuthMsg() {
        return authMsg;
    }

    /**
     * @param authMsg 
	 *            审核留言
     */
    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
    }

    public Date getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(Date freezeTime) {
        this.freezeTime = freezeTime;
    }

    /**
     * @return 是否冻结：-1冻结，其他不冻结
     */
    public String getFreezeFlag() {
        return freezeFlag;
    }

    /**
     * @param freezeFlag 
	 *            是否冻结：-1冻结，其他不冻结
     */
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag;
    }

    /**
     * @return 保证金流水号
     */
    public String getDepositNo() {
        return depositNo;
    }

    /**
     * @param depositNo 
	 *            保证金流水号
     */
    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }

    public CarFinancePayLog getPayLog() {
        return payLog;
    }

    public void setPayLog(CarFinancePayLog payLog) {
        this.payLog = payLog;
    }
}