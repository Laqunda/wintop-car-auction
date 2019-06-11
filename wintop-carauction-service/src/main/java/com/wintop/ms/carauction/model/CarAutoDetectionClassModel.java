package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.CarAutoDetectionClassMap;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoDetectionClassReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoDetectionClassWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoDetectionClassModel {
    @Autowired
    private ICarAutoDetectionClassReadDao readDao;
    @Autowired
    private ICarAutoDetectionClassWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionClassModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoDetectionClass selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoDetectionClass> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public List<CarAutoDetectionClass> selectByAll(){
        return this.readDao.selectByAll();
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoDetectionClass record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoDetectionClass record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoDetectionClass record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoDetectionClass record) {
        return this.writeDao.insertSelective(record);
    }

    public List<CarAutoDetectionClassMap> selectByDetectionDataType(Map map){
        return readDao.selectByDetectionDataType(map);
    }

    public List<CarAutoDetectionClass> getAutoDetectionClass(CarAutoDetectionClass carAutoDetectionClass) {
        return readDao.getAutoDetectionClass(carAutoDetectionClass);
    }
}