package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface ICarAppInfoService {
    /**
     * 根据条件查询记录总数
     */
    public ServiceResult<Integer> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<CarAppInfo>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAppInfo selectById(Long id);
    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer> deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Integer> insert(CarAppInfo carAppInfo);

    /**
     * 根据主键更新属性不为空的记录
     */
    Integer updateByIdSelective(CarAppInfo carAppInfo);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer> updateById(CarAppInfo carAppInfo);

    /**
     * 根据appId获取版本号
     */
    ServiceResult<CarAppInfo> selectVersionByAppId(String appId);

    /***
     * 客户端类型：1卖家，2买家，3代办，4中心
     * @param type
     * @return
     */
    CarAppInfo selectByType(String type);
}
