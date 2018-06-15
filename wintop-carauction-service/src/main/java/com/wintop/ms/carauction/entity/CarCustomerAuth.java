package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * CarCustomerAuth:用户认证信息实体
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerAuth implements Serializable {

    private static final long serialVersionUID = 4147798094012497009L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 身份证号
     */
    private String identityNumber;

    /**
     * 身份证正面照片
     */
    private String identityPhotoFront;

    /**
     * 身份证背面
     */
    private String identityPhotoBack;

    /**
     * 手持身份证
     */
    private String identityPhotoHand;

    /**
     * 审核状态：1待审核，2审核通过，-1审核不通过
     */
    private String authStatus;

    /**
     * 审核留言
     */
    private String authMsg;

    /**
     * 审核时间
     */
    private Date authTime;

    /**
     * 审核人
     */
    private Long authManager;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 签约状态：1未签约，2已经签约，3签约失效
     */
    private String signPact;

    /**
     * 省份
     */
    private Long province;

    /**
     * 地区市
     */
    private Long region;

    /**
     * 县
     */
    private Long  county;

    private String cityName;

    private String mobile;
    private String userStoreName;

    private String isAvailable;
    /***
     * 用户主状态
     */
    private String status;

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getUserStoreName() {
        return userStoreName;
    }

    public void setUserStoreName(String userStoreName) {
        this.userStoreName = userStoreName;
    }

    public Long getCounty() {
        return county;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCounty(Long county) {
        this.county = county;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 客户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            客户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName 
	 *            真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return 通讯地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            通讯地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 身份证号
     */
    public String getIdentityNumber() {
        return identityNumber;
    }

    /**
     * @param identityNumber 
	 *            身份证号
     */
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    /**
     * @return 身份证正面照片
     */
    public String getIdentityPhotoFront() {
        return identityPhotoFront;
    }

    /**
     * @param identityPhotoFront 
	 *            身份证正面照片
     */
    public void setIdentityPhotoFront(String identityPhotoFront) {
        this.identityPhotoFront = identityPhotoFront;
    }

    /**
     * @return 身份证背面
     */
    public String getIdentityPhotoBack() {
        return identityPhotoBack;
    }

    /**
     * @param identityPhotoBack 
	 *            身份证背面
     */
    public void setIdentityPhotoBack(String identityPhotoBack) {
        this.identityPhotoBack = identityPhotoBack;
    }

    /**
     * @return 手持身份证
     */
    public String getIdentityPhotoHand() {
        return identityPhotoHand;
    }

    /**
     * @param identityPhotoHand 
	 *            手持身份证
     */
    public void setIdentityPhotoHand(String identityPhotoHand) {
        this.identityPhotoHand = identityPhotoHand;
    }

    /**
     * @return 审核状态：1待审核，2审核通过，-1审核不通过
     */
    public String getAuthStatus() {
        return authStatus;
    }

    /**
     * @param authStatus 
	 *            审核状态：1待审核，2审核通过，-1审核不通过
     */
    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
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

    /**
     * @return 审核人
     */
    public Long getAuthManager() {
        return authManager;
    }

    /**
     * @param authManager 
	 *            审核人
     */
    public void setAuthManager(Long authManager) {
        this.authManager = authManager;
    }

    /**
     * @return 申请时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime 
	 *            申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return 签约状态：1未签约，2已经签约，3签约失效
     */
    public String getSignPact() {
        return signPact;
    }

    /**
     * @param signPact 
	 *            签约状态：1未签约，2已经签约，3签约失效
     */
    public void setSignPact(String signPact) {
        this.signPact = signPact;
    }

    /**
     * @return 省份
     */
    public Long getProvince() {
        return province;
    }

    /**
     * @param province 
	 *            省份
     */
    public void setProvince(Long province) {
        this.province = province;
    }

    /**
     * @return 地区市
     */
    public Long getRegion() {
        return region;
    }

    /**
     * @param region 
	 *            地区市
     */
    public void setRegion(Long region) {
        this.region = region;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}