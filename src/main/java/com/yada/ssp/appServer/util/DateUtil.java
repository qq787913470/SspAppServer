package com.yada.ssp.appServer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sungang on 2017/4/6.
 */
public class DateUtil {

    private static Calendar gregorianCalendar = null;

    static {
        gregorianCalendar = new GregorianCalendar();
    }

    public static String addDay(String startDay, int date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date now = sdf.parse(startDay);
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.DAY_OF_MONTH, date);
            Date addDay = c.getTime();
            return sdf.format(addDay);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String modifyMon(String startDay, int mon) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date now = sdf.parse(startDay);
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.MONTH, mon);
            Date addDay = c.getTime();
            return sdf.format(addDay);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前日期
     *
     * @return 日期字符串 yyyyMMdd
     */
    public static String getCurDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前月
     *
     * @return 日期字符串 yyyyMM
     */
    public static String getCurMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return 日期字符串 yyyyMMdd
     */
    public static String getCurTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date());
    }

    /**
     * 获得当前的日期时间
     *
     * @return 当前的日期时间
     */
    public static String getCurDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * 获取上个月的第一天
     *
     * @return 上个月的第一天
     */
    public static String getPreviousMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(c.getTime());
    }

    /**
     * 获取两个月前的第一天
     *
     * @return 上个月的第一天
     */
    public static String getPreviousTwoMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -2);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(c.getTime());
    }

    /**
     * 获取三个月前的第一天
     *
     * @return 上个月的第一天
     */
    public static String getPreviousThreeMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(c.getTime());
    }

    /**
     * 获取上个月的最后一天
     *
     * @return 上个月的最后一天
     */
    public static String getPreviousMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(c.getTime());
    }

    /**
     * 获取两个月前的最后一天
     *
     * @return 上个月的最后一天
     */
    public static String getPreviousTwoMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -2);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(c.getTime());
    }

    /**
     * 获取三个月前的最后一天
     *
     * @return 上个月的最后一天
     */
    public static String getPreviousThreeMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(c.getTime());
    }

    /**
     * 获取上周的第一天
     * 获取本周的第一天
     *
     * @return 上周的第一天
     */
    public static String getPreviousWeekFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        //将每周第一天设为星期一，默认是星期天
        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.add(Calendar.WEEK_OF_MONTH, -1);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return sdf.format(c.getTime());
    }

    /**
     * 获取上周的最后一天
     * 获取本周的最后一天
     *
     * @return 上周的最后一天
     */
    public static String getPreviousWeekLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        //将每周第一天设为星期一，默认是星期天
        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.add(Calendar.WEEK_OF_MONTH, -1);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return sdf.format(c.getTime());
    }

    /**
     * 获取指定月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取上个月月份
     *
     * @return 上月月份
     */
    public static String getLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        return dateFormat.format(c.getTime());
    }

    /**
     * 获取昨天日期
     * @return
     */
    public static String getYesterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(c.getTime());
    }

    /**
     * 截取时间为分钟数
     *
     * @param time 时间
     * @return 分钟数 当值为-1时解析错误
     */
    public static long subTime(String time) {
        long minute = -1;
        if (time == null || time.equals("")) {
            return minute;
        }

//        102小时14分钟
//        102小时
//        14分钟
        try {
            if (time.equals("0")) {
                minute = 0;
            } else {
                if (time.contains("小时") && time.contains("分钟")) {
                    String[] string = time.split("小时");
                    minute = Long.valueOf(string[0]) * 60;
                    minute = minute + Long.valueOf(string[1].replaceAll("分钟", "").trim());
                } else if (time.contains("小时")) {
                    time = time.replaceAll("小时", "");
                    time = time.trim();
                    minute = Long.valueOf(time) * 60;
                } else if (time.contains("分钟")) {
                    time =  time.replaceAll("分钟", "");
                    time =  time.trim();
                    minute = Long.valueOf(time);
                }
            }
        } catch (Exception e) {
            minute = -1;
        }

        return minute;
    }
    /**
     * 
     * 描述:获取下一个月.
     * 
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String preMonth = dft.format(cal.getTime());
        return preMonth;
    }

    public static String getCurDateFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

}
