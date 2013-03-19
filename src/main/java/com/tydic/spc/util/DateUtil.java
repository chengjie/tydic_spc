package com.tydic.spc.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>日期帮助类</p>
 * <p>作者： hezhong</p>
 * <p>创建日期 : 2011-2-17</p>
 */
public class DateUtil extends DateUtils {


    /**
     * 格式化日期时间
     *
     * @param date
     * @param format
     *
     * @return
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化当前日期时间
     *
     * @param format
     *
     * @return
     */
    public static String now(String format) {
        return format(new Date(), format);
    }

    /**
     * 通过日期，返回它所在的一周时间,返回形式如:02月06日-02月12日
     *
     * @param date
     *
     * @return
     */
    public static String showWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        cal.setTime(date);
        begin.setTime(date);
        end.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        begin.add(Calendar.DATE, 1 - dayOfWeek);
        end.add(Calendar.DATE, 7 - dayOfWeek);
        return format(begin.getTime(), "MM月dd日") + "-" + format(end.getTime(), "MM月dd日");
    }


    public static String tomorrow(String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return format(cal.getTime(), format);
    }
}
