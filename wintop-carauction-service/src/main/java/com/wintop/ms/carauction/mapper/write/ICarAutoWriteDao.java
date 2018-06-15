package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoWriteDao {


    /**
     *
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     */
    int insert(CarAuto record);

    /**
     *
     */
    int insertSelective(CarAuto record);


    /**
     *
     */
    int updateByPrimaryKeySelective(CarAuto record);

    /**
     *
     */
    int updateByPrimaryKey(CarAuto record);
}