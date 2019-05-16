package com.wintop.ms.carauction.model;



import com.wintop.ms.carauction.entity.CarSaleOrder;
import com.wintop.ms.carauction.mapper.read.ICarSaleOrderReadDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CarSaleOrderModel {
    @Resource
    private ICarSaleOrderReadDAO iCarSaleOrderReadDAO;

    public  List<CarSaleOrder> selectCarOrder(Long customerId){
        return iCarSaleOrderReadDAO.selectCarOrder(customerId);
    }

    public List<CarSaleOrder> selectCarSaleOrder(Long customerId){
        return iCarSaleOrderReadDAO.selectCarSaleOrder(customerId);
    }

    public CarSaleOrder selectCarSaleRetail(Long customerId){
        return iCarSaleOrderReadDAO.selectCarSaleRetail(customerId);
    }
    public Integer selectCarOrderCount (Long customerId){
        return iCarSaleOrderReadDAO.selectCarOrderCount(customerId);
    }

    public Integer selectCarSaleOrderCount (Long customerId){
        return iCarSaleOrderReadDAO.selectCarSaleOrderCount(customerId);
    }
}
