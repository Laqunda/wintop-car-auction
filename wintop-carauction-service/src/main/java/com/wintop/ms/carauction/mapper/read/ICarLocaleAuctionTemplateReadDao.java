package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarLocaleAuctionTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 现场拍主题表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-23 17:40:13
 */
@Mapper
public interface ICarLocaleAuctionTemplateReadDao {

	CarLocaleAuctionTemplate get(Long id);
	
	List<CarLocaleAuctionTemplate> list(CarLocaleAuctionTemplate carLocaleAuctionTemplate);
	
	int count(CarLocaleAuctionTemplate carLocaleAuctionTemplate);

	List<CarLocaleAuctionTemplate> selectAuctionListForApp(Map<String,Object> map);

}
