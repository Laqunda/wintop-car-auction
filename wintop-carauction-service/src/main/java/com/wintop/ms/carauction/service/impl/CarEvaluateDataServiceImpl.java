package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarEvaluateDataService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("carEvaluateDataService")
public class CarEvaluateDataServiceImpl implements ICarEvaluateDataService {

    //   检测报告评价
    private static final Long CHECK_REPORT = 1L;
    //    现场服务评价
    private static final Long LOCALE_ACUTION = 2L;
    //    成交车辆评价
    private static final Long DEAL_WITH_CAR = 3L;
    //    卖家评价
    private static final Long SELLER_EVAL = 4L;
    @Resource
    private CarEvaluateDataModel carEvaluateDataModel;
    @Resource
    private CarAutoDetectionDataModel carAutoDetectionDataModel;
    @Resource
    private CarAutoModel carAutoModel;
    @Resource
    private CarOrderModel carOrderModel;
    @Resource
    private CarStoreModel carStoreModel;
    @Resource
    private AppUserModel appUserModel;
    @Resource
    private CarLocaleAuctionModel carLocaleAuctionModel;
    @Resource
    private CarManagerUserModel carManagerUserModel;
    @Resource
    private CarAutoAuctionModel carAutoAuctionModel;
    @Resource
    private CarLocaleAuctionCarModel carLocaleAuctionCarModel;
    
    private IdWorker idWorker = new IdWorker(10);

    @Transactional
    @Override
    public ServiceResult<Map<String,Object>> insertSelective(CarEvaluateData carEvaluateData) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();

        Integer count = null;
        try {
            carEvaluateData.setId(idWorker.nextId());
            result.setSuccess(true);
            result.setResult(Collections.singletonMap("count",carEvaluateDataModel.insertSelective(carEvaluateData)));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过主键查询数据
     * @param id
     * @return
     */
    @Override
    public CarEvaluateData selectByPrimaryKey(Long id) {
        return carEvaluateDataModel.selectByPrimaryKey(id);
    }

    /**
     * 查询列表数据
     * @param map
     * @return
     */
    @Override
    public List<CarEvaluateData> list(Map<String, Object> map) {
        List<CarEvaluateData> list = carEvaluateDataModel.list(map);
        for (CarEvaluateData record : list) {
            if (CHECK_REPORT.equals(record.getType())) {
                CarAutoDetectionData carAutoDetectionData = carAutoDetectionDataModel.selectByPrimaryKey(record.getObjId());
                CarAuto carAuto = carAutoModel.selectByCarId(Collections.singletonMap("carId", carAutoDetectionData.getAutoId()));
                List<CarOrder> carOrderList = carOrderModel.selectByExample(Collections.singletonMap("carId", carAutoDetectionData.getAutoId()));
                WtAppUser wtAppUser = null;
                if (CollectionUtils.isNotEmpty(carOrderList)) {
                    CarOrder carOrder = carOrderList.stream().findFirst().orElse(new CarOrder());
                    wtAppUser = appUserModel.findById(carOrder.getCustomerId());
                }

                record.setCarAuto(carAuto);
                record.setWtAppUser(wtAppUser);
            } else if(LOCALE_ACUTION.equals(record.getType())){
                CarLocaleAuction carLocaleAuction = carLocaleAuctionModel.selectById(record.getObjId());
                CarManagerUser carManagerUser = carManagerUserModel.selectByPrimaryKey(carLocaleAuction.getCreatePerson());

                record.setCarLocaleAuction(carLocaleAuction);
                record.setCarManagerUser(carManagerUser);
            } else if (DEAL_WITH_CAR.equals(record.getType())) {
                CarOrder carOrder = carOrderModel.selectById(record.getObjId());
                CarAuto carAuto = carAutoModel.selectByCarId(Collections.singletonMap("carId", carOrder.getCarId()));
                List<CarLocaleAuctionCar> auctionCarList = carLocaleAuctionCarModel.selectByCarId(carOrder.getCarId());
                CarLocaleAuction carLocaleAuction = null;
                if (CollectionUtils.isNotEmpty(auctionCarList)) {
                    CarLocaleAuctionCar carLocaleAuctionCar = auctionCarList.stream().findFirst().orElse(new CarLocaleAuctionCar());
                    carLocaleAuction = carLocaleAuctionModel.selectById(carLocaleAuctionCar.getAuctionId());
                }
                WtAppUser wtAppUser = appUserModel.findById(carOrder.getCustomerId());

                record.setCarOrder(carOrder);
                record.setCarAuto(carAuto);
                record.setCarLocaleAuction(carLocaleAuction);
                record.setWtAppUser(wtAppUser);
            } else if (SELLER_EVAL.equals(record.getType())) {
                CarAuto carAuto = carAutoModel.selectByCarId(Collections.singletonMap("carId", record.getObjId()));
                CarAutoAuction carAutoAuction = carAutoAuctionModel.selectAutoAuctionByCarId(record.getObjId());

                record.setCarAuto(carAuto);
                record.setCarAutoAuction(carAutoAuction);
            }
        }
        return list;
    }

    /**
     * 查询记录数
     * @param map
     * @return
     */
    @Override
    public int count(Map<String, Object> map) {
        return 0;
    }
}
