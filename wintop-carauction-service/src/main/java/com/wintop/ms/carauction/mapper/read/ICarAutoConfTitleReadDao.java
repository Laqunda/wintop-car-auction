package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoConfTitle;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;
import java.util.Map;

public interface ICarAutoConfTitleReadDao {

    int countByExample(Map<String,Object> map);


    List<CarAutoConfTitle> selectByExample(Map<String,Object> map);

    List<CarAutoConfTitle> selectAll(Map map);

    CarAutoConfTitle selectByPrimaryKey(Long id);
}