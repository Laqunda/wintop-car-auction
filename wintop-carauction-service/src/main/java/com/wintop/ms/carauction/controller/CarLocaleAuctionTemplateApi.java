package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.service.ICarLocaleAuctionService;
import com.wintop.ms.carauction.service.ICarLocaleAuctionTemplateService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.IWtAppUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 现场拍主题表
 *
 * @author mazg
 * @date 2019-05-23 17:40:13
 */
@RestController
@RequestMapping( "/service/template" )
public class CarLocaleAuctionTemplateApi {

    private static final Logger logger = LoggerFactory.getLogger(CarLocaleAuctionTemplateApi.class);

    private static final Map<Integer, String> weekMap = new HashMap<Integer, String>() {{
        put(7, "星期日");
        put(1, "星期一");
        put(2, "星期二");
        put(3, "星期三");
        put(4, "星期四");
        put(5, "星期五");
        put(6, "星期六");
    }};

    private static Map<String, List<String>> statusList = convertStatusMap();

    private static Map<String, List<String>> convertStatusMap() {
        return new HashMap<String, List<String>>() {{
            put("1", Arrays.asList("2", "3", "4"));
        }};
    }

    @Autowired
    private ICarLocaleAuctionTemplateService carLocaleAuctionTemplateService;
    @Autowired
    private ICarManagerUserService managerUserService;

    @Autowired
    private ICarLocaleAuctionService carLocaleAuctionService;

    @Autowired
    private IWtAppUserService appUserService;

