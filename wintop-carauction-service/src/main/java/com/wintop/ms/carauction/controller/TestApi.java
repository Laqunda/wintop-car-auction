package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAgentUser;
import com.wintop.ms.carauction.service.ICarAgentUserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户微服务Controller。
 *
 * @author mike
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
@RestController
public class TestApi {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(TestApi.class);

    @Autowired
    private ICarAgentUserService agentUserService;


        @RequestMapping(value = "/service/user/add",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
                method=RequestMethod.POST,/*指定请求的method类型， GET、POST、PUT、DELETE等；*/
                headers="token",/* 指定request中必须包含某些指定的header值，才能让该方法处理请求。*/
//			params="age=20",/*指定request中必须包含某些参数值是，才让该方法处理。*/
                consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
                produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
        public ServiceResult<Boolean> check(@RequestBody CarAgentUser carAgentUser, @RequestHeader Map<String,String> headers) {
            //remove::start[]
            ServiceResult<Boolean> result = new ServiceResult<>();
            result.setResult(new Boolean(true));
        result.setMessage("查询car_agent_user成功");
        return result;
        //remove::end[return]
    }

}


