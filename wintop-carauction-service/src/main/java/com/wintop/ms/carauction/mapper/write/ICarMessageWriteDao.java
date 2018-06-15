package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarMessage;

/**
 * Created by liangtingsen on 2018/3/6.
 * 系统消息
 */
public interface ICarMessageWriteDao {
    void save(CarMessage carMessage);

    void update(CarMessage carMessage);
}
