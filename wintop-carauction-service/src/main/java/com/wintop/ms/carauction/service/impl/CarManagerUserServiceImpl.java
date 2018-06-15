package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CarManagerUserServiceImpl implements ICarManagerUserService{
    @Autowired
    private CarManagerUserModel userModel;
    @Autowired
    private CarCenterModel centerModel;
    @Autowired
    private CarCenterStoreModel centerStoreModel;
    @Autowired
    private CarAgentCompanyStoreModel companyStoreModel;
    @Autowired
    private CarStoreModel storeModel;
    @Override
    public int countByExample(Map<String, Object> map) {
        return userModel.countByExample(map);
    }

    @Override
    public List<CarManagerUser> selectByExample(Map<String, Object> map) {
        List<CarManagerUser> userList = userModel.selectByExample(map);
        for(CarManagerUser user:userList){
            user.setDepartmentName(userModel.getDepartmentName(user.getRoleTypeId(),user.getDepartmentId()));
        }
        return userList;
    }

    @Override
    public CarManagerUser selectByPrimaryKey(Long id,boolean detail) {
        CarManagerUser user = userModel.selectByPrimaryKey(id);
        /**
         * 1平台，2中心，3经销店，4代办公司
         */
        if(detail){
            user.setDepartmentName(userModel.getDepartmentName(user.getRoleTypeId(),user.getDepartmentId()));
        }
        return user;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarManagerUser carManagerUser) {
        carManagerUser.setRegionId(userModel.getRegionId(carManagerUser.getRoleTypeId(),carManagerUser.getDepartmentId()));
        return userModel.insert(carManagerUser);
    }

    @Override
    public int updateByPrimaryKey(CarManagerUser carManagerUser) {
        return userModel.updateByPrimaryKey(carManagerUser);
    }

    /**
     * 登录查询记录
     */
    @Override
    public CarManagerUser selectByUserId(String userKey){
        CarManagerUser user = userModel.selectByUserId(userKey);
        if(user != null){
            user.setDepartmentName(userModel.getDepartmentName(user.getRoleTypeId(),user.getDepartmentId()));
        }
        return user;
    }

    /***
     * 修改头像
     * @param id
     * @param userPhoto
     * @return
     */
    @Override
    @Transactional
    public int updateUserPhoto(Long id, String userPhoto) {
        return userModel.updateUserPhoto(id,userPhoto);
    }

    /**
     * 查询某个子部门下的所有用户
     * @param departmentId
     * @return
     */
    @Override
    public List<CommonNameVo> selectAllManagerUser(Long departmentId){
        return userModel.selectAllManagerUser(departmentId);
    }

    /**
     * 查询管理店铺范围
     * @param roleType
     * @param departmentId
     * @return
     */
    public List<Long> queryStoreScope(Long roleType,Long departmentId){
        List<Long> storeIds = userModel.queryStoreScope(roleType, departmentId);
        return storeIds;
    }

    public List<Long> queryStoreScope(Long userId){
        CarManagerUser user = userModel.selectByPrimaryKey(userId);
        List<Long> storeIds = userModel.queryStoreScope(user.getRoleTypeId(), user.getDepartmentId());
        return storeIds;
    }
}
