package com.wintop.ms.carauction.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
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

    @Override
    public List<CarEvaluateTagConf> queryEvaluateTagConfList(Map<String, Object> param) {
        return carEvaluateTagConfModel.queryCarEvaluateTagConfList(param);
    }

    /**
     * 通过id进行查询
     * @param id
     * @return
     */
    @Override
    public CarEvaluateTagConf selectByPrimaryKey(Long id){
        return carEvaluateTagConfModel.selectByPrimaryKey(id);
    }
    /**
     * 查询子节点数据
     * @param map
     * @return
     */
    @Override
    public Map<Object, List<Map<String, Object>>> selectChildrenTagConfList(Map<String, Object> map){
        List<String> recordList = carEvaluateTagConfModel.selectChildrenTagConfList(map);
        List<Map<String, Object>> arrayList = Lists.newArrayList();
        for (String record : recordList) {
            List<String> paramList = Splitter.on(",").splitToList(record);
            for (String param : paramList) {
                List<String> valList = Splitter.on("_").splitToList(param);
                Map<String, Object> paramMap = new HashMap<String, Object>() {{
                    put("type", valList.get(0));
                    put("star", valList.get(1));
                    put("pId", valList.get(2));
                    put("id", valList.get(3));
                    put("title", valList.get(4));
                }};
                arrayList.add(paramMap);
            }

        }
        Map<Object, List<Map<String, Object>>> result = arrayList.stream().collect(Collectors.groupingBy(e -> e.get("star")));
        return result;
    }
    /**
     * 插入数据
     * @param carEvaluateTagConf
     * @return
     */
    @Override
    public Integer insertSelective(CarEvaluateTagConf carEvaluateTagConf){
        return carEvaluateTagConfModel.insertSelective(carEvaluateTagConf);
    }
    /**
     * 修改数据
     * @param carEvaluateTagConf
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(CarEvaluateTagConf carEvaluateTagConf){
        return carEvaluateTagConfModel.updateByPrimaryKeySelective(carEvaluateTagConf);
    }
    /**
     * 删除数据
     * @param id
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long id){
        return carEvaluateTagConfModel.deleteByPrimaryKey(id);
    }

    /**
     * 删除数据
     * @param map
     * @return
     */
    @Override
    public Integer deleteByCondition(Map<String,Object> map){
        return carEvaluateTagConfModel.deleteByCondtion(map);
    }
}
