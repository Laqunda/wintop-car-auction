package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtCity;
import com.wintop.ms.carauction.entity.WtDistrict;
import com.wintop.ms.carauction.service.impl.WtCityServiceImpl;
import com.wintop.ms.carauction.service.impl.WtDistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 区县
 */
@RestController
@RequestMapping("district")
public class WtDistrictApi {

    @Autowired
    private WtDistrictServiceImpl districtService;

    /***
     * 查询区县详情
     * @param map
     * @return
     */
    @RequestMapping(value = "getById" ,method = RequestMethod.POST)
    public ServiceResult<WtDistrict> findById(@RequestBody Map map){
        Long id = Long.parseLong(map.get("districtId")+"");
        return districtService.findById(id);
    }

    /***
     * 获取全部区县信息
     * @return
     */
    @RequestMapping(value = "getAll",method = RequestMethod.POST)
    public ServiceResult<List<WtDistrict>> findAll(){
        Map map = new HashMap();
        map.put("delFlag","0");
        return districtService.findAll(map);
    }

    /***
     * 根据地区市获取区县信息
     * @return
     */
    @RequestMapping(value = "getByCity",method = RequestMethod.POST)
    public ServiceResult<List<WtDistrict>> findAll(@RequestBody Map map){
        map.put("delFlag","0");
        return districtService.findAll(map);
    }

}
