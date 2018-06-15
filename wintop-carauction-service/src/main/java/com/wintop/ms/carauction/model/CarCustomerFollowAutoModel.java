package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;
import com.wintop.ms.carauction.mapper.read.ICarCustomerFollowAutoReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerFollowAutoWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @date 2018-02-27
 */
@Repository
public class CarCustomerFollowAutoModel {

    @Resource
    private ICarCustomerFollowAutoReadDao readDao;
    @Resource
    private ICarCustomerFollowAutoWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerFollowAuto selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerFollowAuto record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerFollowAuto record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerFollowAuto record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerFollowAuto record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 收藏接口
     */
    public int insertCustomerCollection(CarCustomerFollowAuto record){
        return writeDao.insertCustomerCollection(record);
    }
    /**
     * 取消收藏接口
     */
    public int deleteCustomerCollection(Map<String,Object> map){
        return writeDao.deleteCustomerCollection(map);
    }

    /**
     * 查询用户现场\线上关注车辆列表
     * @param map
     * @return
     */
    public List<CarCustomerFollowAuto> queryUserFollowList(Map<String,Object> map){
        return readDao.queryUserFollowList(map);
    }

    /**
     * 查询用户现场\线上关注车辆列表数量
     * @param map
     * @return
     */
    public Integer queryUserFollowCount(Map<String,Object> map){
        return readDao.queryUserFollowCount(map);
    }

    /**
     *根据条件查询关注的车辆数量
     */
    public int selectFollowCount(Map<String,Object> map){
        return readDao.selectFollowCount(map);
    }

    /**
     * 根据auto_id和user_id查询数据
     */
    public CarCustomerFollowAuto selectCustomerFollow(Long autoId,Long userId){
        return readDao.selectCustomerFollow(autoId,userId);
    }
}