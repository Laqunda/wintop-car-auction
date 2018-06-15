package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarQuestion;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
public interface ICarQuestionService {
    /**
     * 根据条件查询记录总数
     */
    Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarQuestion> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarQuestion selectById(Long id);
    /**
     * 根据主键删除记录
     */
    Integer deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Integer insert(CarQuestion carQuestion);

    /**
     * 根据主键更新属性不为空的记录
     */
    Integer updateByIdSelective(CarQuestion carQuestion);

    /**
     * 根据主键更新记录
     */
    Integer updateById(CarQuestion carQuestion);

    /**
    *  根据问题分类查询该分类下面的问题集
    *  @Autor 付陈林
    *  @Time  2018-3-5
    */
    List<CarQuestion> selectByClassifyId(Long classifyId);

    /**
     *  根据问题分类编码查询该分类下面的问题集
     *  @Autor zhangzijuan
     *  @Time  2018-3-6
     */
    ServiceResult<List<CarQuestion>> getQuestionByCode(String code);

    /**
     * 查询首页展示
     * @param limit 显示的条数
     * @return
     */
    List<CarQuestion> selectIndexQuestion(int limit);

}
