package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangzijuan
 * @Description:车辆的评级类别
 * @date 2018-03-07
 */
public class CarGradeType implements Serializable {
    private static final long serialVersionUID = 2257113257639583336L;

    //类型 1综合车况 2整备成本
    private String type;
    //级别名称
    private String typeName;
    //评级内容
    private List<CarGradeRule> carGradeRules;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<CarGradeRule> getCarGradeRules() {
        return carGradeRules;
    }

    public void setCarGradeRules(List<CarGradeRule> carGradeRules) {
        this.carGradeRules = carGradeRules;
    }
}
