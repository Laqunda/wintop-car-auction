package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.WtDictionary;
import com.wintop.ms.carauction.mapper.read.IDictionaryReadDao;
import com.wintop.ms.carauction.mapper.write.IDictionaryWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Repository
public class DictionaryModel {
    @Autowired
    private IDictionaryReadDao dictionaryReadDao;
    @Autowired
    private IDictionaryWriteDao dictionaryWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return dictionaryReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<WtDictionary> selectByExample(Map<String,Object> map){
        return dictionaryReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public WtDictionary selectById(Long id){
        return dictionaryReadDao.selectById(id);
    }

    /***
     * 根据编号查询字典项
     * @param map
     * @return
     */
    public WtDictionary selectByCode(Map<String,Object> map){
        return dictionaryReadDao.selectByCode(map);
    }

    /**
     * 根据上级编号查询子集集合
     */
    public List<WtDictionary> selectByPCode(Map<String,Object> map){
        return dictionaryReadDao.selectByPCode(map);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return dictionaryWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(WtDictionary dictionary){
        return dictionaryWriteDao.insert(dictionary);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(WtDictionary dictionary){
        return dictionaryWriteDao.updateByIdSelective(dictionary);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(WtDictionary dictionary){
        return dictionaryWriteDao.updateById(dictionary);
    }

    public List<WtDictionary> selectByPCodeArr(Map<String, Object> map) {
        return dictionaryReadDao.selectByPCodeArr(map);
    }

    public List<WtDictionary> selectByCodeArr(Map<String, Object> map) {
        return dictionaryReadDao.selectByCodeArr(map);
    }
}
