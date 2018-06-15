package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerSignLog;
import com.wintop.ms.carauction.model.CarCustomerSignLogModel;
import com.wintop.ms.carauction.service.ICarCustomerSignLogService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 会员签约日志服务
 */
@Service
public class CarCustomerSignLogServiceImpl implements ICarCustomerSignLogService{

    Logger logger = LoggerFactory.getLogger(CarCustomerSignServiceImpl.class);

    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private CarCustomerSignLogModel signLogModel;

    @Override
    public ServiceResult<CarCustomerSignLog> findById(Long id) {
        logger.info("会员签约详情查询");
        ServiceResult<CarCustomerSignLog> result = new ServiceResult<>();
        result.setResult(signLogModel.findById(id));
        result.setSuccess("0","查询成功");
        return result;
    }

    @Override
    public ServiceResult<List<CarCustomerSignLog>> findBySignId(Long signId) {
        logger.info("CarCustomerSignLogServiceImpl.findBySignId");
        ServiceResult<List<CarCustomerSignLog>> result = new ServiceResult<>();
        Map<String,Object> map=new HashMap<>();
        map.put("signId",signId);
        result.setResult(signLogModel.findBySignId(map));
        result.setSuccess("0","查询成功");
        return result;
    }

    @Override
    @Transactional
    public void insert(CarCustomerSignLog carCustomerSignLog) {
        logger.info("CarCustomerSignLogServiceImpl.insert");
        carCustomerSignLog.setId(idWorker.nextId());
        carCustomerSignLog.setCreateTime(new Date());
        signLogModel.insert(carCustomerSignLog);
    }
}
