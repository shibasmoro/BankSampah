package com.example.userbanksampah.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tanggal {
    public  static Date date = new Date();
    public static SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");

    public static String getCurrentDate(){
        return dateFormat.format(date);
    }
}
