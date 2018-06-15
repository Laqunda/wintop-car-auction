package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarAgentCompanyModel;
import com.wintop.ms.carauction.model.CarAgentCompanyStoreModel;
import com.wintop.ms.carauction.model.CarStoreModel;
import com.wintop.ms.carauction.service.ICarAgentCompanyService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CarAgentCompanyServiceImpl implements ICarAgentCompanyService {
    @Autowired
    private CarAgentCompanyModel model;
    @Autowired
    private CarAgentCompanyStoreModel companyStoreModel;
    @Autowired
    private CarStoreModel storeModel;

    private IdWorker idWorker = new IdWorker(10);

    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarAgentCompany> selectByExample(Map<String, Object> map) {
        List<CarAgentCompany> list = model.selectByExample(map);
        for(CarAgentCompany company:list){
            List<CommonNameVo> storeList = companyStoreModel.selectAllStore(company.getId());
            company.setStoreList(storeList);
        }
        return list;
    }

    @Override
    public CarAgentCompany selectById(Long id) {
        CarAgentCompany carAgentCompany = model.selectById(id);
        //List<CommonNameVo> storeList = storeModel.selectAllStore();
        List<CommonNameVo> companyStoreList = companyStoreModel.selectAllStore(id);
        /*for(CommonNameVo store:storeList){
            store.setChecked("0");
            for(CommonNameVo companyStore:companyStoreList){
                if(store.getId().longValue()==companyStore.getId().longValue()){
                    store.setChecked("1");
                    break;
                }
            }
        }*/
        carAgentCompany.setStoreList(companyStoreList);
        return  carAgentCompany;
    }

    @Override
    public Integer deleteById(Long id) {
        Integer count = model.deleteById(id);
        return count;
    }

    @Override
    @Transactional
    public Integer insert(CarAgentCompany carAgentCompany) {
        carAgentCompany.setId(idWorker.nextId());
        companyStoreModel.saveBatchStore(carAgentCompany.getId(),carAgentCompany.getStoreIds());
        Integer count = model.insert(carAgentCompany);
        return count;
    }

    @Override
    @Transactional
    public Integer updateByIdSelective(CarAgentCompany carAgentCompany) {
        Integer count = model.updateByIdSelective(carAgentCompany);
        if(count==1){
            companyStoreModel.deleteByCompanyId(carAgentCompany.getId());
            companyStoreModel.saveBatchStore(carAgentCompany.getId(),carAgentCompany.getStoreIds());
        }
        return count;
    }

    @Override
    public Integer updateById(CarAgentCompany carAgentCompany) {
        Integer count = model.updateById(carAgentCompany);
        return count;
    }

    /**
     * 删除记录
     */
    @Override
    @Transactional
    public int updateCenterDel(Long delPerson,Long id){
        companyStoreModel.deleteByCompanyId(id);
        CarAgentCompany company = new CarAgentCompany();
        company.setId(id);
        company.setStatus("3");
        company.setDelTime(new Date());
        company.setDelPerson(delPerson);
        return model.updateByIdSelective(company);
    }

    /**
     * 查询所有代办公司字典表
     * @return
     */@Override
    public List<CommonNameVo> selectAllAgentCompany(){
        return model.selectAllAgentCompany();
    }

    /**
     * 根据条件查询代办公司
     */
    public List<CarAgentCompany> selectCompanyByDepId(Map<String,Object> map){
        return model.selectCompanyByDepId(map);
    }
}
