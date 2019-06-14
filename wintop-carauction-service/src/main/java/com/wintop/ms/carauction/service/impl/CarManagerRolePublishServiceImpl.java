package com.wintop.ms.carauction.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.entity.CarManagerRolePublish;
import com.wintop.ms.carauction.model.CarManagerRolePublishModel;
import com.wintop.ms.carauction.service.ICarManagerRolePublishService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarManagerRolePublishServiceImpl implements ICarManagerRolePublishService {
    private IdWorker idWorker = new IdWorker(10);
    @Autowired
    private CarManagerRolePublishModel carManagerRolePublishModel;

    @Override
    public CarManagerRolePublish selectByPrimaryKey(Long id) {
        return carManagerRolePublishModel.selectByPrimaryKey(id);
    }

    @Override
    public List<CarManagerRolePublish> selectByCondition(Map<String, Object> param) {
        return carManagerRolePublishModel.selectByCondition(param);
    }

    @Override
    public Map<Long, List<CarManagerRolePublish>> selectTreeByCondition(Map<String, Object> param) {
        Map<Long, List<CarManagerRolePublish>> resultList = Maps.newHashMap();
        List<CarManagerRolePublish> publishList = carManagerRolePublishModel.selectByCondition(param);
        if (CollectionUtils.isNotEmpty(publishList)) {
            resultList  = publishList.stream().collect(Collectors.groupingBy(CarManagerRolePublish::getTypeId));
        }
        return resultList;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return carManagerRolePublishModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CarManagerRolePublish record) {
        return carManagerRolePublishModel.insertSelective(record);
    }

    @Override
    public int insert(CarManagerRolePublish record) {
        return carManagerRolePublishModel.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CarManagerRolePublish record) {
        return carManagerRolePublishModel.updateByPrimaryKeySelective(record);
    }

    @Override
    public void saveOrUpdate(List<CarManagerRolePublish> recordList) {

        List<CarManagerRolePublish> tempList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(recordList)) {
            // 批量删除数据
            deleteBatchList(recordList.get(0).getManagerId());
            // 批量保存数据
            saveBatchList(recordList);
        }
    }

    private List<Integer> saveBatchList(List<CarManagerRolePublish> recordList) {
        List<Integer> countList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(recordList)) {
            for (CarManagerRolePublish record : recordList) {
                record.setId(idWorker.nextId());
                int count = this.insertSelective(record);
                if (count == 1){
                    countList.add(count);
                }
            }
        }
        return countList;
    }

    private void deleteBatchList(Long managerId) {
        carManagerRolePublishModel.deleteByCondition(Collections.singletonMap("managerId", managerId));
    }




}
