package com.wintop.ms.carauction.model;



import com.wintop.ms.carauction.entity.CarSaleOrder;
import com.wintop.ms.carauction.mapper.read.ICarSaleOrderReadDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CarSaleOrderModel {
    @Resource
    private ICarSaleOrderReadDAO iCarSaleOrderReadDAO;

    public List<CarSaleOrder> selectCarSaleOrder(Map<String,Object> paramMap){
        return iCarSaleOrderReadDAO.selectCarSaleOrder(paramMap);
    }

    public CarSaleOrder selectCarSaleRetail(Map<String,Object> paramMap){
        return iCarSaleOrderReadDAO.selectCarSaleRetail(paramMap);
    }

    public Integer selectCarSaleOrderCount (Long customerId){
        return iCarSaleOrderReadDAO.selectCarSaleOrderCount(customerId);
    }

    public CarSaleOrder selectRetailById(Long id) {
        return iCarSaleOrderReadDAO.selectRetailById(id);
    }
}
