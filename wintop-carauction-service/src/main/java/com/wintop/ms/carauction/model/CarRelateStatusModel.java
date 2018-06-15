package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarRelateStatus;
import com.wintop.ms.carauction.mapper.read.ICarRelateStatusReadDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-26
 */
@Repository
public class CarRelateStatusModel {
    @Resource
    private ICarRelateStatusReadDao readDao;

    /**
     * 根据条件类型查询所有的状态
     */
    public List<CarRelateStatus> selectAllStatus(Map<String,Object> map){
        return readDao.selectAllStatus(map);
    }
}
