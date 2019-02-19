package com.wintop.ms.carauction.entity;

import java.io.Serializable;

/**
 * class_name: CarPhotoTemp
 * package: com.wintop.ms.carauction.entity
 * describe: 图片临时存储表
 * creat_user: lizhaoyang
 * creat_date-time: 2018/9/8/11:19
 **/
public class CarPhotoTemp implements Serializable{

    private static final long serialVersionUID = -5043857786356028447L;
    private Integer id;

    private String mainPhoto;

    public CarPhotoTemp() {
    }

    public CarPhotoTemp(Integer id, String mainPhoto) {
        this.id = id;
        this.mainPhoto = mainPhoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarPhotoTemp)) return false;

        CarPhotoTemp that = (CarPhotoTemp) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return mainPhoto != null ? mainPhoto.equals(that.mainPhoto) : that.mainPhoto == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mainPhoto != null ? mainPhoto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarPhotoTemp{" +
                "id=" + id +
                ", mainPhoto='" + mainPhoto + '\'' +
                '}';
    }
}
