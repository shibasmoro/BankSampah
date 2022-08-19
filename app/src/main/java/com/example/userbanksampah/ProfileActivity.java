package com.example.userbanksampah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.activty.LoginActivity;
import com.example.userbanksampah.activty.UbahPassword;
import com.example.userbanksampah.databinding.ActivityProfileBinding;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.viewmodel.HomeviewModel;
import com.example.userbanksampah.viewmodel.ProfileViewModel;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private ProfileViewModel model;
    private Boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        model = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.tvgugel.setOnClickListener(view -> logout());
        binding.etAlamat.setText(PreferencesApp.getStr(PreferencesApp.Alamat));
        binding.etUsername.setText(PreferencesApp.getStr(PreferencesApp.Nama));
        binding.etPhone.setText(PreferencesApp.getStr(PreferencesApp.Telepon));
        model.loading.observe(this, this::showLoading);
        binding.updatePassword.setOnClickListener(view -> {
            startActivity(new Intent(this, UbahPassword.class));
        });
        binding.edit.setOnClickListener(view->{
            this.edit =!this.edit;

            if (this.edit){
                Toast.makeText(this, "Edit On", Toast.LENGTH_SHORT).show();
                binding.etPhone.setEnabled(true);
                binding.etUsername.setEnabled(true);
                binding.etAlamat.setEnabled(true);
                binding.updateProfile.setVisibility(View.VISIBLE);
                binding.cancelUpdateProfile.setVisibility(View.VISIBLE);
            }else{
                Toast.makeText(this, "Edit Off", Toast.LENGTH_SHORT).show();
                binding.etPhone.setEnabled(false);
                binding.etUsername.setEnabled(false);
                binding.etAlamat.setEnabled(false);
                binding.updateProfile.setVisibility(View.GONE);
                binding.cancelUpdateProfile.setVisibility(View.GONE);
            }
        });

        binding.cancelUpdateProfile.setOnClickListener(view ->{
            this.edit =false;
            binding.etPhone.setEnabled(false);
            binding.etUsername.setEnabled(false);
            binding.etAlamat.setEnabled(false);
            binding.updateProfile.setVisibility(View.GONE);
            binding.cancelUpdateProfile.setVisibility(View.GONE);
        });

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

    private void init(){
        binding.etPhone.setEnabled(false);
        binding.etUsername.setEnabled(false);
        binding.etAlamat.setEnabled(false);
        binding.updateProfile.setVisibility(View.GONE);
        binding.cancelUpdateProfile.setVisibility(View.GONE);
    }

    public void showLoading(Boolean isLoad) {
        if (isLoad) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}