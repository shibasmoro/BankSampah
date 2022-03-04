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

    // penggunaan shared preferences
    public static  final String MyPreferences   ="Mypref";    // tempat menyimpan data lokal
    SharedPreferences preferences;

    // elemen yang akan ada pada sharedpreferences
    public static final String Id ="id";
    public static final String Nama ="nama";
    public static final String Alamat ="alamat";

    //objek view binding
    private ActivityLoginBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inisialisasi objek binding
        binding =ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textview.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        // inisialisasi objek shared preferences
        preferences =getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
    }

    public void login(View view) {
         if ( !is_empty(binding.username,binding.password)){

        RetrofitImpl.loginrequest().validate_login(binding.username.getText().toString(),binding.password.getText().toString()).enqueue(new Callback<Nasabah>() {
            @Override
            public void onResponse(Call<Nasabah> call, Response<Nasabah> response) {
                // jika api berhasil
                if (response.isSuccessful()){
                    Nasabah data_nasabah = new Nasabah();
                    data_nasabah = response.body();
                    Toast.makeText(getApplicationContext(),data_nasabah.getNama(),Toast.LENGTH_LONG).show();

                    // jika username / password salah
                    if (data_nasabah.getNama().equals("eror")){
                        Toast.makeText(getApplicationContext(),"Gagal Login",Toast.LENGTH_LONG).show();
                        // jika username /password benar
                    }else if (!data_nasabah.getNama().equals("eror")){
                        SharedPreferences.Editor editor = preferences.edit();
                        // memasukan data ke dalam shared preferences
                        editor.putString(Id,response.body().getId_nasabah());
                        editor.putString(Nama,response.body().getNama());
                        editor.putString(Alamat,response.body().getAlamat());
                        editor.commit();

                        Intent data = new Intent(LoginActivity.this ,HomeActivity.class);
                        data.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        data.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(data);


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
            data1.setError("Harap Masukan Data");
            param = true;

        }
        if (data2.getText().toString().isEmpty()){
            data2.setError("Harap Masukan Data");
            param = true;

        }
        return param;
    }


}