package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.entity.CarManagerRolePage;
import com.wintop.ms.carauction.model.CarManagerRolePageModel;
import com.wintop.ms.carauction.service.ICarManagerRolePageService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarManagerRolePageServiceImpl implements ICarManagerRolePageService {
    @Autowired
    private CarManagerRolePageModel rolePageModel;
    private IdWorker idWorker=new IdWorker(10);
    @Override
    public List<CarManagerRolePage> selectAll(Long roleId) {
        return rolePageModel.selectAll(roleId);
    }

    @Override
    public List<String> selectAllPages(Long roleId) {
        return rolePageModel.selectAllPages(roleId);
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        return rolePageModel.deleteByRoleId(roleId);
    }

    @Override
    public int insert(CarManagerRolePage record) {
        return rolePageModel.insert(record);
    }

    /**
     * 角色权限分配
     *
     */
    @Transactional
    public int editRolePage(JSONObject object){
        int result;
        String pageIds=object.getString("pageIds");
        Long roleId=object.getLong("roleId");
        rolePageModel.deleteByRoleId(roleId);
        List<CarManagerRolePage> rolePages=new ArrayList<>();
        if(StringUtils.isNotBlank(pageIds)) {
            String[] pageIdArray = pageIds.split(",");
            for (int i = 0; i < pageIdArray.length; i++) {
                CarManagerRolePage rolePage=new CarManagerRolePage();
                rolePage.setId(idWorker.nextId());
                rolePage.setPageId(Long.valueOf(pageIdArray[i]));
                rolePage.setRoleId(roleId);
                rolePages.add(rolePage);
            }
            result=rolePageModel.batchInsert(rolePages);
        }else {
            result = -1;
        }
        return result;
    }
}
