package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionSetting;
import com.wintop.ms.carauction.entity.CarAuctionRedis;
import com.wintop.ms.carauction.entity.CarRegionSetting;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAuctionSettingService;
import com.wintop.ms.carauction.service.ICarCenterService;
import com.wintop.ms.carauction.service.ICarRegionSettingService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆拍卖信息接口类
 */
@RestController
@RequestMapping("/service/auctionSetting")
public class CarAuctionSettingApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAuctionSettingApi.class);
    private static final int DELAYED_TIME = 10;
    private static final int END_KEEP_TIME = 10;
    private static final int KEEP_TIME = 120;
    private static final String USE = "1";
    @Autowired
    private ICarAuctionSettingService auctionSettingService;
    @Autowired
    private RedisManagerTemplate redisManagerTemplate;
    private IdWorker idWorker = new IdWorker(10);

    /***
     * 查询线上拍卖设置列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAuctionSettingList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarAuctionSetting>> selectAuctionSettingList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarAuctionSetting>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            if(obj.get("regionId")!=null){
                map.put("regionId",obj.getLong("regionId"));
            }
            int count = auctionSettingService.countByExample(map);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            List<CarAuctionSetting> auctionSettings = auctionSettingService.selectByExample(map);
            ListEntity<CarAuctionSetting> listEntity = new ListEntity<>();
            listEntity.setList(auctionSettings);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询线上拍卖设置列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 保存线上拍卖设置
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveAuctionSetting",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveAuctionSetting(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarAuctionSetting auctionSetting = JSONObject.toJavaObject(obj,CarAuctionSetting.class);
            if(auctionSettingService.selectByRegionId(auctionSetting.getRegionId())!=null){
                result.setError(ResultCode.UNIQUE_REGION_SETTING.strValue(),ResultCode.UNIQUE_REGION_SETTING.getRemark());
                return result;
            };
            auctionSetting.setId(idWorker.nextId());
            auctionSetting.setCreateTime(new Date());
            auctionSetting.setKeepTime(KEEP_TIME);
            auctionSetting.setEndKeepTime(END_KEEP_TIME);
            auctionSetting.setDelayedTime(DELAYED_TIME);
            auctionSetting.setStatus(USE);
            int count = auctionSettingService.insert(auctionSetting);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            try{
                redisManagerTemplate.add(CarAuctionRedis.getAuctionKey(auctionSetting.getRegionId()),CarAuctionRedis.getAuctionSettingJson(auctionSetting));
            } catch (Exception e){
                e.printStackTrace();
                logger.info("保存线上拍卖保存redis失败",e);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存线上拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改线上拍卖设置
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateAuctionSetting",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateAuctionSetting(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try {
            CarAuctionSetting auctionSetting = JSONObject.toJavaObject(obj,CarAuctionSetting.class);
            auctionSetting.setUpdateTime(new Date());
            int count = auctionSettingService.updateByIdSelective(auctionSetting);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            try{
                auctionSetting = auctionSettingService.selectById(auctionSetting.getId());
                redisManagerTemplate.update(CarAuctionRedis.getAuctionKey(auctionSetting.getRegionId()),CarAuctionRedis.getAuctionSettingJson(auctionSetting));
            } catch (Exception e){
                e.printStackTrace();
                logger.info("修改线上拍卖保存redis失败",e);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改线上拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /***
     * 查询线上拍卖设置
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAuctionSetting",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarAuctionSetting> selectAuctionSetting(@RequestBody JSONObject obj) {
        ServiceResult<CarAuctionSetting> result = new ServiceResult<>();
        try {
            CarAuctionSetting auctionSetting = auctionSettingService.selectById(obj.getLong("id"));
            if(auctionSetting==null){
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
                return result;
            }
            //System.out.println(CarAuctionRedis.getAuctionSettingJson(auctionSetting));
            result.setResult(auctionSetting);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询线上拍卖设置失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
