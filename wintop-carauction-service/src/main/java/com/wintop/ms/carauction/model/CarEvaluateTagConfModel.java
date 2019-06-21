package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarEntrustRecord;
import com.wintop.ms.carauction.entity.CarEvaluateLevelConf;
import com.wintop.ms.carauction.entity.CarEvaluateTagConf;
import com.wintop.ms.carauction.mapper.read.ICarEvaluateTagConfReadDao;
import com.wintop.ms.carauction.mapper.write.ICarEvaluateTagConfWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarEvaluateTagConfModel {

    @Autowired
    private ICarEvaluateTagConfReadDao readDao;

    @Autowired
    private ICarEvaluateTagConfWriteDao writeDao;
    /**
     * 查询评价标签配置列表
     */
    public List<CarEvaluateTagConf> queryCarEvaluateTagConfList(Map<String,Object> map){
        return  readDao.queryCarEvaluateTagConfList(map);
    }

    /**
     * 通过id进行查询
     * @param id
     * @return
     */
    public CarEvaluateTagConf selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    /**
     * 查询子节点数据
     * @param map
     * @return
     */
    public List<String> selectChildrenTagConfList(Map<String, Object> map) {
        return readDao.selectChildrenTagConfList(map);
    }

    /**
     * 插入数据
     * @param carEvaluateTagConf
     * @return
     */
    public Integer insertSelective(CarEvaluateTagConf carEvaluateTagConf) {
        return writeDao.insertSelective(carEvaluateTagConf);
    }

    /**
     * 修改数据
     * @param carEvaluateTagConf
     * @return
     */
    public Integer updateByPrimaryKeySelective(CarEvaluateTagConf carEvaluateTagConf){
        return writeDao.updateByPrimaryKeySelective(carEvaluateTagConf);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(Long id){
        return writeDao.deleteByPrimaryKey(id);
    }


    public Integer deleteByCondtion(Map<String,Object> map){
        return writeDao.deleteByCondtion(map);
    }
}
