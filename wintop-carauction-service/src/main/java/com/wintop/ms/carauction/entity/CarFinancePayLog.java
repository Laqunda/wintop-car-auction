package com.wintop.ms.carauction.entity;

import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 12991 on 2018/2/27.
 */
public class CarFinancePayLog implements Serializable{
    private static final long serialVersionUID = 6978720403656525357L;
    /**
     * 支付日志流水ID
     */
    private Long id;

    /**
     * 支付流水号
     */
    private String logNo;

    /**
     * 支付类型：1保证金，2违约款，3车款
     */
    private String type;

    /**
     * 付款金额
     */
    private BigDecimal payFee;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付状态：1支付成功，2支付失败，3退款
     */
    private String status;

    /**
     * 支付银行流水号
     */
    private String bankOrderNo;

    /**
     * 支付银行订单日志
     */
    private String bankOrderLog;

    /**
     * 用户ID
     */
    private Long userId;

    private String payType;

    private String payEvidence;

    private String remark;

    private String payWay;

    //操作人
    private Long createPerson;

    //** 1买家，2卖家
    private String createPersonType;

    //操作时间
    private Date createTime;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayEvidence() {
        return payEvidence;
    }

    public void setPayEvidence(String payEvidence) {
        this.payEvidence = payEvidence;
    }


    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    /**
     * @return 支付日志流水ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            支付日志流水ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 支付流水号
     */
    public String getLogNo() {
        if(StringUtils.isBlank(logNo)){
            return RandCodeUtil.getOrderNumber();
        }
        return logNo;
    }

    /**
     * @param logNo
     *            支付流水号
     */
    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }

    /**
     * @return 支付类型：1保证金，2违约款，3车款
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            支付类型：1保证金，2违约款，3车款
     */
    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    /**
     * @return 付款时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime
     *            付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     *            订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     *            订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return 支付状态：1支付成功，2支付失败，3退款
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            支付状态：1支付成功，2支付失败，3退款
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 支付银行流水号
     */
    public String getBankOrderNo() {
        return bankOrderNo;
    }

    /**
     * @param bankOrderNo
     *            支付银行流水号
     */
    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    /**
     * @return 支付银行订单日志
     */
    public String getBankOrderLog() {
        return bankOrderLog;
    }

    /**
     * @param bankOrderLog
     *            支付银行订单日志
     */
    public void setBankOrderLog(String bankOrderLog) {
        this.bankOrderLog = bankOrderLog;
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

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public String getCreatePersonType() {
        return createPersonType;
    }

    public void setCreatePersonType(String createPersonType) {
        this.createPersonType = createPersonType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
