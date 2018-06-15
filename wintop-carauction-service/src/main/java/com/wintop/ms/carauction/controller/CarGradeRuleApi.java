package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarGradeRule;
import com.wintop.ms.carauction.entity.CarGradeType;
import com.wintop.ms.carauction.service.impl.CarAutoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:车辆的评级规则
 * @date 2018-03-07
 */
@RestController
@RequestMapping("/service/gradeRule")
public class CarGradeRuleApi {

    @Autowired
    private CarAutoServiceImpl autoService;

    /**
     * 查询车辆评级规则
     * @return
     */
    @ApiOperation(value = "查询车辆评级规则")
    @RequestMapping(value = "/getCarGradeRule",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarGradeType>> getCarGradeRule() {
        ServiceResult<List<CarGradeType>> result=new ServiceResult<>();
        List<CarGradeType> carGradeTypes=new ArrayList<>();
        CarGradeType gradeType1=new CarGradeType();
        gradeType1.setType("1");
        gradeType1.setTypeName("综合车况");
        List<CarGradeRule> subList1=new ArrayList<>();
        //综合车况
        CarGradeRule gradeRule1=new CarGradeRule();
        gradeRule1.setType("1");
        gradeRule1.setGradeName("A");
        gradeRule1.setContent("1.车身。底盘及骨架（不含保险杠）没有碰撞痕迹。2.发动机和变速箱无异常。");
        subList1.add(gradeRule1);
        CarGradeRule gradeRule2=new CarGradeRule();
        gradeRule2.setType("1");
        gradeRule2.setGradeName("B");
        gradeRule2.setContent("1.前后盖、前叶子板或者车门有碰撞或者更换的痕迹，底盘轻微刮擦。2.发动机和变速箱功能正常，有轻微的抖动和异响。");
        subList1.add(gradeRule2);
        CarGradeRule gradeRule3=new CarGradeRule();
        gradeRule3.setType("1");
        gradeRule3.setGradeName("C");
        gradeRule3.setContent("1.水箱框架、前叶子板内衬、后叶子板、后围板有更换或者修复痕迹，底盘有严重刮伤或者锈蚀。2.发动机异响严重，加速无力，无高速，怠速抖动，冒黑烟。手动变速器挂挡不顺畅，自动变速轻微换挡冲击或者换挡延迟。。");
        subList1.add(gradeRule3);
        CarGradeRule gradeRule4=new CarGradeRule();
        gradeRule4.setType("1");
        gradeRule4.setGradeName("D");
        gradeRule4.setContent("1.梁架、内板、柱、防火墙有碰撞或者切割的痕迹。2.发动机杂乱异响或烧机油，变速箱不走车或者严重闯档。车辆泡水痕迹，火烧痕迹。");
        subList1.add(gradeRule4);
        gradeType1.setCarGradeRules(subList1);
        carGradeTypes.add(gradeType1);
        //整备成本
        CarGradeType gradeType2=new CarGradeType();
        gradeType2.setType("2");
        gradeType2.setTypeName("整备成本");
        List<CarGradeRule> subList2=new ArrayList<>();
        CarGradeRule gradeRule5=new CarGradeRule();
        gradeRule5.setType("2");
        gradeRule5.setGradeName("++");
        gradeRule5.setContent("1.车身无明显伤痕，漆面状况为原车漆，内饰干净整洁，电器设备及仪表功能正常。");
        subList2.add(gradeRule5);
        CarGradeRule gradeRule6=new CarGradeRule();
        gradeRule6.setType("2");
        gradeRule6.setGradeName("+");
        gradeRule6.setContent("1.车身有细小伤痕，喷漆修复低于30%的，无钣金修复，内饰为正常使用痕迹，有轻微污渍和磨损，主要电器设备功能正常。");
        subList2.add(gradeRule6);
        CarGradeRule gradeRule7=new CarGradeRule();
        gradeRule7.setType("2");
        gradeRule7.setGradeName("-");
        gradeRule7.setContent("1.车身有明显漆面伤痕或色差龟裂，需喷漆修复或者钣金修复超过30%,内饰有不易去除的污渍或磨损，部分电器设备功能需修理。");
        subList2.add(gradeRule7);
        CarGradeRule gradeRule8=new CarGradeRule();
        gradeRule8.setType("2");
        gradeRule8.setGradeName("--");
        gradeRule8.setContent("1.车身有多处漆面伤痕，需钣金修复；内饰大面积污损或部件破损，多种电器功能失效需更换。");
        subList2.add(gradeRule8);
        result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        gradeType2.setCarGradeRules(subList2);
        carGradeTypes.add(gradeType2);
        result.setResult(carGradeTypes);
        return result;
    }

    /**
     * 查询车辆评级规则
     * @return
     */
    @ApiOperation(value = "查询某车辆评级规则")
    @RequestMapping(value = "/getCarGradeRuleAuto",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> getCarGradeRuleAuto(@RequestBody JSONObject object) {
        Map map = new HashMap();
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        if (object!=null && object.getLong("autoId")!=null){
            //获取车辆的已有检测报告
            CarAuto auto = autoService.selectByPrimaryKey(object.getLong("autoId")).getResult();
            if (auto!=null){
                map.put("reportColligationRanks",auto.getReportColligationRanks());
                map.put("reportServicingRanks",auto.getReportServicingRanks());
            }
        }
        List<CarGradeType> carGradeTypes=new ArrayList<>();
        CarGradeType gradeType1=new CarGradeType();
        gradeType1.setType("1");
        gradeType1.setTypeName("综合车况");
        List<CarGradeRule> subList1=new ArrayList<>();
        //综合车况
        CarGradeRule gradeRule1=new CarGradeRule();
        gradeRule1.setType("1");
        gradeRule1.setGradeName("A");
        gradeRule1.setContent("1.车身。底盘及骨架（不含保险杠）没有碰撞痕迹。2.发动机和变速箱无异常。");
        subList1.add(gradeRule1);
        CarGradeRule gradeRule2=new CarGradeRule();
        gradeRule2.setType("1");
        gradeRule2.setGradeName("B");
        gradeRule2.setContent("1.前后盖、前叶子板或者车门有碰撞或者更换的痕迹，底盘轻微刮擦。2.发动机和变速箱功能正常，有轻微的抖动和异响。");
        subList1.add(gradeRule2);
        CarGradeRule gradeRule3=new CarGradeRule();
        gradeRule3.setType("1");
        gradeRule3.setGradeName("C");
        gradeRule3.setContent("1.水箱框架、前叶子板内衬、后叶子板、后围板有更换或者修复痕迹，底盘有严重刮伤或者锈蚀。2.发动机异响严重，加速无力，无高速，怠速抖动，冒黑烟。手动变速器挂挡不顺畅，自动变速轻微换挡冲击或者换挡延迟。。");
        subList1.add(gradeRule3);
        CarGradeRule gradeRule4=new CarGradeRule();
        gradeRule4.setType("1");
        gradeRule4.setGradeName("D");
        gradeRule4.setContent("1.梁架、内板、柱、防火墙有碰撞或者切割的痕迹。2.发动机杂乱异响或烧机油，变速箱不走车或者严重闯档。车辆泡水痕迹，火烧痕迹。");
        subList1.add(gradeRule4);
        gradeType1.setCarGradeRules(subList1);
        carGradeTypes.add(gradeType1);
        //整备成本
        CarGradeType gradeType2=new CarGradeType();
        gradeType2.setType("2");
        gradeType2.setTypeName("整备成本");
        List<CarGradeRule> subList2=new ArrayList<>();
        CarGradeRule gradeRule5=new CarGradeRule();
        gradeRule5.setType("2");
        gradeRule5.setGradeName("++");
        gradeRule5.setContent("1.车身无明显伤痕，漆面状况为原车漆，内饰干净整洁，电器设备及仪表功能正常。");
        subList2.add(gradeRule5);
        CarGradeRule gradeRule6=new CarGradeRule();
        gradeRule6.setType("2");
        gradeRule6.setGradeName("+");
        gradeRule6.setContent("1.车身有细小伤痕，喷漆修复低于30%的，无钣金修复，内饰为正常使用痕迹，有轻微污渍和磨损，主要电器设备功能正常。");
        subList2.add(gradeRule6);
        CarGradeRule gradeRule7=new CarGradeRule();
        gradeRule7.setType("2");
        gradeRule7.setGradeName("-");
        gradeRule7.setContent("1.车身有明显漆面伤痕或色差龟裂，需喷漆修复或者钣金修复超过30%,内饰有不易去除的污渍或磨损，部分电器设备功能需修理。");
        subList2.add(gradeRule7);
        CarGradeRule gradeRule8=new CarGradeRule();
        gradeRule8.setType("2");
        gradeRule8.setGradeName("--");
        gradeRule8.setContent("1.车身有多处漆面伤痕，需钣金修复；内饰大面积污损或部件破损，多种电器功能失效需更换。");
        subList2.add(gradeRule8);
        result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        gradeType2.setCarGradeRules(subList2);
        carGradeTypes.add(gradeType2);

        map.put("carGrade",carGradeTypes);
        result.setResult(map);
        return result;
    }
}
