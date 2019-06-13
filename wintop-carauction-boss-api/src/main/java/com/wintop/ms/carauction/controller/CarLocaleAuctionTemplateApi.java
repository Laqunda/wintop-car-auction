package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/template")
public class CarLocaleAuctionTemplateApi {

    private final RestTemplate restTemplate;

    public CarLocaleAuctionTemplateApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static Map<String, String> weekMap = new HashMap<String, String>(){{
        put("0", "星期日");
        put("1", "星期一");
        put("2", "星期二");
        put("3", "星期三");
        put("4", "星期四");
        put("5", "星期五");
        put("6", "星期六");
    }};
    @PostMapping(value = "/list",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResultModel list(@RequestBody Map<String,Object> map){
        if (map.get("page") == null || map.get("limit") == null) {
            return new ResultModel(false, ResultCode.NO_PARAM.value(), ResultCode.NO_PARAM.getRemark(), null);
        }

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/template/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/allList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResultModel allList(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/template/allList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询主题设置详情
     */
    @ApiOperation(value = "查询主题设置详情")
    @PostMapping(value = "/detail",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ResultModel detail(@RequestBody Map<String,Object> map) {

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/template/detail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    /**
     * 保存主题设置
     */
    @RequestMapping(value = "/save",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel save(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/template/save"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改主题设置
     */
    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel edit(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if (map.get("id") == null) {
            return new ResultModel(false, ResultCode.NO_PARAM.value(), ResultCode.NO_PARAM.getRemark(), null);
        }
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/template/edit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除主题设置
     */
    @ApiOperation(value = "删除主题设置")
    @PostMapping(value = "/remove",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel remove(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/template/remove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @AuthPublic
    @PostMapping( value = "/export" )
    public void export(HttpServletRequest request, HttpServletResponse rep,
                        @RequestParam("regionId") Long regionId,
                        @RequestParam("title") String title) {
        String[] headers = {"城市","场次名称","详细地址","开拍日期"};  //,"开拍时间"
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("title", title);
        map.put("regionId", regionId);
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("主题记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/template/allList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        HSSFSheet sheet = workbook.getSheetAt(0);
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONObject("result").getJSONArray("list");
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    JSONObject object = result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i + 2);
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(object.getString("cityName"));

                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(object.getString("title"));

                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(object.getString("address"));


                    JSONArray weekArray = object.getJSONArray("weekList");
                    List<String> weekList = Lists.newArrayList();
                    for (int j = 0; j < weekArray.size(); j++) {
                        JSONObject temp = weekArray.getJSONObject(j);
                        weekList.add(temp.getString("themeWeek"));
                    }
                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(getWeekShow(weekList));


//                    HSSFCell c4 = itemRow.createCell(4);
//                    c4.setCellValue(object.getString("startTime"));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "主题信息列表")).concat(".xls");
        rep.setContentType("application/vnd.ms-excel;charset=utf-8");
        rep.setHeader("Content-disposition", "attachment;filename=" + filename);
        try {
            ServletOutputStream ouputStream = rep.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWeekShow(List<String> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<String> tempList = Lists.newArrayList();
            list.forEach(item->{
                tempList.add(weekMap.get(item));
            });
            return Joiner.on(",").join(tempList);

        }
        return null;
    }
}
