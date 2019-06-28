package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.CarStore;
import com.wintop.ms.carauction.model.CarCenterStoreModel;
import com.wintop.ms.carauction.model.CarManagerUserModel;
import com.wintop.ms.carauction.model.CarStoreModel;
import com.wintop.ms.carauction.service.ICarStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CarStoreServiceImpl implements ICarStoreService {
    @Autowired
    private CarStoreModel storeModel;
    @Autowired
    private CarManagerUserModel managerUserModel;
    @Autowired
    private CarCenterStoreModel centerStoreModel;

    @Override
    public int countByExample(Map<String, Object> map) {
        return storeModel.countByExample(map);
    }

    @Override
    public List<CarStore> selectByExample(Map<String, Object> map) {
        return storeModel.selectByExample(map);
    }

    @Override
    public CarStore selectByPrimaryKey(Long id) {
        return storeModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return storeModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarStore carStore) {
        return storeModel.insert(carStore);
    }

    @Override
    public int updateByPrimaryKey(CarStore carStore) {
        return storeModel.updateByPrimaryKey(carStore);
    }

    /**
     * 查询所有店铺
     * @return
     */
    @Override
    public List<CommonNameVo> selectAllStore(){
        return storeModel.selectAllStore();
    }

    /**
     * 查询所有店铺
     * @return
     */
    @Override
    public List<CommonNameVo> selectAllStore(Long userId){
        List<CommonNameVo> list = null;
        CarManagerUser managerUser = managerUserModel.selectByPrimaryKey(userId);
        if (managerUser.getRoleTypeId()==3L){
            CarStore carStore = storeModel.selectByPrimaryKey(managerUser.getDepartmentId());
            list = new ArrayList<>();
            CommonNameVo vo = new CommonNameVo();
            vo.setId(carStore.getId());
            vo.setCode(carStore.getCode());
            vo.setName(carStore.getName());
            list.add(vo);
        }else if (managerUser.getRoleTypeId()==1L){
            list = storeModel.selectAllStore();
        }else if (managerUser.getRoleTypeId()==2L){
            list = centerStoreModel.selectAllStore(managerUser.getDepartmentId());
        }
        return list;
    }

    /**
     * 删除店铺
     * @param delPerson
     * @param id
     * @return
     */
    @Override
    public int updateStoreDel(Long delPerson,Long id){
        CarStore carStore = new CarStore();
        carStore.setId(id);
        carStore.setStatus("3");
        carStore.setDelTime(new Date());
        carStore.setDelPerson(delPerson);
        return storeModel.updateByPrimaryKey(carStore);
    }

    @Override
    public Long idByExample(Map<String, Object> map) {
        return storeModel.idByExample(map);
    }

    @Override
    public List<CarStore> selectForCenterByCondition(Map<String, Object> map) {
        return storeModel.selectForCenterByCondition(map);
    }
}
