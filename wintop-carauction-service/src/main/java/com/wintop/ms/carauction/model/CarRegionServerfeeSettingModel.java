package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarRegionServerfeeSetting;
import com.wintop.ms.carauction.mapper.read.ICarRegionServerfeeSettingReadDao;
import com.wintop.ms.carauction.mapper.write.ICarRegionServerfeeSettingWriteDao;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CarRegionServerfeeSettingModel {
    @Autowired
    private ICarRegionServerfeeSettingReadDao serverfeeSettingReadDao;
    @Autowired
    private ICarRegionServerfeeSettingWriteDao serverfeeSettingWriteDao;

    private IdWorker idWorker = new IdWorker(10);

    /**
     * 根据成交价查询记录集
     */
    public CarRegionServerfeeSetting selectByClosingPrice(BigDecimal closingPrice){
        return serverfeeSettingReadDao.selectByClosingPrice(closingPrice);
    }

    /**
     * 根据地区配置主键查询记录
     */
    public List<CarRegionServerfeeSetting> selectByRegionSettingId(Long regionSettingId){
        return serverfeeSettingReadDao.selectByRegionSettingId(regionSettingId);
    }

    /**
     * 根据地区配置主键删除记录
     */
    public int deleteByRegionSettingId(Long regionSettingId){
        return serverfeeSettingWriteDao.deleteByRegionSettingId(regionSettingId);
    }

    /**
     * 保存记录
     */
    public int insert(CarRegionServerfeeSetting record){
        return serverfeeSettingWriteDao.insert(record);
    }

    /**
     * 按固定格式批量保存服务费
     * @param regionSettingId
     * @param serverfeeSettings
     * @return
     */
    public int saveBatchServerfee(Long regionSettingId,String serverfeeSettings){
        int count = 0;
        String[] serverfeesArray = serverfeeSettings.split(",");
        for(String serverfees:serverfeesArray){
            String[] serverfee = serverfees.split("-");
            BigDecimal startClosingPrice = new BigDecimal(serverfee[0]);
            BigDecimal endClosingPrice = new BigDecimal(serverfee[1]);
            BigDecimal serviceFee = new BigDecimal(serverfee[2]);
            CarRegionServerfeeSetting serverfeeSetting = new CarRegionServerfeeSetting();
            serverfeeSetting.setId(idWorker.nextId());
            serverfeeSetting.setRegionSettingId(regionSettingId);
            serverfeeSetting.setStartClosingPrice(startClosingPrice);
            serverfeeSetting.setEndClosingPrice(endClosingPrice);
            serverfeeSetting.setServiceFee(serviceFee);
            count += serverfeeSettingWriteDao.insert(serverfeeSetting);
        }
        return count;
    }
}
