package com.wintop.ms.carauction.entity;

import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * class_name: CarDataImportRecord
 * package: com.wintop.ms.carauction.entity
 * describe: 导入数据id记录实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/9/8/9:06
 **/
public class CarDataImportRecord implements Serializable{

    private static final long serialVersionUID = -5558595000037767925L;
    /**
     * 唯一标识性id
     */
    private Long id;
    /**
     * 用来存放导入数据的起始id
     */
    private Integer idRecord;

    private Long timeCheck;

    public CarDataImportRecord() {
    }

    public CarDataImportRecord(Long id, Integer idRecord, Long timeCheck) {
        this.id = id;
        this.idRecord = idRecord;
        this.timeCheck = timeCheck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public Long getTimeCheck() {
        return timeCheck;
    }

    public void setTimeCheck(Long timeCheck) {
        this.timeCheck = timeCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDataImportRecord)) return false;

        CarDataImportRecord that = (CarDataImportRecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idRecord != null ? !idRecord.equals(that.idRecord) : that.idRecord != null) return false;
        return timeCheck != null ? timeCheck.equals(that.timeCheck) : that.timeCheck == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idRecord != null ? idRecord.hashCode() : 0);
        result = 31 * result + (timeCheck != null ? timeCheck.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarDataImportRecord{" +
                "id=" + id +
                ", idRecord=" + idRecord +
                ", timeCheck=" + timeCheck +
                '}';
    }
}
