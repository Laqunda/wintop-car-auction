package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.core.entity.AppVersion;
import org.apache.ibatis.annotations.Param;

/***
 * 读取客户端app版本信息
 */
public interface ICarAppVersionReadDao {

    /***
     * 获取对应客户端的最新版本号
     * @param appType
     * @return
     */
    AppVersion findNewByAppType(@Param("appType") String appType);

}
