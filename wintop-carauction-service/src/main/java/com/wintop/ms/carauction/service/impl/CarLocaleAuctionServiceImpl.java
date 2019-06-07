package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarAutoAuctionModel;
import com.wintop.ms.carauction.model.CarAutoModel;
import com.wintop.ms.carauction.model.CarLocaleAuctionCarModel;
import com.wintop.ms.carauction.model.CarLocaleAuctionModel;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarLocaleAuctionService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CarLocaleAuctionServiceImpl implements ICarLocaleAuctionService {
    @Autowired
    private CarLocaleAuctionModel model;
    @Autowired
    private CarLocaleAuctionCarModel carAuctionCarModel;
    @Autowired
    private CarAutoModel carAutoModel;
    @Autowired
    private CarCustomerGroupModel carCustomerGroupModel;
    @Autowired
    private CarAutoAuctionModel carAutoAuctionModel;
    @Autowired
    private CarAuctionBidRecordModel carAuctionBidRecordModel;
    @Autowired
    private CarRegionSettingModel carRegionSettingModel;
    @Autowired
    private CarRegionServerfeeSettingModel serverfeeSettingModel;
    @Autowired
    private CarAutoLogModel carAutoLogModel;
    @Autowired
    private CarOrderModel carOrderModel;
    @Autowired
    private CarOrderLogModel carOrderLogModel;
    @Autowired
    private CarManagerUserModel carManagerUserModel;

    private IdWorker idWorker = new IdWorker(10);

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    @Qualifier(value = "redisTemplate")
    public void setRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        //泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }

    @Override
    public ServiceResult<Map<String,Object>> countByExample(Map<String, Object> map) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",model.countByExample(map));
        result.setResult(resultMap);
        return result;
    }

    @Override
    public ServiceResult<List<CarLocaleAuction>> selectByExample(Map<String, Object> map) {
        ServiceResult<List<CarLocaleAuction>> result=new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(model.selectByExample(map));
        return result;
    }

    @Override
    public ServiceResult<CarLocaleAuction> selectById(Long id) {
        ServiceResult<CarLocaleAuction> result=new ServiceResult<>();
        result.setSuccess(true);
        CarLocaleAuction carLocaleAuction = model.selectById(id);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("auctionId",id);
        int carNum = carAuctionCarModel.selectCarNumByAuction(paramMap);
        if(carLocaleAuction!=null){
            carLocaleAuction.setCarNum(carNum);
        }
        result.setResult(carLocaleAuction);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> deleteById(Long id,Long userId) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        //删除该场次的竞拍车辆
        List<CarLocaleAuctionCar> carLocaleAuctionCars =carAuctionCarModel.getAuctionCarList(id);
        CarLocaleAuction carLocaleAuction =model.selectById(id);
        //清空redis中的数据
        redisTemplate.delete(String.valueOf(carLocaleAuction.getId()));
        for(CarLocaleAuctionCar carLocaleAuctionCar:carLocaleAuctionCars){
            CarAuto carAuto =new CarAuto();
            carAuto.setId(carLocaleAuctionCar.getCarId());
            carAuto.setStatus("5");
            carAutoModel.updateByPrimaryKeySelective(carAuto);
            //添加车辆操作日志
            CarAutoLog carAutoLog = new CarAutoLog();
            carAutoLog.setId(idWorker.nextId());
            carAutoLog.setAutoId(carLocaleAuctionCar.getCarId());
            carAutoLog.setUserType("2");
            carAutoLog.setStatus("5");
            carAutoLog.setTime(new Date());
            carAutoLog.setMsg("从现场拍"+carLocaleAuction.getTitle()+"场次中剔除了该车辆");
            carAutoLog.setUserId(userId);
            if(userId!=null){
                CarManagerUser carManagerUser=carManagerUserModel.selectByPrimaryKey(userId);
                if(carManagerUser!=null){
                    carAutoLog.setUserMobile(carManagerUser.getUserPhone());
                    carAutoLog.setUserName(carManagerUser.getUserName());
                }
            }
            carAuctionCarModel.deleteById(carLocaleAuctionCar.getId());
        }
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",model.deleteById(id));
        result.setResult(resultMap);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> insert(CarLocaleAuction carAuction) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",model.insert(carAuction));
        result.setResult(resultMap);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> updateByIdSelective(CarLocaleAuction carAuction) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",model.updateByIdSelective(carAuction));
        result.setResult(resultMap);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> updateById(CarLocaleAuction carAuction) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        result.setSuccess(true);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",model.updateById(carAuction));
        result.setResult(resultMap);
        return result;
    }

    /**
     * 根据regionId获取场次列表
     * @param map
     * @return
     */
    @Override
    public ServiceResult<List<CarLocaleAuction>> selectAuctionList(Map<String,Object> map){
        ServiceResult<List<CarLocaleAuction>> result=new ServiceResult<>();
        List<CarLocaleAuction> carAuctions = model.selectAuctionList(map);
        for(CarLocaleAuction carAuction:carAuctions){
            map = new HashMap<>();
            map.put("auctionId",carAuction.getId());
            //map.put("auctionStatus","1");
            int carNum = carAuctionCarModel.selectCarNumByAuction(map);
            carAuction.setCarNum(carNum);
        }
        result.setSuccess(true);
        result.setResult(carAuctions);
        return result;
    }
    /**
     * 根据regionId获取场次列表
     * @param map
     * @return
     */
    @Override
    public ServiceResult<List<CarLocaleAuction>> selectAuctionListForApp(Map<String,Object> map){

        ServiceResult<List<CarLocaleAuction>> result=new ServiceResult<>();
        List<CarLocaleAuction> carAuctions = model.selectAuctionList(map);
        for(CarLocaleAuction carAuction:carAuctions){
            map = new HashMap<>();
            map.put("auctionId",carAuction.getId());
            map.put("auctionStatusArr","'0','1','2'");
            int carNum = carAuctionCarModel.selectCarNumByAuction(map);
            carAuction.setCarNum(carNum);
        }
        result.setSuccess(true);
        result.setResult(carAuctions);
        return result;
    }

    public ServiceResult<List<CarLocaleAuction>> getAuctionList(Map<String,Object> map){
        ServiceResult<List<CarLocaleAuction>> result=new ServiceResult<>();
        List<CarLocaleAuction> carAuctions = model.selectAuctionList(map);
        for(CarLocaleAuction carAuction:carAuctions){
            map = new HashMap<>();
            map.put("auctionId",carAuction.getId());
            int carNum = carAuctionCarModel.selectCarNumByAuction(map);
            carAuction.setCarNum(carNum);
        }
        result.setSuccess(true);
        result.setResult(carAuctions);
        return result;
    }

    /**
     * 场次数量
     * @param map
     * @return
     */
    @Override
    public ServiceResult<Integer> selectAuctionCount(Map<String,Object> map){
        ServiceResult<Integer> result=new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(model.selectAuctionCount(map));
        return result;
    }

    /**
     * 场次数量
     * @param map
     * @return
     */
    @Override
    public ServiceResult<Integer> largeScreenDisplayCount(Map<String,Object> map){
        ServiceResult<Integer> result=new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(model.largeScreenDisplayCount(map));
        return result;
    }
    /**
     * 获取中心发拍我的车辆列表
     * @param map
     * @return
     */
    @Override
    public ServiceResult<List<CarLocaleAuction>> selectCenterRacketCarList(Map<String,Object> map){

        ServiceResult<List<CarLocaleAuction>> result=new ServiceResult<>();
        List<CarLocaleAuction> carAuctions = model.selectCenterRacketCarList(map);
        result.setSuccess(true);
        result.setResult(carAuctions);
        return result;
    }

    /**
     * 获取中心发拍我的车辆数量
     * @param map
     * @return
     */
    @Override
    public ServiceResult<Integer> selectCenterRacketCarCount(Map<String,Object> map){
        ServiceResult<Integer> result=new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(model.selectCenterRacketCarCount(map));
        return result;
    }
    /**
     * @Author 付陈林
     * @Date 2018-3-19
     * @About 根据ID获取到场次的信息及场次的车辆信息
     * */
    @Transactional
    public ServiceResult<Map<String,Object>> getLocaleAuctionDetail(Long id){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        result.setSuccess(true);
        Map<String,Object> resultMap=new HashMap<>();
        //获取到实体的信息
        Map<String,Object> entityMap =new HashMap<>();
        CarLocaleAuction carLocaleAuction =model.selectById(id);
        if(carLocaleAuction==null){
            return new ServiceResult<>(false,"根据id获取不到场次信息","101");
        }else{
            //获取参与车辆数
            Map map = new HashMap<>();
            map.put("auctionId",carLocaleAuction.getId());

            Integer carNum =carAuctionCarModel.selectCarNumByAuction(map);
            //查询已结束拍卖的车辆
            map.put("auctionStatusArr","'2','3','4'");
            Integer finishCarNum =carAuctionCarModel.selectCarNumByAuction(map);
            map.put("auctionStatusArr",null);
            map.put("auctionStatus","2");
            Integer successNum =carAuctionCarModel.selectCarNumByAuction(map);
            entityMap.put("auctionId",carLocaleAuction.getId());
            entityMap.put("carNum",carNum);
            entityMap.put("successNum",successNum);
            BigDecimal successScale =new BigDecimal(0);
            BigDecimal carNumB=new BigDecimal(finishCarNum);
            BigDecimal successNumB=new BigDecimal(successNum);
            if(finishCarNum!=null&&finishCarNum!=0&&successNum!=null&&successNum!=0){
                successScale = successNumB.multiply(new BigDecimal(100)).divide(carNumB,2,BigDecimal.ROUND_HALF_UP);
            }
            entityMap.put("successScale",successScale);
            entityMap.put("status",carLocaleAuction.getStatus());
            entityMap.put("code",carLocaleAuction.getCode());
            entityMap.put("title",carLocaleAuction.getTitle());
            String regionName="";
            if(carLocaleAuction.getRegionId()!=null){
                String[] regionIds =carLocaleAuction.getRegionId().split(",");
                for(String regionId:regionIds){
                    Long carCustomerGroupId =Long.valueOf(regionId);
                    CarCustomerGroup carCustomerGroup = carCustomerGroupModel.selectByPrimaryKey(carCustomerGroupId);
                    if(carCustomerGroup!=null){
                        if(regionName!=null&&!"".equals(regionName)){
                            regionName=regionName+","+carCustomerGroup.getGroupName();
                        }else{
                            regionName=carCustomerGroup.getGroupName();
                        }

                    }
                }
            }
            entityMap.put("regionName",regionName);
            entityMap.put("address",carLocaleAuction.getAddress());
            entityMap.put("seeCarMan",carLocaleAuction.getSeeCarMan());
            entityMap.put("seeCarPhone",carLocaleAuction.getSeeCarPhone());
            entityMap.put("seeCarTime",carLocaleAuction.getSeeCarTime());
        }
        resultMap.put("entity",entityMap);
        List<CarLocaleAuctionCar> carLocaleAuctionCars =carAuctionCarModel.getAuctionCarList(id);
        List<Map<String,Object>> entriesList=new ArrayList<>();
        for(CarLocaleAuctionCar carLocaleAuctionCar:carLocaleAuctionCars){
            Map<String,Object> carMap=new HashMap<>();
            carMap.put("id",carLocaleAuctionCar.getId());
            carMap.put("carId",carLocaleAuctionCar.getCarId());
            carMap.put("auctionId",carLocaleAuctionCar.getAuctionId());
            carMap.put("auctionCode",carLocaleAuctionCar.getAuctionCode());
            carMap.put("auctionStatus",carLocaleAuctionCar.getAuctionStatus());
            carMap.put("sort",carLocaleAuctionCar.getSort());
            carMap.put("carAutoNo",carLocaleAuctionCar.getCarAutoNo());
            carMap.put("autoInfoName",carLocaleAuctionCar.getAutoInfoName());
            carMap.put("licenseNumber",carLocaleAuctionCar.getLicenseNumber());
            carMap.put("startingPrice",carLocaleAuctionCar.getStartingPrice());
            carMap.put("reservePrice",carLocaleAuctionCar.getReservePrice());
            carMap.put("storeName",carLocaleAuctionCar.getStoreName());
            //最后出价
            Map<String,Object> paramMap =new HashMap<>();
            paramMap.put("carId",carLocaleAuctionCar.getCarId());
            paramMap.put("auctionCarId",carLocaleAuctionCar.getId());
//            paramMap.put("auctionId",carLocaleAuctionCar.getAutoAuctionId());
            BigDecimal lastBidPrice = carAuctionBidRecordModel.selectMaxPrice(paramMap);
            carMap.put("lastBidPrice",lastBidPrice);
            carMap.put("beginRegisterDate",carLocaleAuctionCar.getBeginRegisterDate());
            carMap.put("sourceType",carLocaleAuctionCar.getSourceType());
            if(carLocaleAuctionCar.getAuctionNum()!=null){
                carMap.put("auctionNum",carLocaleAuctionCar.getAuctionNum());
            }else{
                carMap.put("auctionNum",0);
            }
            carMap.put("publishUserName",carLocaleAuctionCar.getPublishUserName());
            carMap.put("status", carLocaleAuctionCar.getStatus());
            entriesList.add(carMap);
        }
        resultMap.put("shareUri", Constants.STATIC_WEBSITE+"/carauction/share/index.html");
        resultMap.put("entries",entriesList);
        result.setResult(resultMap);
        return result;
    }
    public ServiceResult<Map<String,Object>> getLocaleAuctionById(Long id){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        result.setSuccess(true);
        CarLocaleAuction carLocaleAuction =model.selectById(id);
        Map<String,Object> resultMap=new HashMap<>();
        if(carLocaleAuction==null){
            return new ServiceResult<>(false,"根据id获取不到场次信息","101");
        }else{
            resultMap.put("id",carLocaleAuction.getId());
            resultMap.put("code",carLocaleAuction.getCode());
            resultMap.put("title",carLocaleAuction.getTitle());
            resultMap.put("regionId",carLocaleAuction.getRegionId());
            resultMap.put("cityId",carLocaleAuction.getCityId());
            resultMap.put("poster",carLocaleAuction.getPoster());
            resultMap.put("startTime",carLocaleAuction.getStartTime());
            resultMap.put("corporateAgent",carLocaleAuction.getCorporateAgent());
            resultMap.put("address",carLocaleAuction.getAddress());
            resultMap.put("seeCarMan",carLocaleAuction.getSeeCarMan());
            resultMap.put("seeCarPhone",carLocaleAuction.getSeeCarPhone());
            resultMap.put("seeCarTime",carLocaleAuction.getSeeCarTime());
            resultMap.put("stationRealId",carLocaleAuction.getStationRealId());
            resultMap.put("templateId", carLocaleAuction.getTemplateId());
        }
        result.setResult(resultMap);
        return result;
    }
    /**
     * @Author 付陈林
     * @Date 2018-3-19
     * @About 批量删除场次的车辆
     * */
    @Transactional
    public ServiceResult<Map<String,Object>> batchDeleteAuctionCar(String ids){
        String[] idArray =ids.split(",");
        if(idArray!=null&&idArray.length>0){
            for(String id :idArray){
                Long longId = Long.valueOf(id);
                if(longId!=null&&longId!=0){
                    carAuctionCarModel.deleteById(longId);
                }
            }
            return new ServiceResult<Map<String,Object>>(true,"删除成功！");
        }else{
            return new ServiceResult<Map<String,Object>>(false,"需要更新的删除的id为空！");
        }
    }

    /**
     * 查询所有有效拍卖场次
     * @return
     */
    @Override
    public List<CommonNameVo> selectAllValidAuction(){
        return model.selectAllValidAuction();
    }

    @Transactional
    public ServiceResult<Map<String,Object>> deleteLocalAuction(Long id){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        //获取该场次的关联车辆信息
        List<CarLocaleAuctionCar> carLocaleAuctionCars = carAuctionCarModel.getAuctionCarList(id);
        for(CarLocaleAuctionCar carLocaleAuctionCar:carLocaleAuctionCars){
            carAuctionCarModel.deleteById(carLocaleAuctionCar.getId());
        }
        Integer count =model.deleteById(id);
        result.setSuccess(true);
        Map resultMap =new HashMap();
        resultMap.put("count",count);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }

    public ServiceResult<List<CarLocaleAuction>> largeScreenDisplay(Map<String,Object> map){
        ServiceResult<List<CarLocaleAuction>> result=new ServiceResult<>();
        List<CarLocaleAuction> carAuctions = model.largeScreenDisplay(map);
        for(CarLocaleAuction carAuction:carAuctions){
            map = new HashMap<>();
            map.put("auctionId",carAuction.getId());
            int carNum = carAuctionCarModel.selectCarNumByAuction(map);
            carAuction.setCarNum(carNum);
        }
        result.setSuccess(true);
        result.setResult(carAuctions);
        return result;
    }
    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次Id进行开拍操作
     * */
    @Transactional
    public ServiceResult<Map<String,Object>> largeScreenStartAuction(Long auctionId){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        //将场次变为正在竞拍
        CarLocaleAuction carLocaleAuction =model.selectById(auctionId);
        if(carLocaleAuction==null||carLocaleAuction.getStatus()==null||!"2".equals(carLocaleAuction.getStatus())){
            return new ServiceResult<>(false,"只有等待开拍的场次才可以开拍！","101");
        }
        CarLocaleAuction updateAuction=new CarLocaleAuction();
        updateAuction.setId(auctionId);
        //正在竞拍
        updateAuction.setStatus("3");
        model.updateByIdSelective(updateAuction);
        //将场次车辆变更为拍卖中、将车辆信息变更为正在竞拍
        List<CarLocaleAuctionCar> carLocaleAuctionCars =carAuctionCarModel.getAuctionCarList(auctionId);
        for(CarLocaleAuctionCar carLocaleAuctionCar:carLocaleAuctionCars){
            if(carLocaleAuctionCar!=null){
                //场次
                CarLocaleAuctionCar updateAuctionCar=new CarLocaleAuctionCar();
                updateAuctionCar.setId(carLocaleAuctionCar.getId());
                //拍卖中
                updateAuctionCar.setAuctionStatus("1");
                carAuctionCarModel.updateByIdSelective(updateAuctionCar);
                //车辆
                CarAuto carAuto =new CarAuto();
                carAuto.setId(carLocaleAuctionCar.getCarId());
                //正在竞拍
                carAuto.setStatus("7");
                carAutoModel.updateByPrimaryKeySelective(carAuto);
                //车辆竞拍信息
                CarAutoAuction carAutoAuction =new CarAutoAuction();
                carAutoAuction.setId(carLocaleAuctionCar.getAutoAuctionId());
                carAutoAuction.setStatus("2");
                carAutoAuctionModel.updateByPrimaryKeySelective(carAutoAuction);
            }
        }
        Map resultMap =new HashMap();
        resultMap.put("count",1);
        result.setResult(resultMap);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次Id进行开拍操作
     * */
    public ServiceResult<Map<String,Object>> largeScreenBidPrice(Long auctionCarId){
        CarLocaleAuctionCar carLocaleAuctionCar =carAuctionCarModel.selectById(auctionCarId);
        if(carLocaleAuctionCar==null||carLocaleAuctionCar.getAuctionId()==null||carLocaleAuctionCar.getCarId()==null){
            return new ServiceResult<>(false,"查询不到场次车辆信息","101");
        }
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        CarAuctionBidRecord carAuctionBidRecord = carAuctionBidRecordModel.selectLastBidRecord(carLocaleAuctionCar.getId());
        Map<String,Object> resultMap =new HashMap();
        BigDecimal serviceFee =new BigDecimal(0);
        BigDecimal agenFee =new BigDecimal(0);
        BigDecimal lastBid=new BigDecimal(0);
        if(carAuctionBidRecord!=null&&carAuctionBidRecord.getBidFee()!=null){
            lastBid=carAuctionBidRecord.getBidFee();
        }
        if(lastBid!=null&&lastBid.compareTo(new BigDecimal(0))>0){
            resultMap.put("lastBid",lastBid);
            CarLocaleAuction carLocaleAuction =model.selectById(carLocaleAuctionCar.getAuctionId());
            if(carLocaleAuction!=null){
                CarRegionSetting carRegionSetting = carRegionSettingModel.selectByRegionId(carLocaleAuction.getCityId());
                if(carRegionSetting!=null){
                    agenFee =carRegionSetting.getAgentFee();
                    List<CarRegionServerfeeSetting> serverfeeSettings = serverfeeSettingModel.selectByRegionSettingId(carRegionSetting.getId());
                    if(serverfeeSettings!=null&&serverfeeSettings.size()>0){
                        for(CarRegionServerfeeSetting carRegionServerfeeSetting:serverfeeSettings){
                            if(carRegionServerfeeSetting.getStartClosingPrice().compareTo(lastBid)<=0&&carRegionServerfeeSetting.getEndClosingPrice().compareTo(lastBid)>0){
                                serviceFee =carRegionServerfeeSetting.getServiceFee();
                            }
                        }
                    }else{
                        return new ServiceResult<>(false,"获取不到服务费","101");
                    }
                }else{
                    return new ServiceResult<>(false,"获取不到该地区的代办费！","101");
                }
            }else{
                return new ServiceResult<>(false,"查询不到场次信息","101");
            }
        }else{
            return new ServiceResult<>(false,"未获取到出价记录","102");
        }
        if(serviceFee==null||agenFee==null||agenFee.compareTo(new BigDecimal(0))==0||serviceFee.compareTo(new BigDecimal(0))==0){
            return new ServiceResult<>(false,"获取不到代办费或者服务费,请确定该地区已经设置！","101");
        }
        resultMap.put("combinedPrice",lastBid.add(serviceFee).add(agenFee));
        result.setResult(resultMap);
        result.setSuccess(true);
        return result;
    }

    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次车辆Id获取,确定该车最终的成交情况
     * */
    @Transactional
    public ServiceResult<Map<String,Object>> largeScreenAuctionResult(Long auctionCarId,Long userId){
        CarLocaleAuctionCar carLocaleAuctionCar =carAuctionCarModel.selectById(auctionCarId);
        if(carLocaleAuctionCar==null||carLocaleAuctionCar.getAuctionId()==null||carLocaleAuctionCar.getCarId()==null){
            return new ServiceResult<>(false,"查询不到场次车辆信息","101");
        }
        //获取到目前的最高出价
        Map<String,Object> paramMap =new HashMap<>();
        paramMap.put("auctionId",carLocaleAuctionCar.getAuctionId());
        paramMap.put("carId",carLocaleAuctionCar.getCarId());
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        //最高出价
        CarAuctionBidRecord carAuctionBidRecord = carAuctionBidRecordModel.selectLastBidRecord(auctionCarId);
        if(carAuctionBidRecord==null||carAuctionBidRecord.getBidFee()==null||carAuctionBidRecord.getBidFee().compareTo(new BigDecimal(0))==0){
//            return new ServiceResult<>(false,"获取不到最高出价","101");
            carAuctionBidRecord = new CarAuctionBidRecord();
            carAuctionBidRecord.setBidFee(BigDecimal.valueOf(new Long(0)));
        }
        //查询车辆的保留价
        CarAutoAuction carAutoAuction = carAutoAuctionModel.selectByPrimaryKey(carLocaleAuctionCar.getAutoAuctionId());
        if(carAutoAuction==null||carAutoAuction.getReservePrice()==null||carAutoAuction.getReservePrice().compareTo(new BigDecimal(0))==0){
            return new ServiceResult<>(false,"获取不到车辆的竞拍信息","101");
        }
        Map resultMap =new HashMap();
        //获取服务费、代理费
        BigDecimal agenFee =new BigDecimal(0);
        BigDecimal serviceFee=new BigDecimal(0);
        CarLocaleAuction carLocaleAuction =model.selectById(carLocaleAuctionCar.getAuctionId());
        if(carLocaleAuction!=null){
            CarRegionSetting carRegionSetting = carRegionSettingModel.selectByRegionId(carLocaleAuction.getCityId());
            if(carRegionSetting!=null){
                agenFee =carRegionSetting.getAgentFee();
                List<CarRegionServerfeeSetting> serverfeeSettings = serverfeeSettingModel.selectByRegionSettingId(carRegionSetting.getId());
                if(serverfeeSettings!=null&&serverfeeSettings.size()>0){
                    for(CarRegionServerfeeSetting carRegionServerfeeSetting:serverfeeSettings){
                        if(carRegionServerfeeSetting.getStartClosingPrice().compareTo(carAuctionBidRecord.getBidFee())<=0&&carRegionServerfeeSetting.getEndClosingPrice().compareTo(carAuctionBidRecord.getBidFee())>0){
                            serviceFee =carRegionServerfeeSetting.getServiceFee();
                        }
                    }
                }else{
                    return new ServiceResult<>(false,"获取不到服务费","101");
                }
            }else{
                return new ServiceResult<>(false,"获取不到该地区的代办费！","101");
            }
        }else{
            return new ServiceResult<>(false,"查询不到场次信息","101");
        }
        if(serviceFee==null||agenFee==null||agenFee.compareTo(new BigDecimal(0))==0||serviceFee.compareTo(new BigDecimal(0))==0){
            return new ServiceResult<>(false,"获取不到代办费或者服务费,请确定该地区已经设置！","101");
        }
        if(carAutoAuction.getIfAgent()!=null&&"2".equals(carAutoAuction.getIfAgent())){
            agenFee=new BigDecimal(0);
        }
        resultMap.put("lastBid",carAuctionBidRecord.getBidFee());
        resultMap.put("combinedPrice",carAuctionBidRecord.getBidFee().add(agenFee).add(serviceFee));
        CarAuto carAuto =carAutoModel.selectByPrimaryKey(carLocaleAuctionCar.getCarId());
        if(carAuctionBidRecord.getBidFee().compareTo(carAutoAuction.getReservePrice())>=0){
            //最高出价大于保留价，表示成交
            resultMap.put("auctionStatus","2");
            largeScreenAuctionExecute(carLocaleAuction,carLocaleAuctionCar,carAuto,carAutoAuction,carAuctionBidRecord,agenFee,serviceFee,
                    "2","8","4","2","8",
                    "该车辆在"+carLocaleAuction.getTitle()+"场次中竞拍成功！",userId);
        }else{
            //最高出价小于保留价，表示流拍
            resultMap.put("auctionStatus","3");
            largeScreenAuctionExecute(carLocaleAuction,carLocaleAuctionCar,carAuto,carAutoAuction,carAuctionBidRecord,agenFee,serviceFee,
                                     "3","19","3","2","19",
                                     "该车辆在"+carLocaleAuction.getTitle()+"场次中流拍了！",userId);
        }
        result.setResult(resultMap);
        result.setSuccess(true);
        return result;
    }

    private void largeScreenAuctionExecute(CarLocaleAuction carLocaleAuction,
                                            CarLocaleAuctionCar carLocaleAuctionCar,
                                            CarAuto carAuto,
                                            CarAutoAuction carAutoAuction,
                                            CarAuctionBidRecord carAuctionBidRecord,
                                            BigDecimal agenFee,
                                            BigDecimal serviceFee,
                                            String carLocaleAuctionCar_status,
                                            String carAuto_status,
                                            String carAutoAuction_status,
                                            String carAutoLog_user_type,
                                            String carAutoLog_status,
                                            String log_msg,
                                            Long userId){
        //step 1 更新车辆场次信息
        CarLocaleAuctionCar updateAuctionCar =new CarLocaleAuctionCar();
        updateAuctionCar.setId(carLocaleAuctionCar.getId());
        updateAuctionCar.setAuctionStatus(carLocaleAuctionCar_status);
        carAuctionCarModel.updateByIdSelective(updateAuctionCar);
        //setp 2 更新车辆信息
        CarAuto updateCarAuto =new CarAuto();
        updateCarAuto.setId(carLocaleAuctionCar.getCarId());
        int auctionNum=0;
        if(carAuto.getAuctionNum()!=null){
            auctionNum=carAuto.getAuctionNum()+1;
        }else{
            auctionNum=1;
        }
        updateCarAuto.setAuctionNum(auctionNum);
        updateCarAuto.setStatus(carAuto_status);
        updateCarAuto.setUpdateTime(new Date());
        updateCarAuto.setUpdateUser(userId);
        carAutoModel.updateByPrimaryKeySelective(updateCarAuto);
        //step 3 更新车辆竞拍信息
        CarAutoAuction updateAutoAuction =new CarAutoAuction();
        updateAutoAuction.setId(carAutoAuction.getId());
        updateAutoAuction.setStatus(carAutoAuction_status);
        updateAutoAuction.setAgentPrice(agenFee);
        updateAutoAuction.setServicePrice(serviceFee);
        updateAutoAuction.setAuctionEndTime(new Date());
        updateAutoAuction.setTopBidPrice(carAuctionBidRecord.getBidFee());
        updateAutoAuction.setTopBidTime(carAuctionBidRecord.getBidTime());
        updateAutoAuction.setTopPricerId(carAuctionBidRecord.getCustomerId());
        updateAutoAuction.setBidersCount(carAuctionBidRecordModel.selectCustomerBidCountForLocale(carLocaleAuctionCar.getId()));
        updateAutoAuction.setBidsCount(carAuctionBidRecordModel.selectBidCountForLocale(carLocaleAuctionCar.getId()));
        updateAutoAuction.setCreatePerson(userId);
        updateAutoAuction.setCreateTime(new Date());
        carAutoAuctionModel.updateByPrimaryKeySelective(updateAutoAuction);
        //step 4
        CarAutoLog carAutoLog = new CarAutoLog();
        carAutoLog.setId(idWorker.nextId());
        carAutoLog.setAutoId(carLocaleAuctionCar.getCarId());
        carAutoLog.setUserType(carAutoLog_user_type);
        carAutoLog.setStatus(carAutoLog_status);
        carAutoLog.setTime(new Date());
        carAutoLog.setMsg(log_msg);
        carAutoLog.setUserId(userId);
        if(userId!=null){
            CarManagerUser carManagerUser=carManagerUserModel.selectByPrimaryKey(userId);
            if(carManagerUser!=null){
                carAutoLog.setUserMobile(carManagerUser.getUserPhone());
                carAutoLog.setUserName(carManagerUser.getUserName());
            }
        }
        carAutoLogModel.insert(carAutoLog);
        //对redis中的数据进行状态变更，
        //判断是否第一条
        String  temporaryFastStr =(String) redisTemplate.opsForHash().get(String.valueOf(carLocaleAuctionCar.getAuctionId()),"0");
        Map<String,Object> temporaryFastMap = JSONObject.parseObject(temporaryFastStr,Map.class);
        long a=carLocaleAuctionCar.getId();
        long b=(Long)temporaryFastMap.get("auctionCarId");
        if(temporaryFastMap!=null&&a==b){
            //如果是第一辆车
            if(temporaryFastMap!=null&&temporaryFastMap.get("status")!=null){
                temporaryFastMap.remove("status");
                temporaryFastMap.put("status",carLocaleAuctionCar_status);
                temporaryFastStr =JSONObject.toJSONString(temporaryFastMap);
                redisTemplate.opsForHash().delete(String.valueOf(carLocaleAuctionCar.getAuctionId()),"0");
                redisTemplate.opsForHash().put(String.valueOf(carLocaleAuctionCar.getAuctionId()),"0",temporaryFastStr);
            }
        }else{
            String  temporaryStr =(String) redisTemplate.opsForHash().get(String.valueOf(carLocaleAuctionCar.getAuctionId()),String.valueOf(carLocaleAuctionCar.getId()));
            Map<String,Object> temporaryMap = JSONObject.parseObject(temporaryStr,Map.class);
            if(temporaryMap!=null&&temporaryMap.get("status")!=null){
                temporaryMap.remove("status");
                temporaryMap.put("status",carLocaleAuctionCar_status);
                temporaryStr =JSONObject.toJSONString(temporaryMap);
                redisTemplate.opsForHash().delete(String.valueOf(carLocaleAuctionCar.getAuctionId()),String.valueOf(carLocaleAuctionCar.getId()));
                redisTemplate.opsForHash().put(String.valueOf(carLocaleAuctionCar.getAuctionId()),String.valueOf(carLocaleAuctionCar.getId()),temporaryStr);
            }
        }


        //如果是车辆成交了需要生成订单操作。
        if("2".equals(carLocaleAuctionCar_status)){
            //生成订单信息
            CarOrder carOrder =new CarOrder();
            carOrder.setId(idWorker.nextId());
            carOrder.setAuctionId(carLocaleAuction.getId());
            carOrder.setOrderNo(String.valueOf(idWorker.nextId()));
            carOrder.setCarId(carAuto.getId());
            carOrder.setCustomerId(carAuctionBidRecord.getCustomerId());
            carOrder.setTransactionFee(carAuctionBidRecord.getBidFee());
            carOrder.setAmountFee(carAuctionBidRecord.getBidFee().add(agenFee).add(serviceFee));
            carOrder.setAgentFee(agenFee);
            carOrder.setServiceFee(serviceFee);
            carOrder.setStatus("1");
            carOrder.setLockFee(carAutoAuction.getReservePrice());
            carOrder.setCreateTime(new Date());
            carOrder.setAuctionBidRecordId(carAuctionBidRecord.getId());
            carOrder.setCreateUser(userId);
            carOrder.setPayEndTime(carRegionSettingModel.getBreachTime(new Date(),carLocaleAuction.getCityId(),"1"));
            carOrder.setAutoAuctionId(carAutoAuction.getId());
            carOrderModel.insert(carOrder);
            //生成订单日志信息
            CarOrderLog carOrderLog =new CarOrderLog();
            carOrderLog.setId(idWorker.nextId());
            carOrderLog.setStatus("1");
            carOrderLog.setStatusCn("等待付款");
            carOrderLog.setCreateTime(new Date());
            carOrderLog.setLogMsg("订单生成");
            carOrderLog.setOrderId(carOrder.getId());
            carOrderLog.setCreatePerson(userId);
            if(userId!=null){
                CarManagerUser carManagerUser=carManagerUserModel.selectByPrimaryKey(userId);
                if(carManagerUser!=null){
                    carOrderLog.setUserMobile(carManagerUser.getUserPhone());
                    carOrderLog.setUserName(carManagerUser.getUserName());
                }
            }
            carOrderLogModel.insert(carOrderLog);
        }
    }

    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次Id获取更新最后的场次竞拍结果
     * */
    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> largeScreenAuctionFinish(Long auctionId){
        CarLocaleAuction carLocaleAuction =model.selectById(auctionId);
        if(carLocaleAuction==null||!"3".equals(carLocaleAuction.getStatus())){
            return new ServiceResult<>(false,"只有在竞拍中状态的场次，才可以结束！","101");
        }
        //判断该场次下面是否还有竞拍完成的车辆状态为（0、等待开拍，1、拍卖中 ）
        Map<String,Object> paramMap =new HashMap<>();
        paramMap.put("auctionId",auctionId);
        paramMap.put("auctionStatus","0");
        //待开拍
        int i = carAuctionCarModel.selectCarNumByAuction(paramMap);
        paramMap.clear();
        paramMap.put("auctionId",auctionId);
        paramMap.put("auctionStatus","1");
        //拍卖中
        int j=carAuctionCarModel.selectCarNumByAuction(paramMap);
        if((i+j)>0){
            return new ServiceResult<>(false,"该场次还有未竞拍完成的车辆","101");
        }
        CarLocaleAuction updateAuction =new CarLocaleAuction();
        updateAuction.setId(carLocaleAuction.getId());
        updateAuction.setStatus("4");
        Integer count =model.updateByIdSelective(updateAuction);
        //将转渠道的流拍车辆定义为草稿
        //查询拍卖场次转渠道并且流拍的车
        paramMap.clear();
        paramMap.put("auctionId",auctionId);
        paramMap.put("status", CarStatusEnum.ABORTIVE_AUCTION.value());
        paramMap.put("transferFlag","1");
        List<CarAutoAuction> carAutoAuctionList = carAutoAuctionModel.selectAutoAuctionBylocale(paramMap);
        if(carAutoAuctionList != null && carAutoAuctionList.size() > 0){
            //将车辆定义为草稿状态
            for(CarAutoAuction autoAuction: carAutoAuctionList ){
                carAutoModel.updateAutoData(autoAuction);
            }
        }
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map resultMap =new HashMap();
        resultMap.put("count",count);
        result.setResult(resultMap);
        result.setSuccess(true);
        //清空redis中的数据
        redisTemplate.delete(String.valueOf(auctionId));
        return result;
    }

    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次Id获取更新最后的场次竞拍结果
     * */
    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> clearLargeScreenAuctionCar(Long auctionId){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map resultMap =new HashMap();
        resultMap.put("count",1);
        result.setResult(resultMap);
        result.setSuccess(true);
        //清空redis中的数据
        redisTemplate.delete(String.valueOf(auctionId));
        return result;
    }

    /**
     * 获取拍卖城市数量
     */
    @Transactional
    public ServiceResult<Integer> selectCityCount(Map<String,Object> map){
        ServiceResult<Integer> result = new ServiceResult<>();
        Integer count = model.selectCityCount(map);
        result.setResult(count);
        return result;
    }

    /**
     * 首页获取场次数量
     */
    @Transactional
    public ServiceResult<Integer> queryAuctionCount(Map<String,Object> map){
        ServiceResult<Integer> result = new ServiceResult<>();
        Integer count = model.queryAuctionCount(map);
        result.setResult(count);
        return result;
    }
    /**
     * 首页开拍场次时间查询
     * @param map
     * @return
     */
    @Override
    public ServiceResult<List<CarLocaleAuction>> queryCarLocaleAuctionList(Map<String,Object> map){

        ServiceResult<List<CarLocaleAuction>> result=new ServiceResult<>();
        List<CarLocaleAuction> carAuctions = model.queryCarLocaleAuctionList(map);
        result.setSuccess(true);
        result.setResult(carAuctions);
        return result;
    }

    /**
     * 根据日期询基站的竞拍场次
     * @param stationRealId
     * @return
     */
    @Override
    public CarLocaleAuction selectByStationRealId(String stationRealId,String auctionDate){
        return model.selectByStationRealId(stationRealId,auctionDate);
    }
}
