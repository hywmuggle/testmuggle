package com.jhtsoft.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UtilDate {
    /**
     * "yyyy-MM-dd"格式
     */
    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * yyyyMMdd 格式
     */
    public static String YYYYMMDD = "yyyyMMdd";

    /**
     * "yyyy-MM-dd HH:mm:ss"格式
     */
    public static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * "HH:mm:ss"格式
     */
    public static String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * "yyyy-MM-dd HH:mm"格式
     */
    public static String DEFAULT_DATETIME_SHOT_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm:ss EEEE 格式。 eg: 2013-01-04 14:51:57 星期五
     */
    public static String DATETIME_FORMAT_WEEK = "yyyy-MM-dd HH:mm:ss EEEE";

    /**
     * yy-MM-dd 格式为：13-12-12
     */
    public static String DEFAULT_SHORT_DATE_FORMAT = "yy-MM-dd";

    /**
     * yy-MM 格式为：13-12
     */
    public static String DEFAULT_6_DATE_FORMAT = "yyyy-MM";

    /**
     * 根据指定的格式字符串，将日期对象转化为字符串 格式：13-12-12
     *
     * @param date
     * @return
     */
    public static String formatShortDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_SHORT_DATE_FORMAT);
        return (sdf.format(date));
    }

    /**
     * 根据指定的格式字符串，将日期对象转化为字符串
     *
     * @param date    日期对象
     * @param pattern 格式字符串，默认格式为：yyyy-MM-dd
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return (sdf.format(date));
    }

    /**
     * 根据指定的格式字符串，将日期对象转化为DEFAULT_DATE_FORMAT格式的字符串
     *
     * @param date 日期对象
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 根据指定的格式军队院校符串，将日期对象转化为DEFAULT_DATETIME_SHOT_FORMAT格式的字符串
     *
     * @param date
     * @return
     */
    public static String formatShortDateTime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_SHOT_FORMAT);
    }

    /**
     * 将当前时间转化为指定格式DEFAULT_DATE_FORMAT的字符串
     *
     * @return
     */
    public static String formatDate() {
        return (formatDate(now(), DEFAULT_DATE_FORMAT));
    }

    /**
     * @param format
     * @return
     */
    public static String formatDate(String format) {
        return (formatDate(now(), format));
    }

    /**
     * 将日期对象转化为指定格式DEFAULT_DATETIME_FORMAT的字符串
     *
     * @param date 日期对象
     * @return
     */
    public static String formatDateTime(Date date) {
        return (formatDate(date, DEFAULT_DATETIME_FORMAT));
    }

    /**
     * 将指定日期转换为format字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateTime(Date date, String format) {
        return (formatDate(date, format));
    }

    /**
     * 将当前系统时间转化为指定格式DEFAULT_DATETIME_FORMAT的字符串
     *
     * @return
     */
    public static String formatDateTime() {
        return (formatDate(now(), DEFAULT_DATETIME_FORMAT));
    }

    /**
     * 将当前系统时间转化为指定格式DEFAULT_DATETIME_FORMAT的字符串
     *
     * @return
     */
    public static String formatDateTime(String format) {
        return (formatDate(now(), format));
    }

    /**
     * 从指定的日期对象中，获取指定格式DEFAULT_TIME_FORMAT的时间字符串
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return (formatDate(date, DEFAULT_TIME_FORMAT));
    }

    /**
     * 从系统当前时间获取时间信息，转化为指定格式DEFAULT_TIME_FORMAT的字符串
     *
     * @return
     */
    public static String formatTime() {
        return (formatDate(now(), DEFAULT_TIME_FORMAT));
    }

    /**
     * 格式化long型的时间
     *
     * @param datetime
     * @return
     */
    public static String formatLongDateTime(long datetime) {
        return formatDate(new Date(datetime));
    }

    /**
     * 获取系统当前日期对象
     *
     * @return
     */
    public static Date now() {
        return (new Date());
    }

    /**
     * 获取毫秒级时间戳
     *
     * @return
     */
    public static String getUnixTimeStamp() {
        String unixTimeStamp = System.currentTimeMillis() + "";
        return unixTimeStamp.trim();
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将字符串转化为默认格式DEFAULT_DATETIME_FORMAT的时间对象
     *
     * @param datetime
     * @return
     */
    public static Date parseDateTime(String datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        if ((datetime == null) || (datetime.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(datetime);
            } catch (ParseException e) {
                return parseDate(datetime);
            }
        }
    }

    /**
     * 将字符串转化为指定格式DEFAULT_DATE_FORMAT的日期对象
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

        if ((date == null) || ("".equals(date))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 将字符串转化为指定格式DEFAULT_DATE_FORMAT的日期对象
     *
     * @param time
     * @return
     */
    public static Date parseTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        if ((time == null) || ("".equals(time))) {
            return null;
        } else {
            try {
                return formatter.parse(time);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 根据传入的格式字符串，解析传入的时间值字符串
     *
     * @param timeStr   时间值字符串
     * @param formatStr 格式字符串
     * @return
     */
    public static Date parseDateByFormatStr(String timeStr, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        if ((timeStr == null) || ("".equals(timeStr))) {
            return null;
        } else {
            try {
                return formatter.parse(timeStr);
            } catch (ParseException e) {
                return parseDate(timeStr);
            }
        }
    }

    /**
     * 根据传入的格式字符串，解析传入的时间值字符串
     *
     * @param timeStr   时间值字符串
     * @param formatStr 格式字符串
     * @return
     */
    public static Date parseDateTimeByFormatStr(String timeStr, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        if ((timeStr == null) || ("".equals(timeStr))) {
            return null;
        } else {
            try {
                return formatter.parse(timeStr);
            } catch (ParseException e) {
                return parseDateTime(timeStr);
            }
        }
    }

    /**
     * @param datetime
     * @return
     */
    public static Date formatToDateTime(Date datetime) {
        if (datetime != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(datetime);
            return calendar.getTime();
        } else
            return null;
    }

    /**
     * 获取某个时间段内所有的日期字符串
     *
     * @param dfrom 起始日期
     * @param dto   结束日期
     * @return 日期字符串列表（格式：yyyy-MM-dd）
     * @throws Exception
     */
    public static List getDateList(String dfrom, String dto) throws Exception {
        List list = new ArrayList();
        list.add(dfrom);
        if (dfrom.equals(dto)) {
            return list;
        }
        Calendar c = toCalendar(dfrom);
        while (1 == 1) {
            c.add(c.DATE, 1);
            String newDate = new java.sql.Date(c.getTimeInMillis()).toString();
            if (newDate.equals(dto)) {
                list.add(newDate);
                break;
            }
            list.add(newDate);
        }
        return list;
    }

    /**
     * 获取某个时间段内所有的日期字符串
     *
     * @param dfrom 起始日期
     * @param dto   结束日期
     * @return 日期字符串列表（格式：yyyy-MM-dd）
     * @throws Exception
     */
    public static List getDateList2(String dfrom, String dto) throws Exception {
        List list = new ArrayList();
        list.add(dfrom);
        if (dfrom.equals(dto)) {
            return list;
        }
        Calendar c = toCalendar(dfrom);
        while (1 == 1) {
            c.add(c.DATE, 1);
            String newDate = new java.sql.Date(c.getTimeInMillis()).toString();
            if (newDate.equals(dto)) {
                //list.add(newDate);
                break;
            }
            list.add(newDate);
        }
        return list;
    }

    public static List<String> getDateListByMonth(String dfrom, String dto) throws Exception {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(dfrom));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(dto));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    public static List<String> getDateListByYear(String dfrom, String dto) throws Exception {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");//格式化为年

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(dfrom));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(dto));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.YEAR, 1);
        }

        return result;
    }

    public static List<String> getHourList(String dfrom, String dto) throws Exception {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化为年月日时分秒

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(dfrom));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DATE), min.get(Calendar.HOUR_OF_DAY), min.get(Calendar.SECOND));

        max.setTime(sdf.parse(dto));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DATE), max.get(Calendar.HOUR_OF_DAY), max.get(Calendar.SECOND));

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(curr.get(Calendar.HOUR_OF_DAY) + "");
            curr.add(Calendar.HOUR, 1);
        }

        return result;
    }

    /**
     * 获取指定年内的所有的日期字符串
     *
     * @param year 指点年度
     * @return 日期字符串列表（格式：yyyy-MM-dd）
     * @throws Exception
     */
    public static List getDateList4Year(String year) throws Exception {
        return getDateList(year + "-01-01", year + "-12-31");
    }

    /**
     * 周六周日判断
     *
     * @param dateString DEFAULT_DATE_FORMAT格式
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(String dateString) {
        Date date = parseDate(dateString);
        boolean weekend = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            weekend = true;
        }
        return weekend;
    }

    /**
     * 根据日期获取是周几
     *
     * @param dateString DEFAULT_DATE_FORMAT格式
     * @return 字符串1, 2, 3...., 7
     */
    public static String getWeek4Day(String dateString) {
        Date date = parseDate(dateString);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        // 一周中的第几天(国外周日是第一天,所以需要减1)
        int n = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (n < 0)
            n = 0;
        return weekDays[n];
    }

    /**
     * 将分隔符为“-”的日期字符串转换成日期
     *
     * @param datestr 日期字符串
     * @return 日期
     */
    private static Calendar toCalendar(String datestr) {
        String[] strs = datestr.split("-");
        int year = 0;
        int month = 1;
        int date = 0;
        if (strs.length >= 3) {
            year = Integer.parseInt(strs[0]);
            month = Integer.parseInt(strs[1]);
            date = Integer.parseInt(strs[2]);
        } else if (strs.length == 2) {
            year = Integer.parseInt(strs[0]);
            month = Integer.parseInt(strs[1]);
        } else
            year = Integer.parseInt(strs[0]);
        return new GregorianCalendar(year, month - 1, date);
    }

    public Date getDateByYearMonth(String year, String month) {
        String dateString = null;
        if (month.length() == 1) {
            month = "0" + month;
        }
        dateString = year + "-" + month + "-" + "01";
        return null;
    }

    /**
     * 判断year,month是否比当前月份靠后
     *
     * @param year
     * @param month
     * @return
     */
    public static boolean isAfterCurrent(String year, String month) {
        Date date1 = UtilDate.parseDate(year + "-" + month + "-01");
        Date current = UtilDate.parseDate(UtilDate.formatDate(now(), "yyyy-mm") + "-01");
        return date1.after(current);
    }

    /**
     * 获取日期中的年
     *
     * @param date 日期
     * @return 年份
     */
    public static String getYear(Date date) {
        DateFormat f_year = new SimpleDateFormat("yyyy");
        return f_year.format(date).toString();
    }

    /**
     * 根据字符串获取年
     *
     * @param date "yyyy-mm-dd"格式
     * @return
     */
    public static String getYear(String dateString) {
        Date date = UtilDate.parseDate(dateString);
        return UtilDate.getYear(date);
    }

    /**
     * 获取日期中的月
     *
     * @param date 日期
     * @return 月份
     */
    public static String getMonth(Date date) {
        DateFormat f_month = new SimpleDateFormat("MM");
        return f_month.format(date).toString();
    }

    /**
     * 获取日期中的月日
     *
     * @param date 日期
     * @return 月日MM-dd
     */
    public static String getMonthAndDay(Date date) {
        DateFormat f_month = new SimpleDateFormat("MM-dd");
        return f_month.format(date).toString();
    }

    /**
     * 根据字符串获取月
     *
     * @param date "yyyy-mm-dd"格式
     * @return
     */
    public static String getMonth(String dateString) {
        Date date = UtilDate.parseDate(dateString);
        return UtilDate.getMonth(date);
    }

    /**
     * 获取日期中天
     *
     * @param date 日期
     * @return 天
     */
    public static String getDay(Date date) {
        DateFormat f_day = new SimpleDateFormat("dd");
        return f_day.format(date).toString();
    }

    /**
     * 获取日期中的星期
     *
     * @param date 日期
     * @return 星期
     */
    public static String getWeek(Date date) {
        DateFormat f_week = new SimpleDateFormat("EEEEEEE");
        return f_week.format(date).toString();
    }

    /**
     * 获取日期中的时间
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        DateFormat f_time = new SimpleDateFormat("HH时mm分 ss秒");
        return f_time.format(date).toString();
    }

    /**
     * 把"yyyy-mm-dd"格式转换为"yyyy年mm月dd日"
     *
     * @param date
     * @param day
     * @return
     */
    public static String getChineseDateString(String dateString) {
        Date date = UtilDate.parseDate(dateString);
        DateFormat f_ch_date = new SimpleDateFormat("yyyy年MM月dd日");
        return f_ch_date.format(date).toString();
    }

    /**
     * @param times 毫秒数
     * @return yyyy-MM-dd HH:mm:ss时间
     * @title:把10位毫秒数着成时间
     */
    public static String getTimeDateString(long times) {
        SimpleDateFormat fm = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        return fm.format(times * 1000);
    }

    /**
     * @param times 毫秒数
     * @return yyyy-MM-dd HH:mm:ss时间
     * @title:把13位毫秒数着成时间
     */
    public static String getTimesDateString(long times) {
        SimpleDateFormat fm = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        return fm.format(times);
    }

    /**
     * @param times 毫秒数
     * @return yyyy-MM-dd HH:mm:ss时间
     * @title:把毫秒数着成时间
     */
    public static Date getTimeDateDate(long times) {
        SimpleDateFormat fm = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        return parseDateTime(fm.format(times * 1000));
    }

    /**
     * 指定日期后推几天到今天 如果为-表示已经在今天后面几天
     *
     * @param dateString
     * @return
     */
    public static long getRemainDay(String dateString) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat(UtilDate.DEFAULT_DATE_FORMAT);
            long diff = sd.parse(dateString).getTime() - UtilDate.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
     *
     * @param date   要加减前的时间，如果不传，则为当前日期
     * @param field  时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
     *               Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
     * @param amount 按指定时间域加减的时间数量，正数为加，负数为减。
     * @return 变动后的时间
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);

        return cal.getTime();
    }

    /**
     * 对指定的日期对象，增加（减少）指定的毫秒数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliSecond(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的秒数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSecond(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的分数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMiunte(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的小时数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的日期数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的月数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 对指定的日期对象，增加（减少）指定的年数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * 当前时间距指定时间几天
     *
     * @param dateString
     * @return
     */
    public static long getRemainDay(Date date) {
        try {
            long diff = date.getTime() - UtilDate.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 当前时间距指定时间几小时
     *
     * @param dateString
     * @return
     */
    public static long getRemainHour(Date date) {
        try {
            long diff = date.getTime() - UtilDate.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffHours;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    public static long getDiff(Date date) {
        try {
            long diff = date.getTime() - UtilDate.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diff;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    /**
     * 当前时间距指定时间几分钟
     *
     * @param dateString
     * @return
     */
    public static long getRemainMinutes(Date date) {
        try {
            long diff = date.getTime() - UtilDate.now().getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffMinutes;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // LJX 20121101 Add

    private static String STR_DAY = "天";

    private static String STR_HOUR = "小时";

    private static String STR_MINUTE = "分钟";

    private static String STR_SECOND = "秒";

    private static String STR_MILLISECOND = "毫秒";

    public static final long MILLIS_PER_SECOND = 1000L;

    public static final long MILLIS_PER_MINUTE = 60000L;

    public static final long MILLIS_PER_HOUR = 3600000L;

    public static final long MILLIS_PER_DAY = 86400000L;

    public static String getDateTimeByMs(long milliseconds) {
        return getDateTimeByMs(milliseconds, TimeType.MILLISECOND.getValue());
    }

    public enum TimeType {
        // DAY("day"),
        // HOUR("hour"),
        MINUTE("minute"), SECOND("second"), MILLISECOND("millisecond");

        private TimeType(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static String getDateTimeByMs(long milliseconds, String timeType) {
        StringBuffer sb = new StringBuffer();
        long day = Math.abs(milliseconds / MILLIS_PER_DAY);
        long hours = Math.abs((milliseconds % MILLIS_PER_DAY) / MILLIS_PER_HOUR);
        long minutes = Math.abs((milliseconds % MILLIS_PER_DAY % MILLIS_PER_HOUR) / MILLIS_PER_MINUTE);
        long s = Math.abs((milliseconds % MILLIS_PER_DAY % MILLIS_PER_HOUR % MILLIS_PER_MINUTE) / MILLIS_PER_SECOND);
        long ms = Math.abs((milliseconds % MILLIS_PER_DAY % MILLIS_PER_HOUR % MILLIS_PER_MINUTE % MILLIS_PER_SECOND));

        boolean isContainMinute = false;
        boolean isContainMillisecond = false;
        boolean isCOntainSecond = false;

        if (timeType.equalsIgnoreCase(TimeType.MINUTE.getValue())) {
            isContainMinute = true;
        } else if (timeType.equalsIgnoreCase(TimeType.SECOND.getValue())) {
            isCOntainSecond = true;
        } else if (timeType.equalsIgnoreCase(TimeType.MILLISECOND.getValue())) {
            isContainMillisecond = true;
        }

        if (milliseconds < 0) {
            sb.append(" - ");
        }

        if (day > 0) {
            sb.append(String.valueOf(day) + STR_DAY);
        }
        if (hours > 0) {
            sb.append(String.valueOf(hours) + STR_HOUR);
        }
        if (isContainMinute) {
            if (minutes > 0) {
                sb.append(String.valueOf(minutes) + STR_MINUTE);
            }
        } else {
            if (isCOntainSecond) {
                if (minutes > 0) {
                    sb.append(String.valueOf(minutes) + STR_MINUTE);
                }
                if (s > 0) {
                    sb.append(String.valueOf(s) + STR_SECOND);
                }
            } else {
                if (isContainMillisecond) {
                    if (minutes > 0) {
                        sb.append(String.valueOf(minutes) + STR_MINUTE);
                    }
                    if (s > 0) {
                        sb.append(String.valueOf(s) + STR_SECOND);
                    }
                    if (ms > 0) {
                        sb.append(String.valueOf(ms) + STR_MILLISECOND);
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * 获取两日期的相隔时间
     *
     * @param dateEnd
     * @param dateBegin
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static long getBetweenDays(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1;
        }

        long diff = dateEnd.getTime() - dateBegin.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }

    /**
     * 获取两日期的相隔小时
     *
     * @param dateEnd
     * @param dateBegin
     * @return
     */
    public static double getBetweenHours(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1d;
        }
        DecimalFormat df = new DecimalFormat("0.0");
        long diff = dateEnd.getTime() - dateBegin.getTime();
        double diffDays = diff / (60 * 60 * 1000);
        return Double.valueOf(df.format(diffDays));
    }

    /**
     * 获取两日期的相隔小时(无精确值)
     *
     * @param dateEnd
     * @param dateBegin
     * @return
     */
    public static double getBetweenHours2(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1d;
        }
        long diff = dateEnd.getTime() - dateBegin.getTime();
        double diffDays = diff / (double) (60 * 60 * 1000);
        return diffDays;
    }

    /**
     * 获取两日期的相隔时间(只算天的部分)
     *
     * @param dateEnd
     * @param dateBegin
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static long getBetweenDaysByDay(Date dateEnd, Date dateBegin) {
        if (null == dateEnd || null == dateBegin) {
            return -1;
        }
        Date tempBegin = new Date(dateBegin.getTime());
        dateEnd.setHours(0);
        dateEnd.setMinutes(0);
        dateEnd.setSeconds(0);
        tempBegin.setHours(0);
        tempBegin.setMinutes(0);
        tempBegin.setSeconds(0);
        long diff = dateEnd.getTime() - tempBegin.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }

    /**
     * 根据日期获取是周几
     *
     * @param dateString DEFAULT_DATE_FORMAT格式
     * @return 字符串1, 2, 3...., 7
     */
    public static String getWeek4Day(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        // 一周中的第几天(国外周日是第一天,所以需要减1)
        int n = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (n < 0)
            n = 0;
        return weekDays[n];
    }

    /**
     * 取默认最小时间
     *
     * @returnDate
     */
    public static Date getMinDate() {
        return parseDateByFormatStr("1970-01-01 00:00:00", DEFAULT_DATETIME_FORMAT);
    }

    /**
     * @return String
     * @throws
     * @title 返回格式化的当前时间
     * @description <br>
     * <pre>
     * 业务逻辑描述：
     * </pre>
     */
    public static String formatNow() {
        return formatDateTime(now());
    }

    /**
     * @return Date
     * @title:获取当前日期，时分秒都为0
     * @throws:
     * @description:获取当前日期，时分秒都为0 <pre>
     * 业务逻辑描述：获取当前日期，时分秒都为0
     * </pre>
     */
    public static Date getDateNoTime() {
        return UtilDate.parseDate(UtilDate.formatDate());
    }

    public static Date getMonthFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    public static Date getMonthLast(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // 设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取指定日期年的最后一天
     *
     * @param date
     * @return
     */
    public static Date getYearLast(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取指定日期年的第一天
     *
     * @param date
     * @return
     */
    public static Date getYearFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取某年某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getMonthFisrt(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getMonthLast(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 根据指定的格式字符串，将日期对象转化为DEFAULT_6_DATE_FORMAT格式的字符串
     *
     * @param date 日期对象
     * @return
     */
    public static String formatDateToYearMonth(Date date) {
        return formatDate(date, DEFAULT_6_DATE_FORMAT);
    }

    /**
     * 获取日期的前一天
     */
    public static Date getPreDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    public static void main(String[] args) {
        String ss = "2016-02";
        System.out.println(ss.substring(0, 4));
        System.out.println(ss.substring(5, 7));
        System.out.println(getMonthFisrt(2016, 2));
        System.out.println(Integer.valueOf("02"));
	/*	String str = getDateTimeByMs(999939, TimeType.MILLISECOND.getValue());
		System.out.println(str);
		str = getDateTimeByMs(999939444444l, TimeType.SECOND.getValue());
		System.out.println(str);
		str = getDateTimeByMs(999939, "");
		System.out.println(str);*/
        Date date = new Date();
        System.out.println(formatDateTime(date));
        System.out.println(formatDateTime(addDay(date, 29)));

        System.out.println("" + getBetweenDays(addHour(date, -1), date));
        Date d = addHour(date, -1);
        if (date.getTime() > d.getTime()) {
            System.out.println("t");
        }
        Map<String, String> map = new HashMap<String, String>();
//	    map.put("success", "success");
        map.put("failure", "评价已经回复过啦！");
        if (map.containsKey("failure")) {
            System.out.println(map.get("failure"));
        }
    }

}
