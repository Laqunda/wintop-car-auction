package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarOrderBargain;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarOrderBargainReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarOrderBargain> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarOrderBargain selectById(Long id);

    /**
     * 根据车辆id查询议价车辆
     * @param map
     * @return
     */
     CarOrderBargain selectBargainByCarId(Map<String,Object> map);

}