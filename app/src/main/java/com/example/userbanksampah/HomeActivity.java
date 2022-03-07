package com.example.userbanksampah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userbanksampah.Retrofit.RetrofitImpl;
import com.example.userbanksampah.Retrofit.VerifyActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private TextView nama_nasabah,number,Saldo;
    private ImageView verify,history;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        verify.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, VerifyActivity.class)));
        history.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, HistoryActivity.class)));

    }

    public void init(){
        preferences =getSharedPreferences(LoginActivity.MyPreferences, Context.MODE_PRIVATE);
        verify = findViewById(R.id.verify);
        history = findViewById(R.id.history);
        nama_nasabah = findViewById(R.id.nama);
        number = findViewById(R.id.number);
        Saldo = findViewById(R.id.saldo);
        nama_nasabah.setText(preferences.getString(LoginActivity.Nama,"kosong"));
        number.setText(preferences.getString(LoginActivity.Alamat,"Kosong"));

        RetrofitImpl.show_saldo().your_saldo(preferences.getString(LoginActivity.Id,"kosong")).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){

                    Saldo.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
    public void logout(View view) {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}