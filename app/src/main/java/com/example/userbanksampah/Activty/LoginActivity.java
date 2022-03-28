package com.example.userbanksampah.Activty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.Model.Nasabah;
import com.example.userbanksampah.ViewModel.LoginViewModel;
import com.example.userbanksampah.databinding.ActivityLoginBinding;
import com.example.userbanksampah.util.PreferencesApp;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getNasabah().observe(this, adata -> {
            if (adata.getNama().equals("eror")) {
                Toast.makeText(getApplicationContext(), "Username dan Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
            } else {
                Move_To_Home(adata);
            }

        });
    }

    public void login(View view) {
        if (!is_empty(binding.username, binding.password)) {
            loginViewModel.login(binding.username.getText().toString(), binding.password.getText().toString());
        }
    }

    public boolean is_empty(TextView data1, TextView data2) {
        boolean param = false;
        if (data1.getText().toString().isEmpty()) {
            data1.setError("Harap Masukan Username");
            param = true;
        }
        if (data2.getText().toString().isEmpty()) {
            data2.setError("Harap Masukan Password");
            param = true;
        }
        return param;
    }

    public void Move_To_Home(Nasabah nasabah) {
        PreferencesApp data = new PreferencesApp(this);
        PreferencesApp.setStr(PreferencesApp.Id, nasabah.getId_nasabah());
        PreferencesApp.setStr(PreferencesApp.Nama, nasabah.getNama());
        PreferencesApp.setStr(PreferencesApp.Alamat, nasabah.getAlamat());
        PreferencesApp.setInt(PreferencesApp.Kode, 1);
        startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}