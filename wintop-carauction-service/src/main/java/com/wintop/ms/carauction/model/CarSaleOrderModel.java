package com.wintop.ms.carauction.model;



import com.wintop.ms.carauction.entity.CarOrderRetail;
import com.wintop.ms.carauction.mapper.read.ICarSaleOrderReadDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CarSaleOrderModel {
    @Resource
    private ICarSaleOrderReadDAO iCarSaleOrderReadDAO;

    public List<CarOrderRetail> selectCarSaleOrder(Map<String,Object> paramMap){
        return iCarSaleOrderReadDAO.selectCarSaleOrder(paramMap);
    }

    public CarOrderRetail selectCarSaleRetail(Map<String,Object> paramMap){
        return iCarSaleOrderReadDAO.selectCarSaleRetail(paramMap);
    }

    public Integer selectCarSaleOrderCount (Map<String,Object> paramMap){
        return iCarSaleOrderReadDAO.selectCarSaleOrderCount(paramMap);
    }

    public CarOrderRetail selectRetailById(Long id) {
        return iCarSaleOrderReadDAO.selectRetailById(id);
    }
}
