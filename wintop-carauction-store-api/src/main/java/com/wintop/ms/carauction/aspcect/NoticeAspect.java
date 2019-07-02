package com.wintop.ms.carauction.aspcect;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.Notify;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
public class NoticeAspect {
    // logger 日志信息
    private static final Logger logger = LoggerFactory.getLogger(NoticeAspect.class);
    private static final String REJECT = "-1";
    private static final String UNDERLINE = "2";
    private static final String PASS = "2";
    private static final String ONLINE = "1";

    private static List<String> methodInfoList = new ArrayList(){{
        add("CarAssessApi.addSave");
        add("CarAssessOrderApi.addSave");
        add("CarAssessOrderApi.editStatus");
        add("AutoManagerApi.initAuto");
        add("CarChaboshiLogApi.vinSearch");
        add("CarBargainingAuditApi.carBargaining");
    }};

    @Autowired
    private RedisManagerTemplate redisManagerTemplate;

    @Around( "@annotation(com.wintop.ms.carauction.core.annotation.InfoNotify)" )
    public void before(ProceedingJoinPoint joinPoint) throws Exception {
        String clazzName = joinPoint.getTarget().getClass().getSimpleName();
        String clazzFullName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //获取参数名称和值
        Map<String,Object > nameAndArgs = this.getFieldsName(this.getClass(), clazzFullName, methodName,args);
        JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(nameAndArgs));
        Long managerId = null;
        if (object.containsKey("managerId")) {
            managerId = object.getLong("managerId");
        }
        if (object.containsKey("userId")){
            managerId = object.getLong("userId");
        }


        logger.info(String.format("print managerId=%s,className=%s,methodName=%s", managerId, clazzName, methodName));
        String key = String.format("%s.%s", clazzName, methodName);
        ResultModel resultModel = null;
        try {
            resultModel = (ResultModel) joinPoint.proceed();
            // 返回错误，则不需要通知
            if (resultModel.getResultCode() != 100) {
                return;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            // 运行过程中失败
            return;
        }
        // 新增评估
        if (key.equals("CarAssessApi.addSave")) {
            Notify notify = new Notify();
            if (Objects.isNull(redisManagerTemplate.get(managerId.toString()))) {
                notify.getAssess().setCarAssessAdd(notify.getAssess().getCarAssessAdd() + 1);
                setRedis(managerId, notify);
            } else {
                notify = getNotify(managerId);
                notify.getAssess().setCarAssessAdd(notify.getAssess().getCarAssessAdd() + 1);
                setRedis(managerId, notify);
            }
        // 申请采购
        } else if (key.equals("CarAssessOrderApi.addSave")) {
            Notify notify = new Notify();
            if (Objects.isNull(redisManagerTemplate.get(managerId.toString()))) {
                notify.getAssess().setCarAssessOrderAdd(notify.getAssess().getCarAssessOrderAdd() + 1);
                setRedis(managerId, notify);
            } else {
                notify = getNotify(managerId);
                notify.getAssess().setCarAssessAdd(notify.getAssess().getCarAssessAdd() + 1);
                setRedis(managerId, notify);
            }
        } else if (key.equals("CarAssessOrderApi.editStatus")) {
            Map map = object.getObject("map", Map.class);
            // 采购驳回
            if (map.containsKey("status") && Objects.equals(map.get("status").toString(), REJECT)) {
                Notify notify = new Notify();
                if (Objects.isNull(redisManagerTemplate.get(managerId.toString())) ) {
                    notify.getAssess().setCarAssessReject(notify.getAssess().getCarAssessReject() + 1);
                    setRedis(managerId, notify);
                } else {
                    notify = getNotify(managerId);
                    notify.getAssess().setCarAssessReject(notify.getAssess().getCarAssessReject() + 1);
                    setRedis(managerId, notify);
                }
            // 采购通过
            }else if (map.containsKey("status") && Objects.equals(map.get("status").toString(), PASS)) {
                Notify notify = new Notify();
                if (Objects.isNull(redisManagerTemplate.get(managerId.toString())) ) {
                    notify.getAssess().setCarAssessPass(notify.getAssess().getCarAssessPass() + 1);
                    setRedis(managerId, notify);
                } else {
                    notify = getNotify(managerId);
                    notify.getAssess().setCarAssessPass(notify.getAssess().getCarAssessPass() + 1);
                    setRedis(managerId, notify);
                }
            // 库存管理
            } else if (key.equals("AutoManagerApi.initAuto")) {
                Notify notify = new Notify();
                String auctionType = object.getString("auctionType");
                // 创建在线车辆
                if (Objects.nonNull(auctionType) && Objects.equals(auctionType, ONLINE)) {
                    if (Objects.isNull(redisManagerTemplate.get(managerId.toString()))) {
                        notify.getStock().setInsertOnlineCar(notify.getStock().getInsertOnlineCar() + 1);
                        setRedis(managerId, notify);
                    } else {
                        notify = getNotify(managerId);
                        notify.getStock().setInsertOnlineCar(notify.getStock().getInsertOnlineCar() + 1);
                        setRedis(managerId, notify);
                    }
                // 创建线下车辆
                } else if (Objects.nonNull(auctionType) && Objects.equals(auctionType, UNDERLINE)) {
                    if (Objects.isNull(redisManagerTemplate.get(managerId.toString()))) {
                        notify.getStock().setInsertOnSiteCar(notify.getStock().getInsertOnSiteCar() + 1);
                        setRedis(managerId, notify);
                    } else {
                        notify = getNotify(managerId);
                        notify.getStock().setInsertOnSiteCar(notify.getStock().getInsertOnSiteCar() + 1);
                        setRedis(managerId, notify);
                    }
                }
            // 订单管理 - 个人车源
            } else if (key.equals("CarChaboshiLogApi.vinSearch")) {
                Notify notify = new Notify();
                managerId = object.getJSONObject("managerUser").getLong("id");
                if (Objects.isNull(redisManagerTemplate.get(managerId.toString()))) {
                    notify.setCbs(notify.getCbs() + 1);
                    setRedis(managerId, notify);
                } else {
                    notify = getNotify(managerId);
                    notify.setCbs(notify.getCbs() + 1);
                    setRedis(managerId, notify);
                }
            // 维修记录
            } else if (key.equals("CarBargainingAuditApi.carBargaining")) {
                Notify notify = new Notify();
                if (Objects.isNull(redisManagerTemplate.get(managerId.toString())) ) {
                    notify.getOrder().setInsertPersonOrder(notify.getOrder().getInsertPersonOrder() + 1);
                    setRedis(managerId, notify);
                } else {
                    notify = getNotify(managerId);
                    notify.getOrder().setInsertPersonOrder(notify.getOrder().getInsertPersonOrder() + 1);
                    setRedis(managerId, notify);
                }
            }
        }
    }

    /**
     * @Description 获取字段名和字段值
     * @return Map<String,Object>
     */
    private Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {
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


    /**
     * 个人数据-保存到redis 中
      */
    private void setRedis(Long managerId, Notify notify) {
        redisManagerTemplate.update(managerId.toString(), JSONObject.toJSONString(notify));
    }

    /**
     * 获取 redis 中的-个人数据
      */
    private Notify getNotify(Long managerId) {
        return JSONObject.toJavaObject(JSONObject.parseObject(redisManagerTemplate.get(managerId.toString())), Notify.class);
    }


}
