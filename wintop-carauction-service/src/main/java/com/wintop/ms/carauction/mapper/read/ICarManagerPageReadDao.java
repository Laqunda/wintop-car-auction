package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarManagerPage;
import com.wintop.ms.carauction.entity.TreeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarManagerPageReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String, Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarManagerPage> selectByExample(Map<String, Object> map);

    /**
     * 根据主键查询记录
     */
    CarManagerPage selectByPrimaryKey(Long id);

    /**
     * 查询所有页面的tree
     *
     * @return
     */
    List<TreeEntity> getPageTreeList(@Param(value = "roleId") Long roleId);

    /**
     * 根据父节点查询
     */
    List<TreeEntity> getPageTreeByPId(Map<String, Object> map);
}