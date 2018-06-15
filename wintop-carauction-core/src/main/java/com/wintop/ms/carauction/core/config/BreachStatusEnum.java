package com.wintop.ms.carauction.core.config;

import com.wintop.ms.carauction.core.entity.BreachStatus;

import java.util.ArrayList;
import java.util.List;

public enum BreachStatusEnum {
    //BREACH_HANDING("1","1","争议处理中"),
    FLAT_BACK("1","2","平退"),
    BUYER_BREACH_PAY("1","3","买家违约-需支付违约金"),
    BUYER_BREACH_NO_PAY("1","4","买家违约-不需支付违约金"),
    MODIFY_PAY_FEE("1","5","争议议价-修改待付车款"),
    SELLER_COMPENSATE("1","6","卖家赔付"),
    BUYER_BREACH("2","1","买家违约"),
    SELLER_BREACH("2","2","卖家违约"),
    CAR_AUTO_STATE("2","3","车辆状况类问题"),
    PROCEDURE_TYPE("2","4","手续类问题");

    private final String type;
    private final String value;
    private final String remark;

    private BreachStatusEnum(String type,String value, String remark){
        this.type=type;
        this.value=value;
        this.remark=remark;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public String getStrValue(){
        return String.valueOf(this.value);
    }

    public String getRemark(){
        return this.remark;
    }

    public static List<BreachStatus> getBreachStatusList(String type){
        List<BreachStatus> list = new ArrayList<>();
        for(BreachStatusEnum statusEnum:BreachStatusEnum.values()){
            if(type.equals(statusEnum.getType())){
                list.add(new BreachStatus(statusEnum.getValue(),statusEnum.getRemark()));
            }
        }
        return list;
    }

    public static String getDetailRemark(String type,String value){
        for(BreachStatusEnum statusEnum:BreachStatusEnum.values()){
            if(type.equals(statusEnum.getType()) && value.equals(statusEnum.getValue())){
                return statusEnum.getRemark();
            }
        }
        return "";
    }

}
