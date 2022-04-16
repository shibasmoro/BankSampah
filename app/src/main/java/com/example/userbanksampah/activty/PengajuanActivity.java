package com.example.userbanksampah.activty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.databinding.ActivityVerifyBinding;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.viewmodel.VerifyViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PengajuanActivity extends AppCompatActivity {

    private VerifyViewModel model;
    private String id_param;
    private ActivityVerifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PreferencesApp pref = new PreferencesApp(this);
        id_param = PreferencesApp.getStr(PreferencesApp.Id);
        model = new ViewModelProvider(this).get(VerifyViewModel.class);
        model.getMaxTransaction(id_param);

        model.getMaxTransaction();
        model._minimal.observe(this, data -> {

            if (data <= 1000) {
                binding.verifikasi.setEnabled(false);
                binding.textView.setText("Maaf Saldo Anda Tidak cukup");
            } else {
                NumberFormat numberFormat = new DecimalFormat("#,###");
                binding.textView.setText("Rp." + numberFormat.format(data));
            }

        });
    }

    public void backbutton(View view) {
        startActivity(new Intent(this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}