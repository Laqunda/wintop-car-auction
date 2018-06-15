package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.OrderStatusEnum;
import com.wintop.ms.carauction.core.config.TransferStatusEnum;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarAutoAuctionService;
import com.wintop.ms.carauction.service.ICarAutoTransferService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CarAutoTransferService")
public class CarAutoTransferServiceImpl implements ICarAutoTransferService {
    @Autowired
    private CarAutoTransferModel carAutoTransferModel;
    @Autowired
    private CarAutoTransferLogModel carAutoTransferLogModel;
    @Autowired
    private CarManagerUserModel userModel;
    @Autowired
    private CarOrderModel carOrderModel;
    @Autowired
    private CarOrderLogModel orderLogModel;
    @Autowired
    private CarAutoModel carAutoModel;
    @Autowired
    private CarAutoLogModel autoLogModel;
    @Autowired
    private CarAutoAuctionModel autoAuctionModel;
    @Autowired
    private CarAgentCompanyModel agentCompanyModel;
    @Autowired
    private CarAgentModel agentModel;
    @Autowired
    private AppUserModel appUserModel;
    private IdWorker idWorker = new IdWorker(10);
    private static final Logger logger = LoggerFactory.getLogger(CarAutoTransferServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoTransfer> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoTransfer> result = new ServiceResult<>();
        try {
            CarAutoTransfer carAutoDetectionClass = this.carAutoTransferModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoTransfer>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoTransfer>> result = new ServiceResult<>();
        try {
            List<CarAutoTransfer> list = this.carAutoTransferModel.selectByExample(example);
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferModel.deleteByPrimaryKey(id);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferModel.updateByPrimaryKeySelective(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferModel.updateByPrimaryKey(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insert(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferModel.insertSelective(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insertSelective(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferModel.insertSelective(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }
    /**
     * 交接完成
     */
    @Transactional
    public ServiceResult<Integer> insertHandOver(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            if(carAutoTransferModel.insertHandOver(record) > 0){
                CarAutoTransferLog carAutoTransferLog = new CarAutoTransferLog();
                carAutoTransferLog.setId(idWorker.nextId());
                carAutoTransferLog.setTransferId(record.getId());
                carAutoTransferLog.setType(record.getStatus());
                carAutoTransferLog.setHandleTime(record.getCreateTime());
                carAutoTransferLog.setFileUrl(record.getCommissionPhoto());
               Integer count =  carAutoTransferLogModel.insert(carAutoTransferLog);
                result.setResult(count);
                result.setSuccess("0","成功");
            }else{
                result.setResult(null);
                result.setSuccess("-1","异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }
    /**
     * 上传手续
     */
    @Transactional
    public ServiceResult<Integer> insertUpload(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            if(carAutoTransferModel.insertUpload(record) > 0){
                CarAutoTransferLog carAutoTransferLog = new CarAutoTransferLog();
                carAutoTransferLog.setId(idWorker.nextId());
                carAutoTransferLog.setTransferId(record.getId());
                carAutoTransferLog.setType(record.getStatus());
                carAutoTransferLog.setHandleTime(record.getCreateTime());
                carAutoTransferLog.setFileUrl(record.getCommissionPhoto());
                Integer count =  carAutoTransferLogModel.insert(carAutoTransferLog);
                result.setResult(count);
                result.setSuccess("0","成功");
            }else{
                result.setResult(null);
                result.setSuccess("-1","异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    /**
     * 根据auto_id和order_id查询有关数据
     */
    public ServiceResult<CarAutoTransfer> selectByOrderId(Long orderId, Long autoId) {
        ServiceResult<CarAutoTransfer> result = new ServiceResult<>();
        try {
            CarAutoTransfer carAutoTransfer = this.carAutoTransferModel.selectByOrderId(orderId,autoId);
            result.setResult(carAutoTransfer);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    /**
     * 交挡
     */
    @Transactional
    public ServiceResult<Integer> insertToStop(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            if(carAutoTransferModel.insertToStop(record) > 0){
                CarAutoTransferLog carAutoTransferLog = new CarAutoTransferLog();
                carAutoTransferLog.setId(idWorker.nextId());
                carAutoTransferLog.setTransferId(record.getId());
                carAutoTransferLog.setType(record.getStatus());
                carAutoTransferLog.setHandleTime(record.getCreateTime());
                carAutoTransferLog.setFileUrl(record.getCommissionPhoto());
                Integer count =  carAutoTransferLogModel.insert(carAutoTransferLog);
                result.setResult(count);
                result.setSuccess("0","成功");
            }else{
                result.setResult(null);
                result.setSuccess("-1","异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }
    /**
     * 出牌
     */
    @Transactional
    public ServiceResult<Integer> insertPlay(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            if(carAutoTransferModel.insertPlay(record) > 0){
                CarAutoTransferLog carAutoTransferLog = new CarAutoTransferLog();
                carAutoTransferLog.setId(idWorker.nextId());
                carAutoTransferLog.setTransferId(record.getId());
                carAutoTransferLog.setType(record.getStatus());
                carAutoTransferLog.setHandleTime(record.getCreateTime());
                carAutoTransferLog.setFileUrl(record.getCommissionPhoto());
                Integer count =  carAutoTransferLogModel.insert(carAutoTransferLog);
                result.setResult(count);
                result.setSuccess("0","成功");
            }else{
                result.setResult(null);
                result.setSuccess("-1","异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }
    /**
     * 提挡
     */
    @Transactional
    public ServiceResult<Integer> insertLiftBlock(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            if(carAutoTransferModel.insertLiftBlock(record) > 0){
                CarAutoTransferLog carAutoTransferLog = new CarAutoTransferLog();
                carAutoTransferLog.setId(idWorker.nextId());
                carAutoTransferLog.setTransferId(record.getId());
                carAutoTransferLog.setType(record.getStatus());
                carAutoTransferLog.setHandleTime(record.getCreateTime());
                carAutoTransferLog.setFileUrl(record.getCommissionPhoto());
                Integer count =  carAutoTransferLogModel.insert(carAutoTransferLog);
                result.setResult(count);
                result.setSuccess("0","成功");
            }else{
                result.setResult(null);
                result.setSuccess("-1","异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }
    /**
     * 确定过户事宜
     */
    @Transactional
    public ServiceResult<Integer> insertDetermine(CarAutoTransfer record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            if(carAutoTransferModel.insertDetermine(record) > 0){
                CarAutoTransferLog carAutoTransferLog = new CarAutoTransferLog();
                carAutoTransferLog.setId(idWorker.nextId());
                carAutoTransferLog.setTransferId(record.getId());
                carAutoTransferLog.setType(record.getStatus());
                carAutoTransferLog.setHandleTime(record.getCreateTime());
                carAutoTransferLog.setFileUrl(record.getCommissionPhoto());
                Integer count =  carAutoTransferLogModel.insert(carAutoTransferLog);
                result.setResult(count);
                result.setSuccess("0","成功");
            }else{
                result.setResult(null);
                result.setSuccess("-1","异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    /**
     * 根据条件查询过户流程信息
     */
    public ServiceResult<List<CarAutoTransfer>> selectByParam(Map<String,Object> map) {
        ServiceResult<List<CarAutoTransfer>> result = new ServiceResult<>();
        try {
            List<CarAutoTransfer> list = this.carAutoTransferModel.selectByParam(map);
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }
    /**
     * 根据条件查询过户流程信息数量
     */
    public ServiceResult<Integer> selectCount(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer count = this.carAutoTransferModel.selectCount(map);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    /**
     *@Author:zhangzijuan
     *上传手续、交挡、出牌、提挡、确定过户事宜
     *@date 2018/3/26
     *@param:
     */
    @Transactional
    public Integer submitTransfer(JSONObject object){
        Integer result=0;
        Long transferId=object.getLong("transferId");
        String status=object.getString("status");
        Long managerId=object.getLong("managerId");
        CarAutoTransfer transfer=carAutoTransferModel.selectByPrimaryKey(transferId);
        if(transfer!=null){
            String phone="";
            String name="";
            if ("3".equals(object.getString("type"))){
                CarManagerUser user=userModel.selectByPrimaryKey(managerId);
                phone=user.getUserPhone();
                name=user.getUserName();
            }else if ("1".equals(object.getString("type"))){
                WtAppUser user=appUserModel.selectUserById(managerId);
                phone=user.getMobile();
                name=user.getName();
            }else {
                return result;
            }
            CarOrder order=carOrderModel.queryOrderBaseInfo(transfer.getOrderId());
            if(order!=null){
                CarAuto auto=carAutoModel.selectByPrimaryKey(order.getCarId());
                //如果是确认过户操作，需要填写迁往何地
                if(TransferStatusEnum.PLATE_HANLDING.value().equals(status)){
                    //判断迁往地是否填写
                    if( StringUtils.isNotBlank(object.getString("moveToWhere")) && StringUtils.isNotBlank("moveAddress")){
                        transfer.setMoveToWhere(object.getString("moveToWhere"));
                        transfer.setMoveAddress(object.getString("moveAddress"));
                        transfer.setHandleUserId(managerId);
                        transfer.setHandleUserMobile(phone);
                        transfer.setHandleUserName(name);
                    }else {
                        return  -1;
                    }
                }
//                如果是上传过户手续，需要修改订单的状态及车辆的状态
                if(TransferStatusEnum.PROCEDURE_HANLDING.value().equals(status)){
                    //修改订单状态  6手续回传待确认
                    order.setStatus(OrderStatusEnum.PROCEDURE_HANLDING.value());
                    carOrderModel.updateByIdSelective(order);
                    //保存修改订单日志
                    CarOrderLog orderLog=new CarOrderLog();
                    orderLog.setId(idWorker.nextId());
                    orderLog.setOrderId(order.getId());
                    orderLog.setStatus(order.getStatus());
                    orderLog.setLogMsg(object.getString("remark"));
                    orderLog.setCreateTime(new Date());
                    orderLog.setCreatePerson(managerId);
                    orderLog.setUserMobile(phone);
                    orderLog.setUserName(name);
                    orderLogModel.insert(orderLog);

                    //修改车辆状态 15手续回传确认中
                    auto.setStatus(CarStatusEnum.PROCEDURE_HANLDING.value());
                    auto.setUpdateTime(new Date());
                    carAutoModel.updateByPrimaryKeySelective(auto);
                    //保存车辆的轨迹日志
                    CarAutoLog autoLog=new CarAutoLog();
                    autoLog.setId(idWorker.nextId());
                    autoLog.setAutoId(auto.getId());
                    autoLog.setMsg(object.getString("remark"));
                    autoLog.setStatus(auto.getStatus());
                    autoLog.setUserType("3");
                    autoLog.setUserId(managerId);
                    autoLog.setUserMobile(phone);
                    autoLog.setUserName(name);
                    autoLog.setTime(new Date());
                    autoLogModel.insert(autoLog);
                    //如果是手续回传
                    transfer.setTransferDoc(object.getString("fileUrl"));

                }

                //更改过户主表的状态
                transfer.setStatus(status);
                transfer.setId(transferId);
                if(carAutoTransferModel.updateByPrimaryKeySelective(transfer)>0){
                    //保存过户日志
                    CarAutoTransferLog transferLog=new CarAutoTransferLog();
                    transferLog.setId(idWorker.nextId());
                    transferLog.setTransferId(transferId);
                    transferLog.setType(status);
                    transferLog.setTime(new Date());
                    transferLog.setHandlePerson(managerId);
                    transferLog.setFileType(object.getString("fileType"));
                    transferLog.setFileUrl(object.getString("fileUrl"));
                    transferLog.setHandleTime(object.getDate("handleTime"));
                    transferLog.setRemark(object.getString("remark"));
                    result=carAutoTransferLogModel.insertSelective(transferLog);
                }
            }
        };
        return result;
    }

    /**
     * 确认手续交接
     *@Author:zhangzijuan
     *@date 2018/4/18
     *@param:
     */
    @Transactional
    public Integer saveTransfer(JSONObject object){
        Integer result=0;
        CarOrder order=carOrderModel.queryOrderBaseInfo(object.getLong("orderId"));
        if (order==null){
            return result;
        }
        CarAutoAuction autoAuction=autoAuctionModel.selectByPrimaryKey(order.getAutoAuctionId());
        CarAuto auto=carAutoModel.selectByPrimaryKey(order.getCarId());
        CarAutoTransfer autoTransfer=carAutoTransferModel.selectByOrderId(order.getId(),order.getCarId());
        Long managerId=object.getLong("managerId");
        CarAutoTransfer transfer=new CarAutoTransfer();
        if (autoTransfer==null && auto!=null && autoAuction!=null && autoAuction.getIfAgent()!=null){
            //   1-如果需要代办
            if ("1".equals(autoAuction.getIfAgent())){
                //代办公司是否为空
                if (object.get("agentCompanyId")!=null){
                    CarAgentCompany agentCompany=agentCompanyModel.selectById(object.getLong("agentCompanyId"));
                    if (agentCompany==null){
                        return result;
                    }
                    //保存代办信息
                    CarAgent agent=new CarAgent();
                    agent.setId(idWorker.nextId());
                    agent.setAgentCompanyId(object.getLong("agentCompanyId"));
                    agent.setOrderId(order.getId());
                    agent.setCarId(order.getCarId());
                    agent.setAgentCompanyName(agentCompany.getCompanyName());
                    agent.setCreateUser(managerId);
                    agent.setCreateTime(new Date());
                    agentModel.insert(agent);
                }else {
                    //缺少参数
                    return -1;
                }
            }
            //保存过户信息
            transfer.setId(idWorker.nextId());
            transfer.setOrderId(order.getId());
            transfer.setAutoId(order.getCarId());
            transfer.setCommissionPhoto(object.getString("commissionPhoto"));
            transfer.setCreateTime(new Date());
            transfer.setCreateUser(managerId);
            transfer.setStatus("1");
            transfer.setMoveToWhere(autoAuction.getMoveToWhere());
            //车辆状态更改为 12 过户中
            auto.setStatus("12");
            carAutoModel.updateByPrimaryKeySelective(auto);
            //保存车辆的轨迹日志
            CarAutoLog autoLog=new CarAutoLog();
            autoLog.setId(idWorker.nextId());
            autoLog.setAutoId(auto.getId());
            autoLog.setStatus(auto.getStatus());
            autoLog.setUserType("2");
            autoLog.setUserId(managerId);
            autoLog.setTime(new Date());
            autoLogModel.insert(autoLog);

            if (carAutoTransferModel.insertSelective(transfer)>0){
                //保存过户日志
                CarAutoTransferLog transferLog=new CarAutoTransferLog();
                transferLog.setId(idWorker.nextId());
                transferLog.setTransferId(transfer.getId());
                transferLog.setType(transfer.getStatus());
                transferLog.setTime(new Date());
                transferLog.setHandlePerson(managerId);
                transferLog.setFileType("1");
                transferLog.setFileUrl(object.getString("commissionPhoto"));
                transferLog.setHandleTime(new Date());
                result=carAutoTransferLogModel.insertSelective(transferLog);
            }
        }
        return result;
    }

    public CarAutoTransfer selectByOrderIdAndAutoId(Long orderId, Long autoId){
        return carAutoTransferModel.selectByOrderId(orderId,autoId);
    }
}