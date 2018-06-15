package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarQuestionClassify;
import com.wintop.ms.carauction.mapper.read.ICarQuestionClassifyReadDao;
import com.wintop.ms.carauction.mapper.write.ICarQuestionClassifyWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
@Repository
public class CarQuestionClassifyModel {
    @Autowired
    private ICarQuestionClassifyReadDao iCarQuestionClassifyReadDao;
    @Autowired
    private ICarQuestionClassifyWriteDao iCarQuestionClassifyWriteDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return iCarQuestionClassifyReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarQuestionClassify> selectByExample(Map<String,Object> map){
        return iCarQuestionClassifyReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarQuestionClassify selectById(Long id){
        return iCarQuestionClassifyReadDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return iCarQuestionClassifyWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarQuestionClassify carQuestionClassify){
        return iCarQuestionClassifyWriteDao.insert(carQuestionClassify);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarQuestionClassify carQuestionClassify){
        return iCarQuestionClassifyWriteDao.updateByIdSelective(carQuestionClassify);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarQuestionClassify carQuestionClassify){
        return iCarQuestionClassifyWriteDao.updateById(carQuestionClassify);
    }

}
