package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.Notify;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarChaboshiLogService;
import com.wintop.ms.carauction.util.AlipayUtil;
import com.wintop.ms.carauction.util.ChaboshiUtils;
import com.wintop.ms.carauction.util.Class2MapUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 查博士日志 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-08
 */
@Service
public class CarChaboshiLogServiceImpl implements ICarChaboshiLogService {
    @Autowired
    private CarChaboshiLogModel model;
    @Autowired
    private CarChaboshiStoreAccountModel storeAccountModel;
    @Autowired
    private CarFinancePayLogModel financePayLogModel;
    @Autowired
    private AppUserModel appUserModel;
    @Autowired
    private CarChaboshiVinDataModel carChaboshiVinDataModel;
    @Autowired
    private CarChaboshiPaymentConfModel carChaboshiPaymentConfModel;
    @Autowired
    private CarManagerUserModel carManagerUserModel;
    @Autowired
    private CarStoreModel carStoreModel;
    @Autowired
    private CarChaboshiStoreConfModel storeConfModel;

    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private RedisManagerTemplate redisManagerTemplate;

    /**
     * 查询查博士日志信息
     *
     * @param id 查博士日志ID
     * @return 查博士日志信息
     */
    @Override
    public CarChaboshiLog selectCarChaboshiLogById(Long id) {
        return model.selectCarChaboshiLogById(id);
    }

    /**
     * 查询查博士日志列表
     *
     * @param map 查博士日志信息
     * @return 查博士日志集合
     */
    @Override
    public List<CarChaboshiLog> selectCarChaboshiLogList(Map<String, Object> map) {
        List<CarChaboshiLog> logList = model.selectCarChaboshiLogList(map);
        for (CarChaboshiLog log : logList) {

            if (log.getUserType().equals("1")) {
                WtAppUser wtAppUser = appUserModel.findById(log.getUserId());
                log.setWtAppUser(wtAppUser);
            } else {
                CarManagerUser carManagerUser = carManagerUserModel.selectByPrimaryKey(log.getUserId());
                log.setCarManagerUser(carManagerUser);
                CarStore carStore = carStoreModel.selectByPrimaryKey(log.getStoreId());
                log.setCarStore(carStore);
            }

            List<CarChaboshiVinData> vinDataList = carChaboshiVinDataModel.selectByCondition(Collections.singletonMap("vin", log.getVin()));
            if (CollectionUtils.isNotEmpty(vinDataList)) {
                CarChaboshiVinData carChaboshiVinData = vinDataList.stream().findFirst().orElse(new CarChaboshiVinData());
                log.setCarChaboshiVinData(carChaboshiVinData);
            }
            List<CarChaboshiPaymentConf> confList = carChaboshiPaymentConfModel.selectCarChaboshiPaymentConfList(new CarChaboshiPaymentConf());
            if (CollectionUtils.isNotEmpty(confList)) {
                CarChaboshiPaymentConf carChaboshiPaymentConf = confList.stream().findFirst().orElse(new CarChaboshiPaymentConf());
                log.setCarChaboshiPaymentConf(carChaboshiPaymentConf);
            }

        }
        return logList;
    }

    /**
     * 新增查博士日志
     *
     * @param carChaboshiLog 查博士日志信息
     * @return 结果
     */
    @Override
    public int insertCarChaboshiLog(CarChaboshiLog carChaboshiLog) {
        return model.insertCarChaboshiLog(carChaboshiLog);
    }

    /**
     * 修改查博士日志
     *
     * @param map 查博士日志信息
     * @return 结果
     */
    @Override
    public int updateCarChaboshiLog(Map<String, Object> map) {
        return model.updateCarChaboshiLog(map);
    }

    /**
     * 删除查博士日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarChaboshiLogByIds(String ids) {
        return model.deleteCarChaboshiLogByIds(ids);
    }

    @Override
    public int selectCount(Map<String, Object> map) {
        return model.selectCount(map);
    }

    /**
     * 根据查博士订单id获取查询日志
     *
     * @param map
     * @return
     */
    @Override
    public CarChaboshiLog selectCarChaboshiLog(Map<String, Object> map) {
        List<CarChaboshiLog> logs = selectCarChaboshiLogList(map);
        if (logs != null && logs.size() > 0) {
            return logs.get(0);
        }
        return null;
    }

