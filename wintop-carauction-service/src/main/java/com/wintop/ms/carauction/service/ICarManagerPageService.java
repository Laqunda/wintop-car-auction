package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarManagerPage;
import com.wintop.ms.carauction.entity.TreeEntity;

import java.util.List;
import java.util.Map;

public interface ICarManagerPageService {

    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarManagerPage> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarManagerPage selectByPrimaryKey(Long id);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarManagerPage record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarManagerPage record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarManagerPage record);

    /**
     * 查询所有页面的tree
     * @return
     */
     List<TreeEntity> getPageTreeList(Long roleId);
    /**
     * 根据父节点查询
     */
     List<TreeEntity> getPageTreeByPId(Map<String, Object> map);

    /**
     * 查询一二级敏据节点
     */
    List<TreeEntity> getPageTreeForTwoNode(Map<String, Object> map);
}
