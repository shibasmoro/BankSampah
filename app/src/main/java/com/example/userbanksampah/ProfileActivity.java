package com.example.userbanksampah;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userbanksampah.activty.LoginActivity;
import com.example.userbanksampah.databinding.ActivityProfileBinding;
import com.example.userbanksampah.util.PreferencesApp;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        PreferencesApp pref = new PreferencesApp(this);
        binding.tvgugel.setOnClickListener(view -> logout());
        binding.etAlamat.setText(PreferencesApp.getStr(PreferencesApp.Alamat));
        binding.etUsername.setText(PreferencesApp.getStr(PreferencesApp.Nama));
        binding.etPhone.setText(PreferencesApp.getStr(PreferencesApp.Telepon));
    }

    private void logout() {
        PreferencesApp.setStr(PreferencesApp.Id, "Kosong");
        PreferencesApp.setStr(PreferencesApp.Nama, "Kosong");
        PreferencesApp.setStr(PreferencesApp.Alamat, "Kosong");
        PreferencesApp.setInt(PreferencesApp.Kode, 2);
        startActivity(new Intent(this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}