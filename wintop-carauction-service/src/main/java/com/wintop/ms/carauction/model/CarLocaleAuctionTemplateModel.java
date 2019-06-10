package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarLocaleAuctionTemplate;
import com.wintop.ms.carauction.mapper.read.ICarLocaleAuctionTemplateReadDao;
import com.wintop.ms.carauction.mapper.write.ICarLocaleAuctionTemplateWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CarLocaleAuctionTemplateModel {

    @Autowired
    private ICarLocaleAuctionTemplateReadDao readDao;

    @Autowired
    private ICarLocaleAuctionTemplateWriteDao writeDao;

    /**
     * 通过条件查询记录，支持分页查询
     * @param carLocaleAuctionTemplate
     * @return
     */
    public List<CarLocaleAuctionTemplate> list(CarLocaleAuctionTemplate carLocaleAuctionTemplate) {
        return readDao.list(carLocaleAuctionTemplate);
    }

    /**
     * 通过条件查询记录数
     * @param carLocaleAuctionTemplate
     * @return
     */
    public int count(CarLocaleAuctionTemplate carLocaleAuctionTemplate){
        return readDao.count(carLocaleAuctionTemplate);
    }

    /**
     * 通过id查询记录
     * @param id
     * @return
     */
    public CarLocaleAuctionTemplate get(Long id) {
        return readDao.get(id);
    }

    /**
     * 插入记录
     * @param carLocaleAuctionTemplate
     * @return
     */
    public int insertSelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate) {
        return writeDao.insertSelective(carLocaleAuctionTemplate);
    }

    /**
     * 通过id修改记录
     * @param carLocaleAuctionTemplate
     * @return
     */
    public int updateByPrimaryKeySelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate) {
        if (isNotEmpty(carLocaleAuctionTemplate.getId())) {
            return writeDao.updateByPrimaryKeySelective(carLocaleAuctionTemplate);
        }
        return 0;
    }

    /**
     * 通过id 删除记录
     * @param id
     * @return
     */
    public int delete(Long id){
        if (isNotEmpty(id)) {
            return writeDao.delete(id);
        }
        return 0;
    }

    public List<CarLocaleAuctionTemplate> selectAuctionListForApp(Map<String, Object> map){
        return readDao.selectAuctionListForApp(map);
    }

    private static <T> boolean isNotEmpty(T t) {
        return Optional.ofNullable(t).isPresent();
    }

}
