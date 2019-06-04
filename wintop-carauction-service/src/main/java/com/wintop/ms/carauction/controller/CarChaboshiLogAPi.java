package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarFinancePayLogModel;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.AlipayUtil;
import com.wintop.ms.carauction.util.ChaboshiUtils;
import com.wintop.ms.carauction.util.Class2MapUtil;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 查博士日志 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-08
 */
@RestController
@RequestMapping("/service/carChaboshiLog")
public class CarChaboshiLogAPi {

    private static final Logger logger = LoggerFactory.getLogger(CarAssessApi.class);
    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarChaboshiLogService carChaboshiLogService;

    @Autowired
    private IWtAppUserService appUserService;

    @Autowired
    private ICarManagerUserService managerUserService;

    @Autowired
    private ICarChaboshiStoreConfService storeConfService;

    @Autowired
    private ICarChaboshiPaymentConfService paymentConfService;

    @Autowired
    private CarFinancePayLogModel financePayLogModel;

    @Autowired
    private ICarChaboshiStoreAccountService accountService;

    @Autowired
    private ICarAssessService assessService;

    @Autowired
    private ICarChaboshiStoreConfService carChaboshiStoreConfService;


    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志列表")
    @RequestMapping(value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarChaboshiLog>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarChaboshiLog>> result = null;
        try {
//            CarChaboshiLog carChaboshiLog = JSONObject.toJavaObject(obj, CarChaboshiLog.class);
//            if (carChaboshiLog == null) {
//                carChaboshiLog = new CarChaboshiLog();
//            }
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            /*根据assessId查询其查博士的查询日志*/
            if (obj.get("assessId") != null && obj.getLong("assessId") > 0) {
                CarAssess carAssess = new CarAssess();
                carAssess.setId(obj.getLong("assessId"));
                CarAssess assess = assessService.selectCarAssessById(carAssess);
//                carChaboshiLog.setVin(assess.getVin());
//                carChaboshiLog.setUserId(assess.getCreateUser());
                param.put("vin", assess.getVin());
                param.put("userId", assess.getCreateUser());
            }
            result = new ServiceResult<>();

            int count = carChaboshiLogService.selectCount(param);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
//            carChaboshiLog.setStartRowNum(pageEntity.getStartRowNum());
//            carChaboshiLog.setEndRowNum(pageEntity.getEndRowNum());
            param.put("startRowNum", pageEntity.getStartRowNum());
            param.put("endRowNum", pageEntity.getEndRowNum());

            List<CarChaboshiLog> list = carChaboshiLogService.selectCarChaboshiLogList(param);
            ListEntity<CarChaboshiLog> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询查博士日志列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志列表")
    @RequestMapping(value = "/allList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> allList(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {

            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            /*根据assessId查询其查博士的查询日志*/
            if (obj.get("assessId") != null && obj.getLong("assessId") > 0) {
                CarAssess carAssess = new CarAssess();
                carAssess.setId(obj.getLong("assessId"));
                CarAssess assess = assessService.selectCarAssessById(carAssess);
                param.put("vin", assess.getVin());
                param.put("userId", assess.getCreateUser());
            }
            List<CarChaboshiLog> list = carChaboshiLogService.selectCarChaboshiLogList(param);
            result.setResult(Collections.singletonMap("list", list));
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询查博士日志列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }

        return result;
    }


    /**
     * 新增保存查博士日志
     */
    @ApiOperation(value = "新增保存查博士日志")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> addSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();

        try {
            CarChaboshiLog carChaboshiLog = JSONObject.toJavaObject(obj, CarChaboshiLog.class);
            if (carChaboshiLog == null) {
                carChaboshiLog = new CarChaboshiLog();
            }
            carChaboshiLog.setId(idWorker.nextId());
            int code = carChaboshiLogService.insertCarChaboshiLog(carChaboshiLog);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("新增保存查博士日志", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 修改查博士日志
     */
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
//            CarChaboshiLog carChaboshiLog = JSONObject.toJavaObject(obj, CarChaboshiLog.class);
//            if (carChaboshiLog == null) {
//                carChaboshiLog = new CarChaboshiLog();
//            }
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            int code = carChaboshiLogService.updateCarChaboshiLog(param);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("修改查博士日志", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 删除查博士日志
     */
    @ApiOperation(value = "删除查博士日志")
    @RequestMapping(value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String ids = obj.getString("ids");
            int code = carChaboshiLogService.deleteCarChaboshiLogByIds(ids);

            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除查博士日志", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 查博士查询
     */
    @ApiOperation(value = "查博士查询")
    @RequestMapping(value = "/vinSearch",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<Map<String, Object>> vinSearch(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            String vin = obj.getString("vin");
            Long userId = obj.getLong("userId");
            String userName = obj.getString("userName");
            Long storeId = obj.getLong("storeId");
            /*1:普通用户 2：店铺人员*/
            String userType = obj.getString("userType");
            /*1:历史单查询 2：新的查询*/
            String searchType = obj.getString("searchType");
            /*查询版本 1维修版 2综合版*/
            String edition = obj.getString("edition");

            if ("1".equals(searchType)) {
                /*历史订单查询*/
                CarChaboshiLog log = carChaboshiLogService.selectCarChaboshiLogById(obj.getLong("logId"));
                result = searChForOrder(log, userId, userName);
            } else if ("2".equals(searchType)) {
                /*新建查询订单 此处只允许店铺用户*/
                if ("2".equals(userType)) {
                    result = searchForStore(obj);
                } else {
                    result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.NO_PARAM.getRemark());
                }
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.NO_PARAM.getRemark());
            }
        } catch (Exception e) {
            logger.info("查博士查询", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }


    /**
     * 店铺用户查询 其中需要验证 查博士支付的金额配置 以及余额是否足够
     *
     * @return
     */
    private ServiceResult<Map<String, Object>> searchForStore(JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        //查找store信息
        CarChaboshiStoreConf storeConf = new CarChaboshiStoreConf();
        storeConf.setStoreId(obj.getLong("storeId"));
        List<CarChaboshiStoreConf> storeConfs = storeConfService.selectCarChaboshiStoreConfList(storeConf);

        if (storeConfs != null && storeConfs.size() > 0) {
            /*查找店铺配置金额*/
            CarChaboshiStoreConf c = storeConfs.get(0);
            BigDecimal balance = c.getBalance();
            BigDecimal payment = c.getPayment();
            BigDecimal paymentComposite = c.getPaymentComposite();

            /*没有设置支付金额 则返回错误*/
            if (payment == null || paymentComposite == null) {
                result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
                return result;
            }

            if ("1".equals(obj.get("edition"))) {
                /*维修版本*/

                if (balance == null || balance.compareTo(payment) == -1) {
                    result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.LOW_BALANCE.getRemark());
                } else {
                    /* 查询--支付--生成查询记录*/
                    result = carChaboshiLogService.chaboshiStore(payment, obj);
                }

            } else if ("2".equals(obj.get("edition"))) {
                /*综合版本*/

                if (balance == null || balance.compareTo(paymentComposite) == -1) {
                    result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.LOW_BALANCE.getRemark());
                } else {
                    /* 查询--支付--生成查询记录*/
                    result = carChaboshiLogService.chaboshiStore(payment, obj);
                }
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.NO_PARAM.getRemark());
            }


        } else {
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.NO_OBJECT.getRemark());
        }
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
        CarChaboshiLog log = carChaboshiLogService.selectCarChaboshiLogById(logId);
       /*
        查询结果 1查询成功，2查询失败，3，查询中
        类型：1店铺，2个人
        条件：钱必须支付，必须是个人用户，状态必须是查询中
        */
        if (log != null && log.getMoney() != null && "2".equals(log.getUserType()) && "3".equals(log.getResponseResult())) {
            result = carChaboshiLogService.chaboshi(userId, userName, edition, logId, vin);
        } else {
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.NO_ORDER.getRemark());
        }
        return result;
    }

