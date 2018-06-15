package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/6.
 * 系统消息接口
 */
public interface ICarMessageService {
    /**
     * 根据id查询系统消息
     * @param id
     * @return
     */
    CarMessage findById(Long id);
    CarMessage findByIdForApp(Long id);
    /***
     * 查询本人系统消息--全部
     * @param userId
     * @return
     */
    List<CarMessage> findByUserIdAll(Long userId,String isRead);

    /***
     * 查询本人系统消息--分页
     * @param map
     * @return
     */
    List<CarMessage> findByUserIdPage(Map map);

    /***
     * 查询本人系统消息--分页
     * @param map
     * @return
     */
    int findByUserIdPageCount(Map map);


    /***
     * 保存消息
     * @param carMessage
     */
    void saveMsg(CarMessage carMessage);

    /***
     * 修改消息
     * @param carMessage
     */
    void readMsg(CarMessage carMessage);

    void updateMsg(CarMessage message);
}
