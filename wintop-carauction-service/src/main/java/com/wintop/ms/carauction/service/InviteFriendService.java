package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.InviteFriend;

/**
 * Created by 12991 on 2018/3/10.
 */
public interface InviteFriendService {
    ServiceResult<InviteFriend> selectByParam();
}
