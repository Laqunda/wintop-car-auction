package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.WtDictionary;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface IDictionaryReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<WtDictionary> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    WtDictionary selectById(Long id);

    /***
     * 根据编号查询字典项
     * @param map
     * @return
     */
    WtDictionary selectByCode(Map<String,Object> map);

    /**
     * 根据上级编号查询子集集合
     */
    List<WtDictionary> selectByPCode(Map<String,Object> map);

    /***
     * 一次查询多个字典
     * @param map
     * @return
     */
    List<WtDictionary> selectByPCodeArr(Map<String, Object> map);

    /***
     * 一次性查询多个字典编号对象
     * @param map
     * @return
     */
    List<WtDictionary> selectByCodeArr(Map<String, Object> map);
}
