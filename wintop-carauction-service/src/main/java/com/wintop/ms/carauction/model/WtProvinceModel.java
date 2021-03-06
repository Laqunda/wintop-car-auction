package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.WtProvince;
import com.wintop.ms.carauction.mapper.read.IWtProvinceReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * 省直辖市表
 */
@Repository
public class WtProvinceModel {
    @Autowired
    private IWtProvinceReadDao readDao;

    public WtProvince findById(Long id){
        return readDao.findById(id);
    }


    public List<WtProvince> findAll(Map map){
        return readDao.findAll(map);
    }
}
