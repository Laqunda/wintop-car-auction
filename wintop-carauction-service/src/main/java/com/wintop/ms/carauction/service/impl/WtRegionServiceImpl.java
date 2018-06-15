package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtRegion;
import com.wintop.ms.carauction.model.WtRegionModel;
import com.wintop.ms.carauction.service.IWtRegionService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liangtingsen on 2018/3/6.
 * 地区区域表
 */
@Service
public class WtRegionServiceImpl implements IWtRegionService {
    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private WtRegionModel wtRegionModel;


    @Override
    public ServiceResult<WtRegion> findById(Long id) {
        WtRegion wtRegion = wtRegionModel.findById(id);
        return new ServiceResult<>(true,wtRegion);
    }

    @Override
    public ServiceResult<WtRegion> findByCode(String code) {
        WtRegion wtRegion = wtRegionModel.findByCode(code);
        return new ServiceResult<>(true,wtRegion);
    }

    @Override
    public ServiceResult<List<WtRegion>> findAll(String status) {
        List<WtRegion> list = wtRegionModel.findAll(status);
        return new ServiceResult<>(true,list);
    }

    @Override
    @Transactional
    public void saveOne(WtRegion wtRegion) {
        wtRegion.setId(idWorker.nextId());
        wtRegion.setEditTime(new Timestamp(System.currentTimeMillis()));
        wtRegionModel.saveOne(wtRegion);
    }

    @Override
    @Transactional
    public void updateOne(WtRegion wtRegion) {
        wtRegion.setEditTime(new Timestamp(System.currentTimeMillis()));
        wtRegionModel.updateOne(wtRegion);
    }
}
