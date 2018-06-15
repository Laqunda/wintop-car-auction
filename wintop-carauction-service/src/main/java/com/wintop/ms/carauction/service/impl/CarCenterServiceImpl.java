package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.CarCenterStore;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.model.CarCenterModel;
import com.wintop.ms.carauction.model.CarCenterStoreModel;
import com.wintop.ms.carauction.model.CarStoreModel;
import com.wintop.ms.carauction.service.ICarCenterService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CarCenterServiceImpl implements ICarCenterService {
    @Autowired
    private CarCenterModel centerModel;
    @Autowired
    private CarCenterStoreModel centerStoreModel;
    @Autowired
    private CarStoreModel carStoreModel;

    private IdWorker idWorker = new IdWorker(10);
    /**
     * 根据条件查询记录总数
     */
    @Override
    public int countByExample(Map<String,Object> map){
        return centerModel.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    @Override
    public List<CarCenter> selectByExample(Map<String,Object> map){
        List<CarCenter> centerList = centerModel.selectByExample(map);
        for(CarCenter center:centerList){
            List<CommonNameVo> centerStoreList = centerStoreModel.selectAllStore(center.getId());
            center.setStoreList(centerStoreList);
        }
        return centerList;
    }

    /**
     * 根据主键查询记录
     */
    @Override
    public CarCenter selectByPrimaryKey(Long id){
        CarCenter carCenter = centerModel.selectByPrimaryKey(id);
        //List<CommonNameVo> storeList = carStoreModel.selectAllStore();
        List<CommonNameVo> centerStoreList = centerStoreModel.selectAllStore(id);
        /*for(CommonNameVo store:storeList){
            store.setChecked("0");
            for(CommonNameVo centerStore:centerStoreList){
                if(store.getId().longValue()==centerStore.getId().longValue()){
                    store.setChecked("1");
                    break;
                }
            }
        }*/
        carCenter.setStoreList(centerStoreList);
        return carCenter;
    }

    /**
     * 根据主键删除记录
     */
    @Override
    public int deleteByPrimaryKey(Long id){
        return centerModel.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    @Override
    @Transactional
    public int insert(CarCenter carCenter){
        carCenter.setId(idWorker.nextId());
        centerStoreModel.saveBatchStore(carCenter.getId(),carCenter.getStoreIds());
        int count = centerModel.insert(carCenter);
        return count;
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    @Override
    public int updateByPrimaryKeySelective(CarCenter carCenter){
        int count = centerModel.updateByPrimaryKeySelective(carCenter);
        if(count==1){
            centerStoreModel.deleteByCenterId(carCenter.getId());
            centerStoreModel.saveBatchStore(carCenter.getId(),carCenter.getStoreIds());
        }
        return count;
    }

    /**
     * 根据主键更新记录
     */
    @Override
    @Transactional
    public int updateByPrimaryKey(CarCenter carCenter){
        int count = centerModel.updateByPrimaryKey(carCenter);
        return count;
    }

    /**
     * 删除记录
     */
    @Override
    @Transactional
    public int updateCenterDel(Long delPerson,Long id){
        centerStoreModel.deleteByCenterId(id);
        CarCenter carCenter = new CarCenter();
        carCenter.setId(id);
        carCenter.setStatus("3");
        carCenter.setDelTime(new Date());
        carCenter.setDelPerson(delPerson);
        return centerModel.updateByPrimaryKeySelective(carCenter);
    }
}
