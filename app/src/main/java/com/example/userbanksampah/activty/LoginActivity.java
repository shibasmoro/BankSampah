package com.example.userbanksampah.activty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.databinding.ActivityLoginBinding;
import com.example.userbanksampah.model.Nasabah;
import com.example.userbanksampah.viewmodel.LoginViewModel;
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

        loginViewModel.nasabah.observe(this, adata -> {
            if (adata.getNama().equals("eror")) {
                showToast("Username dan Password tidak sesuai");
            } else {

                showToast("login Berhasil");
                Move_To_Home(adata);
            }


        });
        loginViewModel.message.observe(this, adata -> {
            if (!adata.isEmpty()) {
                Toast.makeText(getApplicationContext(), adata, Toast.LENGTH_SHORT).show();
            }

        });

        loginViewModel.loading.observe(this,data->showLoading(data));
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
        // PreferencesApp data = new PreferencesApp(this);
        PreferencesApp.setStr(PreferencesApp.Id, nasabah.getId_nasabah());
        PreferencesApp.setStr(PreferencesApp.Nama, nasabah.getNama());
        PreferencesApp.setStr(PreferencesApp.Alamat, nasabah.getAlamat());
        PreferencesApp.setInt(PreferencesApp.Kode, 1);
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    public void showLoading(Boolean isLoad){
        if (isLoad){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.GONE);
        }
    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}