    /**
     * 个人资金流水
     *
     * @param payLog
     */
    @Override
    public int savePayLog(CarFinancePayLog payLog) {
        payLog.setId(idWorker.nextId());
        payLog.setStatus("3");//退款
        payLog.setCreateTime(new Date());
        return financePayLogModel.insert(payLog);
    }

    @Override
    public int recentPayedConut(Map<String, Object> map) {
        return model.recentPayedConut(map);
    }

    @Override
    public List<CarChaboshiLog> recentPayedList(Map<String, Object> map) {
        return model.recentPayedList(map);
    }

    @Override
    public void updateIsOpen(Map map) {
        model.updateIsOpen(map);
    }

    /*******************************************************************************************
     ************************************买家查询************************************************
     *******************************************************************************************/

    /**
     * 买家查询（支付成功后 开始查询）
     *
     * @param obj
     * @return
     */
    @Override
    @Transactional
    public ServiceResult vinSearchForBuyer(JSONObject obj) {
        ServiceResult result = new ServiceResult();
        //写入支付日志表
        CarFinancePayLog payLog = new CarFinancePayLog();
        Long payLogId = idWorker.nextId();
        payLog.setId(payLogId);
        payLog.setCreatePersonType(obj.getString("userType"));
        payLog.setCreatePerson(obj.getLong("userId"));
        payLog.setCreateTime(new Date());
        payLog.setOrderNo(RandCodeUtil.getOrderNumber());
        payLog.setPayTime(new Date());
        payLog.setStatus("1");
        payLog.setType("4");//查博士
        payLog.setBankOrderNo(obj.getString("bankOrderNo"));
        payLog.setBankOrderLog(obj.getString("bankOrderLog"));
        payLog.setPayType(obj.getString("payType"));
        payLog.setPayWay(obj.getString("payWay"));
        payLog.setLogNo(obj.getString("payLogNo"));
        payLog.setPayFee(obj.getBigDecimal("money"));
        payLog.setRemark(obj.getString("passbackParams"));
        payLog.setUserId(obj.getLong("userId"));


        //保存支付日志

        financePayLogModel.insert(payLog);

        //写入查询日志
        Long logId = idWorker.nextId();
        CarChaboshiLog log = new CarChaboshiLog();

        log.setId(logId);
        log.setPayLogId(payLogId);
        log.setUserId(obj.getLong("userId"));
        log.setEdition(obj.getString("edition"));
        log.setUserType(obj.getString("userType"));
        log.setCreateTime(new Date());
        log.setVin(obj.getString("vin"));
        log.setSourceType("1");//查博士
        log.setMoney(obj.getBigDecimal("money"));
        log.setUserName(obj.getString("userName"));
        log.setStoreId(obj.getLong("storeId"));
        log.setResponseResult("3");//查询中

        /*车型信息*/
        log.setVehicleId(obj.getLong("vehicleId"));
        log.setVehicleType(obj.getString("vehicleType"));
        log.setPhoto(obj.getString("photo"));
        log.setEngineNum(obj.getString("engineNum"));

        insertCarChaboshiLog(log);

        /*去查询 并更新查博士日志*/
        result = searchForCustomer(obj.getLong("userId"), obj.getString("userName"), obj.getString("edition"), logId, obj.getString("vin"));

        return result;
    }

