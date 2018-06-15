package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAgentCompanyStore;
import com.wintop.ms.carauction.entity.CommonNameVo;

import java.util.List;

public interface ICarAgentCompanyStoreReadDao {

    /**
     * 查询所有记录集
     */
    List<CommonNameVo> selectAllStore(Long companyId);

    /**
     * 查询单个代办公司所有店铺ID集合
     * @return
     */
    List<Long> selectAllStoreIds(Long companyId);

}