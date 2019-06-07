package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 付陈林.
 * @Description: 现场拍--竞拍场次--service端公共接口
 * @Date: 18:32 on 2018/3/6.
 * @Modified by:
 */
@RestController
@Api(tags = "现场拍--竞拍场次相关接口",value = "提供服务端接口")
@RequestMapping("/service/carLocaleAuction")
public class CarLocaleAuctionApi {
    private static final Logger logger = LoggerFactory.getLogger(CarLocaleAuctionApi.class);
    private IdWorker idWorker = new IdWorker(10);
    @Autowired
    private ICarLocaleAuctionService carLocaleAuctionService;
    @Autowired
    private ICarAutoService carAutoService;
    @Autowired
    private IWtAppUserService appUserService;
    @Autowired
    private ICarLocaleAuctionCarService carLocaleAuctionCarService;

    @Autowired
    private ICarAuctionBidRecordService carAuctionBidRecordService;

    /***
     * 获取场次列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAuctionList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取场次列表",notes = "根据查询条件获取场次列表")
    public ServiceResult<ListEntity<Map<String,Object>>> selectAuctionList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long regionId = obj.getLong("regionId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("regionId",regionId);
            if ("app".equals(obj.get("clientType"))){
                paramMap.put("all","1");
            } else {
                paramMap.put("all", "0");
            }
            if(obj.get("beginTime")!=null){
                paramMap.put("beginTime",obj.getString("beginTime"));
            }
            if(obj.get("endTime")!=null){
                paramMap.put("endTime",obj.getString("endTime"));
            }
            int count = carLocaleAuctionService.selectAuctionCount(paramMap).getResult();
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarLocaleAuction> carAuctions =  carLocaleAuctionService.selectAuctionListForApp(paramMap).getResult();
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarLocaleAuction carAuction:carAuctions){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carAuction.getId());
                map.put("title",carAuction.getTitle());
                map.put("address",carAuction.getAddress());
                map.put("startTime",carAuction.getStartTime());
                map.put("carNum",carAuction.getCarNum());
                map.put("gpsLongitude",carAuction.getGpsLongitude());
                map.put("gpsLatitude",carAuction.getGpsLatitude());
                map.put("poster",carAuction.getPoster());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取场次列表失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

    /***
     * 获取场次汇总列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAuctionTotalList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<AuctionListEntity<Map<String,Object>>>> selectAuctionTotalList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<AuctionListEntity<Map<String,Object>>>> result = new ServiceResult<>();
        try {
            Long regionId = obj.getLong("regionId");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("regionId",regionId);
            if ("app".equals(obj.get("clientType"))){
                paramMap.put("all","1");
            } else {
                paramMap.put("all", "0");
            }
            List<AuctionListEntity<Map<String,Object>>> list = new ArrayList<>();
            for(int i=0;i<3;i++){
                AuctionListEntity<Map<String,Object>> auctionListEntity = new AuctionListEntity<>();
                List<Map<String,Object>> dataList = new ArrayList<>();
                if(i==0){
                    auctionListEntity.setTitle("今日场");
                    Date[] dates = CarAutoUtils.getCurrentDays();
                    paramMap.put("beginTime",dates[0]);
                    paramMap.put("endTime",dates[1]);
                }else if(i==1){
                    auctionListEntity.setTitle("本周场");
                    Date[] dates = CarAutoUtils.getCurrentWeeks();
                    paramMap.put("beginTime",dates[0]);
                    paramMap.put("endTime",dates[2]);
                }else if(i==2){
                    auctionListEntity.setTitle("下周场");
                    Date[] dates = CarAutoUtils.getNextWeeks();
                    paramMap.put("beginTime",dates[0]);
                    paramMap.put("endTime",dates[1]);
                }
                List<CarLocaleAuction> carAuctions =  carLocaleAuctionService.selectAuctionListForApp(paramMap).getResult();
                auctionListEntity.setDesc("共"+carAuctions.size()+"场");
                for(CarLocaleAuction carAuction:carAuctions){
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",carAuction.getId());
                    map.put("title",carAuction.getTitle());
                    map.put("address",carAuction.getAddress());
                    map.put("startTime",carAuction.getStartTime());
                    map.put("carNum",carAuction.getCarNum());
                    map.put("gpsLongitude",carAuction.getGpsLongitude());
                    map.put("gpsLatitude",carAuction.getGpsLatitude());
                    map.put("poster",carAuction.getPoster());
                    dataList.add(map);
                }
                auctionListEntity.setDataList(dataList);
                auctionListEntity.setCount(dataList.size());
                list.add(auctionListEntity);
            }
            ListEntity<AuctionListEntity<Map<String,Object>>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(list.size());
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取场次汇总列表失败",e);
            result.setError("-1","异常");
        }
        return result;
    }
    /***
     * 获取场次汇总列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAuctionTotalList2",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<AuctionListEntity<Map<String,Object>>>> selectAuctionTotalList2(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<AuctionListEntity<Map<String,Object>>>> result = new ServiceResult<>();
        try {

            Map<String,Object> paramMap = new HashMap<>();
            if (StringUtils.isNotEmpty(obj.getString("regionId"))) {
                String regionIds = obj.getString("regionId");
                paramMap.put("regionIds",Splitter.on(",").splitToList(regionIds).stream().map(a-> Longs.tryParse(a)).collect(Collectors.toList()));
            }
            if ("app".equals(obj.get("clientType"))){
                paramMap.put("all","1");
            } else {
                paramMap.put("all", "0");
            }
            List<AuctionListEntity<Map<String,Object>>> list = new ArrayList<>();
            for(int i=0;i<7;i++){
                AuctionListEntity<Map<String,Object>> auctionListEntity = new AuctionListEntity<>();
                List<Map<String,Object>> dataList = new ArrayList<>();
                if(i==0){
                    Date[] dates = CarAutoUtils.getCurrentAfterDays(0);
                    auctionListEntity.setTitle(String.format("今日场(%s%s)",CarAutoUtils.getMonthAndDay(dates[0]),CarAutoUtils.getDayOfWeek(dates[0])));

                }else if(i==1){
                    Date[] dates = CarAutoUtils.getCurrentAfterDays(1);
                    auctionListEntity.setTitle(String.format("明日场(%s%s)",CarAutoUtils.getMonthAndDay(dates[0]),CarAutoUtils.getDayOfWeek(dates[0])));
                    paramMap.put("beginTime",dates[0]);
                    paramMap.put("endTime",dates[1]);
                }else if(i>=2){
                    Date[] dates = CarAutoUtils.getCurrentAfterDays(1);
                    auctionListEntity.setTitle(String.format("%s(%s)",CarAutoUtils.getMonthAndDay(dates[0]),CarAutoUtils.getDayOfWeek(dates[0])));
                    paramMap.put("beginTime",dates[0]);
                    paramMap.put("endTime",dates[1]);
                }
                List<CarLocaleAuction> carAuctions =  carLocaleAuctionService.selectAuctionListForApp(paramMap).getResult();
                auctionListEntity.setDesc("共"+carAuctions.size()+"场");
                for(CarLocaleAuction carAuction:carAuctions){
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",carAuction.getId());
                    map.put("title",carAuction.getTitle());
                    map.put("address",carAuction.getAddress());
                    map.put("startTime",carAuction.getStartTime());
                    map.put("carNum",carAuction.getCarNum());
                    map.put("gpsLongitude",carAuction.getGpsLongitude());
                    map.put("gpsLatitude",carAuction.getGpsLatitude());
                    map.put("poster",carAuction.getPoster());
                    map.put("cityName", carAuction.getCityName());
                    map.put("status", carAuction.getStatus());
                    dataList.add(map);
                }
                auctionListEntity.setDataList(dataList);
                auctionListEntity.setCount(dataList.size());
                list.add(auctionListEntity);
            }
            ListEntity<AuctionListEntity<Map<String,Object>>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(list.size());
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取场次汇总列表失败",e);
            result.setError("-1","异常");
        }
        return result;
    }
    /***
     * 获取场次车辆列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAuctionCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectAuctionCarList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long auctionId = obj.getLong("auctionId");
            Long customerId = obj.getLong("customerId");
            String brandId = obj.getString("brandId");
            String carSort = obj.getString("carSort");
            String carName = obj.getString("carName");
            String grade = obj.getString("grade");
            Map<String,Object> paramMap = new HashMap<>();
            String[] carAges = CarAutoUtils.getCarAgeArray(obj.getString("carAge"));
            paramMap.put("carAge1",carAges[0]);
            paramMap.put("carAge2",carAges[1]);
            paramMap.put("auctionId",auctionId);
            paramMap.put("brandId",brandId);
            paramMap.put("carSort",carSort);
            paramMap.put("customerId",customerId);
            paramMap.put("carName",carName);
            paramMap.put("grade",grade);
            paramMap.put("actionStatusArr","'0','1','2'");
            int count = carAutoService.selectAuctionCarCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuto> carAutos = carAutoService.selectAuctionCarList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAuto carAuto:carAutos){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carAuto.getId());
                map.put("mainPhoto",carAuto.getMainPhoto());
                map.put("address",carAuto.getAddress());
                //map.put("startTime", DateFormatUtils.format(carAuto.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
                map.put("startTime", carAuto.getStartTime());
                map.put("autoInfoName",carAuto.getAutoInfoName());
                map.put("vehicleAttributionCity",carAuto.getVehicleAttributionCity());
                map.put("startingPrice",carAuto.getStartingPrice());
                map.put("mileage",carAuto.getMileage());
                map.put("carAutoNo",carAuto.getAuctionCode());
                map.put("auctionCode",carAuto.getAuctionCode());
                map.put("engineCapacity",carAuto.getEngineCapacity());
                map.put("engineCapacityUnit",carAuto.getEngineCapacityUnit());
                map.put("isFollow",carAuto.getIsFollow());
                map.put("environment",carAuto.getEnvironment());
                map.put("auctionId",carAuto.getAutoAuctionId());
            map.put("beginRegisterDate",carAuto.getBeginRegisterDate());
            map.put("status",carAuto.getStatus());
            list.add(map);
        }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取场次车辆列表失败",e);
            result.setError("-1","异常");
        }
        return result;
    }


    /*********************************用Boss端的接口   Author 付陈林   Date 2018-3-17**********************************/