    /**
     * 历史订单查询
     *
     * @param log
     * @return
     */
    private ServiceResult<Map<String, Object>> searChForOrder(CarChaboshiLog log, Long userId, String userName) {
        return carChaboshiLogService.chaboshiOrder(log, userId, userName);
    }

    /**
     * 查博士 支付成功后，回调保存成功
     *
     * @param obj
     * @return
     * @Time 2018-3-13
     */
    @PostMapping(value = "payChaboshiAmountCallback")
    @ApiOperation(value = "支付成功后，回调保存成功")
    @ApiImplicitParam(name = "obj", value = "JSONObject对象", required = true, dataType = "JSONObject")
    @ResponseBody
    public ServiceResult<Map<String, Object>> payCbsAmountCallback(@RequestBody JSONObject obj) {
        logger.info("支付成功后，回调保存成功");
        ServiceResult result = new ServiceResult();
        try {
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

            if (financePayLogModel.insert(payLog) > 0) {
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

                carChaboshiLogService.insertCarChaboshiLog(log);

                /*去查询 并更新查博士日志*/
                result = searchForCustomer(obj.getLong("userId"), obj.getString("userName"), obj.getString("edition"), logId, obj.getString("vin"));

            } else {
                result.setError("-1", "查博士支付失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "查博士支付失败");
        } finally {
            return result;
        }

    }

    /**
     * 商家 查博士存钱 设置支付成功后，回调保存成功
     *
     * @param obj
     * @return
     * @Autor 付陈林
     * @Time 2018-3-13
     */
    @PostMapping(value = "payCbsChargeCallback")
    @ApiOperation(value = "设置支付成功后，回调保存成功")
    @ApiImplicitParam(name = "obj", value = "JSONObject对象", required = true, dataType = "JSONObject")
    @ResponseBody
    public ServiceResult<Map<String, Object>> payCbsChargeCallback(@RequestBody JSONObject obj) {
        logger.info("查博士存钱成功后，回调保存成功");
        ServiceResult result = new ServiceResult();
        Map resultMap = new HashMap();
        try {
            //获取最低顶层的一条记录--获取余额
            CarChaboshiStoreAccount tmp = new CarChaboshiStoreAccount();
            tmp.setStoreId(obj.getLong("storeId"));
            CarChaboshiStoreAccount account_old = accountService.selectCarChaboshiStoreAccount(tmp);

            BigDecimal b = new BigDecimal(0);
            if (account_old != null) {
                b = account_old.getBalance() == null ? new BigDecimal(0) : account_old.getBalance();
            }
            b = b.add(obj.getBigDecimal("money"));

            //写入查博士资金流水表
            CarChaboshiStoreAccount account = new CarChaboshiStoreAccount();
            account.setId(idWorker.nextId());
            account.setStoreId(obj.getLong("storeId"));
            account.setType("1");//进出帐类型：1进账，2出账
            account.setBalance(b);
            account.setPayment(obj.getBigDecimal("money"));
            account.setUserId(obj.getLong("userId"));
            account.setUserName(obj.getString("userName"));
            account.setServiceType("1");//业务类型：1充值，2查询报告
            account.setCreateTime(new Date());
            if (accountService.insertCarChaboshiStoreAccount(account) > 0) {
                result.setSuccess("0", "查博士支付成功");
            } else {
                result.setError("-1", "查博士支付失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "查博士支付失败");
        } finally {
            return result;
        }

    }


    /**
     * 查博生成报告回掉
     *
     * @return
     * @Time 2018-3-13
     */
    @PostMapping(value = "cbsCallback")
    @ApiOperation(value = "查博生成报告回掉，回调保存成功")
    @ResponseBody
    public Map chaboshiCallback(@RequestParam String result, @RequestParam String message, @RequestParam String orderid) {
        logger.info("查博生成报告回掉，回调成功");
        Map resultMap = new HashMap();
        resultMap.put("code", "0");
        resultMap.put("message", "success");
        try {
            System.out.println("----------查博士回调成功--------------");
            System.out.println("result：" + result + "\n" + "message:" + message + "\n" + "orderId:" + orderid);

            /*根据orderId获取log数据 */
            Map<String, Object> param = Maps.newHashMap();
            param.put("responseResult", "3");
            param.put("sourceType", "1");
            param.put("orderId", orderid);
            CarChaboshiLog log = carChaboshiLogService.selectCarChaboshiLog(param);

            if (log == null) {
                resultMap.put("code", "1");
                resultMap.put("message", "未找到查询记录!");

            } else if ("1".equals(result)) {
                /*生成报告成功  并更新状态*/
                //获取url
                Map chaboshi = ChaboshiUtils.reportDetail(orderid);
                String pcUrl = (String) chaboshi.get("pcUrl");
                String mobileUrl = (String) chaboshi.get("mobileUrl");
                //获取json

                JSONObject object = ChaboshiUtils.reportJson(orderid);

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
                log.setOrderMsg(message);
                log.setFinishTime(new Date());
                log.setPc_url(pcUrl);
                log.setApp_url(mobileUrl);
                //TODO 无车型信息情况下 获取车型信息
                Map<String, Object> logMap = Class2MapUtil.convertMap(log);
                carChaboshiLogService.updateCarChaboshiLog(logMap);
            } else {

                log.setResponseResult("2");//失败
                log.setOrderMsg(message);
                Map<String, Object> logMap = Class2MapUtil.convertMap(log);
                carChaboshiLogService.updateCarChaboshiLog(logMap);

                /*退款*/
                if ("1".equals(log.getUserType())) {
                    /*退款到个人支付宝*/
                    CarFinancePayLog payLog = financePayLogModel.selectById(log.getPayLogId());
                    Map map = AlipayUtil.refundOrder(payLog);
                    if ("0".equals(map.get("code"))) {
                        /*退款成功*/
                        int c = carChaboshiLogService.savePayLog(payLog);
                    } else {
                        /*退款失败*/
                    }
                    //TODO 买家退款失败 --  状态提醒
                } else if ("2".equals(log.getUserType())) {
                    /*退款到店铺资金流水表*/
                    saveStoreAccountLog(log);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", "1");
            resultMap.put("message",e.getMessage());
        }
        return resultMap;

    }

    /**
     * 店铺资金流水表
     *
     * @param log
     */
    private int saveStoreAccountLog(CarChaboshiLog log) {
        //获取最低顶层的一条记录--获取余额
        CarChaboshiStoreAccount tmp = new CarChaboshiStoreAccount();
        tmp.setStoreId(log.getStoreId());
        CarChaboshiStoreAccount account_old = accountService.selectCarChaboshiStoreAccount(tmp);
        BigDecimal balance = new BigDecimal(0);
        if (account_old != null) {
            balance = account_old.getBalance() == null ? new BigDecimal(0) : account_old.getBalance();
        }

        CarChaboshiStoreAccount c = new CarChaboshiStoreAccount();
        c.setId(idWorker.nextId());
        c.setPayment(log.getMoney());
        c.setBalance(balance.add(log.getMoney()));
        c.setUserName(log.getUserName());
        c.setUserId(log.getUserId());
        c.setStoreId(log.getStoreId());
        c.setCreateTime(new Date());
        c.setServiceType("3");//退款
        c.setType("1");//入账
        /*插入流水记录*/
        return accountService.insertCarChaboshiStoreAccount(c);
    }


}
