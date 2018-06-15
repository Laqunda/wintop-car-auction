package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoFamilyWriteDao {

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
    int insert(CarAutoFamily record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarAutoFamily record);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CarAutoFamily record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CarAutoFamily record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarAutoFamily record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarAutoFamily record);
}