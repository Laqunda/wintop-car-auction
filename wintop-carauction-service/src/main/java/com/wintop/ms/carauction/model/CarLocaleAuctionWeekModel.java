package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarLocaleAuctionWeek;
import com.wintop.ms.carauction.mapper.read.ICarLocaleAuctionWeekReadDao;
import com.wintop.ms.carauction.mapper.write.ICarLocaleAuctionWeekWriteDao;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class CarLocaleAuctionWeekModel {

    @Autowired
    private ICarLocaleAuctionWeekReadDao readDao;

    @Autowired
    private ICarLocaleAuctionWeekWriteDao writeDao;

    /**
     * 通过templateId 进行查询
     * @param id
     * @return
     */
    public CarLocaleAuctionWeek get(Long id) {
        return readDao.get(id);
    }

    /**
     * 通过条件进行查询
     * @param map
     * @return
     */
    public List<CarLocaleAuctionWeek> list(Map<String, Object> map) {
        if (MapUtils.isEmpty(map)) {
            return Collections.emptyList();
        }
        return readDao.list(map);
    }

    /**
     * 通过条件查询数量
     * @param map
     * @return
     */
    public int count(Map<String, Object> map) {
        return readDao.count(map);
    }

    /**
     * 保存数据
      * @param carLocaleAuctionWeek
     * @return
     */
    public int save(CarLocaleAuctionWeek carLocaleAuctionWeek){
        return writeDao.insertSelective(carLocaleAuctionWeek);
    }

    /**
     * 批量保存数据
     * @param list
     * @return
     */
    public int insertBatch(@Param("list") List<CarLocaleAuctionWeek> list) {
        return writeDao.insertBatch(list);
    }

    /**
     * 修改数据
     * @param carLocaleAuctionWeek
     * @return
     */
    public int update(CarLocaleAuctionWeek carLocaleAuctionWeek) {
        return writeDao.updateByPrimaryKeySelective(carLocaleAuctionWeek);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public int remove(Long id) {
        return writeDao.remove(id);
    }

    /**
     * 批量删除数据
     * @param param
     * @return
     */
    public int batchRemove(Map<String,Object> param) {
        return writeDao.batchRemove(param);
    }
}
