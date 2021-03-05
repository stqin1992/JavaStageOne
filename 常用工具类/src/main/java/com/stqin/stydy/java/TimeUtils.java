package com.stqin.stydy.java;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static SimpleDateFormat dateHourFormat = new SimpleDateFormat("yyyy-MM-dd HH");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeDetailFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @param timeStamp 数据中timeStamp时间戳
     * @return 数组[日期, 包含小时的日期]
     */
    public static String[] timestampToDate(String timeStamp) {
        String hourDate = dateHourFormat.format(new Date(Long.valueOf(timeStamp) * 1000L));
        String date = dateFormat.format(new Date(Long.valueOf(timeStamp) * 1000L));
        String[] time = new String[2];
        time[0] = date;
        time[1] = hourDate;
        return time;

    }

    /**
     * 日期显示为年-月-日 时-分-秒格式
     *
     * @param date
     * @return
     */
    public static String DateToString(Date date) {
        return timeDetailFormat.format(date);
    }

    /**
     * 时间戳显示为年-月-日 时-分-秒格式
     *
     * @param time
     * @return
     */
    public static String timeDetailToString(long time) {
        return timeDetailFormat.format(time);
    }

    public static long formatTime(long timestamp) {
        try {
            long time = dateToStamp(stampToTime(timestamp));
            return time;
        } catch (Exception e) {
            e.printStackTrace();
            return timestamp;
        }
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws Exception
     */
    public static long dateToStamp(String s) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse(s);
        long time = date.getTime();
        return time;
    }

    /**
     * 将时间戳转换为时间
     *
     * @param lt
     * @return
     */
    public static String stampToTime(long lt) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        // 将时间调整为日期样式
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
