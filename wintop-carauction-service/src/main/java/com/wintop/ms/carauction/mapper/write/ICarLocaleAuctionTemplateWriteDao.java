package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarLocaleAuctionTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 现场拍主题表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-23 17:40:13
 */
@Mapper
public interface ICarLocaleAuctionTemplateWriteDao {

	int insertSelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate);
	
	int updateByPrimaryKeySelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate);

    int delete(Long id);
}
