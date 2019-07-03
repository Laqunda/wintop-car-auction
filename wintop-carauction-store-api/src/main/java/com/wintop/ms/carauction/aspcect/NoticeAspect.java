package com.wintop.ms.carauction.aspcect;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.Notify;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import com.wintop.ms.carauction.util.utils.RequestArgsUtil;
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

    @Autowired
    private RedisManagerTemplate redisManagerTemplate;

    @Around( "@annotation(com.wintop.ms.carauction.core.annotation.InfoNotify)" )
    public Object getAround(ProceedingJoinPoint joinPoint) throws Exception {
        String clazzName = joinPoint.getTarget().getClass().getSimpleName();
        String clazzFullName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info(String.format("print className=%s,methodName=%s",  clazzName, methodName));
        //获取参数名称和值
        Map<String,Object> nameAndArgs = RequestArgsUtil.getFieldsName(this.getClass(), clazzFullName, methodName,args);
        JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(nameAndArgs));
        Long managerId = null;
        if (object.containsKey("managerId")) {
            managerId = object.getLong("managerId");
        }
        if (object.containsKey("userId")){
            managerId = object.getLong("userId");
        }
        if (object.containsKey("managerUser")) {
            managerId = object.getJSONObject("managerUser").getLong("id");
        }
        logger.info(String.format("print managerId=%s,className=%s,methodName=%s", managerId, clazzName, methodName));
        String key = String.format("%s.%s", clazzName, methodName);
        ResultModel resultModel = null;
        try {
            resultModel = (ResultModel) joinPoint.proceed();
            // 返回错误，则不需要通知
            if (resultModel.getResultCode() != 100) {
                return resultModel;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            // 运行过程中失败
            return resultModel;
        }

        Notify notify = getNotify(managerId);
        if (Objects.isNull(notify)) {
            notify = new Notify();
        }
        // 新增评估 - 已评估
        if ("CarAssessApi.addSave".equals(key)) {
            notify.getAssess().setCarAssessAdd(notify.getAssess().getCarAssessAdd() + 1);
        // 申请采购 - 已申请收购
        } else if ("CarAssessOrderApi.addSave".equals(key)) {
            notify = setAlreadyAssess(managerId, notify);
        } else if ("CarAssessOrderApi.editStatus".equals(key)) {
            Map map = object.getObject("map", Map.class);
            // 采购驳回 -- 已申请收购
            if (map.containsKey("status") && Objects.equals(map.get("status").toString(), REJECT)) {
                notify = setAlreadyAssess(managerId, notify);
            // 采购通过 -- 车辆库存
            }else if (map.containsKey("status") && Objects.equals(map.get("status").toString(), PASS)) {
                notify.getStock().setCarStockCount(notify.getStock().getCarStockCount() + 1);
            }
            // 库存管理
        } else if ("AutoManagerApi.initAuto".equals(key)) {
            String auctionType = object.getString("auctionType");
            // 创建在线车辆
            if (Objects.nonNull(auctionType) && Objects.equals(auctionType, ONLINE)) {
                notify.getStock().setInsertOnlineCar(notify.getStock().getInsertOnlineCar() + 1);
            // 创建线下车辆
            } else if (Objects.nonNull(auctionType) && Objects.equals(auctionType, UNDERLINE)) {
                notify.getStock().setInsertOnSiteCar(notify.getStock().getInsertOnSiteCar() + 1);
            }
         // 订单管理 -- 线上订单
        }else if ("BargainingAuditApi.insertBargainingAuditSure".equals(key)) {
           notify.getOrder().setInsertOnlineOrder(notify.getOrder().getInsertOnlineOrder() + 1);
        // 订单管理 -- 零售订单
        }  else if ("CarOrderRetailApi.addSave".equals(key)) {
            notify.getOrder().setInsertRetailOrder(notify.getOrder().getInsertRetailOrder() + 1);

        }
        setRedis(managerId, notify);
        return resultModel;
    }

    /**
     * 已申请收购
     * @param managerId
     * @param notify
     */
    private Notify setAlreadyAssess(Long managerId, Notify notify) {
        notify.getAssess().setCarAssessOrderAdd(notify.getAssess().getCarAssessOrderAdd() + 1);
        return notify;
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
        String json = redisManagerTemplate.get(managerId.toString());
        if (Objects.isNull(json)) {
            return null;
        }
        return JSONObject.toJavaObject(JSONObject.parseObject(json), Notify.class);
    }


}
