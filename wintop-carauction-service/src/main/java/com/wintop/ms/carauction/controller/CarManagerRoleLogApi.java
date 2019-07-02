package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.ManagerRole;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarManagerRoleData;
import com.wintop.ms.carauction.entity.CarManagerRoleLog;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarManagerRoleDataService;
import com.wintop.ms.carauction.service.ICarManagerRoleLogService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 查询查博士查询权限表
 */
@RestController
@RequestMapping( "/service/carManagerRoleLog" )
public class CarManagerRoleLogApi {

    private static final Logger logger = LoggerFactory.getLogger(CarManagerRoleLogApi.class);
    private static final String USE = "1";

    @Autowired
    private ICarManagerRoleLogService carManagerRoleLogService;

    @Autowired
    private ICarManagerUserService carManagerUserService;

    @Autowired
    private ICarManagerRoleDataService carManagerRoleDataService;

    /***
     * 查询查博士查询权限商户审核列表
     * @return
     */
    @RequestMapping( value = "/list",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<ListEntity<CarManagerRoleLog>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarManagerRoleLog>> result = new ServiceResult<>();
        try {
            Long managerId = obj.getLong("managerId");
            CarManagerUser user = carManagerUserService.selectByPrimaryKey(managerId, false);
            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            param.put("storeId", user.getDepartmentId());
            int count = carManagerRoleLogService.selectByConditionCount(param);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            param.put("startRowNum", pageEntity.getStartRowNum());
            param.put("endRowNum", pageEntity.getEndRowNum());
            List<CarManagerRoleLog> list = carManagerRoleLogService.selectByCondition(param);
            ListEntity<CarManagerRoleLog> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询查博士查询权限商户审核列表", e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 查看是否存在查询权限
     */
    @RequestMapping( value = "/queryAudit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<Map<String, Object>> queryAudit(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        Long managerId = obj.getLong("managerId");
        Map<String, Object> resultMap = new HashMap<>();
        CarManagerUser user = carManagerUserService.selectByPrimaryKey(managerId, false);
        //类别 3：经销店
        if (!getJudgeRoleList(user.getRoleTypeId(), 3)) {
            resultMap.put("isRole", "0");
            result.setResult(resultMap);
            result.setSuccess(ResultCode.NO_AUDIT_VIEW_AUTH.strValue(), ResultCode.NO_AUDIT_VIEW_AUTH.getRemark());
            return result;
        }
        if (getJudgeRole(ManagerRole.JXD_ESCFZR, user.getRoleId())) {
            resultMap.put("isRole", "1");
            result.setResult(resultMap);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            return result;
        }
        List<CarManagerRoleData> carManagerRoleDataList = carManagerRoleDataService.selectForCondition(Collections.singletonMap("managerId", managerId));
        if (CollectionUtils.isNotEmpty(carManagerRoleDataList) && carManagerRoleDataList.get(0).getIsRole().equals(USE)) {
            resultMap.put("isRole", "1");
            result.setResult(resultMap);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            return result;
        } else {
            resultMap.put("isRole", "0");
            result.setResult(resultMap);
            result.setSuccess(ResultCode.NO_AUDIT_VIEW_AUTH.strValue(), ResultCode.NO_AUDIT_VIEW_AUTH.getRemark());
            return result;
        }
    }

    public boolean getJudgeRole(ManagerRole managerRole, Long roleId) {
        return Long.valueOf(managerRole.value() + "").equals(roleId);
    }

    public boolean getJudgeRoleList(Long roleTypeId, Integer... roleTypeIds) {
        return Arrays.asList(roleTypeIds).contains(roleTypeId.intValue());
    }

    /***
     * 保存或修改查博士查询权限商户
     * @return
     */
    @RequestMapping( value = "/saveOrUpdate",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<Map<String, Object>> saveOrUpdate(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            Long managerId = obj.getLong("managerId");
            CarManagerUser user = carManagerUserService.selectByPrimaryKey(managerId, false);
            CarManagerRoleLog log = JSONObject.toJavaObject(obj, CarManagerRoleLog.class);
            if (Objects.nonNull(log)) {
                carManagerRoleLogService.saveOrUpdate(log, user);
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.NO_PARAM.strValue(), ResultCode.NO_PARAM.getRemark());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("保存或修改查博士查询权限商户", e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 后端查博士查询权限商户
     * @return
     */
    @RequestMapping( value = "/listForPage",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<ListEntity<CarManagerRoleLog>> listForPage(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarManagerRoleLog>> result = null;
        try {
            CarManagerUser user = obj.getObject("user", CarManagerUser.class);

            Map param = JSONObject.toJavaObject(obj, Map.class);
            param = Maps.filterValues(param, Predicates.not(Predicates.equalTo("")));
            param.put("storeId", user.getDepartmentId());
            result = new ServiceResult<>();

            int count = carManagerRoleLogService.selectByConditionForCount(param);


            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            param.put("startRowNum", pageEntity.getStartRowNum());
            param.put("endRowNum", pageEntity.getEndRowNum());

            List<CarManagerRoleLog> list = carManagerRoleLogService.selectByConditionForPage(param);

            ListEntity<CarManagerRoleLog> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);

            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("后端查博士查询权限商户列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
