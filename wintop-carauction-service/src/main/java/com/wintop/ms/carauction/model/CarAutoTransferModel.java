package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAutoTransfer;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoTransferReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoTransferWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CarAutoTransferModel {
    @Resource
    private ICarAutoTransferReadDao readDao;
    @Resource
    private ICarAutoTransferWriteDao writeDao;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoTransferModel.class);

    public int countByExample(Criteria example) {
        int count = this.readDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CarAutoTransfer selectByPrimaryKey(Long id) {
        return this.readDao.selectByPrimaryKey(id);
    }

    public List<CarAutoTransfer> selectByExample(Criteria example) {
        return this.readDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.writeDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CarAutoTransfer record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarAutoTransfer record) {
        return this.writeDao.updateByPrimaryKey(record);
    }

    public int insertSelective(CarAutoTransfer record) {
        return this.writeDao.insert(record);
    }

    /**
     *根据auto_id和order_id查询有关数据
     */
    public CarAutoTransfer selectByOrderId(Long orderId, Long autoId) {
        return this.readDao.selectByOrderId(orderId,autoId);
    }
    /**
     * 交接完成
     */
    public int insertHandOver(CarAutoTransfer record) {
        return this.writeDao.insert(record);
    }
    /**
     * 上传手续
     */
    public int insertUpload(CarAutoTransfer record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 交挡
     */
    public int insertToStop(CarAutoTransfer record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 出牌
     */
    public int insertPlay(CarAutoTransfer record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 提挡
     */
    public int insertLiftBlock(CarAutoTransfer record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 确定过户事宜
     */
    public int insertDetermine(CarAutoTransfer record) {
        return this.writeDao.updateByPrimaryKeySelective(record);
    }
    /**
     * 根据条件查询过户流程信息
     */
    public List<CarAutoTransfer> selectByParam(Map<String,Object> map) {
        return this.readDao.selectByParam(map);
    }

    /**
     * 根据条件查询过户流程信息数量
     */
    public int selectCount(Map<String,Object> map){
        return this.readDao.selectCount(map);
    }

    /**
     * 根据orderId更新
     * @param autoTransfer
     * @return
     */
    public int updateByOrderId(CarAutoTransfer autoTransfer){
        return writeDao.updateByOrderId(autoTransfer);
    }
}