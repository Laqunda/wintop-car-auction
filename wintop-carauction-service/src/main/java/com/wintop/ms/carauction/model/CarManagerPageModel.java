package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarManagerPage;
import com.wintop.ms.carauction.entity.TreeEntity;
import com.wintop.ms.carauction.mapper.read.ICarManagerPageReadDao;
import com.wintop.ms.carauction.mapper.write.ICarManagerPageWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarManagerPageModel {
    @Autowired
    private ICarManagerPageReadDao pageReadDao;
    @Autowired
    private ICarManagerPageWriteDao pageWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return pageReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarManagerPage> selectByExample(Map<String,Object> map){
        return pageReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarManagerPage selectByPrimaryKey(Long id){
        return pageReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return pageWriteDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarManagerPage record){
        return pageWriteDao.insert(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(CarManagerPage record){
        return pageWriteDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(CarManagerPage record){
        return pageWriteDao.updateByPrimaryKey(record);
    }

    /**
     * 查询所有页面的tree
     * @return
     */
    public List<TreeEntity> getPageTreeList(Long roleId){
        return pageReadDao.getPageTreeList(roleId);
    }
    /**
     * 根据父节点查询
     */
    public List<TreeEntity> getPageTreeByPId(Map<String, Object> map){
        return pageReadDao.getPageTreeByPId(map);
    }
}
