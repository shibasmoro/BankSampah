package com.example.userbanksampah.activty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.userbanksampah.R;
import com.example.userbanksampah.adapter.MutasiAdapter;
import com.example.userbanksampah.adapter.MutasiPenarikanAdapter;
import com.example.userbanksampah.databinding.ActivityMutasiPenarikanBinding;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.util.Tanggal;
import com.example.userbanksampah.viewmodel.MutasiPenarikanViewModel;
import com.example.userbanksampah.viewmodel.MutasiViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MutasiPenarikan extends AppCompatActivity {
    private ActivityMutasiPenarikanBinding binding;
    private MutasiPenarikanViewModel model;
    private DatePickerDialog datePickerDialog;
    private String tanggalAwal;
    private String tanggalAkhir;
    private long tanggalAwalLong;
    private long tanggalAkhirLong;
    Calendar date1 = Calendar.getInstance();
    Calendar date = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMutasiPenarikanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpDate();
        model =new ViewModelProvider(this).get(MutasiPenarikanViewModel.class);

        model.loading.observe(this,loading->{
            showLoading(loading);
        });

        model.getTanggalAwal().observe(this,tanggalAwal->{
            binding.tanggalawal.setText(Tanggal.dateFormatLocal.format(tanggalAwal));
            this.tanggalAwal = Tanggal.dateFormat.format(tanggalAwal);
            this.tanggalAwalLong = dateToMilis(Tanggal.dateFormat.format(tanggalAwal));
        });

        model.getTanggalAkhir().observe(this,tanggaAkhir->{
            binding.tanggalakhir.setText(Tanggal.dateFormatLocal.format(tanggaAkhir));
            this.tanggalAkhir = Tanggal.dateFormat.format(tanggaAkhir);
            this.tanggalAkhirLong = dateToMilis(Tanggal.dateFormat.format(tanggaAkhir));
        });


        model.data.observe(this,data->{
            if (data.size()>0){
                showData(data);
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.tvmutasiNotfound.setVisibility(View.GONE);
            }else{
                binding.recyclerView.setVisibility(View.GONE);
                binding.tvmutasiNotfound.setVisibility(View.VISIBLE);
            }
        });

        binding.tanggalawal.setOnClickListener(view -> {
            datePickerDialog = new DatePickerDialog(this, (view12, year, monthOfYear, dayOfMonth) -> {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date1.set(year,monthOfYear,dayOfMonth);

                binding.tanggalawal.setText(Tanggal.dateFormatLocal.format(newDate.getTime()));
                tanggalAwal = Tanggal.dateFormat.format(newDate.getTime());
                tanggalAwalLong = dateToMilis(tanggalAwal);

            }, date1.get(Calendar.YEAR),date1.get(Calendar.MONTH), date1.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        binding.tanggalakhir.setOnClickListener(view -> {
            datePickerDialog = new DatePickerDialog(this, (view1, year, monthOfYear, dayOfMonth) -> {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date.set(year, monthOfYear, dayOfMonth);
                binding.tanggalakhir.setText(Tanggal.dateFormatLocal.format(newDate.getTime()));
                tanggalAkhir = Tanggal.dateFormat.format(newDate.getTime());
                tanggalAkhirLong = dateToMilis(tanggalAkhir);
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
        binding.cariRiwayat.setOnClickListener(view->{
            model.getMutasi(this.tanggalAwal,this.tanggalAkhir, PreferencesApp.getStr(PreferencesApp.Id));
        });

    }

    public void showData(ArrayList<com.example.userbanksampah.model.MutasiPenarikan> paramData) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MutasiPenarikanAdapter adapter = new MutasiPenarikanAdapter(this, paramData);
        binding.recyclerView.setAdapter(adapter);
    }

    public void showLoading(Boolean isLoad) {
        if (isLoad) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private Long dateToMilis(String data) {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = Tanggal.dateFormat.parse(data);
            calendar.setTime(date);

        } catch (Exception e) {
            showToast(e.getMessage());
        }

        return calendar.getTimeInMillis();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setUpDate(){
        date1.get(Calendar.YEAR);
        date1.set(Calendar.MONTH,0);
        date1.set(Calendar.DAY_OF_MONTH,1);
    }
}