package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoDetectionClassOptions;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoDetectionClassOptionsReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoDetectionClassOptionsWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoDetectionClassOptionsModel {
    @Autowired
    private ICarAutoDetectionClassOptionsReadDao readDao;
    @Autowired
    private ICarAutoDetectionClassOptionsWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionClassOptionsModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoDetectionClassOptions selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoDetectionClassOptions> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoDetectionClassOptions record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoDetectionClassOptions record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoDetectionClassOptions record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoDetectionClassOptions record) {
        return this.writeDao.insertSelective(record);
    }

    public List<CarAutoDetectionClassOptions> selectByAutoId(Map map){
        return readDao.selectByClassIdAutoId(map);
    }
}