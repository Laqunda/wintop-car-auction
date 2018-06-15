package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCenterStore;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.mapper.read.ICarCenterStoreReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCenterStoreWriteDao;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarCenterStoreModel {
    @Autowired
    private ICarCenterStoreReadDao readDao;
    @Autowired
    private ICarCenterStoreWriteDao writeDao;

    private IdWorker idWorker = new IdWorker(10);

    /**
     * 查询所有记录集
     */
    public List<CommonNameVo> selectAllStore(Long centerId){
        return readDao.selectAllStore(centerId);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByCenterId(Long centerId){
        return writeDao.deleteByCenterId(centerId);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarCenterStore carCenterStore){
        return writeDao.insert(carCenterStore);
    }

    /**
     * 保存中心管理的店铺
     * @param centerId
     * @param storeIds
     * @return
     */
    public int saveBatchStore(Long centerId,String storeIds){
        int count = 0;
        if(StringUtils.isNotEmpty(storeIds)){
            for(String storeId:storeIds.split(",")){
                CarCenterStore centerStore = new CarCenterStore();
                centerStore.setId(idWorker.nextId());
                centerStore.setCenterId(centerId);
                centerStore.setStoreId(Long.valueOf(storeId));
                count += writeDao.insert(centerStore);
            }
        }
        return count;
    }

    /**
     * 查询单个中心所有店铺ID集合
     * @return
     */
    public List<Long> selectAllStoreIds(Long centId){
        return readDao.selectAllStoreIds(centId);
    }

}
