package com.wintop.ms.carauction.entity;

import java.io.Serializable;

/**
 * class_name: CarDataImportRecord
 * package: com.wintop.ms.carauction.entity
 * describe: 导入数据id记录实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/9/8/9:12
 **/
public class CarDataImportRecord implements Serializable{

    private static final long serialVersionUID = 4884996489308992856L;
    private Long id;

    private Integer idRecord;

    public CarDataImportRecord() {
    }

    public CarDataImportRecord(Long id, Integer idRecord) {
        this.id = id;
        this.idRecord = idRecord;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDataImportRecord)) return false;

        CarDataImportRecord that = (CarDataImportRecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return idRecord != null ? idRecord.equals(that.idRecord) : that.idRecord == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idRecord != null ? idRecord.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarDataImportRecord{" +
                "id=" + id +
                ", idRecord=" + idRecord +
                '}';
    }
}
