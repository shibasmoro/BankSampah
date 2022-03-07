package com.example.userbanksampah.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tahun {
    private static List<String> Years = new ArrayList<>();

    private static int this_year = Calendar.getInstance().get(Calendar.YEAR);

   public static void setYears(int start_year) {
       for (int i = start_year; i <= this_year; i++) {
           Years.add(Integer.toString(i));
       }
   }
   public static List<String> getYears(){
       return Years;
   }


}
