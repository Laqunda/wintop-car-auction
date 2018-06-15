package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12991 on 2018/2/27.
 */
public class CarFinanceStoreRecord implements Serializable{
    private static final long serialVersionUID = -3264442398503093374L;
    /**
     * 变更记录ID
     */
    private Long id;

    /**
     * 支付流水id
     */
    private Long payId;

    /**
     * 资金累计收入
     */
    private Long accumulatedIncome;

    /**
     * 累计支出金额
     */
    private Long accumulatedOutlay;

    /**
     * 变更金额
     */
    private Long changeAmount;

    /**
     * 变更时间
     */
    private Date changeTime;

    /**
     * 变更类型：1保证金、2违约金、3车款、4其他
     */
    private String type;

    /**
     * 店铺id，4S店
     */
    private Long storeId;

    /**
     * @return 变更记录ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            变更记录ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 支付流水id
     */
    public Long getPayId() {
        return payId;
    }

    /**
     * @param payId
     *            支付流水id
     */
    public void setPayId(Long payId) {
        this.payId = payId;
    }

    /**
     * @return 资金累计收入
     */
    public Long getAccumulatedIncome() {
        return accumulatedIncome;
    }

    /**
     * @param accumulatedIncome
     *            资金累计收入
     */
    public void setAccumulatedIncome(Long accumulatedIncome) {
        this.accumulatedIncome = accumulatedIncome;
    }

    /**
     * @return 累计支出金额
     */
    public Long getAccumulatedOutlay() {
        return accumulatedOutlay;
    }

    /**
     * @param accumulatedOutlay
     *            累计支出金额
     */
    public void setAccumulatedOutlay(Long accumulatedOutlay) {
        this.accumulatedOutlay = accumulatedOutlay;
    }

    /**
     * @return 变更金额
     */
    public Long getChangeAmount() {
        return changeAmount;
    }

    /**
     * @param changeAmount
     *            变更金额
     */
    public void setChangeAmount(Long changeAmount) {
        this.changeAmount = changeAmount;
    }

    /**
     * @return 变更时间
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * @param changeTime
     *            变更时间
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * @return 变更类型：1保证金、2违约金、3车款、4其他
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            变更类型：1保证金、2违约金、3车款、4其他
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 店铺id，4S店
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * @param storeId
     *            店铺id，4S店
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
