package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtProvince;
import com.wintop.ms.carauction.service.impl.WtProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 省直辖市接口服务
 */
@RestController
@RequestMapping("province")
public class WtProvinceApi {

    @Autowired
    private WtProvinceServiceImpl provinceService;

    /***
     * 查询直辖市详情
     * @param map
     * @return
     */
    @RequestMapping(value = "getById" ,method = RequestMethod.POST)
    public ServiceResult<WtProvince> findById(@RequestBody Map map){
        Long id = Long.parseLong(map.get("provinceId")+"");
        return provinceService.findById(id);
    }

    /***
     * 获取全部省直辖市数据
     * @return
     */
    @RequestMapping(value = "getAll",method = RequestMethod.POST)
    public ServiceResult<List<WtProvince>> findAll(){
        Map map = new HashMap();
        map.put("delFlag","0");
        return provinceService.findAll(map);
    }

}
