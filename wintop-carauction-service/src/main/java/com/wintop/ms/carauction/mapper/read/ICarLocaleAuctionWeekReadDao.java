package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarLocaleAuctionWeek;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 主题模板关联周
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-24 15:13:55
 */
@Mapper
public interface ICarLocaleAuctionWeekReadDao {

	CarLocaleAuctionWeek get(Long id);
	
	List<CarLocaleAuctionWeek> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

}
