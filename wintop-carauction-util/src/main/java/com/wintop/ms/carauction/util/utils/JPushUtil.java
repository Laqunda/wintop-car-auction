package com.wintop.ms.carauction.util.utils;

import cn.jpush.api.push.PushResult;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.JPushAutoData;
import com.wintop.ms.carauction.core.entity.JPushMessage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 极光消息推送使用工具类
 */
public class JPushUtil {

    private Logger logger = LoggerFactory.getLogger(JPushUtil.class);

    /***
     * 车辆信息变更推送方法
     * @param pushAutoData
     * @return
     */
    public static boolean sendAutoData(JPushAutoData pushAutoData){
        JPushMessage jPushMessage = new JPushMessage("1","1",null,null,"车辆信息变动",null,
                pushAutoData.getAutoId()+"","2", JSONObject.toJSONString(pushAutoData));
        PushResult pushResult = JPushManager.sendPushSilent(jPushMessage);
        if (pushResult!=null){
            return pushResult.isResultOK();
        }else {
            return false;
        }
    }

    /***
     * 给指定用户推送车辆消息
     * 例如：张三关注的车要开始拍卖了，需要提前给其推送消息
     * @param appId
     * @param userIdArr
     * @param title
     * @param content
     * @param autoId
     * @return
     */
    public static void sendAutoMsg(String appId,String[] userIdArr,String title,String content,String autoId){
        String pushType = "1";
        if (userIdArr!=null && userIdArr.length>0){
            for (int i=0;i<userIdArr.length;i++){
                userIdArr[i] = (appId+"_"+userIdArr[i]);
            }
            pushType = "3";
        }
        //封装推送android
        JPushMessage jPushMessage = new JPushMessage(pushType,"2",userIdArr,null,title,content, autoId,"2", null);
        JPushManager.sendPush(jPushMessage);
        //推送ios
        jPushMessage.pushClientType="3";
        JPushManager.sendPush(jPushMessage);
    }

    /***
     * 给指定用户推送系统消息
     * @param appId
     * @param userIdArr
     * @param title
     * @param content
     * @param openObjId
     * @param openObjType
     * @return
     */
    public static void sendSystemMsg(String appId,String[] userIdArr,String title,String content,String openObjId,String openObjType){
        String pushType = "1";
        if (userIdArr!=null && userIdArr.length>0){
            for (int i=0;i<userIdArr.length;i++){
                userIdArr[i] = (appId+"_"+userIdArr[i]);
            }
            pushType = "3";
        }
        //封装推送android
        JPushMessage jPushMessage = new JPushMessage(pushType,"2",userIdArr,null,title,content, openObjId,openObjType, null);
        JPushManager.sendPush(jPushMessage);
        //推送ios
        jPushMessage.pushClientType="3";
        JPushManager.sendPush(jPushMessage);
    }

    /***
     * 给订单所属人推送订单信息
     * @param appId
     * @param userIdArr
     * @param title
     * @param content
     * @param orderId
     * @return
     */
    public static void sendOrder(String appId,String[] userIdArr,String title,String content,String orderId){
        String pushType = "1";
        if (userIdArr!=null && userIdArr.length>0){
            for (int i=0;i<userIdArr.length;i++){
                userIdArr[i] = (appId+"_"+userIdArr[i]);
            }
            pushType = "3";
        }
        //封装推送android
        JPushMessage jPushMessage = new JPushMessage(pushType,"2",userIdArr,null,title,content, orderId,"4", null);
        JPushManager.sendPush(jPushMessage);
        //推送ios
        jPushMessage.pushClientType="3";
        JPushManager.sendPush(jPushMessage);
    }

    /***
     * 推送拍卖场次信息,
     * 例如：现场拍即将开拍
     * @param appId
     * @param userIdArr
     * @param title
     * @param content
     * @param auctionId
     * @return
     */
    public static void sendAuctionMsg(String appId,String[] userIdArr,String title,String content,String auctionId){
        String pushType = "1";
        if (userIdArr!=null && userIdArr.length>0){
            for (int i=0;i<userIdArr.length;i++){
                userIdArr[i] = (appId+"_"+userIdArr[i]);
            }
            pushType = "3";
        }

        //封装推送android
        JPushMessage jPushMessage = new JPushMessage(pushType,"2",userIdArr,null,title,content, auctionId,"5", null);
        JPushManager.sendPush(jPushMessage);
        //推送ios
        jPushMessage.pushClientType="3";
        JPushManager.sendPush(jPushMessage);
    }

