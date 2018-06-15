package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.mapper.read.ICarLocaleAuctionReadDao;
import com.wintop.ms.carauction.mapper.write.ICarLocaleAuctionWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarLocaleAuctionModel {
    @Autowired
    private ICarLocaleAuctionReadDao readDao;
    @Autowired
    private ICarLocaleAuctionWriteDao writeDao;
    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarLocaleAuction> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarLocaleAuction selectById(Long id){
        return readDao.selectById(id);
    }
    /**
     * 根据主键删除记录
     */
    public int deleteById(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public int insert(CarLocaleAuction carAuction){
        return writeDao.insert(carAuction);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarLocaleAuction carAuction){
        return writeDao.updateByIdSelective(carAuction);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarLocaleAuction carAuction){
        return writeDao.updateById(carAuction);
    }

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    public List<CarLocaleAuction> selectAuctionList(Map<String,Object> map){
        return readDao.selectAuctionList(map);
    }

    /**
     * 场次数量
     * @param map
     * @return
     */
    public int selectAuctionCount(Map<String,Object> map){
        return readDao.selectAuctionCount(map);
    }

    /**
     * 场次数量
     * @param map
     * @return
     */
    public int largeScreenDisplayCount(Map<String,Object> map){
        return readDao.largeScreenDisplayCount(map);
    }

    /**
     * 获取中心发拍我的车辆列表
     */
    public List<CarLocaleAuction> selectCenterRacketCarList(Map<String,Object> map){
        return readDao.selectCenterRacketCarList(map);
    }
    /**
     * 获取中心发拍我的车辆数量
     */
    public int selectCenterRacketCarCount(Map<String,Object> map){
        return readDao.selectCenterRacketCarCount(map);
    }

    /**
     * 查询所有有效拍卖场次
     * @return
     */
    public List<CommonNameVo> selectAllValidAuction(){
        return readDao.selectAllValidAuction();
    }

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    public List<CarLocaleAuction> largeScreenDisplay(Map<String,Object> map){
        return readDao.largeScreenDisplay(map);
    }
    /**
     * 获取拍卖城市数量
     */
    public Integer selectCityCount(Map<String,Object> map){
        return readDao.selectCityCount(map);
    }

    /**
     * 首页获取场次数量
     */
    public Integer queryAuctionCount(Map<String,Object> map){
        return readDao.queryAuctionCount(map);
    }

    /**
     * 首页开拍场次时间查询
     */
    public List<CarLocaleAuction> queryCarLocaleAuctionList(Map<String,Object> map){
        return readDao.queryCarLocaleAuctionList(map);
    }
}
