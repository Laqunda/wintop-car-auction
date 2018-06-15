package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoDetectionClassWriteDao {
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
    int insert(CarAutoDetectionClass record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarAutoDetectionClass record);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CarAutoDetectionClass record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CarAutoDetectionClass record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarAutoDetectionClass record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarAutoDetectionClass record);
}