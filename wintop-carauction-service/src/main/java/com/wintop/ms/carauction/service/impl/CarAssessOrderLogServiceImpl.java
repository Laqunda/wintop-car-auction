package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarAssessOrderLog;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.model.CarAssessOrderLogModel;
import com.wintop.ms.carauction.service.ICarAssessOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 评估采购日志 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessOrderLogServiceImpl implements ICarAssessOrderLogService {
    @Autowired
    private CarAssessOrderLogModel model;

    /**
     * 查询评估采购日志信息
     *
     * @param id 评估采购日志ID
     * @return 评估采购日志信息
     */
    @Override
    public CarAssessOrderLog selectCarAssessOrderLogById(Long id) {
        return model.selectCarAssessOrderLogById(id);
    }

    /**
     * 查询评估采购日志列表
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 评估采购日志集合
     */
    @Override
    public List<CarAssessOrderLog> selectCarAssessOrderLogList(CarAssessOrderLog carAssessOrderLog) {
        return model.selectCarAssessOrderLogList(carAssessOrderLog);
    }

    /**
     * 新增评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
    @Override
    public int insertCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog) {
        return model.insertCarAssessOrderLog(carAssessOrderLog);
    }

    /**
     * 修改评估采购日志
     *
     * @param carAssessOrderLog 评估采购日志信息
     * @return 结果
     */
    @Override
    public int updateCarAssessOrderLog(CarAssessOrderLog carAssessOrderLog) {
        return model.updateCarAssessOrderLog(carAssessOrderLog);
    }

    /**
     * 删除评估采购日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarAssessOrderLogByIds(String ids) {
        return model.deleteCarAssessOrderLogByIds(ids);
    }

    @Override
    public int selectAssessOrderCount(CarAssessOrderLog carAssessOrderLog) {
        return model.selectAssessOrderCount(carAssessOrderLog);
    }

    @Override
    public void saveOrderLog(CarManagerUser managerUser, String msg, String status,long orderlog_id,long orderId) {
        CarAssessOrderLog log = new CarAssessOrderLog();
        log.setId(orderlog_id);
        log.setAssessOrderId(orderId);
        log.setCreatePerson(managerUser.getId());
        log.setLogMsg(msg);
        //状态 1提交申请，2审核通过，-1审核不通过 3审核撤销
        log.setStatus(status);
        if("1".equals(status)){
            log.setStatusCn("提交申请");
        }else if("2".equals(status)){
            log.setStatusCn("审核通过");
        }else if("-1".equals(status)){
            log.setStatusCn("审核不通过");
        }else if("3".equals(status)){
            log.setStatusCn("审核撤回");
        }else if("4".equals(status)){
            log.setStatusCn("已付全款");
        }


        log.setCreateTime(new Date());
        log.setUserMobile(managerUser.getUserPhone());
        log.setUserName(managerUser.getUserName());

        insertCarAssessOrderLog(log);
    }

    @Override
    public int selectCountWaitByParams(Map<String,Object> map) {
        return model.selectCountWaitByParams(map);
    }

    @Override
    public int selectCountEndByUserId(Long userId) {
        return model.selectCountEndByUserId(userId);
    }

    @Override
    public List<CarAssessOrderLog> selectWaitOrderList(Map<String,Object> map){
        return model.selectWaitOrderList(map);
    }
    @Override
    public List<CarAssessOrderLog> selectEndOrderList(Map<String,Object> map){
        return model.selectEndOrderList(map);
    }

}
