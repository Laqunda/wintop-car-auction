package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarStore;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.mapper.read.ICarStoreReadDao;
import com.wintop.ms.carauction.mapper.write.ICarStoreWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarStoreModel {
    @Autowired
    private ICarStoreReadDao storeReadDao;
    @Autowired
    private ICarStoreWriteDao storeWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return storeReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarStore> selectByExample(Map<String,Object> map){
        return storeReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarStore selectByPrimaryKey(Long id){
        return storeReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return storeWriteDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarStore carStore){
        return storeWriteDao.insert(carStore);
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(CarStore carStore){
        return storeWriteDao.updateByPrimaryKey(carStore);
    }

    /**
     * 查询所有店铺
     * @return
     */
    public List<CommonNameVo> selectAllStore(){
        return storeReadDao.selectAllStore();
    }

    /**
     * 查询所有店铺ID集合
     * @return
     */
    public List<Long> selectAllStoreIds(){
        return storeReadDao.selectAllStoreIds();
    }

    /**
     * 查询所有记录集
     */
    public List<CommonNameVo> selectByCenter(Long centerId){
        return storeReadDao.selectByCenter(centerId);
    }
}
