package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.mapper.read.ICarAutoReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CarAutoModel {
    @Resource
    private ICarAutoReadDao readDao;
    @Resource
    private ICarAutoWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoModel.class);

    public int countByExample(Map<String,Object> map) {
        int count = this.readDao.countByExample(map);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAuto selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAuto> selectByExample(Map<String,Object> map) {
        return this.readDao.selectByExample(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAuto record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAuto record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insert(CarAuto record) {
        return this.writeDao.insert(record);
    }

    public int insertSelective(CarAuto record) {
        return this.writeDao.insertSelective(record);
    }

    /**
     * 车辆信息接口
     * @return
     */
    public CarAuto selectByCarId(Map<String,Object> map){
        return readDao.selectByCarId(map);
    }

    /**
     * 根据条件查询记录
     */
    public List<CarAuto> selectAuctionCarList(Map<String,Object> map){
        return readDao.selectAuctionCarList(map);
    }

    /**
     * 查询线上车辆管理列表
     */
    public List<CarAuto> selectCarList(Map<String, Object> map){
        return readDao.selectCarList(map);
    }
    /**
     * 查询总数量
     * @param map
     * @return
     */
    public int selectAuctionCarCount(Map<String,Object> map){
        return readDao.selectAuctionCarCount(map);
    }

    /**
     * 根据条件查询线上拍的车辆列表
     */
    public List<CarAuto> selectOnlineCarList(Map<String,Object> map){
        return readDao.selectOnlineCarList(map);
    }

    /**
     * 根据条件查询线上拍的车辆总数量
     * @param map
     * @return
     */
    public int selectOnlineCarCount(Map<String,Object> map){
        return readDao.selectOnlineCarCount(map);
    }


    /**
     * 根据条件查询发拍的车辆列表
     */
    public List<CarAuto> selectHairShotCarList(Map<String,Object> map){
        return readDao.selectHairShotCarList(map);
    }

    /**
     * 根据条件查询线发拍的车辆数量
     * @param map
     * @return
     */
    public int selectHairShotCarCount(Map<String,Object> map){
        return readDao.selectHairShotCarCount(map);
    }

    public CarAuto selectMyLastAuto(Map paramMap) {
        return readDao.selectMyLastAuto(paramMap);
    }

    /**
     * @Author 付陈林
     * @Date 2018年3月21号
     * @About 获取所有可以参加竞拍的车辆，1、status为5 等待开拍  2、竞拍类型为线下竞拍  3、未加入到已参加的竞拍列表中
     * */
    public List<CarAuto> getLocaleAuctionCar(String searchParam){
        return readDao.getLocaleAuctionCar(searchParam);
    }

    /**
     *查询所有的车辆列表
     * @Author zhangzijiuan
     * @param map
     * @return
     */
    public List<CarAuto> getAllCarAutoList(Map<String,Object> map){
        return readDao.getAllCarAutoList(map);
    }

    /**
     *查询所有的车辆数目
     * @Author zhangzijiuan
     * @param map
     * @return
     */
    public Integer getAllCarAutoCount(Map<String,Object> map){
        return readDao.getAllCarAutoCount(map);
    }

    /**
     * 查询当日上新车辆数
     */
    public Integer selectDayCarCount(Map<String,Object> map){
        return readDao.selectDayCarCount(map);
    }

    /**
     * 库存管理--（零售[已售]、线上拍[车辆库存、审批状态、竞价状态、竞价结果]、现场拍[车辆库存、审批状态、竞价状态、竞价结果]）
     */
    public Integer selectCarAutoForSaleCount(Map<String, Object> map){
        return readDao.selectCarAutoForSaleCount(map);
    }
}