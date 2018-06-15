package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtRegion;

import java.util.List;

/**
 * Created by liangtingsen on 2018/3/6.
 * 地区区域
 */
public interface IWtRegionService {
    /***
     * 根据主键查询详情
     * @param id
     * @return
     */
    ServiceResult<WtRegion> findById(Long id);

    /***
     * 根据地区编号查询详情
     * @param code
     * @return
     */
    ServiceResult<WtRegion> findByCode(String code);

    /***
     * 查询全部地区信息
     * @param status
     * @return
     */
    ServiceResult<List<WtRegion>> findAll(String status);

    /***
     * 保存
     * @param wtRegion
     */
    void saveOne(WtRegion wtRegion);

    /***
     * 修改
     * @param wtRegion
     */
    void updateOne(WtRegion wtRegion);
}
