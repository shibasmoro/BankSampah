package com.example.userbanksampah.activty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.userbanksampah.R;
import com.example.userbanksampah.adapter.DetilMutasiAdapter;
import com.example.userbanksampah.adapter.MutasiAdapter;
import com.example.userbanksampah.databinding.ActivityDetilMutasiBinding;
import com.example.userbanksampah.model.DetilMutasi;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.viewmodel.DetilMutasimodel;
import com.example.userbanksampah.viewmodel.MutasiViewModel;

import java.util.ArrayList;

public class DetilMutasiActivity extends AppCompatActivity {
    private ActivityDetilMutasiBinding Binding;
    private DetilMutasimodel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding =ActivityDetilMutasiBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Mutasi mutasi = (Mutasi) getIntent().getParcelableExtra("ID");
        Log.d("coba", "onCreate: "+mutasi.getId_setor());
        model = new ViewModelProvider(this).get(DetilMutasimodel.class);

        Binding.detilAdmin.setText(mutasi.getAdmin());
        Binding.detilTanggal.setText(mutasi.getTanggal());
        Binding.detilSetor.setText(FormatAngka.format(mutasi.getHarga()));

        model.getDetilMutasi(mutasi.getId_setor());
        model.data.observe(this,data->{
            showData(data);
        });
        model.loading.observe(this ,data->{
            showLoading(data);
        });
    }

    public void showLoading(Boolean isLoad){
        if (isLoad){
            Binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            Binding.progressBar.setVisibility(View.GONE);
        }
    }

    public void showData(ArrayList<DetilMutasi> paramData){
        Binding.rvMutasi.setLayoutManager(new LinearLayoutManager(this));
        DetilMutasiAdapter adapter = new DetilMutasiAdapter (paramData);
        Binding.rvMutasi.setAdapter(adapter);
    }
}