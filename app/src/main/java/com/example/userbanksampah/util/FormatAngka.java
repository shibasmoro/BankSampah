package com.example.userbanksampah.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatAngka {
    private static NumberFormat formatter = new DecimalFormat("#,###.##");

    public static String format(int data){
         return formatter.format(data);
    }
    public static String format(float data){
        return formatter.format(data);
    }
}
