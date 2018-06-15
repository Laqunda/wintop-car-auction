package com.wintop.ms.carauction.core.entity;

import java.io.Serializable;
import java.util.Date;

public class CarManagerUser implements Serializable {

    private static final long serialVersionUID = -7611638328082992013L;
    /**
     * 管理员ID
     */
    private Long id;

    private String userName;


    private String userKey;

    /**
     * 登陆密码
     */
    private String userPassword;

    /**
     * 员工编码
     */
    private String userCode;

    /**
     * 1正常，2冻结，3删除
     */
    private String userStatus;

    /**
     * 管理员手机
     */
    private String userPhone;

    /**
     * 管理员头像
     */
    private String userPhoto;

    /**
     * 角色id
     */
    private Long roleId;

    private String roleName;

    /**
     * 1平台，2中心，3经销店，4代办公司
     */
    private Long roleTypeId;

    private String roleTypeName;

    /**
     * 如果是中心为centrt_id,如果是代办公司为company_id,如果是店铺为store_id
     */
    private Long departmentId;

    private String departmentName;

    /**
     * 管理员最后登陆时间
     */
    private Date loginTime;

    /**
     * 管理员创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private Long createPerson;

    /**
     * 最后修改人
     */
    private Long modifyPerson;

    /**
     * 管理员修改信息时间
     */
    private Date modifyTime;

    private Date delTime;

    private Long delPerson;

    private String remark;

    private String token;

    private Long regionId;

    /**
     * @return 管理员ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            管理员ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return 员工编码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode 
	 *            员工编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @return 1正常，2冻结，3删除
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus 
	 *  1正常，2冻结，3删除
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * @return 管理员手机
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone 
	 *            管理员手机
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return 管理员头像
     */
    public String getUserPhoto() {
        return userPhoto;
    }

    /**
     * @param userPhoto 
	 *            管理员头像
     */
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    /**
     * @return 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 
	 *            角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return 1平台，2中心，3经销店，4代办公司
     */
    public Long getRoleTypeId() {
        return roleTypeId;
    }

    /**
     * @param roleTypeId 
	 *   1平台，2中心，3经销店，4代办公司
     */
    public void setRoleTypeId(Long roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    /**
     * @return 如果是中心为centrt_id,如果是代办公司为company_id,如果是店铺为store_id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId 
	 *            如果是中心为centrt_id,如果是代办公司为company_id,如果是店铺为store_id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return 管理员最后登陆时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime 
	 *            管理员最后登陆时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return 管理员创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            管理员创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 创建人ID
     */
    public Long getCreatePerson() {
        return createPerson;
    }

    /**
     * @param createPerson 
	 *            创建人ID
     */
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * @return 最后修改人
     */
    public Long getModifyPerson() {
        return modifyPerson;
    }

    /**
     * @param modifyPerson 
	 *            最后修改人
     */
    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    /**
     * @return 管理员修改信息时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime 
	 *            管理员修改信息时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public Long getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Long delPerson) {
        this.delPerson = delPerson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}