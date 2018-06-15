package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarManagerRoleType;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarManagerRoleTypeReadDao {

    /**
     * 根据条件查询记录集
     */
    List<CarManagerRoleType> selectByExample(Map<String,Object> map);

}