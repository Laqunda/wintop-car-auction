package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/electronAuctionDetail")
public class ElectronAuctionDetailApi {
    @Autowired
    private ElectronAuctionDetailService auctionDetailService;
    @Autowired
    private TblAuctionLogService auctionLogService;
    @Autowired
    private ICarLocaleAuctionCarService auctionCarService;

    private static final Logger logger = LoggerFactory.getLogger(ElectronAuctionDetailApi.class);

    /***
     * 查询现场拍详情
     * @return
     */
    @RequestMapping(value = "/selectElectronAuctionDetail",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ElectronAuctionDetail> selectElectronAuctionDetail(@RequestBody JSONObject obj) {
        ServiceResult<ElectronAuctionDetail> result = new ServiceResult<>();
        try {
            Long localeAuctionId = obj.getLong("localeAuctionId");
            ElectronAuctionDetail auctionDetail = auctionDetailService.selectElectronAuctionDetail(localeAuctionId);
            result.setResult(auctionDetail);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            logger.info("查询现场拍详情接口失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 调整竞价幅度
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updatePriceRange",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ElectronAuctionDetail> updatePriceRange(@RequestBody JSONObject obj) {
        ServiceResult<ElectronAuctionDetail> result = new ServiceResult<>();
        try {
            Long localeAuctionId = obj.getLong("localeAuctionId");
            CarLocaleAuctionCar localeAuctionCar = auctionCarService.selectCurrentAuctionCar(localeAuctionId);
            if(localeAuctionCar!=null){
                //更新调价幅度
                BigDecimal priceRange = obj.getBigDecimal("priceRange");
                TblAuctionLog auctionLog = new TblAuctionLog();
                auctionLog.setId(IdWorker.getInstance().nextId());
                auctionLog.setLocaleAuctionId(localeAuctionCar.getAuctionId());
                auctionLog.setAuctionTimesName(localeAuctionCar.getTitle());
                auctionLog.setAuctionCarId(localeAuctionCar.getId());
                auctionLog.setCarId(localeAuctionCar.getCarId());
                auctionLog.setPriceRange(priceRange);
                auctionLog.setBidFee(priceRange);
                auctionLog.setAuctionTime(new Date());
                auctionLog.setPriceType("3");
                auctionLog.setEnable("0");
                auctionLog.setCreatePerson(obj.getLong("createPerson"));
                auctionLog.setCreateTime(new Date());
                auctionLogService.updatePriceRange(auctionLog);
                ElectronAuctionDetail auctionDetail = auctionDetailService.selectElectronAuctionDetail(localeAuctionId);
                result.setResult(auctionDetail);
            }else{
                ElectronAuctionDetail auctionDetail = new ElectronAuctionDetail();
                auctionDetail.setSynch("0");
                result.setResult(auctionDetail);
            }
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            return result;
        }catch (Exception e){
            logger.info("调整竞价幅度失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /**
     * 打点出价
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateBidFeePoint",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ElectronAuctionDetail> updateBidFeePoint(@RequestBody JSONObject obj) {
        ServiceResult<ElectronAuctionDetail> result = new ServiceResult<>();
        try {
            Long localeAuctionId = obj.getLong("localeAuctionId");
            CarLocaleAuctionCar localeAuctionCar = auctionCarService.selectCurrentAuctionCar(localeAuctionId);
            if(localeAuctionCar!=null){
                //2加价，1减价
                String adjustType = obj.getString("adjustType");
                TblAuctionLog auctionLog = new TblAuctionLog();
                auctionLog.setId(IdWorker.getInstance().nextId());
                auctionLog.setAuctionTime(new Date());
                auctionLog.setPriceType("2");
                auctionLog.setEnable("0");
                auctionLog.setCreatePerson(obj.getLong("createPerson"));
                auctionLog.setCreateTime(new Date());
                auctionLogService.updateBidFeePoint(localeAuctionCar,auctionLog,adjustType);
                ElectronAuctionDetail auctionDetail = auctionDetailService.selectElectronAuctionDetail(localeAuctionId);
                result.setResult(auctionDetail);
            }else{
                ElectronAuctionDetail auctionDetail = new ElectronAuctionDetail();
                auctionDetail.setSynch("0");
                result.setResult(auctionDetail);
            }
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            return result;
        }catch (Exception e){
            logger.info("更新打点出价失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /***
     * 查询拍卖详情
     * @return
     */
    @RequestMapping(value = "/selectAuctionCarDetail",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ElectronAuctionCarDetail> selectAuctionCarDetail(@RequestBody JSONObject obj) {
        ServiceResult<ElectronAuctionCarDetail> result = new ServiceResult<>();
        try {
            Long auctionCarId = obj.getLong("auctionCarId");
            Map<String,Object> map = new HashMap<>();
            map.put("auctionCarId",auctionCarId);
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            ElectronAuctionCarDetail auctionDetail = auctionDetailService.selectAuctionCarDetail(map);
            result.setResult(auctionDetail);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            logger.info("查询拍卖详情接口失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

}
