package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.CommonNameVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarManagerUserReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarManagerUser> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarManagerUser selectByPrimaryKey(Long id);

    /**
     * 登录查询
     */
    CarManagerUser selectByUserId(@Param("userKey") String userKey);

    /**
     * 查询某个子部门下的所有用户
     * @param departmentId
     * @return
     */
    List<CommonNameVo> selectAllManagerUser(Long departmentId);

}