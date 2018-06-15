package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarRegionServerfeeSetting;
import com.wintop.ms.carauction.entity.CarRegionSetting;
import com.wintop.ms.carauction.mapper.read.ICarRegionSettingReadDao;
import com.wintop.ms.carauction.mapper.write.ICarRegionSettingWriteDao;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class CarRegionSettingModel {
    @Autowired
    private ICarRegionSettingReadDao regionSettingReadDao;
    @Autowired
    private ICarRegionSettingWriteDao regionSettingWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return regionSettingReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarRegionSetting> selectByExample(Map<String,Object> map){
        return regionSettingReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarRegionSetting selectByPrimaryKey(Long id){
        return regionSettingReadDao.selectByPrimaryKey(id);
    }

    /**
     * 根据地区主键查询记录
     */
    public CarRegionSetting selectByRegionId(Long regionId){
        return regionSettingReadDao.selectByRegionId(regionId);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarRegionSetting record){
        return regionSettingWriteDao.insert(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(CarRegionSetting record){
        return regionSettingWriteDao.updateByPrimaryKeySelective(record);
    }

    /**
     *根据当前时间获取配置的支付和过户违约时间
     * @param date
     * @param regionId
     * @param type,1支付违约时间，2过户违约时间
     * @return
     */
    public Date getBreachTime(Date date,Long regionId,String type){
        CarRegionSetting regionSetting = regionSettingReadDao.selectByRegionId(regionId);
        if(regionSetting==null){
            return null;
        }
        int days;
        Date time;
        if("1".equals(type)){
            days = regionSetting.getPayBreachDay();
            time = regionSetting.getPayBreachTime();
        }else{
            days = regionSetting.getTransferBreachDay();
            time = regionSetting.getTransferBreachTime();
        }
        Date endDate = CarAutoUtils.getEndDate(date,days);
        String datetime = DateFormatUtils.format(endDate,"yyyy-MM-dd")+" "+DateFormatUtils.format(time,"HH:mm:ss");
        try{
            return DateUtils.parseDate(datetime,"yyyy-MM-dd HH:mm:ss");
        } catch (Exception e){
            return null;
        }

    }

}
