package com.example.userbanksampah.util;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesApp {
        public static SharedPreferences preferences;
        public static final String Id = "id";
        public static final String Nama = "nama";
        public static final String Alamat = "alamat";
        public static final String Kode = "kode";
        public static final String Telepon = "telepon";
        public static final String Password = "password";
        public final static String PREFS_NAME = "Mypref";

    public PreferencesApp(Context context) {
            preferences =  context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }

        public static void setInt(String key, int value) {
            SharedPreferences prefs = preferences;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(key, value);
            editor.apply();
        }

        public static int getInt(String key) {
            SharedPreferences prefs =preferences;
            return prefs.getInt(key, 0);
        }

        public static void setStr(String key, String value) {
            SharedPreferences prefs =preferences;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.apply();
        }

        public static String getStr(String key) {
            SharedPreferences prefs = preferences;
            return prefs.getString(key, "Kosong");
        }

        public static void setBool(String key, boolean value) {
            SharedPreferences prefs = preferences;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }

        public static boolean getBool(String key) {
            SharedPreferences prefs = preferences;
            return prefs.getBoolean(key, false);
        }
}
