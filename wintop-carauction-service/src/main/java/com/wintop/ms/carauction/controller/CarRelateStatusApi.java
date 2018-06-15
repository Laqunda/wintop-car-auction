package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarRelateStatus;
import com.wintop.ms.carauction.service.ICarRelateStatusService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-26
 */
@RestController
@RequestMapping("/service/relateStatus")
public class CarRelateStatusApi {
    @Autowired
    private ICarRelateStatusService statusService;

    /**
     * 根据条件类型查询所有的状态
     *@Author:zhangzijuan
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "根据条件类型查询所有的状态")
    @ApiImplicitParam(dataType = "string", name = "type", value = "类型", required = true)
    @PostMapping(value = "selectAllStatus", produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarRelateStatus>> selectAllStatus(@RequestBody Map<String, Object> map){
        return statusService.selectAllStatus(map);
    }
}
