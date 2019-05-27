package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarLocaleAuctionTemplate;
import com.wintop.ms.carauction.entity.CarLocaleAuctionWeek;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarLocaleAuctionTemplateService {

    /**
     * 通过条件查询记录，支持分页查询
     *
     * @param carLocaleAuctionTemplate
     * @return
     */
    public List<CarLocaleAuctionTemplate> list(CarLocaleAuctionTemplate carLocaleAuctionTemplate);

    /**
     * 通过条件查询记录数
     *
     * @param carLocaleAuctionTemplate
     * @return
     */
    public int count(CarLocaleAuctionTemplate carLocaleAuctionTemplate);

    /**
     * 通过id查询记录
     *
     * @param id
     * @return
     */
    public CarLocaleAuctionTemplate get(Long id);

    /**
     * 插入记录
     *
     * @param carLocaleAuctionTemplate
     * @return
     */
    public int insertSelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate);

    /**
     * 通过id修改记录
     *
     * @param carLocaleAuctionTemplate
     * @return
     */
    public int updateByPrimaryKeySelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate);

    /**
     * 通过id 删除记录
     *
     * @param id
     * @return
     */
    public int delete(Long id);

}
