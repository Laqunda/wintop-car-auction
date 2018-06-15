package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CommonNameVo implements Serializable{
    private static final long serialVersionUID = -7573664952085709930L;
    private Long id;
    private String name;
    private String checked;
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        if(checked==null){
            return "0";
        }
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public CommonNameVo() {
    }

    public CommonNameVo(Long id, String name) {
        this.id = id;
        this.name = name;
        this.checked="0";
    }

    public CommonNameVo(Long id, String name, String checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public String getCode() {
        if(code == null){
            return "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
