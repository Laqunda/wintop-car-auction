package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.AppUserToken;
import com.wintop.ms.carauction.entity.WtAppUser;
import com.wintop.ms.carauction.mapper.read.AppUserTokenReadDao;
import com.wintop.ms.carauction.mapper.read.IWtAppUserReadDao;
import com.wintop.ms.carauction.mapper.write.AppUserTokenWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author zhangzijuan
 * @date 2018-02-08
 */
@Repository
public class AppUserTokenModel {

    @Resource
    private AppUserTokenReadDao readDao;
    @Resource
    private AppUserTokenWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public AppUserToken selectByPrimaryKey(Long id){
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
    public int insert(AppUserToken record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(AppUserToken record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(AppUserToken record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(AppUserToken record){
        return writeDao.updateByPrimaryKey(record);
    }

}