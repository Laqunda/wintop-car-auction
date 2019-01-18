package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import com.wintop.ms.carauction.mapper.read.ICarLocaleAuctionCarReadDao;
import com.wintop.ms.carauction.mapper.write.ICarLocaleAuctionCarWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarLocaleAuctionCarModel {
    @Autowired
    private ICarLocaleAuctionCarReadDao readDao;
    @Autowired
    private ICarLocaleAuctionCarWriteDao writeDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return readDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarLocaleAuctionCar> selectByExample(Map<String,Object> map){
        return readDao.selectByExample(map);
    }

    /**
     * 根据主键查询记录
     */
    public CarLocaleAuctionCar selectById(Long id){
        return readDao.selectById(id);
    }

    /**
     * 根据carId查询记录
     */
    public List<CarLocaleAuctionCar> selectByCarId(Long carId){
        return readDao.selectByCarId(carId);
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
    public int insert(CarLocaleAuctionCar carAuctionCar){
        return writeDao.insert(carAuctionCar);
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByIdSelective(CarLocaleAuctionCar carAuctionCar){
        return writeDao.updateByIdSelective(carAuctionCar);
    }

    /**
     * 根据主键更新记录
     */
    public int updateById(CarLocaleAuctionCar carAuctionCar){
        return writeDao.updateById(carAuctionCar);
    }

    /**
     * 根据auctionId查询车辆数量
     * @param map
     * @return
     */
    public int selectCarNumByAuction(Map<String,Object> map){
        return readDao.selectCarNumByAuction(map);
    }


    /**
     * @Autor 付陈林
     * @Date 2018年3月19号
     * @About 根据场次车辆ID 查询该场次的所有车辆信息
     * */
    public List<CarLocaleAuctionCar> getAuctionCarList(Long auctionId){
        return readDao.getAuctionCarList(auctionId);
    };

    /**
     * @Autor 付陈林
     * @Date 2018年3月22号
     * @About 根据场次车辆ID 查该场次下面车辆最大的排序
     * */
    public Integer getMaxSortForActionCar(Long auctionId){
        return readDao.getMaxSortForActionCar(auctionId);
    }

    /**
     * 查询场次内最近正在竞拍的车辆序号
     * @param auctionId
     * @return
     */
    public Integer getMinSortForActionCar(Long auctionId){
        return readDao.getMinSortForActionCar(auctionId);
    }

    /**
     * @Autor 付陈林
     * @Date 2018-3-24
     * @About 根据车辆关联ID，查询车辆的基本信息
     *
     * */
    public CarLocaleAuctionCar getCarLocaleAuctionCar(Long auctionCarId){
        return readDao.getCarLocaleAuctionCar(auctionCarId);
    }
    /**
     * @Autor 付陈林
     * @Date 2018-3-25
     * @About 根据场次Id及序号，查询下一辆车的现场拍信息
     * */
    public List<CarLocaleAuctionCar> largeScreenShowCar(Long auctionId){
        return readDao.largeScreenShowCar(auctionId);
    }

    /**
     * @Autor 付陈林
     * @Date 2018-3-25
     * @About 查询当前车辆在整个车辆中的排序
     * */
    public Integer largeScreenSort(Map paramMap){
        return readDao.largeScreenSort(paramMap);
    }

    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆
     * */
    public List<CarLocaleAuctionCar> hasAuctionCarList(Map<String,Object> map){
        return readDao.hasAuctionCarList(map);
    }
    /**
     * @Author 付陈林
     * @Date 2018-3-31
     * @About 查询参拍车辆梳理
     * */
    public Integer hasAuctionCarCount(Map<String,Object> map){
        return readDao.hasAuctionCarCount(map);
    }


    /**
     * 批量插入竞拍车辆信息
     */
    public Integer insertCarLocaleAuctionCarList(List<CarLocaleAuctionCar> list){
        return writeDao.insertCarLocaleAuctionCarList(list);
    }

    /***
     * 修改某场次内的指定顺序号的车 顺延序号
     * @param map
     * @return
     */
    public Integer updateSortAuctionCar(Map map){
        return writeDao.updateSortAuctionCar(map);
    }
//      导入起拍价时，批量存储加价幅度
    public Integer batchUpdateLocaleAuctionById(List<CarLocaleAuctionCar> localeAuctionList){
        return writeDao.batchUpdateLocaleAuctionById(localeAuctionList);
    }

    /**
     * 查询当前场次正在竞拍的车辆
     * @param localeAuctionId
     * @return
     */
    public CarLocaleAuctionCar selectCurrentAuctionCar(Long localeAuctionId){
        return readDao.selectCurrentAuctionCar(localeAuctionId);
    }
}
