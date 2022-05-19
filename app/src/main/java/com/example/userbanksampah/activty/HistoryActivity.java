package com.example.userbanksampah.activty;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.userbanksampah.databinding.ActivityHistoryBinding;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.viewmodel.MutasiViewModel;
import com.example.userbanksampah.adapter.MutasiAdapter;
import com.example.userbanksampah.util.PreferencesApp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding Binding;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String tanggalAwal;
    private String tanggalAkhir;
    private long tanggalAwalLong;
    private long tanggalAkhirLong;
    private MutasiViewModel mutasiModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //PreferencesApp data = new PreferencesApp(this);
        super.onCreate(savedInstanceState);
        Binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        mutasiModel = new ViewModelProvider(this).get(MutasiViewModel.class);
        Binding.tanngalAwal.setOnClickListener(view -> {
            Calendar date = Calendar.getInstance();
            datePickerDialog = new DatePickerDialog(HistoryActivity.this, (view12, year, monthOfYear, dayOfMonth) -> {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                Binding.tanngalAwal.setText(dateFormatter.format(newDate.getTime()));
                tanggalAwal = dateFormatter.format(newDate.getTime());
                tanggalAwalLong = dateToMilis(tanggalAwal);


            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        Binding.tanngalAkhir.setOnClickListener(view -> {
            Calendar date = Calendar.getInstance();
            datePickerDialog = new DatePickerDialog(HistoryActivity.this, (view1, year, monthOfYear, dayOfMonth) -> {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Binding.tanngalAkhir.setText(dateFormatter.format(newDate.getTime()));
                tanggalAkhir = dateFormatter.format(newDate.getTime());
                tanggalAkhirLong = dateToMilis(tanggalAkhir);
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        Binding.btnCekMutasi.setOnClickListener(view -> {
            if (Binding.tanngalAwal.getText().toString().contains("Awal") || Binding.tanngalAwal.getText().toString().contains("Akhir")) {
                showToast("Harap masukan tanggal awal dan tanggal akhir ");

            } else if (tanggalAwalLong > tanggalAkhirLong) {
                showToast("Masukan format tanggal yang benar");
            } else {
                mutasiModel.getData(tanggalAwal, tanggalAkhir, PreferencesApp.getStr(PreferencesApp.Id));
                mutasiModel.loading.observe(HistoryActivity.this, data1 -> showLoading(data1));

                mutasiModel.data.observe(HistoryActivity.this, response -> {
                    if (response.isEmpty()) {
                        showData(response);
                        Binding.notFound.setVisibility(View.VISIBLE);
                        Binding.notFound.setText("Tidak Ada Riwayat Transaksi");
                    } else {
                        Binding.notFound.setVisibility(View.GONE);
                        showData(response);
                    }
                });
            }
        });

    }

    private Long dateToMilis(String data) {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = dateFormatter.parse(data);
            calendar.setTime(date);

        } catch (Exception e) {
            showToast(e.getMessage());
        }

        return calendar.getTimeInMillis();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showData(ArrayList<Mutasi> paramData) {
        Binding.rvMutasi.setLayoutManager(new LinearLayoutManager(this));
        MutasiAdapter adapter = new MutasiAdapter(this, paramData);
        Binding.rvMutasi.setAdapter(adapter);

    }

    public void showLoading(Boolean isLoad) {
        if (isLoad) {
            Binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            Binding.progressBar.setVisibility(View.GONE);
        }
    }

}