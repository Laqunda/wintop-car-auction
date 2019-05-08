package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarAssessLog;
import com.wintop.ms.carauction.entity.CarManagerUser;

import java.util.List;

/**
 * 评估日志 服务层
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public interface ICarAssessLogService 
{
	/**
     * 查询评估日志信息
     * 
     * @param id 评估日志ID
     * @return 评估日志信息
     */
	public CarAssessLog selectCarAssessLogById(Long id);
	
	/**
     * 查询评估日志列表
     * 
     * @param carAssessLog 评估日志信息
     * @return 评估日志集合
     */
	public List<CarAssessLog> selectCarAssessLogList(CarAssessLog carAssessLog);

	/**
     * 新增评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
	public int insertCarAssessLog(CarAssessLog carAssessLog);

	/**
     * 修改评估日志
     *
     * @param carAssessLog 评估日志信息
     * @return 结果
     */
	public int updateCarAssessLog(CarAssessLog carAssessLog);
		
	/**
     * 删除评估日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarAssessLogByIds(String ids);

    int selectAssessCount(CarAssessLog carAssessLog);

    void saveLog(CarManagerUser managerUser, String msg, long log_id, long assessId);
}
