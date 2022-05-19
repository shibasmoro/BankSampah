package com.example.userbanksampah.activty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.userbanksampah.R;
import com.example.userbanksampah.adapter.DetilMutasiAdapter;

import com.example.userbanksampah.databinding.ActivityDetilMutasiBinding;
import com.example.userbanksampah.model.DetilMutasi;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.viewmodel.DetilMutasimodel;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DetilMutasiActivity extends AppCompatActivity {
    private ActivityDetilMutasiBinding Binding;
    private DetilMutasimodel model;
    private Mutasi mutasi;
    private long tanggalLong;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MMM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding =ActivityDetilMutasiBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        mutasi =getIntent().getParcelableExtra("ID");

        model = new ViewModelProvider(this).get(DetilMutasimodel.class);


        model.getDetilMutasi(mutasi.getId_setor());

        model.loading.observe(this,loading->showLoading(loading));
        model.pesanEror.observe(this,pesanEror->showToast(pesanEror));
        model.data.observe(DetilMutasiActivity.this,response->{

            if (!response.isEmpty()){
                showData(response);
                Log.d("coba", "onCreate: "+response);
                showLoading(false);
            }else{
                showToast("Data tidak ada");
                showData(response);

            }
        });

        tanggalLong =dateToMilis(mutasi.getTanggal());
        Binding.namaPengaju.setText(mutasi.getNama_nasabah());
        Binding.total.setText(getString(R.string.format_angka,FormatAngka.format(mutasi.getHarga())));
        Binding.tanggalTransaksi.setText(dateFormatter1.format(longToDate(tanggalLong)));

    }

    public void showLoading(Boolean isLoad){
        if (isLoad){
            Binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            Binding.progressBar.setVisibility(View.GONE);
        }
    }

    public void showData(ArrayList<DetilMutasi> paramData){
        Binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DetilMutasiAdapter adapter = new DetilMutasiAdapter (this,paramData);
        Binding.recyclerView.setAdapter(adapter);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private Long dateToMilis(String data) {
        Calendar calendar  =Calendar.getInstance();
        try {
            Date date =dateFormatter.parse(data);
            calendar.setTime(date);

        } catch (Exception e) {
            showToast(e.getMessage());
        }
        return calendar.getTimeInMillis();
    }

    private Date longToDate(long data) {
        Date date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);
        date = calendar.getTime();
        return date;

    }

}