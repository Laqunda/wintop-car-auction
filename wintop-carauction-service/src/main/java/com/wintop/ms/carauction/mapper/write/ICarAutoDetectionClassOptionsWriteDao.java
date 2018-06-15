package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionClassOptions;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ICarAutoDetectionClassOptionsWriteDao {

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
    int insert(CarAutoDetectionClassOptions record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarAutoDetectionClassOptions record);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CarAutoDetectionClassOptions record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CarAutoDetectionClassOptions record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarAutoDetectionClassOptions record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarAutoDetectionClassOptions record);
}