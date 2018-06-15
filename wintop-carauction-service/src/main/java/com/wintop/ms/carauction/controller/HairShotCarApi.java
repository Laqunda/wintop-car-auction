package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAutoLogService;
import com.wintop.ms.carauction.service.ICarAutoService;
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
 * 发拍车辆列表接口
 */
@RestController
@RequestMapping("/service/hairShotCar")
public class HairShotCarApi {
    private static final Logger logger = LoggerFactory.getLogger(HairShotCarApi.class);
    @Resource
    private ICarAutoService carAutoService;
    @Resource
    private ICarManagerUserService iCarManagerUserService;
    @Resource
    private ICarAutoLogService autoLogService;
    /***
     * 发拍的车辆列表
     * @param obj
     * @return
     */
    @PostMapping(value = "/selectHairShotCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<Map<String,Object>>> selectHairShotCarList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<Map<String,Object>>> result = new ServiceResult<>();
        try {
            Long customerId = obj.getLong("customerId");
            Map<String,Object> paramMap = new HashMap<>();
            String status = obj.getString("status");
            paramMap.put("status",status);
            paramMap.put("customerId",customerId);
            paramMap.put("autoInfoName",obj.getString("autoInfoName"));
            if(customerId != null){
                CarManagerUser carManagerUser = iCarManagerUserService.selectByPrimaryKey(customerId,true);
                paramMap.put("departmentId",carManagerUser.getDepartmentId());
                paramMap.put("managerId",carManagerUser.getId());
            }
          //  paramMap.put("storeId",obj.getLong("storeId"));
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuto> carAutos = carAutoService.selectHairShotCarList(paramMap);
            int count = carAutoService.selectHairShotCarCount(paramMap);
            List<Map<String,Object>> list = new ArrayList<>();
            for(CarAuto carAuto:carAutos){
                Map<String,Object> map = new HashMap<>();
//                if ("3".equals(carAuto.getStatus())){
//                    Map<String,Object>map1=new HashMap<>();
//                    map1.put("status",carAuto.getStatus());
//                    map1.put("carId",carAuto.getId());
//                    CarAutoLog autoLog=autoLogService.selectCarLog(map1);
//                    if (autoLog!=null){
//                        map.put("log",autoLog.getMsg());
//                    }else {
//                        map.put("log","");
//                    }
//                } else{
//                    map.put("log","");
//                }
                map.put("log",carAuto.getLogMsg());
                map.put("id",carAuto.getId());
                map.put("mainPhoto",carAuto.getMainPhoto());
                map.put("auctionStartTime", carAuto.getAuctionStartTime());
                map.put("auctionEndTime",carAuto.getAuctionEndTime());
                map.put("autoInfoName",carAuto.getAutoInfoName());
                map.put("startingPrice",carAuto.getStartingPrice());;
                map.put("carAutoNo",carAuto.getCarAutoNo());
                map.put("status",carAuto.getStatus());
                map.put("transactionFee",carAuto.getTransactionFee());
                map.put("createTime",carAuto.getCreateTime());
                map.put("pulishUserName",carAuto.getPublishUserName());
                map.put("pulishTime",carAuto.getPublishTime());
                list.add(map);
            }
            ListEntity<Map<String,Object>> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取发车车辆列表失败",e);
            result.setError("-1","异常");
        }
        return result;
    }


}