    /***
     * 获取场次列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/getLocaleAuctionList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取场次列表",notes = "根据查询条件获取场次列表")
    public ServiceResult<ListEntity<Map<String,Object>>> getLocaleAuctionList(@RequestBody JSONObject obj){
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        Map<String,Object> paramMap = new HashMap<>();
        if(obj.getString("status")!=null&&!"".equals(obj.getString("status").trim())){
            paramMap.put("status",obj.getString("status"));
        }
        if(obj.getString("title")!=null&&!"".equals(obj.getString("title").trim())){
            paramMap.put("title",obj.getString("title"));
        }
        if(obj.getString("beginTime")!=null&&!"".equals(obj.getString("beginTime"))){
            paramMap.put("beginTime",obj.getString("beginTime"));
        }
        if(obj.getString("endTime")!=null&&!"".equals(obj.getString("endTime"))){
            paramMap.put("endTime",obj.getString("endTime"));
        }
        int count = carLocaleAuctionService.selectAuctionCount(paramMap).getResult();
        PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
        paramMap.put("startRowNum",pageEntity.getStartRowNum());
        paramMap.put("endRowNum",pageEntity.getEndRowNum());
        List<CarLocaleAuction> carAuctions =  carLocaleAuctionService.getAuctionList(paramMap).getResult();
        List<Map<String,Object>> list = new ArrayList<>();
        for(CarLocaleAuction carAuction:carAuctions){
            Map<String,Object> map = new HashMap<>();
            map.put("id",carAuction.getId());
            map.put("code",carAuction.getCode());
            map.put("title",carAuction.getTitle());
            map.put("address",carAuction.getAddress());
            map.put("seeCarMan",carAuction.getSeeCarMan());
            map.put("seeCarPhone",carAuction.getSeeCarPhone());
            map.put("startTime",carAuction.getStartTime());
            Map selectMap = new HashMap<>();
            selectMap.put("auctionId",carAuction.getId());
            Integer carNum =carLocaleAuctionCarService.selectCarNumByAuction(selectMap);
            map.put("carNum",carNum);
            map.put("status",carAuction.getStatus());
            map.put("createTime",carAuction.getCreateTime());
            map.put("createPersonName",carAuction.getCreatePersonName());
            list.add(map);
        }
        ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
        listEntity.setList(list);
        listEntity.setCount(count);
        result.setResult(listEntity);
        if(list.size()>0){
            result.setSuccess("0","成功");
        }else{
            result.setSuccess("1","查询不到数据！");
        }
        return result;
    }
    /***
     * 设置上拍操作
     * @param obj
     * @return
     */
    @RequestMapping(value = "/setUpAuction",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "设置上拍操作",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> setUpAuction(@RequestBody JSONObject obj){
        if(obj.getLong("id")==null&&obj.getLong("id")==0){
            return new ServiceResult<>(false,"场次id不能为空！","101");
        }
        CarLocaleAuction thisCarLocaleAuction =carLocaleAuctionService.selectById(obj.getLong("id")).getResult();
        if(thisCarLocaleAuction==null||thisCarLocaleAuction.getStatus()==null||!"1".equals(thisCarLocaleAuction.getStatus())){
            return new ServiceResult<>(false,"非待上拍状态的场次，不可上拍","101");
        }
        if(thisCarLocaleAuction.getCarNum()==null||thisCarLocaleAuction.getCarNum()==0){
            return new ServiceResult<>(false,"请先添加车辆!","101");
        }
        ServiceResult<Map<String,Object>> result=new ServiceResult<Map<String,Object>>();
        CarLocaleAuction carLocaleAuction =new CarLocaleAuction();
        carLocaleAuction.setId(obj.getLong("id"));
        carLocaleAuction.setStatus("2");
        result =carLocaleAuctionService.updateByIdSelective(carLocaleAuction);
        return result;
    }

    @RequestMapping(value = "/setDownAuction",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "设置下拍操作",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> setDownAuction(@RequestBody JSONObject obj){
        if(obj.getLong("id")==null&&obj.getLong("id")==0){
            return new ServiceResult<>(false,"场次id不能为空！","101");
        }
        CarLocaleAuction thisCarLocaleAuction =carLocaleAuctionService.selectById(obj.getLong("id")).getResult();
        if(thisCarLocaleAuction==null||thisCarLocaleAuction.getStatus()==null||!"2".equals(thisCarLocaleAuction.getStatus())){
            return new ServiceResult<>(false,"非待上拍状态的场次，不可下拍");
        }
        ServiceResult<Map<String,Object>> result=new ServiceResult<Map<String,Object>>();
        CarLocaleAuction carLocaleAuction =new CarLocaleAuction();
        carLocaleAuction.setId(obj.getLong("id"));
        carLocaleAuction.setStatus("1");
        result =carLocaleAuctionService.updateByIdSelective(carLocaleAuction);
        return result;
    }

    @RequestMapping(value = "/saveLocaleAuction",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存竞拍场次",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId",value = "模板id",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "title",value = "场次主题",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "regionId",value = "可见范围，客户组id",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "cityId",value = "场次所在城市",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "poster",value = "封面图片",required = false,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开拍时间",required = true,paramType = "body",dataType = "object"),
            @ApiImplicitParam(name = "corporateAgent",value = "代办公司",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "address",value = "拍卖地址",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarMan",value = "看车联系人",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarPhone",value = "看车联系电话",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarTime",value = "看车时间",required = true,paramType = "body",dataType = "string")
    })
    public ServiceResult<Map<String,Object>> saveLocaleAuction(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result=new ServiceResult<Map<String,Object>>();
        CarLocaleAuction carLocaleAuction =new CarLocaleAuction();
        carLocaleAuction.setId(idWorker.nextId());
        if(obj.getString("title")!=null){
            carLocaleAuction.setTitle(obj.getString("title"));
        }else{
            result.setError("101","场次主题为空！");
            return result;
        }
        if(obj.getString("stationRealId")!=null){
            carLocaleAuction.setStationRealId(obj.getString("stationRealId"));
        }
        if(obj.getString("regionId")!=null){
            carLocaleAuction.setRegionId(obj.getString("regionId"));
        }
        if(obj.getLong("cityId")!=null){
            carLocaleAuction.setCityId(obj.getLong("cityId"));
        }
        if(obj.getString("poster")!=null){
            carLocaleAuction.setPoster(obj.getString("poster"));
        }
        if(obj.getString("startTime")!=null){
            carLocaleAuction.setStartTime(obj.getDate("startTime"));
        }else{
            result.setError("101","开拍时间不能为空！");
            return result;
        }
        if(obj.getLong("corporateAgent")!=null){
            carLocaleAuction.setCorporateAgent(obj.getLong("corporateAgent"));
        }
        if(obj.getString("address")!=null){
            carLocaleAuction.setAddress(obj.getString("address"));
        }else{
            result.setError("101","拍卖场地不能为空！");
            return result;
        }
        if(obj.getString("seeCarMan")!=null){
            carLocaleAuction.setSeeCarMan(obj.getString("seeCarMan"));
        }else{
            result.setError("101","看车联系人不能为空！");
            return result;
        }
        if(obj.getString("seeCarPhone")!=null){
            carLocaleAuction.setSeeCarPhone(obj.getString("seeCarPhone"));
        }else{
            result.setError("101","看车联系电话不能为空！");
            return result;
        }
        if(obj.getString("seeCarTime")!=null){
            carLocaleAuction.setSeeCarTime(obj.getString("seeCarTime"));
        }else{
            result.setError("101","看车时间不能为空！");
            return result;
        }
        if(obj.getString("createPerson")!=null){
            carLocaleAuction.setCreatePerson(obj.getLong("createPerson"));
        }else{
            result.setError("101","创建人不能为空！");
            return result;
        }
        if (obj.getString("templateId") != null) {
            carLocaleAuction.setTemplateId(obj.getLong("templateId"));
        }else{
            result.setError("101","模板id不能为空！");
            return result;
        }
        carLocaleAuction.setDelFlag("0");
        carLocaleAuction.setStatus("1");
        carLocaleAuction.setCreateTime(new Date());
        result = carLocaleAuctionService.insert(carLocaleAuction);
        return result;
    }

    @RequestMapping(value = "/getLocaleAuctionById",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "更新竞拍场次",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "body",dataType = "string")
    public ServiceResult<Map<String,Object>> getLocaleAuctionById(@RequestBody JSONObject obj){
        if(obj.getLong("id")!=null){
            Long id = (Long)obj.getLong("id");
            if(id!=null&&id!=0){
                return carLocaleAuctionService.getLocaleAuctionById(id);
            }else{
                return new ServiceResult<>(false,"查询场次的ID为空！","101");
            }
        }else{
            return new ServiceResult<>(false,"查询场次的ID为空！","101");
        }
    }

    @RequestMapping(value = "/updateLocaleAuction",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "更新竞拍场次",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "templateId",value = "模板id",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "code",value = "场次编号",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "title",value = "场次主题",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "regionId",value = "可见范围，客户组id",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "cityId",value = "场次所在城市",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "poster",value = "封面图片",required = false,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开拍时间",required = true,paramType = "body",dataType = "object"),
            @ApiImplicitParam(name = "corporateAgent",value = "代办公司",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "address",value = "拍卖地址",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarMan",value = "看车联系人",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarPhone",value = "看车联系电话",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarTime",value = "看车时间",required = true,paramType = "body",dataType = "string")
    })
    public ServiceResult<Map<String,Object>> updateLocaleAuction(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result=new ServiceResult<Map<String,Object>>();
        CarLocaleAuction carLocaleAuction =new CarLocaleAuction();
        if(obj.getLong("id")!=null){
            carLocaleAuction.setId(obj.getLong("id"));
        }else{
            result.setError("101","场次主键为空！");
            return result;
        }
        CarLocaleAuction thisCarLocaleAuction =carLocaleAuctionService.selectById(obj.getLong("id")).getResult();
        if(thisCarLocaleAuction==null||thisCarLocaleAuction.getStatus()==null||!"1".equals(thisCarLocaleAuction.getStatus())){
            return new ServiceResult<>(false,"非待上拍状态的场次，不可编辑");
        }
        if(obj.getInteger("code")!=null){
            carLocaleAuction.setCode(obj.getInteger("code"));
        }
        if(obj.getString("title")!=null){
            carLocaleAuction.setTitle(obj.getString("title"));
        }
        if(obj.getString("title")!=null){
            carLocaleAuction.setTitle(obj.getString("title"));
        }
        if(obj.getString("regionId")!=null){
            carLocaleAuction.setRegionId(obj.getString("regionId"));
        }
        if(obj.getLong("cityId")!=null){
            carLocaleAuction.setCityId(obj.getLong("cityId"));
        }
        if(obj.getString("poster")!=null){
            carLocaleAuction.setPoster(obj.getString("poster"));
        }
        if(obj.getString("startTime")!=null){
            carLocaleAuction.setStartTime(obj.getDate("startTime"));
        }
        if(obj.getLong("corporateAgent")!=null){
            carLocaleAuction.setCorporateAgent(obj.getLong("corporateAgent"));
        }
        if(obj.getString("address")!=null){
            carLocaleAuction.setAddress(obj.getString("address"));
        }
        if(obj.getString("seeCarMan")!=null){
            carLocaleAuction.setSeeCarMan(obj.getString("seeCarMan"));
        }
        if(obj.getString("seeCarPhone")!=null){
            carLocaleAuction.setSeeCarPhone(obj.getString("seeCarPhone"));
        }
        if(obj.getString("seeCarTime")!=null){
            carLocaleAuction.setSeeCarTime(obj.getString("seeCarTime"));
        }
        if(obj.getString("modifyPerson")!=null){
            carLocaleAuction.setModifyPerson(obj.getLong("modifyPerson"));
        }
        if (obj.getString("templateId") != null) {
            carLocaleAuction.setTemplateId(obj.getLong("templateId"));
        }
        carLocaleAuction.setModifyTime(new Date());
        result = carLocaleAuctionService.updateByIdSelective(carLocaleAuction);
        return result;
    }
    @PostMapping(value = "/getLocaleAuctionDetail",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取场次的明细信息",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "query",dataType = "long")
    public ServiceResult<Map<String,Object>> getLocaleAuctionDetail(@RequestBody JSONObject obj){
        if(obj.getLong("id")!=null){
            Long id = obj.getLong("id");
            if(id!=null&&id!=0){
                return carLocaleAuctionService.getLocaleAuctionDetail(id);
            }else{
                return new ServiceResult<>(false,"查询场次的ID为空！","101");
            }
        }else{
            return new ServiceResult<>(false,"查询场次的ID为空！","101");
        }
    }

    /**
     *根据id查询场次详情
     */
    @PostMapping(value = "/getLocaleAuctionDetailById",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取场次的明细信息",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "query",dataType = "long")
    public ServiceResult<CarLocaleAuction> getLocaleAuctionDetailById(@RequestBody JSONObject obj){
        if(obj.getLong("id")!=null){
            Long id = obj.getLong("id");
            if(id!=null&&id!=0){
                return carLocaleAuctionService.selectById(id);
            }else{
                return new ServiceResult<>(false,"查询场次的ID为空！","101");
            }
        }else{
            return new ServiceResult<>(false,"查询场次的ID为空！","101");
        }
    }

    @PostMapping(value = "/getLocaleAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取参加现场竞拍的车辆",notes = "")
    @ApiImplicitParam(name = "searchParam",value = "车辆标题/车辆编号",required = true,paramType = "body",dataType = "string")
    public ServiceResult<List<Map<String,Object>>> getLocaleAuctionCar(@RequestBody JSONObject obj){
        ServiceResult<List<Map<String,Object>>> serviceResult =new ServiceResult<>();
        List<CarAuto>  carAutos =carAutoService.getLocaleAuctionCar(obj.getString("searchParam")).getResult();
        List<Map<String,Object>> resultList =new ArrayList<>();
        for(CarAuto carAuto:carAutos){
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("id",carAuto.getId());
            resultMap.put("carAutoNo",carAuto.getCarAutoNo());
            resultMap.put("autoInfoName",carAuto.getAutoInfoName());
            resultMap.put("startingPrice",carAuto.getStartingPrice());
            resultMap.put("reservePrice",carAuto.getReservePrice());
            resultMap.put("beginRegisterDate",carAuto.getBeginRegisterDate());
            resultMap.put("sourceType",carAuto.getSourceType());
            resultMap.put("licenseNumber",carAuto.getLicenseNumber());
            resultMap.put("storeName",carAuto.getStoreName());
            if(carAuto.getAuctionNum()!=null){
                resultMap.put("auctionNum",carAuto.getAuctionNum());
            }else{
                resultMap.put("auctionNum",0);
            }
            resultMap.put("publishUserName",carAuto.getPublishUserName());
            resultMap.put("auctionType",carAuto.getAuctionType());
            resultList.add(resultMap);
        }
        serviceResult.setSuccess(true);
        serviceResult.setResult(resultList);
        return serviceResult;
    }

    @PostMapping(value = "/saveLocaleAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存参加竞拍的车辆",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "场次主键",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "carIds",value = "车辆编号集合，以逗号隔开",required = true,paramType = "body",dataType = "long")
    })
    public ServiceResult<Map<String,Object>> saveLocaleAuctionCar(@RequestBody JSONObject obj){
        //判读传递的参数是否为空！
        //场次ID判断
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"场次ID为空","101");
        }
        CarLocaleAuction thisCarLocaleAuction =carLocaleAuctionService.selectById(obj.getLong("auctionId")).getResult();
        if(thisCarLocaleAuction==null||thisCarLocaleAuction.getStatus()==null||(!"1".equals(thisCarLocaleAuction.getStatus())&&!"2".equals(thisCarLocaleAuction.getStatus()))){
            return new ServiceResult<>(false,"非待上拍、待开拍状态的场次，不可编辑");
        }
        if(obj.getString("carIds")==null||"".equals(obj.getString("carIds"))){
            return new ServiceResult<>(false,"车辆ID集合为空！","101");
        }
        return carLocaleAuctionCarService.saveLocaleAuctionCar(obj.getLong("auctionId"),obj.getString("carIds"),obj.getLong("userId"));
    }

