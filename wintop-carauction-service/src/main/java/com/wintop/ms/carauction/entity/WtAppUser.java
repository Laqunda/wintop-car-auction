package com.wintop.ms.carauction.entity;

import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

/**
 * 客户端用户表
 * Created by liangtingsen on 2018/2/3.
 */
public class WtAppUser implements Serializable {
    private static final long serialVersionUID = 8264696121735296419L;
    private Long id;
    private String userName;//用户名
    private String password;//密码
    private String name;//姓名
    private String address;//同学地址
    private String city;//所在城市
    private String headImg;//头像
    private String sex;//性别男女
    private String mobile;//手机号
    private String loginIp;//最近登陆ip
    private Timestamp registTime;//注册时间
    private Timestamp loginTime;//最近登陆时间
    private String status;//状态
    private String userNum;//车商号
    private Long userLevelId;//用户等级
    private Timestamp updateTime;//修改时间
    private BigDecimal depositAmount;//保证金金额
    private String identityNumber;//省份证号
    private String levelName;//等级名称
    private String freezeFlag;//-1冻结 0不冻结  保证金状态
    private String userStoreName; //会员所属4s店
    private String groupName;//分组名称
    private String isShare;
    private String groupIds;
    private Date signatureTime;
    private Long authId;
    private Long signId;
    private String userStatus;
    private String statusName;
    private String userCode;
    private String auctionPlateNum;//拍牌号

    private String manualAdd; //0 注册 1导入，2添加',

    private String type;
    //,,拍牌物理id
    private String boardRealId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSignatureTime() {
        return signatureTime;
    }

    public void setSignatureTime(Date signatureTime) {
        this.signatureTime = signatureTime;
    }

    public String getUserCode() {
        if(StringUtils.isBlank(userCode)){
            return RandCodeUtil.getOrderNumber();
        }
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getManualAdd() {
        return manualAdd;
    }

    public void setManualAdd(String manualAdd) {
        this.manualAdd = manualAdd;
    }

    public String getAuctionPlateNum() {
        return auctionPlateNum;
    }

    public void setAuctionPlateNum(String auctionPlateNum) {
        this.auctionPlateNum = auctionPlateNum;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * 加价器编号
     */
    private String clientCode;

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }



    /**
     * 备用电话
     */
    private String tel1;

    /**
     * 备用电话
     */
    private String tel2;

    /**
     * 所属店铺
     */
    private Long storeId;

    /**
     * 备注
     */
    private String remark;


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
     * 省份
     */
    private Long province;

    /**
     * 地区市
     */
    private Long region;
    private String cityName;
    private String provinceName;

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getIdentityPhotoFront() {
        return identityPhotoFront;
    }

    public void setIdentityPhotoFront(String identityPhotoFront) {
        this.identityPhotoFront = identityPhotoFront;
    }

    public String getIdentityPhotoBack() {
        return identityPhotoBack;
    }

    public void setIdentityPhotoBack(String identityPhotoBack) {
        this.identityPhotoBack = identityPhotoBack;
    }

    public String getIdentityPhotoHand() {
        return identityPhotoHand;
    }

    public void setIdentityPhotoHand(String identityPhotoHand) {
        this.identityPhotoHand = identityPhotoHand;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }

    public String getUserStoreName() {
        return userStoreName;
    }

    public void setUserStoreName(String userStoreName) {
        this.userStoreName = userStoreName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFreezeFlag() {
        return freezeFlag;
    }

    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Timestamp getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Timestamp registTime) {
        this.registTime = registTime;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }


    public Long getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Long userLevelId) {
        this.userLevelId = userLevelId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getBoardRealId() {
        return boardRealId;
    }

    public void setBoardRealId(String boardRealId) {
        this.boardRealId = boardRealId;
    }
}
