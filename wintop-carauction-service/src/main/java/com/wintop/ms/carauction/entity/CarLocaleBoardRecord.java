package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarLocaleBoardRecord implements Serializable {

    private static final long serialVersionUID = -7029012219228951495L;
    private Long id;

    /**
     * 现场拍场次Id
     */
    private Long localeAuctionId;

    /**
     * 基站物理ID
     */
    private String stationRealId;

    /**
     * 拍牌物理ID
     */
    private String boardRealId;

    private Date createTime;

    private String recordDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 场次Id
     */
    public Long getLocaleAuctionId() {
        return localeAuctionId;
    }

    /**
     * @param localeAuctionId
	 *            场次Id
     */
    public void setLocaleAuctionId(Long localeAuctionId) {
        this.localeAuctionId = localeAuctionId;
    }

    /**
     * @return 基站物理ID
     */
    public String getStationRealId() {
        return stationRealId;
    }

    /**
     * @param stationRealId 
	 *            基站物理ID
     */
    public void setStationRealId(String stationRealId) {
        this.stationRealId = stationRealId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}