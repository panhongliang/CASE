package com.phl.opentsdb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期操作工具类
 * 
 * @ClassName DateUtil
 * @CreateDate 2014-3-19
 * @Version V1.0
 */
public final class DateUtil {

    private DateUtil() {
    }
    /***/
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /***/
    public static final String YYYYMMDD = "yyyyMMdd";
    /***/
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /***/
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /***/
    public static final String YYYY_MM_DD_HH_MM_DASH = "yyyy-MM-dd_HH:mm";
    /***/
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /***/
    public static final String YYYY_MM = "yyyy-MM";
    /***/
    public static final String HH_MM_SS = "HH:mm:ss";
    /***/
    public static final String YYYYMMDDHH = "yyyyMMddHH";
    /***/
    public static final String YYMMDDHHMMSS = "yyMMddHHmmss";
    /***/
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    /***/
    public static final String YYMMDDHHMM = "yyMMddHHmm";
    /***/
    public static final String HHMMSS = "HHmmss";

    /**
    * @Title: formatToString 
    * @Description: 根据指定格式转换成日期字符串
    * @param date Date
    * @param format String
    * @return String
     */
    public static String formatToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
    * @Title: getTodayDate 
    * @Description: 获取今天日期yyyy-MM-dd
    * @return String
     */
    public static String getTodayDate() {
        return formatToString(new Date(), YYYY_MM_DD);
    }
    
    /**
    * @Title: getTodayDateDate 
    * @return Date
     */
    public static Date getTodayDateDate() {
        return parseToDate(formatToString(new Date(), YYYY_MM_DD), YYYY_MM_DD);
    }

    /**
    * @Title: getNow 
    * @Description: 获取当前时间 yyyy-MM-dd HH:mm:ss
    * @return String
     */
    public static String getNow() {
        return formatToString(new Date(), YYYY_MM_DD_HH_MM_SS);
    }
    
