package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtDictionary;
import com.wintop.ms.carauction.model.DictionaryModel;
import com.wintop.ms.carauction.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private DictionaryModel model;
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<WtDictionary>> selectByExample(Map<String, Object> map) {
        List<WtDictionary> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<WtDictionary> selectById(Long id) {
        WtDictionary dictionary =  model.selectById(id);
        return new ServiceResult<>(true,dictionary);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(WtDictionary dictionary) {
        Integer count = model.insert(dictionary);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(WtDictionary dictionary) {
        Integer count = model.updateByIdSelective(dictionary);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(WtDictionary dictionary) {
        Integer count = model.updateById(dictionary);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<WtDictionary> selectByCode(Map<String, Object> map) {
        WtDictionary dictionary = model.selectByCode(map);
        return new ServiceResult<>(true,dictionary);
    }

    @Override
    public ServiceResult<List<WtDictionary>> selectByPCode(Map<String, Object> map) {
        List<WtDictionary> list = model.selectByPCode(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public List<WtDictionary> selectByPCodeArr(Map<String, Object> map) {
        return model.selectByPCodeArr(map);
    }

    @Override
    public List<WtDictionary> selectByCodeArr(Map<String, Object> map) {
        return model.selectByCodeArr(map);
    }
}
