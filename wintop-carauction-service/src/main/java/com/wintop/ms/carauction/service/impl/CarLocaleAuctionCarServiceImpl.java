package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarLocaleAuctionCarService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CarLocaleAuctionCarServiceImpl implements ICarLocaleAuctionCarService {
    @Autowired
    private CarLocaleAuctionCarModel model;
    @Autowired
    private CarLocaleAuctionModel carLocaleAuctionModel;
    @Autowired
    private CarLocaleAuctionCarModel carLocaleAuctionCarModel;
    @Autowired
    private CarAuctionBidRecordModel carAuctionBidRecordModel;
    @Autowired
    private CarAutoAuctionModel carAutoAuctionModel;
    @Autowired
    private CarAutoPhotoModel carAutoPhotoModel;
    @Autowired
    private CarAutoModel carAutoModel;
    @Autowired
    private CarRegionServerfeeSettingModel serverfeeSettingModel;
    @Autowired
    private CarAutoLogModel carAutoLogModel;
    @Autowired
    private CarRegionSettingModel carRegionSettingModel;
    @Autowired
    private CarManagerUserModel carManagerUserModel;
    @Autowired
    private CarOrderModel carOrderModel;
    @Autowired
    private AppUserModel appUserModel;


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
    @Transactional
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarLocaleAuctionCar> selectByExample(Map<String, Object> map) {
        List<CarLocaleAuctionCar> list = model.selectByExample(map);
        return list;
    }

    @Override
    public CarLocaleAuctionCar selectById(Long id) {
        CarLocaleAuctionCar auctionCar = model.selectById(id);
        return auctionCar;
    }

    @Override
    public List<CarLocaleAuctionCar> selectByCarId(Long carId) {
        List<CarLocaleAuctionCar> list = model.selectByCarId(carId);
        return list;
    }

    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> deleteById(Long id,Long userId) {
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        //调整该场次车辆的车辆状态
        CarLocaleAuctionCar carLocaleAuctionCar =model.selectById(id);
        CarAuto carAuto =new CarAuto();
        carAuto.setId(carLocaleAuctionCar.getCarId());
        carAuto.setStatus("5");
        carAutoModel.updateByPrimaryKeySelective(carAuto);
        CarLocaleAuction carLocaleAuction =carLocaleAuctionModel.selectById(carLocaleAuctionCar.getAuctionId());
        //清空redis中的数据
        redisTemplate.delete(String.valueOf(carLocaleAuction.getId()));
        //添加车辆操作日志
        CarAutoLog carAutoLog = new CarAutoLog();
        carAutoLog.setId(idWorker.nextId());
        carAutoLog.setAutoId(carLocaleAuctionCar.getCarId());
        carAutoLog.setUserType("2");
        carAutoLog.setStatus("5");
        carAutoLog.setStatusName("等待上拍");
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
        carAutoLogModel.insert(carAutoLog);
        Integer count = model.deleteById(id);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("auctionId",carLocaleAuction.getId());
        result.setResult(resultMap);
        result.setSuccess(true);
        return result;
    }

    @Override
    @Transactional
    public Integer insert(CarLocaleAuctionCar carAuctionCar) {
        Integer count = model.insert(carAuctionCar);
        return count;
    }

    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> updateByIdSelective(CarLocaleAuctionCar carAuctionCar) {
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        Map  resultMap =new HashMap();
        Integer count = model.updateByIdSelective(carAuctionCar);
        resultMap.put("count",count);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }

    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> updateAuctionCode(Long auctionCarId) {

        //CarLocaleAuctionCar auctionCar = carLocaleAuctionCarModel.selectById(auctionCarId);

        List<CarLocaleAuctionCar> carLocaleAuctionCars = model.getAuctionCarList(auctionCarId);
        List<CarLocaleAuctionCar> updateList=new ArrayList<>();
        int auctionCode =1;
        for(int i=0;i<carLocaleAuctionCars.size();i++){
            CarLocaleAuctionCar carLocaleAuctionCar =carLocaleAuctionCars.get(i);
            CarLocaleAuctionCar updateAuctionCar =new CarLocaleAuctionCar();
            updateAuctionCar.setId(carLocaleAuctionCar.getId());
            if(auctionCode>=100){
                updateAuctionCar.setAuctionCode(""+auctionCode);
            }else if(auctionCode>=10&&auctionCode<100){
                updateAuctionCar.setAuctionCode("0"+auctionCode);
            }else{
                updateAuctionCar.setAuctionCode("00"+auctionCode);
            }
            updateList.add(updateAuctionCar);
            auctionCode++;
        }
        for(CarLocaleAuctionCar carLocaleAuctionCar:updateList){
            model.updateByIdSelective(carLocaleAuctionCar);
        }
        ServiceResult<Map<String ,Object>> result = new ServiceResult<>();
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",1);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }

    @Override
    @Transactional
    public Integer updateById(CarLocaleAuctionCar carAuctionCar) {
        Integer count = model.updateByIdSelective(carAuctionCar);
        return count;
    }

    @Transactional
    public ServiceResult<Map<String ,Object>> saveLocaleAuctionCar(Long auctionId,String carIds,Long createPerson){
        //清空redis中的数据
        redisTemplate.delete(String.valueOf(auctionId));
        String[] carIdArray = carIds.split(",");
        if(carIdArray!=null&&carIdArray.length>0){
            //查询查询该场次下面车辆的最大排序号，
            Integer maxSort =model.getMaxSortForActionCar(auctionId);
            if(maxSort==null){
                maxSort=0;
            }
            List<CarLocaleAuctionCar> updateList=new ArrayList<>();
            CarLocaleAuction carLocaleAuction =carLocaleAuctionModel.selectById(auctionId);
            for(String carIdStr:carIdArray){
                maxSort++;
                CarLocaleAuctionCar carLocaleAuctionCar =new CarLocaleAuctionCar();
                carLocaleAuctionCar.setId(idWorker.nextId());
                Long carIdLong = Long.valueOf(carIdStr);
                carLocaleAuctionCar.setCarId(carIdLong);
                CarAuto carAuto =carAutoModel.selectByPrimaryKey(carIdLong);
                carLocaleAuctionCar.setAuctionId(auctionId);
                if(carAuto==null||carAuto.getAutoAuctionId()==null){
                    return new ServiceResult<>(false,"","101");
                }
                carLocaleAuctionCar.setAutoAuctionId(carAuto.getAutoAuctionId());
                carLocaleAuctionCar.setSort(maxSort);
                if(maxSort>=100){
                    carLocaleAuctionCar.setAuctionCode(""+maxSort);
                }else if(maxSort>=10&&maxSort<100){
                    carLocaleAuctionCar.setAuctionCode("0"+maxSort);
                }else{
                    carLocaleAuctionCar.setAuctionCode("00"+maxSort);
                }
                carLocaleAuctionCar.setAuctionStatus("0");
                carLocaleAuctionCar.setCreatePerson(createPerson);
                carLocaleAuctionCar.setCreateTime(new Date());
                updateList.add(carLocaleAuctionCar);
            }
            for(CarLocaleAuctionCar carLocaleAuctionCar:updateList){
                model.insert(carLocaleAuctionCar);
                //更新车辆信息，将车辆状态改为等待开拍
                CarAuto carAuto =new CarAuto();
                carAuto.setId(carLocaleAuctionCar.getCarId());
                carAuto.setStatus("6");
                //添加车辆操作日志
                CarAutoLog carAutoLog = new CarAutoLog();
                carAutoLog.setId(idWorker.nextId());
                carAutoLog.setAutoId(carLocaleAuctionCar.getCarId());
                carAutoLog.setUserType("2");
                carAutoLog.setStatus("6");
                carAutoLog.setStatusName("等待开拍");
                carAutoLog.setTime(new Date());
                carAutoLog.setMsg("将该车辆增加到"+carLocaleAuction.getTitle()+"场次中");
                carAutoLog.setUserId(createPerson);
                if(createPerson!=null){
                    CarManagerUser carManagerUser=carManagerUserModel.selectByPrimaryKey(createPerson);
                    if(carManagerUser!=null){
                        carAutoLog.setUserMobile(carManagerUser.getUserPhone());
                        carAutoLog.setUserName(carManagerUser.getUserName());
                    }
                }
                carAutoLogModel.insert(carAutoLog);
                carAutoModel.updateByPrimaryKeySelective(carAuto);
            }
        }else{
            return new ServiceResult<>(false,"车辆的集合为空！","101");
        }
        ServiceResult<Map<String ,Object>> result = new ServiceResult<>();
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",1);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }
    public int selectCarNumByAuction(Map<String,Object> map){
        return model.selectCarNumByAuction(map);
    }

    /**
     * @Autor 付陈林
     * @Date 2018-3-24
     * @About 根据车辆关联ID，查询车辆的基本信息
     *
     * */
    public ServiceResult<CarLocaleAuctionCar> getCarLocaleAuctionCar(Long auctionCarId){
        ServiceResult<CarLocaleAuctionCar> result =new ServiceResult<>();
        CarLocaleAuctionCar  carLocaleAuctionCar=model.getCarLocaleAuctionCar(auctionCarId);
        CarAuctionBidRecord carAuctionBidRecord = carAuctionBidRecordModel.selectLastBidInfo(auctionCarId);
        if(carAuctionBidRecord!=null){
            carLocaleAuctionCar.setTopBidPrice(carAuctionBidRecord.getBidFee());
        }
        result.setSuccess(true);
        result.setResult(carLocaleAuctionCar);
        return result;
    }

    /**
     * @Autor 付陈林
     * @Date 2018-3-24
     * @About 根据场次ID查询该场次下面所有车辆
     * */
    public ServiceResult<List<CarLocaleAuctionCar>> getAuctionCarList(Long auctionId){
            ServiceResult<List<CarLocaleAuctionCar>> result =new ServiceResult<>();
            List<CarLocaleAuctionCar> resultList = model.getAuctionCarList(auctionId);
            result.setSuccess(true);
            result.setResult(resultList);
            return result;
    }
    /**
     * @Autor 付陈林
     * @Date 2018-3-25
     * @About 根据场次Id及场次车辆id，查询下一辆车的现场拍信息
     * */
    @Transactional
    public ServiceResult<Map<String,Object>> largeScreenShowCar(Long auctionId,Long auctionCarId){
        //判断redis中，该场次主键是否存在竞拍数据
        if(redisTemplate.opsForHash().size(String.valueOf(auctionId))>0){
            Map<String,Object> resultMap =new HashMap<>();
            if(auctionCarId!=null){
                String auctionCarStr = (String)redisTemplate.opsForHash().get(String.valueOf(auctionId),String.valueOf(auctionCarId));
                resultMap =JSONObject.parseObject(auctionCarStr,Map.class);
            }else{
                //如果为空，则从第一辆车进行查询。
                String auctionCarStr = (String)redisTemplate.opsForHash().get(String.valueOf(auctionId),"0");
                resultMap =JSONObject.parseObject(auctionCarStr,Map.class);
            }
            return recurrenceGetAvailabilityCar(resultMap);

        }else{
            //进行redis初始化操作
            ServiceResult<Map<String,Object>> initRedis =initRedisLocaleAuction(auctionId);
            if(!initRedis.getSuccess()){
                return initRedis;
            }
            return largeScreenShowCar(auctionId,auctionCarId);
        }
    }

    //递归获取等待开拍、拍卖中的车辆
    private ServiceResult<Map<String,Object>> recurrenceGetAvailabilityCar(Map<String,Object> resultMap){
        if(resultMap!=null&&resultMap.values().size()>0){
            if("0".equals(resultMap.get("status"))||"1".equals(resultMap.get("status"))){
                return new ServiceResult<>(true,resultMap);
            }else{
                //如果当前车辆，非等待开拍、拍卖中的车辆，获取下一辆车。
                Long auctionId  =(Long) resultMap.get("auctionId");
                Long nextAuctionCarId  =(Long) resultMap.get("nextAuctionCarId");
                String auctionCarStr = (String)redisTemplate.opsForHash().get(String.valueOf(auctionId),String.valueOf(nextAuctionCarId));
                resultMap =JSONObject.parseObject(auctionCarStr,Map.class);
                if(resultMap!=null&&resultMap.values().size()>0){
                    return recurrenceGetAvailabilityCar(resultMap);
                }else{
                    return new ServiceResult<>(false,"获取不到该场次可竞拍车辆信息","101");
                }
            }
        }else{
           return new ServiceResult<>(false,"获取不到该场次可竞拍车辆信息","101");
        }
    }

    //redis 未初始化数据则将数据进行初始化
    private ServiceResult<Map<String,Object>> initRedisLocaleAuction(Long auctionId){
        List<CarLocaleAuctionCar> carLocaleAuctionCars = model.largeScreenShowCar(auctionId);
        if(carLocaleAuctionCars==null||carLocaleAuctionCars.size()<=0){
            return new ServiceResult<>(false,"该场次没有竞拍车辆","101");
        }
        //判断代办费是否配置
        ServiceResult<Map<String,Object>> agentResult =getAgentPriceAndServicePrice(auctionId);
        if(!agentResult.getSuccess()){
            return agentResult;
        }
        Map<String,Object> agentMap=agentResult.getResult();
        int count=0;
        for(int i=0;i<carLocaleAuctionCars.size();i++){
            CarLocaleAuctionCar carLocaleAuctionCar =carLocaleAuctionCars.get(i);
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("auctionCarId",carLocaleAuctionCar.getId());
            resultMap.put("auctionId",carLocaleAuctionCar.getAuctionId());
            resultMap.put("carId",carLocaleAuctionCar.getCarId());
            resultMap.put("auctionCode",carLocaleAuctionCar.getAuctionCode());
            resultMap.put("carAutoNo",carLocaleAuctionCar.getCarAutoNo());
            resultMap.put("title",carLocaleAuctionCar.getTitle());
            resultMap.put("autoInfoName",carLocaleAuctionCar.getAutoInfoName());
            resultMap.put("sort",carLocaleAuctionCar.getSort());
            resultMap.put("beginRegisterDate",carLocaleAuctionCar.getBeginRegisterDate());
            resultMap.put("licenseNumber",carLocaleAuctionCar.getLicenseNumber());
            resultMap.put("mileage",carLocaleAuctionCar.getMileage());
            resultMap.put("useNature",carLocaleAuctionCar.getUseNature());
            resultMap.put("ifAgent",carLocaleAuctionCar.getIfAgent());
            resultMap.put("status",carLocaleAuctionCar.getAuctionStatus());
            resultMap.put("startingPrice",carLocaleAuctionCar.getStartingPrice());
            resultMap.putAll(agentMap);
            if(carLocaleAuctionCar.getIfAgent()!=null&&"2".equals(carLocaleAuctionCar.getIfAgent())){
                resultMap.put("agentPrice",new BigDecimal(0));
            }
            resultMap.put("carAutoPhoto",carLocaleAuctionCar.getCarAutoPhoto());
            //获取下一辆车信息,判断是否有下一辆车
            if(carLocaleAuctionCars.size()==(i+1)){
                //无下一辆车
                resultMap.put("nextAuctionCarId",null);
                resultMap.put("nextAutoInfoName",null);
                resultMap.put("nextCarPhoto",null);
            }else{
                CarLocaleAuctionCar nextCarLocaleAuctionCar =carLocaleAuctionCars.get(i+1);
                resultMap.put("nextAuctionCarId",nextCarLocaleAuctionCar.getId());
                resultMap.put("nextAutoInfoName",nextCarLocaleAuctionCar.getAutoInfoName());
                resultMap.put("nextCarPhoto",nextCarLocaleAuctionCar.getCarAutoPhoto());
            }
            String resultMapStr =JSONObject.toJSONString(resultMap);
            if(i==0){
                redisTemplate.opsForHash().put(String.valueOf(carLocaleAuctionCar.getAuctionId()),"0",resultMapStr);
            }else{
                redisTemplate.opsForHash().put(String.valueOf(carLocaleAuctionCar.getAuctionId()),String.valueOf(carLocaleAuctionCar.getId()),resultMapStr);
            }
        }
        return new ServiceResult<>(true,"初始化成功！","100");
    }

    //获取代办费及服务费
    private ServiceResult<Map<String,Object>>  getAgentPriceAndServicePrice(Long auctionId){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        //代办费
        BigDecimal agentPrice =new BigDecimal(0);
        String servicePrice ="";
        CarLocaleAuction carLocaleAuction =carLocaleAuctionModel.selectById(auctionId);
        if(carLocaleAuction!=null){
            CarRegionSetting carRegionSetting = carRegionSettingModel.selectByRegionId(carLocaleAuction.getCityId());
            if(carRegionSetting!=null){
                agentPrice =carRegionSetting.getAgentFee();
                List<CarRegionServerfeeSetting> serverfeeSettings = serverfeeSettingModel.selectByRegionSettingId(carRegionSetting.getId());
                if(serverfeeSettings!=null&&serverfeeSettings.size()>0){
                    for(CarRegionServerfeeSetting carRegionServerfeeSetting:serverfeeSettings){
                        servicePrice=servicePrice+"<span class=\"mspan\">"+carRegionServerfeeSetting.getServiceFee()+"元</span>";
                        if(StringUtils.isNotBlank(carRegionServerfeeSetting.getShowText())){
                            servicePrice+="<span class=\"small m-r\">("+carRegionServerfeeSetting.getShowText()+")</span>";
                        }
                    }
                }else{
                    return new ServiceResult<>(false,"获取不到服务费","101");
                }
            }else{
                new ServiceResult<>(false,"获取不到该地区的代办费！","101");
            }
        }else{
            new ServiceResult<>(false,"查询不到场次信息","101");
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("agentPrice",agentPrice);
        resultMap.put("servicePrice",servicePrice);
        result.setResult(resultMap);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Autor 付陈林
     * @Date 2018-3-28
     * @About 设置二拍操作
     * */
    @Transactional
    public ServiceResult<Map<String,Object>> setAgainAuction(Long auctionCarId, Long carId,Long userId, BigDecimal startAmount,BigDecimal reserveAmount){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        CarAuto carAuto =carAutoModel.selectByPrimaryKey(carId);
        if(carAuto==null){
            return new ServiceResult<>(false,"获取不到车辆信息","101");
        }
        CarLocaleAuctionCar carLocaleAuctionCar =model.selectById(auctionCarId);
        if(carLocaleAuctionCar==null){
            return new ServiceResult<>(false,"获取不到场次车辆信息","101");
        }
        if(!"3".equals(carLocaleAuctionCar.getAuctionStatus())){
            return new ServiceResult<>(false,"只有流拍的车辆才可以进行二拍","101");
        }
        //step 1.清空redis中的数据
        redisTemplate.delete(String.valueOf(carLocaleAuctionCar.getAuctionId()));

        //step 2.设置当前场次车辆
        CarLocaleAuctionCar updateAuctionCar =new CarLocaleAuctionCar();
        updateAuctionCar.setId(auctionCarId);
        updateAuctionCar.setAuctionStatus("4");  //已设置二拍
        model.updateByIdSelective(updateAuctionCar);

        //step 3.设置车辆竞拍信息 获取到目前的竞拍信息，将其设置为流拍。
        CarAutoAuction carAutoAuction =carAutoAuctionModel.selectByPrimaryKey(carAuto.getAutoAuctionId());
        CarAutoAuction updateAutoAuction =new CarAutoAuction();
        updateAutoAuction.setId(carAutoAuction.getId());
        updateAutoAuction.setStatus("3");  //流拍
        updateAutoAuction.setModifyPerson(userId);
        updateAutoAuction.setModifyTime(new Date());
        carAutoAuctionModel.updateByPrimaryKeySelective(updateAutoAuction);

        //step 3 产出一条新车辆竞拍信息
        carAutoAuction.setId(idWorker.nextId());
        carAutoAuction.setStatus("2");
        carAutoAuction.setStartingPrice(startAmount);
        carAutoAuction.setReservePrice(reserveAmount);
        carAutoAuction.setCreateTime(new Date());
        carAutoAuction.setCreatePerson(userId);
        carAutoAuctionModel.insert(carAutoAuction);

        //step 4产生一条新的场次车辆信息。
        //新增一个场次车辆
        CarLocaleAuctionCar insertAuctionCar =new CarLocaleAuctionCar();
        insertAuctionCar.setId(idWorker.nextId());
        insertAuctionCar.setAuctionId(carLocaleAuctionCar.getAuctionId());
        insertAuctionCar.setAuctionStatus("1");
        insertAuctionCar.setCarId(carId);
        insertAuctionCar.setAuctionCode(carLocaleAuctionCar.getAuctionCode());
        insertAuctionCar.setAutoAuctionId(carAutoAuction.getId());
        //获取本场最大序号
        int maxSort = model.getMaxSortForActionCar(carLocaleAuctionCar.getAuctionId());
        //获取本场最近正在竞拍的车的序号
        int minSort = model.getMinSortForActionCar(carLocaleAuctionCar.getAuctionId());
        if (maxSort==carLocaleAuctionCar.getSort() || minSort==0) {
            //如果当前二拍车辆就是最后一辆车 或者 没有正在竞拍中的车辆（即车辆全部拍卖完成并未关闭场次） 则直接新增一条竞拍，序号+1
            int newSort = carLocaleAuctionCar.getSort();
            if (minSort==0){
                newSort = maxSort;
            }
            insertAuctionCar.setSort(newSort + 1);
        }else if (maxSort>carLocaleAuctionCar.getSort()&&(carLocaleAuctionCar.getSort()+1)==maxSort) {
            //当后面仅剩一个车时，直接将当前车辆拍到后面即可
            insertAuctionCar.setSort(carLocaleAuctionCar.getSort()+2);
        }else {
            if (minSort==0){
                minSort=maxSort;
            }
            //将二拍车辆放到后面第三位后，需要将剩余其他车辆循序号顺延
            Map map = new HashMap();
            map.put("auctionId",carLocaleAuctionCar.getAuctionId());
            map.put("sort",minSort+1);
            model.updateSortAuctionCar(map);
            insertAuctionCar.setSort(minSort+2);
        }
        insertAuctionCar.setCreateTime(new Date());
        insertAuctionCar.setCreatePerson(userId);
        model.insert(insertAuctionCar);

        // step 5.更新车辆信息,状态竞拍信息
        CarAuto updateCarAuto =new CarAuto();
        updateCarAuto.setId(carId);
        updateCarAuto.setStatus("7");
        updateCarAuto.setAutoAuctionId(carAutoAuction.getId());
        updateCarAuto.setUpdateUser(userId);
        updateCarAuto.setUpdateTime(new Date());
        carAutoModel.updateByPrimaryKeySelective(updateCarAuto);

        // step 6 添加车辆操作日志
        CarLocaleAuction carLocaleAuction =carLocaleAuctionModel.selectById(carLocaleAuctionCar.getAuctionId());
        CarAutoLog carAutoLog = new CarAutoLog();
        carAutoLog.setId(idWorker.nextId());
        carAutoLog.setAutoId(carLocaleAuctionCar.getCarId());
        carAutoLog.setUserType("2");
        carAutoLog.setStatus("6");
        carAutoLog.setStatusName("等待开拍");
        carAutoLog.setTime(new Date());
        carAutoLog.setMsg("将该车辆增加到"+carLocaleAuction.getTitle()+"场次中");
        carAutoLog.setUserId(userId);
        if(userId!=null){
            CarManagerUser carManagerUser=carManagerUserModel.selectByPrimaryKey(userId);
            if(carManagerUser!=null){
                carAutoLog.setUserMobile(carManagerUser.getUserPhone());
                carAutoLog.setUserName(carManagerUser.getUserName());
            }
        }
        carAutoLogModel.insert(carAutoLog);
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("count",1);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }
    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆
     * */
    public ServiceResult<List<CarLocaleAuctionCar>> hasAuctionCarList(Map<String,Object> map){
        ServiceResult<List<CarLocaleAuctionCar>> result =new ServiceResult<>();
        result.setSuccess(true);
        List<CarLocaleAuctionCar> carLocaleAuctionCars =model.hasAuctionCarList(map);
        for(CarLocaleAuctionCar carLocaleAuctionCar:carLocaleAuctionCars){
            Map<String,Object> paramMap =new HashMap<>();
            paramMap.put("carId",carLocaleAuctionCar.getCarId());
            paramMap.put("auctionId",carLocaleAuctionCar.getAuctionId());
            CarOrder carOrder = carOrderModel.selectOrderByCarId(paramMap);
            if(carOrder!=null&&carOrder.getCustomerId()!=null){
            WtAppUser wtAppUser =appUserModel.findById(carOrder.getCustomerId());
            carLocaleAuctionCar.setUserNum(wtAppUser.getUserNum());
            carLocaleAuctionCar.setAuctionPlateNum(wtAppUser.getAuctionPlateNum());
        }
    }
        result.setResult(carLocaleAuctionCars);
        return result;
    }

    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆梳理
     * */
    public ServiceResult<Integer> hasAuctionCarCount(Map<String,Object> map){
        ServiceResult<Integer> result =new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(model.hasAuctionCarCount(map));
        return result;
    }

    //      导入起拍价时，批量存储加价幅度
    public Integer batchUpdateLocaleAuctionById(List<CarLocaleAuctionCar> localeAuctionList){
        return model.batchUpdateLocaleAuctionById(localeAuctionList);
    }

    /**
     * 查询当前场次正在竞拍的车辆
     * @param localeAuctionId
     * @return
     */
    @Override
    public CarLocaleAuctionCar selectCurrentAuctionCar(Long localeAuctionId){
        return model.selectCurrentAuctionCar(localeAuctionId);
    }
}
