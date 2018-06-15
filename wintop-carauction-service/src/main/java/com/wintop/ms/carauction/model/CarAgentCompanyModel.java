package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAgentCompany;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.mapper.read.ICarAgentCompanyReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAgentCompanyWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/16.
 */
@Repository
public class CarAgentCompanyModel {
    @Autowired
    private ICarAgentCompanyReadDao readDao;
    @Autowired
    private ICarAgentCompanyWriteDao writeDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarAgentCompany> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarAgentCompany selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarAgentCompany carAgentCompany){
        return writeDao.insert(carAgentCompany);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarAgentCompany carAgentCompany){
        return writeDao.updateByIdSelective(carAgentCompany);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarAgentCompany carAgentCompany){
        return writeDao.updateById(carAgentCompany);
    }

    /**
     * 查询所有代办公司字典表
     * @return
     */
    public List<CommonNameVo> selectAllAgentCompany(){
        return readDao.selectAllAgentCompany();
    }

    /**
     * 根据条件查询代办公司
     */
    public List<CarAgentCompany> selectCompanyByDepId(Map<String,Object> map){
        return readDao.selectCompanyByDepId(map);
    }
}
