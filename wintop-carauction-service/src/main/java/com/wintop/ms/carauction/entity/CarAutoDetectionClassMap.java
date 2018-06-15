package com.wintop.ms.carauction.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * 车辆检测项=检测报告使用
 */
public class CarAutoDetectionClassMap {

    private Long classId;
    private String name;
    private String classNo;
    private String resultDescribe;
    private String damagedType;
    private List<Map> photoList = new ArrayList<>();

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getResultDescribe() {
        return resultDescribe;
    }

    public void setResultDescribe(String resultDescribe) {
        this.resultDescribe = resultDescribe;
    }

    public String getDamagedType() {
        return damagedType;
    }

    public void setDamagedType(String damagedType) {
        this.damagedType = damagedType;
    }

    public List<Map> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Map> photoList) {
        this.photoList = photoList;
    }
}
