package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;

public interface ICarAutoInfoDetailService {

    ServiceResult<Integer> countByExample(Map<String,Object> map);

    ServiceResult<CarAutoInfoDetail> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoInfoDetail>> selectByExample(Map<String,Object> map);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult updateByPrimaryKeySelective(CarAutoInfoDetail record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoInfoDetail record);

    ServiceResult<Integer> insert(CarAutoInfoDetail record);

    ServiceResult<Integer> insertSelective(CarAutoInfoDetail record);

    /**
     * 查询车辆基本信息
     * @param carId
     * @return
     */
    CarAutoInfoDetail selectDetailByCarId(Long carId);

    /***
     * 根据车辆id修改车辆基本信息
     * @param detail
     * @return
     */
    Integer updateRemarkByautoId(CarAutoInfoDetail detail);

    /***
     * 保存基本信息+配置信息
     * @param detail
     * @return
     */
    Integer updateDetailAndConf(CarAutoInfoDetail detail);
}