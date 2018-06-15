package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.Car;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppInfo;
import com.wintop.ms.carauction.entity.CarMessage;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarAppInfoService;
import com.wintop.ms.carauction.service.ICarMessageService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.JPushUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/6.
 * 系统消息
 */
@RestController
@RequestMapping("carMessage")
public class CarMessageApi {

    private Logger logger = LoggerFactory.getLogger(CarMessageApi.class);

    private IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarMessageService iCarMessageService;
    @Autowired
    private ICarAppInfoService appInfoService;

    /***
     * 获取本人系统消息
     * @param object
     * @return
     */
    @RequestMapping(value = "/getMyMessage",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method= RequestMethod.POST/*指定请求的method类型， GET、POST、PUT、DELETE等；*/)
    public ServiceResult<ListEntity<CarMessage>> getMyMessage(@RequestBody JSONObject object) {
        logger.info("根据用户id查询用户消息");
        ServiceResult serviceResult = new ServiceResult();
        try {
            Map<String ,Object> paramMap = new HashMap<>();
            paramMap.put("userId",object.get("userId"));
            paramMap.put("isRead",object.get("isRead"));
            Integer count = this.iCarMessageService.findByUserIdPageCount(paramMap);
            PageEntity pageEntity= CarAutoUtils.getPageParam(object);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());

            List<CarMessage> list = iCarMessageService.findByUserIdPage(paramMap);
            ListEntity<CarMessage> listEntity = new ListEntity<>();
            listEntity.setCount(count);
            listEntity.setList(list);
            serviceResult.setSuccess("0","成功");
            serviceResult.setResult(listEntity);
        }catch (Exception e){
            e.printStackTrace();
            serviceResult.setError("-1","失败");
        }finally {
            return serviceResult;
        }
    }

    /***
     * 查看详情
     * @param object
     * @return
     */
    @RequestMapping(value = "/findByIdForApp",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method= RequestMethod.POST/*指定请求的method类型， GET、POST、PUT、DELETE等；*/)
    public ServiceResult<CarMessage> findByIdForApp(@RequestBody JSONObject object) {
        logger.info("根据用户id查询用户消息");
        ServiceResult<CarMessage> result = new ServiceResult<>();
        try {
            result.setResult(iCarMessageService.findByIdForApp(object.getLong("id")));
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }


    /***
     * 获取本人系统消息数量
     * @param object
     * @return
     */
    @RequestMapping(value = "/getMyCount",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method= RequestMethod.POST/*指定请求的method类型， GET、POST、PUT、DELETE等；*/)
    public ServiceResult<Map> getMyCount(@RequestBody JSONObject object) {
        logger.info("获取本人系统消息数量");
        ServiceResult serviceResult = new ServiceResult();
        try {
            Map<String ,Object> paramMap = new HashMap<>();
            paramMap.put("userId",object.get("userId"));
            paramMap.put("isRead",object.get("isRead"));
            Integer count = this.iCarMessageService.findByUserIdPageCount(paramMap);
            Map map = new HashMap();
            map.put("count",count);
            serviceResult.setSuccess("0","成功");
            serviceResult.setResult(map);
        }catch (Exception e){
            e.printStackTrace();
            serviceResult.setError("-1","失败");
        }finally {
            return serviceResult;
        }
    }

    /***
     * 消息设置为已读
     * @param object
     * @return
     */
    @RequestMapping(value = "/readMsg",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method= RequestMethod.POST/*指定请求的method类型， GET、POST、PUT、DELETE等；*/)
    public ServiceResult readMsg(@RequestBody JSONObject object) {
        logger.info("根据用户id查询用户消息");
        ServiceResult result = new ServiceResult();
        try {
            CarMessage carMessage = new CarMessage();
            carMessage.setId(object.getLong("id"));
            carMessage.setReadTime(new Timestamp(System.currentTimeMillis()));
            carMessage.setIsRead("2");
            this.iCarMessageService.updateMsg(carMessage);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","失败");
        }finally {
            return result;
        }
    }

    @ApiOperation(value = "发送系统消息使用")
    @PostMapping(value = "sendMessageByUserId")
    public ServiceResult sendMessageByUserId(@RequestBody JSONObject object){
        ServiceResult result = new ServiceResult();
        try {
            CarMessage message = new CarMessage();
            message.setId(idWorker.nextId());
            message.setPushTime(new Timestamp(System.currentTimeMillis()));
            message.setContent(object.getString("content"));
            message.setUserId(object.getLong("userId"));
            message.setUserType(object.getString("userType"));
            message.setIsRead("1");
            message.setOpenObjType(object.getString("openObjType"));
            message.setOpenObjId(object.getLong("openObjId"));
            message.setTitle(object.getString("title"));
            iCarMessageService.saveMsg(message);
            //查找应用信息
            String appType = null;
            //调用jpush推送
            if ("1".equals(message.getUserType())){
                //买家
                appType = "2";
            }else if("2".equals(message.getUserType())){
                //卖家/中心
                appType = "1";
            }else if("3".equals(message.getUserType())){
                //代办
                appType = "3";
            }
            /***
             * 根据对应应用给客户推送消息
             */
            String openObjId = message.getOpenObjId()+"";
            if ("3".equals(message.getOpenObjType())){
                openObjId = message.getContent();
            }
            CarAppInfo appInfo = appInfoService.selectByType(appType);
            if (appInfo!=null && appInfo.getAppId()!=null) {
                JPushUtil.sendSystemMsg(appInfo.getAppId(), new String[]{message.getUserId() + ""}, message.getTitle(),
                        message.getContent(), openObjId, message.getOpenObjType() + "");
            }

            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","失败");
        }finally {
            return result;
        }
    }
}
