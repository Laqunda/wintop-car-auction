package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.WtRegion;
import com.wintop.ms.carauction.mapper.read.IWtRegionReadDao;
import com.wintop.ms.carauction.mapper.write.IWtRegionWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtingsen on 2018/3/6.
 * 地区区域表
 */
@Repository
public class WtRegionModel {
    @Autowired
    private IWtRegionReadDao regionReadDao;
    @Autowired
    private IWtRegionWriteDao regionWriteDao;

    /***
     * 根据主键查询详情
     * @param id
     * @return
     */
    public WtRegion findById(Long id){
        return regionReadDao.findById(id);
    }

    /***
     * 根据地区编号查询详情
     * @param code
     * @return
     */
    public WtRegion findByCode(String code){
        return regionReadDao.findByCode(code);
    }

    /***
     * 查询全部地区信息
     * @param status
     * @return
     */
    public List<WtRegion> findAll(String status){
        return regionReadDao.findAll(status);
    }

    /***
     * 保存
     * @param wtRegion
     */
    public void saveOne(WtRegion wtRegion){
        regionWriteDao.saveOne(wtRegion);
    }

    /***
     * 修改
     * @param wtRegion
     */
    public void updateOne(WtRegion wtRegion){
        regionWriteDao.updateOne(wtRegion);
    }

}
