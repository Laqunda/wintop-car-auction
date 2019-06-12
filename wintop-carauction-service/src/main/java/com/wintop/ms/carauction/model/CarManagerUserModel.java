package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.mapper.read.*;
import com.wintop.ms.carauction.mapper.write.ICarManagerUserWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CarManagerUserModel {
    @Autowired
    private ICarManagerUserReadDao readDao;
    @Autowired
    private ICarManagerUserWriteDao writeDao;
    @Autowired
    private ICarCenterReadDao centerReadDao;
    @Autowired
    private ICarCenterStoreReadDao centerStoreReadDao;
    @Autowired
    private ICarAgentCompanyReadDao companyReadDao;
    @Autowired
    private ICarAgentCompanyStoreReadDao companyStoreReadDao;
    @Autowired
    private ICarStoreReadDao storeReadDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarManagerUser> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarManagerUser selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return writeDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarManagerUser carManagerUser){
        return writeDao.insert(carManagerUser);
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(CarManagerUser carManagerUser){
        return writeDao.updateByPrimaryKey(carManagerUser);
    }

    /**
     * 登录查询记录
     */
    public CarManagerUser selectByUserId(String userKey){
        return readDao.selectByUserId(userKey);
    }

    /***
     * 修改头像
     * @param id
     * @param userPhoto
     * @return
     */
    public int updateUserPhoto(Long id, String userPhoto) {
        Map map = new HashMap();
        map.put("id",id);
        map.put("userPhoto",userPhoto);
        return writeDao.updateUserPhoto(map);
    }

    /**
     * 查询某个子部门下的所有用户
     * @param departmentId
     * @return
     */
    public List<CommonNameVo> selectAllManagerUser(Long departmentId){
        return readDao.selectAllManagerUser(departmentId);
    }

    /**
     * 根据角色类型获取当前哟凝固所属的中心或者代办公司
     * @param roleTypeId
     * @param departmentId
     * @return
     */
    public String getDepartmentName(Long roleTypeId,Long departmentId){
        String departmentName = "";
        if(roleTypeId.longValue()==1l){
            departmentName = "平台";
        }else if(roleTypeId.longValue()==2l){
            CarCenter center = centerReadDao.selectByPrimaryKey(departmentId);
            if(center != null){
                departmentName = center.getCenterName();
            }
        }else if(roleTypeId.longValue()==3l){
            CarStore store = storeReadDao.selectByPrimaryKey(departmentId);
            if(store!=null){
                departmentName = store.getName();
            }
        }else if(roleTypeId.longValue()==4l){
            CarAgentCompany company = companyReadDao.selectById(departmentId);
            if(company!=null){
                departmentName = company.getCompanyName();
            }
        }
        return departmentName;
    }

    public Long getRegionId(Long roleTypeId,Long departmentId){
        Long regionId = null;
        if(roleTypeId.longValue()==2l){
            CarCenter center = centerReadDao.selectByPrimaryKey(departmentId);
            regionId = center.getRegionId();
        }else if(roleTypeId.longValue()==3l){
            CarStore store = storeReadDao.selectByPrimaryKey(departmentId);
            regionId = store.getRegionId();
        }
        return regionId;
    }

    /**
     * 查询管理店铺范围
     * @param roleType
     * @param departmentId
     * @return
     */
    public List<Long> queryStoreScope(Long roleType,Long departmentId){
        List<Long> storeIds = new ArrayList<>();
        if(roleType.longValue()==1l){
            //storeIds=storeModel.selectAllStoreIds();
        }else if(roleType.longValue()==2l){
            storeIds=centerStoreReadDao.selectAllStoreIds(departmentId);
        }else if(roleType.longValue()==3l){
            storeIds.add(departmentId);
        }else if(roleType.longValue()==4l){
            storeIds=companyStoreReadDao.selectAllStoreIds(departmentId);
        }
        return storeIds;
    }

    /**
     * 根据用户审批车辆权限查询管理店铺范围
     * @param map
     * @return
     */
    public List<Long> queryStoreScope(Map<String,Object> map){
        List<Long> storeIds = storeReadDao.queryStoreListByRole(map);
        return storeIds;
    }
}
