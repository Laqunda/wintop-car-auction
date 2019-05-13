package com.wintop.ms.carauction.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarAuctionBidRecordService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RedisAutoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarAuctionBidRecordServiceImpl implements ICarAuctionBidRecordService {
    @Autowired
    private CarAuctionBidRecordModel model;
    @Autowired
    private AppUserModel appUserModel;
    @Autowired
    private CarOrderModel carOrderModel;
    @Autowired
    private CarAutoAuctionModel autoAuctionModel;
    @Autowired
    private RedisAutoManager redisAutoManager;
    @Autowired
    private CarLocaleAuctionCarModel carLocaleAuctionCarModel;
    @Autowired
    private CarCustomerEntrustCarModel entrustCarModel;
    @Autowired
    private CarAuctionFareSettingModel fareSettingModel;
    @Autowired
    private CarAutoModel autoModel;

    private IdWorker idWorker = new IdWorker(10);
    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarAuctionBidRecord> selectByExample(Map<String, Object> map) {
        List<CarAuctionBidRecord> list = model.selectByExample(map);
        return list;
    }

    @Override
    public CarAuctionBidRecord selectById(Long id) {
        CarAuctionBidRecord auctionBidRecord = model.selectById(id);
        return auctionBidRecord;
    }

    @Override
    public List<CarAuctionBidRecord> selectByAuctionId(Long auctionId) {
        List<CarAuctionBidRecord> list = model.selectByAuctionId(auctionId);
        return list;
    }

    @Override
    public Integer deleteById(Long id) {
        Integer count = model.deleteById(id);
        return count;
    }

    @Override
    @Transactional
    public int insert(CarAuctionBidRecord carAuctionBidRecord, RedisAutoData autoData) {
        carAuctionBidRecord.setId(idWorker.nextId());
        Integer count = model.insert(carAuctionBidRecord);
        //***更新redis信息
        redisAutoManager.updateAuto(autoData);
        //***更新竞拍结束时间
        CarAutoAuction autoAuction = new CarAutoAuction();
        autoAuction.setAutoId(autoData.getAutoId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(autoData.getAuctionEndTime());
        autoAuction.setAuctionEndTime(calendar.getTime());
        autoAuctionModel.updateAuctionEndTime(autoAuction);
        return count;
    }

    @Override
    @Transactional
    public int insertBatch(CarAuctionBidRecord carAuctionBidRecord, RedisAutoData autoData) {
        carAuctionBidRecord.setId(idWorker.nextId());
        List<CarAuctionBidRecord> bidRecordList = new ArrayList<>();
        bidRecordList.add(carAuctionBidRecord);
        BigDecimal bidPrice = carAuctionBidRecord.getBidFee();
        BigDecimal maxPrice = carAuctionBidRecord.getBidFee();
        List<CarCustomerEntrustCar> entrustCarList = entrustCarModel.selectValidEntrustList(autoData.getAutoId());
        for(CarCustomerEntrustCar entrustCar:entrustCarList){
            //***如果是自己出价则不加价
            if(carAuctionBidRecord.getCustomerId().compareTo(entrustCar.getCustomerId())==0){
                //System.out.println("自己出价则不加价");
                continue;
            }
            CarAuctionFareSetting fareSetting = fareSettingModel.selectEnableMaxFare(entrustCar.getCustomerId());
            //***没有可出价信息继续
            if(fareSetting==null){
                //System.out.println("没有可出价信息继续");
                continue;
            }
            //***委托价低于上一次价格，继续
            if(entrustCar.getEntrustFee().compareTo(maxPrice)<=0){
                continue;
            }
            bidPrice=bidPrice.add(fareSetting.getFarePrice());
            //***自动加价金额不能超过委托价,并且委托价必须大于上次价格
            if(bidPrice.compareTo(entrustCar.getEntrustFee())>0){
                //System.out.println("自动加价金额不能超过委托价");
                bidPrice=entrustCar.getEntrustFee();
            }
            //***自动出价低于上次价格，继续
            /*if(bidPrice.compareTo(maxPrice)<=0){
                //System.out.println("自动出价低于上次价格，继续");
                continue;
            }*/
            CarAuctionBidRecord auctionBidRecord = new CarAuctionBidRecord();
            auctionBidRecord.setId(idWorker.nextId());
            auctionBidRecord.setAuctionId(autoData.getAutoAuctionId());
            auctionBidRecord.setCarId(autoData.getAutoId());
            auctionBidRecord.setCustomerId(entrustCar.getCustomerId());
            auctionBidRecord.setBidFee(bidPrice);
            auctionBidRecord.setAddFee(fareSetting.getFarePrice());
            auctionBidRecord.setBidTime(new Date());
            bidRecordList.add(auctionBidRecord);
            maxPrice = auctionBidRecord.getBidFee();
            autoData.setMaxPrice(maxPrice);
            autoData.setMaxPriceUserId(entrustCar.getCustomerId());
        }
        Integer count = model.insertBatch(bidRecordList);
        //***更新redis信息
        redisAutoManager.updateAuto(autoData);
        //***更新竞拍结束时间
        CarAutoAuction autoAuction = new CarAutoAuction();
        autoAuction.setAutoId(autoData.getAutoId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(autoData.getAuctionEndTime());
        autoAuction.setAuctionEndTime(calendar.getTime());
        autoAuctionModel.updateAuctionEndTime(autoAuction);
        return count;
    }

    @Override
    public Integer updateByIdSelective(CarAuctionBidRecord carAuctionBidRecord) {
        Integer count = model.updateByIdSelective(carAuctionBidRecord);
        return count;
    }

    @Override
    public Integer updateById(CarAuctionBidRecord carAuctionBidRecord) {
        Integer count = model.updateById(carAuctionBidRecord);
        return count;
    }

    /**
     * 获取车辆的最后一次出价
     * @return
     */
    @Override
    public BigDecimal selectMaxPrice(Map<String,Object> map){
        return model.selectMaxPrice(map);
    }

    /**
     * 根据条件查询用户出价车辆列表
     */
    @Override
    public List<CarAuctionBidRecord> queryUserBidList(Map<String, Object> map) {
        List<CarAuctionBidRecord> list = model.queryUserBidList(map);
        return list;
    }

    /**
     *根据条件查询用户出价车辆数
     */
    @Override
    public int selectBidCount(Map<String, Object> map) {
        Integer count = model.selectBidCount(map);
        return count;
    }

    public ServiceResult<Map<String,Object>> saveCarAuctionBidRecord(CarAuctionBidRecord carAuctionBidRecord){
        //最高出价
        CarAuctionBidRecord bidRecord = model.selectLastBidRecord(carAuctionBidRecord.getAuctionCarId());
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        Map resultMap =new HashMap();
        Integer count = 1;
        if(bidRecord==null || carAuctionBidRecord.getBidFee().compareTo(bidRecord.getBidFee())>0){
            CarAuto auto = autoModel.selectByPrimaryKey(carAuctionBidRecord.getCarId());
            carAuctionBidRecord.setAuctionId(auto.getAutoAuctionId());
            count = model.insert(carAuctionBidRecord);
        }
        resultMap.put("count",count);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }
    public ServiceResult<Map<String,Object>> saveBiddenInfoByCode(CarAuctionBidRecord carAuctionBidRecord,String customerCode){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        //根据车商编号查询车商
        CommonNameVo commonNameVo =appUserModel.getUserInfoByCode(customerCode);
        if(commonNameVo!=null){
            carAuctionBidRecord.setCustomerId(commonNameVo.getId());
        }
        //最高出价
        CarAuctionBidRecord bidRecord = model.selectLastBidRecord(carAuctionBidRecord.getAuctionCarId());
        Map resultMap =new HashMap();
        carAuctionBidRecord.setId(idWorker.nextId());
        Integer count = 1;
        if(bidRecord==null || carAuctionBidRecord.getBidFee().compareTo(bidRecord.getBidFee())>0){
            CarAuto auto = autoModel.selectByPrimaryKey(carAuctionBidRecord.getCarId());
            carAuctionBidRecord.setAuctionId(auto.getAutoAuctionId());
            count = model.insert(carAuctionBidRecord);
        }
        resultMap.put("count",count);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }


    public ServiceResult<List<CarAuctionBidRecord>> getCustomerBidPriceList(Map<String,Object> paramMap){
        ServiceResult<List<CarAuctionBidRecord>> result =new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(model.getCustomerBidPriceList(paramMap));
        return result;
    }

    /**
     * @Author 付陈林
     * @Date 2018-4-3
     * @About 清除场次车辆的所有出价记录
     * */
    public ServiceResult<Map<String,Object>> cleanBiddenInfo(Map<String,Object> paramMap){
        List<CarAuctionBidRecord> carAuctionBidRecords =model.getCustomerBidPriceList(paramMap);
        for(CarAuctionBidRecord carAuctionBidRecord:carAuctionBidRecords){
            model.deleteById(carAuctionBidRecord.getId());
        }
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",1);
        result.setResult(resultMap);
        return result;
    }

    /**
     * @Author 付陈林
     * @Date 2018-4-3
     * @About 清除场次车辆的最后一条出价记录
     * */
    public ServiceResult<Map<String,Object>> cleanLastBiddenInfo(Long auctionCarId){
        CarAuctionBidRecord carAuctionBidRecord =model.selectLastBidInfo(auctionCarId);
        if(carAuctionBidRecord!=null){
            model.deleteById(carAuctionBidRecord.getId());
        }
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",1);
        result.setResult(resultMap);
        return result;
    }


    public ServiceResult<Map<String,Object>> setCustomerBidNumber(String auctionBidRecordIds){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        String[] customerAndBidIds =auctionBidRecordIds.split(",");
        if(customerAndBidIds==null||customerAndBidIds.length<=0){
            return new ServiceResult<>(false,"拼接信息为空！","101");
        }
        for(String customerAndBidId:customerAndBidIds){
            String[] customerAndBidIdArray =customerAndBidId.split("_");
            if(customerAndBidIdArray==null||customerAndBidIdArray.length<2){
                continue;
            }
            CarAuctionBidRecord updateAuctionBidRecord =new CarAuctionBidRecord();
            updateAuctionBidRecord.setId(Long.valueOf(customerAndBidIdArray[0]));
            updateAuctionBidRecord.setCustomerId(Long.valueOf(customerAndBidIdArray[1]));
            model.updateByIdSelective(updateAuctionBidRecord);
            //需要更新订单中的车商信息
            updateAuctionBidRecord =model.selectById(Long.valueOf(customerAndBidIdArray[0]));
            if(updateAuctionBidRecord!=null){
                //最高出价
                CarAuctionBidRecord carAuctionBidRecord =model.selectLastBidRecord(updateAuctionBidRecord.getAuctionCarId());
                if(carAuctionBidRecord!=null&&carAuctionBidRecord.getId().equals(updateAuctionBidRecord.getId())){
                    CarLocaleAuctionCar carLocaleAuctionCar=carLocaleAuctionCarModel.selectById(carAuctionBidRecord.getAuctionCarId());
                    Map<String,Object> paramMap=new HashMap<>();
                    paramMap.put("carId",carAuctionBidRecord.getCarId());
                    paramMap.put("auctionId",carLocaleAuctionCar.getAuctionId());
                    CarOrder carOrder = carOrderModel.selectOrderByCarId(paramMap);
                    if(carOrder!=null){
                        CarOrder updateCarOrder =new CarOrder();
                        updateCarOrder.setId(carOrder.getId());
                        updateCarOrder.setCustomerId(Long.valueOf(customerAndBidIdArray[1]));
                        carOrderModel.updateByIdSelective(updateCarOrder);
                    }
                }
            }


        }
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",1);
        result.setResult(resultMap);
        return result;
    }

    public ServiceResult<Map<String,Object>> setCustomerBidNumberByCode(String auctionBidRecordCodes){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        String[] customerAndBidCodes =auctionBidRecordCodes.split(",");
        if(customerAndBidCodes==null||customerAndBidCodes.length<=0){
            return new ServiceResult<>(false,"拼接信息为空！","101");
        }
        List<CarAuctionBidRecord> carAuctionBidRecords =new ArrayList<>();
        for(String customerAndBidCode:customerAndBidCodes){
            String[] customerAndBidCodeArray =customerAndBidCode.split("_");
            if(customerAndBidCodeArray==null||customerAndBidCodeArray.length<2){
                return new ServiceResult<>(false,"拼接信息错误！","101");
            }
            CarAuctionBidRecord carAuctionBidRecord =new CarAuctionBidRecord();
            carAuctionBidRecord.setId(Long.valueOf(customerAndBidCodeArray[0]));
            CommonNameVo commonNameVo = appUserModel.getUserInfoByCode(customerAndBidCodeArray[1]);
            if(commonNameVo==null){
                return new ServiceResult<>(false,"根据车商编号获取不到车商信息","102");
            }
            carAuctionBidRecord.setCustomerId(commonNameVo.getId());
            carAuctionBidRecords.add(carAuctionBidRecord);
        }
        for(CarAuctionBidRecord carAuctionBidRecord:carAuctionBidRecords){
            model.updateByIdSelective(carAuctionBidRecord);
        }
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",1);
        result.setResult(resultMap);
        return result;
    }

    public ServiceResult<CarAuctionBidRecord> selectLastBidInfo(Long auctionCarId){
        ServiceResult<CarAuctionBidRecord> serviceResult =new ServiceResult<>();
        //最高出价
        CarAuctionBidRecord carAuctionBidRecord =model.selectLastBidRecord(auctionCarId);
        serviceResult.setSuccess(true);
        serviceResult.setResult(carAuctionBidRecord);
        return serviceResult;
    }

    /**
     * 批量保存竞价
     * @param bidRecordList
     * @return
     */
    public int insertBatch(List<CarAuctionBidRecord> bidRecordList){
        return model.insertBatch(bidRecordList);
    }

    /**
     * 竞拍开始，自动出价最高委托价
     */
    public int insertAuto(Long carId){
        CarCustomerEntrustCar entrustCar = entrustCarModel.selectMaxEntrustByCarId(carId);
        return 0;
    }

    public List<Map<String,Object>> getBidPriceList(Long autoId) {
        List<Map<String,Object>> resultList = Lists.newArrayList();
        List<CarAuctionBidRecord> bidRecordList = model.getCustomerBidPriceList(Collections.singletonMap("carId", autoId));
        Map<String, List<CarAuctionBidRecord>> groupBidList = bidRecordList.stream().collect(Collectors.groupingBy(record -> String.format("%d_%d", record.getAutoId(), record.getCustomerId())));
        groupBidList.forEach((key,value)->{
            if (!CollectionUtils.isEmpty(value)){
                Collections.sort(value, Comparator.comparing(CarAuctionBidRecord::getBidFee).reversed());
                // 排序后的第一个值
                Map<String, Object> record = Maps.newHashMap();

                WtAppUser appUser = appUserModel.getUserInfoById(value.get(0).getCustomerId());
                record.put("customerName", appUser.getName());
                record.put("mobile", appUser.getMobile());
                record.put("customerId", value.get(0).getCustomerId());
                record.put("bidFee", value.get(0).getBidFee());
                record.put("bidTime", value.get(0).getBidTime());
                resultList.add(record);
            }
        });
        return resultList;
    }
}
