package com.example.userbanksampah.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userbanksampah.HomeActivity;
import com.example.userbanksampah.R;

public class VerifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
    }

    public void backbutton(View view) {
        startActivity(new Intent(VerifyActivity.this, HomeActivity.class));
    }
}