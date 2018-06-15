package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.WtCity;
import com.wintop.ms.carauction.mapper.read.IWtCityReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * 地区市表
 */
@Repository
public class WtCityModel {
    @Autowired
    private IWtCityReadDao readDao;

    public WtCity findById(Long id){
        return readDao.findById(id);
    }


    public List<WtCity> findAll(Map map){
        return readDao.findAll(map);
    }
}
