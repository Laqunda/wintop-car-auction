package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtCity;
import com.wintop.ms.carauction.entity.WtProvince;
import com.wintop.ms.carauction.service.impl.WtCityServiceImpl;
import com.wintop.ms.carauction.service.impl.WtProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 地区市接口服务
 */
@RestController
@RequestMapping("city")
public class WtCityApi {

    @Autowired
    private WtCityServiceImpl cityService;

    /***
     * 查询地区市详情
     * @param map
     * @return
     */
    @RequestMapping(value = "getById" ,method = RequestMethod.POST)
    public ServiceResult<WtCity> findById(@RequestBody Map map){
        Long id = Long.parseLong(map.get("cityId")+"");
        return cityService.findById(id);
    }

    /***
     * 获取全部地区市数据
     * @return
     */
    @RequestMapping(value = "getAll",method = RequestMethod.POST)
    public ServiceResult<List<WtCity>> findAll(){
        Map map = new HashMap();
        map.put("delFlag","0");
        return cityService.findAll(map);
    }

    /***
     * 根据省直辖市获取地区市信息
     * @return
     */
    @RequestMapping(value = "getByProvince",method = RequestMethod.POST)
    public ServiceResult<List<WtCity>> findAll(@RequestBody Map map){
        map.put("delFlag","0");
        return cityService.findAll(map);
    }

}
