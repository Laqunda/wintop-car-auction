package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarChaboshiLog;

import java.util.Map;

/**
 * 查博士日志 数据层
 *
 * @author ruoyi
 * @date 2019-05-08
 */
public interface CarChaboshiLogWriteDao {
    /**
     * 新增查博士日志
     *
     * @param carChaboshiLog 查博士日志信息
     * @return 结果
     */
    public int insertCarChaboshiLog(CarChaboshiLog carChaboshiLog);

    /**
     * 修改查博士日志
     *
     * @param map 查博士日志信息
     * @return 结果
     */
    public int updateCarChaboshiLog(Map<String, Object> map);

    /**
     * 删除查博士日志
     *
     * @param id 查博士日志ID
     * @return 结果
     */
    public int deleteCarChaboshiLogById(Long id);

    /**
     * 批量删除查博士日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarChaboshiLogByIds(String[] ids);

    public int updateIsOpen(Map map);
}