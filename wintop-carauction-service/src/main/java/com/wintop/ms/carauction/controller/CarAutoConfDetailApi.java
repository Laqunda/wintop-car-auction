package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.service.ICarAutoConfDetailService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * 车辆配置信息基础选项
 */
@RestController
@RequestMapping("autoConfTitle")
public class CarAutoConfDetailApi {
    IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarAutoConfDetailService confDetailService;

    @ApiOperation(value = "保存车辆配置信息")
    @PostMapping(value = "saveOption")
    public ServiceResult insertArr(@ApiParam(value = "车辆配置信息json,含车辆id，配置集合") @RequestBody JSONObject object){
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            List<CarAutoConfDetail> optionList = new ArrayList<>();
            JSONArray jsonArray = object.getJSONArray("optionArr");
            CarAutoConfDetail confDetail = null;
            for (Object option:jsonArray){
                JSONObject optionJson = (JSONObject) option;
                confDetail = new CarAutoConfDetail();
                confDetail.setId(idWorker.nextId());
                confDetail.setAutoId(Long.parseLong(object.get("autoId").toString()));
                confDetail.setConfOption(optionJson.getString("confOption"));
                confDetail.setConfOptionCn(optionJson.getString("confOptionCn"));
                confDetail.setConfTitleId(optionJson.getLong("confTitleId"));
                confDetail.setConfTitleName(optionJson.getString("confTitleName"));
                optionList.add(confDetail);
            }
            if (confDetailService.insertArr(optionList,Long.parseLong(object.get("autoId").toString()))==optionList.size()){
                result.setSuccess("0","成功");
            }else {
                result.setError("-1","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","保存失败");
        }finally {
            return result;
        }
    }
}