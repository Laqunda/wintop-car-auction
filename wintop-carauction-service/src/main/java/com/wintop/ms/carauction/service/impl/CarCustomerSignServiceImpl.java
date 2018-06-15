package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLog;
import com.wintop.ms.carauction.entity.CarCustomerSign;
import com.wintop.ms.carauction.entity.CarCustomerSignLog;
import com.wintop.ms.carauction.entity.WtAppUser;
import com.wintop.ms.carauction.model.AppUserModel;
import com.wintop.ms.carauction.model.CarCustomerLogModel;
import com.wintop.ms.carauction.model.CarCustomerSignLogModel;
import com.wintop.ms.carauction.model.CarCustomerSignModel;
import com.wintop.ms.carauction.service.ICarCustomerSignService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 用户签约服务
 */
@Service
public class CarCustomerSignServiceImpl implements ICarCustomerSignService{

    Logger logger = LoggerFactory.getLogger(CarCustomerSignServiceImpl.class);

    IdWorker idWorker = new IdWorker(10);

    @Autowired
    private CarCustomerSignModel signModel;
    @Autowired
    private CarCustomerSignLogModel signLogModel;
    @Autowired
    private AppUserModel appUserModel;
    @Resource
    private CarCustomerLogModel logModel;

    @Override
    public ServiceResult<CarCustomerSign> findById(Long id) {
        logger.info("查询签名表详情");
        ServiceResult<CarCustomerSign> result = new ServiceResult<>();
        result.setResult(signModel.findById(id));
        result.setSuccess("0","查询成功");
        return result;
    }

    @Override
    public ServiceResult<CarCustomerSign> findByCustomerId(Long customerId) {
        logger.info("查询用户签约数据");
        ServiceResult<CarCustomerSign> listServiceResult = new ServiceResult<>();
        CarCustomerSign carCustomerSign = signModel.findByCustomerId(customerId);
        if (carCustomerSign!=null) {
            Map<String,Object> map =new HashMap<>();
            map.put("signId",carCustomerSign.getId());
            List<CarCustomerSignLog> logList = signLogModel.findBySignId(map);
            if (logList!=null && logList.size()>0) {
                carCustomerSign.setSignLog(logList.get(0));
            }
        }
        listServiceResult.setResult(carCustomerSign);
        listServiceResult.setSuccess("0","查询成功");
        return listServiceResult;
    }

    @Override
    public ServiceResult<List<CarCustomerSign>> findByParams(Map params) {
        logger.info("条件查询会员签约数据");
        ServiceResult<List<CarCustomerSign>> result = new ServiceResult<>();
        result.setResult(signModel.findByParams(params));
        result.setSuccess("0","查询成功");
        return result;
    }

    @Override
    @Transactional
    public ServiceResult<CarCustomerSign> insert(CarCustomerSign carCustomerSign, CarCustomerSignLog carCustomerSignLog) {
        ServiceResult<CarCustomerSign> result = new ServiceResult<>();
        //查询一下用户状态
        WtAppUser wtAppUser = appUserModel.selectUserById(carCustomerSign.getCustomerId());
        if (wtAppUser!=null) {
            logger.info("新增签约数据");
            //查询此人签约记录，则为修改
            CarCustomerSign carCustomerSign_old = signModel.findByCustomerId(carCustomerSign.getCustomerId());
            if (carCustomerSign_old==null) {
                carCustomerSign.setId(idWorker.nextId());
                carCustomerSign.setSignatureTime(new Date());
                carCustomerSign.setStatus("1");
                signModel.insert(carCustomerSign);
            }else {
                carCustomerSign.setId(carCustomerSign_old.getId());
                carCustomerSign.setSignatureTime(new Date());
                carCustomerSign.setStatus("1");
                signModel.updateByPrimaryKeySelective(carCustomerSign);
            }

            carCustomerSignLog.setId(idWorker.nextId());
            carCustomerSignLog.setCreateTime(new Date());
            carCustomerSignLog.setSignId(carCustomerSign.getId());
            carCustomerSignLog.setType("1");
            signLogModel.insert(carCustomerSignLog);
            if (wtAppUser.getStatus()!=null && AppUserStatusEnum.SIG_ERROR.value().equals(wtAppUser.getStatus())){
                //修改用户状态为签约待审核
                wtAppUser.setStatus(AppUserStatusEnum.SIG_ING.value());
                carCustomerSign.setUserStatus(AppUserStatusEnum.SIG_ING.value());
            }else {
                //修改用户状态为已签约--未缴纳保证金
                wtAppUser.setStatus(AppUserStatusEnum.SIG_SUCCESS.value());
                carCustomerSign.setUserStatus(AppUserStatusEnum.SIG_SUCCESS.value());
            }
            wtAppUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            appUserModel.updateUserStatus(wtAppUser);

            //保存客户操作日志
            CarCustomerLog log = new CarCustomerLog();
            log.setId(idWorker.nextId());
            log.setUserId(carCustomerSign.getCustomerId());
            log.setStatus(AppUserStatusEnum.SIG_SUCCESS.value());
            log.setFile(carCustomerSignLog.getPicFileUrl());
            log.setMsg(AppUserStatusEnum.SIG_SUCCESS.getRemark());
            log.setEditType("1");
            log.setEditTime(new Date());
            log.setLogId(carCustomerSign.getId());
            log.setEditUserId(carCustomerSign.getCustomerId());
            logModel.insertSelective(log);

            carCustomerSign.setSignLog(carCustomerSignLog);
            result.setResult(carCustomerSign);
            result.setSuccess("0", "新增签约成功");
        }
        return result;
    }

    @Override
    @Transactional
    public ServiceResult<CarCustomerSign> saveAuth(CarCustomerSign carCustomerSign,CarCustomerSignLog carCustomerSignLog) {
        logger.info("审核签约数据");
        ServiceResult<CarCustomerSign> result = new ServiceResult<>();

        //修改用户状态
        WtAppUser wtAppUser = new WtAppUser();
        wtAppUser.setId(carCustomerSign.getCustomerId());
        wtAppUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //保存客户操作日志
        CarCustomerLog log=new CarCustomerLog();

        if ("2".equals(carCustomerSign.getStatus())) {
            carCustomerSignLog.setId(idWorker.nextId());
            carCustomerSignLog.setSignId(carCustomerSign.getId());
            carCustomerSignLog.setType("2");
            carCustomerSignLog.setCreateTime(new Date());
            signLogModel.insert(carCustomerSignLog);
            wtAppUser.setStatus("7");

            log.setStatus("7");
            log.setFile(carCustomerSignLog.getPicFileUrl());
            log.setMsg("签约审核通过");
        }else {
            wtAppUser.setStatus("6");

            log.setStatus("6");
            log.setFile(carCustomerSignLog.getPicFileUrl());
            log.setMsg("签约审核不通过");
        }
        //保存用户状态
        appUserModel.updateUserStatus(wtAppUser);

        //保存签约信息
        carCustomerSign.setAuthTime(new Date());
        signModel.update(carCustomerSign);

        //保存日志
        log.setId(idWorker.nextId());
        log.setUserId(carCustomerSign.getCustomerId());
        log.setEditType("2");
        log.setEditTime(new Date());
        log.setLogId(carCustomerSign.getId());
        log.setEditUserId(carCustomerSign.getAuthManager());
        logModel.insertSelective(log);

        carCustomerSign.setSignLog(carCustomerSignLog);
        result.setSuccess("0","审核签约成功");
        result.setResult(carCustomerSign);
        return result;
    }

    public CarCustomerSign querySignByUserId(Long customerId){
        return signModel.querySignByUserId(customerId);
    }
}
