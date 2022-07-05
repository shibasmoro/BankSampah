package com.example.userbanksampah.util;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class FormatAngka {
    private static Locale locale =new Locale("IND","ID");
    private static NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

    public static String format(int data){
         return formatter.format(data);
    }
    public static String format(float data){
        return formatter.format(data);
    }
    public static String format(Double number){
        Locale locale =new Locale("IND","ID");
        NumberFormat formatter =NumberFormat.getCurrencyInstance(locale);
        String format =formatter.format(number);
        return format;

    }
    public static String token (String data){
        StringTokenizer tokenizer = new StringTokenizer(data,",");
        return  tokenizer.nextToken();

    }
}
