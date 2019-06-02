package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ManagerRole;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.RedisAutoData;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.ICarAuctionSettingService;
import com.wintop.ms.carauction.service.ICarAutoAuctionService;
import com.wintop.ms.carauction.service.ICarAutoService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RedisAutoManager;
import io.swagger.models.auth.In;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CarAutoServiceImpl implements ICarAutoService {
    @Autowired
    private CarAutoModel carAutoModel;
    @Autowired
    private CarAutoPhotoModel photoModel;
    @Autowired
    private CarAuctionBidRecordModel bidRecordModel;
    @Autowired
    private CarOrderModel orderModel;
    @Autowired
    private CarCustomerBreachModel breachModel;
    @Autowired
    private CarAutoLogModel logModel;
    @Autowired
    private CarManagerUserModel managerUserModel;
    @Autowired
    private CarAutoAuctionModel autoAuctionModel;
    @Autowired
    private CarAutoInfoDetailModel autoInfoDetailModel;
    @Autowired
    private CarAutoProceduresModel proceduresModel;
    @Autowired
    private CarStoreModel storeModel;
    @Autowired
    private CarManagerUserModel userModel;
    @Autowired
    private ICarAutoAuctionService carAutoAuctionService;
    @Autowired
    private ICarAuctionSettingService auctionSettingService;
    @Autowired
    private CarOrderRetailModel carOrderRetailModel;
    @Autowired
    private RedisAutoManager redisAutoManager;
    @Autowired
    private CarLocaleAuctionCarModel carLocaleAuctionCarModel;

    private static Map<String,  List<Integer>> auctionTypeMap = getAuctionTypeMap();

    private static Map<String, List<Integer>> getAuctionTypeMap() {
        return new HashMap<String, List<Integer>>(){{
            put("stock", Lists.newArrayList(1));
            put("approve", Lists.newArrayList(2,3,4));
            put("auction_status", Lists.newArrayList(5, 6, 7));
            put("auction_result", Lists.newArrayList(8,9,10,11,12,13,14,15,16,17,18,19));
        }};
    }

    private static final Logger logger = LoggerFactory.getLogger(CarAutoServiceImpl.class);
    IdWorker idWorker=new IdWorker(10);
    public ServiceResult<Integer> countByExample(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoModel.countByExample(map);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAuto> selectByPrimaryKey(Long id) {
        ServiceResult<CarAuto> result = new ServiceResult<>();
        try {
            CarAuto carAutoDetectionClass = this.carAutoModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAuto>> selectByExample(Map<String,Object> map) {
        ServiceResult<List<CarAuto>> result = new ServiceResult<>();
        try {
            List<CarAuto> list = this.carAutoModel.selectByExample(map);
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoModel.deleteByPrimaryKey(id);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAuto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            //增加车辆日志
            CarAutoLog autoLog = new CarAutoLog();
            autoLog.setId(idWorker.nextId());
            autoLog.setStatus(record.getStatus());
            autoLog.setMsg(record.getLogMsg());
            autoLog.setAutoId(record.getId());
            autoLog.setTime(new Date());
            autoLog.setUserType("2");
            autoLog.setUserId(record.getUpdateUser());
            autoLog.setUserMobile(record.getLogUserMobile());
            autoLog.setUserName(record.getLogUserName());
            logModel.insert(autoLog);
            int count = this.carAutoModel.updateByPrimaryKeySelective(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAuto> updateByPrimaryKey(CarAuto record) {
        ServiceResult<CarAuto> result = new ServiceResult<>();
        try {
            if (this.carAutoModel.updateByPrimaryKey(record)>0){
                result.setResult(record);
                result.setSuccess("0","成功");
            }else {
                result.setError("-1","修改失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAuto> insert(CarAuto record) {
        logger.info("创建车辆");
        ServiceResult<CarAuto> result = new ServiceResult<>();
        try {
            CarManagerUser carManagerUser = managerUserModel.selectByPrimaryKey(record.getCreateUser());
            //基本信息
            CarAutoInfoDetail autoInfoDetail = new CarAutoInfoDetail();
            autoInfoDetail.setId(idWorker.nextId());
            autoInfoDetail.setAutoId(record.getId());
            autoInfoDetail.setCreateTime(record.getCreateTime());
            //竞拍信息
            CarAutoAuction autoAuction = new CarAutoAuction();
            autoAuction.setId(idWorker.nextId());
            autoAuction.setAutoId(record.getId());
            autoAuction.setCreateTime(record.getCreateTime());
            autoAuction.setCreatePerson(record.getCreateUser());
            autoAuction.setStatus("1");
            autoAuction.setDelFlag("0");
            autoAuction.setBidsCount(0);
            autoAuction.setBidersCount(0);
            autoAuction.setAuctionType(record.getAuctionType());
            //如果车发车人属于经销店人员，车辆所属店铺自动绑定到所属店铺
            if (carManagerUser!=null && carManagerUser.getRoleTypeId()==3L){
                CarStore carStore = storeModel.selectByPrimaryKey(carManagerUser.getDepartmentId());
                autoAuction.setStoreName(carStore.getName());
                autoAuction.setStoreId(carStore.getId());
                record.setStoreId(carStore.getId());
                record.setStoreName(carStore.getName());
            }
            record.setAutoAuctionId(autoAuction.getId());
            //手续信息
            CarAutoProcedures autoProcedures = new CarAutoProcedures();
            autoProcedures.setId(idWorker.nextId());
            autoProcedures.setAutoId(record.getId());
            autoProcedures.setCreateUser(record.getCreateUser());
            autoProcedures.setCreateTime(new Date());
            //增加车辆日志
            CarAutoLog autoLog = new CarAutoLog();
            autoLog.setId(idWorker.nextId());
            autoLog.setStatus("1");
            autoLog.setMsg("创建草稿车辆");
            autoLog.setAutoId(record.getId());
            autoLog.setTime(new Date());
            autoLog.setUserType("2");
            autoLog.setUserMobile(record.getLogUserMobile());
            autoLog.setUserName(record.getLogUserName());

            if (this.carAutoModel.insert(record)>0
                    && autoInfoDetailModel.insert(autoInfoDetail)>0
                    && autoAuctionModel.insert(autoAuction)>0
                    && proceduresModel.insert(autoProcedures)>0
                    && logModel.insert(autoLog)>0){
                result.setResult(record);
                result.setSuccess("0","成功");
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                result.setError("-1","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    public ServiceResult<Integer> insertSelective(CarAuto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoModel.insertSelective(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Override
    public CarAuto selectByCarId(Map<String,Object> map) {
        CarAuto carAuto = carAutoModel.selectByCarId(map);
        if(carAuto == null){
            return null;
        }
        String buyerBear = "";
        String sellerBear = "";
        if("1".equals(carAuto.getTransferFee())){
            buyerBear+="过户费;";
        }else if("2".equals(carAuto.getTransferFee())){
            sellerBear+="过户费;";
        }
        if(carAuto.getIllegalScore() != null && carAuto.getIllegalScore()>0){
            if("1".equals(carAuto.getIllegalWho())){
                buyerBear+=("违法扣分"+carAuto.getIllegalScore()+"分;");
            }else{
                sellerBear+=("违法扣分"+carAuto.getIllegalScore()+"分;");
            }
        }
        if(carAuto.getIllegalPrice()!=null &&carAuto.getIllegalPrice().compareTo(new BigDecimal(0))>0){
            if("1".equals(carAuto.getIllegalWho())){
                buyerBear+=("缴纳罚款"+carAuto.getIllegalPrice()+"元;");
            }else{
                sellerBear+=("缴纳罚款"+carAuto.getIllegalPrice()+"元;");
            }
        }
        carAuto.setBuyerBear(buyerBear);
        carAuto.setSellerBear(sellerBear);

        if(map.get("customerId") != null){
            String pattern = "yyyy年MM月dd日 HH:mm:ss";
            /**
             * 获取我的最后出价
             */
            BigDecimal bidFee = bidRecordModel.selectMaxPrice(map);
            carAuto.setMyBidPrice(bidFee);
            /**
             * 返回提示语信息
             */
            //1,获取是否产生订单
            CarOrder carOrder = orderModel.queryCarOrder(map);
            if(carOrder != null){
                map.put("orderId",carOrder.getId());
                CarCustomerBreach carBreach = breachModel.queryNewBreachInfo(map);
                //2,首先判断是否有违约信息
                if(carBreach!=null){
                    carAuto.setTipsMessage(carBreach.getInitiatMsg());
                }else{
                    if("1".equals(carOrder.getStatus())){
                        String orderTime = DateFormatUtils.format(carOrder.getCreateTime(),pattern);
                        carAuto.setTipsMessage("恭喜您在"+orderTime+"竞拍成功，24小时未支付车款，按违约处理。");
                    }else if("2346".contains(carOrder.getStatus())){
                        carAuto.setTipsMessage("您已支付车款，客服会尽快联系您。");
                    }
                }
            }else{
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("carId",(Long)map.get("carId"));
                CarOrder carOrder1 = orderModel.queryCarOrder(paramMap);
                //3,判断该车是否被其他买家拍卖成功
                if(carOrder1!=null){
                    //4,判断用户是否有出价记录
                    BigDecimal myPrice = bidRecordModel.selectMaxPrice(map);
                    if(myPrice!=null){
                        String endTime = DateFormatUtils.format(carAuto.getAuctionEndTime(),pattern);
                        carAuto.setTipsMessage("此车在"+endTime+"竞拍失败。");
                    }
                }
            }
        }
        List<CarAutoPhoto> carAutoPhotos = photoModel.selectByCarId((Long)map.get("carId"));
        carAuto.setCarAutoPhotos(carAutoPhotos);
        return carAuto;
    }

    /**
     * 根据条件查询记录
     */
    @Override
    public List<CarAuto> selectAuctionCarList(Map<String,Object> map){
        return carAutoModel.selectAuctionCarList(map);
    }

    @Override
    public List<CarAuto> selectCarList(Map<String, Object> map) {
        //查询用户权限
        CarManagerUser carManagerUser = userModel.selectByPrimaryKey(Long.valueOf(map.get("userId")+""));
        //如果用户是中心店管理员
        if(ManagerRole.ZX_ESCFZR.value() == carManagerUser.getRoleId()){
//            map.put("roleTyped","2");//中心店
            map.put("departmentId",carManagerUser.getDepartmentId());
            map.put("managerRole",carManagerUser.getRoleId());
        }
        //如果用户是店铺管理员
        if(ManagerRole.JXD_ESCFZR.value() == carManagerUser.getRoleId()){
//            map.put("roleTyped","3");//店铺
            map.put("departmentId",carManagerUser.getDepartmentId());
            map.put("managerRole",carManagerUser.getRoleId());
        }
        return carAutoModel.selectCarList(map);
    }
    /**
     * 查询线上车辆数量
     */
    @Override
    public Integer selectCarCount(Map<String, Object> map) {
        List<Map<String, Object>> resultList = Lists.newArrayList();
        //查询用户权限
        CarManagerUser carManagerUser = userModel.selectByPrimaryKey(Long.valueOf(map.get("userId")+""));
        //如果用户是中心店管理员
        if(ManagerRole.ZX_ESCFZR.value() == carManagerUser.getRoleId()){
//            paramMap.put("auctionType","2");//现场车辆
//            paramMap.put("roleTyped","2");//中心店
            map.put("departmentId",carManagerUser.getDepartmentId());
            map.put("managerRole",carManagerUser.getRoleId());
        }
        //如果用户是店铺管理员
        if(ManagerRole.JXD_ESCFZR.value() == carManagerUser.getRoleId()){
//            paramMap.put("auctionType","1");//线上车辆
//            paramMap.put("roleTyped","3");//店铺
            map.put("departmentId",carManagerUser.getDepartmentId());
            map.put("managerRole",carManagerUser.getRoleId());
        }
        return carAutoModel.selectCarAutoCount(map);
    }

    /**
     * 查询总数量
     * @param map
     * @return
     */
    @Override
    public int selectAuctionCarCount(Map<String,Object> map){
        return carAutoModel.selectAuctionCarCount(map);
    }

    /**
     * 根据条件查询线上拍的车辆列表
     */
    public List<CarAuto> selectOnlineCarList(Map<String,Object> map){
        return carAutoModel.selectOnlineCarList(map);
    }

    /**
     * 根据条件查询线上拍的车辆总数量
     * @param map
     * @return
     */
    public int selectOnlineCarCount(Map<String,Object> map){
        return carAutoModel.selectOnlineCarCount(map);
    }

    @Override
    public CarAuto selectMyLastAuto(Map paramMap) {
        return carAutoModel.selectMyLastAuto(paramMap);
    }

    /**
     * 根据条件查询发拍的车辆列表
     */
    public List<CarAuto> selectHairShotCarList(Map<String,Object> map){
        return carAutoModel.selectHairShotCarList(map);
    }

    /**
     * 零售订单列表
     */
    @Override
    public List<Map<String, Object>> selectRetailForExample(Map<String, Object> map) {
        List<Map<String, Object>> resultList = Lists.newArrayList();
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("autoInfoName",map.get("autoInfoName"));
        //查询用户权限
        CarManagerUser carManagerUser = userModel.selectByPrimaryKey(Long.valueOf(map.get("userId")+""));
        paramMap.put("userId",carManagerUser.getId());
        //如果用户是中心店管理员
        if(ManagerRole.ZX_ESCFZR.value() == carManagerUser.getRoleId()){
//            paramMap.put("auctionType","2");//现场车辆
//            paramMap.put("roleTyped","2");//中心店
            paramMap.put("departmentId",carManagerUser.getDepartmentId());
            paramMap.put("managerRole",carManagerUser.getRoleId());
        }
        //如果用户是店铺管理员
        if(ManagerRole.JXD_ESCFZR.value() == carManagerUser.getRoleId()){
//            paramMap.put("auctionType","1");//线上车辆
//            paramMap.put("roleTyped","3");//店铺
            paramMap.put("departmentId",carManagerUser.getDepartmentId());
            paramMap.put("managerRole",carManagerUser.getRoleId());
        }
        // 零售
        paramMap.put("saleFlag", "1");
        List<CarAuto> recordList = carAutoModel.selectRetailForExample(map);
        Map<String, Object> recordMap;
        for (CarAuto record : recordList) {
            recordMap = Maps.newHashMap();
            recordMap.put("id", record.getId());
            recordMap.put("createTime", record.getCreateTime());
            recordMap.put("autoInfoName", record.getAutoInfoName());
            recordMap.put("mainPhoto", record.getMainPhoto());
            resultList.add(recordMap);
        }
        return resultList;
    }

    /**
     * 零售订单列表总数量
     */
    @Override
    public Integer selectRetailForCount(Map<String, Object> map) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("autoInfoName",map.get("autoInfoName"));
        //查询用户权限
        CarManagerUser carManagerUser = userModel.selectByPrimaryKey(Long.valueOf(map.get("userId")+""));
        paramMap.put("userId",carManagerUser.getId());
        //如果用户是中心店管理员
        if(ManagerRole.ZX_ESCFZR.value() == carManagerUser.getRoleId()){
//            paramMap.put("auctionType","2");//现场车辆
//            paramMap.put("roleTyped","2");//中心店
            paramMap.put("departmentId",carManagerUser.getDepartmentId());
            paramMap.put("managerRole",carManagerUser.getRoleId());
        }
        //如果用户是店铺管理员
        if(ManagerRole.JXD_ESCFZR.value() == carManagerUser.getRoleId()){
//            paramMap.put("auctionType","1");//线上车辆
//            paramMap.put("roleTyped","3");//店铺
            paramMap.put("departmentId",carManagerUser.getDepartmentId());
            paramMap.put("managerRole",carManagerUser.getRoleId());
        }
         // 零售
        paramMap.put("saleFlag", "1");
        return carAutoModel.selectCarAutoForSaleCount(paramMap);
    }

    /**
     * 根据条件查询线发拍的车辆数量
     * @param map
     * @return
     */
    public int selectHairShotCarCount(Map<String,Object> map){
        return carAutoModel.selectHairShotCarCount(map);

    }

    /**
     * @Author 付陈林
     * @Date 2018年3月21号
     * @About 获取所有可以参加竞拍的车辆，1、status为5 等待开拍  2、竞拍类型为线下竞拍  3、未加入到已参加的竞拍列表中
     * */
    public ServiceResult<List<CarAuto>> getLocaleAuctionCar(String searchParam){
        ServiceResult<List<CarAuto>> result =new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(carAutoModel.getLocaleAuctionCar(searchParam));
        return result;
    }

    /**
     *查询所有的车辆列表
     * @param map
     * @return
     */
    public ServiceResult<List<CarAuto>> getAllCarAutoList(Map<String,Object> map){
        ServiceResult<List<CarAuto>> result =new ServiceResult<>();
        try {
                 List<CarAuto> carAutos=carAutoModel.getAllCarAutoList(map);
                 for (CarAuto auto:carAutos){
//                         <!--审批不通过查询审批意见、撤回审批状态查询撤回人信息，及撤回原因-->
                     if ("3".equals(auto.getStatus()) || "4".equals(auto.getStatus())){
                         Map<String,Object>map1=new HashMap<>();
                         map1.put("status",auto.getStatus());
                         map1.put("carId",auto.getId());
                         CarAutoLog autoLog=logModel.selectCarLog(map1);
                         if (autoLog!=null){
                             auto.setLogMsg(autoLog.getMsg());
                             auto.setLogUserName(autoLog.getUserName());
                             auto.setLogUserMobile(autoLog.getUserMobile());
                         }
                     }
                 }
                result.setResult(carAutos);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     *查询所有的车辆数目
     * @Author zhangzijiuan
     * @param map
     * @return
     */
    public ServiceResult<Integer> getAllCarAutoCount(Map<String,Object> map){
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setResult(carAutoModel.getAllCarAutoCount(map));
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
    /**
     * 审核撤回审批的车辆
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:
     */
    @Transactional
    public Integer revokeApprove(JSONObject object){
        Integer result=0;
        Long carId=object.getLong("carId");
        Long managerId=object.getLong("managerId");
        Map<String,Object> map1 =new HashMap<>();
        map1.put("carId",object.getLong("carId"));
        CarAuto auto=carAutoModel.selectByCarId(map1);
        CarAutoLog log=new CarAutoLog();
        if(auto!=null && !"7".equals(auto.getStatus())){
            auto.setUpdateUser(managerId);
            auto.setUpdateTime(new Date());
            //1.车辆状态变更
            // 审核通过，车辆状态回到草稿状态
            if("1".equals(object.getString("status"))){
                //如果撤回时车辆状态等于6 等待开拍 撤回时上拍次数减1
                if ("6".equals(auto.getStatus()) && "1".equals(auto.getAuctionType())){
                    auto.setAuctionNum(auto.getAuctionNum()-1);
                }
                auto.setStatus("1");

                //如果是现场拍的车辆，撤回通过，需要修改场次车辆绑定表的状态为，撤回
//                carLocaleAuctionCarModel.deleteById()

            }else if ("2".equals(object.getString("status"))){
                Map<String,Object> map=new HashMap<>();
                map.put("carId",carId);
                // 审核不通过
                // 车辆已经过了开拍时间 设为19=流拍
                if (auto.getAuctionStartTime()!=null && auto.getAuctionStartTime().compareTo(new Date())<0){
                    auto.setStatus("19");
                }else {
                    // 车辆状态回到撤回的前一个状态（日志表的倒数第二条记录）
                    List<CarAutoLog> logs=logModel.selectCarLogByCarId(map);
                    if(logs!=null && logs.size()>1){
                        auto.setStatus(logs.get(1).getStatus());
                    }else {
                        return  result;
                    }
                }
            }else {
                return result;
            }
            //2.日志存储
            log.setStatus(auto.getStatus());
            log.setAutoId(carId);
            log.setId(idWorker.nextId());
            log.setMsg(object.getString("msg"));
            log.setUserType("2");
            log.setTime(new Date());
            log.setUserId(managerId);
            CarManagerUser managerUser=managerUserModel.selectByPrimaryKey(managerId);
            if (managerUser!=null){
                log.setUserName(managerUser.getUserName());
                log.setUserMobile(managerUser.getUserPhone());
            }
            if(carAutoModel.updateByPrimaryKeySelective(auto)>0){
                result=logModel.insert(log);
                //如果审核不通过返回的状态是6等待开拍的，车辆信息添加到redis
                if ("2".equals(object.getString("status")) && "1".equals(auto.getAuctionType()) && "6".equals(auto.getStatus())){
                    RedisAutoData autoData = carAutoAuctionService.getRedisAutoData(auto.getId());
                    redisAutoManager.updateAuto(autoData);
                }
            }
        }
        return result;
    }
    /**
     * 审批发拍的车辆
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:
     */
    @Transactional
    public Integer approveCarAuto(JSONObject object){
        Integer result=0;
        Map<String,Object> map =new HashMap<>();
        map.put("carId",object.getLong("carId"));
        Long managerId=object.getLong("managerId");
        CarManagerUser managerUser=managerUserModel.selectByPrimaryKey(managerId);
        CarAuto carAuto=carAutoModel.selectByCarId(map);
        if(managerUser!=null && carAuto!=null && carAuto.getAuctionType()!=null){
            CarAutoAuction autoAuction=autoAuctionModel.selectByPrimaryKey(carAuto.getAutoAuctionId());
            CarAuctionSetting auctionSetting=auctionSettingService.selectByRegionId(carAuto.getRegionId());
            //如果审核通过
            if("1".equals(object.getString("status"))){
                //如果是线上拍卖，判断拍卖时间设置
                if("1".equals(carAuto.getAuctionType())){
                    //检测是否有开拍时间
                    boolean b=StringUtils.isNotBlank(object.getString("auctionStartTime"));
                    if(b){
                        if (new Date().compareTo(object.getDate("auctionStartTime"))<0){
                            //设置拍卖开始时间和结束时间
                            Date auctionStartTime=object.getDate("auctionStartTime");
                            // 查询该地区的拍卖时间
                            boolean flag=auctionSetting!=null && auctionSetting.getServerFee()!=null && auctionSetting.getAgentFee()!=null && auctionSetting.getKeepTime()!=null;
                            if (flag){
                                Date auctionEndTime=new Date(auctionStartTime.getTime()+ auctionSetting.getKeepTime()*1000);
                                autoAuction.setId(carAuto.getAutoAuctionId());
//                               保存开拍时间
                                autoAuction.setAuctionStartTime(auctionStartTime);
                                autoAuction.setAuctionEndTime(auctionEndTime);
                                autoAuction.setAuctionEndDefaultTime(auctionEndTime);
                                //保存可见范围
                                autoAuction.setOpenLimit(object.getString("openLimit"));
                                autoAuction.setOpenLimitCn(object.getString("openLimitCn"));
                                //更改车辆状态 6等待开拍
                                carAuto.setStatus("6");
                                //上拍次数
                                int num=carAuto.getAuctionNum()!=null?carAuto.getAuctionNum()+1:1;
                                carAuto.setAuctionNum(num);

                            }else {
                                return -2;
                            }
                        }else {
                            return -3;
                        }
                    }else {
                        return -1;
                    }
                }else {
//               //如果是线下拍卖，更改车辆状态 5等待上拍
                    carAuto.setStatus("5");
                }

                if (auctionSetting!=null){
                    //保存服务费和代办费
                    autoAuction.setServicePrice(auctionSetting.getServerFee());
                    if ("1".equals(autoAuction.getIfAgent())){
                        autoAuction.setAgentPrice(auctionSetting.getAgentFee());
                    }else if ("2".equals(autoAuction.getIfAgent())){
                        autoAuction.setAgentPrice(new BigDecimal(0));
                    }
                }else {
                    return -2;
                }

                //1.修改拍卖表的时间
                autoAuctionModel.updateByPrimaryKeySelective(autoAuction);

                //如果审核通过添加发布人信息
                carAuto.setPublishTime(new Date());
                carAuto.setPublishUserId(managerId);
                carAuto.setPublishUserName(managerUser.getUserName());
                carAuto.setPublishUserMobile(managerUser.getUserPhone());
                //如果审核不通过
            }else if ("2".equals(object.getString("status"))){
                carAuto.setStatus("3");
            }else {
                return result;
            }
            carAuto.setUpdateUser(managerId);
            carAuto.setUpdateTime(new Date());

            //2 更改车辆表的状态
            if (carAutoModel.updateByPrimaryKeySelective(carAuto)>0){
                //3.日志存储
                CarAutoLog log=new CarAutoLog();
                log.setAutoId(carAuto.getId());
                log.setId(idWorker.nextId());
                log.setMsg(object.getString("msg"));
                log.setUserType("2");
                log.setTime(new Date());
                log.setUserId(managerId);
                log.setStatus(carAuto.getStatus());
                if (managerUser!=null){
                    log.setUserName(managerUser.getUserName());
                    log.setUserMobile(managerUser.getUserPhone());
                }
                result=logModel.insert(log);
                if ("1".equals(object.getString("status")) &&"1".equals(carAuto.getAuctionType())){
                    RedisAutoData autoData = carAutoAuctionService.getRedisAutoData(carAuto.getId());
                    redisAutoManager.updateAuto(autoData);
                }
            }
        }
        return result;
    }

    /**
     *获取当日上新车辆数
     */
    @Transactional
    public Integer selectDayCarCount(Map<String,Object> map) {
        Integer result = 0;
        try {
            result = this.carAutoModel.selectDayCarCount(map);

        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Override
    /**
     * 库存管理--（零售[已售]、线上拍[车辆库存、审批状态、竞价状态、竞价结果]、现场拍[车辆库存、审批状态、竞价状态、竞价结果]）
     */
    public List<Map<String,Object>> selectCarAutoForSaleCount(Map<String, Object> map){
        List<Map<String, Object>> mapArrayList = Lists.newArrayList();
        Map<String, Object> paramMap = Maps.newHashMap();
        Map<String, Object> resultMap = Maps.newHashMap();
        List<String> paramStatusList = new ArrayList<String>(){{
            add("stock");
            add("approve");
            add("auction_status");
            add("auction_result");
        }};
        List<String> titleList = new ArrayList<String>(){{
            add("车辆库存");
            add("审批状态");
            add("竞价状态");
            add("竞价结果");
        }};
        try {
            String type = map.get("type").toString();
            //查询用户权限
            CarManagerUser carManagerUser = userModel.selectByPrimaryKey(Long.valueOf(map.get("userId")+""));
            paramMap.put("userId",carManagerUser.getId());
            //如果用户是中心店管理员
            if(ManagerRole.ZX_ESCFZR.value() == carManagerUser.getRoleId()){
//                paramMap.put("auctionType","2");//现场车辆
//                paramMap.put("roleTyped","2");//中心店
                paramMap.put("departmentId",carManagerUser.getDepartmentId());
                paramMap.put("managerRole",carManagerUser.getRoleId());
            }
            //如果用户是店铺管理员
            if(ManagerRole.JXD_ESCFZR.value() == carManagerUser.getRoleId()){
//                paramMap.put("auctionType","1");//线上车辆
//                paramMap.put("roleTyped","3");//店铺
                paramMap.put("departmentId",carManagerUser.getDepartmentId());
                paramMap.put("managerRole",carManagerUser.getRoleId());
            }
            //竞拍
            paramMap.put("saleFalg", "0");
            if ("retail".equals(type)) {
                // 零售
                paramMap.put("saleFlag", "1");
                Integer num = carAutoModel.selectCarAutoForSaleCount(paramMap);
                resultMap.put("title", "已售");
                resultMap.put("num", num);
                mapArrayList.add(resultMap);
            } else if ("online".equals(type)) {
                // 线上
                paramMap.put("auctionType", "1");
                // 车辆库存
                for (int i = 0; i < paramStatusList.size(); i++) {
                    getStockNumResultList(mapArrayList, paramMap, paramStatusList, titleList, i);
                }
            } else if ("onsite".equals(type)) {
                // 现场
                paramMap.put("auctionType", "2");
                // 车辆库存
                for (int i = 0; i < paramStatusList.size(); i++) {
                    getStockNumResultList(mapArrayList, paramMap, paramStatusList, titleList, i);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapArrayList;
    }

    private void getStockNumResultList(List<Map<String, Object>> mapArrayList, Map<String, Object> paramMap, List<String> paramStatusList, List<String> titleList, int i) {
        Map<String, Object> resultMap;
        resultMap = Maps.newHashMap();
        paramMap.put("statusList", auctionTypeMap.get(paramStatusList.get(i)));
        Integer num = carAutoModel.selectCarAutoCount(paramMap);
        resultMap.put("title", titleList.get(i));
        resultMap.put("num", num);
        mapArrayList.add(resultMap);
    }

    public CarAuto selectById(Long id) {
        CarAuto carAuto = this.carAutoModel.selectByPrimaryKey(id);
        return carAuto;
    }

    public int updateByIdSelective(CarAuto record){
        return carAutoModel.updateByPrimaryKeySelective(record);
    }

    /**
     * @About 申请二拍
     * */
    @Transactional
    public Integer setAgainAuction(JSONObject object,CarAuto carAuto){
        Long carId=object.getLong("carId");
        Long userId=object.getLong("userId");
        BigDecimal startAmount=object.getBigDecimal("startAmount");
        BigDecimal reserveAmount=object.getBigDecimal("reserveAmount");
        Integer result =0;
        if (carAuto==null){
             carAuto =carAutoModel.selectByPrimaryKey(carId);
        }
        CarAutoAuction carAutoAuction =autoAuctionModel.selectByPrimaryKey(carAuto.getAutoAuctionId());
        CarManagerUser carManagerUser=managerUserModel.selectByPrimaryKey(userId);
        if (carManagerUser!=null && carAuto!=null && carAutoAuction!=null && "19".equals(carAuto.getStatus())){
            //step 1.设置车辆竞拍信息 获取到目前的竞拍信息，将其设置为流拍。
            CarAutoAuction updateAutoAuction =new CarAutoAuction();
            updateAutoAuction.setId(carAutoAuction.getId());
            updateAutoAuction.setStatus("3");  //流拍
            updateAutoAuction.setModifyPerson(userId);
            updateAutoAuction.setModifyTime(new Date());
            autoAuctionModel.updateByPrimaryKeySelective(updateAutoAuction);

            //step 2 产出一条新车辆竞拍信息
            Date date=new Date();
            carAutoAuction.setId(idWorker.nextId());
            carAutoAuction.setStatus("1");
            carAutoAuction.setStartingPrice(startAmount);
            carAutoAuction.setReservePrice(reserveAmount);
            carAutoAuction.setCreateTime(date);
            carAutoAuction.setCreatePerson(userId);
            carAutoAuction.setModifyTime(date);
            carAutoAuction.setModifyPerson(userId);
            carAutoAuction.setTopBidPrice(null);
            carAutoAuction.setTopBidTime(null);
            carAutoAuction.setTopPricerId(null);
            carAutoAuction.setBidsCount(0);
            carAutoAuction.setBidersCount(0);
            carAutoAuction.setDelFlag("0");
            autoAuctionModel.insert(carAutoAuction);

            // step 3 更新车辆信息
            CarAuto updateCarAuto =new CarAuto();
            updateCarAuto.setId(carId);
            //线上拍 车辆状态变为2-待审核
            if ("1".equals(carAutoAuction.getAuctionType())){
                updateCarAuto.setStatus("2");
            //现场拍 车辆状态变为 5-等待上拍
            }else if ("2".equals(carAutoAuction.getAuctionType())){
                updateCarAuto.setStatus("5");
            }else {
                logger.info("获取不到拍卖类型");
                return  result;
            }
            updateCarAuto.setAutoAuctionId(carAutoAuction.getId());
            updateCarAuto.setUpdateUser(userId);
            updateCarAuto.setUpdateTime(new Date());
            if (carAutoModel.updateByPrimaryKeySelective(updateCarAuto)>0){
                // step 4 添加车辆操作日志
                CarAutoLog carAutoLog = new CarAutoLog();
                carAutoLog.setId(idWorker.nextId());
                carAutoLog.setAutoId(carId);
                carAutoLog.setUserType("2");
                carAutoLog.setStatus(updateCarAuto.getStatus());
                carAutoLog.setTime(new Date());
                carAutoLog.setMsg("");
                carAutoLog.setUserId(userId);
                carAutoLog.setUserMobile(carManagerUser.getUserPhone());
                carAutoLog.setUserName(carManagerUser.getUserName());
                result=logModel.insert(carAutoLog);
            }
        }
        return result;
    }

    /**
     * 根据车辆id查询起拍价和保留价
     */
    public CarAutoAuction  selectCarInfoById(JSONObject object){
       return autoAuctionModel.selectAutoAuction(object.getLong("carId"));
    }

    @Override
    public List<CarAuto> selectUserOrderList(Map<String, Object> map) {
        return carAutoModel.selectUserOrderList(map);
    }

    @Override
    public int selectCountById(Long userId) {
        return carAutoModel.selectCountById(userId);
    }

    @Override
    public int selectCarAutoApprovalCount(Map<String, Object> map) {
        return carAutoModel.selectCarAutoApprovalCount(map);
    }

    @Override
    public List<CarAuto> selectCarAutoApprovalList(Map<String, Object> map) {
        return carAutoModel.selectCarAutoApprovalList(map);
    }

}