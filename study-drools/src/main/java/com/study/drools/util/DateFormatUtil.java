package com.study.drools.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author walle
 */
public class DateFormatUtil {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * 判断指定时间是否在指定区间
     *
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBetween(String nowTime, String startTime, String endTime) {
        LocalTime nowLocalTime = LocalTime.parse(nowTime, dateTimeFormatter);
        LocalTime startLocalTime = LocalTime.parse(startTime, dateTimeFormatter);
        LocalTime endLocalTime = LocalTime.parse(endTime, dateTimeFormatter);

        return (startLocalTime.isBefore(nowLocalTime)) && (nowLocalTime.isBefore(endLocalTime));
    }
}
