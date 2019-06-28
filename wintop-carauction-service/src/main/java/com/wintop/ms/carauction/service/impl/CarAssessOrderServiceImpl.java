package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.CarStatusEnum;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.mapper.read.IWtProvinceReadDao;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.*;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.JPushUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * 评估采购单 服务层实现
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@Service
public class CarAssessOrderServiceImpl implements ICarAssessOrderService {
    private static final String STORE_TYPE = "2";
    private static final String CENTER_TYPE = "2";
    private static final String CENTER_INCHANGE = "3";
    private static final String CELLER = "1";
    private static final String AUDIT_ORDER_TITLE = "审批订单";
    private static final String AUDIT_ORDER_CONTENT = "您有一个订单待审批";
    private static final String STORE_INCHANGE = "8";
    @Autowired
    private CarAssessOrderModel model;
    @Autowired
    private CarAssessModel carAssessModel;
    @Autowired
    private CarStoreModel carStoreModel;

    @Autowired
    private CarAssessFollowDataModel followDataModel;

    @Autowired
    private ICarAssessOrderLogService orderLogService;

    @Autowired
    private ICarAssessLogService logService;
    @Autowired
    private CarManagerUserModel userModel;

    @Autowired
    private CarAutoModel carAutoModel;

    @Autowired
    private CarAutoAuctionModel auctionModel;

    @Autowired
    private CarAutoInfoDetailModel autoInfoDetailModel;

    @Autowired
    private ICarAutoLogService autoLogService;

    @Autowired
    private CarAutoProceduresModel proceduresModel;

    @Autowired
    private CarCenterStoreModel carCenterStoreModel;

    @Autowired
    private ICarAppInfoService appInfoService;

    @Autowired
    private WtProvinceModel provinceModel;

    @Autowired
    private WtCityModel cityModel;
    /**
     * 查询评估采购单信息
     *
     * @param id 评估采购单ID
     * @return 评估采购单信息
     */
    @Override
    public CarAssessOrder selectCarAssessOrderById(Long id) {
        return model.selectCarAssessOrderById(id);
    }

    /**
     * 查询评估采购单列表
     *
     * @param param 评估采购单信息
     * @return 评估采购单集合
     */
    @Override
    public List<CarAssessOrder> selectCarAssessOrderList(Map<String, Object> param) {
        List<CarAssessOrder> carAssessOrderList = model.selectCarAssessOrderList(param);
        for (CarAssessOrder recored : carAssessOrderList) {
            CarAssess carAssess = new CarAssess();
            carAssess.setId(recored.getAssessId());
            CarAssess carAssessDao = carAssessModel.selectCarAssessById(carAssess);
            recored.setCarAssess(carAssessDao);
            if (recored.getStoreId() != null) {
                CarStore carStore = carStoreModel.selectByPrimaryKey(recored.getStoreId());
                recored.setCarStore(carStore);
            }
        }
        return carAssessOrderList;
    }

    /**
     * 新增评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
    @Override
    public int insertCarAssessOrder(CarAssessOrder carAssessOrder) {
        return model.insertCarAssessOrder(carAssessOrder);
    }

    /**
     * 修改评估采购单
     *
     * @param carAssessOrder 评估采购单信息
     * @return 结果
     */
    @Override
    public int updateCarAssessOrder(CarAssessOrder carAssessOrder) {
        return model.updateCarAssessOrder(carAssessOrder);
    }

