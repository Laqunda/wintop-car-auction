package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.entity.CommonNameVo;

import javax.xml.ws.Service;
import java.util.List;
import java.util.Map;

public interface ICarLocaleAuctionService {
    /**
     * 根据条件查询记录总数
     */
    ServiceResult<Map<String,Object>> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<CarLocaleAuction>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    ServiceResult<CarLocaleAuction> selectById(Long id);
    /**
     * 根据主键删除记录
     */
    ServiceResult<Map<String,Object>> deleteById(Long id,Long userId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Map<String,Object>> insert(CarLocaleAuction carAuction);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Map<String,Object>> updateByIdSelective(CarLocaleAuction carAuction);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Map<String,Object>> updateById(CarLocaleAuction carAuction);

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    public ServiceResult<List<CarLocaleAuction>> selectAuctionList(Map<String,Object> map);


    ServiceResult<List<CarLocaleAuction>> selectAuctionListForApp(Map<String, Object> map);

    /**
     * @Author 付陈林
     * @Date 2018-3-23
     * @About 查询竞拍场次
     * */
    public ServiceResult<List<CarLocaleAuction>> getAuctionList(Map<String,Object> map);

    /**
     * 场次数量
     * @param map
     * @return
     */
    ServiceResult<Integer> selectAuctionCount(Map<String,Object> map);

    ServiceResult<Integer> largeScreenDisplayCount(Map<String,Object> map);

    /**
     * 获取中心发拍我的车辆列表
     * @param map
     * @return
     */
    public ServiceResult<List<CarLocaleAuction>> selectCenterRacketCarList(Map<String,Object> map);

    /**
     * 获取中心发拍我的车辆数量
     * @param map
     * @return
     */
    ServiceResult<Integer> selectCenterRacketCarCount(Map<String,Object> map);

    /**
     * @Author 付陈林
     * @Date 2018-3-19
     * @About 根据ID获取到场次的信息及场次的车辆信息
     * */
    ServiceResult<Map<String,Object>> getLocaleAuctionDetail(Long id);

    /**
     * @Author 付陈林
     * @Date 2018-3-21
     * @About 根据ID获取到场次的信息(用于更新)
     * */
    ServiceResult<Map<String,Object>> getLocaleAuctionById(Long id);

    /**
     * @Author 付陈林
     * @Date 2018-3-19
     * @About 批量删除场次的车辆
     * */
    ServiceResult<Map<String,Object>> batchDeleteAuctionCar(String ids);

    /**
     * 查询所有有效拍卖场次
     * @return
     */
    List<CommonNameVo> selectAllValidAuction();

    /**
     * @Author 付陈林
     * @Date 2018-3-24
     * @About 根据Id删除竞拍场次及该场次下面的所有车辆
     * */
    ServiceResult<Map<String,Object>> deleteLocalAuction(Long id);

    /**
     * @Author 付陈林
     * @Date 2018-3-24
     * @About 查询
     * */
    ServiceResult<List<CarLocaleAuction>> largeScreenDisplay(Map<String,Object> map);


    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次Id进行开拍操作
     * */
    ServiceResult<Map<String,Object>> largeScreenStartAuction(Long auctionId);

    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次车辆Id获取，车辆的最高出价记录
     * */
    ServiceResult<Map<String,Object>> largeScreenBidPrice(Long auctionCarId);

    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次车辆Id获取,确定该车最终的成交情况
     * */
    ServiceResult<Map<String,Object>> largeScreenAuctionResult(Long auctionCarId,Long userId);

    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 根据场次Id获取更新最后的场次竞拍结果
     * */
    ServiceResult<Map<String,Object>> largeScreenAuctionFinish(Long auctionId);

    /**
     * @Author 付陈林
     * @Date 2018-3-26
     * @About 清除这个场次的缓存
     * */
    ServiceResult<Map<String,Object>> clearLargeScreenAuctionCar(Long auctionId);

    /**
     * 获取拍卖城市数量
     */
    ServiceResult<Integer> selectCityCount(Map<String,Object> map);

    /**
     * 首页获取场次数量
     */
    ServiceResult<Integer> queryAuctionCount(Map<String,Object> map);

    /**
     * 首页开拍场次时间查询
     */
    ServiceResult<List<CarLocaleAuction>> queryCarLocaleAuctionList(Map<String,Object> map);

    /**
     * 根据日期查询基站的竞拍场次
     * @param stationRealId
     * @return
     */
    CarLocaleAuction selectByStationRealId(String stationRealId,String auctionDate);
}
