package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/6.
 * 查询系统消息
 */
public interface ICarMessageReadDao {
    CarMessage findById(@Param("id") Long id);

    List<CarMessage> findByUserId(Map map);

    int findByUserIdPageCount(Map map);
}
