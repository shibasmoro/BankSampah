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
    private Mutasi mutasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding =ActivityDetilMutasiBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        mutasi =getIntent().getParcelableExtra("ID");

        Log.d("coba", "onCreate: "+mutasi.getNama_nasabah());
        model = new ViewModelProvider(this).get(DetilMutasimodel.class);
        showToast(mutasi.getNama_nasabah());

        model.getDetilMutasi(mutasi.getId_setor());
        model.data.observe(DetilMutasiActivity.this,response->{
            if (!response.isEmpty()){
                showData(response);
                Log.d("coba", "onCreate: "+response);
            }else{
                showToast("Data tidak ada");
                showData(response);
            }
        });


        Binding.namaPengaju.setText(mutasi.getNama_nasabah());
        Binding.total.setText(getString(R.string.format_angka,FormatAngka.format(mutasi.getHarga())));
        Binding.tanggalTransaksi.setText(mutasi.getTanggal());




    }

//    public void showLoading(Boolean isLoad){
//        if (isLoad){
//            Binding.progressBar.setVisibility(View.VISIBLE);
//        }else{
//            Binding.progressBar.setVisibility(View.GONE);
//        }
//    }

    public void showData(ArrayList<DetilMutasi> paramData){
        Binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DetilMutasiAdapter adapter = new DetilMutasiAdapter (this,paramData);
        Binding.recyclerView.setAdapter(adapter);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}