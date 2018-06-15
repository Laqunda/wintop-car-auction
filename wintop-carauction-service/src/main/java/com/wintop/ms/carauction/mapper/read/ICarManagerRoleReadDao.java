package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarManagerRole;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarManagerRoleReadDao {

    /**
     * 根据条件查询记录集
     */
    List<CarManagerRole> selectByExample(Map<String,Object> map);

    /**
     * 根据id查询记录
     */
    public CarManagerRole selectById(Long id);

    /**
     * 根据userId查询记录
     */
    public CarManagerRole selectByUserId(Long userId);
}