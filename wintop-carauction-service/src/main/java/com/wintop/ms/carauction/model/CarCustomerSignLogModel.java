package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerSign;
import com.wintop.ms.carauction.entity.CarCustomerSignLog;
import com.wintop.ms.carauction.mapper.read.ICarCustomerSignLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerSignLogWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * 用户签约日志表
 */
@Repository
public class CarCustomerSignLogModel {
    @Resource
    private ICarCustomerSignLogReadDao readDao;

    @Resource
    private ICarCustomerSignLogWriteDao writeDao;

    public CarCustomerSignLog findById(Long id){
        return readDao.findById(id);
    }

    public List<CarCustomerSignLog> findBySignId (Map<String,Object> map){
        return readDao.findBySignId(map);
    }

    public void insert(CarCustomerSignLog carCustomerSignLog){
        writeDao.insert(carCustomerSignLog);
    }

}
