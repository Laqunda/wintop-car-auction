package com.wintop.ms.carauction.util.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class AppidCreator {
    public static void main(String[] args) {
        System.out.println("boss appid="+ DigestUtils.md5Hex(new String("ningmengcar_boss").getBytes()));
        System.out.println("mobile appid="+DigestUtils.md5Hex(new String("ningmengcar_mobile").getBytes()));
        System.out.println("store appid="+DigestUtils.md5Hex(new  String("ningmengcar_store").getBytes()));
    }
}