    /**
     * 查询分页列表
     *
     * @param obj
     * @return
     */
    @PostMapping( value = "/list",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<ListEntity<CarLocaleAuctionTemplate>> list(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarLocaleAuctionTemplate>> result = new ServiceResult<ListEntity<CarLocaleAuctionTemplate>>();
        logger.info("查询主题设置列表");
        CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
        if (carLocaleAuctionTemplate == null) {
            carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
        }
        try {
            int count = carLocaleAuctionTemplateService.count(carLocaleAuctionTemplate);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            carLocaleAuctionTemplate.setStartRowNum(pageEntity.getStartRowNum());
            carLocaleAuctionTemplate.setEndRowNum(pageEntity.getEndRowNum());

            List<CarLocaleAuctionTemplate> list = carLocaleAuctionTemplateService.list(carLocaleAuctionTemplate);

            ListEntity<CarLocaleAuctionTemplate> listEntity = new ListEntity<>();
            listEntity.setList(list);
            listEntity.setCount(count);
            result.setResult(listEntity);

            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询主题设置列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    @PostMapping( value = "/allList",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<Map<String, Object>> allList(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        logger.info("查询主题设置列表");
        CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
        if (carLocaleAuctionTemplate == null) {
            carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
        }
        try {
            List<CarLocaleAuctionTemplate> list = carLocaleAuctionTemplateService.list(carLocaleAuctionTemplate);
            result.setResult(Collections.singletonMap("list", list));
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询主题设置列表", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询主题设置详情
     */
    @ApiOperation( value = "查询主题设置详情" )
    @RequestMapping( value = "/detail",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<CarLocaleAuctionTemplate> detail(@RequestBody JSONObject obj) {
        ServiceResult<CarLocaleAuctionTemplate> result = null;
        try {
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            result = new ServiceResult<>();

            carLocaleAuctionTemplate = carLocaleAuctionTemplateService.get(carLocaleAuctionTemplate.getId());
            result.setResult(carLocaleAuctionTemplate);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        } catch (Exception e) {
            logger.info("查询主题设置详情", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 修改保存车辆评估
     */
    @RequestMapping( value = "/save",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<Map<String, Object>> save(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            carLocaleAuctionTemplate.setEditTime(new Date());
            carLocaleAuctionTemplate.setEditor(managerUser.getUserName());
            int code = carLocaleAuctionTemplateService.insertSelective(carLocaleAuctionTemplate);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 修改保存车辆评估
     */
    @RequestMapping( value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<Map<String, Object>> editSave(@RequestBody JSONObject obj) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("managerId"), false);
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            carLocaleAuctionTemplate.setEditTime(new Date());
            carLocaleAuctionTemplate.setEditor(managerUser.getUserName());
            int code = carLocaleAuctionTemplateService.updateByPrimaryKeySelective(carLocaleAuctionTemplate);
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());

        }
        return result;
    }

    /**
     * 删除主题设置
     */
    @ApiOperation( value = "删除主题设置" )
    @RequestMapping( value = "/remove",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<Map<String, Object>> remove(@RequestBody JSONObject obj) {

        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            CarLocaleAuctionTemplate carLocaleAuctionTemplate = JSONObject.toJavaObject(obj, CarLocaleAuctionTemplate.class);
            if (carLocaleAuctionTemplate == null) {
                carLocaleAuctionTemplate = new CarLocaleAuctionTemplate();
            }
            int code = carLocaleAuctionTemplateService.delete(carLocaleAuctionTemplate.getId());
            if (code > 0) {
                result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            } else {
                result.setSuccess(ResultCode.FAIL.strValue(), ResultCode.FAIL.getRemark());
            }
        } catch (Exception e) {
            logger.info("删除主题设置", e);
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 获取场次汇总列表
     * @param obj
     * @return
     */
    @RequestMapping( value = "/selectAuctionTotalList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    public ServiceResult<ListEntity<AuctionListEntity<Map<String, Object>>>> selectAuctionTotalList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<AuctionListEntity<Map<String, Object>>>> result = new ServiceResult<>();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            if (obj.getLong("userId") != null && !"".equals(obj.getLong("userId") + "")) {
                //获取会员所属组
                WtAppUser appUser = appUserService.getUserInfoById(obj.getLong("userId")).getResult();
                if (null != appUser.getGroupIds() && !"".equals(appUser.getGroupIds())) {
                    paramMap.put("groupIds", appUser.getGroupIds());
                }
            }
            if (StringUtils.isNotEmpty(obj.getString("regionIds"))) {
                String regionIds = obj.getString("regionIds");
                paramMap.put("regionIds", Splitter.on(",").splitToList(regionIds).stream().map(a -> Longs.tryParse(a)).collect(Collectors.toList()));
            }
            List<AuctionListEntity<Map<String, Object>>> list = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                AuctionListEntity<Map<String, Object>> auctionListEntity = new AuctionListEntity<>();
                List<Map<String, Object>> dataList = new ArrayList<>();
                List<CarLocaleAuction> carAuctions = new ArrayList<>();
                Date[] dates = CarAutoUtils.getCurrentAfterDays(0);
                if (i == 0) {
                    auctionListEntity.setTitle(String.format("今天%s", CarAutoUtils.getMonthAndDay(dates[0])));
                    //今天开场
                    paramMap.put("beginTime", dates[0]);
                    paramMap.put("endTime", dates[1]);
                    //状态为 2等待开拍，3正在竞拍 4竞拍结束
                    paramMap.put("statusList", statusList.get("1"));
                    carAuctions = carLocaleAuctionService.queryAuctionListByParams(paramMap).getResult();
                } else if (i == 1) {
                    dates = CarAutoUtils.getCurrentAfterDays(1);
                    auctionListEntity.setTitle(String.format("明天%s", CarAutoUtils.getMonthAndDay(dates[0])));
                    paramMap.put("beginTime", dates[0]);
                    paramMap.put("endTime", dates[1]);
                } else if (i >= 2) {
                    dates = CarAutoUtils.getCurrentAfterDays(i);
                    auctionListEntity.setTitle(String.format("%s%s", weekMap.get(CarAutoUtils.getDayOfWeek(dates[0])), CarAutoUtils.getMonthAndDay(dates[0])));
                    paramMap.put("beginTime", dates[0]);
                    paramMap.put("endTime", dates[1]);
                }
                List<Map<String, Object>> endList = new ArrayList<>();
                int week = CarAutoUtils.getDayOfWeek(dates[0]);
                paramMap.put("week", week - 1);
                List<CarLocaleAuctionTemplate> templates = carLocaleAuctionTemplateService.selectAuctionListForApp(paramMap).getResult();
                if (carAuctions.size() > 0) {
                    //讲今天开场的场次与主题模板作比较，开场的和未开场的返回
                    for (CarLocaleAuction carAuction : carAuctions) {
                        if (templates == null || templates.size() < 1) {
                            dataList.add(getMap(carAuction));
                        }
                        for (int t = 0; t < templates.size(); t++) {
                            //如果是主题id相同 或者 匹配到最后没有相同的主题id
                            if (carAuction.getTemplateId().equals(templates.get(t).getId())
                                    || templates.size() == t) {
                                Map<String, Object> map = getMap(carAuction);
                                //如果是竞拍结束的场次
                                if (carAuction.getStatus().equals("4")) {
                                    endList.add(map);
                                } else {
                                    dataList.add(map);
                                }
                            }
                            //主题相同的将 主题模板集合移除并跳过此次循环
                            if (carAuction.getTemplateId().equals(templates.get(t).getId())) {
                                templates.remove(templates.get(t));
                                continue;
                            }
                        }
                    }
                }
                if (templates != null && templates.size() > 0) {
                    for (CarLocaleAuctionTemplate template : templates) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", "");
                        map.put("templateId", template.getId());
                        map.put("title", template.getTitle());
                        map.put("localStartTime", template.getStartTime());
                        map.put("poster", template.getPoster());
                        map.put("status", "0");//待拍
                        dataList.add(map);
                    }
                }
                if (endList.size() > 0) {
                    dataList.addAll(endList);
                }
                auctionListEntity.setDataList(dataList);
                auctionListEntity.setCount(dataList.size());
                list.add(auctionListEntity);
            }
            ListEntity<AuctionListEntity<Map<String, Object>>> listEntity = new ListEntity<>();
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("list", list);
            resultMap.put("carAuctions", list);
            listEntity.setList(list);
            listEntity.setCount(list.size());
            result.setResult(listEntity);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取场次汇总列表失败", e);
            result.setError("-1", "异常");
        }
        return result;
    }

    private Map<String, Object> getMap(CarLocaleAuction carAuction) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", carAuction.getId());
        map.put("templateId", carAuction.getTemplateId());
        map.put("title", carAuction.getTitle());
        map.put("localStartTime", new SimpleDateFormat("HH:mm").format(carAuction.getStartTime()));
        map.put("poster", carAuction.getPoster());
        map.put("status", carAuction.getStatus());
        return map;
    }
}
