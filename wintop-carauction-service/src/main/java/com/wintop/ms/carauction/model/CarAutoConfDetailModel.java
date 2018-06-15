package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.mapper.read.ICarAutoConfDetailReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoConfDetailWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoConfDetailModel {
    @Autowired
    private ICarAutoConfDetailReadDao readDao;
    @Autowired
    private ICarAutoConfDetailWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoConfDetailModel.class);

    public int countByExample(Map<String,Object> map) {
        int count = this.readDao.countByExample(map);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoConfDetail selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoConfDetail> selectByExample(Map<String,Object> map) {
        return this.readDao.selectByExample(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoConfDetail record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoConfDetail record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoConfDetail record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoConfDetail record) {
        return this.writeDao.insertSelective(record);
    }

    /**
     * 根据车辆id查询配置信息
     * @param carId
     * @return
     */
    public List<CarAutoConfDetail> selectConfigsByCarId(Long carId){
        return readDao.selectConfigsByCarId(carId);
    }

    /***
     * 批量插入车辆配置信息
     * @param recordArr
     * @return
     */
    public int insertArr(List<CarAutoConfDetail> recordArr) {
        return writeDao.insertArr(recordArr);
    }

    /***
     * 清理车辆配置信息
     * @param autoId
     * @return
     */
    public int deleteByAutoId(Long autoId){
        return writeDao.deleteByAutoId(autoId);
    }
}