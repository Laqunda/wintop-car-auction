package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.InviteFriend;
import com.wintop.ms.carauction.mapper.read.InviteFriendReadDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by 12991 on 2018/3/10.
 */
@Repository
public class InviteFriendModel {
    @Resource
    private InviteFriendReadDao inviteFriendReadDao;

    public InviteFriend selectByParam(){
        return inviteFriendReadDao.selectByParam();
    }
}
