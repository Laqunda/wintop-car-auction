package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.Car;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 车辆信息接口类
 */
@RestController
@RequestMapping("/service/carAuto")
public class CarAutoApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAutoApi.class);
    @Resource
    private ICarAutoService carAutoService;
    @Autowired
    private ICarAutoInfoDetailService carAutoInfoDetailService;
    @Autowired
    private ICarCustomerViewedAutoService viewedAutoService;
    @Autowired
    private ICarAutoPhotoService photoService;
    @Autowired
    private ICarAutoAuctionService auctionService;
    @Autowired
    private ICarAutoProceduresService proceduresService;
    @Autowired
    private ICarManagerUserService managerUserService;
    @Autowired
    private ICarManagerRoleService roleService;
    @Autowired
    private ICarAutoLogService iCarAutoLogService;
    @Autowired
    private TblAuctionLogService tblAuctionLogService;

    private IdWorker idWorker = new IdWorker(10);

    private static Map<String, List<String>> statusList = convertStatusMap();

    private static Map<String, List<String>> convertStatusMap() {
        return new HashMap<String, List<String>>(){{
             put("1", Arrays.asList("1"));
             put("2", Arrays.asList("2"));
             put("3", Arrays.asList("3"));
             put("4", Arrays.asList("6"));
             put("5", Arrays.asList("7"));
             put("6", Arrays.asList("8","9","10","11","12","13","14","15","16","17"));
             put("7", Arrays.asList("19"));
         }};
    }

    /***
     * 车辆信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectByCarId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectByCarId(@RequestBody JSONObject obj) {

        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {

            Long carId = obj.getLong("carId");
            Long customerId = null;
            if(obj.get("customerId")!=null){
                customerId = obj.getLong("customerId");

            }
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("carId",carId);
            paramMap.put("customerId",customerId);

            if (obj.get("auctionId")!=null && StringUtils.isNotEmpty(obj.getString("auctionId"))){
                paramMap.put("auctionId",obj.get("auctionId"));
            }
            CarAuto carAuto = carAutoService.selectByCarId(paramMap);
            Map<String,Object> carIdMap = new HashMap<>();
            carIdMap.put("carId",carId);
            List<CarAutoLog> carAutoLogs = iCarAutoLogService.selectCarLogByCarId(carIdMap);
            List<TblAuctionLog> tblAuctionLogs = tblAuctionLogService.selectByExample(carIdMap);
            carAuto.setCarAutoLog(carAutoLogs);
            carAuto.setTblAuctionLog(tblAuctionLogs);
            if(carAuto == null){
                result.setError(ResultCode.NO_OBJECT.strValue(), ResultCode.NO_OBJECT.getRemark());
                return result;
            }
            if(customerId!=null){
                //***增加浏览记录
                CarCustomerViewedAuto viewedAuto = viewedAutoService.queryViewRecord(carId,customerId);
                if(viewedAuto!=null){
                    viewedAutoService.updateViewTime(carId,customerId);
                }else{
                    viewedAuto = new CarCustomerViewedAuto();
                    viewedAuto.setId(idWorker.nextId());
                    viewedAuto.setAutoId(carId);
                    viewedAuto.setUserId(customerId);
                    viewedAuto.setCreateTime(new Date());
                    viewedAutoService.insert(viewedAuto);
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("ifAgent",carAuto.getIfAgent());
            map.put("auctionType",carAuto.getAuctionType());
            map.put("ifNew",carAuto.getIfNew());
            map.put("vehicleAttributionCity",carAuto.getVehicleAttributionCity());
            map.put("autoInfoName",carAuto.getAutoInfoName());
            map.put("beginRegisterDate",carAuto.getBeginRegisterDate());
            map.put("mileage",carAuto.getMileage());
            map.put("reportColligationRanks",carAuto.getReportColligationRanks());
            map.put("reportServicingRanks",carAuto.getReportServicingRanks());
            map.put("startingPrice",carAuto.getStartingPrice());
            map.put("servicePrice",carAuto.getServicePrice());
            //根据现场线上获取开拍时间
            map.put("auctionStartTime","1".equals(carAuto.getAuctionType())?carAuto.getAuctionStartTime():carAuto.getStartTime());
            map.put("auctionEndTime",carAuto.getAuctionEndTime());
            map.put("carStoreName",carAuto.getCarStoreName());
            map.put("storeLatitude",carAuto.getStoreLatitude());//纬度
            map.put("storeLongitude",carAuto.getStoreLongitude());//经度
            map.put("storeId",carAuto.getStoreId());
            map.put("linkManName",carAuto.getLinkManName());
            map.put("linkManMobile",carAuto.getLinkManMobile());
            map.put("remark",carAuto.getRemark());
            map.put("moveToWhere",carAuto.getMoveToWhere());
            map.put("views",carAuto.getViews());
            map.put("myBidPrice",carAuto.getMyBidPrice());
            map.put("environment",carAuto.getEnvironment());
            map.put("carAutoNo",carAuto.getCarAutoNo());
            map.put("auctionCode",carAuto.getAuctionCode());
            map.put("isFollow",carAuto.getIsFollow());
            map.put("tipsMessage",carAuto.getTipsMessage());
            map.put("sellerBear",carAuto.getSellerBear());
            map.put("buyerBear",carAuto.getBuyerBear());
            map.put("carAutoPhotos",carAuto.getCarAutoPhotos());
            map.put("status",carAuto.getStatus());
            map.put("maxPriceUserId",carAuto.getMaxPriceUserId());
            map.put("serviceTel",null);
            map.put("transferFlag",carAuto.getTransferFlag());
            map.put("agentFee", carAuto.getAgentFee());
            map.put("title", carAuto.getTitle());
            //判断当前车辆 状态和 登录人，获取登陆人应该显示的状态
            //1、未登录 + 待开拍 = 即将开始
            //2、未登录 + 正在竞拍 = 正在竞拍
            //3、未登录 + 已结束 = 已结束
            //4、已登录 + 待开拍 = 即将开始
            //5、已登录 + 正在竞拍 = 正在竞拍
            //6、已登录 + 已结束 + 最高出价 + 个人代拍 = 待确认
            //7、已登录 + 已结束 + 非最高出价 + 个人代拍 = 竞拍失败
            //8、已登录 + 已结束 + 已出价 + 未过保留价 + 非个人代拍 = 竞拍失败
            //9、已登录 + 已结束 + 已出价 + 过保留价 + 非最高出价 + 非个人代拍 = 竞拍失败
            //10、已登录 + 已结束 + 已出价 + 过保留价 + 最高出价 + 非个人代拍 = 竞拍成功
            //11、已登录 + 已结束 + 已出价 + 过保留价 + 最高出价 + 个人代拍 = 待确认

            //12 其他状态各自显示
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询车辆信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }


    /***
     * 车辆信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectByCarIdForStore",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectByCarIdForStore(@RequestBody JSONObject obj) {

        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("carId",obj.getLong("carId"));
            CarAuto carAuto = carAutoService.selectByCarId(paramMap);
            if(carAuto == null){
                result.setError(ResultCode.NO_OBJECT.strValue(), ResultCode.NO_OBJECT.getRemark());
                return result;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("ifAgent",carAuto.getIfAgent());
            map.put("auctionType",carAuto.getAuctionType());
            map.put("ifNew",carAuto.getIfNew());
            map.put("vehicleAttributionCity",carAuto.getVehicleAttributionCity());
            map.put("autoInfoName",carAuto.getAutoInfoName());
            map.put("beginRegisterDate",carAuto.getBeginRegisterDate());
            map.put("mileage",carAuto.getMileage());
            map.put("reportColligationRanks",carAuto.getReportColligationRanks());
            map.put("reportServicingRanks",carAuto.getReportServicingRanks());
            map.put("startingPrice",carAuto.getStartingPrice());
            map.put("servicePrice",carAuto.getServicePrice());
//            map.put("auctionStartTime",carAuto.getAuctionStartTime());

            map.put("auctionStartTime","1".equals(carAuto.getAuctionType())?carAuto.getAuctionStartTime():carAuto.getStartTime());


            map.put("auctionEndTime",carAuto.getAuctionEndTime());
            map.put("carStoreName",carAuto.getCarStoreName());
            map.put("storeId",carAuto.getStoreId());
            map.put("linkManName",carAuto.getLinkManName());
            map.put("linkManMobile",carAuto.getLinkManMobile());
            map.put("remark",carAuto.getRemark());
            map.put("moveToWhere",carAuto.getMoveToWhere());
            map.put("views",carAuto.getViews());
            map.put("myBidPrice",carAuto.getMyBidPrice());
            map.put("environment",carAuto.getEnvironment());
            map.put("carAutoNo",carAuto.getCarAutoNo());
//            map.put("auctionCode",carAuto.getAuctionCode());
            map.put("auctionCode",carAuto.getCarAutoNo());
            map.put("isFollow",carAuto.getIsFollow());
            map.put("tipsMessage",carAuto.getTipsMessage());
            map.put("sellerBear",carAuto.getSellerBear());
            map.put("buyerBear",carAuto.getBuyerBear());
            map.put("carAutoPhotos",carAuto.getCarAutoPhotos());
            map.put("status",carAuto.getStatus());
            map.put("maxPriceUserId",carAuto.getMaxPriceUserId());
            map.put("serviceTel","");
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询车辆信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 车辆信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/getAutoInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarAuto> getAutoInfo(@RequestBody JSONObject obj) {

        ServiceResult<CarAuto> result = new ServiceResult<>();
        try {

            Long carId = obj.getLong("autoId");
            CarAuto carAuto = carAutoService.selectByPrimaryKey(carId).getResult();

            result.setResult(carAuto);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询车辆信息失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 车辆基本信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectDetailByCarId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> selectDetailByCarId(@RequestBody JSONObject obj) {
        logger.info("查询车辆基本信息");
        Long carId = obj.getLong("carId");
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarAutoInfoDetail infoDetail = carAutoInfoDetailService.selectDetailByCarId(carId);
            Map<String, Object> map = new HashMap<>();
            map.put("autoBrand",infoDetail.getAutoBrandCn());
            map.put("autoInfoName",infoDetail.getAutoInfoName());
            map.put("vin",infoDetail.getVin());
            map.put("autoSeries",infoDetail.getAutoSeriesCn());
            map.put("autoStyle",infoDetail.getAutoStyleCn());
            map.put("mileage",infoDetail.getMileage());
            map.put("engineCapacity",infoDetail.getEngineCapacity());
            map.put("engineCapacityUnit",infoDetail.getEngineCapacityUnit());
            map.put("environment",infoDetail.getEnvironmentCn());
            map.put("oilSupplySystem",infoDetail.getOilSupplySystemCn());
            map.put("manufactureDate",infoDetail.getManufactureDate());
            map.put("beginRegisterDate",infoDetail.getBeginRegisterDate());
            map.put("vehicleAttributionProvince",infoDetail.getVehicleAttributionProvinceCn());
            map.put("vehicleAttributionCity",infoDetail.getVehicleAttributionCityCn());
            map.put("licenseNumber",infoDetail.getLicenseNumber());
            map.put("carNature",infoDetail.getCarNatureCn());
            map.put("useNature",infoDetail.getUseNatureCn());
            map.put("originalPrice",infoDetail.getOriginalPrice());
            map.put("vehicleDriver",infoDetail.getVehicleDriverCn());
            map.put("intakeMethod",infoDetail.getIntakeMethodCn());
            map.put("transmission",infoDetail.getTransmissionCn());
            map.put("color",infoDetail.getColorCn());
            map.put("colorChanged",infoDetail.getColorChanged());
            map.put("isModification",infoDetail.getIsModification());
            map.put("configs",infoDetail.getConfDetails());
            result.setResult(map);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 获取线上拍的车辆列表
     * @param obj
     * @return
     */
    @PostMapping(value = "/selectOnlineCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectOnlineCarList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            String[] carAges = CarAutoUtils.getCarAgeArray(obj.getString("carAge"));
            paramMap.put("carAge1",carAges[0]);
            paramMap.put("carAge2",carAges[1]);
            String brandId = obj.getString("brandId");
            String grade = obj.getString("grade");
            String status = obj.getString("status");
            String carName = obj.getString("carName");
            String cityId = obj.getString("cityId");
            if (StringUtils.isNotEmpty(obj.getString("cityIds"))) {
                String cityIds = obj.getString("cityIds");
                paramMap.put("regionIds", Splitter.on(",").splitToList(cityIds).stream().map(a -> Longs.tryParse(a)).collect(Collectors.toList()));
            }
            paramMap.put("brandId",brandId);
            paramMap.put("grade",grade);
            paramMap.put("status",status);
            paramMap.put("carName",carName);
            paramMap.put("customerId",obj.getLong("customerId"));
            paramMap.put("storeId",obj.getLong("storeId"));
            paramMap.put("regionId",cityId);
            paramMap.put("clientType","app");
            int count = carAutoService.selectOnlineCarCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuto> carAutos = carAutoService.selectOnlineCarList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAuto carAuto:carAutos){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carAuto.getId());
                map.put("mainPhoto",carAuto.getMainPhoto());
                map.put("reportColligationRanks",carAuto.getReportColligationRanks());
                map.put("reportServicingRanks",carAuto.getReportServicingRanks());
                map.put("address",carAuto.getAddress());
                map.put("auctionStartTime", carAuto.getAuctionStartTime());
                map.put("auctionEndTime",carAuto.getAuctionEndTime());
                map.put("autoInfoName",carAuto.getAutoInfoName());
                map.put("vehicleAttributionCity",carAuto.getVehicleAttributionCity());
                map.put("startingPrice",carAuto.getStartingPrice());
                map.put("mileage",carAuto.getMileage());
                map.put("carAutoNo",carAuto.getCarAutoNo());
                map.put("isFollow",carAuto.getIsFollow());
                map.put("environment",carAuto.getEnvironment());
                map.put("isEntrust",carAuto.getIsEntrust());
                map.put("beginRegisterDate",carAuto.getBeginRegisterDate());
                map.put("status",carAuto.getStatus());
                map.put("auctionId",carAuto.getAutoAuctionId());
                map.put("transferFlag",carAuto.getTransferFlag());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取线上拍的车辆列表",e);
            result.setError("-1","异常");
        }
        return result;
    }


    @PostMapping(value = "/selectCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAuto>> selectCarList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAuto>> result = new ServiceResult<ListEntity<CarAuto>>();
        ListEntity<CarAuto> listEntity = new ListEntity<CarAuto>();
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> paramMap = Maps.newHashMap();
        try {
            if (obj.getString("autoInfoName") != null) {
                paramMap.put("autoInfoName", obj.getString("autoInfoName"));
            }
            if (obj.getString("status") != null) {
                paramMap.put("statusList", statusList.get(obj.getString("status")));
            }
            paramMap.put("auction_type",obj.getString("type"));

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuto> recordList = carAutoService.selectCarList(paramMap);
            Integer count = carAutoService.selectCarCount(paramMap);
            Map<String, Object> map = Maps.newHashMap();
            /*
            for (CarAuto record : recordList) {
                map = Maps.newHashMap();
                map.put("id", record.getId());
                map.put("mainPhoto", record.getMainPhoto());
                map.put("autoInfoName", record.getAutoInfoName());
                map.put("vehicleAttributionCity", record.getVehicleAttributionCity());
                map.put("mileage", record.getMileage());
                map.put("reportColligationRanks", record.getReportColligationRanks());
                map.put("reportServicingRanks", record.getReportServicingRanks());
                map.put("status", record.getStatus());
                map.put("submitTime", record.getSubmitTime());
                map.put("authTime", record.getAuthTime());
                map.put("authMsg", record.getAuthMsg());
                map.put("auctionStartTime", record.getAuctionStarTime());
                map.put("dealTime", record.getDealTime());
                map.put("passInTime", record.getDealTime());
                list.add(map);
            }
            */
            listEntity.setList(recordList);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("线上车辆管理列表查询失败",e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /***
     * 初始化一辆车
     * @param object
     * @return
     */
    @PostMapping(value = "initAuto")
    public ServiceResult<CarAuto> initAuto(@RequestBody JSONObject object){

        CarAuto carAuto = new CarAuto();
        carAuto.setId(idWorker.nextId());
        carAuto.setStatus("1");
        carAuto.setCreateTime(new Date());
        carAuto.setCreateUser(object.getLong("userId"));
        //初始化车辆时 修改时间+人与创建人一样
        carAuto.setUpdateUser(carAuto.getCreateUser());
        carAuto.setUpdateTime(carAuto.getCreateTime());
        if (object.get("storeId")!=null) {
            carAuto.setStoreId(object.getLong("storeId"));
        }
        carAuto.setIfNew(object.getString("ifNew"));
        carAuto.setLogUserMobile(object.getString("userMobile"));
        carAuto.setLogUserName(object.getString("userName"));
        carAuto.setAuctionType(object.getString("auctionType"));
        carAuto.setRegionId(object.getLong("regionId"));
        return carAutoService.insert(carAuto);

    }

    /**
     * 查询零售订单列表
     */
    @ApiOperation(value = "查询零售订单列表")
    @RequestMapping(value = "/retailOrderList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        Map<String, Object> map = Maps.newHashMap();
        try {
            map.put("autoInfoName", obj.getString("query"));
            Integer count = carAutoService.selectRetailForCount(map);

            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());

            List<Map<String, Object>> list = carAutoService.selectRetailForExample(map);
            ListEntity<Map<String, Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);

            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询车辆评估列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 保存草稿
     * @param carAuto
     * @return
     */
    @PostMapping(value = "saveDraft")
    public ServiceResult<CarAuto> saveDraft(@RequestBody CarAuto carAuto){

       carAuto.setUpdateTime(new Date());
       return carAutoService.updateByPrimaryKey(carAuto);
    }

    /***
     * 将草稿车辆改为审核状态
     * @param object
     * @return
     */
    @PostMapping(value = "submitAuth")
    public ServiceResult submitAuth(@RequestBody JSONObject object){
        ServiceResult result = new ServiceResult();
        try {
            CarAuto auto = new CarAuto();
            auto.setId(object.getLong("autoId"));
            auto.setUpdateUser(object.getLong("updateUser"));
            auto.setUpdateTime(new Date());
            auto.setStatus("2");
            auto.setLogMsg("提交审核");
            ServiceResult serviceResult = carAutoService.updateByPrimaryKeySelective(auto);
            if (serviceResult.getSuccess() && serviceResult.getResult()!=null){
                result.setSuccess("0","提交成功");
            }else {
                result.setError("-1","提交失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","提交失败");
        }finally {
            return result;
        }
    }

    /***
     * 删除车辆
     * @param object
     * @return
     */
    @PostMapping(value = "del")
    public ServiceResult del(@RequestBody JSONObject object){
        ServiceResult result = new ServiceResult();
        try {
            CarAuto auto = new CarAuto();
            auto.setId(object.getLong("autoId"));
            auto.setUpdateUser(object.getLong("updateUser"));
            auto.setUpdateTime(new Date());
            auto.setStatus("0");
            auto.setLogMsg("删除车辆");
            ServiceResult serviceResult = carAutoService.updateByPrimaryKeySelective(auto);
            if (serviceResult.getSuccess() && serviceResult.getResult()!=null){
                result.setSuccess("0","提交成功");
            }else {
                result.setError("-1","提交失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","提交失败");
        }finally {
            return result;
        }
    }

    /***
     * 保存检测报告评分结果
     * @param object
     * @return
     */
    @PostMapping(value = "saveReport")
    public ServiceResult saveDetectionReport(@RequestBody JSONObject object){
        ServiceResult result = new ServiceResult();
        try {
            CarAuto auto = new CarAuto();
            auto.setId(object.getLong("autoId"));
            auto.setReportScore(object.getLong("reportScore"));
            auto.setReportColligationRanks(object.getString("reportColligationRanks"));
            auto.setReportServicingRanks(object.getString("reportServicingRanks"));
            auto.setUpdateTime(new Date());
            auto.setUpdateUser(object.getLong("updateUser"));
            result = carAutoService.updateByPrimaryKeySelective(auto);
            if (result!=null && result.getSuccess()){
                result.setSuccess("0","成功");
            }else {
                result.setSuccess("-1","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess("-1","保存失败");
        }finally {
            result.setResult(null);
            return result;
        }
    }

    @ApiOperation(value = "查询某人最新草稿",notes = "查询用户最新草稿车辆")
    @PostMapping(value = "getLastAutoDraft")
    public ServiceResult<CarAuto> getLastAutoDraft(@ApiParam(value = "用户id") @RequestBody Long userId){
        ServiceResult<CarAuto> result = new ServiceResult<>();
        try {
            Map map = new HashMap();
            map.put("createUser",userId);
            map.put("status","1");
            result.setResult(carAutoService.selectMyLastAuto(map));
            result.setSuccess("0","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","失败");
        }finally {
            return result;
        }
    }
        /**
        * 根据参数查询车辆列表
        *@Author:zhangzijuan
        *@date 2018/3/22
        *@param:map
        */
    @ApiOperation(value = "根据参数查询车辆列表",notes = "根据参数查询车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "status",value = "状态id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "getCarAutoListByParam")
    public ServiceResult<ListEntity<CarAuto>> getCarAutoListByParam (@RequestBody Map<String,Object> map){
        ServiceResult<ListEntity<CarAuto>> result=new ServiceResult<>();
        Integer page=(Integer) map.get("page");
        Integer pageSize=(Integer) map.get("limit");
        map.put("startRowNum",(page-1)*pageSize);
        map.put("endRowNum",pageSize);
        /**
         * 数据权限过滤
         */
        Long userId=Long.parseLong(map.get("managerId").toString());
        if(userId!=null){
            List<Long> storeIds = managerUserService.queryStoreScope(userId);
            map.put("storeIds",storeIds);
        }
        ListEntity<CarAuto> carAutoListEntity=new ListEntity<>();
        carAutoListEntity.setList(carAutoService.getAllCarAutoList(map).getResult());
        carAutoListEntity.setCount(carAutoService.getAllCarAutoCount(map).getResult());
        result.setResult(carAutoListEntity);
        return result;
    }

    /**
     * 根据参数查询车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询车辆列表",notes = "根据参数查询车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "status",value = "状态id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "getCarAutoAllListByParam")
    public ServiceResult<Map<String,Object>> getCarAutoAllListByParam (@RequestBody Map<String,Object> map){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();

        /**
         * 数据权限过滤
         */
        if (MapUtils.isNotEmpty(map) && map.get("managerId") != null){
            Long userId=Long.parseLong(map.get("managerId").toString());
            if(userId!=null){
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                map.put("storeIds",storeIds);
            }
        }
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("list",carAutoService.getAllCarAutoList(map).getResult()) ;
        result.setResult(resultMap);
        result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        return result;
    }
    /**
     *审核撤回审批的车辆
     * @Author zhangzijiuan
     * @return
     */
    @ApiOperation(value = "审核撤回审批的车辆",notes = "审核撤回审批的车辆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "status",value = "审批状态 1 通过 2 不通过",required = true,dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "审批留言",required = false,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "revokeApprove")
    public ServiceResult<Map<String,Object>> revokeApprove(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=carAutoService.revokeApprove(object);
            if(i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     *审批发拍的车辆
     * @Author zhangzijiuan
     * @return
     */
    @ApiOperation(value = "审批发拍的车辆",notes = "审批发拍的车辆，线上拍需要选择可见范围及")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "status",value = "审批状态 1 通过 2 不通过",required = true,dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "审批留言",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "auctionStartTime",value = "开拍时间",required = false,dataType = "string"),
            @ApiImplicitParam(name = "openLimit",value = "开放范围id拼接",required = false,dataType = "string"),
            @ApiImplicitParam(name = "openLimitCn",value = "开放范围，空则全部开放",required = false,dataType = "string")
    })
    @PostMapping(value = "approveCarAuto")
    public ServiceResult<Map<String,Object>> approveCarAuto(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=carAutoService.approveCarAuto(object);
            if(i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else if(i==-1) {
                result.setError(ResultCode.NO_AUCTION_START_TIME.strValue(),ResultCode.NO_AUCTION_START_TIME.getRemark());
            }else if (i==-2){
                result.setError(ResultCode.NO_REGION_AUCTION_SETTING.strValue(),ResultCode.NO_REGION_AUCTION_SETTING.getRemark());
            }else if(i==-3){
                result.setError(ResultCode.PARAM_ERROR.strValue(),ResultCode.PARAM_ERROR.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    @ApiOperation(value = "查询车辆的发布状态")
    @PostMapping(value = "getAutoPublishStatus")
    public ServiceResult<Map<String,Object>> getAutoPublishStatus(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map map = new HashMap();
        map.put("baseInfoStatus","0");
        map.put("photoStatus","0");
        map.put("detectionStatus","0");
        map.put("auctionStatus","0");
        map.put("status",null);
        try {

            boolean baseInfo = true;

            Long roleTypeId = object.getLong("roleTypeId");

            Long autoId = object.getLong("autoId");
            //查询车辆竞拍信息
            CarAutoAuction autoAuction = auctionService.selectByAutoId(autoId);
            if (autoAuction!=null
                    && autoAuction.getReservePrice()!=null
                    && autoAuction.getStoreId()!=null
                    && autoAuction.getStartingPrice()!=null
                    && StringUtils.isNotEmpty(autoAuction.getIfAgent())){
                    map.put("auctionStatus","1");
            }

            //查询车辆基本信息+手续信息+配置信息
            CarAutoInfoDetail infoDetail = carAutoInfoDetailService.selectDetailByCarId(autoId);
            CarAutoProcedures procedures = proceduresService.getAutoProceduresByCarId(autoId).getResult();
            if (infoDetail==null || StringUtils.isEmpty(infoDetail.getVin())
                    || StringUtils.isEmpty(infoDetail.getAutoBrand())
                    || StringUtils.isEmpty(infoDetail.getAutoStyle())
                    || StringUtils.isEmpty(infoDetail.getAutoSeries())
                    || StringUtils.isEmpty(infoDetail.getEngineCapacity())
                    || StringUtils.isEmpty(infoDetail.getEnvironment())
                    || StringUtils.isEmpty(infoDetail.getOilSupplySystem())
                    || StringUtils.isEmpty(infoDetail.getTransmission())
                    || StringUtils.isEmpty(infoDetail.getVehicleDriver())
                    || infoDetail.getMileage()==null
                    || StringUtils.isEmpty(infoDetail.getColor())
                    || StringUtils.isEmpty(infoDetail.getColorChanged())
                    || infoDetail.getManufactureDate()==null
                    || infoDetail.getBeginRegisterDate()==null
                    || StringUtils.isEmpty(infoDetail.getVehicleAttributionProvince())
                    || StringUtils.isEmpty(infoDetail.getVehicleAttributionCity())
                    || StringUtils.isEmpty(infoDetail.getLicenseNumber())
                    || StringUtils.isEmpty(infoDetail.getCarNature())
                    || StringUtils.isEmpty(infoDetail.getUseNature())
                    || StringUtils.isEmpty(infoDetail.getIsModification())
                    || infoDetail.getOriginalPrice()==null
                    || StringUtils.isEmpty(infoDetail.getIntakeMethod())
                    || StringUtils.isEmpty(infoDetail.getCarShape())){
                baseInfo = false;
            }else {
                //二手车--手续信息不完整[中心和经销店需要区分]
                if ("1".equals(infoDetail.getIfNew())
                        &&(procedures==null
                            || procedures.getPurchaseTax()==null
                            || StringUtils.isEmpty(procedures.getDrivingLicense())
                            || StringUtils.isEmpty(procedures.getRegistrationCertificate())
                            || procedures.getYearInsurance()==null
                            || procedures.getCompulsoryInsurance()==null
                            || procedures.getCarKeys()==null
                            || StringUtils.isEmpty(procedures.getUnIllegal())
                            || procedures.getTransferNumber()==null
                            || StringUtils.isEmpty(procedures.getTicketOfTransfer()))){
                    baseInfo = false;

                }else if ("1".equals(infoDetail.getIfNew())
                        && roleTypeId==3
                        && (
                                procedures==null
                                || (procedures.getTransferNumber()==0 && StringUtils.isEmpty(procedures.getNewCarInvoice()))
                                || StringUtils.isEmpty(procedures.getCostPrice()))
                        ){
                    //如果是经销店发车，需要比中心多判断两个字段，新车发票、手续补办费用必填
                    baseInfo = false;
                }
            }
            if (baseInfo){
                map.put("baseInfoStatus","1");
            }

            //查询车辆图片是否完成
            List<CarAutoPhoto> photoList = photoService.selectByAutoId(autoId);
            if (autoAuction!=null
                    && "1".equals(autoAuction.getAuctionType())
                    && photoList!=null && photoList.size()>=12){
                int photoSize = photoList.size();
                for (CarAutoPhoto carAutoPhoto:photoList){
                    if (carAutoPhoto.getSort()>12){
                        photoSize-=1;
                    }
                }
                if (photoSize>=12) {
                    map.put("photoStatus", "1");
                }
            }else if (autoAuction!=null
                    && "2".equals(autoAuction.getAuctionType())
                    && photoList!=null && photoList.size()>=3){
                int photoSize = photoList.size();
                for (CarAutoPhoto carAutoPhoto:photoList){
                    if (carAutoPhoto.getSort()>12){
                        photoSize-=1;
                    }
                }
                if (photoSize>=3) {
                    map.put("photoStatus", "1");
                }
            }

            //检测信息
            if ("2".equals(infoDetail.getIfNew())
                    || (autoAuction!=null && "2".equals(autoAuction.getAuctionType()))
                    || (infoDetail.getReportServicingRanks()!=null && infoDetail.getReportColligationRanks()!=null)){
                map.put("detectionStatus","1");
            }
            //封装车辆状态
            map.put("status",infoDetail.getStatus());
            result.setResult(map);
            result.setSuccess("0","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","查询失败");
        }finally {
            return result;
        }
    }



    /**
     *申请二拍
     * @Author zhangzijiuan
     * @return
     */
    @ApiOperation(value = "申请二拍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "startAmount",value = "起拍价",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "reserveAmount",value = "保留价",required = false,dataType = "string"),
    })
    @PostMapping(value = "setAgainAuction")
    public ServiceResult<Map<String,Object>> setAgainAuction(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            CarAuto auto = carAutoService.selectByPrimaryKey(object.getLong("carId")).getResult();
            //**,2只能操作自己的数据
            Long userId=object.getLong("userId");
            CarManagerRole managerRole = roleService.selectByUserId(userId);
            if("2".equals(managerRole.getWriteType())){
                if(userId.compareTo(auto.getCreateUser())!=0){
                    result.setError(ResultCode.NO_ALLOW_UPDATE.strValue(),ResultCode.NO_ALLOW_UPDATE.getRemark());
                    return result;
                }
            }
            Integer i=carAutoService.setAgainAuction(object,auto);
            if(i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     *根据车辆id查询起拍价和保留价
     * @Author zhangzijiuan
     * @return
     */
    @ApiOperation(value = "根据车辆id查询起拍价和保留价")
    @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long")
    @PostMapping(value = "selectCarInfoById")
    public ServiceResult<Map<String,Object>> selectCarInfoById(@RequestBody JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            CarAutoAuction autoAuction=carAutoService.selectCarInfoById(object);
            if(autoAuction!=null){
                Map<String,Object> map=new HashMap<>();
                map.put("carId",autoAuction.getAutoId());
                map.put("startAmount",autoAuction.getStartingPrice());
                map.put("reserveAmount",autoAuction.getReservePrice());
                map.put("auctionType",autoAuction.getAuctionType());
                result.setResult(map);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 根据条件查询采购申请
     */
    @ApiOperation(value = "根据条件查询车辆申请")
    @RequestMapping(value = "/selectListByType",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>>selectListByType(@RequestBody JSONObject object) {

     ServiceResult<ListEntity<Map<String,Object>>>result = new ServiceResult<>();
        try {
            Long userId = object.getLong("userId");
            String type = object.getString("type");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("userId",userId);
            PageEntity pageEntity= CarAutoUtils.getPageParam(object);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<Map<String,Object>> list = new ArrayList<>();
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            if(type.equals("1")){
                int count = iCarAutoLogService.selectCountWaitByUserId(userId);
                paramMap.put("count",count);
                listEntity.setCount(count);
                List<CarAutoLog> carAutoLogs = iCarAutoLogService.selectWaitOrderList(paramMap);
                for (CarAutoLog carAtuoLog:carAutoLogs){
                    Map<String,Object> map = new HashMap<>();
                    map.put("mainPhoto",carAtuoLog.getMainPhoto());
                    map.put("autoInfoName",carAtuoLog.getAutoInfoName());
                    map.put("time",carAtuoLog.getTime());
                    map.put("publishUserName",carAtuoLog.getPublishUserName());
                    map.put("id",carAtuoLog.getId());
                    map.put("status",carAtuoLog.getStatus());
                    list.add(map);
                    listEntity.setList(list);
                }
            }else if (type.equals("2")){
                int count = iCarAutoLogService.selectCountEndByUserId(userId);
                paramMap.put("count",count);
                listEntity.setCount(count);
                List<CarAutoLog> carAutoLogs = iCarAutoLogService.selectEndOrderList(paramMap);
                for (CarAutoLog carAtuoLog:carAutoLogs){
                    Map<String,Object> map = new HashMap<>();
                    map.put("mainPhoto",carAtuoLog.getMainPhoto());
                    map.put("autoInfoName",carAtuoLog.getAutoInfoName());
                    map.put("time",carAtuoLog.getTime());
                    map.put("publishUserName",carAtuoLog.getPublishUserName());
                    map.put("id",carAtuoLog.getId());
                    map.put("status",carAtuoLog.getStatus());
                    list.add(map);
                    listEntity.setList(list);
                }
            }else if (type.equals("3")){
                int count = carAutoService.selectCountById(userId);
                paramMap.put("count",count);
                listEntity.setCount(count);
                List<CarAuto> carAutos = carAutoService.selectUserOrderList(paramMap);
                for (CarAuto carAuto:carAutos){
                    Map<String,Object> map = new HashMap<>();
                    map.put("mainPhoto",carAuto.getMainPhoto());
                    map.put("autoInfoName",carAuto.getAutoInfoName());
                    map.put("time",carAuto.getTime());
                    map.put("publishUserName",carAuto.getPublishUserName());
                    map.put("id",carAuto.getId());
                    map.put("status",carAuto.getStatus());
                    list.add(map);
                    listEntity.setList(list);
                }
            }
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        } catch (Exception e) {
            logger.info("根据条件查询车辆", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }
}
