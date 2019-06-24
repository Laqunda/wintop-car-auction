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
                    CarLocaleAuctionCar auctionCar = localeAuctionCars.get(i);
                    CarAutoAuction auction=new CarAutoAuction();
                    boolean flag = true;
                        for (int j=0;j<carPriceExcels.size();j++){
                            CarPriceExcel priceExcel = carPriceExcels.get(j);
                            if (auctionCar.getAuctionCode()!=null && auctionCar.getAuctionCode().equals(priceExcel.getCode())){
                                auction.setId(auctionCar.getAutoAuctionId());
                                if (StringUtils.isNotBlank(priceExcel.getStartPrice())){
                                    flag = false;
                                    BigDecimal startPrice=transformToBig(priceExcel.getStartPrice());
                                    auction.setStartingPrice(startPrice);
                                    //初始化加价幅度存储
                                    auctionCar.setPriceRange(queryPriceRange(startPrice));
                                }
                                if (StringUtils.isNotBlank(priceExcel.getReservePrice())){
                                    auction.setReservePrice(transformToBig(priceExcel.getReservePrice()));
                                    flag = false;
                                }
                                auction.setModifyTime(new Date());
                                break;
                            }
                        }
                        if(flag){
                            noPrice+=auctionCar.getAuctionCode()+",";
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
                localeAuctionCarService.batchUpdateLocaleAuctionById(localeAuctionCars);
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

    /**
     * 加价幅度规则
     *
     * 0≤起拍价＜1000元之间加价幅度100元
     *
     * 1000≤起拍价＜10000元之间加价幅度500元
     *
     * 1万≤起拍价＜10万之间加价幅度1000元
     *
     * 10万≤起拍价＜20万之间加价幅度5000元
     *
     * 20万≤起拍价＜100万之间加价幅度1万元
     *
     * 100万≤起拍价＜200万之间加价幅度5万元
     *
     * 起拍价≥200万以上加价幅度10万元
     * @param startPrice
     * @return
     */
    public BigDecimal queryPriceRange(BigDecimal startPrice){
        double price=startPrice.doubleValue();
        double priceRange=0;
        if (price>=0 && price<1000){
            priceRange=100;
        }else if (price>=1000 && price<10000){
            priceRange=500;
        }else if (price>=10000 && price<100000){
            priceRange=1000;
        }else if (price>=100000 && price<200000){
            priceRange=5000;
        }else if (price>=200000 && price<1000000){
            priceRange=10000;
        }else if (price>=1000000 && price<2000000){
            priceRange=50000;
        }else if (price>=2000000){
            priceRange=100000;
        }
        return BigDecimal.valueOf(priceRange);
    }

}
