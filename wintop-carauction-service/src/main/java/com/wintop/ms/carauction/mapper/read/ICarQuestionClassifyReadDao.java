package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarQuestionClassify;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
public interface ICarQuestionClassifyReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarQuestionClassify> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarQuestionClassify selectById(Long id);

}
