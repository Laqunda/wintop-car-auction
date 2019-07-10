package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateData;

import java.util.List;
import java.util.Map;

public interface ICarEvaluateDataService {

    /**
     * 保存评价数据
     * @param carEvaluateData
     * @return
     */
    public ServiceResult<Map<String,Object>> insertSelective(CarEvaluateData carEvaluateData)  ;

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
