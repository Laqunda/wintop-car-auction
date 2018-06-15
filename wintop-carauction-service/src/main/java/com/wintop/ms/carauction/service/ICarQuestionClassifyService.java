package com.wintop.ms.carauction.service;


import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarQuestionClassify;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
public interface ICarQuestionClassifyService {
    /**
     * 根据条件查询记录总数
     */
    Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarQuestionClassify> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarQuestionClassify selectById(Long id);
    /**
     * 根据主键删除记录
     */
    Integer deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Integer insert(CarQuestionClassify carQuestionClassify);

    /**
     * 根据主键更新属性不为空的记录
     */
    Integer updateByIdSelective(CarQuestionClassify carQuestionClassify);

    /**
     * 根据主键更新记录
     */
    Integer updateById(CarQuestionClassify carQuestionClassify);

}
