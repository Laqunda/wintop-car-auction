package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarAutoAuction;
import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.mapper.read.ICarAutoReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoAuctionWriteDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoLogWriteDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoWriteDao;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class CarAutoModel {
    @Resource
    private ICarAutoReadDao readDao;
    @Resource
    private ICarAutoWriteDao writeDao;
    @Autowired
    private ICarAutoAuctionWriteDao auctionWriteDao;
    @Autowired
    private ICarAutoLogWriteDao autoLogWriteDao;


    private IdWorker idWorker = new IdWorker(10);
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
     * 零售订单列表
     */
    public List<CarAuto> selectRetailForExample(Map<String, Object> map) {
        return readDao.selectRetailForExample(map);
    }

    /**
     * 零售订单列表总数量
     */
    public Integer selectRetailForCount(Map<String, Object> map) {
        return readDao.selectRetailForCount(map);
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
     * 库存管理--（零售[已售]）
     */
    public Integer selectCarAutoForSaleCount(Map<String, Object> map){
        return readDao.selectCarAutoForSaleCount(map);
    }

    /**
     * 库存管理--（线上拍[车辆库存、审批状态、竞价状态、竞价结果]、现场拍[车辆库存、审批状态、竞价状态、竞价结果]）
     */
    public Integer selectCarAutoCount(Map<String, Object> map){
        return readDao.selectCarAutoCount(map);
    }

    public List<CarAuto> selectUserOrderList(Map<String, Object> map){
        return  readDao.selectUserOrderList(map);
    }

    public int selectCountById(Long userId){
        return readDao.selectCountById(userId);
    }

    /**
     * 待我审批车辆条数
     * @param map
     * @return
     */
    public int selectCarAutoApprovalCount(Map<String, Object> map){
        return readDao.selectCarAutoApprovalCount(map);
    }

    /**
     * 待我审批车辆列表
     * @param map
     * @return
     */
    public List<CarAuto> selectCarAutoApprovalList(Map<String, Object> map){
        return readDao.selectCarAutoApprovalList(map);
    }
    /**
     * 更新车辆发拍信息
     * @param autoAuction
     */
    public void updateAutoData(CarAutoAuction autoAuction){
        Long autoAuctionId = idWorker.nextId();
        CarAuto carAuto = new CarAuto();
        carAuto.setId(autoAuction.getAutoId());
        carAuto.setStatus(CarStatusEnum.DRAFT.value());
        carAuto.setAutoAuctionId(autoAuctionId);
        carAuto.setTransferFlag("0");
        writeDao.updateByPrimaryKeySelective(carAuto);
        CarAuto auto = readDao.selectByPrimaryKey(carAuto.getId());
        //重新插入一条竞拍信息
        autoAuction.setAuctionType("1");
        autoAuction.setStatus("1");
        autoAuction.setAuctionStartTime(null);
        autoAuction.setAuctionEndDefaultTime(null);
        autoAuction.setAuctionEndTime(null);
        autoAuction.setTopPricerId(null);
        autoAuction.setTopBidPrice(null);
        autoAuction.setTopBidTime(null);
        autoAuction.setCreateTime(new Date());
        autoAuction.setCreatePerson(auto.getCreateUser());
        autoAuction.setId(autoAuctionId);
        auctionWriteDao.insert(autoAuction);
        //保存log日志
        CarAutoLog carAutoLog = new CarAutoLog();
        carAutoLog.setId(idWorker.nextId());
        carAutoLog.setAutoId(auto.getId());
        carAutoLog.setUserType("2");
        carAutoLog.setStatus(CarStatusEnum.DRAFT.value());
        carAutoLog.setTime(new Date());
        carAutoLog.setMsg("现场拍流拍后转车辆草稿");
        autoLogWriteDao.insert(carAutoLog);
    }
}