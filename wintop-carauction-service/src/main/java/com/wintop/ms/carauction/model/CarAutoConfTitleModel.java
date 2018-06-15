package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoConfTitle;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoConfTitleReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoConfTitleWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAutoConfTitleModel {
    @Autowired
    private ICarAutoConfTitleReadDao readDao;
    @Autowired
    private ICarAutoConfTitleWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoConfTitleModel.class);

    public int countByExample(Map<String,Object> map) {
        int count = this.readDao.countByExample(map);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoConfTitle selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoConfTitle> selectByExample(Map<String,Object> map) {
        return this.readDao.selectByExample(map);
    }

    public List<CarAutoConfTitle> selectAll(Map map) {
        return this.readDao.selectAll(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoConfTitle record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoConfTitle record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAutoConfTitle record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAutoConfTitle record) {
        return this.writeDao.insertSelective(record);
    }
}