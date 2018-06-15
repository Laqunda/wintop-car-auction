package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerDeposit;
import com.wintop.ms.carauction.entity.CarCustomerDepositLog;
import com.wintop.ms.carauction.entity.CarCustomerLog;
import com.wintop.ms.carauction.entity.WtAppUser;
import com.wintop.ms.carauction.model.AppUserModel;
import com.wintop.ms.carauction.model.CarCustomerDepositModel;
import com.wintop.ms.carauction.model.CarCustomerLogModel;
import com.wintop.ms.carauction.model.CarFinancePayLogModel;
import com.wintop.ms.carauction.service.ICarCustomerDepositService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户保证金业务层
 * @author zhangzijuan
 * @date 2018-02-08
 */
@Service("carCustomerDepositService")
public class CarCustomerDepositServiceImpl implements ICarCustomerDepositService {
    IdWorker idWorker = new IdWorker(10);
    @Resource
    private CarCustomerDepositModel customerDepositModel;
    @Autowired
    private CarCustomerLogModel carCustomerLogModel;
    @Autowired
    private CarFinancePayLogModel payLogModel;
    @Autowired
    private AppUserModel appUserModel;

    private static final Logger logger = LoggerFactory.getLogger(CarCustomerDepositServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerDeposit> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerDeposit> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerDepositModel.selectByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerDepositModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerDeposit record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerDepositModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerDeposit record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerDepositModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerDeposit record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerDepositModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerDeposit record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerDepositModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存用户保证金
     * @Autor 付陈林
     * @Time 2018-3-13
     * @param record
     * @return
     */
    @Transactional
    public int insertCarCustomerDeposit(CarCustomerDeposit record) {
            //1、增加保证金--支付流水
            payLogModel.insert(record.getPayLog());

            //2、修改用户的状态为保证金待审核
            WtAppUser appUser = new WtAppUser();
            appUser.setId(record.getUserId());
            appUser.setStatus(AppUserStatusEnum.SIG_ING.value());
            appUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            appUserModel.updateUserStatus(appUser);

            //3、增加用户变更日志
            CarCustomerLog carCustomerLog = new CarCustomerLog();
            carCustomerLog.setId(idWorker.nextId());
            carCustomerLog.setUserId(appUser.getId());
            carCustomerLog.setStatus(appUser.getStatus());
            carCustomerLog.setMsg("保证金支付成功");
            carCustomerLog.setEditUserId(appUser.getId());
            carCustomerLog.setEditTime(new Date());
            carCustomerLog.setEditType(record.getPayLog().getCreatePersonType());
            carCustomerLog.setLogId(record.getId());
            carCustomerLogModel.insertSelective(carCustomerLog);
            //4、判断用户是否已存在有效的保证金记录，如果有，则为修改
            Map map = new HashMap();
            map.put("userId",record.getUserId());
            CarCustomerDeposit customerDeposit = customerDepositModel.selectDepositByUserId(map);
            if (customerDeposit!=null){
                record.setId(customerDeposit.getId());
                return customerDepositModel.updateByPrimaryKeySelective(record);
            }else {
                return customerDepositModel.insertSelective(record);
            }
    }
    /**
     * 根据用户ID查询保证金余额
     * @Autor 付陈林
     * @Time 2018-3-5
     * @param userId
     * @return
     */
    @Override
    public ServiceResult<Map<String,Object>> getDepositAmountByUserId(Long userId) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            Map<String,Object> resultMap =new HashMap<>();
            Map map = new HashMap();
            map.put("userId",userId);
            CarCustomerDeposit carCustomerDeposit = customerDepositModel.selectDepositByUserId(map);
            if (carCustomerDeposit!=null) {
                resultMap.put("depositAmount", carCustomerDeposit.getDepositAmount());
                resultMap.put("status", carCustomerDeposit.getStatus());
            }else {
                resultMap.put("depositAmount", 0);
                resultMap.put("status","0");
            }
            result.setResult(resultMap);
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询保证金列表
     * @Autor zhangzijuan
     * @param map
     * @return
     */
    public List<CarCustomerDeposit> selectDepositList(Map<String,Object> map){
        return customerDepositModel.selectDepositList(map);
    }
    /**
     * 查询保证金总数目
     * @Autor zhangzijuan
     * @param map
     * @return
     */
    public Integer  selectDepositCount(Map<String,Object> map){
        return customerDepositModel.selectDepositCount(map);
    }

    public CarCustomerDeposit selectDepositByUserId(Map<String,Object> map){
        return  customerDepositModel.selectDepositByUserId(map);
    }
}