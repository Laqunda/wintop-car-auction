package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import com.wintop.ms.carauction.entity.ElectronAuctionCar;
import com.wintop.ms.carauction.entity.ElectronAuctionCarDetail;
import com.wintop.ms.carauction.entity.ElectronAuctionDetail;

import java.util.Map;

public interface ElectronAuctionDetailService {
    /**
     * 查询竞拍详情
     * @param localeAuctionId
     * @return
     */
    ElectronAuctionDetail selectElectronAuctionDetail(Long localeAuctionId);

    /**
     * 查询拍卖详情
     * @param map
     * @return
     */
    ElectronAuctionCarDetail selectAuctionCarDetail(Map<String,Object> map);

}
