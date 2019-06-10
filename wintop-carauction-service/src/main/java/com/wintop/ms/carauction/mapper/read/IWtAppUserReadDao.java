package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.WtAppUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/2/5.
 * 用户信息
 */
public interface IWtAppUserReadDao {
    /**根据用户名查询用户信息*/
    WtAppUser findByUserName(@Param("userName") String userName);

    WtAppUser findById(@Param("id") Long appUserId);


    WtAppUser selectUserById(@Param("userId") Long userId);
    /**
     * 根据参数查询用户信息列表
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    List<WtAppUser> selectListByParam(Map<String,Object> map);
    /**
     * 根据参数查询用户数量
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    Integer selectCountByParam(Map<String,Object> map);

    /**
     * 查询没有分组的会员数量
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    Integer selectCountNoGroup(Map<String,Object> map);

    /**
     * 查询会员车商号是否重复
     * @Author:zhangzijuan
     * @param userId
     * @param userNum
     * @return
     */
    Integer selectUserNumIsRepeat(@Param(value = "userId") Long userId,@Param(value = "userNum") String userNum);
    /**
     * 查询会员车拍牌号是否重复
     * @Author:zhangzijuan
     * @param mobile
     * @param auctionPlateNum
     * @return
     */
    Integer selectAuctionPlateNumIsRepeat(@Param(value = "mobile") String mobile,@Param(value = "auctionPlateNum") String auctionPlateNum);

    WtAppUser getUserInfoById(@Param("userId") Long userId);

    CommonNameVo getUserInfoByCode(@Param("customerCode") String customerCode);

    /**
     * 查询可以出价的用户
     * @return
     */
    List<CommonNameVo> selectAllUserForSelect();

    WtAppUser selectUserByAuctionPlateNum(Map<String,Object> map);

    WtAppUser selectUserByMobile(Map<String,Object> map);
}