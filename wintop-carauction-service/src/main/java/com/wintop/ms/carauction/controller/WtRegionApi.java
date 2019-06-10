package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.WtRegion;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.IWtRegionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangtingsen on 2018/3/6.
 * 地区城市api
 */
@RestController
@RequestMapping("/wtRegion")
public class WtRegionApi {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(WtRegionApi.class);

    @Autowired
    private IWtRegionService iWtRegionService;

    @Autowired
    private ICarManagerUserService managerUserService;

    /***
     * 根据id查询地区
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method = RequestMethod.POST,/*指定请求的method类型， GET、POST、PUT、DELETE等；*/
            consumes = "application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces = "application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<WtRegion> getById(@RequestBody Long id) {
        Logger.info("根据id查询地区");
        return this.iWtRegionService.findById(id);
    }

    /***
     * 根据编号code查询地区
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method = RequestMethod.POST,/*指定请求的method类型， GET、POST、PUT、DELETE等；*/
            consumes = "application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces = "application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<WtRegion> getByCode(@RequestBody String code) {
        Logger.info("根据code查询地区");
        return this.iWtRegionService.findByCode(code);
    }

    /***
     * 查询全部城市地区
     * @param status
     * @return
     */
    @RequestMapping(value = "/getAll",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method = RequestMethod.POST/*指定请求的method类型， GET、POST、PUT、DELETE等；*/)
    public ServiceResult<List<WtRegion>> getAll(@RequestBody String status) {
        Logger.info("根据code查询地区");
        return this.iWtRegionService.findAll(status);
    }

    /***
     * 查询全部城市地区
     * @param obj
     * boolon isAll
     * long regionId
     * status
     * @return
     */
    @RequestMapping(value = "/getAllByUser",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method = RequestMethod.POST/*指定请求的method类型， GET、POST、PUT、DELETE等；*/)
    public ServiceResult<List<WtRegion>> getAllByUserRegion(@RequestBody JSONObject obj) {
        Logger.info("根据code查询地区");
        String status = "1";
        if (obj.get("status") != null) {
            status = obj.getString("status");
        }

        CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("userId"),true);
        Boolean isAll = false;
        Long regionId = new Long(-1);
        if (managerUser != null) {
            if (1 == managerUser.getRoleTypeId()) {
                /*平台用户*/
                isAll = true;
            } else {
                /*其他用户*/
                regionId = managerUser.getRegionId();
            }
        }

        if (isAll)
            return this.iWtRegionService.findAll(status);
        else {
            ServiceResult<List<WtRegion>> result = new ServiceResult<>();

            ServiceResult<WtRegion> region = this.iWtRegionService.findById(regionId);
            WtRegion r = region.getResult();
            if (r == null) {
                result.setResult(new ArrayList<>());
            } else {
                List<WtRegion> regions = new ArrayList<>();
                regions.add(r);
                result.setResult(regions);
            }
            return result;
        }
    }


}
