package com.example.userbanksampah;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userbanksampah.Retrofit.RetrofitImpl;
import com.example.userbanksampah.databinding.ActivityHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences preferences;

    private ActivityHomeBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding =ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        init();

    }

    public void init(){
        preferences =getSharedPreferences(LoginActivity.MyPreferences, Context.MODE_PRIVATE);
        Binding.nama.setText(preferences.getString(LoginActivity.Nama,"Kosong"));
        Binding.number.setText(preferences.getString(LoginActivity.Alamat,"Kosong"));

        RetrofitImpl.show_saldo().your_saldo(preferences.getString(LoginActivity.Id,"kosong")).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){

                    Binding.saldo.setText("Rp. "+response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}