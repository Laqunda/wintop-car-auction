package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import com.wintop.ms.carauction.entity.ElectronAuctionDetail;

public interface ElectronAuctionDetailService {
    /**
     * 查询竞拍详情
     * @param localeAuctionId
     * @return
     */
    ElectronAuctionDetail selectElectronAuctionDetail(Long localeAuctionId);

}
