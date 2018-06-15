package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface ICarAppInfoReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAppInfo> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAppInfo selectById(Long id);

    /**
     * 根据appId获取版本号
     */
    CarAppInfo selectVersionByAppId(@Param(value = "appId") String appId);

    /***
     * 客户端类型：1卖家，2买家，3代办，4中心
     * @param type
     * @return
     */
    CarAppInfo selectByType(@Param(value = "type") String type);
}
