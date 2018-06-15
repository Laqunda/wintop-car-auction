package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerDeposit;
import com.wintop.ms.carauction.mapper.read.ICarCustomerDepositReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerDepositWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @date 2018-02-08
 */
@Repository
public class CarCustomerDepositModel {

    @Resource
    private ICarCustomerDepositReadDao readDao;
    @Resource
    private ICarCustomerDepositWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerDeposit selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Long id){
        return writeDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    public int insert(CarCustomerDeposit record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerDeposit record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerDeposit record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerDeposit record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 根据用户ID查询保证金余额
     * @Autor 付陈林
     * @Time 2018-3-5
     * @param userId
     * @return
     */
    public BigDecimal getDepositAmountByUserId(Long userId){
        return readDao.getDepositAmountByUserId(userId);
    }
    /**
     * 根据用户ID查询保证金余额
     * @Autor zhangzijuan
     * @Time 2018-3-15
     * @param map
     * @return
     */
    public CarCustomerDeposit selectDepositByUserId(Map<String,Object> map){
        return readDao.selectDepositByUserId(map);
    }
    /**
     * 根据会员id修改会员保证金
     * @param record
     * @return
     */
    public int updateDepositByUserId(CarCustomerDeposit record){
        return  writeDao.updateDepositByUserId(record);
    }


    /**
     * 查询保证金列表
     * @Autor zhangzijuan
     * @param map
     * @return
     */
    public List<CarCustomerDeposit> selectDepositList(Map<String,Object> map){
        return readDao.selectDepositList(map);
    }

    /**
     * 查询保证金总数目
     * @Autor zhangzijuan
     * @param map
     * @return
     */
    public Integer  selectDepositCount(Map<String,Object> map){
        return readDao.selectDepositCount(map);
    }
}