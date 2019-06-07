package com.wintop.ms.carauction.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.entity.CarLocaleAuctionTemplate;
import com.wintop.ms.carauction.entity.CarLocaleAuctionWeek;
import com.wintop.ms.carauction.model.CarLocaleAuctionTemplateModel;
import com.wintop.ms.carauction.model.CarLocaleAuctionWeekModel;
import com.wintop.ms.carauction.model.WtRegionModel;
import com.wintop.ms.carauction.service.ICarLocaleAuctionTemplateService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("carLocaleAuctionTemplateService")
public class CarLocaleAuctionTemplateServiceImpl implements ICarLocaleAuctionTemplateService {

    private static final String UNDER_LINE = "2";
    private IdWorker idWorker = new IdWorker(10);
    @Autowired
    private CarLocaleAuctionTemplateModel carLocaleAuctionTemplateModel;
    @Autowired
    private WtRegionModel wtRegionModel;
    @Autowired
    private CarLocaleAuctionWeekModel carLocaleAuctionWeekModel;
    /**
     * 通过条件查询记录，支持分页查询
     * @param carLocaleAuctionTemplate
     * @return
     */
    @Override
    public List<CarLocaleAuctionTemplate> list(CarLocaleAuctionTemplate carLocaleAuctionTemplate) {
        List<CarLocaleAuctionTemplate> list = carLocaleAuctionTemplateModel.list(carLocaleAuctionTemplate);
        for (int i = 0; i < list.size(); i++) {
            Long regionId = list.get(i).getRegionId();
            if (isNotEmpty(regionId)) {
                list.get(i).setCityName(wtRegionModel.findById(regionId).getName());
                List<CarLocaleAuctionWeek> carLocaleAuctionWeekList = carLocaleAuctionWeekModel.list(Collections.singletonMap("templateId", list.get(i).getId()));
                list.get(i).setWeekList(carLocaleAuctionWeekList);
            }
        }
        return list;
    }
    /**
     * 通过条件查询记录数
     * @param carLocaleAuctionTemplate
     * @return
     */
    @Override
    public int count(CarLocaleAuctionTemplate carLocaleAuctionTemplate) {
        return carLocaleAuctionTemplateModel.count(carLocaleAuctionTemplate);
    }
    /**
     * 通过id查询记录
     * @param id
     * @return
     */
    @Override
    public CarLocaleAuctionTemplate get(Long id) {
        CarLocaleAuctionTemplate carLocaleAuctionTemplate = carLocaleAuctionTemplateModel.get(id);
        Long regionId = carLocaleAuctionTemplate.getRegionId();
        if (isNotEmpty(regionId)) {
            carLocaleAuctionTemplate.setCityName(wtRegionModel.findById(regionId).getName());
            List<CarLocaleAuctionWeek> carLocaleAuctionWeekList = carLocaleAuctionWeekModel.list(Collections.singletonMap("templateId", carLocaleAuctionTemplate.getId()));
            carLocaleAuctionTemplate.setWeekList(carLocaleAuctionWeekList);
        }
        return carLocaleAuctionTemplate;
    }

    /**
     * 插入记录
     * @param carLocaleAuctionTemplate
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate) {
        long templateId = idWorker.nextId();
        carLocaleAuctionTemplate.setId(templateId);
        carLocaleAuctionTemplate.setType(UNDER_LINE);
        int code = carLocaleAuctionTemplateModel.insertSelective(carLocaleAuctionTemplate);

        saveWeek(carLocaleAuctionTemplate.getWeeks(), templateId);
        return code;
    }

    /**
     * 插入记录
     * @return
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public void saveWeek(String weeks, long templateId) {
        List<CarLocaleAuctionWeek> weekSaveList = Lists.newArrayList();
        List<String> weekList = Splitter.on(",").splitToList(weeks);
        for (String themeWeek : weekList) {
            CarLocaleAuctionWeek weekRecord = new CarLocaleAuctionWeek();
            weekRecord.setId(idWorker.nextId());
            weekRecord.setTemplateId(templateId);
            weekRecord.setThemeWeek(themeWeek);
            weekSaveList.add(weekRecord);
        }
        carLocaleAuctionWeekModel.insertBatch(weekSaveList);
    }

    /**
     * 通过id修改记录
     * @param carLocaleAuctionTemplate
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByPrimaryKeySelective(CarLocaleAuctionTemplate carLocaleAuctionTemplate) {
        carLocaleAuctionTemplate.setType(UNDER_LINE);
        int code = carLocaleAuctionTemplateModel.updateByPrimaryKeySelective(carLocaleAuctionTemplate);
        if (carLocaleAuctionTemplate.getStopStartTime() == null || carLocaleAuctionTemplate.getStopEndTime() == null) {
            carLocaleAuctionWeekModel.batchRemove(Collections.singletonMap("templateId",carLocaleAuctionTemplate.getId()));
            saveWeek(carLocaleAuctionTemplate.getWeeks(),carLocaleAuctionTemplate.getId());
        }
        return code;
    }

    /**
     * 通过id 删除记录
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        int code = carLocaleAuctionTemplateModel.delete(id);
        carLocaleAuctionWeekModel.batchRemove(Collections.singletonMap("templateId", id));
        return code;
    }

    @Override
    public ServiceResult<List<CarLocaleAuctionTemplate>> selectAuctionListForApp(Map<String, Object> map) {
        ServiceResult<List<CarLocaleAuctionTemplate>> result=new ServiceResult<>();
        List<CarLocaleAuctionTemplate> carLocaleAuctionTemplates = carLocaleAuctionTemplateModel.selectAuctionListForApp(map);
        result.setSuccess(true);
        result.setResult(carLocaleAuctionTemplates);
        return result;
    }

    private static <T> boolean isNotEmpty(T t) {
        return Optional.ofNullable(t).isPresent();
    }
}
