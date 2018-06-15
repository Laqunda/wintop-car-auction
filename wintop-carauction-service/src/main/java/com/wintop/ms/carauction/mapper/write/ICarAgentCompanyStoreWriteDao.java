package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAgentCompanyStore;

public interface ICarAgentCompanyStoreWriteDao {

    /**
     * 根据公司id删除记录
     */
    int deleteByCompanyId(Long companyId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarAgentCompanyStore companyStore);

}