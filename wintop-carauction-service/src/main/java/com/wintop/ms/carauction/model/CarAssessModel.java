package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAssess;
import com.wintop.ms.carauction.mapper.read.CarAssessReadDao;
import com.wintop.ms.carauction.mapper.write.CarAssessWriteDao;
import com.wintop.ms.carauction.util.Convert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liangtingsen on 2018/2/5.
 */
@Repository
public class CarAssessModel {

    @Resource
    private CarAssessReadDao readDao;
    @Resource
    private CarAssessWriteDao writeDao;

    /**
     * 查询车辆评估信息
     *
     * @param id 车辆评估ID
     * @return 车辆评估信息
     */
    public CarAssess selectCarAssessById(Long id)
    {
        return readDao.selectCarAssessById(id);
    }

    /**
     * 查询车辆评估列表
     *
     * @param carAssess 车辆评估信息
     * @return 车辆评估集合
     */
    public List<CarAssess> selectCarAssessList(CarAssess carAssess)
    {
        return readDao.selectCarAssessList(carAssess);
    }

    /**
     * 新增车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
    public int insertCarAssess(CarAssess carAssess)
    {
        return writeDao.insertCarAssess(carAssess);
    }

    /**
     * 修改车辆评估
     *
     * @param carAssess 车辆评估信息
     * @return 结果
     */
    public int updateCarAssess(CarAssess carAssess)
    {
        return writeDao.updateCarAssess(carAssess);
    }

    /**
     * 删除车辆评估对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarAssessByIds(String ids)
    {
        return writeDao.deleteCarAssessByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据条件查找数量
     *
     * @param carAssess
     */
    public int selectCarAssessCount(CarAssess carAssess) {
       return readDao.selectCarAssessCount(carAssess);
    }
}