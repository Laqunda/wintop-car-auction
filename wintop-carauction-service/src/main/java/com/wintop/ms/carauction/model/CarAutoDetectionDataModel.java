package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoDetectionData;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoDetectionDataReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoDetectionDataWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoDetectionDataModel {
    @Autowired
    private ICarAutoDetectionDataReadDao readDao;
    @Autowired
    private ICarAutoDetectionDataWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionDataModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoDetectionData selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoDetectionData> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    /**
     * 根据条件查询记录
     * @param param
     * @return
     */
    public List<CarAutoDetectionData> selectByCondition(Map<String, Object> param){
        return this.readDao.selectByCondition(param);
    }

    public List<CarAutoDetectionData> selectByAutoId(Long autoId) {
        return this.readDao.selectByAutoId(autoId);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int deleteByClassIdAutoId(Map map) {
        return this.writeDao.deleteByClassIdAutoId(map);
    }

    public int updateByPrimaryKeySelective(CarAutoDetectionData record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoDetectionData record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoDetectionData record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoDetectionData record) {
        return this.writeDao.insertSelective(record);
    }

    public int insertArr(List<CarAutoDetectionData> dataList) {
        return this.writeDao.insertArr(dataList);
    }
}