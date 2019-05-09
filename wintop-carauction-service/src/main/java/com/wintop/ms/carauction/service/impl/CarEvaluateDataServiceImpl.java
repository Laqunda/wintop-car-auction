package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateData;
import com.wintop.ms.carauction.model.CarEvaluateDataModel;
import com.wintop.ms.carauction.service.ICarEvaluateDataService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

@Service("carEvaluateDataService")
public class CarEvaluateDataServiceImpl implements ICarEvaluateDataService {

    @Resource
    private CarEvaluateDataModel carEvaluateDataModel;

    private IdWorker idWorker = new IdWorker(10);

    @Transactional
    @Override
    public ServiceResult<Map<String,Object>> insertSelective(CarEvaluateData carEvaluateData) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();

        Integer count = null;
        try {
            carEvaluateData.setId(idWorker.nextId());
            result.setSuccess(true);
            result.setResult(Collections.singletonMap("count",carEvaluateDataModel.insertSelective(carEvaluateData)));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
}
