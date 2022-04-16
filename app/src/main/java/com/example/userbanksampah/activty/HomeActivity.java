package com.example.userbanksampah.activty;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.userbanksampah.DynamicRvAdapter;
import com.example.userbanksampah.DynamicRvModel;
import com.example.userbanksampah.adapter.KategoriSampahAdapter;
import com.example.userbanksampah.adapter.SampahAdapter;
import com.example.userbanksampah.databinding.ActivityHomeBinding;
import com.example.userbanksampah.model.KategoriSampah;
import com.example.userbanksampah.model.Sampah;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.viewmodel.HomeviewModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<DynamicRvModel> items = new ArrayList<>();
    DynamicRvAdapter dynamicRvAdapter;

    private ActivityHomeBinding Binding;
    private HomeviewModel model;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        getSupportActionBar().hide();

        PreferencesApp pref = new PreferencesApp(this);

        Binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        model = new ViewModelProvider(this).get(HomeviewModel.class);

        Binding.nama.setText(PreferencesApp.getStr(PreferencesApp.Nama));
        model.getSaldo(PreferencesApp.getStr(PreferencesApp.Id));

        // medapat saldo saat ini
        model.data.observe(this, data -> {
            //NumberFormat formatter = new DecimalFormat("#,###.##");
            Binding.saldo.setText("Rp." + FormatAngka.format(data));

        });

        model.loading.observe(this,data->{
            showLoading(data);
            if (data){
                Binding.saldo.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else{
                Binding.saldo.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });
        // mendapatkan semua kategori sampah yang ada
        model.getKategori();

        Binding.pengajuan.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, com.example.userbanksampah.activty.PengajuanActivity.class)));
        Binding.history.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, HistoryActivity.class)));

        // observe data kategori sampah
        model.dataKategori.observe(this,data->{
            setKategoriSampah(data);
        });
    }

    /*
    public void logout(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LoginActivity.Id,"Kosong");
        editor.putString(LoginActivity.Nama,"Kosong");
        editor.putString(LoginActivity.Alamat,"Kosong");
        editor.commit();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
     */

   private void setKategoriSampah(ArrayList<KategoriSampah> dataKategori){
       KategoriSampahAdapter adapter = new KategoriSampahAdapter();
       adapter.setData(dataKategori);
       Binding.rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
       Binding.rv1.setAdapter(adapter);
       adapter.setItemClick(data -> setSampah(data));
   }

   private void setSampah(KategoriSampah sampah){
        model.getSampah(sampah.getId());
        model.dataSampah.observe(this,dataSampah->{
                showDataSampah(dataSampah);
        });
   }
   private void showDataSampah(ArrayList<Sampah> data){
       SampahAdapter adapter = new SampahAdapter();
       adapter.setDataSampah(data);
       Binding.rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
       Binding.rv2.setAdapter(adapter);
   }

    public void showLoading(Boolean isLoad){
        if (isLoad){
            Binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            Binding.progressBar.setVisibility(View.GONE);
        }
    }

}