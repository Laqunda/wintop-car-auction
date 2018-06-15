package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.CommonNameVo;

import java.util.List;
import java.util.Map;

public interface ICarCenterService {
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    public List<CarCenter> selectByExample(Map<String,Object> map);
    
    /**
     * 根据主键查询记录
     */
    public CarCenter selectByPrimaryKey(Long id);

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarCenter carCenter);

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(CarCenter carCenter);

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(CarCenter carCenter);

    /**
     * 删除中心
     * @param delPerson
     * @param id
     * @return
     */
    public int updateCenterDel(Long delPerson,Long id);

}
