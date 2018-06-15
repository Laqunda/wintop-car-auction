package com.wintop.ms.carauction.service.impl;


import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLevel;
import com.wintop.ms.carauction.entity.CarCustomerLevelPrice;
import com.wintop.ms.carauction.model.CarCustomerLevelModel;
import com.wintop.ms.carauction.model.CarCustomerLevelPriceModel;
import com.wintop.ms.carauction.service.ICarCustomerLevelService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户级别
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerLevelService")
public class CarCustomerLevelServiceImpl implements ICarCustomerLevelService {
    @Resource
    private CarCustomerLevelModel levelModel;

    @Resource
    private CarCustomerLevelPriceModel levelPriceModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerLevelServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerLevel> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerLevel> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelModel.selectByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLevel record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerLevel record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerLevel record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerLevel record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据获取等级查询该等级因交保证金金额
     * @Autor 付陈林
     * @Time  2018-3-7
     * @param customerLevelId
     * @return
     */
    @Override
    public ServiceResult<Map<String,Object>> getDepositAmountByCustomerLevelId(Long customerLevelId) {
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            CarCustomerLevel carCustomerLevel =levelModel.selectByPrimaryKey(customerLevelId);
            //判断级别是否为空！同时保证金额不为0
            if(carCustomerLevel!=null&&carCustomerLevel.getDepositMoney().compareTo(new BigDecimal(0))>0){
                result.setSuccess(true);
                Map<String,Object> resultMap =new HashMap<>();
                resultMap.put("depositMoney",carCustomerLevel.getDepositMoney());
                result.setResult(resultMap);
            }else{
                result.setSuccess(false);
                result.setError("级别查找不到或者保证金未录入全");
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 根据参数查询会员等级列表
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    @Override
    public List<CarCustomerLevel> selectListByParam(Map<String,Object> map){
        return levelModel.selectListByParam(map);
    }

    /**
     *根据参数查询会员等级总数量
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    @Override
    public Integer selectCountByParam(Map<String,Object> map){
        return levelModel.selectCountByParam(map);
    }
/**
 * 保存会员级别
 *@Author:zhangzijuan
 *@date 2018/3/15
 *@param:
 */
    @Transactional
    public ServiceResult<Map<String,Object>> saveLevel(JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        IdWorker idWorker=new IdWorker(10);
        try {
            if(object.get("isDefault")!=null){
                String isDefault=object.getString("isDefault");
                //新增的会员级别是否设置成为了默认，如果设置为默认，先将其他级别设置非默认
                if(StringUtils.isNotBlank(isDefault)){
                    if("1".equals(isDefault)){
                        levelModel.setNoDefault(new HashMap<>());
                    }
                }
            }
            CarCustomerLevel customerLevel=(CarCustomerLevel) JSONObject.toBean(object, CarCustomerLevel.class);
            customerLevel.setId(idWorker.nextId());
            customerLevel.setCreateTime(new Date());
            customerLevel.setCreateManager(object.getLong("createPerson"));
            //添加会员等级
            Integer i=levelModel.insertSelective(customerLevel);
            //获取该会员等级可以加价的金额id
            if(customerLevel!=null && StringUtils.isNotBlank(customerLevel.getFareIds())){
                String [] fareIds= customerLevel.getFareIds().split(",");
                for(String fareId:fareIds){
                    //客户对应的配置出价表
                    CarCustomerLevelPrice levelPrice=new CarCustomerLevelPrice();
                    levelPrice.setId(idWorker.nextId());
                    levelPrice.setLevelId(customerLevel.getId());
                    levelPrice.setFareId(Long.parseLong(fareId));
                    levelPrice.setCreateTime(new Date());
                   levelPrice.setCreateManager(object.getLong("createPerson"));
                    //添加会员等级加价金额
                    levelPriceModel.insertSelective(levelPrice);
                }
            }
            Map<String,Object> map=new HashMap<>();
            map.put("count",i);
            if(i>0){
                result.setResult(map);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());;
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    public ServiceResult<Map<String,Object>> IsOpenLevel(Long levelId){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            CarCustomerLevel level=levelModel.selectByPrimaryKey(levelId);
            /**
             * 查询会员等级信息，如果等级开启就设置为关闭，如果为关闭则开启
             */
            if(level!=null && level.getIsOpen()!=null){
                if("1".equals(level.getIsOpen())){
                        level.setIsOpen("2");
                }else {
                    level.setIsOpen("1");
                }
            }
            Integer i=levelModel.updateByPrimaryKeySelective(level);
            Map<String,Object> map=new HashMap<>();
            map.put("count",i);
            if(i>0){
                result.setResult(map);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 修改会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    @Transactional
    public ServiceResult<Map<String,Object>> updateLevel(JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        IdWorker idWorker=new IdWorker(10);
        try {
            if(object.get("isDefault")!=null){
                String isDefault=object.getString("isDefault");
                //新增的会员级别是否设置成为了默认，如果设置为默认，先将其他级别设置非默认
                if(StringUtils.isNotBlank(isDefault)){
                    if("1".equals(isDefault)){
                        levelModel.setNoDefault(new HashMap<>());
                    }
                }
            }
            CarCustomerLevel customerLevel=(CarCustomerLevel) JSONObject.toBean(object, CarCustomerLevel.class);
            //修改会员等级
            Integer i=levelModel.updateByPrimaryKeySelective(customerLevel);
            //删除之前设置的会员等级加价价格
            if(i>0){
                levelPriceModel.deleteByLevelId(customerLevel.getId());
                //获取该会员等级可以加价的金额id
                if(customerLevel!=null && StringUtils.isNotBlank(customerLevel.getFareIds())){
                    String [] fareIds= customerLevel.getFareIds().split(",");
                    for(String fareId:fareIds){
                        //客户对应的配置出价表
                        CarCustomerLevelPrice levelPrice=new CarCustomerLevelPrice();
                        levelPrice.setId(idWorker.nextId());
                        levelPrice.setLevelId(customerLevel.getId());
                        levelPrice.setFareId(Long.parseLong(fareId));
                        levelPrice.setCreateTime(new Date());
                        levelPrice.setCreateManager(object.getLong("updatePerson"));
                        //添加会员等级加价金额
                        levelPriceModel.insertSelective(levelPrice);
                    }
                }
            }
            Map<String,Object> map=new HashMap<>();
            map.put("count",i);
            if(i>0){
                result.setResult(map);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    @Transactional
    public ServiceResult<Map<String,Object>> deleteLevel(Long levelId){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            CarCustomerLevel level=levelModel.selectByPrimaryKey(levelId);
            if(level!=null){
                //默认级别不允许删除
                if ("1".equals(level.getIsDefault())){
                    result.setError(ResultCode.DELETE_LEVEL_DEFAUIT.strValue(),ResultCode.DELETE_LEVEL_DEFAUIT.getRemark());
                    return result;
                }
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                return result;
            }
            Integer i=levelModel.deleteByPrimaryKey(levelId);
            if(i>0){
                levelPriceModel.deleteByLevelId(levelId);
            }
            Map<String,Object> map=new HashMap<>();
            map.put("count",i);
            if(i>0){
                result.setResult(map);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }



    public ServiceResult<Map<String,Object>> isDefaultLevel(Long levelId){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        Map<String,Object> paramMap=new HashMap<>();
        try {
            CarCustomerLevel level=levelModel.selectByPrimaryKey(levelId);
            /**
             * 查询会员等级信息，如果该等级是默认的就直接设置为不默认，
             * 要将该级别设置为默认级别，先将其他级别设置为不默认
             */
            if(level!=null && level.getIsDefault()!=null){
                if("1".equals(level.getIsDefault())){
                    level.setIsDefault("2");
                }else {
                    //设置其他级别不是默认
                    if(levelModel.setNoDefault(paramMap)>0){
                        level.setIsDefault("1");
                    }
                }
            }
            Integer i=levelModel.updateByPrimaryKeySelective(level);
            Map<String,Object> map=new HashMap<>();
            map.put("count",i);
            if(i>0){
                result.setResult(map);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<CarCustomerLevel> selectLevelById(Map<String,Object> map){
        ServiceResult<CarCustomerLevel> result=new ServiceResult<>();
        try{
            CarCustomerLevel level=levelModel.selectLevelById(map);
            if(level!=null){
                result.setResult(level);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询默认级别
     *@Author:zhangzijuan
     *@date 2018/3/28
     *@param:
     */
    public CarCustomerLevel getDefaultLevel(){
        return levelModel.getDefaultLevel();
    }
}