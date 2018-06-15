package com.wintop.ms.carauction.core.model;

/***
 * 支付宝支付--请求参数实体
 */
public class AlipayRequestModel {

    /***
     * 附加信息
     * 支付成功原样回传
     */
    private String passbackParams;

    /***
     * 支付商品标题
     */
    private String subject;

    /***
     * 订单描述
     */
    private String body;

    /***
     * 业务订单号
     */
    private String outTradeNo;

    /***
     * 超时关闭订单时间默认30分钟
     */
    private String timeoutExpress = "30m";

    /***
     * 支付总金额
     */
    private String totalAmount;

    /***
     * //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
     */
    private String productCode = "QUICK_MSECURITY_PAY";

    /***
     * 支付成功回调通知地址
     */
    private String payNotifyUrl;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }
}
