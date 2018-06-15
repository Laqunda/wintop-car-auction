package com.wintop.ms.carauction.util.utils;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.PageEntity;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CarAutoUtils {

    /**
     * 获取车龄数据
     * @param carAge
     * @return
     */
    public static String[] getCarAgeArray(String carAge){
        String[] carAges = new String[2];
        if(StringUtils.isEmpty(carAge)){
            carAges[0]=null;
            carAges[1]=null;
        }else{
            String s1="(";
            String s2="[";
            String s3=")";
            String s4="]";
            String[] carAgesTemp = carAge.split(",");
            String carAge1 = carAgesTemp[0].replace(s1,">").replace(s2,">=");
            String carAge2 = null;
            if(carAgesTemp.length>1){
                carAge2 = carAgesTemp[1];
                if(carAge2.contains(s3)){
                    carAge2 = "<"+carAge2.replace(s3,"");
                }else if(carAge2.contains(s4)){
                    carAge2 = "<="+carAge2.replace(s4,"");
                }
            }
            carAges[0]=carAge1;
            carAges[1]=carAge2;
        }
        return carAges;
    }

    /**
     * 获取分页参数
     * @param obj
     * @return
     */
    public static PageEntity getPageParam(JSONObject obj){
        PageEntity pageEntity = new PageEntity();
        int page = 1;
        int pageSize = 10;
        if(obj!=null){
            if(obj.getInteger("page")!=null){
                page = obj.getIntValue("page");
            }
            if(obj.getInteger("count")!=null){
                pageSize = obj.getIntValue("count");
            }else if(obj.getInteger("limit")!=null){
                pageSize = obj.getIntValue("limit");
            }
        }
        pageEntity.setStartRowNum((page-1)*pageSize);
        pageEntity.setEndRowNum(pageSize);
        return pageEntity;
    }

    /**
     * 获取当日的开始时间和结束时间
     * @return
     */
    public static Date[] getCurrentDays() throws ParseException{
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        Date[] dates = new Date[2];
        Calendar calendar =Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(System.currentTimeMillis());
        dates[0]=format2.parse(format1.format(calendar.getTime())+" 00:00:00");
        dates[1]=format2.parse(format1.format(calendar.getTime())+" 23:59:59");
        return dates;
    }

    /**
     * 获取当前周的周一和周日
     * @return
     */
    public static Date[] getCurrentWeeks() throws ParseException{
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        Date[] dates = new Date[3];
        Calendar calendar =Calendar.getInstance(Locale.CHINA);
        dates[0]=format2.parse(format1.format(calendar.getTime())+" 00:00:00");
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        dates[1]=format2.parse(format1.format(calendar.getTime())+" 00:00:00");
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        dates[2]=format2.parse(format1.format(calendar.getTime())+" 23:59:59");
        return dates;
    }

    /**
     * 获取下一周的周一和周日
     * @return
     */
    public static Date[] getNextWeeks() throws ParseException{
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        Date[] dates = new Date[2];
        Calendar calendar =Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        dates[0]=format2.parse(format1.format(calendar.getTime())+" 00:00:00");
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        dates[1]=format2.parse(format1.format(calendar.getTime())+" 23:59:59");
        return dates;
    }

    public static void getMonday(){
        SimpleDateFormat format=new SimpleDateFormat("y年M月d日 E H时m分s秒",Locale.CHINA);
        Calendar calendar =Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        //当前时间，貌似多余，其实是为了所有可能的系统一致
        calendar.setTimeInMillis(System.currentTimeMillis());
        System.out.println("当前时间:"+format.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("周一时间:"+format.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        System.out.println("周一时间:"+format.format(calendar.getTime()));
    }

    //**生成6位随机密码
    public static String getRandomCode(){
        String code = "123456";
        //防止没有生成6位数字
        for(int i=0;i<10;i++){
            int intCode = (int)(Math.random()*1000000);
            code = String.valueOf(intCode);
            if(code.length()==6){
                break;
            }
        }
        return code;
    }

    /**
     * 获取某个日期几天后的数据
     * @param date
     * @param day
     * @return
     */
    public static Date getEndDate(Date date, int day){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    public static void main(String[] args) throws ParseException{
        getMonday();
        System.out.println(getCurrentDays()[0]);
        System.out.println(getCurrentDays()[1]);
    }
}
