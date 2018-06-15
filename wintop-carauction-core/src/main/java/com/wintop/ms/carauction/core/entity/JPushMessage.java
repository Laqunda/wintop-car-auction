package com.wintop.ms.carauction.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/7.
 * JPush推送消息对象封装
 */
public class JPushMessage implements Serializable {
    public String pushType;//推送方式：1广播、2指定tag分组的人、3指定别名的人
    public String pushClientType;//1全部设备、2android、3iOS
    public String[] aliasArr;//设备别名id
    public String[] tagArr;//用户标签
    public String title;//消息标题
    public String content;//消息内容
    public Map<String, String> extras;//附加信息
    /**
     * 1\广播方式，userIdArr、tagArr传null即可
     * //   推送内容对象id||网址
     *      推送内容对象类型：1系统消息、2车辆、3网页地址、4订单、5拍卖场次、6推送车辆动态
     * */
    public JPushMessage(String pushType,
                 String pushClientType,
                 String[] aliasArr,
                 String[] tagArr,
                 String title,
                 String content,
                 String openObjId,
                 String openObjType,
                 String reserved){
        this.pushType = pushType;
        this.pushClientType = pushClientType;
        this.aliasArr = aliasArr;
        this.tagArr = tagArr;
        this.title = title;
        this.content = content;
        extras = new HashMap<>();
        extras.put("openObjId",openObjId);
        extras.put("openObjType",openObjType);
        extras.put("reserved",reserved);
    }
}
