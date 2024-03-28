package com.n7.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class ConvertTimeUtils {
    public static String convertDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public static String convertLocalDatetime(LocalDateTime localDateTime) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(localDateTime).toString();
    }
}