    /**
     * 删除评估采购单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarAssessOrderByIds(String ids) {
        return model.deleteCarAssessOrderByIds(ids);
    }


    @Override
    public int selectAssessOrderCount(Map<String, Object> param) {
        return model.selectAssessOrderCount(param);
    }

    @Override
    public int selectCountById(Long userId) {
        return model.selectCountById(userId);
    }

    @Override
    public List<CarAssessOrder> selectUserOrderList(Map<String, Object> map) {
        return model.selectUserOrderList(map);
    }

    @Transactional
    @Override
    public ServiceResult<Map<String, Object>> createAssessOrder(JSONObject obj, IdWorker idWorker) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarAssessOrder carAssessOrder = JSONObject.toJavaObject(obj, CarAssessOrder.class);
            if (carAssessOrder == null) {
                carAssessOrder = new CarAssessOrder();
            }
            CarManagerUser managerUser = userModel.selectByPrimaryKey(obj.getLong("managerId"));
            CarAssessFollowData follow = carAssessOrder.getFollow();
            long followId = idWorker.nextId();
            long order_id = idWorker.nextId();
            long orderlog_id = idWorker.nextId();

            int code = 0;
            if (follow != null) {
                //存评估跟进
                follow.setId(followId);
                follow.setFollowUser(managerUser.getId());
                followDataModel.insertCarAssessFollowData(follow);

                //存储采购订单
                carAssessOrder.setId(order_id);
                carAssessOrder.setCreateTime(new Date());
                carAssessOrder.setFollowId(followId);
                carAssessOrder.setStatus("1");//待审核
                code = model.insertCarAssessOrder(carAssessOrder);
                if (code > 0) {
                    //更改评估状态 为 3:提交审核（确认采购）
                    CarAssess assess = new CarAssess();
                    assess.setId(carAssessOrder.getAssessId());
                    assess.setStatus("3");
                    assess.setRegionId(carAssessOrder.getRegionId());
                    carAssessModel.updateCarAssess(assess);

                    //评估日志
                    logService.saveLog(managerUser, "申请采购", idWorker.nextId(), carAssessOrder.getAssessId());

                    //写入订单记录表
                    orderLogService.saveOrderLog(managerUser, "提交申请采购单！", "1", orderlog_id, order_id);

                    result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
                    assessPushMsg(managerUser, order_id);
                } else {
                    result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
                }
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), "没有发现跟进信息！");

            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    private void assessPushMsg(CarManagerUser managerUser, long order_id) {
        CarAppInfo carAppInfo = appInfoService.selectByType(CELLER);
        if (managerUser.getRoleTypeId().equals(Long.valueOf(CENTER_TYPE))){
            List<CarManagerUser> userList = userModel.selectByExample(Collections.singletonMap("departmentId", managerUser.getDepartmentId()));
            for (CarManagerUser user : userList) {
                // 二手车负责人 中心店
                if (user.getRoleTypeId().equals(Long.valueOf(CENTER_TYPE)) && user.getRoleId().equals(Long.valueOf(CENTER_INCHANGE))) {
                    JPushUtil.sendOrder(carAppInfo.getAppId(), new String[]{user.getId() + ""},AUDIT_ORDER_TITLE,AUDIT_ORDER_CONTENT,order_id +"");
                }
            }
        } else if (managerUser.getRoleTypeId().equals(Long.valueOf(STORE_TYPE))) {
            List<CarManagerUser> userList = userModel.selectByExample(Collections.singletonMap("departmentId", managerUser.getDepartmentId()));
            for (CarManagerUser user : userList) {
                // 二手车负责人 经销店
                if (user.getRoleTypeId().equals(Long.valueOf(STORE_TYPE)) && user.getRoleId().equals(Long.valueOf(STORE_INCHANGE))) {
                    JPushUtil.sendOrder(carAppInfo.getAppId(), new String[]{user.getId() + ""},AUDIT_ORDER_TITLE,AUDIT_ORDER_CONTENT,order_id +"");
                }
            }
        }
    }

    @Override
    @Transactional
    public ServiceResult<Map<String, Object>> editStatus(JSONObject obj, IdWorker idWorker) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        CarManagerUser managerUser = userModel.selectByPrimaryKey(obj.getLong("managerId"));

        CarAssessOrder carAssessOrder = JSONObject.toJavaObject(obj, CarAssessOrder.class);
        CarAssessOrder old = model.selectCarAssessOrderById(carAssessOrder.getId());
        if (carAssessOrder == null) {
            carAssessOrder = new CarAssessOrder();
        }
        //写入日日志表
        //审核通过
        String logMsg = "";
        if ("2".equals(carAssessOrder.getStatus())) {
            logMsg = "审核通过";
            //审核不通过
            //评估日志
            logService.saveLog(managerUser, "申请通过", idWorker.nextId(), old.getAssessId());
            //修改评估状态为 审核通过
            CarAssess assess = new CarAssess();
            assess.setId(old.getAssessId());
            assess.setStatus("5");
            assess.setRejectReason(obj.getString("rejectReason"));
            carAssessModel.updateCarAssess(assess);
        } else if ("-1".equals(carAssessOrder.getStatus())) {
            logMsg = "审核不通过";
            //评估日志
            logService.saveLog(managerUser, "申请不通过", idWorker.nextId(), old.getAssessId());

            //修改评估状态为 审核不通过
            CarAssess assess = new CarAssess();
            assess.setId(old.getAssessId());
            assess.setStatus("4");
            assess.setRejectReason(obj.getString("rejectReason"));
            carAssessModel.updateCarAssess(assess);
        } else if ("4".equals(carAssessOrder.getStatus())) {
            logMsg = "已付全款";
        }
        int code = model.updateCarAssessOrder(carAssessOrder);
        if (code > 0) {
            //写入订单记录表
            orderLogService.saveOrderLog(managerUser, logMsg, carAssessOrder.getStatus(), idWorker.nextId(), carAssessOrder.getId());
            if ("2".equals(carAssessOrder.getStatus())) {
                /*写入库存 car_auto car_auto_auction ...*/
                int backCode = writeAuto(old.getAssessId(), carAssessOrder.getId(), idWorker.nextId(), managerUser,idWorker);
            }
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } else {
            result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
        }

        return result;
    }

    /**
     * 写入汽车相关表
     *
     * @param assessId
     * @param orderId
     * @return
     */
    private int writeAuto(Long assessId, Long orderId, Long autoId, CarManagerUser managerUser,IdWorker idWorker) {
        /*更新评估表*/
        CarAssess t = new CarAssess();
        t.setId(assessId);
        t.setAutoId(autoId);
        carAssessModel.updateCarAssess(t);
        t.setAutoId(null);
        CarAssess a = carAssessModel.selectCarAssessById(t);
        CarAssessOrder oreder = model.selectCarAssessOrderById(orderId);
        CarManagerUser user = userModel.selectByPrimaryKey(a.getCreateUser());
        //拍卖信息id
        Long autoAuctionId = idWorker.nextId();
        /*写入car_auto*/
        saveCarAuto(autoId,a,oreder,autoAuctionId);
        /*写入car_auto_auction*/
        saveAution(autoAuctionId,autoId,user);
        /*car_auto_info_detail*/
        CarAutoInfoDetail detail = new CarAutoInfoDetail();
        detail.setId(idWorker.nextId());
        detail.setAutoId(autoId);
        detail.setVin(a.getVin());
        detail.setAutoBrand(a.getAutoBrand());
        detail.setAutoBrandCn(a.getAutoBrandCn());
        detail.setAutoSeries(a.getAutoSeries());
        detail.setAutoStyle(a.getAutoStyle());
        detail.setAutoStyleCn(a.getAutoStyleCn());
        detail.setEngineCapacity(a.getEngineCapacity() + "");
        detail.setEngineCapacityUnit("L");
        detail.setMileage(a.getMileage());
        detail.setColor(a.getColor());
        detail.setColorCn(a.getColorCn());
        detail.setManufactureDate(a.getManufactureDate());
        detail.setBeginRegisterDate(a.getBeginRegisterDate());
        detail.setLicenseNumber(a.getPlateNum());
        detail.setCarNature(a.getAutoNature());//Nature CN
        detail.setCarNatureCn(a.getAutoNatureCn());
        detail.setUseNature(a.getFunction());//Function CN
        detail.setUseNatureCn(a.getFunctionCn());
//        detail.setIsModification(oreder.getIfAdd());
        detail.setOriginalPrice(oreder.getNewCarPrice());
        detail.setRemark(a.getRemark());
        detail.setRemarkPhoto(a.getOtherPhoto());
        if(a.getRegionId() != null && !"".equals(a.getRegionId())){
            WtCity city = cityModel.findById(Long.parseLong(a.getRegionId()));
            WtProvince province = provinceModel.findById(city.getProvinceId());
            detail.setVehicleAttributionCity(a.getRegionId());
            detail.setVehicleAttributionCityCn(oreder.getCarAddress());
            detail.setVehicleAttributionProvince(province.getProvinceId()+"");
            detail.setVehicleAttributionProvinceCn(province.getProvinceName());
        }
        detail.setCreateTime(new Date());
        detail.setCreateUser(a.getCreateUser() + "");
        autoInfoDetailModel.insert(detail);
        /* car_auto_procedures */
        saveProcedures(autoId,idWorker,a,oreder,a.getCreateUser());
        /* car auto log*/
        CarAutoLog log = new CarAutoLog();
        log.setId(idWorker.nextId());
        log.setAutoId(autoId);
        log.setMsg("车辆采购审核通过，创建草稿车辆");
        log.setStatus(CarStatusEnum.DRAFT.value());
        log.setTime(new Date());
        log.setUserType("2");//'操作人类型  1买家 2管理员 3代办',
        log.setUserId(user.getId());
        log.setUserMobile(user.getUserPhone());
        log.setUserName(user.getUserName());
        autoLogService.insert(log);
        return 1;
    }

    /**
     * 保存car_auto
     */
    private int saveCarAuto(Long autoId,CarAssess a ,CarAssessOrder oreder, Long autoAuctionId){
        CarAuto auto = new CarAuto();
        auto.setId(autoId);
        auto.setAutoInfoName(a.getAutoBrandCn() + " " + a.getAutoSeriesCn() + " " + a.getAutoStyleCn());//车辆名称=品牌+车系+车型
        if(a.getCarPhoto() != null){
            String[] photoUrls = a.getCarPhoto().split(",");
            auto.setMainPhoto( photoUrls.length > 0 ? photoUrls[0]:null);
        }
        auto.setStoreId(oreder.getStoreId());
        auto.setReportColligationRanks(a.getReportColligationRanks());
        auto.setCreateUser(a.getCreateUser());
        auto.setCreateTime(new Date());
        auto.setUpdateUser(a.getCreateUser());
        auto.setUpdateTime(new Date());
        auto.setAuctionNum(0);
        if (a.getRegionId() != null) {
            auto.setRegionId(Long.parseLong(a.getRegionId()));
        }
        auto.setStatus(CarStatusEnum.DRAFT.value());//草稿
        auto.setAutoAuctionId(autoAuctionId);
        auto.setIfNew("1");
        return carAutoModel.insert(auto);
    }

    /**
     * 保存 car_auto_auction
     */
    private int saveAution(Long autoAuctionId,Long autoId,CarManagerUser user){
        CarAutoAuction auction = new CarAutoAuction();
        auction.setId(autoAuctionId);
        auction.setAutoId(autoId);
        auction.setCreatePerson(user.getId());
        auction.setCreateTime(new Date());
        auction.setAuctionType("-1");//待分配
//        Long roleTypeId = user.getRoleTypeId();
//        if (roleTypeId == 2) {
//            auction.setAuctionType("2");//线下
//        }
//        if (roleTypeId == 3) {
//            auction.setAuctionType("1");//线上
//        }
        auction.setStatus("1");
        auction.setDelFlag("0");
        auction.setBidsCount(0);
        auction.setBidersCount(0);
        return auctionModel.insert(auction);
    }

    /**
     * 手续信息
     */
    private int saveProcedures(Long autoId,IdWorker idWorker,CarAssess a,CarAssessOrder order,Long userId){
        CarAutoProcedures autoProcedures = new CarAutoProcedures();
        autoProcedures.setId(idWorker.nextId());
        autoProcedures.setAutoId(autoId);
        autoProcedures.setPurchaseTax(Long.valueOf(order.getPurchaseTax()));
        autoProcedures.setRegistrationCertificate(order.getRegistrationCertificate());
        autoProcedures.setMaintenanceManual(order.getMaintenanceManual());
        autoProcedures.setCompulsoryInsurance(order.getSaliEndDate());
        autoProcedures.setBusinessInsurance(order.getCommercialInsuranceEndDate());
        autoProcedures.setNewCarInvoice(order.getNewCarInvoice());
        autoProcedures.setCarKeys(order.getCarKeys());
        autoProcedures.setTransferNumber(a.getTransferNumber());
        autoProcedures.setDrivingLicense(order.getDrivingLicense());
        autoProcedures.setYearInsurance(a.getYearInsurance());
        autoProcedures.setCreateUser(userId);
        autoProcedures.setCreateTime(new Date());
        return proceduresModel.insert(autoProcedures);
    }

}
