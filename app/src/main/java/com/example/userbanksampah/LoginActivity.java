package com.example.userbanksampah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userbanksampah.Model.Nasabah;
import com.example.userbanksampah.Retrofit.RetrofitImpl;
import com.example.userbanksampah.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static  final String MyPreferences  ="Mypref";    // tempat menyimpan data lokal
    SharedPreferences preferences;

    private ActivityLoginBinding binding;

    public static final String Id ="id";
    public static final String Nama ="nama";
    public static final String Alamat ="alamat";
    public static final String Kode ="kode";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.register.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        preferences =getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
    }

    public void login(View view) {
         if ( !is_empty(binding.username,binding.password)){

        RetrofitImpl.loginrequest().validate_login(binding.username.getText().toString(),binding.password.getText().toString()).enqueue(new Callback<Nasabah>() {
            @Override
            public void onResponse(Call<Nasabah> call, Response<Nasabah> response) {

                if (response.isSuccessful()){
                    Nasabah data_nasabah = new Nasabah();
                    data_nasabah = response.body();
                    Toast.makeText(getApplicationContext(),data_nasabah.getNama(),Toast.LENGTH_LONG).show();

                    if (data_nasabah.getNama().equals("eror")){
                        Toast.makeText(getApplicationContext(),"Username dan Password Tidak Sesuai",Toast.LENGTH_LONG).show();
                    }else if (!data_nasabah.getNama().equals("eror")){
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(Id,response.body().getId_nasabah());
                        editor.putString(Nama,response.body().getNama());
                        editor.putString(Alamat,response.body().getAlamat());
                        editor.putInt(Kode,1);
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

                    }

                }
            }

            @Override
            public void onFailure(Call<Nasabah> call, Throwable t) {

            }
        });

         }

    }

    public boolean is_empty(TextView data1,TextView data2){
        boolean param = false;
        if (data1.getText().toString().isEmpty()){
            data1.setError("Harap Masukan Username");
            param = true;

        }
        if (data2.getText().toString().isEmpty()){
            data1.setError("Harap Masukan Password");
            param = true;

        }

        return param;
    }


}