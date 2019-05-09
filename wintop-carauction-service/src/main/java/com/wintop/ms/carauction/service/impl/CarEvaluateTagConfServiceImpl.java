package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarEvaluateTagConf;
import com.wintop.ms.carauction.model.CarEvaluateTagConfModel;
import com.wintop.ms.carauction.service.ICarEvaluateTagConfService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("carEvaluateTagConfService")
public class CarEvaluateTagConfServiceImpl implements ICarEvaluateTagConfService {

    @Resource
    private CarEvaluateTagConfModel carEvaluateTagConfModel;
    @Override
    public Map<String, List<CarEvaluateTagConf>> queryEvaluateTagConfTreeList(Map<String, Object> param) {
        Map<String, List<CarEvaluateTagConf>> resultMapList = new HashMap<>();
        List<CarEvaluateTagConf> recordList = carEvaluateTagConfModel.queryCarEvaluateTagConfList(param);
        Map<Long, List<CarEvaluateTagConf>> groupMapList = recordList.stream().filter(conf-> !conf.getpId().equals(new Long(-1))).collect(Collectors.groupingBy(CarEvaluateTagConf::getpId));
        groupMapList.keySet().forEach(pid->{
            CarEvaluateTagConf carEvaluateTagConf = recordList.stream().filter(conf -> pid.equals(conf.getId())).collect(Collectors.toList()).stream().findFirst().get();
            resultMapList.put(carEvaluateTagConf.getTitle(), groupMapList.get(pid));
        });
        return resultMapList;
    }
}
