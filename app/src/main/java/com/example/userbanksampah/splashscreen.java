package com.example.userbanksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.userbanksampah.Activty.HomeActivity;
import com.example.userbanksampah.Activty.LoginActivity;

public class splashscreen extends AppCompatActivity {

    SharedPreferences preferences;
    private  String id;
    private int data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        preferences =getSharedPreferences(LoginActivity.MyPreferences, Context.MODE_PRIVATE);
        id   = preferences.getString(LoginActivity.Id,"Kosong");
        data = preferences.getInt("kode",0);
        if (!id.equals("Kosong")){
            Intent data = new Intent(splashscreen.this , HomeActivity.class);
            data.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            data.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(data);
        }
        else if (data == 1){
            Intent data = new Intent(splashscreen.this ,LoginActivity.class);
            data.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            data.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(data);
        }
        else{
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