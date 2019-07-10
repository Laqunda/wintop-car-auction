package com.wintop.ms.carauction.util.utils;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.util.HashMap;
import java.util.Map;

public class RequestArgsUtil {

    /**
     * @Description 获取字段名和字段值
     * @return Map<String,Object>
     */
    public static Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {
        Map<String,Object > map=new HashMap<String,Object>();


        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        //paramNames即参数名
        for (int i = 0; i < cm.getParameterTypes().length; i++){
            map.put(attr.variableName(i + pos),args[i]);
        }
        return map;
    }
}
