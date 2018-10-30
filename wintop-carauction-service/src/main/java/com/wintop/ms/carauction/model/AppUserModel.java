package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.WtAppUser;
import com.wintop.ms.carauction.mapper.read.IWtAppUserReadDao;
import com.wintop.ms.carauction.mapper.write.IWtAppUserWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/2/5.
 */
@Repository
public class AppUserModel {

    @Resource
    private IWtAppUserReadDao readDao;
    @Resource
    private IWtAppUserWriteDao writeDao;

    /***
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    public WtAppUser findByUserName(String userName){
        return readDao.findByUserName(userName);
    }

    /***
     * 根据ID查询用户详情
     * @param appUserId
     * @return
     */
    public WtAppUser findById(Long appUserId) {
        return readDao.findById(appUserId);
    }

    /***
     * 保存用户信息
     * @param appUser
     */
    public Integer saveUser(WtAppUser appUser){
        return writeDao.saveUser(appUser);
    }

    /***
     * 修改用户信息
     * @param appUser
     */
    public Integer updateUser(WtAppUser appUser){
        return writeDao.updateUser(appUser);
    }

    /***
     * 修改密码
     * @param appUser
     */
    public void updatePwd(WtAppUser appUser) {
        writeDao.updatePwd(appUser);
    }
    /**
     * 根据ID查询用户详情
     * @param appUserId
     * @return
     */
    public WtAppUser selectUserById(Long appUserId) {
        return readDao.selectUserById(appUserId);
    }

    /**
     * 根据参数查询用户信息列表
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    public List<WtAppUser> selectListByParam(Map<String,Object> map){
        return  readDao.selectListByParam(map);
    }
    /**
     * 根据参数查询用户数量
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    public Integer selectCountByParam(Map<String,Object> map){
        return readDao.selectCountByParam(map);
    }
    /**
     * 查询没有分组的会员数量
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    public Integer selectCountNoGroup(Map<String,Object> map){
        return readDao.selectCountNoGroup(map);
    }

    /**
     * 更改用户状态
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    public Integer updateUserStatus(WtAppUser appUser){
        return writeDao.updateUserStatus(appUser);
    }
    /**
     * 批量修改用户级别
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:map
     */
    public Integer batchUpdateUserLevel(Map<String,Object> map){
        return writeDao.batchUpdateUserLevel(map);
    }
    /**
     * 查询会员车商号是否重复
     * @Author:zhangzijuan
     * @param userId
     * @param userNum
     * @return
     */
    public Integer selectUserNumIsRepeat(Long userId,String userNum){
        return readDao.selectUserNumIsRepeat(userId,userNum);
    }
    /**
     * 查询会员详情信息
     * @Author:zhangzijuan
     * @param userId
     * @return
     */
    public WtAppUser getUserInfoById(Long userId){
        return readDao.getUserInfoById(userId);
    }

    /**
     * 查询会员详情信息
     * @Author:zhangzijuan
     * @param userCode
     * @return
     */
    public CommonNameVo getUserInfoByCode(String userCode){
        return readDao.getUserInfoByCode(userCode);
    }

    /**
     * 查询可以出价的用户
     * @Author:zhangzijuan
     * @return
     */
    public List<CommonNameVo> selectAllUserForSelect(){
        return readDao.selectAllUserForSelect();
    }

    /**
     * 根据拍牌号查询用户
     * @param map
     * @return
     */
    public WtAppUser selectUserByAuctionPlateNum(Map<String,Object> map){
        return readDao.selectUserByAuctionPlateNum(map);
    }

    public WtAppUser selectUserByMobile(Map<String,Object> map){
        return readDao.selectUserByMobile(map);
    }

    public Integer updateUserInfo(WtAppUser appUser){
        return  writeDao.updateUserInfo(appUser);
    }
}