package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAgentCompanyStore;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.mapper.read.ICarAgentCompanyStoreReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAgentCompanyStoreWriteDao;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarAgentCompanyStoreModel {
    @Autowired
    private ICarAgentCompanyStoreReadDao readDao;
    @Autowired
    private ICarAgentCompanyStoreWriteDao writeDao;

    private IdWorker idWorker = new IdWorker(10);

    /**
     * 查询所有记录集
     */
    public List<CommonNameVo> selectAllStore(Long companyId){
        return readDao.selectAllStore(companyId);
    }

    /**
     * 根据公司id删除记录
     */
    public int deleteByCompanyId(Long companyId){
        return writeDao.deleteByCompanyId(companyId);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAgentCompanyStore companyStore){
        return writeDao.insert(companyStore);
    }

    /**
     * 保存代办管理的店铺
     * @param companyId
     * @param storeIds
     * @return
     */
    public int saveBatchStore(Long companyId,String storeIds){
        int count = 0;
        if(StringUtils.isNotEmpty(storeIds)){
            for(String storeId:storeIds.split(",")){
                CarAgentCompanyStore companyStore = new CarAgentCompanyStore();
                companyStore.setId(idWorker.nextId());
                companyStore.setCompanyId(companyId);
                companyStore.setStoreId(Long.valueOf(storeId));
                count += writeDao.insert(companyStore);
            }
        }
        return count;
    }

    /**
     * 查询单个代办公司所有店铺ID集合
     * @return
     */
    public List<Long> selectAllStoreIds(Long companyId){
        return readDao.selectAllStoreIds(companyId);
    }
}
