package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAgentCompany;
import com.wintop.ms.carauction.entity.CommonNameVo;

import java.util.List;
import java.util.Map;

public interface ICarAgentCompanyReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAgentCompany> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAgentCompany selectById(Long id);

    /**
     * 查询所有代办公司字典表
     * @return
     */
    List<CommonNameVo> selectAllAgentCompany();

    /**
     * 根据条件查询代办公司
     */
    List<CarAgentCompany> selectCompanyByDepId(Map<String,Object> map);
}