package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarChaboshiVinData;
import com.wintop.ms.carauction.mapper.read.ICarChaboshiVinDataReadDao;
import com.wintop.ms.carauction.mapper.write.ICarChaboshiVinDataWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarChaboshiVinDataModel {

    @Autowired
    private ICarChaboshiVinDataReadDao readDao;

    @Autowired
    private ICarChaboshiVinDataWriteDao writeDao;

    /**
     * 通过订单id 查询记录
     * @param id
     * @return
     */
    public CarChaboshiVinData selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    /**
     * 通过条件查询记录
     * @param param
     * @return
     */
    public List<CarChaboshiVinData> selectByCondition(Map<String, Object> param){
        return readDao.selectByCondition(param);
    }

    /**
     * 通过 id 删除记录
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Long id) {
        return writeDao.deleteByPrimaryKey(id);
    }

    /**
     * 插入记录
     * @param record
     * @return
     */
    public int insertSelective(CarChaboshiVinData record) {
        return writeDao.insertSelective(record);
    }

    /**
     * 修改记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarChaboshiVinData record) {
        return writeDao.updateByPrimaryKeySelective(record);
    }

}