    /**
     * 查询所有有效场次
     * @return
     */
    @RequestMapping(value = "/selectAllValidAuction",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CommonNameVo>> selectAllValidAuction(){
        ServiceResult<List<CommonNameVo>> result = new ServiceResult<>();
        try {
            List<CommonNameVo> commonNameVos = carLocaleAuctionService.selectAllValidAuction();
            result.setResult(commonNameVos);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取有效场次车辆列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    @PostMapping(value = "/adjustAuctionCarSort",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "调整车辆顺序",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionCarId",value = "车辆关联表id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "sort",value = "顺序",required = true,paramType = "query",dataType = "int")
    })
    public ServiceResult<Map<String,Object>> adjustAuctionCarSort(@RequestBody JSONObject obj){
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"车辆关联Id为空！","101");
        }
        if(obj.getInteger("sort")==null){
            return new ServiceResult<>(false,"调整的顺序值为空！","101");
        }
        CarLocaleAuctionCar carLocaleAuctionCar =new CarLocaleAuctionCar();
        carLocaleAuctionCar.setId(obj.getLong("auctionCarId"));
        carLocaleAuctionCar.setSort(obj.getInteger("sort"));
        //保存调整后的顺序
        ServiceResult<Map<String,Object>> serviceResult = carLocaleAuctionCarService.updateByIdSelective(carLocaleAuctionCar);
        if(!serviceResult.getSuccess()){
            return serviceResult;
        }
        //调整车辆场次编号
        return carLocaleAuctionCarService.updateAuctionCode(obj.getLong("auctionCarId"));
    }

    @PostMapping(value = "/getAuctionCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据场次ID查询该场次的所有车辆",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次ID",required = true,paramType = "query",dataType = "long")
    public ServiceResult<List<Map<String,Object>>> getAuctionCarList(@RequestBody JSONObject obj){
        ServiceResult<List<Map<String,Object>>> result =new ServiceResult<>();
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"场次的ID不为空！","101");
        }
        List<CarLocaleAuctionCar> carLocaleAuctionCars =  carLocaleAuctionCarService.getAuctionCarList(obj.getLong("auctionId")).getResult();
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
            carMap.put("beginRegisterDate",carLocaleAuctionCar.getBeginRegisterDate());
            carMap.put("sourceType",carLocaleAuctionCar.getSourceType());
            carMap.put("auctionNum",carLocaleAuctionCar.getAuctionNum());
            carMap.put("publishUserName",carLocaleAuctionCar.getPublishUserName());
            carMap.put("storeName",carLocaleAuctionCar.getStoreName());
            entriesList.add(carMap);
        }
        result.setResult(entriesList);
        result.setSuccess(true);
        return result;
    }

    @PostMapping(value = "/deleteAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "删除竞拍场次中的车辆",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆关联Id",required = true,paramType = "query",dataType = "long")
    public ServiceResult<Map<String,Object>> deleteAuctionCar(@RequestBody JSONObject obj){
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"竞拍场次车辆关联Id为空！","101");
        }
        CarLocaleAuctionCar carLocaleAuctionCar =carLocaleAuctionCarService.selectById(obj.getLong("auctionCarId"));
        if(carLocaleAuctionCar==null||carLocaleAuctionCar.getAuctionStatus()==null||!"0".equals(carLocaleAuctionCar.getAuctionStatus())){
            return new ServiceResult<>(false,"只有等待开拍状态的车辆才可以删除！","101");
        }
        ServiceResult<Map<String,Object>> serviceResult =  carLocaleAuctionCarService.deleteById(obj.getLong("auctionCarId"),obj.getLong("userId"));
        if(!serviceResult.getSuccess()){
            return serviceResult;
        }
        //调整车辆场次编号，只是待上拍可以调整顺序
        CarLocaleAuction carLocaleAuction =carLocaleAuctionService.selectById((Long)serviceResult.getResult().get("auctionId")).getResult();
        if(carLocaleAuction!=null&&carLocaleAuction.getStatus()!=null&&"1".equals(carLocaleAuction.getStatus())){
            return carLocaleAuctionCarService.updateAuctionCode((Long)serviceResult.getResult().get("auctionId"));
        }else{
            return serviceResult;
        }
    }

    @PostMapping(value = "/getAuctionCarInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取关联车辆信息",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆关联Id",required = true,paramType = "query",dataType = "long")
    public ServiceResult<CarLocaleAuctionCar> getAuctionCarInfo(@RequestBody JSONObject obj){
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"竞拍场次车辆关联Id为空！","101");
        }
        return  carLocaleAuctionCarService.getCarLocaleAuctionCar(obj.getLong("auctionCarId"));
    }

    @PostMapping(value = "/deleteLocaleAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "删除竞拍场次",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = false,paramType = "query",dataType = "string")
    public ServiceResult<Map<String,Object>> deleteLocaleAuction(@RequestBody JSONObject obj){
        if(obj.getLong("id")==null||obj.getLong("id")==0){
            return new ServiceResult<>(false,"竞拍场次Id为空！","101");
        }
        CarLocaleAuction carLocaleAuction =carLocaleAuctionService.selectById(obj.getLong("id")).getResult();
        if(carLocaleAuction==null||carLocaleAuction.getStatus()==null||!"1".equals(carLocaleAuction.getStatus())){
            return new ServiceResult<>(false,"只有待上拍状态的才可以删除");
        }
        return carLocaleAuctionService.deleteById(obj.getLong("id"),obj.getLong("userId"));
    }

    @PostMapping(value = "/saveBiddenInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存出价信息",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "customerId",value = "车商id",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "lastAmount",value = "最高出价",required = true,paramType = "query",dataType = "double")
    })
    public ServiceResult<Map<String,Object>> saveBiddenInfo(@RequestBody JSONObject obj){
        CarAuctionBidRecord carAuctionBidRecord =new CarAuctionBidRecord();
        carAuctionBidRecord.setId(idWorker.nextId());
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"竞拍场次Id为空！","101");
        }
        carAuctionBidRecord.setAuctionId(obj.getLong("auctionId"));
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"竞拍场次车辆Id为空！","101");
        }
        carAuctionBidRecord.setAuctionCarId(obj.getLong("auctionCarId"));
        if(obj.getLong("carId")==null||obj.getLong("carId")==0){
            return new ServiceResult<>(false,"车辆Id为空！","101");
        }
        carAuctionBidRecord.setCarId(obj.getLong("carId"));
        if(obj.getLong("customerId")!=null&&obj.getLong("customerId")!=0){
            carAuctionBidRecord.setCustomerId(obj.getLong("customerId"));
        }
        if(obj.getLong("lastAmount")==null){
            return new ServiceResult<>(false,"最高出价不能为空！","101");
        }
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("auctionCarId", obj.getLong("auctionCarId"));
        paramMap.put("carId", obj.getLong("carId"));
        List<CarAuctionBidRecord> recordList = carAuctionBidRecordService.selectPriceList(paramMap);
        if (CollectionUtils.isNotEmpty(recordList)) {
            List<BigDecimal> priceList = recordList.stream().map(a -> a.getBidFee()).collect(Collectors.toList());
            if (priceList.contains(obj.getBigDecimal("lastAmount"))) {
                return new ServiceResult<>(false,"不可重复出价！","101");
            }
        }
        CarLocaleAuctionCar carLocaleAuctionCar = carLocaleAuctionCarService.selectById(obj.getLong("auctionCarId"));
        if(carLocaleAuctionCar==null||!"1".equals(carLocaleAuctionCar.getAuctionStatus())){
            return new ServiceResult<>(false,"只有拍卖中的车辆才可以出价","101");
        }
        carAuctionBidRecord.setBidFee(obj.getBigDecimal("lastAmount"));
        carAuctionBidRecord.setAddFee(obj.getBigDecimal("lastAmount"));
        carAuctionBidRecord.setBidTime(new Date());
        return carAuctionBidRecordService.saveCarAuctionBidRecord(carAuctionBidRecord);
    }

    @PostMapping(value = "/saveBiddenInfoByCode",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据拍牌号保存出价信息",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "customerCode",value = "车商编号",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "lastAmount",value = "最高出价",required = true,paramType = "query",dataType = "double")
    })
    public ServiceResult<Map<String,Object>> saveBiddenInfoByCode(@RequestBody JSONObject obj){
        CarAuctionBidRecord carAuctionBidRecord =new CarAuctionBidRecord();
        carAuctionBidRecord.setId(idWorker.nextId());
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"竞拍场次Id为空！","101");
        }
        carAuctionBidRecord.setAuctionId(obj.getLong("auctionId"));
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"竞拍场次车辆Id为空！","101");
        }
        carAuctionBidRecord.setAuctionCarId(obj.getLong("auctionCarId"));
        if(obj.getLong("carId")==null||obj.getLong("carId")==0){
            return new ServiceResult<>(false,"车辆Id为空！","101");
        }
        carAuctionBidRecord.setCarId(obj.getLong("carId"));
        if(obj.getString("customerCode")==null||"".equals(obj.getString("customerCode").trim())){
            return new ServiceResult<>(false,"车商编号为空！","101");
        }
        if(obj.getLong("lastAmount")==null){
            return new ServiceResult<>(false,"最高出价不能为空！","101");
        }
        carAuctionBidRecord.setBidFee(obj.getBigDecimal("lastAmount"));
        carAuctionBidRecord.setAddFee(obj.getBigDecimal("lastAmount"));
        carAuctionBidRecord.setBidTime(new Date());
        return carAuctionBidRecordService.saveBiddenInfoByCode(carAuctionBidRecord,obj.getString("customerCode"));
    }

    @PostMapping(value = "/cleanBiddenInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "清除车辆的出价记录",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long")
    })
    public ServiceResult<Map<String,Object>> cleanBiddenInfo(@RequestBody JSONObject obj){
        Map<String,Object> paramMap =new HashMap<>();
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"竞拍场次Id为空！","101");
        }
        paramMap.put("auctionId",obj.getLong("auctionId"));
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"竞拍场次车辆Id为空！","101");
        }
        paramMap.put("auctionCarId",obj.getLong("auctionCarId"));
        if(obj.getLong("carId")==null||obj.getLong("carId")==0){
            return new ServiceResult<>(false,"车辆Id为空！","101");
        }
        paramMap.put("carId",obj.getLong("carId"));
        return carAuctionBidRecordService.cleanBiddenInfo(paramMap);
    }

    @PostMapping(value = "/cleanLastBiddenInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "清除车辆的最后一条出价记录",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long")
    public ServiceResult<Map<String,Object>> cleanLastBiddenInfo(@RequestBody JSONObject obj){

        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"竞拍场次车辆Id为空！","101");
        }
        ServiceResult result = carAuctionBidRecordService.cleanLastBiddenInfo(obj.getLong("auctionCarId"));
        if(!result.getSuccess()){
            return result;
        }
        //获取最高出价记录
        CarAuctionBidRecord carAuctionBidRecord = carAuctionBidRecordService.selectLastBidInfo(obj.getLong("auctionCarId")).getResult();
        Map<String,Object> resultMap=new HashMap<>();
        if(carAuctionBidRecord!=null){
            resultMap.put("bidFee",carAuctionBidRecord.getBidFee());
            result.setResult(resultMap);
            return result;
        }else{
            CarLocaleAuctionCar carLocaleAuctionCar = carLocaleAuctionCarService.getCarLocaleAuctionCar(obj.getLong("auctionCarId")).getResult();
            resultMap.put("bidFee",carLocaleAuctionCar.getStartingPrice());
            result.setResult(resultMap);
            return result;
        }
    }

    @PostMapping(value = "/getCustomerBidPriceList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询该车辆的出价记录列表",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
    })
    public ServiceResult<List<Map<String,Object>>> getCustomerBidPriceList(@RequestBody JSONObject obj){
        ServiceResult<List<Map<String,Object>>> result =new ServiceResult<>();
        Map<String,Object> paramMap =new HashMap<>();
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"竞拍场次Id为空！","101");
        }
        paramMap.put("auctionId",obj.getLong("auctionId"));
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"竞拍场次车辆Id为空！","101");
        }
        paramMap.put("auctionCarId",obj.getLong("auctionCarId"));
        if(obj.getLong("carId")==null||obj.getLong("carId")==0){
            return new ServiceResult<>(false,"车辆Id为空！","101");
        }
        paramMap.put("carId",obj.getLong("carId"));
        List<CarAuctionBidRecord> carAuctionBidRecords = carAuctionBidRecordService.getCustomerBidPriceList(paramMap).getResult();
        List<Map<String,Object>> resultList =new ArrayList<>();
        for(CarAuctionBidRecord carAuctionBidRecord:carAuctionBidRecords){
            Map<String,Object> resultMap =new HashMap<>();
            resultMap.put("id",carAuctionBidRecord.getId());
            resultMap.put("auctionId",carAuctionBidRecord.getAuctionId());
            resultMap.put("auctionCarId",carAuctionBidRecord.getAuctionCarId());
            resultMap.put("carId",carAuctionBidRecord.getCarId());
            WtAppUser wtAppUser =appUserService.findById(carAuctionBidRecord.getCustomerId()).getResult();
            if(wtAppUser!=null){
                resultMap.put("customerCode",wtAppUser.getUserNum());
            }else {
                resultMap.put("customerCode",null);
            }
            resultMap.put("customerId",carAuctionBidRecord.getCustomerId());
            resultMap.put("addFee",carAuctionBidRecord.getAddFee());
            resultMap.put("bidFee",carAuctionBidRecord.getBidFee());
            resultMap.put("bidTime",carAuctionBidRecord.getBidTime());
            resultList.add(resultMap);
        }
        result.setSuccess(true);
        result.setResult(resultList);
        return result;
    }

    @PostMapping(value = "/setCustomerBidNumber",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "车商号补全",notes = "")
    @ApiImplicitParam(name = "auctionBidRecordIds",value = "出价记录Ids 规则，auctionBidRecordId1_customerId1,auctionBidRecordId2_customerId2",required = true,paramType = "query",dataType = "long")
    public ServiceResult<Map<String,Object>> setCustomerBidNumber(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        if(obj.getString("auctionBidRecordIds")==null||"".equals(obj.getString("auctionBidRecordIds"))){
            return new ServiceResult<>(false,"出价记录Ids为空！","101");
        }
        return carAuctionBidRecordService.setCustomerBidNumber(obj.getString("auctionBidRecordIds"));
    }

    @PostMapping(value = "/setCustomerBidNumberByCode",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "车商号补全",notes = "")
    @ApiImplicitParam(name = "auctionBidRecordCodes",value = "出价记录Ids 规则，auctionBidRecordId1_amount_customerCode1,auctionBidRecordId1_amount_customerCode1",required = true,paramType = "query",dataType = "long")
    public ServiceResult<Map<String,Object>> setCustomerBidNumberByCode(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        if(obj.getString("auctionBidRecordCodes")==null||"".equals(obj.getString("auctionBidRecordCodes"))){
            return new ServiceResult<>(false,"出价记录Ids为空！","101");
        }
        return carAuctionBidRecordService.setCustomerBidNumberByCode(obj.getString("auctionBidRecordCodes"));
    }

    @PostMapping(value = "/setAgainAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "设置车辆二拍",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionCarId",value = "场次车辆ID",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆信息Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "startAmount",value = "起步价",required = true,paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "reserveAmount",value = "保留价",required = true,paramType = "body",dataType = "double")
    })
    public ServiceResult<Map<String,Object>> setAgainAuction(@RequestBody JSONObject obj){
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"场次车辆ID为空！","101");
        }
        if(obj.getLong("carId")==null||obj.getLong("carId")==0){
            return new ServiceResult<>(false,"车辆Id为空！","101");
        }
        if(obj.getLong("startAmount")==null||obj.getLong("startAmount")==0){
            return new ServiceResult<>(false,"起步价为空！","101");
        }
        if(obj.getLong("reserveAmount")==null||obj.getLong("reserveAmount")==0){
            return new ServiceResult<>(false,"保留价为空！","101");
        }
        ServiceResult serviceResult = carLocaleAuctionCarService.setAgainAuction(obj.getLong("auctionCarId"),obj.getLong("carId"),obj.getLong("userId"),new BigDecimal(obj.getLong("startAmount")),new BigDecimal(obj.getLong("reserveAmount")));
        CarLocaleAuctionCar carLocaleAuctionCar =carLocaleAuctionCarService.selectById(obj.getLong("auctionCarId"));
        carLocaleAuctionCarService.largeScreenShowCar(carLocaleAuctionCar.getAuctionId(),obj.getLong("auctionCarId"));
        return serviceResult;
    }

    /***
     * 大屏显示列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/largeScreenDisplay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示列表",notes = "根据查询条件获取场次列表")
    public ServiceResult<ListEntity<Map<String,Object>>> largeScreenDisplay(@RequestBody JSONObject obj){
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        Map<String,Object> paramMap = new HashMap<>();
        if(obj.getString("status")!=null&&!"".equals(obj.getString("status").trim())){
            paramMap.put("status",obj.getString("status"));
        }
        if(obj.getString("title")!=null&&!"".equals(obj.getString("title").trim())){
            paramMap.put("title",obj.getString("title"));
        }
        if(obj.getString("beginTime")!=null&&!"".equals(obj.getString("beginTime"))){
            paramMap.put("beginTime",obj.getString("beginTime"));
        }
        if(obj.getString("endTime")!=null&&!"".equals(obj.getString("endTime"))){
            paramMap.put("endTime",obj.getString("endTime"));
        }
        int count = carLocaleAuctionService.largeScreenDisplayCount(paramMap).getResult();
        PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
        paramMap.put("startRowNum",pageEntity.getStartRowNum());
        paramMap.put("endRowNum",pageEntity.getEndRowNum());
        List<CarLocaleAuction> carAuctions =  carLocaleAuctionService.largeScreenDisplay(paramMap).getResult();
        List<Map<String,Object>> list = new ArrayList<>();
        for(CarLocaleAuction carAuction:carAuctions){
            Map<String,Object> map = new HashMap<>();
            map.put("id",carAuction.getId());
            map.put("code",carAuction.getCode());
            map.put("title",carAuction.getTitle());
            map.put("address",carAuction.getAddress());
            map.put("seeCarMan",carAuction.getSeeCarMan());
            map.put("seeCarPhone",carAuction.getSeeCarPhone());
            map.put("startTime",carAuction.getStartTime());
            Map selectMap = new HashMap<>();
            selectMap.put("auctionId",carAuction.getId());
            Integer carNum =carLocaleAuctionCarService.selectCarNumByAuction(selectMap);
            map.put("carNum",carNum);
            map.put("status",carAuction.getStatus());
            map.put("createTime",carAuction.getCreateTime());
            map.put("createPersonName",carAuction.getCreatePersonName());
            list.add(map);
        }
        ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
        listEntity.setList(list);
        listEntity.setCount(count);
        result.setResult(listEntity);
        if(list.size()>0){
            result.setSuccess("0","成功");
        }else{
            result.setSuccess("1","查询不到数据！");
        }
        return result;
    }

    /***
     * 大屏显示列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/largeScreenAuctionInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取大屏场次信息",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> largeScreenAuctionInfo(@RequestBody JSONObject obj){
        ServiceResult<Map<String,Object>> result =new ServiceResult<>();
        if(obj.getLong("auctionId")==null&&obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"场次Id为空！","101");
        }
        ServiceResult<CarLocaleAuction> auctionServiceResult =carLocaleAuctionService.selectById(obj.getLong("auctionId"));
        if(auctionServiceResult==null||!auctionServiceResult.getSuccess()||auctionServiceResult.getResult()==null){
            return new ServiceResult<>(false,"获取不到场次信息","101");
        }
        CarLocaleAuction carLocaleAuction=auctionServiceResult.getResult();
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("auctionId",carLocaleAuction.getId());
        resultMap.put("title",carLocaleAuction.getTitle());
        resultMap.put("carNum",carLocaleAuction.getCarNum());
        resultMap.put("status",carLocaleAuction.getStatus());
        resultMap.put("startTime",carLocaleAuction.getStartTime());
        result.setResult(resultMap);
        result.setSuccess(true);
        return result;
    }

    /***
     * 大屏显示列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/clearLargeScreenAuctionCar",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "清除车辆缓存操作",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> clearLargeScreenAuctionCar(@RequestBody JSONObject obj){
        if(obj.getLong("auctionId")==null&&obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"场次Id为空！","101");
        }
        return carLocaleAuctionService.clearLargeScreenAuctionCar(obj.getLong("auctionId"));
    }

    /***
     * 大屏显示--单条记录
     * @param obj
     * @return
     */
    @PostMapping(value = "/largeScreenShowCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--单条记录",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "车辆场次id",required = false,paramType = "query",dataType = "object"),
    })
    public ServiceResult<Map<String,Object>> largeScreenShowCar(@RequestBody JSONObject obj){
        Map paramMap =new HashMap();
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"场次Id为空！","101");
        }
        paramMap.put("auctionId",obj.getLong("auctionId"));
        paramMap.put("auctionCarId",obj.getLong("auctionCarId"));
        return carLocaleAuctionCarService.largeScreenShowCar(obj.getLong("auctionId"),obj.getLong("auctionCarId"));
    }

    /***
     * 大屏显示--开拍操作
     * @param obj
     * @return
     */
    @PostMapping(value = "/largeScreenStartAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--开拍操作",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> largeScreenStartAuction(@RequestBody JSONObject obj){
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"场次Id为空！","101");
        }
        return carLocaleAuctionService.largeScreenStartAuction(obj.getLong("auctionId"));
    }

    /***
     * 大屏显示--获取当前车辆的最高出价信息
     * @param obj
     * @return
     */
    @PostMapping(value = "/largeScreenBidPrice",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--获取当前车辆的最高出价信息",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "场次车辆id",required = false,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> largeScreenBidPrice(@RequestBody JSONObject obj){
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"场次车辆id为空！","101");
        }
        return carLocaleAuctionService.largeScreenBidPrice(obj.getLong("auctionCarId"));
    }

    /***
     * 大屏显示--确定当前车的竞拍结果
     * @param obj
     * @return
     */
    @PostMapping(value = "/largeScreenAuctionResult",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--确定当前车的竞拍结果",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "场次车辆id",required = false,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> largeScreenAuctionResult(@RequestBody JSONObject obj){
        if(obj.getLong("auctionCarId")==null||obj.getLong("auctionCarId")==0){
            return new ServiceResult<>(false,"场次Id为空！","101");
        }
        return carLocaleAuctionService.largeScreenAuctionResult(obj.getLong("auctionCarId"),obj.getLong("userId"));
    }

    /***
     * 大屏显示--确定当前车的竞拍结果
     * @param obj
     * @return
     */
    @PostMapping(value = "/largeScreenAuctionFinish",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--确定当前车的竞拍结果",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    public ServiceResult<Map<String,Object>> largeScreenAuctionFinish(@RequestBody JSONObject obj){
        if(obj.getLong("auctionId")==null||obj.getLong("auctionId")==0){
            return new ServiceResult<>(false,"场次Id为空！","101");
        }
        return carLocaleAuctionService.largeScreenAuctionFinish(obj.getLong("auctionId"));
    }


    @PostMapping(value = "/hasAuctionCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "参拍车辆列表",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime",value = "开拍开始时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "endTime",value = "开拍结束时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "title",value = "场次主题",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "cityId",value = "城市Id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    public ServiceResult<ListEntity<Map<String,Object>>> hasAuctionCarList(@RequestBody JSONObject obj){
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        Map<String,Object> paramMap = new HashMap<>();
        if(obj.getString("title")!=null&&!"".equals(obj.getString("title").trim())){
            paramMap.put("title",obj.getString("title"));
        }
        if(obj.getString("beginTime")!=null&&!"".equals(obj.getString("beginTime"))){
            paramMap.put("beginTime",obj.getString("beginTime"));
        }
        if(obj.getString("endTime")!=null&&!"".equals(obj.getString("endTime"))){
            paramMap.put("endTime",obj.getString("endTime")+"23:59:59");
        }
        if(obj.getLong("cityId")!=null&&!"".equals(obj.getLong("cityId"))){
            paramMap.put("cityId",obj.getLong("cityId"));
        }
        if(obj.get("status")!=null&&!"".equals(obj.getString("status"))){
            paramMap.put("status",obj.getLong("status"));
        }
        int count=0;
        if(obj.get("page")!=null && obj.get("limit")!=null){
            count = carLocaleAuctionCarService.hasAuctionCarCount(paramMap).getResult();
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
        }
        List<CarLocaleAuctionCar> carLocaleAuctionCars =  carLocaleAuctionCarService.hasAuctionCarList(paramMap).getResult();
        List<Map<String,Object>> list = new ArrayList<>();
        for(CarLocaleAuctionCar carLocaleAuctionCar:carLocaleAuctionCars){
            Map<String,Object> map = new HashMap<>();
            map.put("id",carLocaleAuctionCar.getId());
            map.put("auctionId",carLocaleAuctionCar.getAuctionId());
            map.put("carAutoNo",carLocaleAuctionCar.getCarAutoNo());
            map.put("userNum",carLocaleAuctionCar.getUserNum());
            map.put("auctionPlateNum",carLocaleAuctionCar.getAuctionPlateNum());
            map.put("autoInfoName",carLocaleAuctionCar.getAutoInfoName());
            map.put("licenseNumber",carLocaleAuctionCar.getLicenseNumber());
            map.put("storeName",carLocaleAuctionCar.getStoreName());
            map.put("sourceType",carLocaleAuctionCar.getSourceType());
            map.put("startTime",carLocaleAuctionCar.getStartTime());
            map.put("address",carLocaleAuctionCar.getAddress());
            map.put("title",carLocaleAuctionCar.getTitle());
            map.put("startingPrice",carLocaleAuctionCar.getStartingPrice());
            map.put("reservePrice",carLocaleAuctionCar.getReservePrice());
            map.put("topBidPrice",carLocaleAuctionCar.getTopBidPrice());
            map.put("auctionStatus",carLocaleAuctionCar.getAuctionStatus());
            map.put("publishUserName",carLocaleAuctionCar.getPublishUserName());
            map.put("publishUserMobile",carLocaleAuctionCar.getPublishUserName());
            list.add(map);
        }
        ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
        listEntity.setList(list);
        listEntity.setCount(count);
        result.setResult(listEntity);
        if(list.size()>0){
            result.setSuccess("0","成功");
        }else{
            result.setSuccess("1","查询不到数据！");
        }
        return result;
    }

    /***
     * 获取分享场次信息
     * zhangzijuan
     * @param obj
     * @return
     */
    @PostMapping(value = "/selectShareAuctionList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<AuctionListEntity<Map<String,Object>>> selectShareAuctionList(@RequestBody JSONObject obj) {
        ServiceResult<AuctionListEntity<Map<String, Object>>> result = new ServiceResult<>();
        try {
            AuctionListEntity<Map<String, Object>> auctionListEntity=new AuctionListEntity<>();
            Map<String,Object> paramMap=new HashMap<>();
            Long auctionId = obj.getLong("auctionId");
            paramMap.put("auctionId",auctionId);
            int count = carAutoService.selectAuctionCarCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuto> carAutos = carAutoService.selectAuctionCarList(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAuto carAuto:carAutos){
                Map<String,Object> map = new HashMap<>();
                map.put("id",carAuto.getId());
                map.put("mainPhoto",carAuto.getMainPhoto());
                map.put("startTime", carAuto.getStartTime());
                map.put("autoInfoName",carAuto.getAutoInfoName());
                map.put("vehicleAttributionCity",carAuto.getVehicleAttributionCity());
                map.put("startingPrice",carAuto.getStartingPrice());
                map.put("mileage",carAuto.getMileage());
                map.put("carAutoNo",carAuto.getCarAutoNo());
                map.put("auctionCode",carAuto.getAuctionCode());
                map.put("engineCapacity",carAuto.getEngineCapacity());
                map.put("engineCapacityUnit",carAuto.getEngineCapacityUnit());
                map.put("environment",carAuto.getEnvironment());
                map.put("beginRegisterDate",carAuto.getBeginRegisterDate());
                map.put("status",carAuto.getStatus());
                list.add(map);
            }
            Map<String,Object> map=new HashMap<>();
            map.put("auctionId",auctionId);
            List<CarLocaleAuction> carAuctions =  carLocaleAuctionService.selectAuctionListForApp(map).getResult();
            if (carAuctions!=null && carAuctions.size()>0 && carAuctions.get(0)!=null){
                auctionListEntity.setTitle(carAuctions.get(0).getTitle());
                auctionListEntity.setDesc(carAuctions.get(0).getSeeCarPhone());
            }
            auctionListEntity.setDataList(list);
            auctionListEntity.setCount(count);
            result.setResult(auctionListEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询单个竞拍详情
     * @param obj
     * @return
     */
    @PostMapping(value = "/selectLocaleAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarLocaleAuctionCar> selectLocaleAuctionCar(@RequestBody JSONObject obj) {
        ServiceResult<CarLocaleAuctionCar> result = new ServiceResult<>();
        try {
            Long auctionCarId = obj.getLong("auctionCarId");
            CarLocaleAuctionCar localeAuctionCar = carLocaleAuctionCarService.selectById(auctionCarId);
            if(localeAuctionCar!=null){
                CarAuctionBidRecord bidRecord = carAuctionBidRecordService.selectLastBidInfo(auctionCarId).getResult();
                if(bidRecord!=null){
                    localeAuctionCar.setTopBidPrice(bidRecord.getBidFee());
                }
            }
            result.setResult(localeAuctionCar);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
