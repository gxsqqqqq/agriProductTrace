package com.trace.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateTime() {
        return format(new Date());
    }

    public static String dateTime(String format) {
        return format(new Date(), format);
    }

    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseDate(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
}
