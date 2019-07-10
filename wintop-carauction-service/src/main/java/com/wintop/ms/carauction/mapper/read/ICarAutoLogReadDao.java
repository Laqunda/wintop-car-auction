package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoLogReadDao {
    /**
     * ����������ѯ��¼����
     */
    int countByExample(Criteria example);

    /**
     * ����������ѯ��¼��
     */
    List<CarAutoLog> selectByExample(Criteria example);

    /**
     * ����������ѯ��¼
     */
    CarAutoLog selectByPrimaryKey(Long id);
    /**
     * 查询车辆轨迹
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:
     */
    List<CarAutoLog> selectCarLogByCarId(Map<String,Object> map);

    CarAutoLog selectCarLog(Map<String,Object> map);

    int selectCountWaitByUserId(Long userId);

    int selectCountEndByUserId(Long userId);

    List<CarAutoLog> selectWaitOrderList(Map<String,Object> map);

    List<CarAutoLog> selectEndOrderList(Map<String,Object> map);


}