package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.CarStore;

import java.util.List;
import java.util.Map;

public interface ICarStoreService {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarStore> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarStore selectByPrimaryKey(Long id);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarStore carStore);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarStore carStore);

    /**
     * 查询所有店铺
     * @return
     */
    List<CommonNameVo> selectAllStore();
    /**
     * 查询所有店铺
     * @return
     */
    List<CommonNameVo> selectAllStore(Long userId);

    /**
     * 删除店铺
     * @param delPerson
     * @param id
     * @return
     */
    public int updateStoreDel(Long delPerson,Long id);

    Long idByExample(Map<String,Object> map);

}
