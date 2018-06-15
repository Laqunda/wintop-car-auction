package com.wintop.ms.carauction.core.model;


import com.wintop.ms.carauction.core.config.ResultStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 自定义返回结果
 * @author XieEnlong
 * @date 2015/7/14.
 */
public class ResultModel implements Serializable{

    private static final long serialVersionUID = 8841481017013995726L;
    /***
     * 接口是否成功
     */
    private boolean success;

    /**
     * 返回码
     */
    private int resultCode;

    /**
     * 返回结果描述
     */
    private String resultMsg;

    /**
     * 返回内容
     */
    private Object data;

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public Object getData() {
        return data;
    }
    public boolean getSuccess(){
        return success;
    }

    public ResultModel() {}

    public ResultModel(boolean success,int code, String message) {
        this.success = success;
        this.resultCode = code;
        this.resultMsg = message;
        this.data = new Object();
    }
    /**
     * 给出状态码不满足时，自定义使用
     * @param code      状态码
     * @param message   状态描述
     * @param data   业务结果内容
     * */
    public ResultModel(boolean success,int code, String message, Object data) {
        this.success = success;
        this.resultCode = code;
        this.resultMsg = message;
        //为防止返回客户端“”或null 如果，为空则返回一个空HashMap对象
        this.data = data==null||data==""?new HashMap<>():data;
    }

    public ResultModel(boolean success,ResultStatus status) {
        this.success = success;
        this.resultCode = status.getCode();
        this.resultMsg = status.getMessage();
        this.data = new HashMap<>();
    }

    public ResultModel(boolean success, ResultStatus status, Object content) {
        this.success = success;
        this.resultCode = status.getCode();
        this.resultMsg = status.getMessage();
        //为防止返回客户端“”或null 如果，为空则返回一个空HashMap对象
        this.data = content==null||content==""?new HashMap<>():content;
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(true,ResultStatus.SUCCESS, content);
    }

    public static ResultModel ok(ResultStatus status,Object content) {
        return new ResultModel(true,status, content);
    }

    public static ResultModel ok() {
        return new ResultModel(true,ResultStatus.SUCCESS);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(false,error);
    }
}
