package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppInfo;
import com.wintop.ms.carauction.model.CarAppInfoModel;
import com.wintop.ms.carauction.service.ICarAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Service
public class CarAppInfoServiceImpl implements ICarAppInfoService {
    @Autowired
    private CarAppInfoModel model;

    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarAppInfo>> selectByExample(Map<String, Object> map) {
        List<CarAppInfo> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public CarAppInfo selectById(Long id) {
        CarAppInfo carAppInfo =  model.selectById(id);
        return carAppInfo;
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarAppInfo carAppInfo) {
        Integer count = model.insert(carAppInfo);
        return new ServiceResult<>(true,count);
    }

    @Override
    public Integer updateByIdSelective(CarAppInfo carAppInfo) {
        Integer count = model.updateByIdSelective(carAppInfo);
        return count;
    }

    @Override
    public ServiceResult<Integer> updateById(CarAppInfo carAppInfo) {
        Integer count = model.updateById(carAppInfo);
        return new ServiceResult<>(true,count);
    }
    /**
     * 根据appId获取版本号
     */
    @Override
    public ServiceResult<CarAppInfo> selectVersionByAppId(String appId){
        ServiceResult<CarAppInfo> result=new ServiceResult<>();
        try {
            CarAppInfo carAppInfo=model.selectVersionByAppId(appId);
            if(carAppInfo!=null){
                result.setResult(carAppInfo);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 客户端类型：1卖家，2买家，3代办，4中心
     * @param type
     * @return
     */
    public CarAppInfo selectByType(String type){
        return model.selectByType(type);
    }

}
