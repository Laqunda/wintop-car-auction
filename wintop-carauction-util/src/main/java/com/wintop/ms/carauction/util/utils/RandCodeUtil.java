package com.wintop.ms.carauction.util.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandCodeUtil {
    public static String getCouponCode(){
        String s = new Long(new Date().getTime()).toString();
        StringBuilder st=new StringBuilder(s);
        String rand = "";
        for(int i=0;i<5;i++){
            int randomInt = new Random().nextInt(9);
            rand += randomInt;
        }
        st = st.replace(3,6,rand);
        return st.toString();
    }

    public static String getSerialNumber(){
        Date date = new Date();
        String s = new SimpleDateFormat("yyyyMMdd").format(date)+date.getTime();
        String rand = "";
        for(int i=0;i<5;i++){
            int randomInt = new Random().nextInt(9);
            rand += randomInt;
        }
        return s+rand;
    }

    public static String getOrderNumber(){
        Date date = new Date();
        String s = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String rand = "";
        for(int i=0;i<5;i++){
            int randomInt = new Random().nextInt(9);
            rand += randomInt;
        }
        return s+rand;
    }

    public static String getUserCode(){
        Date date = new Date();
        String s = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String rand = "";
        for(int i=0;i<3;i++){
            int randomInt = new Random().nextInt(9);
            rand += randomInt;
        }
        return s+rand;
    }
}
