package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;
import com.wintop.ms.carauction.model.CarCustomerFollowAutoModel;
import com.wintop.ms.carauction.service.ICarCustomerFollowAutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户关注车辆业务层
 * @author zhangzijuan
 * @date 2018-02-27
 */
@Service("carCustomerFollowAutoService")
public class CarCustomerFollowAutoServiceImpl implements ICarCustomerFollowAutoService {
    @Resource
    private CarCustomerFollowAutoModel followAutoModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerFollowAutoServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerFollowAuto> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerFollowAuto> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.selectByPrimaryKey(id));
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
            result.setResult(followAutoModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerFollowAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerFollowAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerFollowAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerFollowAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据auto_id和user_id查询数据
     */
    @Override
    public ServiceResult<CarCustomerFollowAuto> selectCustomerFollow(Long autoId, Long userId) {
        ServiceResult<CarCustomerFollowAuto> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.selectCustomerFollow(autoId,userId));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 收藏接口
     */
    @Override
    public ServiceResult<Integer> insertCustomerCollection(CarCustomerFollowAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.insertCustomerCollection(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 取消收藏接口
     */
    @Override
    public ServiceResult<Integer> deleteCustomerCollection(Map<String,Object> map) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(followAutoModel.deleteCustomerCollection(map));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询用户现场\线上关注车辆列表
     * @param map
     * @return
     */
    @Override
    public List<CarCustomerFollowAuto> queryUserFollowList(Map<String,Object> map){
        return followAutoModel.queryUserFollowList(map);
    }

    /**
     * 查询用户现场\线上关注车辆列表数量
     * @param map
     * @return
     */
    @Override
    public int queryUserFollowCount(Map<String,Object> map){
        return followAutoModel.queryUserFollowCount(map);
    }
}