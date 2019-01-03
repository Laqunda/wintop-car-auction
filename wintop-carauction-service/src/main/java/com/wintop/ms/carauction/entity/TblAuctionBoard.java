package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

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
    private String boardRealName;

    /**
     * 基站主键id
     */
    private Long bsId;

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

    private Date createTime;

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

    /**
     * @return 拍牌显示名字
     */
    public String getBoardRealName() {
        return boardRealName;
    }

    /**
     * @param boardRealName 
	 *            拍牌显示名字
     */
    public void setBoardRealName(String boardRealName) {
        this.boardRealName = boardRealName;
    }

    /**
     * @return 基站主键id
     */
    public Long getBsId() {
        return bsId;
    }

    /**
     * @param bsId 
	 *            基站主键id
     */
    public void setBsId(Long bsId) {
        this.bsId = bsId;
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
}