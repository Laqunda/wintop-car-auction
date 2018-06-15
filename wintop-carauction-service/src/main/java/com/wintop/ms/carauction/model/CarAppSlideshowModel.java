package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAppSlideshow;
import com.wintop.ms.carauction.mapper.read.ICarAppSlideshowReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAppSlideshowWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
@Repository
public class CarAppSlideshowModel {
    @Autowired
    private ICarAppSlideshowReadDao carAppSlideshowReadDao;
    @Autowired
    private ICarAppSlideshowWriteDao carAppSlideshowWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return carAppSlideshowReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAppSlideshow> selectByExample(Map<String,Object> map){
        return carAppSlideshowReadDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAppSlideshow selectById(Long id){
        return carAppSlideshowReadDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return carAppSlideshowWriteDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAppSlideshow carAppSlideshow){
        return carAppSlideshowWriteDao.insert(carAppSlideshow);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAppSlideshow carAppSlideshow){
        return carAppSlideshowWriteDao.updateByIdSelective(carAppSlideshow);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAppSlideshow carAppSlideshow){
        return carAppSlideshowWriteDao.updateById(carAppSlideshow);
    }

}
