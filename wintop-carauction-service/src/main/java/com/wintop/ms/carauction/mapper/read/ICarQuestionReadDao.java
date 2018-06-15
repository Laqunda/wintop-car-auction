package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
public interface ICarQuestionReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarQuestion> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarQuestion selectById(Long id);

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
     List<CarQuestion> getQuestionByCode(@Param("code") String code);

    /**
     * 查询首页展示
     * @param limit 首页展示条数
     * @return
     */
    List<CarQuestion> selectIndexQuestion(int limit);
}
