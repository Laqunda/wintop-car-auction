package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarOrderBargain;
import com.wintop.ms.carauction.mapper.read.ICarOrderBargainReadDao;
import com.wintop.ms.carauction.mapper.write.ICarOrderBargainWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarOrderBargainModel {
    @Autowired
    private ICarOrderBargainReadDao readDao;
    @Autowired
    private ICarOrderBargainWriteDao writeDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarOrderBargain> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarOrderBargain selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarOrderBargain carOrderBargain){
        return writeDao.insert(carOrderBargain);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarOrderBargain carOrderBargain){
        return writeDao.updateByIdSelective(carOrderBargain);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarOrderBargain carOrderBargain){
        return writeDao.updateById(carOrderBargain);
    }

    public CarOrderBargain selectBargainByCarId(Map<String,Object> map){
        return readDao.selectBargainByCarId(map);
    }

}
