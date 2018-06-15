package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarAgentCompany;
import com.wintop.ms.carauction.entity.CommonNameVo;

import java.util.List;
import java.util.Map;

public interface ICarAgentCompanyService {

    /**
     * 根据条件查询记录总数
     */
    Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAgentCompany> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAgentCompany selectById(Long id);

    /**
     * 根据主键删除记录
     */
    Integer deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Integer insert(CarAgentCompany carAgentCompany);

    /**
     * 根据主键更新属性不为空的记录
     */
    Integer updateByIdSelective(CarAgentCompany carAgentCompany);

    /**
     * 根据主键更新记录
     */
    Integer updateById(CarAgentCompany carAgentCompany);

    /**
     * 删除记录
     */
    public int updateCenterDel(Long delPerson,Long id);

    /**
     * 查询所有代办公司字典表
     * @return
     */
    public List<CommonNameVo> selectAllAgentCompany();

    /**
     *根据条件查询代办公司
     */
    public List<CarAgentCompany> selectCompanyByDepId(Map<String,Object> map);
}
