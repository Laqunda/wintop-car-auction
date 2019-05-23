package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.entity.CarOrderRetail;
import com.wintop.ms.carauction.entity.CarSaleOrder;
import com.wintop.ms.carauction.model.CarAutoLogModel;
import com.wintop.ms.carauction.model.CarAutoModel;
import com.wintop.ms.carauction.model.CarOrderRetailModel;
import com.wintop.ms.carauction.model.CarSaleOrderModel;
import com.wintop.ms.carauction.service.ICarOrderRetailService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("carOrderRetailService")
public class CarOrderRetailServiceImpl implements ICarOrderRetailService {

    @Autowired
    private CarOrderRetailModel carOrderRetailModel;
    @Autowired
    private CarAutoModel carAutoModel;
    @Autowired
    private CarAutoLogModel autoLogModel;
    @Autowired
    private CarSaleOrderModel carSaleOrderModel;

    private IdWorker idWorker = new IdWorker(10);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective(CarOrderRetail record) {
        //保存零售车辆
        carOrderRetailModel.insertSelective(record);
        //更改车辆状态
        CarAuto auto=carAutoModel.selectByPrimaryKey(record.getCarId());
        CarAuto carAuto = new CarAuto();
        auto.setStatus(CarStatusEnum.EXCHANGE_COMPLETE.value());
        auto.setSaleFlag("1");
        auto.setUpdateTime(new Date());
        carAutoModel.updateByPrimaryKeySelective(auto);
        //保存车辆的轨迹日志
        CarAutoLog autoLog=new CarAutoLog();
        autoLog.setId(idWorker.nextId());
        autoLog.setAutoId(auto.getId());
        autoLog.setMsg("零售出售");
        autoLog.setStatus(auto.getStatus());
        autoLog.setUserType("2");
        autoLog.setUserId(record.getCreateUser());
        autoLog.setTime(new Date());
        autoLogModel.insert(autoLog);
    }

    @Override
    public CarSaleOrder selectRetailById(Long id) {
        return carSaleOrderModel.selectRetailById(id);
    }
}
