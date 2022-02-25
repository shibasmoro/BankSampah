package com.example.userbanksampah;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userbanksampah.Retrofit.RetrofitImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private TextView nama_nasabah,number,Saldo;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

    }

    public void init(){
        preferences =getSharedPreferences(LoginActivity.MyPreferences, Context.MODE_PRIVATE);
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
}