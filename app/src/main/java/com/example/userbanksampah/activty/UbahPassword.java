package com.example.userbanksampah.activty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.userbanksampah.R;
import com.example.userbanksampah.databinding.ActivityProfileBinding;
import com.example.userbanksampah.databinding.ActivityUbahPasswordBinding;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.viewmodel.ProfileViewModel;

public class UbahPassword extends AppCompatActivity {
    private ActivityUbahPasswordBinding binding;
    private ProfileViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUbahPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        model.pesanPassword.observe(this,data->{
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            if (data.contains("berhasil")){
                Toast.makeText(this, "Silahkan login kembali dengan password yang baru", Toast.LENGTH_SHORT).show();
                logout();
            }
        });
        binding.updateProfile.setOnClickListener(view->{
            model.updatePassword(binding.passwordEditText.getText().toString(), PreferencesApp.getStr(PreferencesApp.Id));
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
}