package com.honeybee.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.haier.oms.openapi.common.utils
 * Description：
 * Author: yangjun
 * Date: Created in 2018/12/26 9:45
 * Company: haier
 */
public class DateUtils {


    /**
     * @return YYYYMMDD
     * @Description 获取当前时间的前一天
     * @params
     * @Author yangjun
     * @createDate 2018/12/26
     */
    public static String getLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String lastDate = sdf.format(date);
        return lastDate;
    }

    /**
     * @return YYYYMMDD
     * @Description 获取当前时间
     * @params
     * @Author yangjun
     * @createDate 2018/12/26
     */
    public static Map <String, String> getDay() {
        Map <String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        map.put("firstDay", date);
        map.put("today", date);
        return  map;
    }

    /**
     * @return 当前时间所在周的第一天
     * @Description
     * @params
     * @createDate 2019/4/21
     */
    public static Map getWeek() {
        Map <String, String> map = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        String today = format.format(time);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();
        String firstday = format.format(mondayDate);
        map.put("firstDay", firstday);
        map.put("today", today);
        return map;
    }

    /**
     * @return
     * @Description获取当前时间月的第一天
     * @params
     * @createDate 2019/4/21
     */
    public static Map <String, String> getMonth() {
        HashMap <String, String> map = new HashMap <>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        Date time = cale.getTime();
        String today = format.format(time);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        String firstday = format.format(cale.getTime());
        map.put("firstDay", firstday);
        map.put("today", today);
        return map;
    }

    /**
     * @return
     * @Description获取当前时间近三个月的第一天
     * @params
     * @createDate 2019/4/21
     */
    public static Map <String, String> getThreeMonth() {
        HashMap <String, String> map = new HashMap <>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        Date time = cale.getTime();
        String today = format.format(time);
        cale.add(Calendar.MONTH, -2);
        cale.set(Calendar.DAY_OF_MONTH,1);
        String firstday = format.format(cale.getTime());
        map.put("firstDay", firstday);
        map.put("today", today);
        return map;
    }

    /**
     * @return
     * @Description获取当前时间的近一年
     * @params
     * @createDate 2019/4/21
     */
    public static Map <String, String> getYear() {
        HashMap <String, String> map = new HashMap <>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        Date time = cale.getTime();
        String today = format.format(time);
        cale.set(Calendar.YEAR,cale.get(Calendar.YEAR)-1);
        cale.add(Calendar.MONTH, +1);
        cale.set(Calendar.DAY_OF_MONTH,1);
        String firstday = format.format(cale.getTime());
        map.put("firstDay", firstday);
        map.put("today", today);
        return map;
    }


    public static void main(String[] args) {

    }
}
