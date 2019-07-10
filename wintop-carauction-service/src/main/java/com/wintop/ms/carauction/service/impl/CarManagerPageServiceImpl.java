package com.wintop.ms.carauction.service.impl;

import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.entity.CarManagerPage;
import com.wintop.ms.carauction.entity.TreeEntity;
import com.wintop.ms.carauction.model.CarManagerPageModel;
import com.wintop.ms.carauction.service.ICarManagerPageService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CarManagerPageServiceImpl implements ICarManagerPageService {
    @Autowired
    private CarManagerPageModel pageModel;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return pageModel.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarManagerPage> selectByExample(Map<String,Object> map){
        return pageModel.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarManagerPage selectByPrimaryKey(Long id){
        return pageModel.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id){
        return pageModel.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarManagerPage record){
        return pageModel.insert(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(CarManagerPage record){
        return pageModel.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(CarManagerPage record){
        return pageModel.updateByPrimaryKey(record);
    }

    /**
     * 查询所有页面的tree
     * @return
     */
    public List<TreeEntity> getPageTreeList(Long roleId){
        return pageModel.getPageTreeList(roleId);
    }
    /**
     * 根据父节点查询
     */
    @Override
    public List<TreeEntity> getPageTreeByPId(Map<String, Object> map){
        List<TreeEntity> treeEntities=pageModel.getPageTreeByPId(map);
        for (TreeEntity entity:treeEntities){
            map.put("parentId",entity.getId());
            List<TreeEntity> tree1=pageModel.getPageTreeByPId(map);
            entity.setTreeEntityList(tree1);
            for (TreeEntity e:tree1){
                map.put("parentId",e.getId());
                List<TreeEntity> tree2=pageModel.getPageTreeByPId(map);
                e.setTreeEntityList(tree2);
            }
        }
        return treeEntities;
    }

    /**
     * 查询一二级敏据节点
     */
    @Override
    public List<TreeEntity> getPageTreeForTwoNode(Map<String, Object> map){
        if (MapUtils.isEmpty(map) || !map.containsKey("roleId")) {
            return Collections.emptyList();
        }
        map.put("parentId", 0L);
        List<TreeEntity> treeEntities=pageModel.getPageTreeByPId(map);
        for (TreeEntity entity:treeEntities){
            map.put("parentId",entity.getId());
            List<TreeEntity> twoTree=pageModel.getPageTreeByPId(map);
            entity.setTreeEntityList(twoTree);
        }
        return treeEntities;
    }
}
