package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoConfTitle;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ICarAutoConfTitleWriteDao {
    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarAutoConfTitle record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarAutoConfTitle record);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CarAutoConfTitle record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CarAutoConfTitle record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarAutoConfTitle record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarAutoConfTitle record);
}