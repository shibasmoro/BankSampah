package com.example.userbanksampah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.activty.LoginActivity;
import com.example.userbanksampah.databinding.ActivityProfileBinding;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.viewmodel.HomeviewModel;
import com.example.userbanksampah.viewmodel.ProfileViewModel;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private ProfileViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(ProfileViewModel.class);
        PreferencesApp pref = new PreferencesApp(this);
        binding.tvgugel.setOnClickListener(view -> logout());
        binding.etAlamat.setText(PreferencesApp.getStr(PreferencesApp.Alamat));
        binding.etUsername.setText(PreferencesApp.getStr(PreferencesApp.Nama));
        binding.etPhone.setText(PreferencesApp.getStr(PreferencesApp.Telepon));
        model.loading.observe(this, this::showLoading);

        model.pesan.observe(this,data->{
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            if (data.contains("berhasil")){
                PreferencesApp.setStr(PreferencesApp.Nama,binding.etUsername.getText().toString());
                PreferencesApp.setStr(PreferencesApp.Alamat, binding.etAlamat.getText().toString());
                PreferencesApp.setStr(PreferencesApp.Telepon, binding.etPhone.getText().toString());
                finish();
            }
        });
        binding.updateProfile.setOnClickListener(view -> {
            model.updateData(binding.etUsername.getText().toString(),binding.etPhone.getText().toString(),binding.etAlamat.getText().toString(),PreferencesApp.getStr(PreferencesApp.Id));

        });

    }

    private void logout() {
        PreferencesApp.setStr(PreferencesApp.Id, "Kosong");
        PreferencesApp.setStr(PreferencesApp.Nama, "Kosong");
        PreferencesApp.setStr(PreferencesApp.Alamat, "Kosong");
        PreferencesApp.setInt(PreferencesApp.Kode, 2);
        startActivity(new Intent(this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();


    }

    private void update(){

    }

    public void showLoading(Boolean isLoad) {
        if (isLoad) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}