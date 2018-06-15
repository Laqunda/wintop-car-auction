package com.wintop.ms.carauction.controller;




import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLevel;
import com.wintop.ms.carauction.entity.CarCustomerLevelPrice;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarCustomerLevelPriceService;
import com.wintop.ms.carauction.service.ICarCustomerLevelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:会员级别
 * @date 2018-03-15
 */
@RestController
@RequestMapping(value = "/service/level")
public class CarCustomerLevelApi {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarCustomerLevelApi.class);

    @Autowired
    private ICarCustomerLevelService levelService;

    /**
     * 保存会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:object
     */
    @ApiOperation(value = "保存会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "用户等级编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "levelName",value = "客户等级名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "depositMoney",value = "该等级所需缴纳保证金金额",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sort",value = "排序",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "isDefault",value = "'是否默认",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "isOpen",value = "是否开启",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "fareIds",value = "竞拍加价金额（加价金额id用逗号拼接）",required = false,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "saveLevel", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveLevel(@RequestBody JSONObject object){
        Logger.info("保存会员级别");
        return levelService.saveLevel(object);
    }
    /**
     *设置会员级别开启
     * @Author:zhangzijuan
     *@date 2018/3/15
     *@param:levelId
     */
    @ApiOperation(value = "设置会员级别开启")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "会员级别Id", required = true)
    @PostMapping(value = "isOpenLevel", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> isOpenLevel(@RequestBody JSONObject object){
        Logger.info("设置会员级别开启");
        return levelService.IsOpenLevel(object.getLong("levelId"));
    }

    /**
     * 删除会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     * @param object
     * @return
     */
    @ApiOperation(value = "删除会员级别")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "会员级别Id", required = true)
    @PostMapping(value = "deleteLevel", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteLevel(@RequestBody JSONObject object){
        Logger.info("删除会员级别");
        return levelService.deleteLevel(object.getLong("levelId"));
    }

    /**
     * 修改会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:object
     */
    @ApiOperation(value = "修改会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "客户级别Id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "code",value = "用户等级编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "levelName",value = "客户等级名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "depositMoney",value = "该等级所需缴纳保证金金额",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sort",value = "排序",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "isDefault",value = "'是否默认",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "isOpen",value = "是否开启",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "fareIds",value = "竞拍加价金额（加价金额id用逗号拼接）",required = false,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "updateLevel", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateLevel(@RequestBody JSONObject object){
        Logger.info("修改会员级别");
        return levelService.updateLevel(object);
    }

    /**
     * 查询会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:object
     */
    @ApiOperation(value = "查询会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "levelName",value = "客户等级名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "selectListByParam", produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarCustomerLevel>> selectListByParam(@RequestBody Map<String,Object> map){
        Logger.info("查询会员级别");
        ServiceResult<ListEntity<CarCustomerLevel>> result = new ServiceResult<>();
        try {
            ListEntity<CarCustomerLevel> listEntity = new ListEntity<>();
            Logger.info("page: "+map.get("page")+";limit:"+map.get("limit"));
            Integer page=(Integer) map.get("page");
            Integer pageSize=(Integer) map.get("limit");
            map.put("startRowNum",(page-1)*pageSize);
            map.put("endRowNum",pageSize);
            List<CarCustomerLevel> customerLevels=levelService.selectListByParam(map);
            if(customerLevels!=null && customerLevels.size()!=0){
                listEntity.setList(customerLevels);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
            listEntity.setCount(levelService.selectCountByParam(map));
            result.setResult(listEntity);
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return  result;
    }

    /**
     *设置级别为默认级别
     * @Author:zhangzijuan
     *@date 2018/3/15
     *@param:levelId
     */
    @ApiOperation(value = "设置级别为默认级别")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "会员级别Id", required = true)
    @PostMapping(value = "isDefaultLevel", produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> isDefaultLevel(@RequestBody JSONObject object){
        Logger.info("设置级别为默认级别");
        return levelService.isDefaultLevel(object.getLong("levelId"));
    }


    /**
     * 查询会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:object
     */
    @ApiOperation(value = "查询所有开启的会员级别")
    @PostMapping(value = "selectAllLevel", produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarCustomerLevel>> selectAllLevel(){
        Logger.info("查询所有会员级别");
        Map<String,Object> map =new HashMap<>();
        ServiceResult<List<CarCustomerLevel>> result = new ServiceResult<>();
        map.put("isOpen","1");
        try {
            List<CarCustomerLevel> customerLevels=levelService.selectListByParam(map);
            if(customerLevels!=null && customerLevels.size()!=0){
                result.setResult(customerLevels);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 查询会员级别详情
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:object
     */
    @ApiOperation(value = "查询会员级别详情")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "级别Id", required = true)
    @PostMapping(value = "selectLevelById", produces="application/json; charset=UTF-8")
    public ServiceResult<CarCustomerLevel> selectLevelById(@RequestBody JSONObject object){
        Logger.info("查询会员级别详情");
        Map<String,Object> map=new HashMap<>();
        map.put("levelId",object.getLong("levelId"));
        return levelService.selectLevelById(map);
    }

    }
