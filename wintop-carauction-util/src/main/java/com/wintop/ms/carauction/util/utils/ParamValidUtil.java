package com.wintop.ms.carauction.util.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class ParamValidUtil {
    public static boolean invalidMapParam(Map<String,Object> map,String param){
        if(map.get(param)==null || map.get(param).toString().contains("null") || StringUtils.isBlank(map.get(param).toString())){
            return true;
        }
        return false;
    }

}
