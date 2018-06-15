package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * CarCustomerBreach:客户违约信息
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerBreach implements Serializable {

    private static final long serialVersionUID = 5837060128524362507L;
    /**
     * 违约主键
     */
    private Long id;

    /**
     * 发起人
     */
    private Long initiator;

    /**
     * 违约对象类型：1买家违约，2卖家违约
     */
    private String breachObjType;

    /**
     * 发起人姓名
     */
    private String initiatCn;

    /**
     * 发起人电话
     */
    private String initiatMobile;

    /**
     * 发起时间
     */
    private Date initiatTime;

    /**
     * 发起违约留言
     */
    private String initiatMsg;

    /**
     * 发起审核人
     */
    private Long initiatAuthManager;

    /**
     * 发起审核时间
     */
    private Date initiatAuthTime;

    /**
     * 对发起审核留言
     */
    private String initiatAuthMsg;

    /**
     * 支付违约金金额
     */
    private BigDecimal money;

    /**
     * 支付时间
     */
    private Date payTime;

    private Long payAuthManager;

    /**
     * 支付审核时间
     */
    private Date payAuthTime;

    /**
     * 支付审核留言
     */
    private String payAuthMsg;

    /**
     * 状态：1发起待审核，2发起审核通过，3发起审核不通过，4待支付，5已支付待确认，6支付失败，7支付成功完成。
     */
    private String status;

    /**
     * 违约来源：1超时支付，2过户超时，3其他
     */
    private String breachSource;

    private Long orderId;

    //***争议处理人名字
    private String initiatAuthName;


    private Long autoId;

    private String breachOrderStatus;

    private String breachAutoStatus;

    private String initialAuthFile;

    private String payAuthFile;

    private String breachTransferStatus;

    public String getBreachTransferStatus() {
        return breachTransferStatus;
    }

    public void setBreachTransferStatus(String breachTransferStatus) {
        this.breachTransferStatus = breachTransferStatus;
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getBreachOrderStatus() {
        return breachOrderStatus;
    }

    public void setBreachOrderStatus(String breachOrderStatus) {
        this.breachOrderStatus = breachOrderStatus;
    }

    public String getBreachAutoStatus() {
        return breachAutoStatus;
    }

    public void setBreachAutoStatus(String breachAutoStatus) {
        this.breachAutoStatus = breachAutoStatus;
    }

    public String getInitialAuthFile() {
        return initialAuthFile;
    }

    public void setInitialAuthFile(String initialAuthFile) {
        this.initialAuthFile = initialAuthFile;
    }

    public String getPayAuthFile() {
        return payAuthFile;
    }

    public void setPayAuthFile(String payAuthFile) {
        this.payAuthFile = payAuthFile;
    }

    /**
     * @return 违约主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 违约主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 发起人
     */
    public Long getInitiator() {
        return initiator;
    }

    /**
     * @param initiator 
	 *            发起人
     */
    public void setInitiator(Long initiator) {
        this.initiator = initiator;
    }

    /**
     * @return 违约对象类型：1买家违约，2卖家违约
     */
    public String getBreachObjType() {
        return breachObjType;
    }

    /**
     * @param breachObjType 
	 *            违约对象类型：1买家违约，2卖家违约
     */
    public void setBreachObjType(String breachObjType) {
        this.breachObjType = breachObjType;
    }

    /**
     * @return 发起人姓名
     */
    public String getInitiatCn() {
        return initiatCn;
    }

    /**
     * @param initiatCn 
	 *            发起人姓名
     */
    public void setInitiatCn(String initiatCn) {
        this.initiatCn = initiatCn;
    }

    /**
     * @return 发起时间
     */
    public Date getInitiatTime() {
        return initiatTime;
    }

    /**
     * @param initiatTime 
	 *            发起时间
     */
    public void setInitiatTime(Date initiatTime) {
        this.initiatTime = initiatTime;
    }

    /**
     * @return 发起违约留言
     */
    public String getInitiatMsg() {
        return initiatMsg;
    }

    /**
     * @param initiatMsg 
	 *            发起违约留言
     */
    public void setInitiatMsg(String initiatMsg) {
        this.initiatMsg = initiatMsg;
    }

    /**
     * @return 发起审核人
     */
    public Long getInitiatAuthManager() {
        return initiatAuthManager;
    }

    /**
     * @param initiatAuthManager 
	 *            发起审核人
     */
    public void setInitiatAuthManager(Long initiatAuthManager) {
        this.initiatAuthManager = initiatAuthManager;
    }

    /**
     * @return 发起审核时间
     */
    public Date getInitiatAuthTime() {
        return initiatAuthTime;
    }

    /**
     * @param initiatAuthTime 
	 *            发起审核时间
     */
    public void setInitiatAuthTime(Date initiatAuthTime) {
        this.initiatAuthTime = initiatAuthTime;
    }

    /**
     * @return 对发起审核留言
     */
    public String getInitiatAuthMsg() {
        return initiatAuthMsg;
    }

    /**
     * @param initiatAuthMsg 
	 *            对发起审核留言
     */
    public void setInitiatAuthMsg(String initiatAuthMsg) {
        this.initiatAuthMsg = initiatAuthMsg;
    }

    /**
     * @return 支付违约金金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money 
	 *            支付违约金金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * @return 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime 
	 *            支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getPayAuthManager() {
        return payAuthManager;
    }

    public void setPayAuthManager(Long payAuthManager) {
        this.payAuthManager = payAuthManager;
    }

    /**
     * @return 支付审核时间
     */
    public Date getPayAuthTime() {
        return payAuthTime;
    }

    /**
     * @param payAuthTime 
	 *            支付审核时间
     */
    public void setPayAuthTime(Date payAuthTime) {
        this.payAuthTime = payAuthTime;
    }

    /**
     * @return 支付审核留言
     */
    public String getPayAuthMsg() {
        return payAuthMsg;
    }

    /**
     * @param payAuthMsg 
	 *            支付审核留言
     */
    public void setPayAuthMsg(String payAuthMsg) {
        this.payAuthMsg = payAuthMsg;
    }

    /**
     * @return 状态：1发起待审核，2发起审核通过，3发起审核不通过，4待支付，5已支付待确认，6支付失败，7支付成功完成。
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1发起待审核，2发起审核通过，3发起审核不通过，4待支付，5已支付待确认，6支付失败，7支付成功完成。
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 违约来源：1超时支付，2过户超时，3其他
     */
    public String getBreachSource() {
        return breachSource;
    }

    /**
     * @param breachSource 
	 *            违约来源：1超时支付，2过户超时，3其他
     */
    public void setBreachSource(String breachSource) {
        this.breachSource = breachSource;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }



    public String getInitiatMobile() {
        return initiatMobile;
    }

    public void setInitiatMobile(String initiatMobile) {
        this.initiatMobile = initiatMobile;
    }

    public String getInitiatAuthName() {
        return initiatAuthName;
    }

    public void setInitiatAuthName(String initiatAuthName) {
        this.initiatAuthName = initiatAuthName;
    }
}