package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoAuction;
import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import com.wintop.ms.carauction.entity.CarPriceExcel;
import com.wintop.ms.carauction.service.ICarAutoAuctionService;
import com.wintop.ms.carauction.service.ICarLocaleAuctionCarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-04-03
 */
@RestController
@RequestMapping("/service/importPrice")
public class ImportCarPriceInfoApi {
    @Resource
    private ICarLocaleAuctionCarService localeAuctionCarService;
    @Resource
    private ICarAutoAuctionService autoAuctionService;
    @PostMapping(value = "importCarPriceInfo")
    public ServiceResult<Map<String,Object>> importCarPriceInfo(@RequestBody Map<String,Object> map){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            List<CarPriceExcel> carPriceExcels=JSONObject.parseArray(JSONObject.toJSONString(map.get("carPriceExcels")),CarPriceExcel.class);
            List<CarLocaleAuctionCar> localeAuctionCars=localeAuctionCarService.getAuctionCarList((Long) map.get("auctionId")).getResult();
            List<CarAutoAuction> updateList=new ArrayList<>();
            String noPrice="";
            if (localeAuctionCars!=null && localeAuctionCars.size()>0){
                for (int i=0;i<localeAuctionCars.size();i++){
                    CarAutoAuction auction=new CarAutoAuction();
                    boolean flag = true;
                        for (int j=0;j<carPriceExcels.size();j++){
                            if (localeAuctionCars.get(i).getAuctionCode()!=null&&localeAuctionCars.get(i).getAuctionCode().equals(carPriceExcels.get(j).getCode())){
                                auction.setId(localeAuctionCars.get(j).getAutoAuctionId());
                                if (StringUtils.isNotBlank(carPriceExcels.get(j).getStartPrice())){
                                    flag = false;
                                    auction.setStartingPrice(transformToBig(carPriceExcels.get(j).getStartPrice()));
                                }
                                if (StringUtils.isNotBlank(carPriceExcels.get(j).getReservePrice())){
                                    auction.setReservePrice(transformToBig(carPriceExcels.get(j).getReservePrice()));
                                    flag = false;
                                }
                                auction.setModifyTime(new Date());
                                break;
                            }
                        }
                        if(flag){
                            noPrice+=localeAuctionCars.get(i).getAuctionCode()+",";
                        }else {
                            updateList.add(auction);
                        }
                }
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
            Integer a=0;
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("noPrice",noPrice);
            if (updateList.size()>0){
                a=autoAuctionService.batchUpdateById(updateList);
            }else {
                result.setResult(resultMap);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                return result;
            }
            if (a>0){
                result.setResult(resultMap);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }

    public BigDecimal transformToBig(String str){
        if (StringUtils.isNotBlank(str)){
            return new BigDecimal(str);
        }else {
            return new BigDecimal(0);
        }
    }
}
