package com.wintop.ms.carauction.service.impl;

import com.google.common.collect.Maps;
import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.model.CarAssessModel;
import com.wintop.ms.carauction.model.CarAutoInfoDetailModel;
import com.wintop.ms.carauction.model.CarStoreModel;
import com.wintop.ms.carauction.service.ICarAssessService;
import com.wintop.ms.carauction.service.ICarAutoInfoDetailService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 车辆评估 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessServiceImpl implements ICarAssessService {
    @Autowired
    private CarAssessModel carAssessModel;
    @Autowired
    private CarAutoInfoDetailModel carAutoInfoDetailModel;
    @Autowired
    private CarStoreModel carStoreModel;

    /**
     * 查询车辆评估信息
     *
     * @param id 车辆评估ID
     * @return 车辆评估信息
     */
    @Override
    public CarAssess selectCarAssessById(Long id) {
        return carAssessModel.selectCarAssessById(id);
    }

    /**
     * 查询车辆评估列表
     *
     * @param carAssess 车辆评估信息
     * @return 车辆评估集合
     */
    @Override
    public List<CarAssess> selectCarAssessList(CarAssess carAssess) {
        return carAssessModel.selectCarAssessList(carAssess);
    }

    /**
     * 新增车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
    @Override
    public int insertCarAssess(CarAssess carAssess) {
        return carAssessModel.insertCarAssess(carAssess);
    }

    /**
     * 修改车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
    @Override
    public int updateCarAssess(CarAssess carAssess) {
        return carAssessModel.updateCarAssess(carAssess);
    }

    /**
     * 删除车辆评估对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarAssessByIds(String ids) {
        return carAssessModel.deleteCarAssessByIds(ids);
    }

    /**
     * 根据条件查找数量
     *
     * @param carAssess
     */
    @Override
    public int selectAssessCount(CarAssess carAssess) {
        return carAssessModel.selectCarAssessCount(carAssess);
    }

    /**
     * 根据车辆id查询车辆详情
     *
     * @param autoId
     * @return
     */
    @Override
    public CarAssess selectCarAssessDetailById(Long autoId) {
        return carAssessModel.selectCarAssessDetailById(autoId);
    }

    /**
     * 采购审批详情
     */
    @Override
    public Map<String,Object> selectAccessAuditDetail(Long carId){
        Map<String, Object> result = Maps.newHashMap();
        CarAutoInfoDetail carAutoInfoDetail = carAutoInfoDetailModel.selectDetailByCarId(carId);

        result.put("id", carId);
        result.put("mainPhoto", carAutoInfoDetail.getMainPhoto());
        result.put("autoInfoName", carAutoInfoDetail.getAutoInfoName());
        result.put("vehicleAttributionCity", carAutoInfoDetail.getVehicleAttributionProvinceCn());
        result.put("mileage", carAutoInfoDetail.getMileage());
        result.put("reportColligationRanks", carAutoInfoDetail.getReportColligationRanks());
        result.put("reportServicingRanks", carAutoInfoDetail.getReportServicingRanks());

        CarAssess param = new CarAssess();
        param.setAutoId(carId);
        List<CarAssess> carAssessList = carAssessModel.selectCarAssessList(param);
        if (CollectionUtils.isNotEmpty(carAssessList)) {
            CarAssess carAssess = carAssessList.get(0);
            result.put("createUser", carAssess.getCreateUser());
            result.put("status", carAssess.getStatus());
            if (isNotEmpty(carAssess.getOrder())) {
                result.put("procurementType", carAssess.getOrder().getProcurementType());
                result.put("price", carAssess.getOrder().getPrice());
                result.put("procurementDate", carAssess.getOrder().getProcurementDate());
                // 所属店铺
                if (isNotEmpty(carAssess.getOrder().getStoreId())) {
                    result.put("storeName", carStoreModel.selectByPrimaryKey(carAssess.getOrder().getStoreId()).getName());
                }
                result.put("ifOldNew", carAssess.getOrder().getIfOldNew());
                result.put("entiretyPrice", carAssess.getOrder().getEntiretyPrice());
                result.put("customerName", carAssess.getOrder().getCustomerName());
                result.put("customerTel", carAssess.getOrder().getCustomerTel());
                result.put("customerSource", carAssess.getOrder().getCustomerSource());
            }
        }
        return result;
    }

    private static <T> boolean isNotEmpty(T t) {
        return Optional.ofNullable(t).isPresent();
    }
}
