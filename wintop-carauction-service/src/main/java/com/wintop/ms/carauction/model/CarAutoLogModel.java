package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoLogWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoLogModel {
    @Autowired
    private ICarAutoLogReadDao readDao;
    @Autowired
    private ICarAutoLogWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoLogModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoLog selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoLog> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public int insert(CarAutoLog record) {
        return this.writeDao.insert(record);
    }
    /**
     * 查询车辆轨迹
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:
     */
    public List<CarAutoLog> selectCarLogByCarId(Map<String,Object> map){
        return readDao.selectCarLogByCarId(map);
    }
    public CarAutoLog selectCarLog(Map<String,Object> map){
        return readDao.selectCarLog(map);
    }

    public int selectCountEndByUserId(Long userId){
        return readDao.selectCountEndByUserId(userId);
    }

    public List<CarAutoLog> selectWaitOrderList(Map<String,Object> map){
        return readDao.selectWaitOrderList(map);
    }

    public List<CarAutoLog> selectEndOrderList(Map<String,Object> map){
        return readDao.selectEndOrderList(map);
    }


}