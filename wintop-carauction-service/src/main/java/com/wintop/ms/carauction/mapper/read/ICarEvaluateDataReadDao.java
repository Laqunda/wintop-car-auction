package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarEvaluateData;

import java.util.List;
import java.util.Map;

public interface ICarEvaluateDataReadDao {

    /**
     * 通过主键查询数据
     * @param id
     * @return
     */
    public CarEvaluateData selectByPrimaryKey(Long id);

    /**
     * 查询列表数据
     * @param map
     * @return
     */
    public List<CarEvaluateData> list(Map<String, Object> map);

    /**
     * 查询记录数
     * @param map
     * @return
     */
    public int count(Map<String, Object> map);
}
