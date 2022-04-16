package com.example.userbanksampah.activty;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.userbanksampah.databinding.ActivityHistoryBinding;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.viewmodel.MutasiViewModel;
import com.example.userbanksampah.adapter.MutasiAdapter;
import com.example.userbanksampah.util.PreferencesApp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding Binding;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    // sebagai param buat cek mutasi
    String temp;

    private String tanggalAwal;
    private String tanggalAkhir;

    private final NumberFormat formatter = new DecimalFormat("#,###.##");

    private MutasiViewModel mutasiModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferencesApp data = new PreferencesApp(this);
        super.onCreate(savedInstanceState);
        Binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        mutasiModel =new ViewModelProvider(this).get(MutasiViewModel.class);


        Binding.tanngalAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar date = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(HistoryActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);



                        Binding.tanngalAwal.setText("Tanggal Awal : "+dateFormatter.format(newDate.getTime()));
                        tanggalAwal =dateFormatter.format(newDate.getTime());
                    }

                },date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        Binding.tanngalAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar date = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(HistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        Binding.tanngalAkhir.setText("Tanggal Akhir : "+dateFormatter.format(newDate.getTime()));
                        tanggalAkhir =dateFormatter.format(newDate.getTime());
                    }

                },date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        Log.d("history", "onCreate: "+temp);
        Binding.rvMutasi.setHasFixedSize(false);
        Binding.btnCekMutasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mutasiModel.getData(tanggalAwal,tanggalAkhir,PreferencesApp.getStr(PreferencesApp.Id));
                mutasiModel.data();

                mutasiModel.loading.observe(HistoryActivity.this,data->{
                    showLoading(data);
                });
                mutasiModel.data.observe(HistoryActivity.this,response->{

                    if (response.isEmpty()){
                        showData(response);
                        Binding.notFound.setVisibility(View.VISIBLE);
                        Binding.notFound.setText("Tidak Ada Riwayat Transaksi");
                    }else{
                        Binding.notFound.setVisibility(View.GONE);
                        showData(response);
                    }

                });
                /*
                mutasiModel.transaction.observe(HistoryActivity.this,data->{
                    if (data.getHarga()>0){
                        Binding.total.setText(""+formatter.format(data.getHarga()));
                        Binding.adminTugas.setText(data.getNama_admin());
                    }else{
                        Binding.total.setText("");
                        Binding.adminTugas.setText("");
                    }
                });

                 */

            }
        });

    }
    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void showData(ArrayList<Mutasi> paramData){
        Binding.rvMutasi.setLayoutManager(new LinearLayoutManager(this));
        MutasiAdapter adapter = new MutasiAdapter(paramData);
        Binding.rvMutasi.setAdapter(adapter);

    }
    public void showLoading(Boolean isLoad){
        if (isLoad){
            Binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            Binding.progressBar.setVisibility(View.GONE);
        }
    }

}