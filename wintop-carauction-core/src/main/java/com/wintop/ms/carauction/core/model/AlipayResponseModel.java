package com.wintop.ms.carauction.core.model;

/***
 * 支付宝支付--通知结果实体
 */
public class AlipayResponseModel {

    /***
     * 付款金额
     */
    private String amount;
    /***
     * 商户订单号
     */
    private String outTradeNo;
    /***
     * 支付宝交易号
     */
    private String tradeNo;
    /***
     * 附加数据
     */
    private String passbackParams;

    /***
     * 支付结果log
     */
    private String log;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
