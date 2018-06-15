package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoDetectionDataPhoto;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoDetectionDataPhotoReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoDetectionDataPhotoWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoDetectionDataPhotoModel {
    @Autowired
    private ICarAutoDetectionDataPhotoReadDao readDao;
    @Autowired
    private ICarAutoDetectionDataPhotoWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionDataPhotoModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoDetectionDataPhoto selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoDetectionDataPhoto> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public List<CarAutoDetectionDataPhoto> selectByAutoId(Long autoId) {
        return this.readDao.selectByAutoId(autoId);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoDetectionDataPhoto record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoDetectionDataPhoto record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoDetectionDataPhoto record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoDetectionDataPhoto record) {
        return this.writeDao.insertSelective(record);
    }

    public int insertArr(List<CarAutoDetectionDataPhoto> photoList) {
        return this.writeDao.insertArr(photoList);
    }

    public int deleteByClassIdAutoId(Map map) {
        return writeDao.deleteByClassIdAutoId(map);
    }
}