    /***
     * 推送签约消息,
     * 例如：签约审核通过
     * @param appId     接收方所在应用
     * @param userIdArr 接收方用户id
     * @param title     标题
     * @param content   内容
     * @return
     */
    public static void sendSigMsg(String appId,String[] userIdArr,String title,String content){
        String pushType = "1";
        if (userIdArr!=null && userIdArr.length>0){
            for (int i=0;i<userIdArr.length;i++){
                userIdArr[i] = (appId+"_"+userIdArr[i]);
            }
            pushType = "3";
        }
//        封装推送android
        JPushMessage jPushMessage = new JPushMessage(pushType,"2",userIdArr,null,title,content, null,"6", null);
        JPushManager.sendPush(jPushMessage);
//        //推送ios
        jPushMessage.pushClientType="3";
        JPushManager.sendPush(jPushMessage);
    }

    /***
     * 认证消息,
     * 例如：认证审核通过
     * @param appId     接收方所在应用
     * @param userIdArr 接收方用户id
     * @param title     标题
     * @param content   内容
     * @return
     */
    public static void sendAuthMsg(String appId,String[] userIdArr,String title,String content){
        String pushType = "1";
        if (userIdArr!=null && userIdArr.length>0){
            for (int i=0;i<userIdArr.length;i++){
                userIdArr[i] = (appId+"_"+userIdArr[i]);
            }
            pushType = "3";
        }
        //封装推送android
        JPushMessage jPushMessage = new JPushMessage(pushType,"2",userIdArr,null,title,content, null,"7", null);
        JPushManager.sendPush(jPushMessage);
        //推送ios
        jPushMessage.pushClientType="3";
        JPushManager.sendPush(jPushMessage);
    }

    /***
     * 保证金
     * 例如：保证金支付成功
     * @param appId     接收方所在应用
     * @param userIdArr 接收方用户id
     * @param title     标题
     * @param content   内容
     * @return
     */
    public static void sendDeposit(String appId,String[] userIdArr,String title,String content){
        String pushType = "1";
        if (userIdArr!=null && userIdArr.length>0){
            for (int i=0;i<userIdArr.length;i++){
                userIdArr[i] = (appId+"_"+userIdArr[i]);
            }
            pushType = "3";
        }
        //封装推送android
        JPushMessage jPushMessage = new JPushMessage(pushType,"2",userIdArr,null,title,content, null,"8", null);
        JPushManager.sendPush(jPushMessage);
        //推送ios
        jPushMessage.pushClientType="3";
        JPushManager.sendPush(jPushMessage);
    }

    /***
     * 车辆状态或最高价变更调用，将变更后信息推送给客户端
     */
    @Test
    public void testSendAutoData(){
        Long nowTime = new Date().getTime();
        JPushAutoData pushAutoData = new JPushAutoData();
        pushAutoData.setAutoId(3840522660636672L);
        pushAutoData.setAuctionEndTime((nowTime)+"");
        pushAutoData.setMaxPrice(new BigDecimal(2000));
        pushAutoData.setStartingPrice(new BigDecimal(1000));
        pushAutoData.setStatus("8");
        pushAutoData.setServerTime(nowTime+"");
        pushAutoData.setMaxPriceUserId(5L);
        JPushMessage jPushMessage = new JPushMessage("1","1",null,null,"车辆标题","车辆推送内容",
                pushAutoData.getAutoId()+"","6", JSONObject.toJSONString(pushAutoData));
        PushResult pushResult = JPushManager.sendPushSilent(jPushMessage);
        logger.info(pushResult.isResultOK()+"");
    }

    @Test
    public void testSendAutoDataAll(){
        //封装，并推送给所有android客户端
        JPushMessage jPushMessage = new JPushMessage("1","2",null,null,"车辆标题","车辆推送内容",
                "1","2",null);
        PushResult pushResult = JPushManager.sendPush(jPushMessage);
        logger.info(pushResult.isResultOK()+"");
        //在推送给所有ios客户端
        jPushMessage.pushClientType="3";
        pushResult = JPushManager.sendPush(jPushMessage);
        logger.info(pushResult.isResultOK()+"");
    }

    @Test
    public void sendSigMsg(){
        JPushUtil.sendSigMsg("1234567",new String[]{"3833653830952960","3834071464060928","6"},"柠檬竞价：恭喜您签约通过！","柠檬竞价恭喜您，在线签约审核通过，您可以选择您中意的车进行竞拍了！");
    }
}