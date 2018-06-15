package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.entity.TreeEntity;
import com.wintop.ms.carauction.service.ICarCenterService;
import com.wintop.ms.carauction.service.ICarManagerPageService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求接口地址Url
 */
@RestController
@RequestMapping("/service/carManagerPage")
public class CarManagerPageApi {
    private static final Logger logger = LoggerFactory.getLogger(CarManagerPageApi.class);
    @Autowired
    private ICarManagerPageService pageService;

    /***
     * 查询列表
     * @param obj
     * @return
     */
    @RequestMapping(value = "/getPageTreeList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<TreeEntity>> getPageTreeList(@RequestBody JSONObject obj) {
        ServiceResult<List<TreeEntity>> result = new ServiceResult<>();
        try {
            List<TreeEntity> list = pageService.getPageTreeList(obj.getLong("roleId"));
            if (list!=null && list.size()>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
            result.setResult(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }



    /***
     * 根据父节点查询
     * @return
     */
    @PostMapping(value = "/getPageTreeByPId",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<TreeEntity>> getPageTreeByPId(@RequestBody Map<String,Object> map) {
        ServiceResult<List<TreeEntity>> result = new ServiceResult<>();
        try {
            List<TreeEntity> list = pageService.getPageTreeByPId(map);
            if (list!=null && list.size()>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
            result.setResult(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据父节点查询",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

}
