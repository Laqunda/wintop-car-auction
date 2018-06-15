package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.WtDistrict;
import com.wintop.ms.carauction.mapper.read.IWtDistrictReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * 地区市表
 */
@Repository
public class WtDistrictModel {
    @Autowired
    private IWtDistrictReadDao readDao;

    public WtDistrict findById(Long id){
        return readDao.findById(id);
    }


    public List<WtDistrict> findAll(Map map){
        return readDao.findAll(map);
    }
}
