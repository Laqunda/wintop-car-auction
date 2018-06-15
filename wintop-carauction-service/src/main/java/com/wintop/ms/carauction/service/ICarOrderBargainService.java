package com.wintop.ms.carauction.service;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarOrderBargain;

import java.util.List;
import java.util.Map;

public interface ICarOrderBargainService {

    /**
     * 根据条件查询记录总数
     */
    ServiceResult<Integer> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<CarOrderBargain>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    ServiceResult<CarOrderBargain> selectById(Long id);

    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer> deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Integer> insert(CarOrderBargain carOrderBargain);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Integer> updateByIdSelective(CarOrderBargain carOrderBargain);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer> updateById(CarOrderBargain carOrderBargain);
    /**
     * 议价确认
     *@Author:zhangzijuan
     *@date 2018/4/17
     *@param:
     */
    Integer sureBargaining(JSONObject object,CarAuto auto);
}
