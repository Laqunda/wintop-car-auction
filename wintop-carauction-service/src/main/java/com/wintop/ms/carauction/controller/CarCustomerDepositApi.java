package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.ICarCustomerAuthService;
import com.wintop.ms.carauction.service.ICarCustomerDepositService;
import com.wintop.ms.carauction.service.ICarCustomerLevelService;
import com.wintop.ms.carauction.service.ICarQuestionService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 付陈林.
 * @Description: 保证金的接口Controller
 * @Date: 19:37 on 2018/3/5.
 * @Modified by:
 */
@RestController
@RequestMapping(value = "/service/carCustomerDeposit")
@Api(tags = "保证金相关接口",value = "保证金接口")
public class CarCustomerDepositApi {
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerDepositApi.class);
    private IdWorker idWorker = new IdWorker(10);
    @Resource
    private ICarQuestionService carQuestionService;

    @Resource
    private ICarCustomerDepositService carCustomerDepositService;

    @Resource
    private ICarCustomerLevelService carCustomerLevelService;

    private Long QUESTION_CLASSIFY_ID=5l;


    /**
     * 获取保证金常见问题接口
     * @return
     */
    @PostMapping(value = "getCarQuestionForDeposit",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取保证金额的相关常见问题",notes = "")
    public ServiceResult<List<CarQuestion>> getCarQuestionForDeposit(){
        logger.info(" 获取保证金常见问题");
        ServiceResult<List<CarQuestion>> result = new ServiceResult<>();
        try {
            List<CarQuestion> questionList = carQuestionService.selectByClassifyId(QUESTION_CLASSIFY_ID);
            result.setResult(questionList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取保证金常见问题失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 根据用户ID查询保证金余额
     * @Autor 付陈林
     * @Time 2018-3-5
     * @param obj
     * @return
     */
    @PostMapping(value = "getDepositAmountByUserId",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据用户的ID获取到用户的保证金余额",notes = "传人userId字段")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "JSONObject")
    @ResponseBody
    public ServiceResult<Map<String,Object>> getDepositAmountByUserId(@RequestBody JSONObject obj){
        logger.info(" 根据用户的ID获取到用户的保证金余额");
        return carCustomerDepositService.getDepositAmountByUserId(obj.getLong("userId"));
    }

    /**
     * 根据获取等级查询该等级因交保证金金额
     * @Autor 付陈林
     * @Time  2018-3-7
     * @param obj
     * @return
     */
    @PostMapping(value = "getDepositAmountByCustomerLevelId",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据获取等级查询该等级因交保证金金额",notes = "传人customerLevelId字段")
    @ApiImplicitParam(name = "customerLevelId",value = "用户等级Id",required = true,dataType = "JSONObject")
    @ResponseBody
    public ServiceResult<Map<String,Object>> getDepositAmountByCustomerLevelId(@RequestBody JSONObject obj){
        logger.info(" 根据获取等级查询该等级因交保证金金额");
        return carCustomerLevelService.getDepositAmountByCustomerLevelId(obj.getLong("customerLevelId"));
    }

    /**
     * 设置支付成功后，回调保存成功
     * @Autor 付陈林
     * @Time  2018-3-13
     * @param obj
     * @return
     */
    @PostMapping(value = "payDepositAmountCallback")
    @ApiOperation(value = "设置支付成功后，回调保存成功")
    @ApiImplicitParam(name = "obj",value = "JSONObject对象",required = true,dataType = "JSONObject")
    @ResponseBody
    public ServiceResult<Map<String,Object>> payDepositAmountCallback(@RequestBody JSONObject obj){
        logger.info("设置支付成功后，回调保存成功");
        ServiceResult result = new ServiceResult();
        try {
            CarCustomerDeposit carCustomerDeposit =new CarCustomerDeposit();
            carCustomerDeposit.setId(idWorker.nextId());
            carCustomerDeposit.setDepositAmount(obj.getBigDecimal("depositAmount"));
            carCustomerDeposit.setDepositNo(RandCodeUtil.getOrderNumber());
            carCustomerDeposit.setFreezeFlag("0");
            carCustomerDeposit.setPayTime(new Date());
            carCustomerDeposit.setStatus("1");
            carCustomerDeposit.setUserId(obj.getLong("userId"));
            carCustomerDeposit.setUseStatus("0");

            CarFinancePayLog payLog = new CarFinancePayLog();
            payLog.setId(idWorker.nextId());
            payLog.setCreatePersonType(obj.getString("userType"));
            payLog.setCreatePerson(obj.getLong("userId"));
            payLog.setCreateTime(new Date());
            payLog.setOrderNo(carCustomerDeposit.getDepositNo());
            payLog.setPayTime(new Date());
            payLog.setStatus("1");
            payLog.setType("1");
            payLog.setOrderId(carCustomerDeposit.getId());
            payLog.setBankOrderNo(obj.getString("bankOrderNo"));
            payLog.setBankOrderLog(obj.getString("bankOrderLog"));
            payLog.setPayType(obj.getString("payType"));
            payLog.setPayWay(obj.getString("payWay"));
            payLog.setLogNo(obj.getString("payLogNo"));
            payLog.setPayFee(carCustomerDeposit.getDepositAmount());
            payLog.setRemark(obj.getString("passbackParams"));
            payLog.setUserId(obj.getLong("userId"));
            carCustomerDeposit.setPayLog(payLog);

            if (carCustomerDepositService.insertCarCustomerDeposit(carCustomerDeposit)>0){
                result.setSuccess("0","保证金支付成功");
            }else {
                result.setError("-1","保证金支付失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","保证金支付失败");
        }finally {
            return result;
        }

    }


    /***
     * 获取用户的保证金缴纳记录
     *  zhangzijuan
     */
    @ApiOperation(value = "获取用户的保证金缴纳记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "/queryUserDepositByUserId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarCustomerDeposit>> queryUserDepositByUserId(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarCustomerDeposit>> result = new ServiceResult<>();
        try {
            Long userId = obj.getLong("userId");
            Map<String,Object> paramMap = new HashMap<>();
            Integer page=obj.getInteger("page");
            Integer pageSize=obj.getInteger("limit");
            paramMap.put("startRowNum",(page-1)*pageSize);
            paramMap.put("endRowNum",pageSize);
            paramMap.put("userId",userId);
            int count = carCustomerDepositService.selectDepositCount(paramMap);
            List<CarCustomerDeposit> customerDeposits = carCustomerDepositService.selectDepositList(paramMap);
            ListEntity<CarCustomerDeposit> listEntity = new ListEntity<>();
            listEntity.setList(customerDeposits);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取用户的保证金缴纳记录异常",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