    public static Date getNowDate() {
        return parseToDate(formatToString(new Date(), YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
    }
    
    /**
    * @Title: parseToDate 
    * @param s String
    * @param format String
    * @return Date
     */
    public static Date parseToDate(String s, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
    * @Title: parseToDateTry 
    * @param s String
    * @return Date
     */
    public static Date parseToDateTry(String s) {
        Date v = null;
        if (s.length() == DateUtil.YYYY_MM_DD.length()) {
            v = DateUtil.parseToDate(s, DateUtil.YYYY_MM_DD);
        } else if (s.length() == DateUtil.YYYY_MM_DD_HH_MM_SS.length()) {
            v = DateUtil.parseToDate(s, DateUtil.YYYY_MM_DD_HH_MM_SS);
        } else if (s.length() == DateUtil.YYYYMMDDHHMMSS.length()) {
            v = DateUtil.parseToDate(s, DateUtil.YYYYMMDDHHMMSS);
        } else if (s.length() == DateUtil.YYYYMMDD.length()) {
            v = DateUtil.parseToDate(s, DateUtil.YYYYMMDD);
        } else if (s.length() == "yyyy/MM/dd HH:mm".length()) {
            v = DateUtil.parseToDate(s, "yyyy/MM/dd HH:mm");
        } else {
            System.err.println("Unsupported date string format: " + s);
            return v;
        }
        return v;
    }

    /**
     * 格式化毫秒数
     * 
     * @param time
     *            毫秒数
     * @return HH:MM:ss
     */
    public static String formatMillisecond(long time) {
        time = time / 1000;
        long hh = time / 60 / 60;
        long mm = time / 60 % 60;
        long ss = time % 60;
        return (hh < 10 ? "0" + hh : hh) + ":" + (mm < 10 ? "0" + mm : mm) + ":" + (ss < 10 ? "0" + ss : ss);
    }

    // 判断日期格式是否正确
    /**
    * @Title: checkDate 
    * @Description: 判断日期格式是否正确
    * @param date String
    * @param format String
    * @return boolean
     */
    public static boolean checkDate(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = df.parse(date);
        } catch (Exception e) {
            // 如果不能转换,肯定是错误格式
            return false;
        }
        String s1 = df.format(d);
        // 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
        // "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
        // 逻辑上不对.
        return date.equals(s1);
    }

    // 判断字符串是否是合法日期格式
    /**
    * @Title: checkStrDate 
    * @Description: 判断字符串是否是合法日期格式
    * @param format String
    * @return boolean
     */
    public static boolean checkStrDate(String format) {
        try {
            parseToDateTry(format);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * excel日期解析 返回Date
     * */
    /**
    * @Title: convertDate4JXL 
    * @Description: excel日期解析 返回Date
    * @param jxlDate Date
    * @return Date
     */
    public static Date convertDate4JXL(Date jxlDate) {
        if (jxlDate == null) {
            return null;
        }
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dateFormat.setTimeZone(gmt);
        String str = dateFormat.format(jxlDate);
        TimeZone local = TimeZone.getDefault();
        dateFormat.setTimeZone(local);
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * excel日期解析 返回String
     * */
    /**
    * @Title: strDate4JXL 
    * @Description: excel日期解析 返回String
    * @param jxlDate Date
    * @return String
     */
    public static String strDate4JXL(Date jxlDate) {
        if (jxlDate == null) {
            return "";
        }
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dateFormat.setTimeZone(gmt);
        return dateFormat.format(jxlDate);
    }

    /**
    * @Title: getMonthLastDay 
    * @Description: 返回一个月份的最后一天
    * @param time String
    * @return String
     */
    public static String getMonthLastDay(String time) {
        int yy = Integer.parseInt(time.substring(0, 4));
        int mm = Integer.parseInt(time.substring(5));

        String endTime = "";
        boolean r = yy % 4 == 0 && yy % 100 != 0 || yy % 400 == 0;
        if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
            endTime = time + "-31";
        } else if (mm != 2) {
            endTime = time + "-30";
        } else {
            if (r) {
                endTime = time + "-29";
            } else {
                endTime = time + "-28";
            }
        }
        return endTime;
    }
    
    

    
    /**
    * @Title: dayAdd 
    * @param dateStr String
    * @param plus int
    * @return String
     */
    public static String dayAdd(String dateStr, int plus) {
        Date date = parseToDate(dateStr, YYYY_MM_DD);
        Date resultDate = new Date(date.getTime() + plus * 24 * 60 * 60 * 1000);
        return formatToString(resultDate, YYYY_MM_DD);
    }

    /**
     * @Title: getDayOfMethod
     * @Description: 获得当前是这个月份的第几天
     * @param: @return
     * @return String
     */
    public static String getDayOfMethod() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            return "D0" + day;
        } else {
            return "D" + day;
        }
    }
    

    /**
     * 获取指定日期上周的周日
     * 
     * @param date        日期
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 如果是周日
        int off = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            off = -7;
        }
        c.add(Calendar.DATE, off - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    /**
     * 获取指定日期当周的周日
     *
     * @param date        日期
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 如果是周日
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            return  date;
        }
        c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }
    
    /**
     * 获取指定日期上周的周一
     * 
     * @param date        日期
     */
    public static Date getFirstDayOfLastWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 如果是周日
        int off = -6;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            off = -13;
        }
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.add(Calendar.DATE, off - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }
    
    /**
     * 获取指定日期当周的周一
     * 
     * @param date        日期
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 如果是周日
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DAY_OF_MONTH, -1);  
        }
        c.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        int day = c.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
        c.add(Calendar.DATE, c.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
        return c.getTime();
    }
    
    /**
     * 获取指定日期上月的月末
     * 
     * @param date        日期
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,0);
        return c.getTime();
    }

    /**
     * 获取指定日期当月的月末
     *
     * @param date        日期
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH,0);
        return c.getTime();
    }

    /**
     * 获取指定日期上月的月初
     *
     * @param date        日期
     */
    public static Date getFirstDayOfLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,0);
        c.setTime(c.getTime());
        c.set(Calendar.DAY_OF_MONTH,1);
        return c.getTime();
    }

    /**
     * 获取指定日期上月的月初
     *
     * @param date        日期
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,1);
        return c.getTime();
    }

    public static Date dayAdd(Date date, int plus) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, plus);
        return c.getTime();
    }

   public static void main(String[] s) {
       System.out.println(getFirstDayOfMonth(new Date(1501481411000L)));
    }


}
