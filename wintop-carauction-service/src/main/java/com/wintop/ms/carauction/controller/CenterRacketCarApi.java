package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAutoLogService;
import com.wintop.ms.carauction.service.ICarLocaleAuctionService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中心发拍我的车辆列表接口
 */
@RestController
@RequestMapping("/service/centerRacketCar")
public class CenterRacketCarApi {
    private static final Logger logger = LoggerFactory.getLogger(CenterRacketCarApi.class);
    @Resource
    private ICarLocaleAuctionService iCarLocaleAuctionService;
    @Resource
    private ICarManagerUserService iCarManagerUserService;
    @Resource
    private ICarAutoLogService autoLogService;
    /***
     * 中心发拍我的车辆列表
     * @param obj
     * @return
     */
    @PostMapping(value = "/selectCenterRacketCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectCenterRacketCarList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {

            Map<String,Object> paramMap = new HashMap<>();
            String status = obj.getString("status");
            String autoInfoName = obj.getString("autoInfoName");
            paramMap.put("status",status);
            paramMap.put("auctionType",obj.get("auctionType"));
            paramMap.put("autoInfoName",autoInfoName);
            Long userId = obj.getLong("customerId");
            if(userId != null){
               CarManagerUser carManagerUser = iCarManagerUserService.selectByPrimaryKey(userId,true);
               paramMap.put("departmentId",carManagerUser.getDepartmentId());
            }
            int count = iCarLocaleAuctionService.selectCenterRacketCarCount(paramMap).getResult();
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarLocaleAuction> carLocaleAuctions = iCarLocaleAuctionService.selectCenterRacketCarList(paramMap).getResult();
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarLocaleAuction carLocaleAuction : carLocaleAuctions){
                Map<String,Object> map = new HashMap<>();
                if ("3".equals(carLocaleAuction.getStatus())){
                    Map<String,Object>map1=new HashMap<>();
                    map1.put("status",carLocaleAuction.getStatus());
                    map1.put("carId",carLocaleAuction.getId());
                    CarAutoLog autoLog=autoLogService.selectCarLog(map1);
                    if (autoLog!=null){
                        map.put("log",autoLog.getMsg());
                    }else {
                        map.put("log","");
                    }
                } else{
                    map.put("log","");
                }
                map.put("id",carLocaleAuction.getCarId());
                map.put("mainPhoto",carLocaleAuction.getMainPhoto());
                map.put("auctionStartTime", carLocaleAuction.getAuctionStartTime());
                map.put("auctionEndTime",carLocaleAuction.getAuctionEndTime());
                map.put("autoInfoName",carLocaleAuction.getAutoInfoName());
                map.put("startingPrice",carLocaleAuction.getStartingPrice());;
                map.put("carAutoNo",carLocaleAuction.getCarAutoNo());
                map.put("status",carLocaleAuction.getStatus());
                map.put("transactionFee",carLocaleAuction.getTransactionFee());
                map.put("createTime",carLocaleAuction.getCreateTime());
                map.put("pulishUserName",carLocaleAuction.getPublishUserName());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取中心发拍我的车辆列表失败",e);
            result.setError("-1","异常");
        }
        return result;
    }

}
