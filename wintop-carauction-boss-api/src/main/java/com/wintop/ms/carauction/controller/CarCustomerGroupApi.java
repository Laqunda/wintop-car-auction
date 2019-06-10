package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:会员分组
 * @date 2018-03-16
 */
@RestController
@RequestMapping(value = "/group")
public class CarCustomerGroupApi {
    private final RestTemplate restTemplate;
    CarCustomerGroupApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询用户分组及对应的用户数量
     *@Author:zhangzijuan
     *@date 2018-03-16
     *@param:map
     */
    @ApiOperation(value = "查询用户分组及对应的用户数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName",value = "姓名、手机号模糊搜索",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "status",value = "会员状态",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "storeId",value = "店铺ID",required = false,paramType = "query",dataType = "long"),
    })
    @PostMapping(value = "/getGroupAndNum",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getGroupAndNum(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/selectGroupAndNum"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 查询用户分组及对应的用户数量
     *@Author:zhangzijuan
     *@date 2018-03-16
     *@param:map
     */
    @ApiOperation(value = "新建会员分组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName",value = "客户分组名称",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "会员状态",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/saveGroup",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveGroup(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("groupName")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createManager",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/saveGroup"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询用户分组及对应的用户数量
     *@Author:zhangzijuan
     *@date 2018-03-16
     *@param:map
     */
    @ApiOperation(value = "查询用户分组及对应的用户数量")
    @PostMapping(value = "/selectGroupAndNum",produces="application/json; charset=UTF-8",consumes="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectGroupAndNum(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        map.put("createManager",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/selectGroupAndNum"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    @AuthPublic
    @PostMapping(value = "/exportUserList",produces="application/json; charset=UTF-8")
    public void exportUserList(HttpServletRequest request, HttpServletResponse rep,
                                    @RequestParam(value = "searchName",required = false) String searchName,
                                    @RequestParam(value = "status",required = false) String status,
                                    @RequestParam(value = "groupId",required = false) Long groupId,
                                    @RequestParam(value = "storeId",required = false) Long storeId,
                                    @RequestParam(value = "appId",required = false) String appId,
                                    @RequestParam(value = "managerId",required = false) @CurrentUserId Long managerId){
        String[] headers={"车商号","拍牌号","电子拍牌","姓名","注册手机号","所属店铺","会员级别","会员分组","创建时间","会员状态"};
        HSSFWorkbook workbook= ExcelUtil.createStartExcel("出价记录",headers);
        Map<String,Object> map=new HashMap<>();
        map.put("searchName", searchName);
        map.put("status", status);
        map.put("groupId", groupId);
        map.put("storeId", storeId);
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/searchByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        ApiUtil.getResultModel(response,ApiUtil.OBJECT);
        HSSFSheet sheet=workbook.getSheetAt(0);
        if(response.getStatusCode()== HttpStatus.OK){
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONObject("result").getJSONArray("list");
            if (result!=null && result.size()>0){
                for (int i=0;i<result.size();i++){
                    JSONObject object=result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i+2);
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(object.getString("auctionPlateNum"));

                    HSSFCell c12 = itemRow.createCell(1);
                    c12.setCellValue(object.getString("boardRealId"));

                    HSSFCell c1 = itemRow.createCell(2);
                    c1.setCellValue(object.getString("name"));

                    HSSFCell c2 = itemRow.createCell(3);
                    c2.setCellValue(object.getString("mobile"));

                    HSSFCell c3 = itemRow.createCell(4);
                    c3.setCellValue(object.getString("userStoreName"));

                    HSSFCell c4 = itemRow.createCell(5);
                    c4.setCellValue(object.getString("levelName"));

                    HSSFCell c5 = itemRow.createCell(6);
                    c5.setCellValue(object.getString("groupName"));

                    HSSFCell c6 = itemRow.createCell(7);
                    c6.setCellValue(object.getString("statusName"));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request,"会员信息列表")).concat(".xls");
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

    /**
     * 获取用户角色
     *@Author:mazg
     *@date 2018-03-16
     *@param:map
     */
    @AuthUserToken
    @ApiOperation(value = "获取用户角色")
    @PostMapping(value = "/selectUserRole",produces="application/json; charset=UTF-8",consumes="application/json; charset=UTF-8")
    public ResultModel selectUserRole(@RequestBody Map<String,Object> map,@CurrentUserId Long managerId) {
        if (MapUtils.isEmpty(map)) {
            map = Maps.newHashMap();
        }
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/selectUserRole"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 查询所有可以选择的用户分组
     *@Author:zhangzijuan
     *@date 2018-03-17
     *@param:map
     */
    @ApiOperation(value = "查询所有可以选择的用户分组")
    @PostMapping(value = "/getGroupForSelect",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getGroupForSelect() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/selectGroupForSelect"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 删除会员分组
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:map
     */
    @ApiOperation(value = "删除会员分组")
    @ApiImplicitParam(dataType = "Long", name = "groupId", value = "会员级别Id", required = true)
    @PostMapping(value = "/deleteGroupById",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteGroupById(@RequestBody Map<String,Object> map) {
        if(map.get("groupId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/deleteGroupById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
