package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TblAuctionBoard implements Serializable {

    private static final long serialVersionUID = 8410781848585188881L;
    private Long id;

    /**
     * 拍牌物理ID(十六进制)
     */
    private String boardRealId;

    /**
     * 拍牌显示名字
     */
    private String boardName;

    /**
     * 是否时间切割拍牌（一个拍卖场只能有一个（0：正常拍牌；1：时间切割拍牌））
     */
    private String cuttingSign;

    /**
     * 是否启用(0:启用；1:未启用)默认值为0
     */
    private String enable;

    /**
     * 备注
     */
    private String remarks;

    private Long createPerson;

    private Date createTime;

    private Long modifyPerson;

    private Date modifyTime;

    private Long delPerson;

    private Date delTime;
    //,,"1" 未删除  “2” 已删除
    private String delFlag;

    //多个基站物理id
    private String stationRealIds;

    //所属拍场，多个逗号隔开
    private String auctionNames;

    private List<TblBaseStation> baseStations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 拍牌物理ID(十六进制)
     */
    public String getBoardRealId() {
        return boardRealId;
    }

    /**
     * @param boardRealId 
	 *            拍牌物理ID(十六进制)
     */
    public void setBoardRealId(String boardRealId) {
        this.boardRealId = boardRealId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    /**
     * @return 是否时间切割拍牌（一个拍卖场只能有一个（0：正常拍牌；1：时间切割拍牌））
     */
    public String getCuttingSign() {
        return cuttingSign;
    }

    /**
     * @param cuttingSign 
	 *            是否时间切割拍牌（一个拍卖场只能有一个（0：正常拍牌；1：时间切割拍牌））
     */
    public void setCuttingSign(String cuttingSign) {
        this.cuttingSign = cuttingSign;
    }

    /**
     * @return 是否启用(0:启用；1:未启用)默认值为0
     */
    public String getEnable() {
        return enable;
    }

    /**
     * @param enable 
	 *            是否启用(0:启用；1:未启用)默认值为0
     */
    public void setEnable(String enable) {
        this.enable = enable;
    }

    /**
     * @return 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks 
	 *            备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Long getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Long delPerson) {
        this.delPerson = delPerson;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getStationRealIds() {
        return stationRealIds;
    }

    public void setStationRealIds(String stationRealIds) {
        this.stationRealIds = stationRealIds;
    }

    public List<TblBaseStation> getBaseStations() {
        return baseStations;
    }

    public void setBaseStations(List<TblBaseStation> baseStations) {
        this.baseStations = baseStations;
    }

    public String getAuctionNames() {
        return auctionNames;
    }

    public void setAuctionNames(String auctionNames) {
        this.auctionNames = auctionNames;
    }
}