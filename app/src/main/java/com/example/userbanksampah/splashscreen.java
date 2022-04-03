package com.example.userbanksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.userbanksampah.activty.HomeActivity;
import com.example.userbanksampah.activty.LoginActivity;
import com.example.userbanksampah.util.PreferencesApp;

public class splashscreen extends AppCompatActivity {


    private  String id;
    private int data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        PreferencesApp pref = new PreferencesApp(this);
        id   = PreferencesApp.getStr(PreferencesApp.Id);
        data = PreferencesApp.getInt(PreferencesApp.Kode);

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