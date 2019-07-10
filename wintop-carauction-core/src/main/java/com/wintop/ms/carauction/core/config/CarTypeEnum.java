package com.wintop.ms.carauction.core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 车辆相类型
 */
public enum CarTypeEnum {
    AUCTION_TYPE_WAIT("-1","待划分"),
    AUCTION_TYPE_ONLINE("1","线上"),
    AUCTION_TYPE_SCENE("2","现场"),
    AUCTION_TYPE_RETAIL("3","零售"),
    TRANSFER_FLAG_0("0","未转渠道"),
    TRANSFER_FLAG_1("1","线上拍转现场拍"),
    TRANSFER_FLAG_2("2","现场拍转线上拍"),
    TRANSFER_FLAG_3("3","线上拍转零售"),
    TRANSFER_FLAG_4("4","现场拍转零售"),
    TRANSFER_FLAG_5("5","零售转线上拍"),
    TRANSFER_FLAG_6("6","零售转现场拍"),
    SALE_FLAG_SELL_WHOLESALE("0","批售"),
    SALE_FLAG_RETAIL("1","零售");

    private final String value;
    private final String remark;

    private CarTypeEnum(String value, String remark){
        this.value=value;
        this.remark=remark;
    }

    public final static Map<String,String> auctionType = new HashMap<String, String>();
    static {
        auctionType.put(CarTypeEnum.AUCTION_TYPE_WAIT.value, CarTypeEnum.AUCTION_TYPE_WAIT.getRemark());
        auctionType.put(CarTypeEnum.AUCTION_TYPE_ONLINE.value, CarTypeEnum.AUCTION_TYPE_ONLINE.getRemark());
        auctionType.put(CarTypeEnum.AUCTION_TYPE_SCENE.value, CarTypeEnum.AUCTION_TYPE_SCENE.getRemark());
        auctionType.put(CarTypeEnum.AUCTION_TYPE_RETAIL.value, CarTypeEnum.AUCTION_TYPE_RETAIL.getRemark());
    }

    public final static Map<String,String> transferRlag = new HashMap<String, String>();
    static {
        transferRlag.put(CarTypeEnum.TRANSFER_FLAG_0.value, CarTypeEnum.TRANSFER_FLAG_0.getRemark());
        transferRlag.put(CarTypeEnum.TRANSFER_FLAG_1.value, CarTypeEnum.TRANSFER_FLAG_1.getRemark());
        transferRlag.put(CarTypeEnum.TRANSFER_FLAG_2.value, CarTypeEnum.TRANSFER_FLAG_2.getRemark());
        transferRlag.put(CarTypeEnum.TRANSFER_FLAG_3.value, CarTypeEnum.TRANSFER_FLAG_3.getRemark());
        transferRlag.put(CarTypeEnum.TRANSFER_FLAG_4.value, CarTypeEnum.TRANSFER_FLAG_4.getRemark());
        transferRlag.put(CarTypeEnum.TRANSFER_FLAG_5.value, CarTypeEnum.TRANSFER_FLAG_5.getRemark());
        transferRlag.put(CarTypeEnum.TRANSFER_FLAG_6.value, CarTypeEnum.TRANSFER_FLAG_6.getRemark());
    }
    public String value(){
        return this.value;
    }

    public String getRemark(){
        return this.remark;
    }

}
