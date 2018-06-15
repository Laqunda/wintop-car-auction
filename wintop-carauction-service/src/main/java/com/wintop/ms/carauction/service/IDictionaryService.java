package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtDictionary;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface IDictionaryService {
    /**
     * 根据条件查询记录总数
     */
    public ServiceResult<Integer> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<WtDictionary>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    ServiceResult<WtDictionary> selectById(Long id);
    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer> deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Integer> insert(WtDictionary dictionary);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Integer> updateByIdSelective(WtDictionary dictionary);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer> updateById(WtDictionary dictionary);

    /***
     * 根据编号查询字典项
     * @param map
     * @return
     */
    ServiceResult<WtDictionary> selectByCode(Map<String,Object> map);

    /**
     * 根据上级编号查询子集集合
     */
    ServiceResult<List<WtDictionary>> selectByPCode(Map<String,Object> map);

    /**
     * 一次查询多个字典集合
     */
    List<WtDictionary> selectByPCodeArr(Map<String,Object> map);

    /**
     * 一次查询多个字典对象
     */
    List<WtDictionary> selectByCodeArr(Map<String,Object> map);
}
