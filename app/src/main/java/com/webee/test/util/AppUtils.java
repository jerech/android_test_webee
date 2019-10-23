package com.webee.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtils {

    public static String formatDate(Date date, String newFormat){

        SimpleDateFormat format = new SimpleDateFormat(newFormat);

        return format.format(date);
    }

    public static Date parseDate(String strDate, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
}
