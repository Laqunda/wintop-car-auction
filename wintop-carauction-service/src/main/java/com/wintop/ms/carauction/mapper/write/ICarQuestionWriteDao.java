package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarQuestion;

/**
 * Created by 12991 on 2018/3/5.
 */
public interface ICarQuestionWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteById(Long id);

    /**
     * 根据分类id删除问题
     * @param classifyId
     * @return
     */
    int deleteByClassifyId(Long classifyId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarQuestion carQuestion);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(CarQuestion carQuestion);

    /**
     * 根据主键更新记录
     */
    int updateById(CarQuestion carQuestion);
}
