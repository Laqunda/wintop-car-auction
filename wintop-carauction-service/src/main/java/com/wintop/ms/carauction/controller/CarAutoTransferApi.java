package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by 12991 on 2018/3/9.
 */
@RestController
@RequestMapping("/service/carAutoTransfer")
public class CarAutoTransferApi {
    @Autowired
    private ICarAutoTransferService iCarAutoTransferService;
    @Autowired
    private IWtAppUserService iWtAppUserService;
    @Autowired
    private ICarAgentCompanyService iCarAgentCompanyService;
    @Autowired
    private ICarAutoProceduresService iCarAutoProceduresService;
    @Autowired
    private ICarOrderService iCarOrderService;

    private IdWorker idWorker = new IdWorker(10);
    private static final Logger logger = LoggerFactory.getLogger(CarAutoTransferApi.class);

    /**
     *交接完成
     */
    @RequestMapping(value = "/saveHandOver",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveHandOver(@RequestBody JSONObject obj) {
        logger.info("交接完成");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long orderId = obj.getLong("orderId");
            Long agentCompanyId = obj.getLong("agentCompanyId");
            Long userId = obj.getLong("userId");
            Date handleTime = new Date();
            String photo = obj.getString("photo");
            String createUser = obj.getString("createUser");
            CarOrder carOrder = iCarOrderService.selectById(orderId);
            CarAutoTransfer vo = iCarAutoTransferService.selectByOrderId(orderId,carOrder.getCarId()).getResult();
           CarAutoProcedures carAutoProcedures = iCarAutoProceduresService.getAutoProceduresByCarId(carOrder.getCarId()).getResult();
           String moveToWhere = null;
           if(carAutoProcedures != null){
               moveToWhere = carAutoProcedures.getMoveToWhere();
           }
            if(vo != null){
                result.setError("0","该订单车辆已交接完成");
                map.put("count",0);
                return result;
            }
            photo = photo.replaceAll("\\[","").replaceAll("]","");
            if(agentCompanyId == null){
                WtAppUser wtAppUser = iWtAppUserService.selectUserById(userId).getResult();
                CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
                carAutoTransfer.setId(idWorker.nextId());
                carAutoTransfer.setOrderId(orderId);
                carAutoTransfer.setAutoId(carOrder.getCarId());
                carAutoTransfer.setCommissionPhoto(photo);
                carAutoTransfer.setHandleUserId(wtAppUser.getId());
                carAutoTransfer.setHandleUserName(wtAppUser.getUserName());
                carAutoTransfer.setHandleUserMobile(wtAppUser.getMobile());
                carAutoTransfer.setCreateTime(handleTime);
                carAutoTransfer.setMoveToWhere(moveToWhere);
                carAutoTransfer.setStatus(null);
                Integer count =  iCarAutoTransferService.insertHandOver(carAutoTransfer).getResult();
                map.put("count",count);
            }else{
               CarAgentCompany carAgentCompany = iCarAgentCompanyService.selectById(agentCompanyId);
                CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
                carAutoTransfer.setId(idWorker.nextId());
                carAutoTransfer.setOrderId(orderId);
                carAutoTransfer.setAutoId(carOrder.getCarId());
                carAutoTransfer.setHandleUserId(carAgentCompany.getHandlerId());
                carAutoTransfer.setHandleUserName(carAgentCompany.getHandlerName());
                carAutoTransfer.setHandleUserMobile(carAgentCompany.getHandlerTel());
                carAutoTransfer.setCommissionPhoto(photo);
                carAutoTransfer.setCreateTime(handleTime);
                carAutoTransfer.setMoveToWhere(moveToWhere);
                carAutoTransfer.setStatus(null);
                Integer count =  iCarAutoTransferService.insertHandOver(carAutoTransfer).getResult();
                map.put("count",count);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("交接失败",e);
        }
        return result;
    }


    /**
     *上传手续
     */
    @RequestMapping(value = "/saveCarAutoTransfer",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveCarAutoTransfer(@RequestBody JSONObject obj) {
        logger.info("上传手续");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
           Long orderId = obj.getLong("orderId");
          CarOrder carOrder = iCarOrderService.selectById(orderId);
          Long autoId = carOrder.getCarId();
           Date handleTime = obj.getDate("handleTime");
           String photo = obj.getString("photo");
           String createUser = obj.getString("createUser");
          CarAutoTransfer vo = iCarAutoTransferService.selectByOrderId(orderId,autoId).getResult();
           if(vo != null && "5".equals(vo.getStatus())){
               result.setError("0","该订单车辆已上传手续");
               map.put("count",0);
               return result;
           }else if(vo != null){
               CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
               carAutoTransfer.setId(vo.getId());
               carAutoTransfer.setOrderId(orderId);
               carAutoTransfer.setAutoId(autoId);
               carAutoTransfer.setCommissionPhoto(photo);
//               carAutoTransfer.setCreateUser(createUser);
               carAutoTransfer.setCreateTime(handleTime);
               carAutoTransfer.setStatus("5");
               Integer count =  iCarAutoTransferService.insertUpload(carAutoTransfer).getResult();
               map.put("count",count);
           }else{
               map.put("count",0);
           }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("上传手续失败",e);
        }
        return result;
    }

    /**
     *交挡
     */
    @RequestMapping(value = "/saveToStop",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveToStop(@RequestBody JSONObject obj) {
        logger.info("交挡");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long orderId = obj.getLong("orderId");
            CarOrder carOrder = iCarOrderService.selectById(orderId);
            Long autoId = carOrder.getCarId();
            Date handleTime = obj.getDate("handleTime");
            String photo = obj.getString("photo");
            String createUser = obj.getString("createUser");
            CarAutoTransfer vo = iCarAutoTransferService.selectByOrderId(orderId,autoId).getResult();
            if(vo != null && "3".equals(vo.getStatus())){
                result.setError("0","该订单车辆已交挡");
                map.put("count",0);
                return result;
            }else if(vo != null){
                CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
                carAutoTransfer.setId(vo.getId());
                carAutoTransfer.setOrderId(orderId);
                carAutoTransfer.setAutoId(autoId);
                carAutoTransfer.setCommissionPhoto(photo);
//                carAutoTransfer.setCreateUser(createUser);
                carAutoTransfer.setCreateTime(handleTime);
                carAutoTransfer.setStatus("3");
                Integer count =  iCarAutoTransferService.insertToStop(carAutoTransfer).getResult();
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("交挡失败",e);
        }
        return result;
    }
    /**
     *出牌
     */
    @RequestMapping(value = "/savePlay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> savePlay(@RequestBody JSONObject obj) {
        logger.info("出牌");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long orderId = obj.getLong("orderId");
            CarOrder carOrder = iCarOrderService.selectById(orderId);
            Long autoId = carOrder.getCarId();
            Date handleTime = obj.getDate("handleTime");
            String photo = obj.getString("photo");
            String createUser = obj.getString("createUser");
            CarAutoTransfer vo = iCarAutoTransferService.selectByOrderId(orderId,autoId).getResult();
            if(vo != null && "2".equals(vo.getStatus())){
                result.setError("0","该订单车辆已出牌");
                map.put("count",0);
                return result;
            }else if(vo != null){
                CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
                carAutoTransfer.setId(vo.getId());
                carAutoTransfer.setOrderId(orderId);
                carAutoTransfer.setAutoId(autoId);
                carAutoTransfer.setCommissionPhoto(photo);
//                carAutoTransfer.setCreateUser(createUser);
                carAutoTransfer.setCreateTime(handleTime);
                carAutoTransfer.setStatus("2");
                Integer count =  iCarAutoTransferService.insertPlay(carAutoTransfer).getResult();
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("出牌失败",e);
        }
        return result;
    }
    /**
     *提挡
     */
    @RequestMapping(value = "/saveLiftBlock",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveLiftBlock(@RequestBody JSONObject obj) {
        logger.info("提挡");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long orderId = obj.getLong("orderId");
            CarOrder carOrder = iCarOrderService.selectById(orderId);
            Long autoId = carOrder.getCarId();
            Date handleTime = obj.getDate("handleTime");
            String photo = obj.getString("photo");
            String createUser = obj.getString("createUser");
            CarAutoTransfer vo = iCarAutoTransferService.selectByOrderId(orderId,autoId).getResult();
            if(vo != null && "4".equals(vo.getStatus())){
                result.setError("0","该订单车辆已提挡");
                map.put("count",0);
                return result;
            }else if(vo != null){
                CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
                carAutoTransfer.setId(vo.getId());
                carAutoTransfer.setOrderId(orderId);
                carAutoTransfer.setAutoId(autoId);
                carAutoTransfer.setCommissionPhoto(photo);
//                carAutoTransfer.setCreateUser(createUser);
                carAutoTransfer.setCreateTime(handleTime);
                carAutoTransfer.setStatus("4");
                Integer count =  iCarAutoTransferService.insertLiftBlock(carAutoTransfer).getResult();
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("提挡失败",e);
        }
        return result;
    }

    /**
     *确定过户事宜
     */
    @RequestMapping(value = "/saveDetermine",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveDetermine(@RequestBody JSONObject obj) {
        logger.info("确定过户事宜");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            Long orderId = obj.getLong("orderId");
            CarOrder carOrder = iCarOrderService.selectById(orderId);
            Long autoId = carOrder.getCarId();
            Date handleTime = obj.getDate("handleTime");
            String photo = obj.getString("photo");
            String createUser = obj.getString("createUser");
            CarAutoTransfer vo = iCarAutoTransferService.selectByOrderId(orderId,autoId).getResult();
            if(vo != null && "1".equals(vo.getStatus())){
                result.setError("0","该订单车辆已确定过户事宜");
                map.put("count",0);
                return result;
            }else if(vo != null){
                CarAutoTransfer carAutoTransfer = new CarAutoTransfer();
                carAutoTransfer.setId(vo.getId());
                carAutoTransfer.setOrderId(orderId);
                carAutoTransfer.setAutoId(autoId);
                carAutoTransfer.setCommissionPhoto(photo);
                carAutoTransfer.setCreateTime(handleTime);
                carAutoTransfer.setStatus("1");
                Integer count =  iCarAutoTransferService.insertDetermine(carAutoTransfer).getResult();
                map.put("count",count);
            }else{
                map.put("count",0);
            }
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("确定过户事宜失败",e);
        }
        return result;
    }

    /**
     * 根据条件查询有关过户流程信息
     */
    @RequestMapping(value = "/queryTransferList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> queryTransferList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long autoId = obj.getLong("autoId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("autoId",autoId);
           List<CarAutoTransfer> carAutoTransfers = iCarAutoTransferService.selectByParam(paramMap).getResult();
           Integer count = iCarAutoTransferService.selectCount(paramMap).getResult();
            List<Map<String,Object>> list = new ArrayList<>();
            if(carAutoTransfers != null && carAutoTransfers.size() > 0){
                for(CarAutoTransfer carAutoTransfer:carAutoTransfers){
                    Map<String,Object> map = new HashMap<>();
                    map.put("status",carAutoTransfer.getStatus());
                    map.put("createTime",carAutoTransfer.getCreateTime());
                    map.put("commissionPhoto",carAutoTransfer.getCommissionPhoto());
                    map.put("type",carAutoTransfer.getType());
                    map.put("moveToWhere",carAutoTransfer.getMoveToWhere());
                    list.add(map);
                }
            }else{
                Map<String,Object> map = new HashMap<>();
                map.put("status","");
                map.put("createTime","");
                map.put("commissionPhoto","");
                map.put("type","");
                map.put("moveToWhere","");
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            if(count != null){
                listEntity.setCount(count);
            }else{
                listEntity.setCount(0);
            }
            listEntity.setList(list);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询过户流程信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }


    /**
     *确定收款
     */
    @RequestMapping(value = "/saveGathering",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveGathering(@RequestBody JSONObject obj) {
        logger.info("确定收款");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            map.put("count",0);
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
            logger.info("确定过户事宜失败",e);
        }
        return result;
    }

    /**
     * 确认手续交接
     *@Author:zhangzijuan
     *@date 2018/4/18
     *@param:
     */
    @PostMapping(value = "/saveTransfer",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveTransfer(@RequestBody JSONObject obj){
        logger.info("手续交接确认");
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=iCarAutoTransferService.saveTransfer(obj);
            if (i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else if (i==-1){
                result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            logger.info("手续交接确认失败",e);
        }
        return result;
    }


}
