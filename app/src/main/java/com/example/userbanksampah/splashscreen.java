package com.example.userbanksampah;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class splashscreen extends AppCompatActivity {

    SharedPreferences preferences;
    private  String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        preferences =getSharedPreferences(LoginActivity.MyPreferences, Context.MODE_PRIVATE);
        id  = preferences.getString(LoginActivity.Id,"Kosong");
        if (!id.equals("Kosong")){
            Intent data = new Intent(splashscreen.this ,HomeActivity.class);
            data.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            data.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(data);
        }else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), onboarding.class));
                    finish();
                }
            }, 1000L); //3000 L = 3 detik
        }





    }


}