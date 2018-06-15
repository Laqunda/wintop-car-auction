package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAgent;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAgentService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:代办相关
 * @date 2018-03-24
 */
@RestController
@RequestMapping("/service/carAgent")
public class CarAgentApi {
    private static final Logger logger = LoggerFactory.getLogger(BargainingAuditApi.class);
    @Resource
    private ICarAgentService carAgentService;
    @Resource
    private ICarManagerUserService managerUserService;
    /**
     * 根据参数获取代办列表
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:map
     */
    @ApiOperation(value = "根据参数获取代办列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "拍卖类型（1线上 2线下）",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "moveToWhere",value = "迁往何处（1本市外迁均可，2只能外迁，3只能本市'）",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开始时间 例如：2018-03-24",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间 例如：2018-03-24",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "status",value = "代办状态",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "selectListByParam")
    public ServiceResult<ListEntity<CarAgent>> selectListByParam(@RequestBody Map<String,Object> map){
        logger.info("根据参数获取代办列表");
        ServiceResult<ListEntity<CarAgent>> result=new ServiceResult<>();
        try {
            logger.info("page: "+map.get("page")+";limit:"+map.get("limit"));
            Integer page=(Integer) map.get("page");
            Integer pageSize=(Integer) map.get("limit");
            map.put("startRowNum",(page-1)*pageSize);
            map.put("endRowNum",pageSize);
            // 根据代办公司id过滤
            if(map.get("managerId")!=null){
                Long userId=Long.parseLong(map.get("managerId").toString());
                List<Long> storeIds = managerUserService.queryStoreScope(userId);
                map.put("storeIds",storeIds);
            }
            List<CarAgent> carAgentList=carAgentService.selectListByParam(map);
            if (carAgentList!=null && carAgentList.size()>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
            ListEntity<CarAgent> listEntity=new ListEntity<>();
            listEntity.setList(carAgentList);
            listEntity.setCount(carAgentService.countByParam(map));
            result.setResult(listEntity);
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
