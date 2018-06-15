package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoFamilyReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoFamilyWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarAutoFamilyModel {
    @Autowired
    private ICarAutoFamilyReadDao readDao;
    @Autowired
    private ICarAutoFamilyWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoFamilyModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoFamily selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoFamily> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoFamily record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoFamily record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoFamily record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoFamily record) {
        return this.writeDao.insertSelective(record);
    }
}