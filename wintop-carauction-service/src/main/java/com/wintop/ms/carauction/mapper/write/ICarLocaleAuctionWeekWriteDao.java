package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarLocaleAuctionWeek;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ICarLocaleAuctionWeekWriteDao {

    int insertSelective(CarLocaleAuctionWeek carLocaleAuctionWeek);

    int insertBatch(@Param("list") List<CarLocaleAuctionWeek> list);

    int updateByPrimaryKeySelective(CarLocaleAuctionWeek carLocaleAuctionWeek);

    int remove(Long id);

    int batchRemove(Map<String,Object> param);
}
