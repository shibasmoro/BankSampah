package com.example.userbanksampah.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tanggal {
    public  static Date date = new Date();
    public static Locale local = new Locale("id","ID");
    public static SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dateFormatLocal =  new SimpleDateFormat("dd-MMMM-yyyy",local);
    public static SimpleDateFormat dateFormatLocal1 =  new SimpleDateFormat("MMMM-yyyy",local);
    public static String getCurrentDate(){
        return dateFormat.format(date);
    }
}
