package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoProceduresReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoProceduresWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarAutoProceduresModel {
    @Autowired
    private ICarAutoProceduresReadDao readDao;
    @Autowired
    private ICarAutoProceduresWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoProceduresModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoProcedures selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoProcedures> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoProcedures record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoProcedures record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoProcedures record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoProcedures record) {
        return this.writeDao.insertSelective(record);
    }

    /**
     * 根据车辆d查询车辆手续信息
     * @param carId
     * @return
     */
    public CarAutoProcedures getAutoProceduresByCarId(Long carId) {
        return this.readDao.getAutoProceduresByCarId(carId);
    }
}