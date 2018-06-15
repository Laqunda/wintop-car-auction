package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerSign;
import com.wintop.ms.carauction.mapper.read.ICarCustomerSignReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerSignWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * 用户签约表
 */
@Repository
public class CarCustomerSignModel {
    @Resource
    private ICarCustomerSignReadDao readDao;

    @Resource
    private ICarCustomerSignWriteDao writeDao;

    public CarCustomerSign findById(Long id){
        return readDao.findById(id);
    }

    public CarCustomerSign findByCustomerId(Long customerId){
        return readDao.findByCustomerId(customerId);
    }

    public List<CarCustomerSign> findByParams(Map params){
        return readDao.findByParams(params);
    }

    public void insert(CarCustomerSign carCustomerSign){
        writeDao.insert(carCustomerSign);
    }

    public void update(CarCustomerSign carCustomerSign){
        writeDao.update(carCustomerSign);
    }

    public Integer insertSelective(CarCustomerSign carCustomerSign){
        return writeDao.insertSelective(carCustomerSign);
    }

    public Integer updateByPrimaryKeySelective(CarCustomerSign carCustomerSign){
        return writeDao.updateByPrimaryKeySelective(carCustomerSign);
    }

    public Integer updateByUserId(CarCustomerSign carCustomerSign){
        return  writeDao.updateByUserId(carCustomerSign);
    }

    public CarCustomerSign querySignByUserId(Long customerId){
        return readDao.querySignByUserId(customerId);
    }
}