    /**
     * 个人用户查询 其中需要验证
     * 查博士支付的金额配置
     * 是否已经支付该查询的费用--在cahboshilog中存在记录
     *
     * @param userId
     * @param edition
     * @return
     */
    private ServiceResult<Map<String, Object>> searchForCustomer(Long userId, String userName, String edition, Long logId, String vin) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        /*查找查博士log*/
        CarChaboshiLog log = selectCarChaboshiLogById(logId);
       /*
        查询结果 1查询成功，2查询失败，3，查询中
        类型：1个人，2店铺
        条件：钱必须支付，必须是个人用户，状态必须是查询中
        */
        if (log != null && log.getMoney() != null && "1".equals(log.getUserType()) && "3".equals(log.getResponseResult())) {
            result = chaboshi(userId, userName, edition, logId, vin);
        } else {
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.NO_ORDER.getRemark());
        }
        return result;
    }

    /**
     * 买家查询
     *
     * @param userId
     * @param userName
     * @param edition
     * @param logId
     * @param vin
     * @return
     */
    @Override
    public ServiceResult<Map<String, Object>> chaboshi(Long userId, String userName, String edition, Long logId, String vin) {
        ServiceResult result = new ServiceResult();
        result.setSuccess("0", ResultCode.SUCCESS.getRemark());
        Map data = new HashMap();
        JSONObject object = null;
        object = cha(edition, vin, result);

        CarChaboshiLog log = selectCarChaboshiLogById(logId);
        boolean isSuccess = false;
        if (object != null) {
            if ("0".equals(object.get("Code"))) {
                /*查询成功*/
                data.put("orderId", object.getString("orderId"));
                result.setResult(data);
                log.setOrderId(object.getString("orderId"));
                log.setOrderMsg(object.getString("Message"));
                log.setResponseResult("3");//查询中
                //数据来自数据库
                if("2".equals(object.get("sourceType"))){
                    log.setResponseResult("1");//查询成功
                    log.setResponseMsg(object.getString("responseMsg"));
                    log.setOrderId(object.getString("orderId"));
                    log.setOrderMsg(object.getString("orderMsg"));
                    log.setFinishTime(object.getDate("finishTime"));
                    log.setSourceType(object.getString("sourceType"));
                    log.setVehicleType(object.getString("vehicleType"));
                    log.setPc_url(object.getString("pc_url"));
                    log.setApp_url(object.getString("app_url"));
                }
                isSuccess = true;
            } else {
                /*查询失败*/
                log.setOrderMsg(object.getString("Message"));
                log.setResponseResult("2");
            }
        } else {
            log.setResponseResult("2");
        }
        /*更新日志*/
        Map<String, Object> param = Class2MapUtil.convertMap(log);
        int code = updateCarChaboshiLog(param);
        if (!isSuccess) {
            /*退款到个人支付宝*/
            CarFinancePayLog payLog = financePayLogModel.selectById(log.getPayLogId());
            Map map = AlipayUtil.refundOrder(payLog);
            if ("0".equals(map.get("code"))) {
                /*退款成功*/
                int c = savePayLog(payLog);
            } else {
                /*退款失败*/
                Map m = new HashMap();
                m.put("msg", "退款失败");
                result.setResult(m);
            }
            //TODO 买家退款失败 --  状态提醒
        }
        return result;
    }

    /*******************************************************************************************
     ************************************店铺查询************************************************
     *******************************************************************************************/

    /**
     * 卖家查询 需要验证 查博士支付的金额配置 以及余额是否足够
     *
     * @param obj
     * @return
     */
    @Override
    @Transactional
    public ServiceResult<Map<String, Object>> searchForStore(JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        //查找store信息
        CarChaboshiStoreConf storeConf = new CarChaboshiStoreConf();
        storeConf.setStoreId(obj.getLong("storeId"));
        CarChaboshiStoreConf storeConfDao = storeConfModel.selectCarChaboshiStoreConfByParams(storeConf);
        if (storeConfDao != null) {
            /*查找店铺配置金额*/
            BigDecimal balance = storeConfDao.getBalance();
            BigDecimal payment = storeConfDao.getPayment();
            BigDecimal paymentComposite = storeConfDao.getPaymentComposite();
            /*没有设置支付金额 则返回错误*/
            if (payment == null || paymentComposite == null) {
                result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
                return result;
            }
            if ("1".equals(obj.get("edition"))) {
                /*维修版本*/
                if (balance == null || balance.compareTo(payment) == -1) {
                    result.setError(ResultCode.FAIL.strValue(), ResultCode.LOW_BALANCE.getRemark());
                } else {
                    /* 查询--支付--生成查询记录*/
                    result = chaboshiStore(payment, obj);
                }
            } else if ("2".equals(obj.get("edition"))) {
                /*综合版本*/
                if (balance == null || balance.compareTo(paymentComposite) == -1) {
                    result.setError(ResultCode.FAIL.strValue(), ResultCode.LOW_BALANCE.getRemark());
                } else {
                    /* 查询--支付--生成查询记录*/
                    result = chaboshiStore(payment, obj);
                }
            } else {
                result.setError(ResultCode.FAIL.strValue(), ResultCode.NO_PARAM.getRemark());
            }


        } else {
            result.setError(ResultCode.FAIL.strValue(), ResultCode.NO_OBJECT.getRemark());
        }
        return result;
    }

    /**
     * 店铺的查博士查询
     *
     * @param payment
     * @return
     */
    @Override
    public ServiceResult<Map<String, Object>> chaboshiStore(BigDecimal payment, JSONObject obj) {

        String edition = obj.getString("edition");
        String vin = obj.getString("vin");
        Long storeId = obj.getLong("storeId");
        Long userId = obj.getLong("userId");
        String userName = obj.getString("userName");

        ServiceResult result = new ServiceResult();
        Map data = new HashMap();

        //校验账户余额
        CarChaboshiStoreAccount account = new CarChaboshiStoreAccount();
        account.setStoreId(storeId);
        List<CarChaboshiStoreAccount> accounts = storeAccountModel.selectCarChaboshiStoreAccountList(account);
        if (accounts != null && accounts.size() > 0) {
            CarChaboshiStoreAccount c = accounts.get(0);
            c.setBalance(c.getBalance().subtract(payment));
            /*小于零 余额不足*/
            if (c.getBalance().compareTo(BigDecimal.ZERO) == -1) {
                result.setError(ResultCode.FAIL.strValue(), "余额不足！");
                return result;
            }
        } else {
            /*没有找到流水记录*/
            result.setError(ResultCode.FAIL.strValue(), "没有找到流水记录");
            return result;
        }
        JSONObject object = cha(edition, vin, result);
        /*log 创建存储*/
        CarChaboshiLog log = new CarChaboshiLog();
        log.setId(idWorker.nextId());
        log.setStoreId(storeId);
        log.setUserName(userName);
        log.setUserId(userId);
        log.setEdition(edition);
        log.setMoney(payment);
        log.setSourceType("1");
        log.setVin(vin);
        log.setUserType("2");
        log.setCreateTime(new Date());

        /*车型信息*/
        log.setVehicleId(obj.getLong("vehicleId"));
        log.setVehicleType(obj.getString("vehicleType"));
        log.setPhoto(obj.getString("photo"));
        log.setEngineNum(obj.getString("engineNum"));
        if (object != null) {
            if ("0".equals(object.get("Code"))) {
                data.put("orderId", object.getString("orderId"));
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
                result.setResult(data);

                log.setOrderId(object.getString("orderId"));
                log.setOrderMsg(object.getString("Message"));
                log.setResponseResult("3");//查询中
                //数据来自数据库
                if("2".equals(object.get("sourceType"))){
                    log.setResponseResult("1");//查询成功
                    log.setResponseMsg(object.getString("responseMsg"));
                    log.setOrderId(object.getString("orderId"));
                    log.setOrderMsg(object.getString("orderMsg"));
                    log.setFinishTime(object.getDate("finishTime"));
                    log.setSourceType(object.getString("sourceType"));
                    log.setVehicleType(object.getString("vehicleType"));
                    log.setPc_url(object.getString("pc_url"));
                    log.setApp_url(object.getString("app_url"));
                }
                /*扣款*/
                CarChaboshiStoreAccount c = accounts.get(0);
                c.setId(idWorker.nextId());
                c.setPayment(payment);
                c.setBalance(c.getBalance().subtract(payment));
                c.setUserName(userName);
                c.setUserId(userId);
                c.setCreateTime(new Date());
                c.setServiceType("2");//查询报告
                c.setType("2");//出账
                /*插入流水记录*/
                storeAccountModel.insertCarChaboshiStoreAccount(c);
                // 查博士角标设置
                setCornerMark(userId);
            } else {
                /*查博士查找失败*/
                log.setResponseResult("2");
                log.setOrderMsg(object.getString("Message"));
                result.setError(ResultCode.FAIL.strValue(), object.getString("Message"));
            }
        } else {
            /*查询失败*/
            log.setResponseResult("2");
            result.setError(ResultCode.FAIL.strValue(), "查找失败！");
        }
        insertCarChaboshiLog(log);
        return result;
    }

    /******************************************end******************************************/

    /**
     * 根据您历史--订单查找
     *
     * @param log
     * @param userId
     * @param userName
     * @return
     */

    @Override
    @Transactional
    public ServiceResult<Map<String, Object>> chaboshiOrder(CarChaboshiLog log, Long userId, String userName) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("appUrl","");
        if (log == null || log.getOrderId() == null) {
            result.setSuccess(ResultCode.FAIL.strValue(), "未发现查博士订单！");
        } else {
            JSONObject orderStatus = ChaboshiUtils.orderStatus(log.getOrderId());
            if ("1104".equals(orderStatus.get("Code"))) {
                /*已出报告*/
                Map cbs = ChaboshiUtils.reportDetail(log.getOrderId());
                resultMap.put("appUrl",cbs.get("mobileUrl"));
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
                //如果当时的log状态为查询中 则需要更新状态
                if ("3".equals(log.getResponseResult())) {
                    //获取url
                    String pcUrl = "" + cbs.get("pcUrl");
                    String mobileUrl = "" + cbs.get("mobileUrl");
                    //获取json
                    JSONObject object = ChaboshiUtils.reportJson(log.getOrderId());
                    if (object != null) {
                        log.setResponseMsg(object.toJSONString());
                        if ("1104".equals(object.getString("Code"))) {
                            String brand = object.getString("brand");
                            String modelName = object.getString("modelName");
                            String seriesName = object.getString("seriesName");

                            log.setVehicleType(brand + "-" + seriesName + "-" + modelName);
                        }
                    }
                    log.setResponseResult("1");
                    log.setFinishTime(new Date());
                    log.setPc_url(pcUrl);
                    log.setApp_url(mobileUrl);
                    Map logMap = JSONObject.parseObject(JSONObject.toJSON(log).toString(), Map.class);
                    logMap.remove("createTime");
                    logMap.put("finishTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss"));
                    updateCarChaboshiLog(logMap);
                    // 查博士设置角标
                    setCornerMark(userId);
                }
            } else if ("1102".equals(orderStatus.get("Code"))) {
                /*查询中*/
                result.setSuccess(ResultCode.FAIL.strValue(), "订单正在查询中！");
                log.setResponseResult("3");
            } else {
                /*其他情况*/
                //TODO 这里不做退款处理，退款的处理在查博士回调后
                log.setResponseResult("2");
                log.setOrderMsg(orderStatus.getString("Message"));
                result.setSuccess(ResultCode.FAIL.strValue(), orderStatus.getString("Message"));
            }
            /*log.setId(idWorker.nextId());
            log.setMoney(new BigDecimal(0));
            log.setSourceType("2");
            log.setUserId(userId);
            log.setUserName(userName);
            log.setCreateTime(new Date());
            *//*写入日志*//*
            insertCarChaboshiLog(log);*/
        }
        result.setResult(resultMap);
        return result;
    }

    /**
     * 查博士设置角标
     * @param managerId
     */
    private void setCornerMark(Long managerId) {
        Notify notify = new Notify();

        if (Objects.isNull(redisManagerTemplate.get(managerId.toString()))) {
            notify.setCbs(notify.getCbs() + 1);
            setRedis(managerId, notify);
        } else {
            notify = getNotify(managerId);
            notify.setCbs(notify.getCbs() + 1);
            setRedis(managerId, notify);
        }
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

    /**
     * 查博士查询
     *
     * @param edition
     * @param vin
     * @param result
     * @return
     */
    private JSONObject cha(String edition, String vin, ServiceResult result) {
        JSONObject object = null;
        Map<String,Object> map = new HashMap<>();
        map.put("fs", DateUtils.addMonths(new Date(), -1));
        map.put("responseResult", "1");
        map.put("vin",vin);
        map.put("edition",edition);
        //查询数据库公式或vin重复1个月之内有效的维修记录
        List<CarChaboshiLog> carChaboshiLogDao = model.selectCarChaboshiLogList(map);
        if(carChaboshiLogDao != null && carChaboshiLogDao.size() > 0){
            CarChaboshiLog carChaboshiLog = carChaboshiLogDao.get(0);
            object = JSONObject.parseObject(JSONObject.toJSON(carChaboshiLog).toString());
            object.put("sourceType","2");
            object.put("Code","0");
            return object;
        }
        if ("1".equals(edition)) {
            //维修版本
            object = ChaboshiUtils.report(vin);
        } else if ("2".equals(edition)) {
            //综合版本
            object = ChaboshiUtils.repairReport(vin);
        }
        object.put("sourceType","1");
        return object;
    }
}
