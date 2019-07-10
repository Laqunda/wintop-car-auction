package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarAssessLog;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.model.CarAssessLogModel;
import com.wintop.ms.carauction.service.ICarAssessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 评估日志 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessLogServiceImpl implements ICarAssessLogService {
    @Autowired
    private CarAssessLogModel model;

    /**
     * 查询评估日志信息
     *
     * @param id 评估日志ID
     * @return 评估日志信息
     */
    @Override
    public CarAssessLog selectCarAssessLogById(Long id) {
        return model.selectCarAssessLogById(id);
    }

    /**
     * 查询评估日志列表
     *
     * @param carAssessLog 评估日志信息
     * @return 评估日志集合
     */
    @Override
    public List<CarAssessLog> selectCarAssessLogList(CarAssessLog carAssessLog) {
        return model.selectCarAssessLogList(carAssessLog);
    }

    /**
     * 新增评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
    @Override
    public int insertCarAssessLog(CarAssessLog carAssessLog) {
        return model.insertCarAssessLog(carAssessLog);
    }

    /**
     * 修改评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
    @Override
    public int updateCarAssessLog(CarAssessLog carAssessLog) {
        return model.updateCarAssessLog(carAssessLog);
    }

    /**
     * 删除评估日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarAssessLogByIds(String ids) {
        return model.deleteCarAssessLogByIds(ids);
    }

    @Override
    public int selectAssessCount(CarAssessLog carAssessLog) {
        return model.selectAssessCount(carAssessLog);
    }

    @Override
    public void saveLog(CarManagerUser managerUser, String msg, long log_id,long assessId) {
        CarAssessLog log = new CarAssessLog();
        log.setId(log_id);
        log.setAssessId(assessId);
        log.setCreatePerson(managerUser.getId());
        log.setLogMsg(msg);
        //状态 1提交申请，2审核通过，-1审核不通过 3审核撤销
        log.setCreateTime(new Date());
        log.setUserName(managerUser.getUserName());
        insertCarAssessLog(log);
    }

}
