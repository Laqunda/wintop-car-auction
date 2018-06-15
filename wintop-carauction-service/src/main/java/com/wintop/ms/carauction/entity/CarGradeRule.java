package com.wintop.ms.carauction.entity;

import java.io.Serializable;

/**
 * @author zhangzijuan
 * @Description:车辆的评级规则
 * @date 2018-03-07
 */
public class CarGradeRule implements Serializable {
    private static final long serialVersionUID = 2257113257639583336L;

    //类型 1综合车况 2整备成本
    private String type;
    //级别名称
    private String gradeName;
    //评级内容
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
