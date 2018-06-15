package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoStyle;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoStyleReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoStyleWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarAutoStyleModel {
    @Autowired
    private ICarAutoStyleReadDao readDao;
    @Autowired
    private ICarAutoStyleWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoStyleModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoStyle selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoStyle> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoStyle record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoStyle record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoStyle record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoStyle record) {
        return this.writeDao.insertSelective(record);
    }
}