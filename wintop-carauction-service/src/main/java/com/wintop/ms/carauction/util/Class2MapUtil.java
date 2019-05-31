package com.wintop.ms.carauction.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class Class2MapUtil<T> {
    private static Gson gson =new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .enableComplexMapKeySerialization().create();
    public static <T> Map convertMap(T t) {
        Object json=gson.toJson(t,new TypeToken<Object>() {}.getType());
        return gson.fromJson(json.toString(),new TypeToken<Map<String,Object>>() {
        }.getType());
    }

    public static <T> T convertBean(Map map,Class clazz) {
        String json=gson.toJson(map,new TypeToken<T>() {
        }.getType());
        return (T) gson.fromJson(json,  clazz);
    }

}
