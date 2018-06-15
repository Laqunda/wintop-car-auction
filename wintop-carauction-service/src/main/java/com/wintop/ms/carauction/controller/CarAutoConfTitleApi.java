package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoConfTitle;
import com.wintop.ms.carauction.service.ICarAutoConfTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 车辆配置信息基础选项
 */
@RestController
@RequestMapping("autoConfTitle")
public class CarAutoConfTitleApi {
    @Autowired
    private ICarAutoConfTitleService confTitleService;


    /***
     * 查询所有启用的车辆配置信息
     * @return
     */
    @PostMapping(value = "getAllApp")
    public ServiceResult<List<CarAutoConfTitle>> getAll(@RequestBody Map map){
        ServiceResult<List<CarAutoConfTitle>> result = new ServiceResult<>();
        try {
            map.put("status","1");
            List<CarAutoConfTitle> list = confTitleService.selectAll(map);
            for (CarAutoConfTitle confTitle:list){
                //根据配置项多少，封装选项返回
                if (confTitle.getOptionSize()==2){
                    confTitle.setOptionList(Constants.AUTO_CONF_OPTION_TWO);
                }else if (confTitle.getOptionSize()==3){
                    confTitle.setOptionList(Constants.AUTO_CONF_OPTION_THREE);
                }
            }
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }
}
