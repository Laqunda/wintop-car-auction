package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CustomerBoard implements Serializable {

    private static final long serialVersionUID = 3099993595889742117L;
    private Long id;

    /**
     * 会员id
     */
    private Long customerId;

    /**
     * 拍牌id
     */
    private Long boardId;

    /**
     * 拍牌物理ID
     */
    private String boardRealId;

    /**
     * 状态：1绑定，2解绑
     */
    private String bindStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createPerson;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private Long modifyPerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 会员id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            会员id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return 拍牌id
     */
    public Long getBoardId() {
        return boardId;
    }

    /**
     * @param boardId 
	 *            拍牌id
     */
    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    /**
     * @return 拍牌物理ID
     */
    public String getBoardRealId() {
        return boardRealId;
    }

    /**
     * @param boardRealId 
	 *            拍牌物理ID
     */
    public void setBoardRealId(String boardRealId) {
        this.boardRealId = boardRealId;
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 创建人
     */
    public Long getCreatePerson() {
        return createPerson;
    }

    /**
     * @param createPerson 
	 *            创建人
     */
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * @return 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime 
	 *            修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return 修改人
     */
    public Long getModifyPerson() {
        return modifyPerson;
    }

    /**
     * @param modifyPerson 
	 *            修改人
     */
    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }
}