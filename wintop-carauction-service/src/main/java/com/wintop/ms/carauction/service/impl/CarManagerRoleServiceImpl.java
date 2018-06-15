package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarManagerRole;
import com.wintop.ms.carauction.entity.CarManagerRoleType;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarManagerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarManagerRoleServiceImpl implements ICarManagerRoleService {
    @Autowired
    private CarManagerRoleTypeModel typeModel;
    @Autowired
    private CarManagerRoleModel roleModel;
    @Autowired
    private CarAgentCompanyModel companyModel;
    @Autowired
    private CarCenterModel centerModel;
    @Autowired
    private CarStoreModel storeModel;

    /**
     * 根据条件查询记录集
     */
    @Override
    public List<CarManagerRoleType> selectByExample(Map<String,Object> map){
        List<CarManagerRoleType> typeList = typeModel.selectByExample(map);
        for(CarManagerRoleType roleType:typeList){
            //***查询角色列表
            Map<String,Object> map1 = new HashMap<>();
            map1.put("typeId",roleType.getId());
            List<CarManagerRole> roleList = roleModel.selectByExample(map1);
            roleType.setRoleList(roleList);
            /**
             * 查询所有中心公司店铺列表
             * 1平台，2中心，3经销店，4代办公司
             */
            if(roleType.getId()==1){
                List<CommonNameVo> storeVoList = new ArrayList<>();
                CommonNameVo storeVo = new CommonNameVo(1l,"平台");
                storeVoList.add(storeVo);
                roleType.setStoreVoList(storeVoList);
            }else if(roleType.getId()==2){
                List<CommonNameVo> storeVoList = centerModel.selectAllCenter();
                roleType.setStoreVoList(storeVoList);
            }else if(roleType.getId()==3){
                List<CommonNameVo> storeVoList = storeModel.selectAllStore();
                roleType.setStoreVoList(storeVoList);
            }else if(roleType.getId()==4){
                List<CommonNameVo> storeVoList = companyModel.selectAllAgentCompany();
                roleType.setStoreVoList(storeVoList);
            }
        }
        return typeList;
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    public CarManagerRole getManagerRole(Long id){
        return roleModel.selectById(id);
    }

    /**
     * 根据userId查询记录
     */
    public CarManagerRole selectByUserId(Long userId){
        return roleModel.selectByUserId(userId);
    }

    /**
     * 根据Id查询记录
     */
    public CarManagerRole selectById(Long id){
        return roleModel.selectById(id);
    }
}
