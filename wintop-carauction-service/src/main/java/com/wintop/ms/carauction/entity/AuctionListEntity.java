package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 接口返回list
 * @param <T>
 */
public class AuctionListEntity<T> implements Serializable {

    private static final long serialVersionUID = 8750693796855391028L;
    private List<T> dataList;

    private String title;

    private String desc;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
