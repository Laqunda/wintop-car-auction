package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoProceduresWriteDao {

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
    int insert(CarAutoProcedures record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarAutoProcedures record);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CarAutoProcedures record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CarAutoProcedures record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarAutoProcedures record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarAutoProcedures record);
}