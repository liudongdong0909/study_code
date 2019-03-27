package com.study.excel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author walle
 */
public class DateFormatThreadLocal {

    public static final ThreadLocal<DateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }

    public static String format(Date date) {
        return df.get().format(date);
    }
}
