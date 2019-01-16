package com.wintop.ms.carauction.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class TblBoardStation implements Serializable {

    private static final long serialVersionUID = 3169368567644698231L;
    private Long id;

    /**
     * 拍牌物理ID(十六进制)
     */
    private String boardRealId;

    /**
     * 基站物理ID
     */
    private String stationRealId;

    private String stationName;

    //0为选择，1选择
    private String checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardRealId() {
        return boardRealId;
    }

    public void setBoardRealId(String boardRealId) {
        this.boardRealId = boardRealId;
    }

    public String getStationRealId() {
        return stationRealId;
    }

    public void setStationRealId(String stationRealId) {
        this.stationRealId = stationRealId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getChecked() {
        if(StringUtils.isBlank(checked)){
            return "0";
        }
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}