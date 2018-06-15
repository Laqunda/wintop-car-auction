package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoTransferLog;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoTransferLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoTransferLogWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoTransferLogModel {
    @Autowired
    private ICarAutoTransferLogReadDao readDao;
    @Autowired
    private ICarAutoTransferLogWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoTransferLogModel.class);

    public int countByExample(Map<String,Object> map) {
        int count = this.readDao.countByExample(map);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoTransferLog selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoTransferLog> selectByExample(Map<String,Object> map) {
        return this.readDao.selectByExample(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }


    public int insert(CarAutoTransferLog record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoTransferLog record) {
        return this.writeDao.insert(record);
    }

    /**
     * 当前订单过户流程标记
     * @param map
     * @return
     */
    public List<CarAutoTransferLog> queryTransferFlowList(Map<String,Object> map){
        return readDao.queryTransferFlowList(map);
    }

    /**
     * 查询过户轨迹
     * @param map
     * @return
     */
    public List<CarAutoTransferLog> queryTransferList(Map<String,Object> map){
        return readDao.queryTransferList(map);
    }

    /**
     * 查询订单手续信息
     * @param orderId
     * @return
     */
    public List<CarAutoTransferLog> selectTransferLogByOrderId(Long orderId){
        return readDao.selectTransferLogByOrderId(orderId);
    }
}