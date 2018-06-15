package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAppInfo;
import com.wintop.ms.carauction.mapper.read.ICarAppInfoReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAppInfoWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Repository
public class CarAppInfoModel {
    @Autowired
    private ICarAppInfoReadDao carAppInfoReadDao;
    @Autowired
    private ICarAppInfoWriteDao carAppInfoWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carAppInfoReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAppInfo> selectByExample(Map<String,Object> map){
        return carAppInfoReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAppInfo selectById(Long id){
        return carAppInfoReadDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return carAppInfoWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAppInfo carAppInfo){
        return carAppInfoWriteDao.insert(carAppInfo);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAppInfo carAppInfo){
        return carAppInfoWriteDao.updateByIdSelective(carAppInfo);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAppInfo carAppInfo){
        return carAppInfoWriteDao.updateById(carAppInfo);
    }

    /**
     * 根据appId获取版本号
     */
    public CarAppInfo selectVersionByAppId(String appId){
        return carAppInfoReadDao.selectVersionByAppId(appId);
    }

    /***
     * 客户端类型：1卖家，2买家，3代办，4中心
     * @param type
     * @return
     */
    public CarAppInfo selectByType(String type){
        return carAppInfoReadDao.selectByType(type);
    }
}
