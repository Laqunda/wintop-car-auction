package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoPhoto;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoPhotoReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoPhotoWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoPhotoModel {
    @Autowired
    private ICarAutoPhotoReadDao readDao;
    @Autowired
    private ICarAutoPhotoWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoPhotoModel.class);

    public int countByExample(Map<String,Object> map) {
        int count = this.readDao.countByExample(map);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoPhoto selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoPhoto> selectByExample(Map<String,Object> map) {
        return this.readDao.selectByExample(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoPhoto record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoPhoto record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoPhoto record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoPhoto record) {
        return this.writeDao.insertSelective(record);
    }

    /**
     * 根据carId条件查询
     */
    public List<CarAutoPhoto> selectByCarId(Long carId){
        return readDao.selectByCarId(carId);
    }

    /***
     * 批量新增多张照片
     * @param list
     * @return
     */
    public int insertArr(List<CarAutoPhoto> list){
        return writeDao.insertArr(list);
    }

    public void deleteByAutoId(Long autoId) {
        writeDao.deleteByAutoId(autoId);
    }
}