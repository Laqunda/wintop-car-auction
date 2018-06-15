package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoInfoDetailReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoInfoDetailWriteDao;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoInfoDetailModel {
    @Autowired
    private ICarAutoInfoDetailReadDao readDao;
    @Autowired
    private ICarAutoInfoDetailWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoInfoDetailModel.class);

    public int countByExample(Map<String,Object> map) {
        int count = this.readDao.countByExample(map);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoInfoDetail selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoInfoDetail> selectByExample(Map<String,Object> map) {
        return this.readDao.selectByExample(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoInfoDetail record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoInfoDetail record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoInfoDetail record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoInfoDetail record) {
        return this.writeDao.insertSelective(record);
    }

    /**
     * 查询车辆基本信息
     * @param carId
     * @return
     */
    public CarAutoInfoDetail selectDetailByCarId(Long carId){
        return readDao.selectDetailByCarId(carId);
    }

    /***
     * 根据车辆id修改车辆基本信息
     * @param detail
     * @return
     */
    public int updateRemarkByautoId(CarAutoInfoDetail detail){
        return writeDao.updateRemarkByautoId(detail);
    }
}