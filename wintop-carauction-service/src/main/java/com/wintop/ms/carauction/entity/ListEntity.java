package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 接口返回list
 * @param <T>
 */
public class ListEntity<T> implements Serializable {
    private static final long serialVersionUID = -8382631969906532508L;

    private List<T> list;

    private int count;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ListEntity(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }

    public ListEntity() {
    }
}
