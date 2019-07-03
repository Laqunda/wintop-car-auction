package com.wintop.ms.carauction.aspcect;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.Notify;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import com.wintop.ms.carauction.util.utils.RequestArgsUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class NoticeCleanAspect {

    // logger 日志信息
    private static final Logger logger = LoggerFactory.getLogger(NoticeCleanAspect.class);
    private static final String RETAIL = "-1";
    private static final int CLEAN_FLAG = -1;
    private static final String ONLINE = "online";
    private static final String ONSITE = "onsite";

    @Autowired
    private RedisManagerTemplate redisManagerTemplate;

    @Around( "@annotation(com.wintop.ms.carauction.core.annotation.InfoCleanNotify)" )
    public Object getCleanAround(ProceedingJoinPoint joinPoint) throws Exception {
        String clazzName = joinPoint.getTarget().getClass().getSimpleName();
        String clazzFullName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //获取参数名称和值
        Map<String, Object> nameAndArgs = RequestArgsUtil.getFieldsName(this.getClass(), clazzFullName, methodName, args);
        JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(nameAndArgs));
        Long managerId = null;
        if (object.containsKey("managerId")) {
            managerId = object.getLong("managerId");
        }
        if (object.containsKey("userId")) {
            managerId = object.getLong("userId");
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
        if (Objects.isNull(notify)){
            return resultModel;
        }
        // 清除 已申请收购 已评估
        if ("CarAssessApi.list".equals(key)) {
            notify.getAssess().setCarAssessAdd(CLEAN_FLAG);
            notify.getAssess().setCarAssessOrderAdd(CLEAN_FLAG);
        } else if ("StockStatisticsApi.stockInfo".equals(key)) {
            Map map = object.getObject("map", Map.class);
            // 采购驳回 -- 已申请收购
            if (map.containsKey("type") && Objects.equals(map.get("type").toString(), ONLINE)) {
                notify.getStock().setInsertOnlineCar(CLEAN_FLAG);
                // 采购通过 -- 车辆库存
            } else if (map.containsKey("type") && Objects.equals(map.get("type").toString(), ONSITE)) {
                notify.getStock().setInsertOnSiteCar(CLEAN_FLAG);
            }
        // 库存管理
        } else if ("CarAutoApi.selectCarList".equals(key)) {
            Map map = object.getObject("map", Map.class);
            String type = map.get("type").toString();
            if (Objects.nonNull(type) && Objects.equals(type, RETAIL)) {
                notify.getStock().setCarStockCount(CLEAN_FLAG);
            }
        } else if ("CarOrderApi.selectHistoryCarList".equals(key)) {
            notify.getOrder().setInsertOnlineOrder(CLEAN_FLAG);
        } else if ("CarSaleOrderListApi.getCarSaleOrderRetailList".equals(key)) {
            notify.getOrder().setInsertRetailOrder(CLEAN_FLAG);
        } else if ("CarChaboshiLogApi.list".equals(key)) {
            notify.setCbs(CLEAN_FLAG);
        }
        setRedis(managerId, notify);
        return resultModel;
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
