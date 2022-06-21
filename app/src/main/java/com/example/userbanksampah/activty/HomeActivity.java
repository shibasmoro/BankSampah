package com.example.userbanksampah.activty;


import static com.example.userbanksampah.util.PreferencesApp.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.databinding.ActivityHomeBinding;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.viewmodel.HomeviewModel;

import java.util.StringTokenizer;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding Binding;
    private HomeviewModel model;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        PreferencesApp pref = new PreferencesApp(this);

        Binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        model = new ViewModelProvider(this).get(HomeviewModel.class);

        Binding.nama.setText(PreferencesApp.getStr(PreferencesApp.Nama));

        model.getSaldo(PreferencesApp.getStr(PreferencesApp.Id));
        //model.validasiAjuan(PreferencesApp.getStr(PreferencesApp.Id));

        model.loading.observe(this,data->{
            showLoading(data);
            if (data){
                Binding.saldo.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else{
                Binding.saldo.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });

        Binding.pengajuan.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, PengajuanActivity.class)));

        // medapat saldo saat ini
      model.data.observe(this,data->{
          Binding.saldo.setText(token(FormatAngka.format(data)));
      });

        Binding.history.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, HistoryActivity.class)));


        model.pesanError.observe(this,data->showToast(data));
        Binding.historyPenarikan.setOnClickListener(view -> logout());
    }


    public void logout() {
        PreferencesApp.setStr(PreferencesApp.Id, "Kosong");
        PreferencesApp.setStr(PreferencesApp.Nama, "Kosong");
        PreferencesApp.setStr(PreferencesApp.Alamat, "Kosong");
        PreferencesApp.setInt(PreferencesApp.Kode, 1);
        startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }


    public void showLoading(Boolean isLoad){
        if (isLoad){
            Binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            Binding.progressBar.setVisibility(View.GONE);
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String token (String data){
        StringTokenizer tokenizer = new StringTokenizer(data,",");
        return  tokenizer.nextToken();

    }

}