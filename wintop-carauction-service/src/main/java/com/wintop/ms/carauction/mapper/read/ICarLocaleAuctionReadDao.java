package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.entity.CommonNameVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarLocaleAuctionReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarLocaleAuction> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarLocaleAuction selectById(Long id);

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    List<CarLocaleAuction> selectAuctionList(Map<String,Object> map);

    /**
     * 场次数量
     * @param map
     * @return
     */
    int selectAuctionCount(Map<String,Object> map);

    /**
     * 场次数量
     * @param map
     * @return
     */
    int largeScreenDisplayCount(Map<String,Object> map);

    /**
     * 获取中心发拍我的车辆列表
     */
    List<CarLocaleAuction> selectCenterRacketCarList(Map<String,Object> map);

    /**
     * 获取中心发拍我的车辆数量
     */
    int selectCenterRacketCarCount(Map<String,Object> map);

    /**
     * 查询所有有效拍卖场次
     * @return
     */
    List<CommonNameVo> selectAllValidAuction();

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    List<CarLocaleAuction> largeScreenDisplay(Map<String,Object> map);

    /**
     * 获取拍卖城市数量
     */
    Integer selectCityCount(Map<String,Object> map);

    /**
     * 首页获取场次数量
     */
    Integer queryAuctionCount(Map<String,Object> map);
    /**
     * 首页开拍场次时间查询
     */
    List<CarLocaleAuction> queryCarLocaleAuctionList(Map<String,Object> map);

    /**
     * 根据日期查询基站的竞拍场次
     * @param stationRealId
     * @return
     */
    CarLocaleAuction selectByStationRealId(@Param("stationRealId") String stationRealId, @Param("auctionDate") String auctionDate);

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    List<CarLocaleAuction> queryAuctionListByParams(Map<String,Object> map);
}