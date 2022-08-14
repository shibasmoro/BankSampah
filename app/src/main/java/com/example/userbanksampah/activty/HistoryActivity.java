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
import com.example.userbanksampah.util.Tanggal;
import com.example.userbanksampah.viewmodel.MutasiViewModel;
import com.example.userbanksampah.adapter.MutasiAdapter;
import com.example.userbanksampah.util.PreferencesApp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding Binding;
    private DatePickerDialog datePickerDialog;
    //private SimpleDateFormat dateFormatter;
    private String tanggalAwal;
    private String tanggalAkhir;
    private long tanggalAwalLong;
    private long tanggalAkhirLong;
    private MutasiViewModel mutasiModel;
    Calendar date1 = Calendar.getInstance();
    Calendar date = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //PreferencesApp data = new PreferencesApp(this);
        super.onCreate(savedInstanceState);
        Binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        setUpDate();

        //dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        mutasiModel = new ViewModelProvider(this).get(MutasiViewModel.class);

        mutasiModel.getTanggalAwal().observe(this,tanggalAwal->{
            Binding.tanngalAwal.setText(Tanggal.dateFormatLocal.format(tanggalAwal));
            this.tanggalAwal = Tanggal.dateFormat.format(tanggalAwal);
            this.tanggalAwalLong = dateToMilis(Tanggal.dateFormat.format(tanggalAwal));
        });

        mutasiModel.getTanggalAkhir().observe(this,tanggaAkhir->{
            Binding.tanngalAkhir.setText(Tanggal.dateFormatLocal.format(tanggaAkhir));
            this.tanggalAkhir = Tanggal.dateFormat.format(tanggaAkhir);
            this.tanggalAkhirLong = dateToMilis(Tanggal.dateFormat.format(tanggaAkhir));
        });


        Binding.tanngalAwal.setOnClickListener(view -> {
            datePickerDialog = new DatePickerDialog(HistoryActivity.this, (view12, year, monthOfYear, dayOfMonth) -> {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date1.set(year,monthOfYear,dayOfMonth);

                Binding.tanngalAwal.setText(Tanggal.dateFormatLocal.format(newDate.getTime()));
                tanggalAwal = Tanggal.dateFormat.format(newDate.getTime());
                tanggalAwalLong = dateToMilis(tanggalAwal);

            }, date1.get(Calendar.YEAR),date1.get(Calendar.MONTH), date1.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        Binding.tanngalAkhir.setOnClickListener(view -> {

            datePickerDialog = new DatePickerDialog(HistoryActivity.this, (view1, year, monthOfYear, dayOfMonth) -> {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date.set(year, monthOfYear, dayOfMonth);
                Binding.tanngalAkhir.setText(Tanggal.dateFormatLocal.format(newDate.getTime()));
                tanggalAkhir = Tanggal.dateFormat.format(newDate.getTime());
                tanggalAkhirLong = dateToMilis(tanggalAkhir);
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        Binding.btnCekMutasi.setOnClickListener(view -> {


           if (tanggalAwalLong > tanggalAkhirLong) {
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

    private void setUpDate(){
        date1.get(Calendar.YEAR);
        date1.set(Calendar.MONTH,0);
        date1.set(Calendar.DAY_OF_MONTH,1);
    }

}