package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.InviteFriend;
import com.wintop.ms.carauction.model.InviteFriendModel;
import com.wintop.ms.carauction.service.InviteFriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 12991 on 2018/3/10.
 */
@Service("inviteFriendService")
public class InviteFriendServiceImpl implements InviteFriendService {
    @Resource
    private InviteFriendModel inviteFriendModel;
    private static final Logger logger = LoggerFactory.getLogger(InviteFriendServiceImpl.class);

    @Override
    public ServiceResult<InviteFriend> selectByParam() {
        ServiceResult<InviteFriend> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(inviteFriendModel.selectByParam());
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

}
