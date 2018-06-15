package com.wintop.ms.carauction.core.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

public class PageEntity implements Serializable{
    private static final long serialVersionUID = -1834792677282504192L;
    private int startRowNum;
    private int endRowNum;

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    /**
     * 添加接收分页参数，page,count
     * @param paramMap
     * @param obj
     * @return
     */
    @Deprecated
    public Map<String,Object> putPageParam(Map<String,Object> paramMap, JSONObject obj){
        int page = 1;
        int count = 10;
        if(obj!=null && StringUtils.isNotEmpty(obj.getString("page"))){
            page = obj.getIntValue("page");
        }
        if(obj!=null && StringUtils.isNotEmpty(obj.getString("count"))){
            count = obj.getIntValue("count");
        }
        int startRowNum = (page-1)*count;
        paramMap.put("startRowNum",startRowNum);
        paramMap.put("endRowNum",count);
        return paramMap;
    }

}
