package com.wintop.ms.carauction.util.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.wintop.ms.carauction.core.config.CarJPushConfig;
import com.wintop.ms.carauction.core.entity.JPushMessage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by liangtingsen on 2018/3/7.
 * 极光推送使用
 */
public class JPushManager {


    public static JPushClient jpushClient = new JPushClient(CarJPushConfig.MASTER_SECRET, CarJPushConfig.APP_KEY,null, ClientConfig.getInstance());
    public static Logger logger = LoggerFactory.getLogger(JPushManager.class);


    public static PushResult sendPush(JPushMessage jPushMessage){

        PushResult result = null;
        try {
            /**======================根据不同的场景需要，创建不同的推送操作类=========================================*/
            PushPayload pushPayload = null;
            if (jPushMessage.pushType=="1" && jPushMessage.pushClientType=="1"){
                pushPayload = buildPushObject_all_all_alert(jPushMessage);
            }else if (jPushMessage.pushType=="1" && jPushMessage.pushClientType=="2"){
                pushPayload = buildPushObject_android_all_alert(jPushMessage);
            }else if (jPushMessage.pushType=="1" && jPushMessage.pushClientType=="3"){
                pushPayload = buildPushObject_ios_all_alert(jPushMessage);
            }else if (jPushMessage.pushType=="2" && jPushMessage.pushClientType=="1"){
                pushPayload = buildPushObject_all_tag_alert(jPushMessage);
            }else if (jPushMessage.pushType=="2" && jPushMessage.pushClientType=="2"){
                pushPayload = buildPushObject_android_tag_alertWithTitle(jPushMessage);
            }else if (jPushMessage.pushType=="2" && jPushMessage.pushClientType=="3"){
                pushPayload = buildPushObject_ios_tag_alertWithTitle(jPushMessage);
            }else if (jPushMessage.pushType=="3" && jPushMessage.pushClientType=="1"){
                pushPayload = buildPushObject_all_alias_alert(jPushMessage);
            }else if (jPushMessage.pushType=="3" && jPushMessage.pushClientType=="2"){
                pushPayload = buildPushObject_android_all_alias_alert(jPushMessage);
            }else if (jPushMessage.pushType=="3" && jPushMessage.pushClientType=="3"){
                pushPayload = buildPushObject_ios_all_alias_alert(jPushMessage);
            }
            result = jpushClient.sendPush(pushPayload);
            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.info("Connection error, should retry later");
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.info("Should review the error, and fix the request");
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }finally {
            return result;
        }
    }

    /***
     * 调用静默方式推送
     * @param jPushMessage
     * @return
     */
    public static PushResult sendPushSilent(JPushMessage jPushMessage){

        PushResult result = null;
        try {
            /**======================调用静默方式推送=========================================*/
            PushPayload pushPayload = buildPushObject_all_all_alert_silent(jPushMessage);
            logger.info(pushPayload.toString());
            result = jpushClient.sendPush(pushPayload);
            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.info("Connection error, should retry later");
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.info("Should review the error, and fix the request");
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }finally {
            return result;
        }
    }

    /***
     * 广播全部设备消息
     * @return
     */
    public static PushPayload buildPushObject_all_all_alert(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.alert(message.content))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }

    /***
     * 广播全部设备消息【静默】
     * @return
     */
    public static PushPayload buildPushObject_all_all_alert_silent(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent("").addExtras(message.extras).build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }

    /***
     * 群发消息==android
     * @return
     */
    public static PushPayload buildPushObject_android_all_alert(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(message.content,message.title,message.extras))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }

    /***
     * 群发消息==iOS
     * @return
     */
    public static PushPayload buildPushObject_ios_all_alert(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.ios(message.content, message.extras))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }

    /***
     * 根据用户别名推送==全部设备
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_all_alias_alert(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(message.aliasArr))
                .setMessage(Message.newBuilder().setMsgContent(message.content).addExtras(message.extras).setTitle(message.title).build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }

    /***
     * 根据用户别名推送==android
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_android_all_alias_alert(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(message.aliasArr))
                .setNotification(Notification.android(message.content, message.title, message.extras))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }

    /***
     * 根据用户别名推送==ios
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_ios_all_alias_alert(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias(message.aliasArr))
                .setNotification(Notification.ios(message.content, message.extras))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }

    /***
     * 根据用户分組推送==全部设备
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_all_tag_alert(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(message.tagArr))
                .setMessage(Message.newBuilder().setMsgContent(message.content).addExtras(message.extras).build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }


    /**
     * 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(message.tagArr))
                .setNotification(Notification.android(message.content, message.title, message.extras))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }
    /**
     * 构建推送对象：平台是 iOS，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_ios_tag_alertWithTitle(JPushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag(message.tagArr))
                .setNotification(Notification.android(message.content, message.title, message.extras))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(CarJPushConfig.ifProduction)
                        .build())
                .build();
    }
    @Test
    public void test() {
        java.lang.String[] aliasArr = new java.lang.String[]{"1234567_3794130041071616"};
        JPushMessage jPushMessage = new JPushMessage("1","1",null,null,"车辆竞价结束","","1","6","预留字段");
        JPushManager.sendPushSilent(jPushMessage);
    }
}