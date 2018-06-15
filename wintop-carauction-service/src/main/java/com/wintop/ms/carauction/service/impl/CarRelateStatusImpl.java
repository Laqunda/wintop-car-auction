package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarRelateStatus;
import com.wintop.ms.carauction.model.CarRelateStatusModel;
import com.wintop.ms.carauction.service.ICarRelateStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-26
 */
@Service
public class CarRelateStatusImpl  implements ICarRelateStatusService{

    Logger logger = LoggerFactory.getLogger(CarRelateStatusImpl.class);

    @Resource
    private CarRelateStatusModel relateStatusModel;

    @Override
    public ServiceResult<List<CarRelateStatus>> selectAllStatus(Map<String, Object> map) {
        ServiceResult<List<CarRelateStatus>> result=new ServiceResult<>();
        try {
            List<CarRelateStatus> relateStatuses=relateStatusModel.selectAllStatus(map);
            if(relateStatuses!=null && relateStatuses.size()>0){
                result.setResult(relateStatuses);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
